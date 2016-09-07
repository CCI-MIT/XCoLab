package org.xcolab.service.sharedcolab.domain.sharedMember;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.SharedMember;
import org.xcolab.model.tables.records.SharedMemberRecord;

import java.util.Optional;

import static org.xcolab.model.Tables.SHARED_MEMBER;

@Repository
public class SharedMemberDaoImpl implements SharedMemberDao {

    private final DSLContext dslContext;

    @Autowired
    public SharedMemberDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public boolean isScreenNameTaken(String screenName) {
        return dslContext.selectCount()
                .from(SHARED_MEMBER)
                .where(SHARED_MEMBER.SCREEN_NAME.eq(screenName))
                .fetchOne(0, Integer.class) > 0;
    }

    @Override
    public boolean isEmailUsed(String email) {
        return dslContext.selectCount()
                .from(SHARED_MEMBER)
                .where(SHARED_MEMBER.EMAIL_ADDRESS.eq(email))
                .fetchOne(0, Integer.class) > 0;
    }

    @Override
    public Optional<SharedMember> getByScreenNameAndEmail(String screenName, String email) {
        SharedMemberRecord record = this.dslContext.selectFrom(SHARED_MEMBER)
                .where(SHARED_MEMBER.SCREEN_NAME.eq(screenName))
                .and(SHARED_MEMBER.EMAIL_ADDRESS.eq(email)).fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(SharedMember.class));
    }

    @Override
    public Long create(String screenName, String emailAddress, String colabOrigin) {

        SharedMemberRecord record = this.dslContext.insertInto(SHARED_MEMBER)
                .set(SHARED_MEMBER.SCREEN_NAME, screenName)
                .set(SHARED_MEMBER.EMAIL_ADDRESS, emailAddress)
                .set(SHARED_MEMBER.CREATE_DATE, DSL.currentTimestamp())
                .set(SHARED_MEMBER.COLAB_ORIGIN,colabOrigin)
                .returning(SHARED_MEMBER.SHARED_MEMBER_ID)
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve inserted id");
        }
        return record.getValue(SHARED_MEMBER.SHARED_MEMBER_ID);
    }
}
