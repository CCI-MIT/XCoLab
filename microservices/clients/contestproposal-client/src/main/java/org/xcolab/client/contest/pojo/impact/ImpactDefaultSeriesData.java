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
public class ImpactDefaultSeriesData extends AbstractImpactDefaultSeriesData implements Serializable {

    public static final TypeProvider<ImpactDefaultSeriesData> TYPES =
            new TypeProvider<>(ImpactDefaultSeriesData.class,
                    new ParameterizedTypeReference<List<ImpactDefaultSeriesData>>() {});

    public ImpactDefaultSeriesData() {}

    public ImpactDefaultSeriesData(ImpactDefaultSeriesData value) {
        super(value);
    }

    public ImpactDefaultSeriesData(AbstractImpactDefaultSeriesData abstractImpactDefaultSeriesData) {
        super(abstractImpactDefaultSeriesData);
    }
}
