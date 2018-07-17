package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.interfaces.AbstractRestResource;
import org.xcolab.util.http.client.interfaces.IdentifiableHttpResource;
import org.xcolab.util.http.client.types.TypeProvider;

/**
 * Represents a second-level nested REST resource.
 *
 * A nested REST-resource must resolve its parent resource's ID using
 * {@link #resolveParentId(QueryId)} before it can be queried.
 *
 * @param <ResourceT1> the parent resource's element type
 * @param <IdT1> the parent resource's element identifier type
 * @param <ResourceT2> the nested resource's element type
 * @param <IdT2> the nested resource's identifier type
 */
public class RestResource2<ResourceT1, IdT1, ResourceT2, IdT2> {

    private final IdentifiableHttpResource<ResourceT1, IdT1> parent;
    private final String resourceName;
    private final TypeProvider<ResourceT2> typeProvider;

    /**
     * Creates a new nested rest resource from
     *
     * @see RestResource1#nestedResource(String, TypeProvider)
     *
     * @param parent the parent resource
     * @param resourceName the name of the nested resource
     * @param typeProvider a container for the nested resource's entity and list types
     */
    public RestResource2(IdentifiableHttpResource<ResourceT1, IdT1> parent, String resourceName,
            TypeProvider<ResourceT2> typeProvider) {
        this.parent = parent;
        this.resourceName = resourceName;
        this.typeProvider = typeProvider;
    }

    /**
     * Resolves the parent resource's ID, to make the nested resource queriable.
     *
     *
     *
     * @param queryId the type-safe identifier object of the parent resource
     * @return
     */
    public RestResource<ResourceT2, IdT2> resolveParentId(final QueryId<ResourceT1, IdT1> queryId) {
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
