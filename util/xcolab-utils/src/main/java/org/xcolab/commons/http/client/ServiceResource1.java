package org.xcolab.commons.http.client;

import org.xcolab.commons.http.UriBuilder;
import org.xcolab.commons.http.client.enums.ResourceEnum;
import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.interfaces.AbstractServiceResource;

public class ServiceResource1 extends AbstractServiceResource {

    private final ResourceEnum resourceEnum;
    private final ServiceNamespace namespace;

    public ServiceResource1(ResourceEnum resourceEnum) {
        this(resourceEnum, ServiceNamespace.instance());
    }

    public ServiceResource1(ResourceEnum resourceEnum, ServiceNamespace namespace) {
        super(resourceEnum.getResourceName());
        this.resourceEnum = resourceEnum;
        this.namespace = namespace;
    }

    @Override
    public UriBuilder getBaseUrl() {
        return resourceEnum.getCoLabService().getBaseUrl(namespace);
    }
}
