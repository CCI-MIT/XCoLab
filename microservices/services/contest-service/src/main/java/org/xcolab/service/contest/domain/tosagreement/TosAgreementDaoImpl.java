package org.xcolab.service.contest.domain.tosagreement;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.xcolab.model.tables.TosAgreementTable.TOS_AGREEMENT;

@Repository
public class TosAgreementDaoImpl implements TosAgreementDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public boolean hasMemberAgreedToContestTos(long contestId, long memberId) {
        Record1<Boolean> record = dslContext.select(TOS_AGREEMENT.AGREED)
                .from(TOS_AGREEMENT)
                .where(TOS_AGREEMENT.CONTEST_ID.eq(contestId)
                        .and(TOS_AGREEMENT.USER_ID.eq(memberId)))
                .fetchOne();
        return record == null ? false : record.value1();
    }

    @Override
    public void setMemberAgreedToContestTos(long contestId, long memberId, boolean agreed) {
        dslContext.insertInto(TOS_AGREEMENT, TOS_AGREEMENT.CONTEST_ID, TOS_AGREEMENT.USER_ID,
                TOS_AGREEMENT.AGREED)
                .values(contestId, memberId, agreed)
                .onDuplicateKeyUpdate()
                .set(TOS_AGREEMENT.AGREED, agreed)
                .execute();
    }
}
