package com.jalasoft.bootcamp.applicant.infrastructure.dto.applicant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jalasoft.bootcamp.applicant.domain.applicant.Applicant;
import com.jalasoft.bootcamp.applicant.domain.applicantstagequalification.ApplicantStageQualification;
import com.jalasoft.bootcamp.applicant.infrastructure.dto.curriculum.CurriculumDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ApplicantDTO
{
    @ApiModelProperty(
        example = "-7187368664014109313"
    )
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    @ApiModelProperty(
        example = "Juan Perez"
    )
    @Size(min = 6, max = 30,
        message = "The name must not be greater than 6 characters and less than 30 characters")
    @NotBlank(message = "Name is mandatory")
    @JsonProperty("fullName")
    @JsonInclude(Include.NON_NULL)
    private String fullName;

    @ApiModelProperty(
        example = "1993-02-24"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birthday is mandatory")
    @Past
    @JsonProperty("birthday")
    @JsonInclude(Include.NON_NULL)
    private LocalDate birthday;

    @ApiModelProperty(
        example = "juan.perez@gmail.com"
    )
    @NotBlank(message = "Email is mandatory")
    @JsonProperty("email")
    @JsonInclude(Include.NON_NULL)
    private String email;

    @ApiModelProperty(
        example = "71873664"
    )
    @NotBlank(message = "Telephone is mandatory")
    @JsonProperty("telephone")
    @JsonInclude(Include.NON_NULL)
    private String telephone;

    @ApiModelProperty(
        example = "Cochabamba"
    )
    @NotBlank(message = "City is mandatory")
    @JsonProperty("city")
    @JsonInclude(Include.NON_NULL)
    private String city;

    @ApiModelProperty(
        example = "Bolivia"
    )
    @NotBlank(message = "Country is mandatory")
    @JsonProperty("country")
    @JsonInclude(Include.NON_NULL)
    private String country;

    @ApiModelProperty(
        example = "Systems Engineer"
    )
    @Size(min = 5, max = 30)
    @NotBlank(message = "Career is mandatory")
    @JsonProperty("career")
    @JsonInclude(Include.NON_NULL)
    private String career;

    @ApiModelProperty(
        example = "Post grade"
    )
    @NotBlank(message = "Degree is mandatory")
    @JsonProperty("degree")
    @JsonInclude(Include.NON_NULL)
    private String degree;

    @ApiModelProperty(
        example = "Base64:==1asdSDewsd"
    )
    @JsonProperty("photo")
    @JsonInclude(Include.NON_NULL)
    private String photo;

    @ApiModelProperty(
        example = "{curriculumVitae:Base64:==1asdSDewsd, fileName: input file name}",
        dataType = "CurriculumDTO"
    )
    @JsonProperty("curriculum")
    @JsonInclude(Include.NON_NULL)
    private CurriculumDTO curriculum;

    @ApiModelProperty(
        example = "[{stageName: 'Psychometric', status: 'IN_PROGRESS'}]",
        dataType = "List<ApplicantStageQualificationDTO>",
        required = false,
        hidden = true
    )
    private List<ApplicantStageQualificationDTO> stages;

    @ApiModelProperty(
        example = "[Java, Python, PHP, Asp.net]"
    )
    @JsonProperty("skills")
    private List<String> skills;

    @ApiModelProperty(
        example = "[-718736866401410967, -718736866401410914]"
    )
    @JsonProperty("includedInBootcampIds")
    @JsonInclude(Include.NON_NULL)
    private List<String> includedInBootcampIds;

    public ApplicantDTO(Applicant applicant)
    {
        this.id = applicant.getId();
        this.fullName = applicant.getFullName();
        this.birthday = applicant.getBirthday();
        this.email = applicant.getEmail();
        this.telephone = applicant.getTelephone();
        this.city = applicant.getLocation().getCity();
        this.country = applicant.getLocation().getCountry();
        this.career = applicant.getCareer();
        this.degree = applicant.getDegree();
        this.photo = applicant.getPhoto();
        this.curriculum = new CurriculumDTO(
            applicant.getCurriculumVitae().getFileName(),
            applicant.getCurriculumVitae().getUrl(),
            applicant.getCurriculumVitae().getContent());
        this.stages = applicant.getApplicantStageQualifications().stream()
            .map(ApplicantStageQualificationDTO::new).collect(Collectors.toList());
        this.includedInBootcampIds = applicant.getApplicantBootcampHistoryIds().stream()
            .map(String::valueOf).collect(Collectors.toList());
        this.skills = applicant.getSkills();
    }

    @JsonCreator
    public ApplicantDTO(
        String fullName, LocalDate birthday, String email, String telephone, String city,
        String country, String career, String degree, String photo, CurriculumDTO curriculum,
        List<String> skills)
    {
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
        this.telephone = telephone;
        this.city = city;
        this.country = country;
        this.career = career;
        this.degree = degree;
        this.photo = photo;
        this.curriculum = curriculum;
        this.skills = skills;
    }

    public ApplicantDTO(
        long applicantId, String fullName,
        Set<ApplicantStageQualification> stages)
    {
        this.id = applicantId;
        this.fullName = fullName;
        this.stages = stages.stream()
            .map(ApplicantStageQualificationDTO::new)
            .collect(Collectors.toList());
    }

    public ApplicantDTO(
        long applicantId, String fullName,
        String country, List<String> skills)
    {
        this.id = applicantId;
        this.fullName = fullName;
        this.country = country;
        this.skills = skills;
    }
}