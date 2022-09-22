package com.jalasoft.bootcamp.becommon.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jalasoft.bootcamp.becommon.infrastructure.utils.ValidatorUtils;
import org.junit.jupiter.api.Test;

class ValidatorUtilsTest
{

    @Test
    void testIsEmailValidWhenEmailDomainIsGmail_thenReturnTrue() {
        String email = "luis.info@gmail.com";
        assertTrue(ValidatorUtils.isEmailValid(email));
    }

    @Test
    void testIsEmailValidWhenEmailDomainIsHotmail_thenReturnTrue() {
        String email = "luis_info@hotmail.com";
        assertTrue(ValidatorUtils.isEmailValid(email));
    }

    @Test
    void testIsEmailValidWhenEmailDomainIsOutlook_thenReturnTrue() {
        String email = "luis-info@outlook.es";
        assertTrue(ValidatorUtils.isEmailValid(email));
    }

    @Test
    void testIsEmailValidWhenEmailDomainIsMalformed_thenReturnFalse() {
        String email = "luis-info@mail..com";
        assertFalse(ValidatorUtils.isEmailValid(email));
    }

    @Test
    void testIsEmailValidWhenEmailPrefixIsMalformed_thenReturnFalse() {
        String email = "luis[info]@mail.com";
        assertFalse(ValidatorUtils.isEmailValid(email));
    }

    @Test
    void testIsEmailValidWhenEmailAddressIsMalformed_thenReturnFalse() {
        String email = "luis.info[@]mail.com";
        assertFalse(ValidatorUtils.isEmailValid(email));
    }

    @Test
    void testIsEmailValidWhenEmailDoesNotHaveAtSign_thenReturnFalse() {
        String email = "luis.infomail.com";
        assertFalse(ValidatorUtils.isEmailValid(email));
    }

    @Test
    void testIsEmailValidWhenEmailDomainHasSpecialCharacters_thenReturnFalse() {
        String email = "luis.info@mail#.com";
        assertFalse(ValidatorUtils.isEmailValid(email));
    }

    @Test
    void testIsEmailValidWhenEmailPrefixHasSpecialCharacters_thenReturnTrue() {
        String email = "luis#info@mail.com";
        assertTrue(ValidatorUtils.isEmailValid(email));
    }

    @Test
    void testIsEmailValidWhenEmailPrefixHasNumbers_thenReturnTrue() {
        String email = "luis.info20@mail.com";
        assertTrue(ValidatorUtils.isEmailValid(email));
    }
}