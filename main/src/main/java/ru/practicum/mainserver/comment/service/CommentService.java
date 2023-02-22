package ru.practicum.mainserver.comment.service;

import ru.practicum.mainserver.comment.dto.CommentInDto;
import ru.practicum.mainserver.comment.dto.CommentOutDto;

import java.util.List;

public interface CommentService {

    CommentOutDto createComment(CommentInDto commentInDto, Long userId, Long eventId);

    CommentOutDto updateComment(CommentInDto commentInDto, Long commentId);

    void deleteComment(Long commentId);

    List<CommentOutDto> getCommentOfUser(Long commentId, Long userId);

    List<CommentOutDto> getCommentOfUserAndEvent(Long userId, Long eventId);
}
