package com.eduardo.spoilerappnetwork.spoiler.entity;


import com.eduardo.spoilerappnetwork.comment.entity.Comment;
import com.eduardo.spoilerappnetwork.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spoiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer episode;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
