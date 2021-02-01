package com.eduardo.spoilerappnetwork.spoiler.service;


import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;
import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface SpoilerService {

    SpoilerResponseDTO create(UserDetails userDetails, SpoilerDTO userDTO);
    SpoilerResponseDTO update(UserDetails userDetails, Long id, SpoilerDTO userDTO);
    List<SpoilerDTO> findAll();
    void delete(Long id);

}
