package com.eduardo.spoilerappnetwork.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long id;

    @NotEmpty
    @NotNull
    private String commentText;

    @NotNull
    private Long spoilerId;

    @NotNull
    private Long authorId;

    private Long commentId;
}
