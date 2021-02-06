package com.eduardo.spoilerappnetwork.spoiler.service;


import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;
import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerResponseDTO;
import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface SpoilerService {

    SpoilerResponseDTO create(UserDetails userDetails, SpoilerDTO userDTO);
    SpoilerResponseDTO update(UserDetails userDetails, Long id, SpoilerDTO userDTO);
    SpoilerResponseDTO findById(Long id);
    Spoiler verifyAndGetIfExists(Long id);
    List<SpoilerResponseDTO> findAll();
    List<SpoilerResponseDTO> findByNameContaining(String name);
    void delete(UserDetails userDetails, Long id);

}
