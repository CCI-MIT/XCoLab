package org.xcolab.service.members.domain.analyticsuserevent;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.user.pojo.IAnalyticsUserEvent;

import static org.xcolab.model.Tables.ANALYTICS_USER_EVENT;

@Repository
public class AnalyticsUserEventDaoImpl implements AnalyticsUserEventDao {

    private final DSLContext dslContext;

    @Autowired
    public AnalyticsUserEventDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public boolean exists(long userId, String idString) {
        return dslContext.selectCount()
                .from(ANALYTICS_USER_EVENT)
                .where(ANALYTICS_USER_EVENT.USER_ID.eq(userId)
                    .and(ANALYTICS_USER_EVENT.ID_STRING.eq(idString)))
                .execute() > 0;
    }

    @Override
    public IAnalyticsUserEvent create(IAnalyticsUserEvent analyticsUserEvent) {
        dslContext.insertInto(ANALYTICS_USER_EVENT)
                .set(ANALYTICS_USER_EVENT.USER_ID, analyticsUserEvent.getUserId())
                .set(ANALYTICS_USER_EVENT.ID_STRING, analyticsUserEvent.getIdString())
                .set(ANALYTICS_USER_EVENT.CATEGORY, analyticsUserEvent.getCategory())
                .set(ANALYTICS_USER_EVENT.ACTION, analyticsUserEvent.getAction())
                .set(ANALYTICS_USER_EVENT.LABEL, analyticsUserEvent.getLabel())
                .set(ANALYTICS_USER_EVENT.VALUE, analyticsUserEvent.getValue())
                .execute();
        return analyticsUserEvent;
    }
}
