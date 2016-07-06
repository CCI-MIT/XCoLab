package org.xcolab.client.members;

import org.xcolab.client.members.exceptions.MemberCategoryNotFoundException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Contact_;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.client.members.pojo.Role_;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.queries.ListQuery;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class MembersClient {

    private static final RestService memberService = new RestService("members-service");

    private static final RestResource<Member> memberResource = new RestResource<>(memberService,
            "members", Member.TYPES);
    private static final RestResource<MemberCategory> memberCategoryResource =
            new RestResource<>(memberService, "membercategories", MemberCategory.TYPES);
    private static final RestResource<Contact_> contactResource = new RestResource<>(memberService,
            "contacts", Contact_.TYPES);

    private MembersClient() {
    }

    public static List<Member> findMembersMatching(String partialName, int maxMembers) {
        return memberResource.list()
                .addRange(0, maxMembers)
                .queryParam("partialName", partialName)
                .queryParam("sort", "screenName")
                .execute();
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
            return memberResource.service("count", Integer.class)
                    .optionalQueryParam("screenName", screenNameFilterValue)
                    .optionalQueryParam("category", categoryFilterValue)
                    .cacheIdentifier("members_count_category_" + categoryFilterValue
                            + "_screenName_" + screenNameFilterValue)
                    .get();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer getMemberActivityCount(long memberId) {
        try {
            return memberResource.service(memberId, "activityCount", Integer.class).get();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer getMemberMaterializedPoints(long memberId) {
        try {
            return memberResource.service(memberId, "materializedPoints", Integer.class).get();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static List<Role_> getMemberRoles(long memberId) {
        return memberResource.getSubRestResource(memberId, "roles", Role_.TYPES)
                .list()
                .cacheIdentifier("memberId_" + memberId)
                .execute();
    }

    public static List<Role_> getMemberRolesInContest(long memberId, long contestId) {
        return memberResource
                .getSubRestResource(memberId, "contestRoles", Role_.TYPES)
                .list().queryParam("contestId", contestId)
                .cacheIdentifier("memberId_" + memberId + "_contestId_" + contestId)
                .execute();
    }

    public static MemberCategory getMemberCategory(long roleId) {
        try {
            return memberCategoryResource.get(roleId).cacheIdentifier("roleId_" + roleId).execute();
        } catch (EntityNotFoundException e) {
            throw new MemberCategoryNotFoundException("Category with role id " + roleId + " not found.");
        }
    }

    public static MemberCategory getMemberCategory(String displayName) {
        MemberCategory memberCategory = memberCategoryResource.list()
                .queryParam("displayName", displayName)
                .cacheIdentifier("displayName_" + displayName)
                .executeWithResult().getFirstIfExists();
        if (memberCategory == null) {
            throw new MemberCategoryNotFoundException("Category with name " + displayName + " not found.");
        }
        return memberCategory;
    }

    public static Member getMember(long memberId) throws MemberNotFoundException {
        try {
            return memberResource.get(memberId).cacheIdentifier("memberId_" + memberId).execute();
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with id " + memberId + " not found.");
        }
    }

    public static Member getMemberUnchecked(long memberId) {
        try {
            return memberResource.get(memberId).cacheIdentifier("memberId_" + memberId).execute();
        } catch (EntityNotFoundException e) {
            throw new IllegalStateException("Member not found: " + memberId, e);
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
        //TODO: improve cache naming
        final String cacheKey = "_" + Member.class.getSimpleName() + "_memberId_" + member.getId_();
        return memberResource.update(member, member.getId_()).cacheIdentifier(cacheKey).execute();
    }

    public static boolean deleteMember(long memberId) {
        return memberResource.delete(memberId).execute();
    }

    public static Contact_ getContact(Long contactId) {
        return contactResource.get(contactId).executeUnchecked();
    }

    public static boolean isScreenNameUsed(String screenName) {
        return memberResource.service("isUsed", Boolean.class)
                .queryParam("screenName", screenName)
                .getUnchecked();
    }

    public static boolean isEmailUsed(String email) {
        return memberResource.service("isUsed", Boolean.class)
                .queryParam("email", email)
                .getUnchecked();
    }

    public static Long updateUserPassword(String forgotPasswordToken, String password) {
        return memberResource.service("updateForgottenPassword", Long.class)
                .queryParam("forgotPasswordToken", forgotPasswordToken)
                .queryParam("password", password)
                .post();
    }

    public static boolean isForgotPasswordTokenValid(String passwordToken) {
        return memberResource.service("validateForgotPasswordToken", Boolean.class)
                .queryParam("passwordToken", passwordToken)
                .getUnchecked();
    }

    public static String createForgotPasswordToken(Long memberId) {
        return memberResource.service("createForgotPasswordToken", String.class)
                .queryParam("memberId", memberId)
                //TODO: this should be posted!
                .getUnchecked();
    }

    public static String generateScreenName(String lastName, String firstName) {
        return memberResource.service("generateScreenName", String.class)
                .queryParam("values", firstName, lastName)
                .getUnchecked();
    }

    public static String hashPassword(String password) {
        return hashPassword(password, false);
    }

    public static String hashPassword(String password, boolean liferayCompatible) {
        return memberResource.service("hashPassword", String.class)
                .queryParam("password", password)
                .queryParam("liferayCompatible", liferayCompatible)
                .getUnchecked();
    }

    // /members/createForgotPasswordToken

    public static boolean validatePassword(String password, long memberId) {
        return memberResource.service("validatePassword", Boolean.class)
                .queryParam("password", password)
                .queryParam("memberId", memberId)
                .getUnchecked();
    }

    public static Member register(Member member) {
        return memberResource.create(member).execute();
    }

    public static boolean login(long memberId, String password) {
        return memberResource.service(memberId, "login", Boolean.class).post();
    }

    public static boolean subscribeToNewsletter(long memberId) {
        return memberResource.service(memberId, "subscribe", Boolean.class).put();
    }

    public static boolean unsubscribeFromNewsletter(long memberId) {
        return memberResource.service(memberId, "unsubscribe", Boolean.class).put();
    }

    public static boolean isSubscribedToNewsletter(long memberId) {
        return memberResource.service(memberId, "isSubscribed", Boolean.class).getUnchecked();
    }
}
