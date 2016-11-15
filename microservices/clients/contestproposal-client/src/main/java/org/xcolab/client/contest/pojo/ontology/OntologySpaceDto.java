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
public class OntologySpaceDto extends AbstractOntologySpace
        implements DataTransferObject<OntologySpace> {

    public static final TypeProvider<OntologySpaceDto> TYPES = new TypeProvider<>(OntologySpaceDto.class,
            new ParameterizedTypeReference<List<OntologySpaceDto>>() {
            });

    public OntologySpaceDto() {}

    public OntologySpaceDto(AbstractOntologySpace value) {
        super(value);
    }

    @Override
    public OntologySpace toPojo(RestService restService) {
        return new OntologySpace(this, restService);
    }
}
