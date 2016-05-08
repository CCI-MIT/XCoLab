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
import org.xcolab.service.utils.PaginationHelper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MembersController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public List<Member> listMembers(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String partialName,
            @RequestParam(required = false) String roleName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) Long facebookId,
            @RequestParam(required = false) String openId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return memberDao.findByGiven(paginationHelper, partialName, roleName,
                email, screenName, facebookId, openId);
    }

    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.GET)
    public Member getMember(@PathVariable("memberId") Long memberId) throws NotFoundException {
        if (memberId == null || memberId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            return memberDao.getMember(memberId);
        }
    }

    @RequestMapping(value = "/members/{memberId}/roles", method = RequestMethod.GET)
    public List<Role_> getMemberRoles(@PathVariable("memberId") Long memberId) {
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
        if (category != null && !category.isEmpty()) {
            return memberDao.countMembersFilteredByCategory(screenName, category);
        } else {
            return memberDao.countMembers(screenName);
        }
    }

    @RequestMapping(value = "/members", method = RequestMethod.POST)
    public Member register(@RequestBody Member member) throws NoSuchAlgorithmException {
        return memberService.register(member.getScreenName(), member.getHashedPassword(),
                member.getEmailAddress(), member.getFirstName(), member.getLastName(),
                member.getShortBio(), member.getCountry(), member.getFacebookId(),
                member.getOpenId(), 0L, member.getId_());
    }

    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.PUT)
    public String updateMember(@RequestBody Member member, @PathVariable("memberId") Long memberId)
            throws NotFoundException {
        if (memberDao.getMember(memberId) != null) {
            memberDao.updateMember(member);
            return "Updated successfully";
        } else {
            return "Member not found";
        }
    }

    @RequestMapping(value = "/members/{memberId}/activityCount", method = RequestMethod.GET)
    public Integer getMemberActivityCount(@PathVariable("memberId") Long memberId) {
        if (memberId == null) {
            return 0;
        } else {
            Integer ret = memberDao.getMemberActivityCount(memberId);
            return ((ret == null) ? (0) : (ret));
        }
    }

    @RequestMapping(value = "/members/{memberId}/materializedPoints", method = RequestMethod.GET)
    public Integer getMemberMaterializedPoints(@PathVariable("memberId") Long memberId) {
        if (memberId == null) {
            return 0;
        } else {
            Integer ret = memberDao.getMemberMaterializedPoints(memberId);
            return ((ret == null) ? (0) : (ret));
        }
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
            return memberService
                    .validatePassword(password, memberDao.getMember(memberId).getHashedPassword());
        }
        throw new NotFoundException(
                "The endpoint you requested is not available for the given attributes");
    }

    @RequestMapping(value = "/members/{memberId}/login", method = RequestMethod.POST)
    public boolean login(@PathVariable long memberId, @RequestBody String password)
            throws NoSuchAlgorithmException, NotFoundException {
        final Member member = memberDao.getMember(memberId);
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

    @RequestMapping(value = "/members/{memberId}/roles/contests/{contestId}", method = RequestMethod.GET)
    public List<Role_> getMemberRoles(@PathVariable("memberId") Long memberId,
            @PathVariable("contestId") Long contestId) {
        if (memberId == null || contestId == null) {
            return new ArrayList<>();
        } else {
            return this.roleService.getMemberRolesInContest(memberId, contestId);
        }
    }
}
