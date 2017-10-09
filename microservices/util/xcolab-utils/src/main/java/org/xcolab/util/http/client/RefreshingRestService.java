package org.xcolab.util.http.client;

import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.http.UriProvider;

import java.util.Objects;

public class RefreshingRestService extends RestService {

    private final AttributeGetter<String> namespaceAttributeGetter;

    public RefreshingRestService(CoLabService serviceName,
            AttributeGetter<String> namespaceAttributeGetter) {
        this(serviceName.getServiceName(), namespaceAttributeGetter);
    }

    public RefreshingRestService(String serviceName,
            AttributeGetter<String> namespaceAttributeGetter) {
        super(serviceName, "placeholder-namespace");
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
        return Objects.hash(super.hashCode(), namespaceAttributeGetter);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RefreshingRestService)) {
            return false;
        }
        RefreshingRestService that = (RefreshingRestService) obj;
        return super.equals(obj) && Objects.equals(namespaceAttributeGetter, that.namespaceAttributeGetter);
    }
}
