package org.xcolab.client.members;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.members.exceptions.MemberCategoryNotFoundException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Contact_;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.client.members.pojo.Role_;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

import java.util.List;

public final class MembersClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/members-service";

    private MembersClient() {
    }

    public static List<Member> listMembers(String categoryFilterValue, String screenNameFilterValue, String sortField,
                                          boolean ascOrder, int firstMember, int lastMember) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members")
                        .queryParam("startRecord", firstMember)
                        .queryParam("limitRecord", lastMember);

        if (sortField != null && !sortField.isEmpty()) {
            final String prefix = (ascOrder) ? ("") : ("-");
            switch (sortField) {
                case "USER_NAME": uriBuilder.queryParam("sort", prefix + "screenName");
                    break;
                case "MEMBER_SINCE": uriBuilder.queryParam("sort", prefix + "createDate");
                    break;
                case "CATEGORY": uriBuilder.queryParam("sort", prefix + "roleName");
                    break;
                case "ACTIVITY": uriBuilder.queryParam("sort", prefix + "activityCount");
                    break;
                case "POINTS": uriBuilder.queryParam("sort", prefix + "points");
                    break;
                default:
            }

        }
        if (screenNameFilterValue != null && !screenNameFilterValue.isEmpty()) {
            uriBuilder.queryParam("partialName", screenNameFilterValue);
        }
        if (categoryFilterValue != null && !categoryFilterValue.isEmpty()) {
            uriBuilder.queryParam("roleName", categoryFilterValue);
        } else {
            uriBuilder.queryParam("roleName", "Member");
        }

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Member>>() {
        });
    }

    public static Integer countMembers(String categoryFilterValue, String screenNameFilterValue) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members/count");
        if (screenNameFilterValue != null) {
            uriBuilder.queryParam("screenName", screenNameFilterValue);
        }
        if (categoryFilterValue != null) {
            uriBuilder.queryParam("category", categoryFilterValue);
        }
        try {
            return RequestUtils.get(uriBuilder, Integer.class,
                    "members_count_category_" + categoryFilterValue
                            + "_screenName_" + screenNameFilterValue);
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer getMemberActivityCount(Long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/activityCount");
        try {
            return RequestUtils.get(uriBuilder, Integer.class);
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer getMemberMaterializedPoints(Long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/materializedPoints");
        try {
            return RequestUtils.get(uriBuilder, Integer.class);
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static List<Role_> getMemberRoles(Long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/roles");

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Role_>>() {
        }, "memberId_" + memberId);
    }

    public static List<Role_> getMemberRolesInContest(Long memberId, Long contestId) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/roles/contests/" + contestId);
        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Role_>>() {
        }, "memberId_" + memberId + "_contestId_" + contestId);
    }


    public static MemberCategory getMemberCategory(Long roleId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/membercategories/" + roleId + "");

        try {
            return RequestUtils.get(uriBuilder, MemberCategory.class, "roleId_" + roleId);
        } catch (EntityNotFoundException e) {
            throw new MemberCategoryNotFoundException("Cateogry with role id " + roleId + " not found.");
        }
    }

    public static Member getMember(Long memberId) throws MemberNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "");
        try {
            return RequestUtils.get(uriBuilder, Member.class, "memberId_" + memberId);
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with id " + memberId + " not found.");
        }
    }

    public static Member findMemberByEmailAddress(String emailAddress) throws MemberNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members")
                .queryParam("email", emailAddress);
        try {
            return RequestUtils.getFirstFromList(uriBuilder,
                    new ParameterizedTypeReference<List<Member>>() {
                    });
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with email " + emailAddress + " does not exist");
        }
    }

    public static Member findMemberByScreenName(String screenName) throws MemberNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members")
                .queryParam("screenName", screenName);
        try {
            return RequestUtils.getFirstFromList(uriBuilder,
                    new ParameterizedTypeReference<List<Member>>() {
                    });
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with screenName " + screenName + " does not exist");
        }
    }

    public static Member findMemberByFacebookId(long facebookId) throws MemberNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members")
                .queryParam("facebookId", facebookId);
        try {
            return RequestUtils.getFirstFromList(uriBuilder,
                    new ParameterizedTypeReference<List<Member>>() {
                    });
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with facebookId " + facebookId + " does not exist");
        }
    }

    public static Member findMemberByOpenId(String openId) throws MemberNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members")
                .queryParam("openId", openId);
        try {
            return RequestUtils.getFirstFromList(uriBuilder,
                    new ParameterizedTypeReference<List<Member>>() {
                    });
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with openId " + openId + " does not exist");
        }
    }

    public static void updateMember(Member member) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + member.getId_() + "");

        RequestUtils.put(uriBuilder, member);
    }

    public static Contact_ getContact(Long contactId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contact/" + contactId + "");
        return RequestUtils.getUnchecked(uriBuilder, Contact_.class);
    }

    public static boolean isScreenNameUsed(String screenName) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/isUsed")
                    .queryParam("screenName", screenName);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static boolean isEmailUsed(String email) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/isUsed")
                    .queryParam("email", email);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static String generateScreenName(String lastName, String firstName) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/generateScreenName")
                    .queryParam("values", firstName, lastName);
        return RequestUtils.getUnchecked(uriBuilder, String.class);
    }

    public static String hashPassword(String password) {
        return hashPassword(password, false);
    }

    public static String hashPassword(String password, boolean liferayCompatible) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/hashPassword")
                .queryParam("password", password)
                .queryParam("liferayCompatible", liferayCompatible);
        return RequestUtils.getUnchecked(uriBuilder, String.class);
    }

    // /members/createForgotPasswordToken

    public static boolean validatePassword(String password, long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/validatePassword")
                .queryParam("password", password)
                .queryParam("memberId", memberId);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static Member register(Member member) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members");
        return RequestUtils.post(uriBuilder, member, Member.class);
    }

    public static boolean login(long memberId, String password) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/login");
        return RequestUtils.post(uriBuilder, password, Boolean.class);
    }

    public static boolean subscribeToNewsletter(long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/subscribe");
        return RequestUtils.put(uriBuilder, null);
    }

    public static boolean unsubscribeFromNewsletter(long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/unsubscribe");
        return RequestUtils.put(uriBuilder, null);
    }

    public static boolean isSubscribedToNewsletter(long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/isSubscribed");
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }
}
