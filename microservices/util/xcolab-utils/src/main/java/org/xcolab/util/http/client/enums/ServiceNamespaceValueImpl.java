package org.xcolab.util.http.client.enums;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

final class ServiceNamespaceValueImpl implements ServiceNamespace {

    private static final Map<String, ServiceNamespaceValueImpl> INSTANCES =
            new ConcurrentHashMap<>();

    private final String namespace;

    private ServiceNamespaceValueImpl(String namespace) {
        this.namespace = namespace;
    }

    static ServiceNamespaceValueImpl of(String namespace) {
        if (StringUtils.isEmpty(namespace)) {
            throw new IllegalArgumentException("Namespace cannot be empty.");
        }
        return INSTANCES.computeIfAbsent(namespace, ServiceNamespaceValueImpl::new);
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public Boolean isMutable() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceNamespaceValueImpl)) {
            return false;
        }
        ServiceNamespaceValueImpl that = (ServiceNamespaceValueImpl) o;
        return Objects.equals(getNamespace(), that.getNamespace());
    }

    @Override
    public int hashCode() {
        return namespace.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("namespace", namespace).toString();
    }
}
