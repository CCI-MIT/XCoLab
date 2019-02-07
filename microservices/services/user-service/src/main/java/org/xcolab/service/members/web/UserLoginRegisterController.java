package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.user.IUserLoginRegisterClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.service.members.domain.member.UserDao;
import org.xcolab.service.members.service.member.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController

public class UserLoginRegisterController implements IUserLoginRegisterClient {

    private final UserService memberService;

    private final UserDao memberDao;

    @Autowired
    public UserLoginRegisterController(UserService memberService, UserDao memberDao) {
        Assert.notNull(memberService, "UserService bean is required");
        Assert.notNull(memberDao, "UserDao bean is required");
        this.memberService = memberService;
        this.memberDao = memberDao;
    }

    @GetMapping("/members/generateScreenName")
    public String generateScreenName(@RequestParam String[] values) {
        return memberService.generateScreenName(values);
    }

    @GetMapping("/members/isUsed")
    public boolean isUsed(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String email) {
        boolean ret = false;
        if (screenName != null) {
            ret = memberDao.isScreenNameTaken(screenName);
        }
        if (email != null) {
            ret = ret || memberDao.isEmailUsed(email);
        }
        return ret;
    }

    @GetMapping("/members/hashPassword")
    public String hashPassword(@RequestParam String password) {
        password = decode(password);
        return memberService
                .hashPassword(password);
    }

    @PostMapping("/members/validatePassword")
    public Boolean validatePassword(
            @RequestParam String password,
            @RequestParam(required = false) String hash,
            @RequestParam(required = false) Long userId)
            throws MemberNotFoundException {
        if (hash != null) {
            hash = decode(hash);
        }
        password = decode(password);

        if (hash != null) {
            return memberService.validatePassword(password, hash);
        }

        if (userId != null) {
            final UserWrapper member = memberDao.getUser(userId).orElseThrow(MemberNotFoundException::new);
            return memberService.validatePassword(password, member.getHashedPassword());
        }
        throw new MemberNotFoundException("The endpoint you requested is not available for the given attributes");
    }

    @PostMapping("/members/{userId}/updatePassword")
    public boolean updateForgottenPasswordByToken(@PathVariable long userId,
            @RequestParam String newPassword)
            throws MemberNotFoundException {
        newPassword = decode(newPassword);
        return memberService.updatePassword(userId, newPassword);
    }

    @GetMapping("/members/createForgotPasswordToken")
    public String createForgotPasswordToken(
            @RequestParam(required = false) Long userId) throws MemberNotFoundException {
        if (userId != null) {
            return memberService.createNewForgotPasswordToken(userId);
        }
        throw new MemberNotFoundException(
                "The endpoint you requested is not available for the given attributes");
    }

    @PostMapping("/members/updateForgottenPassword")
    public Long updateForgottenPasswordByToken(
            @RequestParam String forgotPasswordToken,
            @RequestParam String password)
            throws MemberNotFoundException {
        password = decode(password);
        return memberService.updateUserPasswordWithToken(forgotPasswordToken, password);
    }

    @GetMapping("/members/validateForgotPasswordToken")
    public boolean validateForgotPasswordToken(
            @RequestParam String passwordToken)
            throws MemberNotFoundException {
        return memberService.validateForgotPasswordToken(passwordToken);
    }

    private String decode(@RequestParam String newPassword) {
        try {
            newPassword = URLDecoder.decode(newPassword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalException(e);
        }
        return newPassword;
    }
}
