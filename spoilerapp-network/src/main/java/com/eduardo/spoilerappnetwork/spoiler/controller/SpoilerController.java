package com.eduardo.spoilerappnetwork.spoiler.controller;

import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;
import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerResponseDTO;
import com.eduardo.spoilerappnetwork.spoiler.service.SpoilerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/spoilers")
public class SpoilerController {

    private final SpoilerService spoilerService;

    public SpoilerController(SpoilerService spoilerService) {
        this.spoilerService = spoilerService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public SpoilerResponseDTO create(@RequestBody @Valid SpoilerDTO userDTO) {
        return spoilerService.create(userDTO);
    }

    @PutMapping("/{id}")
    public SpoilerResponseDTO update(@PathVariable Long id, @RequestBody @Valid SpoilerDTO userDTO) {
        return spoilerService.update(id, userDTO);
    }
}
