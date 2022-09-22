package com.jalasoft.bootcamp.authentication.domain.user.repository;

import com.jalasoft.bootcamp.authentication.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    boolean existsByEmailIgnoreCase(String email);

    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
}
