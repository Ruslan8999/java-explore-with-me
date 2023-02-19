package ru.practicum.mainserver.comment.service;

import ru.practicum.mainserver.comment.dto.CommentInDto;
import ru.practicum.mainserver.comment.dto.CommentOutDto;

public interface CommentService {

    CommentOutDto createComment(CommentInDto commentInDto, Long userId, Long eventId);

    CommentOutDto updateComment(CommentInDto commentInDto, Long commentId);

    void deleteComment(Long commentId);
}
