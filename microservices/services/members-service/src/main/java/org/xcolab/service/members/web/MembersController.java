package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.Role_;
import org.xcolab.service.members.domain.member.MemberDao;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.service.member.MemberService;
import org.xcolab.service.members.service.role.RoleService;
import org.xcolab.service.utils.ControllerUtils;
import org.xcolab.service.utils.PaginationHelper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MembersController {

    private final MemberService memberService;

    private final MemberDao memberDao;

    private final RoleService roleService;

    @Autowired
    public MembersController(MemberDao memberDao, RoleService roleService,
            MemberService memberService) {
        this.memberDao = memberDao;
        this.roleService = roleService;
        this.memberService = memberService;
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public List<Member> listMembers(HttpServletResponse response,
                                    @RequestParam(required = false) Integer startRecord,
                                    @RequestParam(required = false) Integer limitRecord,
                                    @RequestParam(required = false) String sort,
                                    @RequestParam(required = false) String partialName,
                                    @RequestParam(required = false) String roleName,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false) String screenName,
                                    @RequestParam(required = false) Long facebookId,
                                    @RequestParam(required = false) String openId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                Integer.toString(memberDao.countByGiven(partialName, roleName)));

        return memberDao.findByGiven(paginationHelper, partialName, roleName,
                email, screenName, facebookId, openId);
    }

    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.GET)
    public Member getMember(@PathVariable long memberId) throws NotFoundException {
        if (memberId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            return memberDao.getMember(memberId).orElseThrow(NotFoundException::new);
        }
    }

    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.DELETE)
    public boolean deleteMember(@PathVariable long memberId) throws NotFoundException {
        if (memberId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            final Member member = memberDao.getMember(memberId).orElseThrow(NotFoundException::new);
            member.setStatus(5);
            return memberDao.updateMember(member);
        }
    }

    @RequestMapping(value = "/members/{memberId}/roles", method = RequestMethod.GET)
    public List<Role_> getMemberRoles(@PathVariable Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        } else {
            return this.roleService.getMemberRoles(memberId);
        }
    }

    @RequestMapping(value = "/members/count", method = RequestMethod.GET)
    public Integer countMembers(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String category) {
        return memberDao.countByGiven(screenName, category);
    }

    @RequestMapping(value = "/members", method = RequestMethod.POST)
    public Member register(@RequestBody Member member) throws NoSuchAlgorithmException {
        return memberService.register(member.getScreenName(), member.getHashedPassword(),
                member.getEmailAddress(), member.getFirstName(), member.getLastName(),
                member.getShortBio(), member.getCountry(), member.getFacebookId(),
                member.getOpenId(), member.getPortraitFileEntryId(), member.getId_());
    }

    @RequestMapping(value = "/members/registerFromSharedColab", method = RequestMethod.POST)
    public Member registerFromSharedColab(@RequestBody Member member) throws NoSuchAlgorithmException {
            return memberService.registerWithHashedPassword(member.getScreenName(), member.getHashedPassword(),
                    member.getEmailAddress(), member.getFirstName(), member.getLastName(),
                    member.getShortBio(), member.getCountry(), member.getFacebookId(),
                    member.getOpenId(), member.getPortraitFileEntryId(), member.getId_());
    }

    @RequestMapping(value = "/members/isUsed", method = RequestMethod.GET)
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

    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.PUT)
    public boolean updateMember(@RequestBody Member member, @PathVariable Long memberId)
            throws NotFoundException {
        if (memberId == 0 || memberDao.getMember(memberId) == null) {
            throw new NotFoundException("No member with id " + memberId);
        }
        return memberDao.updateMember(member);
    }

    @RequestMapping(value = "/members/{memberId}/activityCount", method = RequestMethod.GET)
    public int getMemberActivityCount(@PathVariable Long memberId) {
        if (memberId == null) {
            return 0;
        } else {
            Integer ret = memberDao.getMemberActivityCount(memberId);
            return ((ret == null) ? (0) : (ret));
        }
    }

    @RequestMapping(value = "/members/{memberId}/materializedPoints", method = RequestMethod.GET)
    public int getMemberMaterializedPoints(@PathVariable Long memberId) {
        if (memberId == null) {
            return 0;
        } else {
            Integer ret = memberDao.getMemberMaterializedPoints(memberId);
            return ((ret == null) ? (0) : (ret));
        }
    }


    @RequestMapping(value = "/members/generateScreenName", method = RequestMethod.GET)
    public String generateScreenName(@RequestParam String[] values) {
        return memberService.generateScreenName(values);
    }

    @RequestMapping(value = "/members/hashPassword", method = RequestMethod.GET)
    public String hashPassword(@RequestParam String password,
                               @RequestParam(required = false) Boolean liferayCompatible)
            throws NoSuchAlgorithmException {
        return memberService
                .hashPassword(password, liferayCompatible != null ? liferayCompatible : false);
    }

    @RequestMapping(value = "/members/validatePassword", method = RequestMethod.GET)
    public Boolean validatePassword(
            @RequestParam String password,
            @RequestParam(required = false) String hash,
            @RequestParam(required = false) Long memberId)
            throws NoSuchAlgorithmException, NotFoundException {

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

    @RequestMapping(value = "/members/createForgotPasswordToken", method = RequestMethod.GET)
    public String createForgotPasswordToken(
            @RequestParam(required = false) Long memberId)
            throws NoSuchAlgorithmException, NotFoundException {
        if (memberId != null) {
            return memberService.createNewForgotPasswordToken(memberId);
        }
        throw new NotFoundException(
                "The endpoint you requested is not available for the given attributes");
    }

    @RequestMapping(value = "/members/updateForgottenPassword", method = RequestMethod.POST)
    public Long updateForgottenPasswordByToken(
            @RequestParam(required = false) String forgotPasswordToken,
            @RequestParam(required = false) String password)
            throws NoSuchAlgorithmException, NotFoundException {
        if (forgotPasswordToken != null) {
            return memberService.updateUserPasswordWithToken(forgotPasswordToken, password);
        }
        throw new NotFoundException(
                "The endpoint you requested is not available for the given attributes");
    }

    @RequestMapping(value = "/members/validateForgotPasswordToken", method = RequestMethod.GET)
    public boolean validateForgotPasswordToken(
            @RequestParam(required = false) String passwordToken)
            throws NoSuchAlgorithmException, NotFoundException {
        if (passwordToken != null) {
            return memberService.validateForgotPasswordToken(passwordToken);
        }
        throw new NotFoundException(
                "The endpoint you requested is not available for the given attributes");
    }

    @RequestMapping(value = "/members/{memberId}/login", method = RequestMethod.POST)
    public boolean login(@PathVariable long memberId, @RequestBody String password)
            throws NoSuchAlgorithmException, NotFoundException {
        final Member member = memberDao.getMember(memberId).orElseThrow(NotFoundException::new);
        return memberService.validatePassword(password, member.getHashedPassword());
    }

    @RequestMapping(value = "/members/{memberId}/subscribe", method = RequestMethod.PUT)
    public boolean subscribe(@PathVariable long memberId) throws NotFoundException {
        return memberService.subscribeToNewsletter(memberId);
    }

    @RequestMapping(value = "/members/{memberId}/unsubscribe", method = RequestMethod.PUT)
    public boolean unsubscribe(@PathVariable long memberId) throws NotFoundException {
        return memberService.unsubscribeFromNewsletter(memberId);
    }

    @RequestMapping(value = "/members/{memberId}/isSubscribed", method = RequestMethod.GET)
    public boolean isSubscribed(@PathVariable long memberId) throws IOException, NotFoundException {
        return memberService.isSubscribedToNewsletter(memberId);
    }

    @RequestMapping(value = "/members/{memberId}/contestRoles", method = RequestMethod.GET)
    public List<Role_> getMemberRoles(@PathVariable long memberId,
                                      @RequestParam long contestId) {
        return this.roleService.getMemberRolesInContest(memberId, contestId);
    }
}
