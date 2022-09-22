package com.jalasoft.bootcamp.authentication.domain.infrastructure.controller.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import com.jalasoft.bootcamp.authentication.domain.user.User;
import com.jalasoft.bootcamp.authentication.domain.user.UserStatus;
import com.jalasoft.bootcamp.authentication.infrastructure.controller.user.UserController;
import com.jalasoft.bootcamp.authentication.infrastructure.dto.ChangePasswordDTO;
import com.jalasoft.bootcamp.authentication.infrastructure.dto.UserDTO;
import com.jalasoft.bootcamp.authentication.infrastructure.usecase.user.fetch.UserFetchUseCase;
import com.jalasoft.bootcamp.authentication.infrastructure.usecase.user.manipulation.UserHandleUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.PasswordException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidArgumentsEntityException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest
{
    @InjectMocks
    private UserController userController;

    @Mock
    private UserFetchUseCase userFetchUseCase;

    @Mock
    private UserHandleUseCase userHandleUseCase;

    private long id;
    private String userName;
    private String userRole;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String comments;
    private UserStatus status;
    private LocalDate passwordUpdatedDate;
    private byte[] photo;
    private String celNumber;

    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setUp()
    {
        id = UUID.randomUUID().getLeastSignificantBits();
        userName = "jalasoft-user";
        userRole = "ROLE_ADMIN";
        firstName = "Juan";
        lastName = "Perez";
        email = "Juan.Perez@jalasoft.com";
        password = "password";
        comments = "comments";
        status = UserStatus.ACTIVE;
        passwordUpdatedDate = LocalDate.now();
        celNumber = "56789878";

        user = new User(
            id,
            userName,
            userRole,
            firstName,
            lastName,
            email,
            password,
            comments,
            status,
            passwordUpdatedDate,
            photo,
            celNumber);

        userDTO = new UserDTO(user);
    }

    @Test
    void testCreateValidUser()
    {

        when(userHandleUseCase.create(userDTO)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.create(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDTO.getId(), response.getBody().getId());
        assertEquals(userDTO.getFirstName(), response.getBody().getFirstName());
        assertEquals(userDTO.getLastName(), response.getBody().getLastName());
        assertEquals(userDTO.getEmail(), response.getBody().getEmail());
        assertEquals(userDTO.getPassword(), response.getBody().getPassword());
        assertEquals(userDTO.getComments(), response.getBody().getComments());
        assertEquals(userDTO.getStatus(), response.getBody().getStatus());
        assertEquals(userDTO.getPhoto(), response.getBody().getPhoto());
        assertEquals(userDTO.getPhoneNumber(), response.getBody().getPhoneNumber());
    }

    @Test
    void testInvalidArgumentsCreateUser()
    {
        UserDTO invalidUser = new UserDTO();
        invalidUser.setId(id);
        when(userHandleUseCase.create(invalidUser)).thenThrow(
            InvalidArgumentsEntityException.class);

        Assertions.assertThrows(
            InvalidArgumentsEntityException.class,
            () -> userController.create(invalidUser));
    }

    @Test
    void testFetchAllUsers()
    {
        when(userFetchUseCase.fetchAll()).thenReturn(Arrays.asList(userDTO));

        ResponseEntity<List<UserDTO>> response = userController.FetchAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody().get(0));
    }

    @Test
    void testFetchUserById()
    {
        Mockito.when(userFetchUseCase.fetchById(id)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.fetchUserById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    void testInvalidIdFetchUserById()
    {
        when(userFetchUseCase.fetchById(id)).thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> userController.fetchUserById(id));
    }

    @Test
    void testUpdateUserById()
    {
        when(userHandleUseCase.update(id, userDTO)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.updateUserById(id, userDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    void testInvalidIdUpdateUserById()
    {
        when(userHandleUseCase.update(id, userDTO))
            .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> userController.updateUserById(id, userDTO));
    }

    @Test
    void testDeleteUser()
    {
        doNothing().when(userHandleUseCase).delete(
            any(Long.class),
            any(String.class));
        ResponseEntity<Void> response = userController.deleteUser(-12345, "");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteUserInvalidID()
    {
        doThrow(EntityNotFoundException.class).when(userHandleUseCase)
            .delete(any(Long.class), any(String.class));
        assertThrows(
            EntityNotFoundException.class,
            () -> userController.deleteUser(-12345, ""));
    }

    @Test
    void testChangePassword()
    {
        doNothing().when(userHandleUseCase).updatePassword(
            any(Long.class),
            any(ChangePasswordDTO.class));
        ResponseEntity<Void> response = userController.updatePassword(
            -123456,
            new ChangePasswordDTO());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testUpdatePasswordInvalidOldPassword()
    {
        ChangePasswordDTO newPasswordInformation = new ChangePasswordDTO(
            "Password1+",
            "newPassword");
        doThrow(PasswordException.class).when(userHandleUseCase).updatePassword(
            any(Long.class),
            any(ChangePasswordDTO.class));
        assertThrows(PasswordException.class, () -> userController.updatePassword(
            -123456,
            newPasswordInformation));
    }

    @Test
    public void testUpdatePasswordNewPasswordEqualThanOldPassword()
    {
        ChangePasswordDTO newPasswordInformation = new ChangePasswordDTO(
            "password",
            "password");
        doThrow(PasswordException.class).when(userHandleUseCase).updatePassword(
            any(Long.class),
            any(ChangePasswordDTO.class));
        assertThrows(PasswordException.class, () -> userController.updatePassword(
            -123456,
            newPasswordInformation));
    }

    @Test
    public void testUpdatePasswordExpiration()
    {
        doNothing().when(userHandleUseCase).updateUsersExpirationDays(10);
        ResponseEntity<Void> response = userController.updateExpiration(10);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
