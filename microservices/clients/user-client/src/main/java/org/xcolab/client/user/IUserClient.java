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

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.user.exceptions.MemberCategoryNotFoundException;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.MemberCategoryWrapper;
import org.xcolab.client.user.pojo.wrapper.RoleWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.io.IOException;
import java.util.List;

@FeignClient("xcolab-user-service")
public interface IUserClient {

    @GetMapping("/members")
    List<UserWrapper> listUsers(
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
            @RequestParam(required = false) String climateXId);

    @GetMapping("/members/findByIp")
    List<UserWrapper> getUserByIp(@RequestParam String ip);

    @GetMapping("/members/findByScreenNameOrName")
    List<UserWrapper> getUserByScreenNameName(@RequestParam String name);

    @GetMapping("/members/findByScreenName")
    UserWrapper getUserByScreenNameNoRole(@RequestParam String screenName)
            throws MemberNotFoundException;


    @GetMapping("/members/{userId}")
    UserWrapper getUser(@PathVariable long userId) throws MemberNotFoundException;

    @PutMapping("/members/{userId}")
    boolean updateUser(@RequestBody UserWrapper member, @PathVariable Long userId)
            throws MemberNotFoundException;

    @DeleteMapping("/members/{userId}")
    boolean deleteUser(@PathVariable long userId) throws MemberNotFoundException;

    @GetMapping("/members/{userId}/roles")
    List<RoleWrapper> getUserRoles(@PathVariable long userId,
            @RequestParam(required = false) Long contestId);

    @PutMapping("/members/{userId}/roles/{roleId}")
    boolean assignUserRole(@PathVariable long userId,
            @PathVariable Long roleId);

    @DeleteMapping("/members/{userId}/roles/{roleId}")
    boolean deleteUserRole(@PathVariable long userId,
            @PathVariable Long roleId);

    @GetMapping("/members/count")
    Integer countUsers(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String category);

    @PostMapping("/members/")
    UserWrapper register(@RequestBody UserWrapper member);

    @GetMapping("/members/{userId}/points")
    int getUserPoints(@PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "false") boolean hypothetical);

    @PutMapping("/members/{userId}/subscribe")
    boolean subscribeToNewsletter(@PathVariable long userId) throws MemberNotFoundException;

    @PutMapping("/members/{userId}/unsubscribe")
    boolean unsubscribeToNewsletter(@PathVariable long userId) throws MemberNotFoundException;

    @GetMapping("/members/{userId}/isSubscribed")
    boolean isSubscribedToNewsletter(@PathVariable long userId)
            throws IOException, MemberNotFoundException;

    default boolean updateUser(UserWrapper member) throws MemberNotFoundException {
        return updateUser(member, member.getId());
    }

    default List<UserWrapper> listMembersWithRoles(List<Long> roleIds) {
        return listUsers(0, Integer.MAX_VALUE, null,
                null,
                null,
                null,
                roleIds,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    default MemberCategoryWrapper getHighestCategory(List<RoleWrapper> roles) {
        MemberCategoryWrapper category = null;

        for (RoleWrapper r : roles) {
            try {
                MemberCategoryWrapper currentCategory =
                        StaticUserContext.getUserCategoryClient().getMemberCategory(r.getId());
                if (category == null) {
                    category = currentCategory;
                } else if (currentCategory.getSortOrder() > category.getSortOrder()) {
                    category = currentCategory;
                }
            } catch (MemberCategoryNotFoundException e) {
                //ignore invisible categories
            }
        }

        return category;
    }

    default void assignMemberRole(long userId, long roleId) {
        assignUserRole(userId, roleId);
    }

    default void removeMemberRole(long userId, long roleId) {
        deleteUserRole(userId, roleId);
    }

    default List<UserWrapper> listMembers(String categoryFilterValue,
            String screenNameFilterValue, String emailFilterValue, String sortField,
            boolean ascOrder, int firstMember, int lastMember) {


        final String prefix = (ascOrder) ? ("") : ("-");
        String sortFieldAndPrefix = "";
        if (sortField != null && !sortField.isEmpty()) {


            switch (sortField) {
                case "USER_NAME":
                    String sortType = "screenName";
                    if (ConfigurationAttributeKey.DISPLAY_FIRST_NAME_LAST_NAME.get()) {
                        sortType = "displayName";
                    }
                    sortFieldAndPrefix = prefix + sortType;
                    break;
                case "MEMBER_SINCE":
                    sortFieldAndPrefix = prefix + "createdAt";
                    break;
                case "CATEGORY":
                    sortFieldAndPrefix = prefix + "roleName";
                    break;
                case "ACTIVITY":
                    sortFieldAndPrefix = prefix + "activityCount";
                    break;
                case "POINTS":
                    sortFieldAndPrefix = prefix + "points";
                    break;
                default:
            }
        }

        return listUsers(firstMember, lastMember, sortFieldAndPrefix,
                screenNameFilterValue,
                emailFilterValue,
                categoryFilterValue,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    default List<UserWrapper> findMembersMatching(String partialName, int maxMembers) {
        return listUsers(0, Integer.MAX_VALUE,
                null,
                partialName,
                null,
                null,
                null,
                null,
                "screenName",
                null,
                null,
                null,
                null);
    }

    default List<UserWrapper> listAllMembers() {
        return listMembers(null, null, null,
                null, true, 0, Integer.MAX_VALUE);
    }

    default Integer countMembers(String categoryFilterValue, String screenNameFilterValue) {
        return countUsers(screenNameFilterValue, categoryFilterValue);
    }

    default UserWrapper findMemberByEmailAddress(String emailAddress)
            throws MemberNotFoundException {
        List<UserWrapper> users =
                listUsers(0, Integer.MAX_VALUE, null, null,
                        null, null, null, emailAddress, null,
                        null, null,
                        null, null);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        throw new MemberNotFoundException("User with email not found" + emailAddress);

    }

    default UserWrapper findMemberByFacebookId(long facebookId) throws MemberNotFoundException {

        List<UserWrapper> users =
                listUsers(0, Integer.MAX_VALUE, null, null,
                        null, null, null, null, null,
                        facebookId, null,
                        null, null);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        throw new MemberNotFoundException(
                "Member with facebookId " + facebookId + " does not exist");

    }

    default UserWrapper findMemberByGoogleId(String googleId) throws MemberNotFoundException {

        List<UserWrapper> users =
                listUsers(0, Integer.MAX_VALUE, null, null,
                        null, null, null, null, null,
                        null, googleId,
                        null, null);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }

        throw new MemberNotFoundException("Member with googleId " + googleId + " does not exist");

    }

    default UserWrapper findMemberByColabSsoId(String colabSsoId) throws MemberNotFoundException {
        List<UserWrapper> users =
                listUsers(0, Integer.MAX_VALUE, null, null,
                        null, null, null, null, null,
                        null, null,
                        colabSsoId, null);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        throw new MemberNotFoundException(
                "Member with colabSsoId " + colabSsoId + " does not exist");

    }

    default UserWrapper findMemberByClimateXId(String climateXId) throws MemberNotFoundException {

        List<UserWrapper> users =
                listUsers(0, Integer.MAX_VALUE, null, null,
                        null, null, null, null, null,
                        null, null,
                        null, climateXId);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        throw new MemberNotFoundException(
                "Member with climateXId " + climateXId + " does not exist");

    }



    default UserWrapper findMemberByScreenName(String screenName) throws MemberNotFoundException {

        List<UserWrapper> users =
                listUsers(0, Integer.MAX_VALUE, null, null, null, null, null, null, screenName,
                        null, null,
                        null, null);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        throw new MemberNotFoundException(
                "Member with screenName " + screenName + " does not exist");

    }

    default UserWrapper getMember(long userId) throws MemberNotFoundException {
        try {
            return getMemberInternal(userId);
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with id " + userId + " not found.");
        }
    }

    default UserWrapper getMemberOrNull(long userId) {
        try {
            return getMemberInternal(userId);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    default UserWrapper getMemberUnchecked(long userId) {
        try {
            return getMemberInternal(userId);
        } catch (EntityNotFoundException e) {
            throw new UncheckedMemberNotFoundException(userId);
        }
    }

    default UserWrapper getMemberInternal(long userId) throws EntityNotFoundException {

        return getUser(userId);
    }

    default Integer getMemberMaterializedPoints(long userId) {
        return getUserPoints(userId, false);
    }
    default Integer getMemberHypotheticalPoints(long userId){
        return getUserPoints(userId, true);
    }
    default List<RoleWrapper> getMemberRolesInContest(long userId, long contestId) {
        return getUserRoles(userId,contestId);
    }
}
