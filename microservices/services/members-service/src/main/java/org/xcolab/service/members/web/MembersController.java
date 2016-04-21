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
import org.xcolab.model.tables.pojos.User_;
import org.xcolab.service.members.domain.member.MemberDao;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.service.member.MemberService;
import org.xcolab.service.members.service.role.RoleService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MembersController {

    private static final Integer NUMBER_OF_RECORDS_PER_REQUEST = 20;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.GET)
    public Member getMember(@PathVariable("memberId") Long memberId) throws NotFoundException {
        if (memberId == null || memberId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            return memberDao.getMember(memberId);
        }
    }

    @RequestMapping("/members")
    public List<Member> listMembers(@RequestParam String firstRecord,
                                   @RequestParam String lastRecord,
                                   @RequestParam(required = false) String sort,
                                   @RequestParam(required = false) String screenName,
                                   @RequestParam(required = false) String category) {
        boolean isAsc = true;
        if (sort == null) {
            sort = "";
            isAsc = false;
        }
        if (sort.startsWith("-")) {
            sort = sort.substring(1, sort.length());
            isAsc = false;
        }
        if (screenName == null) {
            screenName = "";
        }
        int firstRecordInt = 0;
        int lastRecordInt = NUMBER_OF_RECORDS_PER_REQUEST;
        try {
            firstRecordInt = Integer.parseInt(firstRecord);
            lastRecordInt = Integer.parseInt(lastRecord);
        } catch (NumberFormatException e) {
            //log exeption
        }
        switch (sort) {
            case "USER_NAME":
                if (category == null) {
                    return memberDao.listMembersSortByScreenName(firstRecordInt, lastRecordInt, screenName, isAsc);
                } else {
                    return memberDao.listMembersSortByScreenNameFilteredByCategory(firstRecordInt,
                            lastRecordInt, screenName, isAsc, category);
                }
            case "POINTS":
                if (category == null) {
                    return memberDao.listMembersSortByPoint(firstRecordInt, lastRecordInt, screenName, isAsc);
                } else {
                    return memberDao.listMembersSortByPointFilteredByCategory(firstRecordInt, lastRecordInt,
                            screenName, isAsc, category);
                }
            case "ACTIVITY":
                if (category == null) {
                    return memberDao
                            .listMembersSortByActivityCount(firstRecordInt, lastRecordInt, screenName, isAsc);
                } else {
                    return memberDao.listMembersSortByActivityCountFilteredByCategory(firstRecordInt,
                            lastRecordInt, screenName, isAsc, category);
                }
            case "CATEGORY":
                if (category == null) {
                    return memberDao.listMembersSortByRoleName(firstRecordInt, lastRecordInt, screenName, isAsc);
                } else {
                    return memberDao.listMembersSortByRoleNameFilteredByCategory(firstRecordInt, lastRecordInt,
                            screenName, isAsc, category);
                }
            case "MEMBER_SINCE":
                if (category == null) {
                    return memberDao.listMembersSortByMemberSince(firstRecordInt, lastRecordInt, screenName, isAsc);
                } else {
                    return memberDao.listMembersSortByMemberSinceFilteredByCategory(firstRecordInt,
                            lastRecordInt, screenName, isAsc, category);
                }
            default:
                return memberDao.listMembersSortByPoint(firstRecordInt, lastRecordInt, screenName, isAsc);
        }
    }

    @RequestMapping("/members/count")
    public Integer countMembers(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty()) {
            return memberDao.countMembersFilteredByCategory(screenName, category);
        } else {
            return memberDao.countMembers(screenName);
        }
    }

    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.POST)
    public String updateMember(@RequestBody Member member, @PathVariable("memberId") Long memberId) {
        if (memberDao.getMember(memberId) != null) {
            memberService.updateMember(member);
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

    @RequestMapping(value = "/members/{memberId}/roles", method = RequestMethod.GET)
    public List<Role_> getMemberRoles(@PathVariable("memberId") Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        } else {
            return this.roleService.getMemberRoles(memberId);
        }
    }

    @RequestMapping("/members/isUsed")
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

    @RequestMapping("/members/generateScreenName")
    public String generateScreenName(@RequestParam String[] values) {
        return memberService.generateScreenName(values);
    }

    @RequestMapping("/members/hashPassword")
    public String hashPassword(@RequestParam String password) throws NoSuchAlgorithmException {
        return memberService.hashPassword(password);
    }

    @RequestMapping("/members/validatePassword")
    public Boolean validatePassword(
            @RequestParam String password,
            @RequestParam(required = false) String hash,
            @RequestParam(required = false) Long memberId)
            throws NoSuchAlgorithmException, NotFoundException {

        if (hash != null) {
            return memberService.validatePassword(password, hash);
        }

        if (memberId != null) {
            return memberService.validatePassword(password, memberDao.getMember(memberId).getHashedPassword());
        }
        throw new NotFoundException("The endpoint you requested is not available for the given attributes");
    }

    @RequestMapping(value = "/members", method = RequestMethod.POST)
    public User_ register(@RequestBody User_ member) {
        return null;
    }

    @RequestMapping(value = "/members/{memberId}/login", method = RequestMethod.POST)
    public boolean login(@PathVariable long memberId, @RequestBody String password) {
        return false;
    }

    @RequestMapping(value = "/members/{memberId}/subscribe", method = RequestMethod.PUT)
    public boolean subscribe(@PathVariable long memberId) {
        return memberService.subscribeToNewsletter(memberId);
    }

    @RequestMapping(value = "/members/{memberId}/unsubscribe", method = RequestMethod.PUT)
    public boolean unsubscribe(@PathVariable long memberId) {
        return memberService.unsubscribeFromNewsletter(memberId);
    }

    @RequestMapping(value = "/members/{memberId}/isSubscribed", method = RequestMethod.GET)
    public boolean isSubscribed(@PathVariable long memberId) throws IOException {
        return memberService.isSubscribedToNewsletter(memberId);
    }
}
