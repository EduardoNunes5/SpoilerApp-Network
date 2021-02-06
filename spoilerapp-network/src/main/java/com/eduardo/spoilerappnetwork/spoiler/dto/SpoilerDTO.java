package com.eduardo.spoilerappnetwork.spoiler.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpoilerDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Integer episode;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    private Long authorId;
}
