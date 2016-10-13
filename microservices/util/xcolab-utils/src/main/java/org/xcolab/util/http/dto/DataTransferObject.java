package org.xcolab.util.http.dto;

import org.xcolab.util.http.client.RestService;

public interface DataTransferObject<PojoT> {
    PojoT toPojo(RestService restService);
}
