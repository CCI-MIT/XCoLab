package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.commons.exceptions.InternalException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@FeignClient("xcolab-user-service")
@RequestMapping("/members")
public interface IUserLoginRegisterClient {

    @GetMapping("generateScreenName")
    String generateScreenName(@RequestParam String[] values);

    @GetMapping("isUsed")
    boolean isUsed(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String email);

    @GetMapping("hashPassword")
    String hashPassword(@RequestParam String password);

    @PostMapping("validatePassword")
    Boolean validatePassword(
            @RequestParam String password,
            @RequestParam(required = false) String hash,
            @RequestParam(required = false) Long userId)
            throws MemberNotFoundException;

    @PostMapping("{userId}/updatePassword")
    boolean updateForgottenPasswordByToken(@PathVariable long userId,
            @RequestParam String newPassword)
            throws MemberNotFoundException;

    @GetMapping("createForgotPasswordToken")
    String createForgotPasswordToken(
            @RequestParam(required = false) Long userId) throws MemberNotFoundException;

    @PostMapping("updateForgottenPassword")
    Long updateForgottenPasswordByToken(
            @RequestParam String forgotPasswordToken,
            @RequestParam String password)
            throws MemberNotFoundException;

    @GetMapping("validateForgotPasswordToken")
    boolean validateForgotPasswordToken(
            @RequestParam String passwordToken)
            throws MemberNotFoundException;

    default boolean isEmailUsed(String email) {
        return isUsed(null, email);
    }

    default boolean isScreenNameUsed(String screen) {
        return isUsed(screen, null);
    }

    default boolean isForgotPasswordTokenValid(String token) {
        try {
            return validateForgotPasswordToken(token);
        } catch (MemberNotFoundException e) {
            return false;
        }
    }

    default boolean updatePassword(long userId, String newPassword) {
        try {
            return updateForgottenPasswordByToken(userId, encode(newPassword));
        } catch (MemberNotFoundException ignore) {
            return false;
        }
    }

    default String generateScreenName(String lastName, String firstName) {
        String values[] = {lastName, firstName};
        return generateScreenName(values);
    }

    default String hashThePassword(String password) {
        password = encode(password);
        return hashPassword(password);

    }

    default boolean validatePassword(String password, long userId) {
        try {
            return validatePassword(encode(password), null, userId);
        } catch (MemberNotFoundException ignore) {
            return false;
        }

    }

    default boolean validatePassword(String password, String hashedPassword) {
        try {
            return validatePassword(encode(password), encode(hashedPassword), null);
        } catch (MemberNotFoundException ignore) {
            return false;
        }
    }

    default String encode(String password) {
        if (password != null) {
            try {
                password = URLEncoder.encode(password, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new InternalException(e);
            }
        }
        return password;
    }

}
