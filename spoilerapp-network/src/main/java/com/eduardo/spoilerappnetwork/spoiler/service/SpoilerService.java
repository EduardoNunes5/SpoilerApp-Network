package com.eduardo.spoilerappnetwork.spoiler.service;


import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;
import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerResponseDTO;

import java.util.List;

public interface SpoilerService {

    SpoilerResponseDTO create(SpoilerDTO userDTO);
    SpoilerResponseDTO update(Long id, SpoilerDTO userDTO);
    List<SpoilerDTO> findAll();
    void delete(Long id);

}
