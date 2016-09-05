package org.xcolab.client.sharedcolab;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.sharedcolab.exceptions.MemberNotFoundException;
import org.xcolab.client.sharedcolab.pojo.Member;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.queries.ServiceQuery;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;


public class SharedColabClient {

    private static final RestService sharedColabService = new RestService("sharedcolab-service");

    private static final RestService memberService = new RestService("members-service");

    private static final RestResource<Member> memberResource = new RestResource<>(memberService,
            "members", Member.TYPES);

    private static final RestResource<Object> sharedColabResource = new RestResource<>(sharedColabService,
            "members");

    public static boolean isScreenNameUsed(String screenName) {
        String location = ConfigurationAttributeKey.SHARED_COLAB_LOCATION.get();
        String port = ConfigurationAttributeKey.SHARED_COLAB_PORT.get();
        sharedColabService.setServiceHost(location);
        sharedColabService.setServicePort(port);
        return sharedColabResource.service("isUsed",Boolean.class)
                .queryParam("screenName", screenName).get();
    }

    public static boolean isEmailUsed(String email) {
        String location = ConfigurationAttributeKey.SHARED_COLAB_LOCATION.get();
        String port = ConfigurationAttributeKey.SHARED_COLAB_PORT.get();
        sharedColabService.setServiceHost(location);
        sharedColabService.setServicePort(port);
        return sharedColabResource.service("isUsed", Boolean.class)
                .queryParam("email", email)
                .get();
    }

    public static Long retrieveSharedId(String email, String screenName, String colabName) {
        sharedColabService.setServiceHost(ConfigurationAttributeKey.SHARED_COLAB_LOCATION.get());
        sharedColabService.setServicePort(ConfigurationAttributeKey.SHARED_COLAB_PORT.get());
        ServiceQuery<Long, Long> retrieveSharedId = sharedColabResource.service("retrieveSharedId", Long.class);

        if (email != null) {
            retrieveSharedId.optionalQueryParam("email", email);
        }
        if (screenName != null) {
            retrieveSharedId.optionalQueryParam("screenName", screenName);
        }

        retrieveSharedId.optionalQueryParam("colabOrigin", colabName);

        return retrieveSharedId.post();
    }

    public static Member findMemberByScreenName(String screenName) throws MemberNotFoundException {
        memberService.setServiceHost(ConfigurationAttributeKey.PARTNER_COLAB_LOCATION.get());
        memberService.setServicePort(ConfigurationAttributeKey.PARTNER_COLAB_PORT.get());
        //TODO: port to new methods
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

    public static Member registerInPartnerColab(Member member) {
        memberService.setServiceHost(ConfigurationAttributeKey.PARTNER_COLAB_LOCATION.get());
        memberService.setServicePort(ConfigurationAttributeKey.PARTNER_COLAB_PORT.get());
        return memberResource.service("registerFromSharedColab",Member.class).post(member);
    }


    public static boolean validatePassword(String password, long memberId) {
        memberService.setServiceHost(ConfigurationAttributeKey.PARTNER_COLAB_LOCATION.get());
        memberService.setServicePort(ConfigurationAttributeKey.PARTNER_COLAB_PORT.get());
        return memberResource.service("validatePassword", Boolean.class)
                .queryParam("password", password)
                .queryParam("memberId", memberId)
                .get();
    }

}
