package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ImpactIteration extends AbstractImpactIteration implements Serializable {

    public static final TypeProvider<ImpactIteration> TYPES =
            new TypeProvider<>(ImpactIteration.class,
                    new ParameterizedTypeReference<List<ImpactIteration>>() {});

    public ImpactIteration() {}

    public ImpactIteration(ImpactIteration value) {
        super(value);
    }

    public ImpactIteration(AbstractImpactIteration abstractImpactIteration) {
        super(abstractImpactIteration);
    }
}
