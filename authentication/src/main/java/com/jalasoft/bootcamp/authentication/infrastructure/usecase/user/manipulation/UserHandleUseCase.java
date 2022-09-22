package com.jalasoft.bootcamp.authentication.infrastructure.usecase.user.manipulation;

import com.jalasoft.bootcamp.authentication.domain.user.User;
import com.jalasoft.bootcamp.authentication.domain.user.UserStatus;
import com.jalasoft.bootcamp.authentication.domain.user.repository.UserRepository;
import com.jalasoft.bootcamp.authentication.infrastructure.dto.ChangePasswordDTO;
import com.jalasoft.bootcamp.authentication.infrastructure.dto.UserDTO;
import com.jalasoft.bootcamp.authentication.infrastructure.factory.UserFactory;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsUseCase;
import com.jalasoft.bootcamp.becommon.domain.exceptions.PasswordException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@IsUseCase
@Service
public class UserHandleUseCase
{
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserHandleUseCase(
        final UserRepository userRepository,
        final UserFactory userFactory,
        final PasswordEncoder bcryptEncoder
    )
    {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.bcryptEncoder = bcryptEncoder;
    }

    public UserDTO create(final UserDTO userDTO)
    {
        User user = userFactory.create(userDTO);
        return new UserDTO(userRepository.save(user));
    }

    public UserDTO update(final long userId, UserDTO userDTO)
    {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(ErrorsUtil.ERROR_DESCRIPTION_USER_NOT_FOUND, Field.ID,
                    String.valueOf(userId))
            ));
        String encodedPassword = user.getPassword();
        if (userDTO.getPassword() != null
            && !bcryptEncoder.matches(userDTO.getPassword(), encodedPassword))
        {
            encodedPassword = bcryptEncoder.encode(userDTO.getPassword());
            //(TODO: cristal.flores) it must get from setting micro-service
        }
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoto(userDTO.getPhoto());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUsername());
        user.setPassword(encodedPassword);
        user.setPasswordUpdatedDate(userDTO.getPasswordUpdatedDate());
        user.setStatus(userDTO.getStatus());
        user.setComments(user.getComments());
        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser);
    }

    public void delete(long id, String comments)
    {
        User user =
            userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(ErrorsUtil.ERROR_USER_NOT_FOUND, Field.ID,
                    String.valueOf(id))));
        user.setStatus(UserStatus.INACTIVE);
        user.setComments(comments);
        userRepository.save(user);
    }

    public void updatePassword(long id, ChangePasswordDTO changePasswordDTO)
    {
        User user = userRepository.findById(id).orElseThrow((() -> new EntityNotFoundException(
            Field.ID,
            ErrorsUtil.getDescription(ErrorsUtil.ERROR_USER_NOT_FOUND, Field.ID,
                String.valueOf(id)))));
        verifyPassword(user.getPassword(), changePasswordDTO);
        user.setPassword(bcryptEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    private void verifyPassword(String currentPassword, ChangePasswordDTO changePasswordDTO)
    {
        if (!bcryptEncoder.matches(changePasswordDTO.getOldPassword(), currentPassword))
        {
            throw new PasswordException("oldPassword", ErrorsUtil.ERROR_BAD_OLD_PASSWORD);
        }

        if (changePasswordDTO.getOldPassword().equalsIgnoreCase(changePasswordDTO.getNewPassword()))
        {
            throw new PasswordException(
                "newPassword",
                ErrorsUtil.ERROR_NEW_PASSWORD_IS_THE_SAME);
        }
    }

    public void updateUsersExpirationDays(int passwordExpirationInDays)
    {
        List<User> users = userRepository.findAll();
        List<User> usersUpdated = users.stream().map((user) -> {
            if (passwordExpirationInDays == 0)
            {
                user.setPasswordUpdatedDate(null);
            }
            else
            {
                LocalDate currentExpiration = user.getPasswordUpdatedDate();
                if (currentExpiration == null)
                {
                    user.setPasswordUpdatedDate(LocalDate.now().plusDays(passwordExpirationInDays));
                }
                else
                {
                    user.setPasswordUpdatedDate(currentExpiration.plusDays(passwordExpirationInDays));
                }
            }
            return user;
        }).collect(Collectors.toList());
        userRepository.saveAll(usersUpdated);
    }
}
