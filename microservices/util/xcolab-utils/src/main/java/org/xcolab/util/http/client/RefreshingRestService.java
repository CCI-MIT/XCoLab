package org.xcolab.util.http.client;

import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.UriProvider;

public class RefreshingRestService extends RestService {

    private final AttributeGetter<String> namespaceAttributeGetter;

    public RefreshingRestService(CoLabService serviceName,
            AttributeGetter<String> namespaceAttributeGetter) {
        this(serviceName.getServiceName(), namespaceAttributeGetter);
    }

    public RefreshingRestService(String serviceName,
            AttributeGetter<String> namespaceAttributeGetter) {
        super(serviceName, namespaceAttributeGetter.get());
        this.namespaceAttributeGetter = namespaceAttributeGetter;
    }

    @Override
    public UriProvider getBaseUrl() {
        return getBaseUrl(namespaceAttributeGetter.get());
    }

    @Override
    public RestService withServiceName(String serviceName) {
        return new RefreshingRestService(serviceName, namespaceAttributeGetter);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + namespaceAttributeGetter.hashCode();
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
                && namespaceAttributeGetter.equals(other.namespaceAttributeGetter);
    }
}
