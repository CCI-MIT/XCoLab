package org.xcolab.util.http.dto;

import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.ArrayList;
import java.util.List;

public final class DtoUtil {

    private DtoUtil() {
    }

    public static <PojoT, DtoT extends DataTransferObject<PojoT>> List<PojoT> toPojos(
            List<DtoT> dtos, ServiceNamespace serviceNamespace) {
        final List<PojoT>  pojos = new ArrayList<>(dtos.size());
        for (DtoT commentDto : dtos) {
            pojos.add(commentDto.toPojo(serviceNamespace));
        }
        return pojos;
    }
}
