package com.eduardo.spoilerappnetwork.spoiler.controller;

import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;
import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerResponseDTO;
import com.eduardo.spoilerappnetwork.spoiler.service.SpoilerService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public SpoilerResponseDTO create(@AuthenticationPrincipal UserDetails authUser, @RequestBody @Valid SpoilerDTO userDTO) {
        return spoilerService.create(authUser, userDTO);
    }

    @GetMapping
    public List<SpoilerResponseDTO> findAll() {
        return spoilerService.findAll();
    }

    @GetMapping("/{spoilerId}")
    public SpoilerResponseDTO findById(@PathVariable Long spoilerId){
        return spoilerService.findById(spoilerId);
    }

    @PatchMapping("/{id}")
    public SpoilerResponseDTO update(@AuthenticationPrincipal UserDetails authUser, @PathVariable Long id, @RequestBody @Valid SpoilerDTO userDTO) {
        return spoilerService.update(authUser, id, userDTO);
    }

    @DeleteMapping("/{spoilerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal UserDetails authUser, @PathVariable Long spoilerId) {
        spoilerService.delete(authUser, spoilerId);
    }

    @GetMapping(params = "name")
    public List<SpoilerResponseDTO> findByNameContaining(@RequestParam(value = "name") String name) {
        return spoilerService.findByNameContaining(name);
    }
}
