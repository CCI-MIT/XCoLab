package org.xcolab.client.user;

import org.springframework.util.Assert;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.user.exceptions.MemberCategoryNotFoundException;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.user.pojo.LoginLog;
import org.xcolab.client.user.pojo.LoginToken;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.user.pojo.MemberCategory;
import org.xcolab.client.user.pojo.Role;
import org.xcolab.client.user.pojo.TokenValidity;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public final class MembersClient {

    private static final RestResource1<Member, Long> memberResource =
            new RestResource1<>(UserResource.USER, Member.TYPES);

    private static final RestResource2L<Member, Role> memberRoleResource =
            new RestResource2L<>(memberResource, "roles", Role.TYPES);

    private static final RestResource<MemberCategory, Long> memberCategoryResource =
            new RestResource1<>(UserResource.USER_CATEGORY, MemberCategory.TYPES);

    private static final RestResource<LoginLog, Long> loginLogResource =
            new RestResource1<>(UserResource.LOGIN_LOG, LoginLog.TYPES);

    private static final RestResource<LoginToken, String> loginTokenResource =
            new RestResource1<>(UserResource.LOGIN_TOKEN, LoginToken.TYPES);

    private MembersClient() {
    }

    public static List<Member> findMembersMatching(String partialName, int maxMembers) {
        return memberResource.list()
                .addRange(0, maxMembers)
                .queryParam("partialName", partialName)
                .queryParam("sort", "screenName")
                .execute();
    }

    public static List<Member> listAllMembers() {
        return listMembers(null, null, null, null, true, 0, Integer.MAX_VALUE);
    }

    public static List<Member> listMembersWithRoles(List<Long> roleIds) {
        return memberResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("roleIds", roleIds)
                .withCache(CacheName.MEMBER_LIST)
                .execute();
    }

    public static List<Member> listMembers(String categoryFilterValue,
            String screenNameFilterValue, String emailFilterValue, String sortField,
            boolean ascOrder, int firstMember, int lastMember) {

        final ListQuery<Member> memberListQuery = memberResource.list()
                .addRange(firstMember, lastMember)
                .optionalQueryParam("partialName", screenNameFilterValue)
                .optionalQueryParam("partialEmail", emailFilterValue)
                .optionalQueryParam("roleName", categoryFilterValue);

        if (sortField != null && !sortField.isEmpty()) {
            final String prefix = (ascOrder) ? ("") : ("-");

            switch (sortField) {
                case "USER_NAME":
                    String sortType = "screenName";
                    if (ConfigurationAttributeKey.DISPLAY_FIRST_NAME_LAST_NAME.get()) {
                        sortType = "displayName";
                    }
                    memberListQuery.queryParam("sort", prefix + sortType);
                    break;
                case "MEMBER_SINCE":
                    memberListQuery.queryParam("sort", prefix + "createdAt");
                    break;
                case "CATEGORY":
                    memberListQuery.queryParam("sort", prefix + "roleName");
                    break;
                case "ACTIVITY":
                    memberListQuery.queryParam("sort", prefix + "activityCount");
                    break;
                case "POINTS":
                    memberListQuery.queryParam("sort", prefix + "points");
                    break;
                default:
            }
        }
        memberListQuery.withCache(CacheName.MEMBER_LIST);
        return memberListQuery.execute();
    }

    public static Integer countMembers(String categoryFilterValue, String screenNameFilterValue) {
        try {
            return memberResource.<Member, Integer>collectionService("count", Integer.class)
                    .optionalQueryParam("screenName", screenNameFilterValue)
                    .optionalQueryParam("category", categoryFilterValue)
                    .withCache(CacheKeys.withClass(Member.class)
                            .withParameter("screenName", screenNameFilterValue)
                            .withParameter("category", categoryFilterValue).asCount(), CacheName.MISC_SHORT)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer getMemberMaterializedPoints(long userId) {
        try {
            return memberResource.<Member, Integer>elementService(userId,"points", Integer.class)
                    .withCache(CacheKeys.withClass(Member.class)
                            .withParameter("userId", userId)
                            .asCount(), CacheName.MISC_REQUEST)
                    .getChecked();

        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer getMemberHypotheticalPoints(long userId) {
        try {
            return memberResource.elementService(userId, "points", Integer.class)
                    .queryParam("hypothetical", true)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static List<Role> getMemberRoles(long userId) {
        return memberRoleResource.resolveParentId(memberResource.id(userId))
                .list()
                .withCache(CacheName.ROLES)
                .execute();
    }

    public static MemberCategory getHighestCategory(List<Role> roles) {
        MemberCategory category = null;

        for (Role r : roles) {
            try {
                MemberCategory currentCategory = MembersClient.getMemberCategory(r.getId());
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

    public static void assignMemberRole(long userId, long roleId) {
        memberRoleResource.resolveParentId(memberResource.id(userId))
                .update(null, roleId)
                .execute();
        ServiceRequestUtils.clearCache(CacheName.ROLES);
    }

    public static void removeMemberRole(long userId, long roleId) {
        memberRoleResource.resolveParentId(memberResource.id(userId))
                .delete(roleId)
                .execute();
        ServiceRequestUtils.clearCache(CacheName.ROLES);
    }

    //TODO COLAB-2594: this seems to be duplicated in the ContestTeamMemberClient
    public static List<Role> getMemberRolesInContest(long userId, long contestId) {
        return memberRoleResource.resolveParentId(memberResource.id(userId))
                .list()
                .queryParam("contestId", contestId)
                .withCache(CacheName.CONTEST_DETAILS)
                .execute();
    }

    public static List<MemberCategory> listMemberCategories() {
        return memberCategoryResource.list()
                .withCache(CacheName.ROLES)
                .execute();
    }

    public static MemberCategory getMemberCategory(long roleId) {
        try {
            return memberCategoryResource.get(roleId)
                    .withCache(CacheName.ROLES).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new MemberCategoryNotFoundException("Category with role id " + roleId + " not found.");
        }
    }

    public static MemberCategory getMemberCategory(String displayName) {
        MemberCategory memberCategory = memberCategoryResource.list()
                .queryParam("displayName", displayName)
                .withCache(CacheName.ROLES)
                .executeWithResult().getFirstIfExists();
        if (memberCategory == null) {
            throw new MemberCategoryNotFoundException("Category with name " + displayName + " not found.");
        }
        return memberCategory;
    }

    public static List<MemberCategory> getVisibleMemberCategories() {

        return memberCategoryResource.list()
                .queryParam("showInList", true)
                .withCache(CacheName.ROLES)
                .execute();
    }

    public static Member getMember(long userId) throws MemberNotFoundException {
        try {
            return getMemberInternal(userId);
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with id " + userId + " not found.");
        }
    }

    public static Member getMemberOrNull(long userId) {
        try {
            return getMemberInternal(userId);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public static Member getMemberUnchecked(long userId) {
        try {
            return getMemberInternal(userId);
        } catch (EntityNotFoundException e) {
            throw new UncheckedMemberNotFoundException(userId);
        }
    }

    private static Member getMemberInternal(long userId) throws EntityNotFoundException {

        return memberResource.get(userId)
                .withCache(CacheName.MEMBER)
                .executeChecked();
    }

    public static Member findMemberByEmailAddress(String emailAddress) throws MemberNotFoundException {
        Member member = memberResource.list()
                .queryParam("email", emailAddress)
                .executeWithResult().getFirstIfExists();
        if (member == null) {
            throw new MemberNotFoundException("Member with email " + emailAddress + " does not exist");
        }
        return member;
    }

    public static Member findMemberByScreenName(String screenName) throws MemberNotFoundException {
        Member member = memberResource.list()
                .queryParam("screenName", screenName)
                .executeWithResult().getFirstIfExists();
        if (member == null) {
            throw new MemberNotFoundException("Member with screenName " + screenName + " does not exist");
        }
        return member;
    }

    public static List<Member> findMembersByIp(String ip) {
            return memberResource.collectionService("findByIp",
                    Member.TYPES.getTypeReference())
                    .queryParam("ip", ip).getList();
    }


    public static List<Member> findByScreenNameOrName(String ip) {
        return memberResource.collectionService("findByScreenNameOrName",
                Member.TYPES.getTypeReference())
                .queryParam("name", ip).getList();
    }

    public static Member findMemberByScreenNameNoRole(String screenName) throws MemberNotFoundException {
        try {
            return memberResource.collectionService("findByScreenName", Member.class)
                    .queryParam("screenName", screenName).getChecked();
        } catch (EntityNotFoundException ignored) {
            throw new MemberNotFoundException("Member with screenName " + screenName + " does not exist");
        }

    }

    public static Member findMemberByFacebookId(long facebookId) throws MemberNotFoundException {
        Member member = memberResource.list()
                .queryParam("facebookId", facebookId)
                .executeWithResult().getOneIfExists();
        if (member == null) {
            throw new MemberNotFoundException("Member with facebookId " + facebookId + " does not exist");
        }
        return member;
    }

    public static Member findMemberByGoogleId(String googleId) throws MemberNotFoundException {
        Member member = memberResource.list()
                .queryParam("googleId", googleId)
                .executeWithResult().getOneIfExists();
        if (member == null) {
            throw new MemberNotFoundException("Member with googleId " + googleId + " does not exist");
        }
        return member;
    }

    public static Member findMemberByColabSsoId(String colabSsoId) throws MemberNotFoundException {
        Member member = memberResource.list()
                .queryParam("colabSsoId", colabSsoId)
                .executeWithResult().getOneIfExists();
        if (member == null) {
            throw new MemberNotFoundException("Member with colabSsoId " + colabSsoId + " does not exist");
        }
        return member;
    }

    public static Member findMemberByClimateXId(String climateXId) throws MemberNotFoundException {
        Member member = memberResource.list()
                .queryParam("climateXId", climateXId)
                .executeWithResult().getOneIfExists();
        if (member == null) {
            throw new MemberNotFoundException("Member with climateXId " + climateXId + " does not exist");
        }
        return member;
    }

    public static boolean updateMember(Member member) {
        return memberResource.update(member, member.getId())
                .cacheName(CacheName.MEMBER)
                .execute();
    }

    public static boolean deleteMember(long userId) {
        boolean result = memberResource.delete(userId)
                .cacheName(CacheName.MEMBER)
                .execute();
        if (result) {
            ServiceRequestUtils.clearCache(CacheName.MEMBER_LIST);
        }
        return result;
    }

    public static boolean isScreenNameUsed(String screenName) {
        return memberResource.collectionService("isUsed", Boolean.class)
                .queryParam("screenName", screenName)
                .get();
    }

    public static boolean isEmailUsed(String email) {
        return memberResource.collectionService("isUsed", Boolean.class)
                .queryParam("email", email)
                .get();
    }

    public static Long updateUserPassword(String forgotPasswordToken, String password) {
        final Long result = memberResource.collectionService("updateForgottenPassword", Long.class)
                .queryParam("forgotPasswordToken", forgotPasswordToken)
                .queryParam("password", encode(password))
                .post();
        //TODO COLAB-2589: improve API so we can do more fine-grained cache refreshing
        ServiceRequestUtils.clearCache(CacheName.MEMBER);
        return result;
    }

    private static String encode(String password) {
        if (password != null) {
            try {
                password = URLEncoder.encode(password, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new InternalException(e);
            }
        }
        return password;
    }

    public static boolean isForgotPasswordTokenValid(String passwordToken) {
        try {
            return memberResource.collectionService("validateForgotPasswordToken", Boolean.class)
                    .queryParam("passwordToken", passwordToken)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    public static String createForgotPasswordToken(long userId) {
        return memberResource.collectionService("createForgotPasswordToken", String.class)
                .queryParam("userId", userId)
                //TODO COLAB-2594: this should be posted!
                .get();
    }

    public static LoginToken createLoginToken(long userId) {
        final LoginToken loginToken =
                memberResource.elementService(userId, "loginToken", LoginToken.class).post();
        ServiceRequestUtils.clearCache(CacheName.MEMBER);
        return loginToken;
    }

    public static TokenValidity validateLoginToken(String tokenId, String tokenKey) {
        try {
            return loginTokenResource.elementService(tokenId, "validate", TokenValidity.class)
                    .queryParam("tokenKey", tokenKey).get();
        } catch (UncheckedEntityNotFoundException e) {
            return TokenValidity.INVALID;
        }
    }

    public static void invalidateLoginToken(String tokenId) {
        loginTokenResource.elementService(tokenId,"invalidate", Void.class)
                .post();
    }

    public static Member getMemberForLoginToken(String tokenId) {
        return loginTokenResource.elementService(tokenId, "member", Member.class)
                .get();
    }

    public static String generateScreenName(String lastName, String firstName) {
        Assert.notNull(lastName, "First name is required");
        Assert.notNull(lastName, "Last name is required");
        return memberResource.collectionService("generateScreenName", String.class)
                .queryParam("values", firstName, lastName)
                .get();
    }

    public static String hashPassword(String password) {
        password = encode(password);
        return memberResource.collectionService("hashPassword", String.class)
                .queryParam("password", password)
                .get();
    }

    public static boolean validatePassword(String password, long userId) {
        return memberResource.collectionService("validatePassword", Boolean.class)
                .queryParam("password", encode(password))
                .queryParam("userId", userId)
                .post();
    }

    public static boolean validatePassword(String password, String hashedPassword) {
        return memberResource.collectionService("validatePassword", Boolean.class)
                .queryParam("password", encode(password))
                .queryParam("hash", encode(hashedPassword))
                .post();
    }

    public static boolean updatePassword(long userId, String newPassword) {
        final Boolean result = memberResource.elementService(userId, "updatePassword", Boolean.class)
                .queryParam("newPassword", encode(newPassword))
                .post();
        //TODO COLAB-2589: improve endpoint for caching
        memberResource.get(userId).withCache(CacheName.MEMBER).deleteFromCache();
        return result;
    }

    public static Member register(Member member) {
        return memberResource.create(member).execute();
    }

    //TODO COLAB-2594: this shouldn't be done manually
    public static LoginLog createLoginLog(long userId, String ipAddress, String redirectUrl) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(userId);
        loginLog.setIpAddress(ipAddress);
        loginLog.setEntryUrl(redirectUrl != null
                ? redirectUrl.substring(0, Math.min(250, redirectUrl.length()))
                : "");

        return loginLogResource.create(loginLog)
                .execute();
    }

    public static boolean subscribeToNewsletter(long userId) {
        return memberResource.elementService(userId, "subscribe", Boolean.class).put();
    }

    public static boolean unsubscribeFromNewsletter(long userId) {
        return memberResource.elementService(userId, "unsubscribe", Boolean.class).put();
    }

    public static boolean isSubscribedToNewsletter(long userId) {
        return memberResource.elementService(userId, "isSubscribed", Boolean.class).get();
    }
}
