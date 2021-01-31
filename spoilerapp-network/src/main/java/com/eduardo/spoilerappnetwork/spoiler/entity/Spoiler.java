package com.eduardo.spoilerappnetwork.spoiler.entity;


import com.eduardo.spoilerappnetwork.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
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
}
