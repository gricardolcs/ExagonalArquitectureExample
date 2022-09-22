package com.jalasoft.bootcamp.authentication.infrastructure.usecase.user.fetch;

import com.jalasoft.bootcamp.authentication.domain.user.Account;
import com.jalasoft.bootcamp.authentication.domain.user.User;
import com.jalasoft.bootcamp.authentication.domain.user.repository.UserRepository;
import com.jalasoft.bootcamp.authentication.infrastructure.dto.UserDTO;
import com.jalasoft.bootcamp.becommon.domain.exceptions.AuthTokenException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.EntityNotFoundException;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.Field;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserFetchUseCase implements UserDetailsService
{
    private final static Logger logger = LoggerFactory.getLogger(
        UserFetchUseCase.class);
    private final UserRepository userRepository;

    @Autowired
    public UserFetchUseCase(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException
    {
        Optional<User> userByUsername = userRepository.findByUserName(usernameOrEmail);
        Optional<User> userByEmail = null;
        if (!userByUsername.isPresent())
        {
            userByEmail = userRepository.findByEmail(usernameOrEmail);
            if (!userByEmail.isPresent())
            {
                logger.error("User not found with the data provided " + usernameOrEmail);
                throw new AuthTokenException(
                    "User not found with the name or email :  ",
                    usernameOrEmail);
            }
        }
        return userByUsername.isPresent() ? userByUsername.get() : userByEmail.get();
    }

    public List<UserDTO> fetchAll()
    {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO fetchById(long id)
    {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(ErrorsUtil.ERROR_DESCRIPTION_USER_NOT_FOUND, Field.ID,
                    String.valueOf(id))));
        return new UserDTO(user);
    }

    public Account getAlertMessage(LocalDate passwordUpdatedDate)
    {
        String message = "";
        int EXPIRATION_DAYS = 90;
        LocalDate currentDate = LocalDate.now();
        long remainingDays = EXPIRATION_DAYS - ChronoUnit.DAYS.between(passwordUpdatedDate, currentDate);
        if (remainingDays < 0)
        {
            message += "You must change your password";
        }
        if (remainingDays < 10)
        {
            message += "You password will expire in " + remainingDays + "days";
        }
        return new Account("", remainingDays, message);
    }
}
