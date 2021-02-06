package com.eduardo.spoilerappnetwork.comment.repository;

import com.eduardo.spoilerappnetwork.comment.entity.Comment;
import com.eduardo.spoilerappnetwork.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndAuthor(Long id, User user);
}
