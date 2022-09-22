package com.jalasoft.bootcamp.becommon.infrastructure.utils.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorsUtil
{
    public static final String ERROR_MESSAGE_ENTITY_NOT_FOUND = "Entity not found";
    public static final String ERROR_MESSAGE_ENTITY_DUPLICATED = "Entity already registered";
    public static final String ERROR_MESSAGE_ENTITY_INVALID = "Entity is invalid";
    public static final String ERROR_MESSAGE_FILE_INVALID = "File is unsupported";
    public static final String ERROR_MESSAGE_INVALID_QUERY_PARAM = "Query param is invalid";
    public static final String ERROR_MESSAGE_BAD_REQUEST = "Bad request";

    public static final String ERROR_DESCRIPTION_APPLICANT_NOT_FOUND = "Applicant with %s: %s "
        + "was not found";
    public static final String ERROR_DESCRIPTION_APPLICANT_DUPLICATED = "Applicant with %s: "
        + "%s is already registered";
    public static final String ERROR_DESCRIPTION_APPLICANT_NOT_FOUND_IN_BOOTCAMP = " in "
        + "Bootcamp with %s: %s";

    public static final String ERROR_DESCRIPTION_FILE_INVALID_EXTENSION = " Unsupported file "
        + "extension";
    public static final String ERROR_DESCRIPTION_CONTENT_FILE_INVALID = " File is unsupported";

    public static final String ERROR_DESCRIPTION_BOOTCAMP_NOT_FOUND = "Bootcamp with %s: %s was "
        + "not found";
    public static final String ERROR_DESCRIPTION_FILE_INVALID_TYPE_OR_SIZE =
        "File has not a valid extension and/or size";
    public static final String ERROR_DESCRIPTION_INVALID_QUERY_PARAM = "Invalid query param: %s";
    public static final String ERROR_DESCRIPTION_BAD_REQUEST = "Http client bad request exception";
    public static final String ERROR_DESCRIPTION_USER_NOT_FOUND = "User with %s: %s "
        + "was not found";
    public static final String ERROR_DESCRIPTION_USER_DUPLICATED = "User with %s: "
        + "%s is already registered";
    public static final String ERROR_MESSAGE_ = "";
    public static final String ERROR_DESCRIPTION_ = "";

    public static final String ERROR_DESCRIPTION_WORKFLOW_NOT_FOUND = "The workflow with %s: %s "
        + "was not found";
    public static final String ERROR_DESCRIPTION_STAGE_QUALIFICATION_NOT_FOUND =
        "The stage qualification with %s: %s was not found";
    public static final String ERROR_DESCRIPTION_DEPARTMENT_NOT_FOUND =
        "The department with %s: %s was not found";
    public static final String ERROR_DESCRIPTION_PROJECT_TYPE_NOT_FOUND =
        "The project type with %s: %s was not found";
    public static final String ERROR_DESCRIPTION_STAGE_NOT_FOUND = "The stage was not found";

    public static final String ERROR_DESCRIPTION_INVALID_ARGUMENT = "%s in bootcamp is invalid";
    public static final String ERROR_DESCRIPTION_INVALID_DESCRIPTION =
        "Bootcamp description must be maximum of 100 characters";
    public static final String ERROR_DESCRIPTION_INVALID_PARTICIPANTS_EXPECTED_QUANTITY =
        "Bootcamp expected quantity of participants must be a positive number";
    public static final String ERROR_DESCRIPTION_INVALID_START_DATE =
        "Bootcamp start date can not be higher than the bootcamp end date";
    public static final String ERROR_DESCRIPTION_INVALID_NEXT_QUALIFICATION =
        "Invalid next qualification to current qualification";
    public static final String ERROR_DESCRIPTION_INVALID_SCORE = "Invalid score value to current "
        + "qualification";

    public static final String ERROR_DESCRIPTION_BOOTCAMP_DUPLICATED =
        "Bootcamp with %s: %s is already registered";

    public static final String ERROR_MESSAGE_AUTHENTICATION_TOKEN = "Unauthorized token";

    public static final String ERROR_DESCRIPTION_CATEGORY_DUPLICATED = "Category with %s: %s is "
        + "already registered";

    public static final String ERROR_USER_NOT_FOUND = "User with %s: %s not found";
    
    public static final String ERROR_DESCRIPTION_CATEGORY_NOT_FOUND = "Category with %s: %s not "
        + "found";

    public static final String ERROR_PASSWORD_DONT_CONTAIN_EXPECTED_VALUES = "Password doesn't "
        + "contain expected values";

    public static final String ERROR_BAD_OLD_PASSWORD = "Invalid old password";

    public static final String ERROR_NEW_PASSWORD_IS_THE_SAME = "New password is the same than the "
        + "old password";

    public static final String ERROR_PASSWORD_CHANGE_INVALID_PARAMETERS = "Password change with "
        + "invalid parameters";
    public static final String ERROR_DESCRIPTION_CATEGORY_SKILLS_NOT_FOUND = "Skills of "
        + "categories not found";

    public static final String ERROR_MICROSERVICE_COMMUNICATION = "We cannot communicate with the"
        + " service";

    public static final String ERROR_CERTAIN_MICROSERVICE_COMMUNICATION = "We cannot communicate "
        + "with the service: %s";

    public static final String ERROR_SETTINGS_FILE_NOT_FOUND = "Settings found";

    public static final String ERROR_DESCRIPTION_DEPARTMENT_DUPLICATED =
        "Department with %s: %s is already registered";

    public static final String ERROR_DESCRIPTION_DEFAULT_ACCOUNT_SETTING_NOT_FOUND = "Default "
        + "values were not loaded correctly";

    public static String getDescription(String message, String field, String value)
    {
        return String.format(message, field, value);
    }
}