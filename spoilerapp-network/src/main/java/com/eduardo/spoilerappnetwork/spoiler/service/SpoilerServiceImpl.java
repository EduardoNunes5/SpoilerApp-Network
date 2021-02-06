package com.eduardo.spoilerappnetwork.spoiler.service;

import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;
import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerResponseDTO;
import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import com.eduardo.spoilerappnetwork.spoiler.exception.SpoilerNotFoundException;
import com.eduardo.spoilerappnetwork.spoiler.mapper.SpoilerMapper;
import com.eduardo.spoilerappnetwork.spoiler.repository.SpoilerRepository;
import com.eduardo.spoilerappnetwork.user.entity.User;
import com.eduardo.spoilerappnetwork.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public SpoilerResponseDTO create(UserDetails userDetails, SpoilerDTO spoilerDTO) {
        User user = userService.verifyAndGetIfExists(userDetails.getUsername());
        Spoiler spoilerToBeSaved = spoilerMapper.toModel(spoilerDTO);
        spoilerToBeSaved.setAuthor(user);

        Spoiler saved = this.spoilerRepository.save(spoilerToBeSaved);
        return spoilerMapper.toDTO(saved);
    }

    @Override
    public SpoilerResponseDTO update(UserDetails authUser, Long id, SpoilerDTO spoilerDTO) {
        User foundUser = this.userService.verifyAndGetIfExists(authUser.getUsername());
        Spoiler found = verifyAndGetIfExistsByIdAndUser(id, foundUser);
        Spoiler toUpdate = spoilerMapper.toModel(spoilerDTO);

        toUpdate.setAuthor(found.getAuthor());
        toUpdate.setId(found.getId());

        Spoiler updated = this.spoilerRepository.save(toUpdate);
        return spoilerMapper.toDTO(updated);
    }

    @Override
    public SpoilerResponseDTO findById(Long id) {
        Spoiler spoiler = verifyAndGetIfExists(id);
        return spoilerMapper.toDTO(spoiler);
    }

    @Override
    public Spoiler verifyAndGetIfExists(Long id){
        return this.spoilerRepository.findById(id)
                .orElseThrow(() -> new SpoilerNotFoundException(id));
    }


    @Override
    public List<SpoilerResponseDTO> findAll() {
        return this.spoilerRepository.findAll()
                .stream()
                .map(spoilerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(UserDetails userDetails, Long id) {
        User foundUser = this.userService.verifyAndGetIfExists(userDetails.getUsername());
        Spoiler spoiler = this.verifyAndGetIfExistsByIdAndUser(id, foundUser);
        this.spoilerRepository.deleteByIdAndAuthor(spoiler.getId(), foundUser);

    }

    @Override
    public List<SpoilerResponseDTO> findByNameContaining(String name) {
        return this.spoilerRepository.findAllByNameContaining(name)
                .stream()
                .map(spoilerMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Spoiler verifyAndGetIfExistsByIdAndUser(Long id, User foundUser) {
        return this.spoilerRepository.findByIdAndAuthor(id, foundUser)
                .orElseThrow(() -> new SpoilerNotFoundException(id));
    }
}
