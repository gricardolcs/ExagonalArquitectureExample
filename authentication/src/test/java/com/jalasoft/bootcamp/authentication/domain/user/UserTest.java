package com.jalasoft.bootcamp.authentication.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jalasoft.bootcamp.authentication.infrastructure.dto.UserDTO;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserTest
{
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
        photo = new byte[0];
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
    void testUserCreation()
    {
        assertEquals(id, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(comments, user.getComments());
        assertEquals(status, user.getStatus());
        assertEquals(passwordUpdatedDate, user.getPasswordUpdatedDate());
        assertEquals(photo, user.getPhoto());
        assertEquals(celNumber, user.getPhoneNumber());
    }
}
