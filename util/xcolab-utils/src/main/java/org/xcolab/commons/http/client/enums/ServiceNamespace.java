package org.xcolab.commons.http.client.enums;

import org.xcolab.commons.attributes.AttributeGetter;
import org.xcolab.commons.http.ServiceRequestUtils;

public interface ServiceNamespace {

    String getNamespace();

    Boolean isMutable();

    static ServiceNamespace instance() {
        return instance(ServiceRequestUtils.getNamespace());
    }

    static ServiceNamespace instance(String namespace) {
        return ServiceNamespaceValueImpl.of(namespace);
    }

    static ServiceNamespace instance(AttributeGetter<String> namespaceAttributeGetter) {
        return ServiceNamespaceAttributeImpl.of(namespaceAttributeGetter);
    }
}
