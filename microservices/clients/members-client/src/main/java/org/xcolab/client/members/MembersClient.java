package org.xcolab.client.members;

import net.spy.memcached.MemcachedClient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Contact_;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.client.members.pojo.Role_;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

public final class MembersClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/members-service";

    private static final int MEMCACHED_TIMEOUT = 3;

    private static MemcachedClient memcached;

    static {
        try {
            memcached = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
            ;
        } catch (IOException ignored) {
            memcached = null;
        }
    }

    private static final RestTemplate restTemplate = new RestTemplate();

    private MembersClient() {
    }

    public static List<Member> listMembers(String categoryFilterValue, String screenNameFilterValue, String sortField,
                                          boolean ascOrder, int firstUser, int lastUser) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members")
                        .queryParam("firstRecord", firstUser)
                        .queryParam("lastRecord", lastUser);

        if (sortField != null && !sortField.isEmpty()) {
            uriBuilder.queryParam("sort", ((ascOrder) ? ("") : ("-")) + sortField);
        }
        if (screenNameFilterValue != null && !screenNameFilterValue.isEmpty()) {
            uriBuilder.queryParam("screenName", screenNameFilterValue);
        }
        if (categoryFilterValue != null && !categoryFilterValue.isEmpty()) {
            uriBuilder.queryParam("category", categoryFilterValue);
        }

        ResponseEntity<List<Member>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Member>>() {
                });

        return response.getBody();
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
        return restTemplate.getForObject(uriBuilder.build().toString(), Integer.class);
    }

    public static Integer getMemberActivityCount(Long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/activityCount");
        return restTemplate.getForObject(uriBuilder.build().toString(), Integer.class);
    }

    public static Integer getMemberMaterializedPoints(Long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/materializedPoints");
        return restTemplate.getForObject(uriBuilder.build().toString(), Integer.class);
    }

    public static List<Role_> getMemberRoles(Long memberId) {

        List<Role_> ret;
        if (memcached != null) {
            ret = (List<Role_>) memcached.get("member_roles_" + memberId);
            if (ret != null) {
                return ret;
            }
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/roles");
        ResponseEntity<List<Role_>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Role_>>() {
                });
        ret = response.getBody();

        if (memcached != null) {
            memcached.add("member_roles_" + memberId, MEMCACHED_TIMEOUT, ret);
        }

        return ret;
    }

    public static List<Role_> getMemberRolesInContest(Long memberId, Long contestId) {

        List<Role_> ret;
        if (memcached != null) {
            ret = (List<Role_>) memcached.get("member_roles_contest_" + memberId + "_" + contestId);
            if (ret != null) {
                return ret;
            }
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/roles/contests/" + contestId);
        ResponseEntity<List<Role_>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Role_>>() {
                });
        ret = response.getBody();

        if (memcached != null) {
            memcached.add("member_roles_contest_" + memberId + "_" + contestId, MEMCACHED_TIMEOUT, ret);
        }

        return ret;
    }


    public static MemberCategory getMemberCategory(Long roleId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/membercategories/" + roleId + "");

        return restTemplate.getForObject(uriBuilder.build().toString(), MemberCategory.class);
    }

    public static Member getMember(Long memberId) {
        Member ret;
        if (memcached != null) {
            ret = (Member) memcached.get("member_" + memberId);
            if (ret != null) {
                return ret;
            }
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "");
        ret = restTemplate.getForObject(uriBuilder.build().toString(), Member.class);

        if (memcached != null) {
            memcached.add("member_" + memberId, MEMCACHED_TIMEOUT, ret);
        }
        return ret;
    }

    public static Member findMemberByEmailAddress(String emailAddress) throws MemberNotFoundException {
        try {
            //TODO: add API call
            return null;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new MemberNotFoundException("Member with email " + emailAddress + " does not exist");
            }
            throw e;
        }
    }

    public static Member findMemberByScreenName(String screenName) throws MemberNotFoundException {
        try {
            //TODO: add API call
            return null;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new MemberNotFoundException("Member with screenName " + screenName + " does not exist");
            }
            throw e;
        }
    }

    public static void updateMember(Member member) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + member.getId_() + "");

        restTemplate.postForObject(uriBuilder.build().toString(),
                member, String.class);
    }

    public static Contact_ getContact(Long contactId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contact/" + contactId + "");
        return restTemplate.getForObject(uriBuilder.build().toString(), Contact_.class);
    }

    public static boolean isScreenNameUsed(String screenName) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/isUsed")
                    .queryParam("screenName", screenName);
        return restTemplate.getForObject(uriBuilder.build().toString(), Boolean.class);
    }

    public static boolean isEmailUsed(String email) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/isUsed")
                    .queryParam("email", email);
        return restTemplate.getForObject(uriBuilder.build().toString(), Boolean.class);
    }

    public static String generateScreenName(String lastName, String firstName) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/generateScreenName")
                    .queryParam("values", lastName, firstName);
        return restTemplate.getForObject(uriBuilder.build().toString(), String.class);
    }

    public static String hashPassword(String password) {
        return hashPassword(password, false);
    }

    public static String hashPassword(String password, boolean liferayCompatible) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/hashPassword")
                .queryParam("password", password)
                .queryParam("liferayCompatible", liferayCompatible);
        return restTemplate.getForObject(uriBuilder.build().toString(), String.class);
    }

    public static boolean validatePassword(String password, long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/validatePassword")
                .queryParam("password", password)
                .queryParam("memberId", memberId);
        return restTemplate.getForObject(uriBuilder.build().toString(), Boolean.class);
    }

    public static Member register(Member member) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members");
        return restTemplate.postForObject(uriBuilder.build().toString(), member, Member.class);
    }

    public static boolean login(long memberId, String password) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/login");
        return restTemplate.postForObject(uriBuilder.build().toString(), password, Boolean.class);
    }

    public static boolean subscribeToNewsletter(long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/subscribe");
        return restTemplate.exchange(uriBuilder.build().toString(), HttpMethod.PUT, null, Boolean.class).getBody();
    }

    public static boolean unsubscribeFromNewsletter(long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/unsubscribe");
        return restTemplate.exchange(uriBuilder.build().toString(), HttpMethod.PUT, null, Boolean.class).getBody();
    }

    public static boolean isSubscribedToNewsletter(long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/isSubscribed");
        return restTemplate.getForObject(uriBuilder.build().toString(), Boolean.class);
    }
}
