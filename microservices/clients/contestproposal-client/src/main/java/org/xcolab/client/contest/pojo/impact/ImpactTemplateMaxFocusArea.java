package org.xcolab.client.contest.pojo.impact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ImpactTemplateMaxFocusArea extends AbstractImpactTemplateMaxFocusArea
        implements Serializable {

    public static final TypeProvider<ImpactTemplateMaxFocusArea> TYPES =
            new TypeProvider<>(ImpactTemplateMaxFocusArea.class,
                    new ParameterizedTypeReference<List<ImpactTemplateMaxFocusArea>>() {});

    public ImpactTemplateMaxFocusArea() {}

    public ImpactTemplateMaxFocusArea(ImpactTemplateMaxFocusArea value) {
        super(value);
    }

    public ImpactTemplateMaxFocusArea(
            AbstractImpactTemplateMaxFocusArea abstractImpactTemplateMaxFocusArea) {
        super(abstractImpactTemplateMaxFocusArea);
    }
}
