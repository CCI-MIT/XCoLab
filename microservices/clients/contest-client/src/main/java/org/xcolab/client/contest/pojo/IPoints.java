package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.Points;

@JsonDeserialize(as = Points.class)
public interface IPoints {

    Long getId();

    void setId(Long id);

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getUserId();

    void setUserId(Long userId);

    Double getMaterializedPoints();

    void setMaterializedPoints(Double materializedPoints);

    Double getHypotheticalPoints();

    void setHypotheticalPoints(Double hypotheticalPoints);

    Long getPointsSourceId();

    void setPointsSourceId(Long pointsSourceId);

    Long getOriginatingContestPK();

    void setOriginatingContestPK(Long originatingContestPK);

    Long getOriginatingProposalId();

    void setOriginatingProposalId(Long originatingProposalId);
}
