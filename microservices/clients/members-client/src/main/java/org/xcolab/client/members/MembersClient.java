package org.xcolab.client.members;

import org.xcolab.client.members.exceptions.LockoutLoginException;
import org.xcolab.client.members.exceptions.MemberCategoryNotFoundException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.PasswordLoginException;
import org.xcolab.client.members.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.members.pojo.Contact_;
import org.xcolab.client.members.pojo.LoginBean;
import org.xcolab.client.members.pojo.LoginLog;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.client.members.pojo.Role_;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.Http401UnauthorizedException;
import org.xcolab.util.http.exceptions.Http403ForbiddenException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public final class MembersClient {

    private static final RestService memberService = new RestService(CoLabService.MEMBER,
            ServiceRequestUtils.getNamespace());

    private static final RestResource1<Member, Long> memberResource = new RestResource1<>(memberService,
            "members", Member.TYPES);

    private static final RestResource2L<Member, Role_> memberRoleResource =
            new RestResource2L<>(memberResource, "roles", Role_.TYPES);

    private static final RestResource<MemberCategory, Long> memberCategoryResource =
            new RestResource1<>(memberService, "membercategories", MemberCategory.TYPES);
    private static final RestResource<Contact_, Long> contactResource = new RestResource1<>(memberService,
            "contacts", Contact_.TYPES);

    private static final RestResource<LoginLog, Long> loginLogResource = new RestResource1<>(memberService,
            "loginLogs", LoginLog.TYPES);

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
                case "USER_NAME": memberListQuery.queryParam("sort", prefix + "screenName");
                    break;
                case "MEMBER_SINCE": memberListQuery.queryParam("sort", prefix + "createDate");
                    break;
                case "CATEGORY": memberListQuery.queryParam("sort", prefix + "roleName");
                    break;
                case "ACTIVITY": memberListQuery.queryParam("sort", prefix + "activityCount");
                    break;
                case "POINTS": memberListQuery.queryParam("sort", prefix + "points");
                    break;
                default:
            }
        }

        return memberListQuery.execute();
    }

    public static Integer countMembers(String categoryFilterValue, String screenNameFilterValue) {
        try {
            return memberResource.<Member, Integer>service("count", Integer.class)
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

    public static Integer getMemberActivityCount(long memberId) {
        try {
            return memberResource.service(memberId, "activityCount", Integer.class).getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer getMemberMaterializedPoints(long memberId) {
        try {
            return memberResource.service(memberId, "points", Integer.class).getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer getMemberHypotheticalPoints(long memberId) {
        try {
            return memberResource.service(memberId, "points", Integer.class)
                    .queryParam("hypothetical", true)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static List<Role_> getMemberRoles(long memberId) {
        return memberRoleResource.resolveParent(memberResource.id(memberId))
                .list()
                .withCache(CacheName.ROLES)
                .execute();
    }

    public static void assignMemberRole(long memberId, long roleId) {
        memberRoleResource.resolveParent(memberResource.id(memberId))
                .update(null, roleId)
                .execute();
        ServiceRequestUtils.clearCache(CacheName.ROLES);
    }

    public static void removeMemberRole(long memberId, long roleId) {
        memberRoleResource.resolveParent(memberResource.id(memberId))
                .delete(roleId)
                .execute();
        ServiceRequestUtils.clearCache(CacheName.ROLES);
    }

    //TODO: this seems to be duplicated in the ContestTeamMemberClient
    public static List<Role_> getMemberRolesInContest(long memberId, long contestId) {
        return memberRoleResource.resolveParent(memberResource.id(memberId))
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

    public static Member getMember(long memberId) throws MemberNotFoundException {
        try {
            return getMemberInternal(memberId);
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with id " + memberId + " not found.");
        }
    }

    public static Member getMemberOrNull(long memberId) {
        try {
            return getMemberInternal(memberId);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public static Member getMemberUnchecked(long memberId) {
        try {
            return getMemberInternal(memberId);
        } catch (EntityNotFoundException e) {
            throw new UncheckedMemberNotFoundException(memberId);
        }
    }

    private static Member getMemberInternal(long memberId) throws EntityNotFoundException {

        return memberResource.get(memberId)
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
            return memberResource.service("findByIp",
                    Member.TYPES.getTypeReference())
                    .queryParam("ip", ip).getList();
    }


    public static List<Member> findByScreenNameOrName(String ip) {
        return memberResource.service("findByScreenNameOrName",
                Member.TYPES.getTypeReference())
                .queryParam("name", ip).getList();
    }

    public static Member findMemberByScreenNameNoRole(String screenName) throws MemberNotFoundException {
        try {
            return memberResource.service("findByScreenName", Member.class)
                    .queryParam("screenName", screenName).getChecked();
        } catch (EntityNotFoundException ignored) {
            throw new MemberNotFoundException("Member with screenName " + screenName + " does not exist");
        }

    }

    public static Member findMemberByFacebookId(long facebookId) throws MemberNotFoundException {
        Member member = memberResource.list()
                .queryParam("facebookId", facebookId)
                .executeWithResult().getFirstIfExists();
        if (member == null) {
            throw new MemberNotFoundException("Member with facebookId " + facebookId + " does not exist");
        }
        return member;
    }

    public static Member findMemberByGoogleId(String googleId) throws MemberNotFoundException {
        Member member = memberResource.list()
                .queryParam("googleId", googleId)
                .executeWithResult().getFirstIfExists();
        if (member == null) {
            throw new MemberNotFoundException("Member with googleId " + googleId + " does not exist");
        }
        return member;
    }

    public static boolean updateMember(Member member) {
        return memberResource.update(member, member.getId_())
                .cacheName(CacheName.MEMBER)
                .execute();
    }

    public static boolean deleteMember(long memberId) {
        return memberResource.delete(memberId)
                .cacheName(CacheName.MEMBER)
                .execute();
    }

    public static boolean isScreenNameUsed(String screenName) {
        return memberResource.service("isUsed", Boolean.class)
                .queryParam("screenName", screenName)
                .get();
    }

    public static boolean isEmailUsed(String email) {
        return memberResource.service("isUsed", Boolean.class)
                .queryParam("email", email)
                .get();
    }

    public static Long updateUserPassword(String forgotPasswordToken, String password) {
        password = encode(password);

        final Long result = memberResource.service("updateForgottenPassword", Long.class)
                .queryParam("forgotPasswordToken", forgotPasswordToken)
                .queryParam("password", password)
                .post();
        //TODO: improve API so we can do more fine-grained cache refreshing
        ServiceRequestUtils.clearCache(CacheName.MEMBER);
        return result;
    }

    private static String encode(String password) {
        try {
            password = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalException(e);
        }
        return password;
    }

    public static boolean isForgotPasswordTokenValid(String passwordToken) {
        try {
            return memberResource.service("validateForgotPasswordToken", Boolean.class)
                    .queryParam("passwordToken", passwordToken)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    public static String createForgotPasswordToken(Long memberId) {
        return memberResource.service("createForgotPasswordToken", String.class)
                .queryParam("memberId", memberId)
                //TODO: this should be posted!
                .get();
    }

    public static String generateScreenName(String lastName, String firstName) {
        return memberResource.service("generateScreenName", String.class)
                .queryParam("values", firstName, lastName)
                .get();
    }

    public static String hashPassword(String password) {
        password = encode(password);
        return memberResource.service("hashPassword", String.class)
                .queryParam("password", password)
                .get();
    }

    public static boolean validatePassword(String password, long memberId) {

        password = encode(password);
        return memberResource.service("validatePassword", Boolean.class)
                .queryParam("password", password)
                .queryParam("memberId", memberId)
                .post();
    }

    public static boolean validatePassword(String password, String hashedPassword) {
        hashedPassword = encode(hashedPassword);
        password = encode(password);

        return memberResource.service("validatePassword", Boolean.class)
                .queryParam("password", password)
                .queryParam("hash", hashedPassword)
                .post();
    }

    public static boolean updatePassword(long memberId, String newPassword) {
        newPassword = encode(newPassword);
        final Boolean result = memberResource.service(memberId, "updatePassword", Boolean.class)
                .queryParam("newPassword", newPassword)
                .post();
        //TODO: improve endpoint for caching
        memberResource.get(memberId).withCache(CacheName.MEMBER).deleteFromCache();
        return result;
    }

    public static Member register(Member member) {
        return memberResource.create(member).execute();
    }

    public static boolean login(long memberId, String password, String remoteIp, String redirectUrl)
            throws PasswordLoginException, LockoutLoginException {
        LoginBean loginBean = new LoginBean();
        loginBean.setPassword(password);
        loginBean.setIpAddress(remoteIp);
        loginBean.setRedirectUrl(redirectUrl);
        try {
            return memberResource.service(memberId, "login", Boolean.class)
                    .post(loginBean);
        } catch (UncheckedEntityNotFoundException e) {
            throw new UncheckedMemberNotFoundException(memberId);
        } catch (Http401UnauthorizedException e) {
            throw new PasswordLoginException();
        } catch (Http403ForbiddenException e) {
            throw new LockoutLoginException();
        }
    }

    //TODO: this shouldn't be done manually
    public static LoginLog createLoginLog(long memberId, String ipAddress, String redirectUrl) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(memberId);
        loginLog.setIpAddress(ipAddress);
        loginLog.setEntryUrl(redirectUrl != null
                ? redirectUrl.substring(0, Math.min(250, redirectUrl.length()))
                : "");

        return loginLogResource.create(loginLog)
                .execute();
    }

    public static boolean subscribeToNewsletter(long memberId) {
        return memberResource.service(memberId, "subscribe", Boolean.class).put();
    }

    public static boolean unsubscribeFromNewsletter(long memberId) {
        return memberResource.service(memberId, "unsubscribe", Boolean.class).put();
    }

    public static boolean isSubscribedToNewsletter(long memberId) {
        return memberResource.service(memberId, "isSubscribed", Boolean.class).get();
    }

    public static boolean isUserInGroup(Long memberId, Long groupId) {
        return memberResource.service(memberId, "isMemberInGroup", Boolean.class)
                .queryParam("groupId", groupId)
                .get();
    }

    public static void createUserGroupRole(Long memberId, Long groupId) {
         memberResource.service(memberId, "addMemberToGroup", Boolean.class)
                .queryParam("groupId", groupId)
                .get();
    }
}
