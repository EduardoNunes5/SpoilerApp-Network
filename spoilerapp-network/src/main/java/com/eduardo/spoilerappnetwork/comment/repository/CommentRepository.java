package com.eduardo.spoilerappnetwork.comment.repository;

import com.eduardo.spoilerappnetwork.comment.entity.Comment;
import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import com.eduardo.spoilerappnetwork.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndAuthor(Long id, User user);
    void deleteByIdAndAuthor(Long id, User user);
    List<Comment> findAllBySpoilerId(Long spoilerId);
    Optional<Comment> findByIdAndAuthorAndSpoiler(Long id, User user, Spoiler spoiler);
    @Query("select case when count(c) > 0 then true else false end from Comment c where c.id = ?1 and c.parent is not null")
    boolean isCommentReply(Long parentId);
}
