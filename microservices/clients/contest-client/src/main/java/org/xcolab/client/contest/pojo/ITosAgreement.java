package org.xcolab.client.contest.pojo;

public interface ITosAgreement {

    Long getContestId();

    void setContestId(Long contestId);

    Long getUserId();

    void setUserId(Long userId);

    Boolean getAgreed();

    void setAgreed(Boolean agreed);
}
