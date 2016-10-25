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

    @Override
    public RestService withServiceName(String serviceName) {
        return new RefreshingRestService(serviceName, hostNameAttribute, portAttribute);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + hostNameAttribute.hashCode();
        result = 31 * result + portAttribute.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RefreshingRestService)) {
            return false;
        }
        RefreshingRestService other = (RefreshingRestService) obj;

        return super.equals(obj)
                && hostNameAttribute.equals(other.hostNameAttribute)
                && portAttribute.equals(other.portAttribute);
    }
}
