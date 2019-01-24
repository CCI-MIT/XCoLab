package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.user.pojo.IRole;
import org.xcolab.client.user.pojo.IUser;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@FeignClient("xcolab-user-service")
@RequestMapping("/members")
public interface IUserClient {

    @GetMapping
    List<IUser> listUsers(HttpServletResponse response,
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
            @RequestParam(required = false) String climateXId) ;

    @GetMapping("findByIp")
    List<IUser> getUserByIp(@RequestParam String ip);

    @GetMapping("findByScreenNameOrName")
    List<IUser> getUserByScreenNameName(@RequestParam String name);

    @GetMapping("findByScreenName")
    IUser getUserByScreenNameNoRole(@RequestParam String screenName) throws MemberNotFoundException;


    @GetMapping("{userId}")
    IUser getUser(@PathVariable long userId) throws MemberNotFoundException;

    @PutMapping(value = "{userId}")
    boolean updateUser(@RequestBody IUser member, @PathVariable Long userId) throws MemberNotFoundException;

    @DeleteMapping("{userId}")
    boolean deleteUser(@PathVariable long userId) throws MemberNotFoundException;

    @GetMapping("{userId}/roles")
    List<IRole> getUserRoles(@PathVariable long userId,
            @RequestParam(required =  false) Long contestId);

    @PutMapping("{userId}/roles/{roleId}")
    boolean assignUserRole(@PathVariable long userId,
            @PathVariable Long roleId);

    @DeleteMapping("{userId}/roles/{roleId}")
    boolean deleteUserRole(@PathVariable long userId,
            @PathVariable Long roleId);

    @GetMapping("count")
    Integer countUsers(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String category);

    @PostMapping
    IUser register(@RequestBody IUser member);

    @GetMapping("{userId}/points")
    int getUserPoints(@PathVariable Long userId,
            @RequestParam (required = false, defaultValue = "false") boolean hypothetical);

    @PutMapping("{userId}/subscribe")
    boolean subscribe(@PathVariable long userId) throws MemberNotFoundException;

    @PutMapping("{userId}/unsubscribe")
    boolean unsubscribe(@PathVariable long userId) throws MemberNotFoundException;

    @GetMapping("{userId}/isSubscribed")
    boolean isSubscribed(@PathVariable long userId) throws IOException, MemberNotFoundException;

    default boolean updateUser(IUser member) throws MemberNotFoundException {
        return updateUser(member,0l);
    }


    default IUser getMember(long userId) throws MemberNotFoundException {
        try {
            return getMemberInternal(userId);
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with id " + userId + " not found.");
        }
    }

    default IUser getMemberOrNull(long userId) {
        try {
            return getMemberInternal(userId);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    default IUser getMemberUnchecked(long userId) {
        try {
            return getMemberInternal(userId);
        } catch (EntityNotFoundException e) {
            throw new UncheckedMemberNotFoundException(userId);
        }
    }

    default IUser getMemberInternal(long userId) throws EntityNotFoundException {

        return getMember(userId);
    }
}
