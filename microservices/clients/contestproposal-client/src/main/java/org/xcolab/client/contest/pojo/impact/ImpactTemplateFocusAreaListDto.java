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
public class ImpactTemplateFocusAreaListDto extends AbstractImpactTemplateFocusAreaList
        implements DataTransferObject<ImpactTemplateFocusAreaList> {

    public static final TypeProvider<ImpactTemplateFocusAreaListDto> TYPES =
            new TypeProvider<>(ImpactTemplateFocusAreaListDto.class,
                    new ParameterizedTypeReference<List<ImpactTemplateFocusAreaListDto>>() {
                    });

    public ImpactTemplateFocusAreaListDto() {}

    public ImpactTemplateFocusAreaListDto(AbstractImpactTemplateFocusAreaList value) {
        super(value);
    }

    @Override
    public ImpactTemplateFocusAreaList toPojo(ServiceNamespace serviceNamespace) {
        return new ImpactTemplateFocusAreaList(this, serviceNamespace);
    }
}
