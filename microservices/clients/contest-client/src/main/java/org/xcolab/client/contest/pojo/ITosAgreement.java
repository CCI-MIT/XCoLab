package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.TosAgreement;

@JsonDeserialize(as = TosAgreement.class)
public interface ITosAgreement {

    Long getContestId();

    void setContestId(Long contestId);

    Long getUserId();

    void setUserId(Long userId);

    Boolean isAgreed();

    void setAgreed(Boolean agreed);
}
