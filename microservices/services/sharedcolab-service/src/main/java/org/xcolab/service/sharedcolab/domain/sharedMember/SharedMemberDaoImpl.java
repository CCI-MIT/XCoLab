package org.xcolab.service.sharedcolab.domain.sharedMember;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.SharedMember;
import org.xcolab.model.tables.records.SharedMemberRecord;

import static org.xcolab.model.Tables.SHARED_MEMBER;

import java.sql.Timestamp;

@Repository
public class SharedMemberDaoImpl implements SharedMemberDao {

    @Autowired
    private DSLContext dslContext;

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
    public Long create(String screenName, String emailAddress, Timestamp createDate) {

        SharedMemberRecord ret = this.dslContext.insertInto(SHARED_MEMBER)
                .set(SHARED_MEMBER.SCREEN_NAME, screenName)
                .set(SHARED_MEMBER.EMAIL_ADDRESS, emailAddress)
                .set(SHARED_MEMBER.CREATE_DATE, createDate)
                .returning(SHARED_MEMBER.SHARED_MEMBER_ID)
                .fetchOne();
        if (ret != null) {
            return ret.getValue(SHARED_MEMBER.SHARED_MEMBER_ID);
        } else {
            return null;
        }

    }
}
