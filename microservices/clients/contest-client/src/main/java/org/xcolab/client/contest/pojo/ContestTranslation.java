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
public class ContestTranslation extends AbstractContestTranslation implements Serializable {

    public static final TypeProvider<ContestTranslation> TYPES = new TypeProvider<>(
            ContestTranslation.class, new ParameterizedTypeReference<List<ContestTranslation>>() {});

    public ContestTranslation() {
    }

    public ContestTranslation(AbstractContestTranslation value) {
        super(value);
    }
}
