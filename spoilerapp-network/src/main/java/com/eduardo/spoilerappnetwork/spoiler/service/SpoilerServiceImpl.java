package com.eduardo.spoilerappnetwork.spoiler.service;

import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;
import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerResponseDTO;
import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import com.eduardo.spoilerappnetwork.spoiler.exception.SpoilerNotFoundException;
import com.eduardo.spoilerappnetwork.spoiler.mapper.SpoilerMapper;
import com.eduardo.spoilerappnetwork.spoiler.repository.SpoilerRepository;
import com.eduardo.spoilerappnetwork.user.entity.User;
import com.eduardo.spoilerappnetwork.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpoilerServiceImpl implements  SpoilerService{

    private final UserService userService;
    private final SpoilerRepository spoilerRepository;
    private final SpoilerMapper spoilerMapper = SpoilerMapper.INSTANCE;

    public SpoilerServiceImpl(UserService userService, SpoilerRepository spoilerRepository) {
        this.userService = userService;
        this.spoilerRepository = spoilerRepository;
    }

    @Override
    public SpoilerResponseDTO create(SpoilerDTO spoilerDTO) {
        User user = userService.verifyAndGetIfExists(spoilerDTO.getAuthorId());
        Spoiler spoilerToBeSaved = spoilerMapper.toModel(spoilerDTO);
        spoilerToBeSaved.setAuthor(user);

        System.out.println(spoilerToBeSaved);
        System.out.println(spoilerDTO);

        Spoiler saved = this.spoilerRepository.save(spoilerToBeSaved);
        return spoilerMapper.toDTO(saved);
    }

    @Override
    public SpoilerResponseDTO update(Long id, SpoilerDTO spoilerDTO) {
        Spoiler found = verifyAndGetIfExists(id);
        Spoiler toUpdate = spoilerMapper.toModel(spoilerDTO);

        toUpdate.setAuthor(found.getAuthor());
        toUpdate.setId(found.getId());

        Spoiler updated = this.spoilerRepository.save(toUpdate);
        return spoilerMapper.toDTO(updated);
    }

    private Spoiler verifyAndGetIfExists(Long id) {
        return this.spoilerRepository.findById(id)
                .orElseThrow(() -> new SpoilerNotFoundException(id));
    }

    @Override
    public List<SpoilerDTO> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
