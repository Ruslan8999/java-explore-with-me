package ru.practicum.mainserver.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.comment.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
