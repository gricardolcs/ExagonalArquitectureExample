package com.jalasoft.bootcamp.authentication.domain.infrastructure.usecase.user.fetch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import com.jalasoft.bootcamp.authentication.domain.user.User;
import com.jalasoft.bootcamp.authentication.domain.user.UserStatus;
import com.jalasoft.bootcamp.authentication.domain.user.repository.UserRepository;
import com.jalasoft.bootcamp.authentication.infrastructure.dto.UserDTO;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.authentication.infrastructure.usecase.user.fetch.UserFetchUseCase;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserFetchUseCaseTest
{
    @InjectMocks
    private UserFetchUseCase userFetchUseCase;

    @Mock
    private UserRepository userRepository;

    private long id;
    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setUp()
    {
        id = UUID.randomUUID().getLeastSignificantBits();

        user = new User(id,
            "jalasoft-user",
            "ROLE_ADMIN",
            "Juan",
            "Perez",
            "Juan.Perez@jalasoft.com",
            "password",
            "comments",
            UserStatus.ACTIVE,
            LocalDate.now(),
            new byte[0],
            "56789878");

        userDTO = new UserDTO(user);
    }

    @Test
    void testFetchAllUsers()
    {
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<UserDTO> expectedUsers = userFetchUseCase.fetchAll();

        assertEquals(userDTO.getId(), expectedUsers.get(0).getId());
        assertEquals(userDTO.getFirstName(), expectedUsers.get(0).getFirstName());
        assertEquals(userDTO.getLastName(), expectedUsers.get(0).getLastName());
        assertEquals(userDTO.getEmail(), expectedUsers.get(0).getEmail());
        assertEquals(userDTO.getPassword(), expectedUsers.get(0).getPassword());
        assertEquals(userDTO.getComments(), expectedUsers.get(0).getComments());
        assertEquals(userDTO.getStatus(), expectedUsers.get(0).getStatus());
        assertEquals(userDTO.getPasswordUpdatedDate(), expectedUsers.get(0).getPasswordUpdatedDate());
        assertEquals(userDTO.getPhoto(), expectedUsers.get(0).getPhoto());
        assertEquals(userDTO.getPhoneNumber(), expectedUsers.get(0).getPhoneNumber());
    }

    @Test
    void testUserFetchById()
    {
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDTO expectedUser = userFetchUseCase.fetchById(id);

        assertEquals(userDTO.getId(), expectedUser.getId());
        assertEquals(userDTO.getFirstName(), expectedUser.getFirstName());
        assertEquals(userDTO.getLastName(), expectedUser.getLastName());
        assertEquals(userDTO.getEmail(), expectedUser.getEmail());
        assertEquals(userDTO.getPassword(), expectedUser.getPassword());
        assertEquals(userDTO.getComments(), expectedUser.getComments());
        assertEquals(userDTO.getStatus(), expectedUser.getStatus());
        assertEquals(userDTO.getPasswordUpdatedDate(), expectedUser.getPasswordUpdatedDate());
        assertEquals(userDTO.getPhoto(), expectedUser.getPhoto());
        assertEquals(userDTO.getPhoneNumber(), expectedUser.getPhoneNumber());
    }
}
