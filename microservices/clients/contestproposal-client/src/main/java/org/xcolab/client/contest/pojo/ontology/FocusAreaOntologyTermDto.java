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
public class FocusAreaOntologyTermDto extends AbstractFocusAreaOntologyTerm
        implements DataTransferObject<FocusAreaOntologyTerm> {

    public static final TypeProvider<FocusAreaOntologyTermDto> TYPES =
            new TypeProvider<>(FocusAreaOntologyTermDto.class,
                    new ParameterizedTypeReference<List<FocusAreaOntologyTermDto>>() {
                    });


    public FocusAreaOntologyTermDto() {}

    public FocusAreaOntologyTermDto(AbstractFocusAreaOntologyTerm value) {
        super(value);
    }

    @Override
    public FocusAreaOntologyTerm toPojo(ServiceNamespace serviceNamespace) {
        return new FocusAreaOntologyTerm(this, serviceNamespace);
    }
}
