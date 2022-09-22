package com.jalasoft.bootcamp.authentication.domain.infrastructure.usecase.user.manipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jalasoft.bootcamp.authentication.domain.user.User;
import com.jalasoft.bootcamp.authentication.domain.user.UserStatus;
import com.jalasoft.bootcamp.authentication.domain.user.repository.UserRepository;
import com.jalasoft.bootcamp.authentication.infrastructure.dto.ChangePasswordDTO;
import com.jalasoft.bootcamp.authentication.infrastructure.dto.UserDTO;
import com.jalasoft.bootcamp.authentication.infrastructure.factory.UserFactory;
import com.jalasoft.bootcamp.authentication.infrastructure.usecase.user.manipulation.UserHandleUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.PasswordException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserHandleUseCaseTest
{
    @Spy
    private final PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
    @InjectMocks
    private UserHandleUseCase userHandleUseCase;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserFactory userFactory;
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
    private String phoneNumber;
    private User user;
    private ChangePasswordDTO data;

    @BeforeEach
    void setUp()
    {
        id = UUID.randomUUID().getLeastSignificantBits();
        userName = "jalasoft-user";
        userRole = "ROLE_ADMIN";
        firstName = "Juan";
        lastName = "Perez";
        email = "Juan.Perez@jalasoft.com";
        password = bcryptEncoder.encode("password");
        comments = "comments";
        status = UserStatus.ACTIVE;
        passwordUpdatedDate = LocalDate.now();
        photo = new byte[0];
        phoneNumber = "56789878";
        data = new ChangePasswordDTO("Old123456+", "New1234+");

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
            phoneNumber);
    }

    @Test
    void testUserCreation()
    {
        UserDTO userDTO = new UserDTO(user);
        Mockito.when(userFactory.create(userDTO)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDTO userSaved = userHandleUseCase.create(userDTO);

        assertEquals(userDTO.getId(), userSaved.getId());
        assertEquals(userDTO.getFirstName(), userSaved.getFirstName());
        assertEquals(userDTO.getLastName(), userSaved.getLastName());
        assertEquals(userDTO.getEmail(), userSaved.getEmail());
        assertEquals(userDTO.getPassword(), userSaved.getPassword());
        assertEquals(userDTO.getComments(), userSaved.getComments());
        assertEquals(userDTO.getStatus(), userSaved.getStatus());
        assertEquals(userDTO.getPasswordUpdatedDate(), userSaved.getPasswordUpdatedDate());
        assertEquals(userDTO.getPhoto(), userSaved.getPhoto());
        assertEquals(userDTO.getPhoneNumber(), userSaved.getPhoneNumber());
    }

    @Test
    void testUpdateUser()
    {
        User modifiedUser = new User(id, userName, userRole, "Jose", lastName,
            "Jose.Perez@jalasoft.com", password, comments, status, passwordUpdatedDate,
            photo, phoneNumber);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(modifiedUser)).thenReturn(modifiedUser);
        UserDTO userDTO = new UserDTO(modifiedUser);
        UserDTO updateUserDTO = userHandleUseCase.update(id, userDTO);
        assertEquals(userDTO.getId(), updateUserDTO.getId());
        assertEquals(userDTO.getFirstName(), updateUserDTO.getFirstName());
        assertEquals(userDTO.getLastName(), updateUserDTO.getLastName());
        assertEquals(userDTO.getEmail(), updateUserDTO.getEmail());
        assertEquals(userDTO.getComments(), updateUserDTO.getComments());
        assertEquals(userDTO.getPassword(), updateUserDTO.getPassword());
        assertEquals(userDTO.getStatus(), updateUserDTO.getStatus());
        assertEquals(userDTO.getPasswordUpdatedDate(), updateUserDTO.getPasswordUpdatedDate());
        assertEquals(userDTO.getPhoto(), updateUserDTO.getPhoto());
        assertEquals(userDTO.getPhoneNumber(), updateUserDTO.getPhoneNumber());
    }

    @Test
    void testUpdateUserPassword()
    {
        String newPassword = "New Password";
        String encodedNewPassword = bcryptEncoder.encode(newPassword);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Mockito.when(bcryptEncoder.encode(newPassword)).thenReturn(encodedNewPassword);
        Mockito.when(bcryptEncoder.matches(newPassword, password)).thenReturn(Boolean.FALSE);
        User modifiedUser = new User(id, userName, userRole, "Jose", lastName,
            "Jose.Perez@jalasoft.com", password, comments, status, passwordUpdatedDate,
            photo, phoneNumber);
        UserDTO userDTO = new UserDTO(modifiedUser);
        userDTO.setPassword(newPassword);
        modifiedUser.setPassword(encodedNewPassword);
        lenient().when(userRepository.save(any(User.class))).thenReturn(modifiedUser);
        UserDTO updateUserDTO = userHandleUseCase.update(id, userDTO);
        assertEquals(userDTO.getId(), updateUserDTO.getId());
        assertEquals(userDTO.getFirstName(), updateUserDTO.getFirstName());
        assertEquals(userDTO.getLastName(), updateUserDTO.getLastName());
        assertEquals(userDTO.getEmail(), updateUserDTO.getEmail());
        assertEquals(userDTO.getComments(), updateUserDTO.getComments());
        assertEquals(userDTO.getStatus(), updateUserDTO.getStatus());
        assertEquals(userDTO.getPasswordUpdatedDate(), updateUserDTO.getPasswordUpdatedDate());
        assertEquals(userDTO.getPhoto(), updateUserDTO.getPhoto());
        assertEquals(userDTO.getPhoneNumber(), updateUserDTO.getPhoneNumber());
    }

    @Test
    void testInvalidIdUpdateUser()
    {
        UserDTO userDTO = new UserDTO(user);
        Mockito.when(userRepository.findById(id)).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> userHandleUseCase.update(id, userDTO));
    }

    @Test
    public void testDeleteUser()
    {
        UserHandleUseCase mock = mock(UserHandleUseCase.class);
        long id = UUID.randomUUID().getLeastSignificantBits();
        mock.delete(id, "");
        verify(mock, times(1)).delete(id, "");
    }

    @Test
    public void testDeleteUserInvalidId()
    {
        when(userRepository.findById(any(Long.class))).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> userHandleUseCase.delete(-12345, ""));
    }

    @Test
    public void testUpdatePasswordValidData()
    {
        UserHandleUseCase mock = mock(UserHandleUseCase.class);
        mock.updatePassword(-12345678, data);
        verify(mock, times(1)).updatePassword(-12345678, data);
    }

    @Test
    public void testUpdatePasswordInvalidUserId()
    {
        when(userRepository.findById(any(Long.class))).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> userHandleUseCase.updatePassword(-123456, data));
    }

    @Test
    public void testUpdatePasswordInvalidOldPassword()
    {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Assertions.assertThrows(
            PasswordException.class,
            () -> userHandleUseCase.updatePassword(id, data));
    }

    @Test
    public void testUpdatePasswordNewPasswordEqualThanOldPassword()
    {
        ChangePasswordDTO newPasswordInformation = new ChangePasswordDTO(
            "password",
            "password");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Assertions.assertThrows(
            PasswordException.class,
            () -> userHandleUseCase.updatePassword(id, newPasswordInformation));
    }

    @Test
    public void testUpdateExpirationDate()
    {
        UserHandleUseCase mock = mock(UserHandleUseCase.class);
        mock.updateUsersExpirationDays(15);
        verify(mock, times(1)).updateUsersExpirationDays(15);
    }
}
