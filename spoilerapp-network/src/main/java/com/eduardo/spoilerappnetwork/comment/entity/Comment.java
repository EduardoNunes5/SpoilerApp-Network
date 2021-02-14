package com.eduardo.spoilerappnetwork.comment.entity;

import com.eduardo.spoilerappnetwork.comment.annotations.CannotBeRepliedIfReply;
import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import com.eduardo.spoilerappnetwork.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotNull
    @NotEmpty
    private String commentText;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime creationDate;

    @ManyToOne
    private Spoiler spoiler;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> replies;

    public void addReply(Comment comment){
        this.replies.add(comment);
    }
}


