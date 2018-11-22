package org.xcolab.client.contest.pojo.impact;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ImpactTemplateFocusAreaList extends AbstractImpactTemplateFocusAreaList {

    public static final TypeProvider<ImpactTemplateFocusAreaList> TYPES =
            new TypeProvider<>(ImpactTemplateFocusAreaList.class,
                    new ParameterizedTypeReference<List<ImpactTemplateFocusAreaList>>() {});

    public ImpactTemplateFocusAreaList() {}

    public ImpactTemplateFocusAreaList(ImpactTemplateFocusAreaList value) {
        super(value);
    }

    public ImpactTemplateFocusAreaList(AbstractImpactTemplateFocusAreaList
            abstractImpactTemplateFocusAreaList,
            ServiceNamespace serviceNamespace) {
        super(abstractImpactTemplateFocusAreaList);
    }
}
