package org.xcolab.client.sharedcolab;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.sharedcolab.exceptions.MemberNotFoundException;
import org.xcolab.client.sharedcolab.pojo.Member;
import org.xcolab.client.sharedcolab.pojo.SharedContest;
import org.xcolab.client.sharedcolab.pojo.SharedMember;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.ServiceResource;
import org.xcolab.util.http.client.ServiceResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;

public class SharedColabClient {

    //TODO COLAB-1857: the naming in this client is very confusing

    private static final ServiceNamespace PARTNER_COLAB_NAMESPACE = ServiceNamespace
            .instance(ConfigurationAttributeKey.SHARED_COLAB_NAMESPACE
                    .withDefaultValue(ServiceRequestUtils.getNamespace()));
    private static final ServiceNamespace PARTNER_MEMBER_SERVICE = ServiceNamespace
            .instance(ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE);


    private static final RestResource<Member, Long> partnerMemberResource = new RestResource1<>(
            SharedUserResource.USERS, Member.TYPES, PARTNER_MEMBER_SERVICE);

    //TODO COLAB-1857: this doesn't look right - it's the same endpoint as above
    private static final RestResource<SharedMember, Long> sharedMemberResource =
            new RestResource1<>(SharedUserResource.USERS, SharedMember.TYPES,
                    PARTNER_MEMBER_SERVICE);

    private static final RestResource<SharedContest, Long> sharedContestResource =
            new RestResource1<>(SharedColabResource.CONTESTS, SharedContest.TYPES,
                    PARTNER_COLAB_NAMESPACE);

    private static final ServiceResource sharedColabResource = new ServiceResource1(
            SharedColabResource.USERS, PARTNER_COLAB_NAMESPACE);


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

    public static SharedMember findSharedMemberByEmail(String email) {
        return sharedMemberResource.list()
                .queryParam("email", email)
                .executeWithResult()
                .getOneIfExists();
    }

    public static SharedMember findSharedMemberByScreenName(String screenName) {
        return sharedMemberResource.list()
                .queryParam("screenName", screenName)
                .executeWithResult()
                .getOneIfExists();
    }


    public static void updateSharedContestName(Long sharedContestId, String sharedContestName) {
        sharedContestResource.service(sharedContestId,"updateSharedContestName", Boolean.class)
                .queryParam("sharedContestName", sharedContestName)
                .put();
    }

    public static Long retrieveContestSharedId(String sharedContestName, String colabName) {
        return sharedContestResource.service("retrieveSharedId", Long.class)
                .queryParam("sharedContestName", sharedContestName)
                .queryParam("colabOrigin", colabName)
                .post();
    }

    public static List<SharedContest> retrieveContestsFromForeignColab(String colabOrigin) {
        return sharedContestResource.list()
                .optionalQueryParam("colabOrigin", colabOrigin)
                .execute();
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
