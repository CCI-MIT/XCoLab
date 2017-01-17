package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.service.members.domain.member.MemberDao;
import org.xcolab.service.members.exceptions.ForbiddenException;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.exceptions.UnauthorizedException;
import org.xcolab.service.members.service.login.LoginBean;
import org.xcolab.service.members.service.member.MemberService;

@RestController
@RequestMapping("/members")
public class MembersLoginRegisterController {

    private final MemberService memberService;

    private final MemberDao memberDao;

    @Autowired
    public MembersLoginRegisterController(MemberService memberService, MemberDao memberDao) {
        Assert.notNull(memberService, "MemberService bean is required");
        Assert.notNull(memberDao, "MemberDao bean is required");
        this.memberService = memberService;
        this.memberDao = memberDao;
    }

    @PostMapping("{memberId}/login")
    public boolean login(@PathVariable long memberId, @RequestBody LoginBean loginBean)
            throws NotFoundException, UnauthorizedException, ForbiddenException {
        final Member member = memberDao.getMember(memberId).orElseThrow(NotFoundException::new);
        memberService.login(member, loginBean);
        return true;
    }

    @GetMapping("generateScreenName")
    public String generateScreenName(@RequestParam String[] values) {
        return memberService.generateScreenName(values);
    }

    @GetMapping("isUsed")
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

    @GetMapping("hashPassword")
    public String hashPassword(@RequestParam String password,
            @RequestParam(required = false) Boolean liferayCompatible) {
        return memberService
                .hashPassword(password, liferayCompatible != null ? liferayCompatible : false);
    }

    @GetMapping("validatePassword")
    public Boolean validatePassword(
            @RequestParam String password,
            @RequestParam(required = false) String hash,
            @RequestParam(required = false) Long memberId)
            throws NotFoundException {

        if (hash != null) {
            return memberService.validatePassword(password, hash);
        }

        if (memberId != null) {
            final Member member = memberDao.getMember(memberId).orElseThrow(NotFoundException::new);
            return memberService
                    .validatePassword(password, member.getHashedPassword());
        }
        throw new NotFoundException("The endpoint you requested is not available for the given attributes");
    }

    @PostMapping("{memberId}/updatePassword")
    public boolean updateForgottenPasswordByToken(@PathVariable long memberId,
            @RequestParam String newPassword)
            throws NotFoundException {
        return memberService.updatePassword(memberId, newPassword);
    }

    @GetMapping("createForgotPasswordToken")
    public String createForgotPasswordToken(
            @RequestParam(required = false) Long memberId) throws NotFoundException {
        if (memberId != null) {
            return memberService.createNewForgotPasswordToken(memberId);
        }
        throw new NotFoundException(
                "The endpoint you requested is not available for the given attributes");
    }

    @PostMapping("updateForgottenPassword")
    public Long updateForgottenPasswordByToken(
            @RequestParam String forgotPasswordToken,
            @RequestParam String password)
            throws NotFoundException {
        return memberService.updateUserPasswordWithToken(forgotPasswordToken, password);
    }

    @GetMapping("validateForgotPasswordToken")
    public boolean validateForgotPasswordToken(
            @RequestParam String passwordToken)
            throws NotFoundException {
        return memberService.validateForgotPasswordToken(passwordToken);
    }
}
