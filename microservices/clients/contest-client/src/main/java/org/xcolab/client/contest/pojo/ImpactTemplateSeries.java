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
public class ImpactTemplateSeries extends AbstractImpactTemplateSeries
        implements Serializable {

    public static final TypeProvider<ImpactTemplateSeries> TYPES =
            new TypeProvider<>(ImpactTemplateSeries.class,
                    new ParameterizedTypeReference<List<ImpactTemplateSeries>>() {});

    public ImpactTemplateSeries() {}

    public ImpactTemplateSeries(ImpactTemplateSeries value) {
        super(value);
    }

    public ImpactTemplateSeries(AbstractImpactTemplateSeries abstractImpactTemplateSeries) {
        super(abstractImpactTemplateSeries);
    }
}
