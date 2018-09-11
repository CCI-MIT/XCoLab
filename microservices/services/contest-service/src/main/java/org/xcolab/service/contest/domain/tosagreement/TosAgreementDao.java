package org.xcolab.service.contest.domain.tosagreement;

public interface TosAgreementDao {

    boolean hasMemberAgreedToContestTos(long contestId, long memberId);

    void setMemberAgreedToContestTos(long contestId, long memberId, boolean agreed);
}
