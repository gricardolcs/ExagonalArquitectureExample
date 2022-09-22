package com.jalasoft.bootcamp.authentication.domain.user;

import com.jalasoft.bootcamp.becommon.domain.ddd.IsEntity;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@IsEntity
@Entity
@Table(name = "users")
public class User extends AbstractAggregateRoot<User> implements UserDetails
{
    @Id
    private Long id;

    private String userName;

    private String role;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String comments;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private LocalDate passwordUpdatedDate;

    private byte[] photo;

    private String phoneNumber;

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername()
    {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
