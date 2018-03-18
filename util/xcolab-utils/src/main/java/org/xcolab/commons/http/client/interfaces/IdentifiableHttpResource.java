package org.xcolab.commons.http.client.interfaces;

import org.xcolab.commons.http.client.QueryId;

public interface IdentifiableHttpResource<ResourceT, IdT> extends HttpResource {

    QueryId<ResourceT, IdT> id(IdT id);
}
