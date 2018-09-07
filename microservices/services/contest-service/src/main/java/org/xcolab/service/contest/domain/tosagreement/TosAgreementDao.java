package org.xcolab.service.contest.domain.tosagreement;

public interface TosAgreementDao {

    long hasMemberAgreedToConestToS(long contestId, long memberId);

    void setMemberAgreedToContestToS(long contestId, long memberId);
}
