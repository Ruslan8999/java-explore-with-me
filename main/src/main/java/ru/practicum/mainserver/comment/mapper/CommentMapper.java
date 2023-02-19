package ru.practicum.mainserver.comment.mapper;

import ru.practicum.mainserver.comment.dto.CommentInDto;
import ru.practicum.mainserver.comment.dto.CommentOutDto;
import ru.practicum.mainserver.comment.model.Comment;

public class CommentMapper {
    public static CommentOutDto toCommentDto(Comment comment) {
        return CommentOutDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorName(comment.getAuthor().getName())
                .created(comment.getCreatedOn())
                .build();
    }

    public static Comment toComment(Comment comment, CommentInDto commentInDto) {
        if (commentInDto.getText() != null) comment.setText(commentInDto.getText());
        return comment;
    }
}