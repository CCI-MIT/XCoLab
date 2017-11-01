package org.xcolab.util.http.client.interfaces;

import org.xcolab.util.http.client.QueryId;

public interface IdentifiableHttpResource<ResourceT, IdT> extends HttpResource {

    QueryId<ResourceT, IdT> id(IdT id);
}
