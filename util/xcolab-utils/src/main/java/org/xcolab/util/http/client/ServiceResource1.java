package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.enums.ResourceEnum;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.interfaces.AbstractServiceResource;

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
