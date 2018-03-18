package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.interfaces.AbstractRestResource;
import org.xcolab.util.http.client.interfaces.IdentifiableHttpResource;
import org.xcolab.util.http.client.types.TypeProvider;

public class RestResource2<ResourceT1, IdT1, ResourceT2, IdT2> {

    private final IdentifiableHttpResource<ResourceT1, IdT1> parent;
    private final String resourceName;
    private final TypeProvider<ResourceT2> typeProvider;

    public RestResource2(IdentifiableHttpResource<ResourceT1, IdT1> parent, String resourceName,
            TypeProvider<ResourceT2> typeProvider) {
        this.parent = parent;
        this.resourceName = resourceName;
        this.typeProvider = typeProvider;
    }

    public RestResource<ResourceT2, IdT2> resolveParent(final QueryId<ResourceT1, IdT1> queryId) {
        return new NestedResource(queryId);
    }

    private class NestedResource extends AbstractRestResource<ResourceT2, IdT2> {

        private final QueryId<ResourceT1, IdT1> queryId;

        NestedResource(QueryId<ResourceT1, IdT1> queryId) {
            super(resourceName, typeProvider);
            this.queryId = queryId;
        }

        @Override
        public UriBuilder getResourceUrl() {
            return parent.getResourceUrl(queryId.getId()).resource(resourceName);
        }

        @Override
        public UriBuilder getResourceUrl(Object resourceId) {
            return parent.getResourceUrl(queryId.getId()).resource(resourceName, resourceId);
        }

        @Override
        public UriBuilder getBaseUrl() {
            return parent.getBaseUrl();
        }
    }
}
