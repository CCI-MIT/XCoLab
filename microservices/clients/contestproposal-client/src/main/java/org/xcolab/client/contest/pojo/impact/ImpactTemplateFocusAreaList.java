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
public class ImpactTemplateFocusAreaList extends AbstractImpactTemplateFocusAreaList
        implements Serializable {

    public static final TypeProvider<ImpactTemplateFocusAreaList> TYPES =
            new TypeProvider<>(ImpactTemplateFocusAreaList.class,
                    new ParameterizedTypeReference<List<ImpactTemplateFocusAreaList>>() {});

    public ImpactTemplateFocusAreaList() {}

    public ImpactTemplateFocusAreaList(ImpactTemplateFocusAreaList value) {
        super(value);
    }

    public ImpactTemplateFocusAreaList(AbstractImpactTemplateFocusAreaList
            abstractImpactTemplateFocusAreaList) {
        super(abstractImpactTemplateFocusAreaList);
    }
}
