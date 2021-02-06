package com.eduardo.spoilerappnetwork.spoilers.builder;

import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;
import lombok.Builder;
@Builder
public class SpoilerDTOBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String name = "Jujutsu Kaisen";

    @Builder.Default
    private final Integer episode = 16;

    @Builder.Default
    private String description = "Panda revela ter 3 núcleos e derrota e mechamaru";

    @Builder.Default
    private Long authorId = 1L;

    public SpoilerDTO buildSpoiler(){
        return new SpoilerDTO(
                id,
                name,
                episode,
                description,
                authorId
        );
    }
}
