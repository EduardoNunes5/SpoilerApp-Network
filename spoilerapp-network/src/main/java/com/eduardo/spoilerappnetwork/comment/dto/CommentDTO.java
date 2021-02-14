package com.eduardo.spoilerappnetwork.comment.dto;

import com.eduardo.spoilerappnetwork.comment.annotations.CannotBeRepliedIfReply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;

    @NotEmpty
    @NotNull
    private String commentText;

    @NotNull
    private Long spoilerId;

    @NotNull
    private Long authorId;
}
