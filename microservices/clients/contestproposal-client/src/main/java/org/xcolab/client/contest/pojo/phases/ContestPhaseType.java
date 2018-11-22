package org.xcolab.client.contest.pojo.phases;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ContestPhaseType extends AbstractContestPhaseType {

    public static final TypeProvider<ContestPhaseType> TYPES = new TypeProvider<>(
            ContestPhaseType.class, new ParameterizedTypeReference<List<ContestPhaseType>>() {});

    public ContestPhaseType() {}

    public ContestPhaseType(ContestPhaseType value) {
        super(value);
    }

    public ContestPhaseType(AbstractContestPhaseType abstractContestPhaseType,
            ServiceNamespace serviceNamespace) {
        super(abstractContestPhaseType);
    }

    public ContestStatus getStatusEnum() {
        return ContestStatus.valueOf(getStatus());
    }

    public ContestPhasePromoteType getDefaultPromotionTypeEnum() {
        return ContestPhasePromoteType.getPromoteType(getDefaultPromotionType());
    }
}
