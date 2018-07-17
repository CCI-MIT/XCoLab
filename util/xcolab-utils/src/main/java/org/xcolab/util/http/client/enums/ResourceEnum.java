package org.xcolab.util.http.client.enums;

import org.xcolab.util.http.client.CoLabService;

/**
 * Represents a resource together with information on how to address it.
 *
 * A resource can be addressed by knowing which microservice it belongs to (determining the base URL
 * through {@link CoLabService#getBaseUrl(ServiceNamespace)}) and knowing its resource name.
 */
public interface ResourceEnum {

    /**
     * Retrieve the name of the rest resource that is used as part of the resource URI.
     *
     * The name should be plural, as it refers to the collection of elements of this resource.
     *
     * @return the resource name.
     */
    String getResourceName();

    /**
     *
     * @return the service this resource belongs to.
     */
    CoLabService getCoLabService();
}
