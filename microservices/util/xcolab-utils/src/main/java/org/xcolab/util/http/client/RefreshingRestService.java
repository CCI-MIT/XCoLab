package org.xcolab.util.http.client;

import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.http.UriProvider;

public class RefreshingRestService extends RestService {

    private final AttributeGetter<String> hostNameAttribute;
    private final AttributeGetter<String> portAttribute;

    public RefreshingRestService(String serviceName,
            AttributeGetter<String> hostNameAttribute, AttributeGetter<String> portAttribute) {
        super(serviceName);
        this.hostNameAttribute = hostNameAttribute;
        this.portAttribute = portAttribute;
    }

    @Override
    public UriProvider getBaseUrl() {
        return getBaseUrl(hostNameAttribute.get(), portAttribute.get());
    }
}
