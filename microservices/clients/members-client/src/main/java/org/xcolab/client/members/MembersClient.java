package org.xcolab.client.members;

import org.xcolab.client.members.exceptions.MemberCategoryNotFoundException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.members.pojo.Contact_;
import org.xcolab.client.members.pojo.LoginBean;
import org.xcolab.client.members.pojo.LoginLog;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.client.members.pojo.Role_;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class MembersClient {

    private static final RestService memberService = new RestService(CoLabService.MEMBER);

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
    public static List<Member> listAllMembers(){
        return listMembers(null,null,null,true,0,Integer.MAX_VALUE);
    }
    public static List<Member> listMembers(String categoryFilterValue, String screenNameFilterValue, String sortField,
                                          boolean ascOrder, int firstMember, int lastMember) {

        final ListQuery<Member> memberListQuery = memberResource.list()
                .addRange(firstMember, lastMember)
                .optionalQueryParam("partialName", screenNameFilterValue)
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
                            .withParameter("category", categoryFilterValue).asCount(), CacheRetention.SHORT)
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
                .withCache(CacheKeys.withClass(Role_.class)
                        .withParameter("memberId", memberId).asList(), CacheRetention.MEDIUM)
                .execute();
    }

    public static void assignMemberRole(long memberId, long roleId) {

         memberRoleResource.resolveParent(memberResource.id(memberId))
                .service("assignRoleToUser",Boolean.class)
                .queryParam("roleId",roleId)
                .put();
    }

    public static List<Role_> getMemberRolesInContest(long memberId, long contestId) {
        return memberRoleResource.resolveParent(memberResource.id(memberId))
                .list()
                .queryParam("contestId", contestId)
                .withCache(CacheKeys.withClass(Role_.class)
                                .withParameter("memberId", memberId)
                                .withParameter("contestId", contestId).asList(),
                        CacheRetention.SHORT)
                .execute();
    }

    public static MemberCategory getMemberCategory(long roleId) {
        try {
            return memberCategoryResource.get(roleId)
                    .withCache(CacheKeys.of(MemberCategory.class, roleId),
                            CacheRetention.MEDIUM).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new MemberCategoryNotFoundException("Category with role id " + roleId + " not found.");
        }
    }

    public static MemberCategory getMemberCategory(String displayName) {
        MemberCategory memberCategory = memberCategoryResource.list()
                .queryParam("displayName", displayName)
                .withCache(CacheKeys.withClass(MemberCategory.class)
                                .withParameter("displayName", displayName).asSingletonList("ifExists"),
                        CacheRetention.MEDIUM)
                .executeWithResult().getFirstIfExists();
        if (memberCategory == null) {
            throw new MemberCategoryNotFoundException("Category with name " + displayName + " not found.");
        }
        return memberCategory;
    }

    public static List<MemberCategory> getVisibleMemberCategories() {
        return memberCategoryResource.list()
                .queryParam("showInList", true)
                .execute();
    }

    public static Member getMember(long memberId) throws MemberNotFoundException {
        try {
            return memberResource.get(memberId)
                    .withCache(CacheKeys.of(Member.class, memberId),
                            CacheRetention.REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with id " + memberId + " not found.");
        }
    }

    public static Member getMemberUnchecked(long memberId) {
        try {
            return memberResource.get(memberId)
                    .withCache(CacheKeys.of(Member.class, memberId),
                    CacheRetention.REQUEST).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new UncheckedMemberNotFoundException(memberId);
        }
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

    public static Member findMemberByScreenNameNoRole(String screenName) throws MemberNotFoundException {
        try {
            return memberResource.service("findByScreenName", Member.class)
                    .queryParam("screenName", screenName).getChecked();
        }catch (EntityNotFoundException ignored){
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

    public static Member findMemberByOpenId(String openId) throws MemberNotFoundException {
        Member member = memberResource.list()
                .queryParam("openId", openId)
                .executeWithResult().getFirstIfExists();
        if (member == null) {
            throw new MemberNotFoundException("Member with openId " + openId + " does not exist");
        }
        return member;
    }

    public static boolean updateMember(Member member) {
        return memberResource.update(member, member.getId_())
                .cacheKey(CacheKeys.of(Member.class, member.getId_()))
                .execute();
    }

    public static boolean deleteMember(long memberId) {
        return memberResource.delete(memberId).execute();
    }

    public static Contact_ getContact(Long contactId) {
        return contactResource.get(contactId).execute();
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
        return memberResource.service("updateForgottenPassword", Long.class)
                .queryParam("forgotPasswordToken", forgotPasswordToken)
                .queryParam("password", password)
                .post();
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

    //TODO: remove, needed for liferay
    public static String hashPassword(String password) {
        return memberResource.service("hashPassword", String.class)
                .queryParam("password", password)
                .queryParam("liferayCompatible", true)
                .get();
    }

    public static boolean validatePassword(String password, long memberId) {
        return memberResource.service("validatePassword", Boolean.class)
                .queryParam("password", password)
                .queryParam("memberId", memberId)
                .get();
    }

    public static boolean updatePassword(long memberId, String oldPassword, String newPassword) {
        return memberResource.service(memberId, "updatePassword", Boolean.class)
                .queryParam("oldPassword", oldPassword)
                .queryParam("newPassword", newPassword)
                .post();
    }

    public static Member register(Member member) {
        return memberResource.create(member).execute();
    }

    public static boolean login(long memberId, String password, String remoteIp, String redirectUrl) {
        LoginBean loginBean = new LoginBean();
        loginBean.setPassword(password);
        loginBean.setIpAddress(remoteIp);
        loginBean.setRedirectUrl(redirectUrl);
        return memberResource.service(memberId, "login", Boolean.class)
                .post(loginBean);
    }

    //TODO: this shouldn't be done manually
    public static LoginLog createLoginLog(long memberId, String ipAddress, String redirectUrl) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(memberId);
        loginLog.setIpAddress(ipAddress);
        loginLog.setEntryUrl(redirectUrl.substring(0,Math.min(250, redirectUrl.length())));

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
    public static boolean isUserInGroup(Long memberId, Long groupId){
        return memberResource.service(memberId, "isMemberInGroup",Boolean.class)
                .queryParam("groupId", groupId)
                .get();
    }
    public static void createUserGroupRole(Long memberId, Long groupId){
         memberResource.service(memberId, "addMemberToGroup",Boolean.class)
                .queryParam("groupId", groupId)
                .get();
    }
}
