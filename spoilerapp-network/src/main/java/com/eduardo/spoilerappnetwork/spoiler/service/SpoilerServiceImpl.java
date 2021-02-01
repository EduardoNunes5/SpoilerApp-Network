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

    private Spoiler verifyAndGetIfExistsByIdAndUser(Long id, User foundUser) {
        return this.spoilerRepository.findByIdAndAuthor(id, foundUser)
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
