package org.xcolab.client.contest.pojo.impact;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ImpactTemplateMaxFocusArea extends AbstractImpactTemplateMaxFocusArea {

    public static final TypeProvider<ImpactTemplateMaxFocusArea> TYPES =
            new TypeProvider<>(ImpactTemplateMaxFocusArea.class,
                    new ParameterizedTypeReference<List<ImpactTemplateMaxFocusArea>>() {});

    public ImpactTemplateMaxFocusArea() {}

    public ImpactTemplateMaxFocusArea(ImpactTemplateMaxFocusArea value) {
        super(value);
    }

    public ImpactTemplateMaxFocusArea(
            AbstractImpactTemplateMaxFocusArea abstractImpactTemplateMaxFocusArea,
            ServiceNamespace serviceNamespace) {
        super(abstractImpactTemplateMaxFocusArea);
    }
}
