package org.xcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.Role_;
import org.xcolab.model.tables.pojos.User_;
import org.xcolab.service.member.MemberService;
import org.xcolab.service.role.RoleService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MembersController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoleService roleService;

    private static final Integer NUMBER_OF_RECORDS_PER_REQUEST = 20;
	@RequestMapping("/members")
    public List<User_> listMembers(@RequestParam String firstRecord,
                             @RequestParam String lastRecord,
                             @RequestParam(required = false) String sort,
                             @RequestParam(required = false) String screenName,
                             @RequestParam(required = false) String category) {

        boolean isAsc = true;

        if(sort == null ) {
            sort = "";
            isAsc = false;
        }

        if (sort.startsWith("-")) {
            sort = sort.substring(1, sort.length());
            isAsc = false;
        }

        if(screenName == null){
            screenName = "";
        }
        Integer firstRecordInt = 0 ;
        Integer lastRecordInt = NUMBER_OF_RECORDS_PER_REQUEST ;
        try{
            firstRecordInt = Integer.parseInt(firstRecord);
            lastRecordInt = Integer.parseInt(lastRecord);
        } catch (NumberFormatException e) {
            //log exeption
        }

        switch (sort) {
            case "USER_NAME":
                if(category == null) {
                    return memberService.listMembersSortByScreenName(firstRecordInt, lastRecordInt, screenName, isAsc);
                }else{
                    return memberService.listMembersSortByScreenNameFilteredByCategory(firstRecordInt,
                            lastRecordInt, screenName, isAsc, category);
                }
            case "POINTS":
                if(category == null) {
                    return memberService.listMembersSortByPoint(firstRecordInt, lastRecordInt, screenName, isAsc);
                }else{
                    return memberService.listMembersSortByPoint(firstRecordInt, lastRecordInt, screenName, isAsc);
                }
            case "ACTIVITY":
                if(category == null) {
                    return memberService.listMembersSortByActivityCount(firstRecordInt, lastRecordInt, screenName, isAsc);
                }else{
                    return memberService.listMembersSortByActivityCount(firstRecordInt, lastRecordInt, screenName, isAsc);
                }
            case "CATEGORY":
                if(category == null) {
                    return memberService.listMembersSortByRoleName(firstRecordInt, lastRecordInt, screenName, isAsc);
                }else{
                    return memberService.listMembersSortByRoleName(firstRecordInt, lastRecordInt, screenName, isAsc);
                }
            case "MEMBER_SINCE":
                if(category == null) {
                    return memberService.listMembersSortByMemberSince(firstRecordInt, lastRecordInt, screenName, isAsc);
                }else{
                    return memberService.listMembersSortByMemberSinceFilteredByCategory(firstRecordInt,
                            lastRecordInt, screenName, isAsc, category);
                }
            default:
                return memberService.listMembersSortByPoint(firstRecordInt, lastRecordInt, screenName, isAsc);
        }
    }

    @RequestMapping("/members/count")
    public Integer countMembers(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String category) {

        if (category != null && !category.isEmpty()) {
            return memberService.countMembersFilteredByCategory(screenName, category);
        } else {
            return memberService.countMembers(screenName);
        }

    }

    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.GET)
    public User_ getMember(@PathVariable("memberId") String memberId){

        return null;
    }
    @RequestMapping(value = "/members/{memberId}/activityCount", method = RequestMethod.GET)
    public Integer getMemberActivityCount(@PathVariable("memberId") Long memberId){
        if(memberId == null){
            return 0;
        } else {
            return this.memberService.getMemberActivityCount(memberId);
        }

    }
    @RequestMapping(value = "/members/{memberId}/materializedPoints", method = RequestMethod.GET)
    public Integer getMemberMaterializedPoints(@PathVariable("memberId") Long memberId){
        if(memberId == null){
            return 0;
        }else{
            return this.memberService.getMemberMaterializedPoints(memberId);
        }

    }
    @RequestMapping(value = "/members/{memberId}/roles", method = RequestMethod.GET)
    public List<Role_> getMemberRoles(@PathVariable("memberId") Long memberId){
        if(memberId == null){
            return new ArrayList<>();
        }else{
            return this.roleService.getMemberRoles(memberId);
        }

    }
}
