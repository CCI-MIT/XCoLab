package org.xcolab.util.http.dto;

import org.xcolab.util.http.client.RestService;

import java.io.Serializable;

public interface DataTransferObject<PojoT> extends Serializable {
    PojoT toPojo(RestService restService);
}
