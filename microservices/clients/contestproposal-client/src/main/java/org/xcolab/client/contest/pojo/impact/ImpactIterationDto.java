package org.xcolab.client.contest.pojo.impact;

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
public class ImpactIterationDto extends AbstractImpactIteration
        implements DataTransferObject<ImpactIteration> {

    public static final TypeProvider<ImpactIterationDto> TYPES =
            new TypeProvider<>(ImpactIterationDto.class,
                    new ParameterizedTypeReference<List<ImpactIterationDto>>() {
                    });

    public ImpactIterationDto() {}

    public ImpactIterationDto(AbstractImpactIteration value) {
        super(value);
    }

    @Override
    public ImpactIteration toPojo(ServiceNamespace serviceNamespace) {
        return new ImpactIteration(this, serviceNamespace);
    }
}
