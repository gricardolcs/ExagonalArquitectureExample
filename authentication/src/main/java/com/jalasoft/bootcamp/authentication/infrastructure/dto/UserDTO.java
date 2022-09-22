package com.jalasoft.bootcamp.authentication.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.authentication.domain.user.User;
import com.jalasoft.bootcamp.authentication.domain.user.UserStatus;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.ValidatorUtils;
import com.jalasoft.bootcamp.becommon.infrastructure.utils.constants.ErrorsUtil;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO
{
    @ApiModelProperty(
        example = "-5319461058060181693"
    )
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("id")
    private long id;

    @ApiModelProperty(
        example = "jalasoft-user"
    )
    @JsonProperty("username")
    @NotBlank
    private String username;

    @ApiModelProperty(
        example = "ROLE_ADMIN",
        hidden = true
    )
    @JsonProperty("role")
    private String role;

    @ApiModelProperty(
        example = "Juan"
    )
    @JsonProperty("firstname")
    @NotBlank
    private String firstName;

    @ApiModelProperty(
        example = "Perez"
    )
    @JsonProperty("lastname")
    @NotBlank
    private String lastName;

    @ApiModelProperty(
        example = "Juan.Perez@jalasoft.com"
    )
    @JsonProperty("email")
    @NotBlank
    private String email;

    @ApiModelProperty(
        example = "password"
    )
    @JsonProperty("password")
    @Pattern(
        regexp = ValidatorUtils.REGEX_PASSWORD,
        message = ErrorsUtil.ERROR_PASSWORD_DONT_CONTAIN_EXPECTED_VALUES
    )
    private String password;

    @ApiModelProperty(
        example = "comments"
    )
    @JsonProperty("comments")
    private String comments;

    @ApiModelProperty
    @JsonProperty("status")
    private UserStatus status;

    @ApiModelProperty(
        example = "2022-03-15",
        hidden = true
    )
    @JsonProperty("passwordUpdatedDate")
    private LocalDate passwordUpdatedDate;

    @JsonProperty("photo")
    private byte[] photo;

    @ApiModelProperty(
        example = "71873664"
    )
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    public UserDTO(User user)
    {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.comments = user.getComments();
        this.status = user.getStatus();
        this.passwordUpdatedDate = user.getPasswordUpdatedDate();
        this.photo = user.getPhoto();
        this.phoneNumber = user.getPhoneNumber();
    }
}
