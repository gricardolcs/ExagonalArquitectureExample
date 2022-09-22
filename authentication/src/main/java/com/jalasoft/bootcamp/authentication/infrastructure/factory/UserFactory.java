package com.jalasoft.bootcamp.authentication.infrastructure.factory;

import com.jalasoft.bootcamp.authentication.domain.user.User;
import com.jalasoft.bootcamp.authentication.domain.user.UserStatus;
import com.jalasoft.bootcamp.authentication.domain.user.repository.UserRepository;
import com.jalasoft.bootcamp.authentication.infrastructure.dto.UserDTO;
import com.jalasoft.bootcamp.becommon.domain.ddd.IsFactory;
import com.jalasoft.bootcamp.becommon.domain.exceptions.DuplicatedEntityException;
import com.jalasoft.bootcamp.becommon.infrastructure.factory.Factory;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@IsFactory

@Service
public class UserFactory implements Factory<User, UserDTO>
{
    private final UserRepository userRepository;

    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserFactory(final UserRepository userRepository, final PasswordEncoder bcryptEncoder)
    {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public User create(final UserDTO userCreationDTO)
    {
        final Long userId = UUID.randomUUID().getLeastSignificantBits();

        validateIfEmailIsUnique(userCreationDTO.getEmail());

        return new User(
            userId,
            userCreationDTO.getUsername(),
            "ROLE_ADMIN",
            userCreationDTO.getFirstName(),
            userCreationDTO.getLastName(),
            userCreationDTO.getEmail(),
            bcryptEncoder.encode(userCreationDTO.getPassword()),
            userCreationDTO.getComments(),
            UserStatus.ACTIVE,
            LocalDate.now(),
            userCreationDTO.getPhoto(),
            userCreationDTO.getPhoneNumber());
    }

    private void validateIfEmailIsUnique(String email)
    {
        if (userRepository.existsByEmailIgnoreCase(email))
        {
            throw new DuplicatedEntityException(
                Field.EMAIL,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_USER_DUPLICATED,
                    Field.EMAIL, email));
        }
    }
}
