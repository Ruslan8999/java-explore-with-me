package ru.practicum.mainserver.comment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.comment.dto.CommentInDto;
import ru.practicum.mainserver.comment.dto.CommentOutDto;
import ru.practicum.mainserver.comment.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users/comments")
@RequiredArgsConstructor
public class PrivateCommentsController {

    private final CommentService commentService;


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{commentId}")
    public void deleteCommentPrivate(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

    @PatchMapping("/{commentId}")
    public CommentOutDto updateCommentPrivate(@Valid @RequestBody CommentInDto commentInDto,
                                              @PathVariable Long commentId) {
        return commentService.updateComment(commentInDto, commentId);
    }

    @GetMapping("/{commentId}/{userId}")
    public List<CommentOutDto> getCommentOfUser(@PathVariable Long commentId, @PathVariable Long userId) {
        return commentService.getCommentOfUser(commentId, userId);
    }

}
