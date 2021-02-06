package com.eduardo.spoilerappnetwork.comment.dto;

import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;

    @NotEmpty
    @NotNull
    private String comment;

    @NotNull
    private Long spoilerId;

    @NotNull
    private Long authorId;
}
