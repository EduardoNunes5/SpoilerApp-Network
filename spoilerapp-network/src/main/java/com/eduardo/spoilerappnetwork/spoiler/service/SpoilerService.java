package com.eduardo.spoilerappnetwork.spoiler.service;


import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;

import java.util.List;

public interface SpoilerService {

    SpoilerDTO create(SpoilerDTO userDTO);
    SpoilerDTO update(Long id, SpoilerDTO userDTO);
    List<SpoilerDTO> findAll();
    void delete(Long id);
}
