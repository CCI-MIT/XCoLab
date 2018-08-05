package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/members")
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

    @GetMapping
    public List<Member> listMembers(HttpServletResponse response,
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String partialName,
            @RequestParam(required = false) String partialEmail,
            @RequestParam(required = false) String roleName,
            @RequestParam(required = false) List<Long> roleIds,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) Long facebookId,
            @RequestParam(required = false) String googleId,
            @RequestParam(required = false) String colabSsoId,
            @RequestParam(required = false) String climateXId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                Integer.toString(memberDao.countByGiven(partialName, partialEmail, roleName)));

        return memberDao.findByGiven(paginationHelper, partialName, partialEmail,
                roleName, email, screenName, facebookId, googleId, colabSsoId, climateXId, roleIds);
    }

    @GetMapping("findByIp")
    public List<Member> getMemberByIp(@RequestParam String ip){
        return memberDao.findByIp(ip);
    }

    @GetMapping("findByScreenNameOrName")
    public List<Member> getMemberByScreenNameName(@RequestParam String name){
        return memberDao.findByScreenNameName(name);
    }

    @GetMapping("findByScreenName")
    public Member getMemberByScreenNameNoRole(@RequestParam String screenName) throws NotFoundException {
        if (screenName == null) {
            throw new NotFoundException("No message id given");
        } else {
            return memberDao.findOneByScreenName(screenName).orElseThrow(NotFoundException::new);
        }
    }
    @GetMapping("{userId}")
    public Member getMember(@PathVariable long userId) throws NotFoundException {
        if (userId == 0) {
            throw new NotFoundException("No member id given");
        } else {
            return memberDao.getMember(userId).orElseThrow(NotFoundException::new);
        }
    }

    @PutMapping(value = "{userId}")
    public boolean updateMember(@RequestBody Member member, @PathVariable Long userId)
            throws NotFoundException {
        if (userId == 0 || memberDao.getMember(userId) == null) {
            throw new NotFoundException("No member with id " + userId);
        }
        return memberDao.updateMember(member);
    }

    @DeleteMapping("{userId}")
    public boolean deleteMember(@PathVariable long userId) throws NotFoundException {
        if (userId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            final Member member = memberDao.getMember(userId).orElseThrow(NotFoundException::new);
            member.setStatus(5);
            return memberDao.updateMember(member);
        }
    }

    @GetMapping("{userId}/roles")
    public List<Role_> getMemberRoles(@PathVariable long userId,
            @RequestParam(required =  false) Long contestId) {
        if (contestId == null) {
            return this.roleService.getMemberRoles(userId);
        } else {
            return roleService.getMemberRolesInContest(userId, contestId);
        }
    }

    @PutMapping("{userId}/roles/{roleId}")
    public boolean assignMemberRole(@PathVariable long userId,
            @PathVariable Long roleId) {
        return this.roleService.assignMemberRole(userId, roleId);
    }

    @DeleteMapping("{userId}/roles/{roleId}")
    public boolean deleteMemberRole(@PathVariable long userId,
            @PathVariable Long roleId) {
        return this.roleService.deleteMemberRole(userId, roleId);
    }

    @GetMapping("count")
    public Integer countMembers(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String category) {
        return memberDao.countByGiven(screenName, null, category);
    }

    @PostMapping
    public Member register(@RequestBody Member member) {
        return memberService.register(member);
    }

    @GetMapping("{userId}/points")
    public int getMemberPoints(@PathVariable Long userId,
            @RequestParam (required = false, defaultValue = "false") boolean hypothetical) {
        if (userId == null) {
            return 0;
        } else {
            Integer ret;
            if (hypothetical) {
                ret = memberDao.getMemberHypotheticalPoints(userId);
            } else {
                ret = memberDao.getMemberMaterializedPoints(userId);
            }
            return ((ret == null) ? (0) : (ret));
        }
    }

    @PutMapping("{userId}/subscribe")
    public boolean subscribe(@PathVariable long userId) throws NotFoundException {
        return memberService.subscribeToNewsletter(userId);
    }

    @PutMapping("{userId}/unsubscribe")
    public boolean unsubscribe(@PathVariable long userId) throws NotFoundException {
        return memberService.unsubscribeFromNewsletter(userId);
    }

    @GetMapping("{userId}/isSubscribed")
    public boolean isSubscribed(@PathVariable long userId) throws IOException, NotFoundException {
        return memberService.isSubscribedToNewsletter(userId);
    }
}
