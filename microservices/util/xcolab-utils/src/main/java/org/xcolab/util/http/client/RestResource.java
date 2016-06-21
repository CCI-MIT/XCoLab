package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;

public class RestResource<T> implements UrlProvider {

    private final UrlProvider urlProvider;
    private final String resourceName;

    public RestResource(UrlProvider serviceOrParent, String resourceName) {
        this.urlProvider = serviceOrParent;
        this.resourceName = resourceName;
    }

    @Override
    public UriBuilder getBaseUrl() {
        return urlProvider.getBaseUrl();
    }

    public UriBuilder getResourceUrl() {
        return urlProvider.getBaseUrl().path("/" + resourceName);
    }

    public UriBuilder getResourceUrl(long resourceId) {
        return urlProvider.getBaseUrl().path("/" + resourceName + "/" + resourceId);
    }

    public UriBuilder getResourceUrl(String resourceId) {
        return urlProvider.getBaseUrl().path("/" + resourceName + "/" + resourceId);
    }

    public RestResource getSubResource(long resourceId, String subResourceName) {
        return new RestResource(new ParentResourceClient(this, resourceId), subResourceName);
    }

    private static class ParentResourceClient implements UrlProvider {

        private final RestResource parentRestResource;
        private final long parentResourceId;

        public ParentResourceClient(RestResource parentRestResource, long parentResourceId) {
            this.parentRestResource = parentRestResource;
            this.parentResourceId = parentResourceId;
        }

        @Override
        public UriBuilder getBaseUrl() {
            return parentRestResource.getResourceUrl(parentResourceId);
        }
    }
}
