package org.xcolab.client.flagging;

import org.xcolab.util.RequestUtils;

public final class FlaggingClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:"+RequestUtils.getServicesPort()+"/flagging-service";

    private FlaggingClient() {
    }

}
