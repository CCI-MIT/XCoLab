package org.xcolab.client.sharedcolab;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.sharedcolab.exceptions.MemberNotFoundException;
import org.xcolab.client.sharedcolab.pojo.Member;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.ServiceResource;

public class SharedColabClient {

    private static final RestService sharedColabService = new RefreshingRestService("sharedcolab-service",
            ConfigurationAttributeKey.SHARED_COLAB_LOCATION,
            ConfigurationAttributeKey.SHARED_COLAB_PORT);

    private static final RestService partnerMemberService = new RefreshingRestService("members-service",
            ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
            ConfigurationAttributeKey.PARTNER_COLAB_PORT);

    private static final RestResource<Member> partnerMemberResource = new RestResource<>(
            partnerMemberService, "members", Member.TYPES);

    private static final ServiceResource sharedColabResource = new ServiceResource(
            sharedColabService, "members");

    public static boolean isScreenNameUsed(String screenName) {
        return sharedColabResource.service("isUsed", Boolean.class)
                .queryParam("screenName", screenName)
                .get();
    }

    public static boolean isEmailUsed(String email) {
        return sharedColabResource.service("isUsed", Boolean.class)
                .queryParam("email", email)
                .get();
    }

    public static Long retrieveSharedId(String email, String screenName, String colabName) {
        return sharedColabResource.service("retrieveSharedId", Long.class)
                .queryParam("email", email)
                .queryParam("screenName", screenName)
                .queryParam("colabOrigin", colabName)
                .post();
    }

    public static Member findMemberByScreenName(String screenName) throws MemberNotFoundException {
        final Member member = partnerMemberResource.list()
                .queryParam("screenName", screenName)
                .executeWithResult()
                .getFirstIfExists();
        if (member == null) {
            throw new MemberNotFoundException(
                    "Member with screenName " + screenName + " does not exist");
        }
        return member;
    }

    public static Member registerInPartnerColab(Member member) {
        return partnerMemberResource.service("registerFromSharedColab", Member.class)
                .post(member);
    }

    public static boolean validatePassword(String password, long memberId) {
        return partnerMemberResource.service("validatePassword", Boolean.class)
                .queryParam("password", password)
                .queryParam("memberId", memberId)
                .get();
    }
}
