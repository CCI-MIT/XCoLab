package org.xcolab.client.members;

import net.spy.memcached.MemcachedClient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.members.pojo.Contact_;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.client.members.pojo.Role_;
import org.xcolab.client.members.pojo.User_;

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

    static RestTemplate restTemplate = new RestTemplate();

    private MembersClient() {
    }


    public static List<User_> listMembers(String categoryFilterValue, String screenNameFilterValue, String sortField,
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

        ResponseEntity<List<User_>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User_>>() {
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
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/roles");

        ResponseEntity<List<Role_>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Role_>>() {
                });

        return response.getBody();
    }

    public static MemberCategory getMemberCategory(Long roleId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/membercategories/" + roleId + "");

        return restTemplate.getForObject(uriBuilder.build().toString(), MemberCategory.class);
    }

    public static User_ getMember(Long memberId) {
        User_ ret;
        if (memcached != null) {
            ret = (User_) memcached.get("member_" + memberId);
            if (ret != null) {
                return ret;
            }
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "");
        ret = restTemplate.getForObject(uriBuilder.build().toString(), User_.class);

        if (memcached != null) {
            memcached.add("member_" + memberId, MEMCACHED_TIMEOUT, ret);
        }
        return ret;
    }

    public static void updateMember(User_ user) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + user.getUserId() + "");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User_> entity = new HttpEntity(user, headers);


        restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.POST, entity,
                String.class);

    }

    public static Contact_ getContact(Long contactId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contact/" + contactId + "");
        return restTemplate.getForObject(uriBuilder.build().toString(), Contact_.class);
    }
}
