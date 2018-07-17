package org.xcolab.client.sharedcolab;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.sharedcolab.pojo.SharedContest;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;

public class SharedColabClient {

    //TODO COLAB-1857: the naming in this client is very confusing
    private static final ServiceNamespace PARTNER_COLAB_NAMESPACE = ServiceNamespace
            .instance(ConfigurationAttributeKey.SHARED_COLAB_NAMESPACE
                    .withDefaultValue(ServiceRequestUtils.getNamespace()));

    private static final RestResource<SharedContest, Long> sharedContestResource =
            new RestResource1<>(SharedColabResource.CONTESTS, SharedContest.TYPES,
                    PARTNER_COLAB_NAMESPACE);


    public static void updateSharedContestName(Long sharedContestId, String sharedContestName) {
        sharedContestResource.elementService(sharedContestId,"updateSharedContestName", Boolean.class)
                .queryParam("sharedContestName", sharedContestName)
                .put();
    }

    public static Long retrieveContestSharedId(String sharedContestName, String colabName) {
        return sharedContestResource.collectionService("retrieveSharedId", Long.class)
                .queryParam("sharedContestName", sharedContestName)
                .queryParam("colabOrigin", colabName)
                .post();
    }
}
