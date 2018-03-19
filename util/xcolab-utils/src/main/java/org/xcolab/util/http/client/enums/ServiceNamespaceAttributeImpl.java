package org.xcolab.util.http.client.enums;

import org.apache.commons.lang3.builder.ToStringBuilder;

import org.xcolab.commons.attributes.AttributeGetter;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

final class ServiceNamespaceAttributeImpl implements ServiceNamespace {

    private static final Map<AttributeGetter, ServiceNamespaceAttributeImpl> INSTANCES =
            new ConcurrentHashMap<>();

    private final AttributeGetter<String> namespaceAttributeGetter;

    private ServiceNamespaceAttributeImpl(AttributeGetter<String> namespaceAttributeGetter) {
        this.namespaceAttributeGetter = namespaceAttributeGetter;
    }

    static ServiceNamespaceAttributeImpl of(AttributeGetter<String> namespaceAttributeGetter) {
        if (namespaceAttributeGetter == null) {
            throw new IllegalArgumentException("NamespaceGetter cannot be null.");
        }
        return INSTANCES.computeIfAbsent(namespaceAttributeGetter, ServiceNamespaceAttributeImpl::new);
    }

    @Override
    public String getNamespace() {
        return namespaceAttributeGetter.get();
    }

    @Override
    public Boolean isMutable() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceNamespaceAttributeImpl)) {
            return false;
        }
        ServiceNamespaceAttributeImpl that = (ServiceNamespaceAttributeImpl) o;
        return Objects.equals(namespaceAttributeGetter, that.namespaceAttributeGetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespaceAttributeGetter);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("namespaceAttributeGetter", namespaceAttributeGetter).toString();
    }
}
