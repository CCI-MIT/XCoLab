package org.xcolab.commons.http.dto;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

import java.io.Serializable;

public interface DataTransferObject<PojoT> extends Serializable {

    PojoT toPojo(ServiceNamespace serviceNamespace);
}
