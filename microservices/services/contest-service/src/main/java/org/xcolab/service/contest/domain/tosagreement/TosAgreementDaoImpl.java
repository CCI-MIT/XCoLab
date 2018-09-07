package org.xcolab.service.contest.domain.tosagreement;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.members.pojo.Member;

import static org.xcolab.model.tables.TosAgreementTable.TOS_AGREEMENT;

@Repository
public class TosAgreementDaoImpl implements TosAgreementDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public long hasMemberAgreedToConestToS(long contestId, long memberId) {
        return dslContext.select(TOS_AGREEMENT.AGREED)
                .from(TOS_AGREEMENT)
                .where(TOS_AGREEMENT.CONTEST_ID.eq(contestId)
                        .and(TOS_AGREEMENT.USER_ID.eq(memberId)))
                .execute();
    }

    @Override
    public void setMemberAgreedToContestToS(long contestId, long memberId) {
        dslContext.insertInto(TOS_AGREEMENT, TOS_AGREEMENT.CONTEST_ID, TOS_AGREEMENT.USER_ID,
                TOS_AGREEMENT.AGREED)
                .values(contestId, memberId, true)
                .onDuplicateKeyUpdate()
                .set(TOS_AGREEMENT.AGREED, true)
                .execute();
    }
}
