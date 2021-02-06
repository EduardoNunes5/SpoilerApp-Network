package com.eduardo.spoilerappnetwork.spoiler.mapper;

import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;
import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerResponseDTO;
import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpoilerMapper {

    SpoilerMapper INSTANCE = Mappers.getMapper(SpoilerMapper.class);

    Spoiler toModel(SpoilerDTO spoilerDTO);
    SpoilerResponseDTO toDTO(Spoiler spoiler);
}
