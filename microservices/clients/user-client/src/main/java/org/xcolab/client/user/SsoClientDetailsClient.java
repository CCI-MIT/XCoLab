package org.xcolab.client.user;

import org.xcolab.client.user.pojo.SsoClientDetails;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;

public class SsoClientDetailsClient {

    private static final RestResource<SsoClientDetails, String> ssoClientDetailsResource =
            new RestResource1<>(UserResource.SSO_CLIENT_DETAILS, SsoClientDetails.TYPES);

    public static SsoClientDetails getSsoClientDetails(String clientId) {
        return ssoClientDetailsResource.get(clientId).execute();
    }
}
