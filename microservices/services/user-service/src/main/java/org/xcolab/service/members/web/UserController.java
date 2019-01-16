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

import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.IRole;
import org.xcolab.client.user.pojo.IUser;
import org.xcolab.service.members.domain.member.UserDao;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.service.member.UserService;
import org.xcolab.service.members.service.role.RoleService;
import org.xcolab.service.utils.ControllerUtils;
import org.xcolab.service.utils.PaginationHelper;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController

@RequestMapping("/members")
public class UserController implements IUserClient {

    private final UserService memberService;

    private final UserDao memberDao;

    private final RoleService roleService;

    @Autowired
    public UserController(UserDao memberDao, RoleService roleService,
            UserService memberService) {
        this.memberDao = memberDao;
        this.roleService = roleService;
        this.memberService = memberService;
    }

    @Override
    @GetMapping
    public List<IUser> listUsers(HttpServletResponse response,
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

    @Override
    @GetMapping("findByIp")
    public List<IUser> getUserByIp(@RequestParam String ip) {
        return memberDao.findByIp(ip);
    }

    @Override
    @GetMapping("findByScreenNameOrName")
    public List<IUser> getUserByScreenNameName(@RequestParam String name) {
        return memberDao.findByScreenNameName(name);
    }

    @Override
    @GetMapping("findByScreenName")
    public IUser getUserByScreenNameNoRole(@RequestParam String screenName)
            throws MemberNotFoundException {
        if (screenName == null) {
            throw new MemberNotFoundException("No message id given");
        } else {
            return memberDao.findOneByScreenName(screenName)
                    .orElseThrow(MemberNotFoundException::new);
        }
    }

    @Override
    @GetMapping("{userId}")
    public IUser getUser(@PathVariable long userId) throws MemberNotFoundException {
        if (userId == 0) {
            throw new MemberNotFoundException("No member id given");
        } else {
            return memberDao.getUser(userId).orElseThrow(MemberNotFoundException::new);
        }
    }

    @Override
    @PutMapping(value = "{userId}")
    public boolean updateUser(@RequestBody IUser member, @PathVariable Long userId)
            throws MemberNotFoundException {
        if (userId == 0 || memberDao.getUser(userId) == null) {
            throw new MemberNotFoundException("No member with id " + userId);
        }
        return memberDao.updateUser(member);
    }

    @Override
    @DeleteMapping("{userId}")
    public boolean deleteUser(@PathVariable long userId) throws MemberNotFoundException {
        if (userId == 0) {
            throw new MemberNotFoundException("No message id given");
        } else {
            final IUser member =
                    memberDao.getUser(userId).orElseThrow(MemberNotFoundException::new);
            member.setStatus(5);
            return memberDao.updateUser(member);
        }
    }

    @Override
    @GetMapping("{userId}/roles")
    public List<IRole> getUserRoles(@PathVariable long userId,
            @RequestParam(required = false) Long contestId) {
        if (contestId == null) {
            return this.roleService.getUserRoles(userId);
        } else {
            return roleService.getUserRolesInContest(userId, contestId);
        }
    }

    @Override
    @PutMapping("{userId}/roles/{roleId}")
    public boolean assignUserRole(@PathVariable long userId,
            @PathVariable Long roleId) {
        return this.roleService.assignUserRole(userId, roleId);
    }

    @Override
    @DeleteMapping("{userId}/roles/{roleId}")
    public boolean deleteUserRole(@PathVariable long userId,
            @PathVariable Long roleId) {
        return this.roleService.deleteUserRole(userId, roleId);
    }

    @Override
    @GetMapping("count")
    public Integer countUsers(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String category) {
        return memberDao.countByGiven(screenName, null, category);
    }

    @Override
    @PostMapping
    public IUser register(@RequestBody IUser member) {
        return memberService.register(member);
    }

    @Override
    @GetMapping("{userId}/points")
    public int getUserPoints(@PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "false") boolean hypothetical) {
        if (userId == null) {
            return 0;
        } else {
            Integer ret;
            if (hypothetical) {
                ret = memberDao.getUserHypotheticalPoints(userId);
            } else {
                ret = memberDao.getUserMaterializedPoints(userId);
            }
            return ((ret == null) ? (0) : (ret));
        }
    }

    @Override
    @PutMapping("{userId}/subscribe")
    public boolean subscribe(@PathVariable long userId) throws MemberNotFoundException {
        try {
            return memberService.subscribeToNewsletter(userId);
        } catch (NotFoundException e) {
            throw new MemberNotFoundException("User with id " + userId + "does not exist");
        }
    }

    @Override
    @PutMapping("{userId}/unsubscribe")
    public boolean unsubscribe(@PathVariable long userId) throws MemberNotFoundException {
        try {
            return memberService.unsubscribeFromNewsletter(userId);
        } catch (NotFoundException e) {
            throw new MemberNotFoundException("User with id " + userId + "does not exist");
        }
    }

    @Override
    @GetMapping("{userId}/isSubscribed")
    public boolean isSubscribed(@PathVariable long userId)
            throws IOException, MemberNotFoundException {
        try {
            return memberService.isSubscribedToNewsletter(userId);
        } catch (NotFoundException e) {
            throw new MemberNotFoundException("User with id " + userId + "does not exist");
        }
    }
}
