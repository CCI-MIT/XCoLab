package org.xcolab.client.members;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.members.exceptions.MemberCategoryNotFoundException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Contact_;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.client.members.pojo.Role_;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class MembersClient {

    private static final RestService memberService = new RestService("members-service");
    private static final RestResource memberResource =
            new RestResource(memberService, "members");
    private static final RestResource memberCategoryResource =
            new RestResource(memberService, "membercategories");
    private static final RestResource contactResource =
            new RestResource(memberService, "contacts");

    private MembersClient() {
    }

    public static List<Member> findMembersMatching(String partialName, int maxMembers) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
                .addRange(0, maxMembers)
                .queryParam("partialName", partialName)
                .queryParam("sort", "screenName");
        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Member>>() {
        });
    }

    public static List<Member> listMembers(String categoryFilterValue, String screenNameFilterValue, String sortField,
                                          boolean ascOrder, int firstMember, int lastMember) {

        final UriBuilder uriBuilder = memberResource.getResourceUrl()
                .addRange(firstMember, lastMember)
                .optionalQueryParam("partialName", screenNameFilterValue)
                .optionalQueryParam("roleName", categoryFilterValue);

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

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Member>>() {
        });
    }

    public static Integer countMembers(String categoryFilterValue, String screenNameFilterValue) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl().path("/count")
                .optionalQueryParam("screenName", screenNameFilterValue)
                .optionalQueryParam("category", categoryFilterValue);
        try {
            return RequestUtils.get(uriBuilder, Integer.class,
                    "members_count_category_" + categoryFilterValue
                            + "_screenName_" + screenNameFilterValue);
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer getMemberActivityCount(long memberId) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl(memberId)
                .path("/activityCount");
        try {
            return RequestUtils.get(uriBuilder, Integer.class);
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer getMemberMaterializedPoints(long memberId) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl(memberId)
                .path("/materializedPoints");
        try {
            return RequestUtils.get(uriBuilder, Integer.class);
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static List<Role_> getMemberRoles(long memberId) {
        final UriBuilder uriBuilder =
                memberResource.getSubResource(memberId, "roles").getResourceUrl();

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Role_>>() {
        }, "memberId_" + memberId);
    }

    public static List<Role_> getMemberRolesInContest(long memberId, long contestId) {
        //TODO: make uri nicer
        final UriBuilder uriBuilder =
                memberResource.getSubResource(memberId, "roles").getResourceUrl()
                .path("/contests/" + contestId);
        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Role_>>() {
        }, "memberId_" + memberId + "_contestId_" + contestId);
    }

    public static MemberCategory getMemberCategory(long roleId) {
        final UriBuilder uriBuilder = memberCategoryResource.getResourceUrl(roleId);

        try {
            return RequestUtils.get(uriBuilder, MemberCategory.class, "roleId_" + roleId);
        } catch (EntityNotFoundException e) {
            throw new MemberCategoryNotFoundException("Category with role id " + roleId + " not found.");
        }
    }

    public static MemberCategory getMemberCategory(String displayName) {
        final UriBuilder uriBuilder = memberCategoryResource.getResourceUrl()
                .queryParam("displayName", displayName);

        try {
            return RequestUtils.getFirstFromList(uriBuilder,
                    new ParameterizedTypeReference<List<MemberCategory>>() {
                    }, "displayName_" + displayName);
        } catch (EntityNotFoundException e) {
            throw new MemberCategoryNotFoundException("Category with name " + displayName + " not found.");
        }
    }

    public static Member getMember(long memberId) throws MemberNotFoundException {
        final UriBuilder uriBuilder = memberResource.getResourceUrl(memberId);
        try {
            return RequestUtils.get(uriBuilder, Member.class, "memberId_" + memberId);
        } catch (EntityNotFoundException e) {
            throw new MemberNotFoundException("Member with id " + memberId + " not found.");
        }
    }

    public static Member findMemberByEmailAddress(String emailAddress) throws MemberNotFoundException {
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
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
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
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
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
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
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
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
        final UriBuilder uriBuilder = memberResource.getResourceUrl(member.getId_());

        final String cacheKey = "_" + Member.class.getSimpleName() + "_memberId_" + member.getId_();
        RequestUtils.put(uriBuilder, member, cacheKey);
    }

    public static void deleteMember(long memberId) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl(memberId);

        RequestUtils.delete(uriBuilder);
    }

    public static Contact_ getContact(Long contactId) {
        final UriBuilder uriBuilder = contactResource.getResourceUrl(contactId);
        return RequestUtils.getUnchecked(uriBuilder, Contact_.class);
    }

    public static boolean isScreenNameUsed(String screenName) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl().path("/isUsed")
                    .queryParam("screenName", screenName);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static boolean isEmailUsed(String email) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl().path("/isUsed")
                    .queryParam("email", email);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static Long updateUserPassword(String forgotPasswordToken, String password) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
                .path("/updateForgottenPassword")
                .queryParam("forgotPasswordToken", forgotPasswordToken)
                .queryParam("password", password);
        return RequestUtils.post(uriBuilder, null, Long.class);
    }

    public static boolean isForgotPasswordTokenValid(String passwordToken) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
                .path("/validateForgotPasswordToken")
                .queryParam("passwordToken", passwordToken);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static String createForgotPasswordToken(Long memberId) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
                .path("/createForgotPasswordToken")
                .queryParam("memberId", memberId );
        //TODO: this should be posted!
        return RequestUtils.getUnchecked(uriBuilder, String.class);
    }

    public static String generateScreenName(String lastName, String firstName) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
                .path("/generateScreenName")
                    .queryParam("values", firstName, lastName);
        return RequestUtils.getUnchecked(uriBuilder, String.class);
    }

    public static String hashPassword(String password) {
        return hashPassword(password, false);
    }

    public static String hashPassword(String password, boolean liferayCompatible) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
                .path("/hashPassword")
                .queryParam("password", password)
                .queryParam("liferayCompatible", liferayCompatible);
        return RequestUtils.getUnchecked(uriBuilder, String.class);
    }

    // /members/createForgotPasswordToken

    public static boolean validatePassword(String password, long memberId) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl()
                .path("/validatePassword")
                .queryParam("password", password)
                .queryParam("memberId", memberId);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static Member register(Member member) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, member, Member.class);
    }

    public static boolean login(long memberId, String password) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl(memberId)
                .path("/login");
        return RequestUtils.post(uriBuilder, password, Boolean.class);
    }

    public static boolean subscribeToNewsletter(long memberId) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl(memberId)
                .path("/subscribe");
        return RequestUtils.put(uriBuilder, null);
    }

    public static boolean unsubscribeFromNewsletter(long memberId) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl(memberId)
                .path("/unsubscribe");
        return RequestUtils.put(uriBuilder, null);
    }

    public static boolean isSubscribedToNewsletter(long memberId) {
        final UriBuilder uriBuilder = memberResource.getResourceUrl(memberId)
                .path("/isSubscribed");
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }
}
