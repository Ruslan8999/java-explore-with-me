package ru.practicum.mainserver.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainserver.comment.dto.CommentInDto;
import ru.practicum.mainserver.comment.dto.CommentOutDto;
import ru.practicum.mainserver.comment.mapper.CommentMapper;
import ru.practicum.mainserver.comment.model.Comment;
import ru.practicum.mainserver.comment.repository.CommentRepository;
import ru.practicum.mainserver.events.model.Event;
import ru.practicum.mainserver.events.repository.EventRepository;
import ru.practicum.mainserver.exceptions.ResourceNotFoundException;
import ru.practicum.mainserver.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


    @Override
    public CommentOutDto createComment(CommentInDto commentInDto, Long userId, Long eventId) {
        Comment newComment = CommentMapper.toComment(new Comment(), commentInDto);
        newComment.setCreatedOn(LocalDateTime.now());
        var owner = userRepository.findById(userId);
        if (owner.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with id=%d not found", userId));
        } else {
            newComment.setAuthor(owner.get());
        }
        var event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item with id=%d not found", eventId)));
        Comment createdComment = commentRepository.save(newComment);
        event.addComment(createdComment);
        eventRepository.save(event);
        log.info("Comment created" + createdComment);
        return CommentMapper.toCommentDto(createdComment);
    }


    @Override
    public CommentOutDto updateComment(CommentInDto commentInDto, Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("comment with id=%d not found", commentId)));
        CommentMapper.toComment(comment, commentInDto);
        Comment updatedComment = commentRepository.save(comment);
        log.info("Comment created" + updatedComment);

        return CommentMapper.toCommentDto(updatedComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("comment with id=%d not found", commentId)));
        commentRepository.delete(comment);
        log.info(String.format("Comment with id=%d deleted", commentId));
    }

    @Override
    public List<CommentOutDto> getCommentOfUser(Long commentId, Long userId) {
        var owner = userRepository.findById(userId);
        if (owner.isEmpty()) throw new ResourceNotFoundException(String.format("User with id=%d not found", userId));

        return commentRepository.findAllCommentsByAuthorId(userId).stream()
                .filter(c -> c.getId().equals(commentId))
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentOutDto> getCommentOfUserAndEvent(Long userId, Long eventId) {
        List<Comment> result = new ArrayList<>();
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item with id=%d not found", eventId)));
        List<Comment> comment = commentRepository.findAllCommentsByAuthorId(userId);
        for (Comment com : event.getComments()) {
            if (comment.contains(com)) {
                result.add(com);
            } else {
                throw new ResourceNotFoundException(String.format("User with id=%d not found", userId));
            }
        }
        return result.stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }
}
