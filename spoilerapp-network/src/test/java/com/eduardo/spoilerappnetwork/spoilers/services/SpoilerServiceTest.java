package com.eduardo.spoilerappnetwork.spoilers.services;

import com.eduardo.spoilerappnetwork.security.userdetails.UserDetailsImpl;
import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerDTO;
import com.eduardo.spoilerappnetwork.spoiler.dto.SpoilerResponseDTO;
import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import com.eduardo.spoilerappnetwork.spoiler.mapper.SpoilerMapper;
import com.eduardo.spoilerappnetwork.spoiler.repository.SpoilerRepository;
import com.eduardo.spoilerappnetwork.spoiler.service.SpoilerService;
import com.eduardo.spoilerappnetwork.spoiler.service.SpoilerServiceImpl;
import com.eduardo.spoilerappnetwork.spoilers.builder.SpoilerDTOBuilder;
import com.eduardo.spoilerappnetwork.user.entity.User;
import com.eduardo.spoilerappnetwork.user.exception.UserNotFoundException;
import com.eduardo.spoilerappnetwork.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class SpoilerServiceTest {

    private SpoilerMapper mapper = SpoilerMapper.INSTANCE;

    private SpoilerDTOBuilder spoilerDTOBuilder;

    @Mock
    private SpoilerRepository spoilerRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private SpoilerServiceImpl spoilerService;

    private UserDetailsImpl authenticated = new UserDetailsImpl("eduu", "123456789");

    @BeforeEach
    void setUp() {
        this.spoilerDTOBuilder = SpoilerDTOBuilder.builder().build();
    }

    @Test
    void whenNewPostIsSentThenItShouldBeCreated() {

        SpoilerDTO toBeSavedDTO = this.spoilerDTOBuilder.buildSpoiler();
        Spoiler toBeSaved = this.mapper.toModel(toBeSavedDTO);
        SpoilerResponseDTO expectedResponse = this.mapper.toDTO(toBeSaved);

        Mockito.when(userService.verifyAndGetIfExists(authenticated.getUsername()))
                .thenReturn(new User());

        Mockito.when(this.spoilerRepository.save(any(Spoiler.class)))
                .thenReturn(toBeSaved);

        SpoilerResponseDTO foundResponse = this.spoilerService.create(authenticated, toBeSavedDTO);

        assertThat(foundResponse, is(expectedResponse));
    }

    @Test
    void whenANewSpoilerIsCreatedAndAnInvalidUserIsSentThenAnExceptionShouldBeThrown(){

        SpoilerDTO toBeSavedDTO = this.spoilerDTOBuilder.buildSpoiler();

        Mockito.when(userService.verifyAndGetIfExists(authenticated.getUsername()))
                .thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> this.spoilerService.create(authenticated, toBeSavedDTO));
    }
}
