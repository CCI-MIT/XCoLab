package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.pojo.tables.pojos.ContestPhaseType;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;

@JsonDeserialize(as = ContestPhaseType.class)
public interface IContestPhaseType {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    String getStatus();

    void setStatus(String status);

    Boolean isFellowScreeningActiveDefault();

    void setFellowScreeningActiveDefault(Boolean fellowScreeningActiveDefault);

    String getContestPhaseAutopromoteDefault();

    void setContestPhaseAutopromoteDefault(String contestPhaseAutopromoteDefault);

    Boolean isInvisible();

    void setInvisible(Boolean invisible);

    Integer getPointsAccessible();

    void setPointsAccessible(Integer pointsAccessible);

    String getDefaultPromotionType();

    void setDefaultPromotionType(String defaultPromotionType);

    String getDefaultFlagText();

    void setDefaultFlagText(String defaultFlagText);

    Boolean isIsDeprecated();

    void setIsDeprecated(Boolean isDeprecated);

    default ContestStatus getStatusEnum() {
        return ContestStatus.valueOf(getStatus());
    }

    default ContestPhasePromoteType getDefaultPromotionTypeEnum() {
        return ContestPhasePromoteType.getPromoteType(getDefaultPromotionType());
    }

}
