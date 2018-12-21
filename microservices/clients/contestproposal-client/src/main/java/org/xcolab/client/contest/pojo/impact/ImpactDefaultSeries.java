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
public class ImpactDefaultSeries extends AbstractImpactDefaultSeries implements Serializable {

    public static final TypeProvider<ImpactDefaultSeries> TYPES = new TypeProvider<>(ImpactDefaultSeries.class,
            new ParameterizedTypeReference<List<ImpactDefaultSeries>>() {});

    public ImpactDefaultSeries() {}

    public ImpactDefaultSeries(ImpactDefaultSeries value) {
        super(value);
    }

    public ImpactDefaultSeries(AbstractImpactDefaultSeries abstractImpactDefaultSeries) {
        super(abstractImpactDefaultSeries);
    }
}
