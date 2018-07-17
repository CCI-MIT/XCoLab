package org.xcolab.util.http.client.enums;

import org.xcolab.commons.attributes.AttributeGetter;
import org.xcolab.util.http.ServiceRequestUtils;

/**
 * Represents a service namespace.
 *
 * Service namespaces are used to allow services of several xCoLabs to communicate with each other
 * for data sharing.
 */
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
