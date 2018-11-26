package org.xcolab.client.contest.pojo.phases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContestPhaseType extends AbstractContestPhaseType implements Serializable {

    public static final TypeProvider<ContestPhaseType> TYPES = new TypeProvider<>(
            ContestPhaseType.class, new ParameterizedTypeReference<List<ContestPhaseType>>() {});

    public ContestPhaseType() {}

    public ContestPhaseType(ContestPhaseType value) {
        super(value);
    }

    public ContestPhaseType(AbstractContestPhaseType abstractContestPhaseType) {
        super(abstractContestPhaseType);
    }

    public ContestStatus getStatusEnum() {
        return ContestStatus.valueOf(getStatus());
    }

    public ContestPhasePromoteType getDefaultPromotionTypeEnum() {
        return ContestPhasePromoteType.getPromoteType(getDefaultPromotionType());
    }
}
