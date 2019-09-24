package org.xcolab.service.members.domain.loginlog;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.client.user.pojo.tables.pojos.LoginLog;

import java.util.Optional;

import static org.xcolab.model.Tables.LOGIN_LOG;

@Repository
public class LoginLogDaoImpl implements LoginLogDao {

    private final DSLContext dslContext;

    @Autowired
    public LoginLogDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<LoginLog> get(long loginLogId) {
        final Record record = dslContext.select()
                .from(LOGIN_LOG)
                .where(LOGIN_LOG.ID.eq(loginLogId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(LoginLog.class));
    }

    @Override
    public org.xcolab.client.user.pojo.tables.pojos.LoginLog create(LoginLog loginLog) {
        final Record record = dslContext.insertInto(LOGIN_LOG)
                .set(LOGIN_LOG.USER_ID, loginLog.getUserId())
                .set(LOGIN_LOG.IP_ADDRESS, loginLog.getIpAddress())
                .set(LOGIN_LOG.COUNTRY, loginLog.getCountry())
                .set(LOGIN_LOG.CITY, loginLog.getCity())
                .set(LOGIN_LOG.ENTRY_URL, loginLog.getEntryUrl())
                .set(LOGIN_LOG.CREATED_AT, DSL.currentTimestamp())
                .returning(LOGIN_LOG.ID)
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Failed to retrieve id after creating LoginLog");
        }
        return record.into(LoginLog.class);
    }
}
