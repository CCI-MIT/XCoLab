package org.xcolab.client.contest.pojo.ontology;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class OntologyTermDto extends AbstractOntologyTerm
        implements DataTransferObject<OntologyTerm> {

    public static final TypeProvider<OntologyTermDto> TYPES = new TypeProvider<>(OntologyTermDto.class,
            new ParameterizedTypeReference<List<OntologyTermDto>>() {
            });

    public OntologyTermDto() {}

    public OntologyTermDto(AbstractOntologyTerm value) {
        super(value);
    }

    @Override
    public OntologyTerm toPojo(RestService restService) {
        return new OntologyTerm(this, restService);
    }
}
