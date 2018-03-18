package org.xcolab.client.contest.pojo.ontology;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class FocusAreaDto extends AbstractFocusArea implements DataTransferObject<FocusArea> {

    public static final TypeProvider<FocusAreaDto> TYPES = new TypeProvider<>(FocusAreaDto.class,
            new ParameterizedTypeReference<List<FocusAreaDto>>() {
            });


    public FocusAreaDto() {}

    public FocusAreaDto(AbstractFocusArea value) {
        super(value);
    }

    @Override
    public FocusArea toPojo(ServiceNamespace serviceNamespace) {
        return new FocusArea(this, serviceNamespace);
    }
}
