package org.xcolab.service.tracking.domain.balloonusertracking;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.tracking.pojo.IBalloonUserTracking;
import org.xcolab.client.tracking.pojo.tables.pojos.BalloonUserTracking;
import org.xcolab.service.tracking.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.BALLOON_USER_TRACKING;

@Repository
public class BalloonUserTrackingDaoImpl implements BalloonUserTrackingDao {

    private final DSLContext dslContext;

    @Autowired
    public BalloonUserTrackingDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public IBalloonUserTracking getBalloonUserTrackingByUuid(String uuid) throws NotFoundException {
        final Record record = dslContext.select()
                .from(BALLOON_USER_TRACKING)
                .where(BALLOON_USER_TRACKING.UUID.eq(uuid)).fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(BalloonUserTracking.class);

    }

    @Override
    public List<IBalloonUserTracking> list(String email, String context) {
        final SelectQuery<Record> query =
                dslContext.select().from(BALLOON_USER_TRACKING).getQuery();

        if (email != null) {
            query.addConditions(BALLOON_USER_TRACKING.EMAIL.eq(email));
        }

        if (context != null) {
            query.addConditions(BALLOON_USER_TRACKING.BALLOON_LINK_CONTEXT.eq(context));
        }

        return query.fetchInto(BalloonUserTracking.class);

    }

    @Override
    public boolean update(IBalloonUserTracking balloonUserTracking) {
        return dslContext.update(BALLOON_USER_TRACKING)
                .set(BALLOON_USER_TRACKING.UUID, balloonUserTracking.getUuid())
                .set(BALLOON_USER_TRACKING.EMAIL, balloonUserTracking.getEmail())
                .set(BALLOON_USER_TRACKING.PARENT, balloonUserTracking.getParent())
                .set(BALLOON_USER_TRACKING.IP, balloonUserTracking.getIp())
                .set(BALLOON_USER_TRACKING.REGISTRATION_DATE, balloonUserTracking.getRegistrationDate())
                .set(BALLOON_USER_TRACKING.FORM_FILED_DATE, balloonUserTracking.getFormFiledDate())
                .set(BALLOON_USER_TRACKING.USER_ID, balloonUserTracking.getUserId())
                .set(BALLOON_USER_TRACKING.BALLOON_TEXT_ID, balloonUserTracking.getBalloonTextId())
                .set(BALLOON_USER_TRACKING.REFERRER, balloonUserTracking.getReferrer())
                .set(BALLOON_USER_TRACKING.LATITUDE, balloonUserTracking.getLatitude())
                .set(BALLOON_USER_TRACKING.LONGITUDE, balloonUserTracking.getLongitude())
                .set(BALLOON_USER_TRACKING.CITY, balloonUserTracking.getCity())
                .set(BALLOON_USER_TRACKING.EXTRA_DATA, balloonUserTracking.getExtraData())
                .set(BALLOON_USER_TRACKING.BALLOON_LINK_UUID, balloonUserTracking.getBalloonLinkUuid())
                .set(BALLOON_USER_TRACKING.BALLOON_LINK_CONTEXT, balloonUserTracking.getBalloonLinkContext())
                .set(BALLOON_USER_TRACKING.USER_AGENT, balloonUserTracking.getUserAgent())
                .where(BALLOON_USER_TRACKING.UUID.eq(balloonUserTracking.getUuid()))
                .execute() > 0;
    }

    @Override
    public IBalloonUserTracking create(IBalloonUserTracking balloonUserTracking) {
        this.dslContext.insertInto(BALLOON_USER_TRACKING)
                .set(BALLOON_USER_TRACKING.UUID, balloonUserTracking.getUuid())
                .set(BALLOON_USER_TRACKING.EMAIL, balloonUserTracking.getEmail())
                .set(BALLOON_USER_TRACKING.PARENT, balloonUserTracking.getParent())
                .set(BALLOON_USER_TRACKING.IP, balloonUserTracking.getIp())
                .set(BALLOON_USER_TRACKING.CREATED_AT, DSL.currentTimestamp())
                .set(BALLOON_USER_TRACKING.REGISTRATION_DATE, balloonUserTracking.getRegistrationDate())
                .set(BALLOON_USER_TRACKING.FORM_FILED_DATE, balloonUserTracking.getFormFiledDate())
                .set(BALLOON_USER_TRACKING.USER_ID, balloonUserTracking.getUserId())
                .set(BALLOON_USER_TRACKING.BALLOON_TEXT_ID, balloonUserTracking.getBalloonTextId())
                .set(BALLOON_USER_TRACKING.REFERRER, balloonUserTracking.getReferrer())
                .set(BALLOON_USER_TRACKING.LATITUDE, balloonUserTracking.getLatitude())
                .set(BALLOON_USER_TRACKING.LONGITUDE, balloonUserTracking.getLongitude())
                .set(BALLOON_USER_TRACKING.CITY, balloonUserTracking.getCity())
                .set(BALLOON_USER_TRACKING.EXTRA_DATA, balloonUserTracking.getExtraData())
                .set(BALLOON_USER_TRACKING.BALLOON_LINK_UUID, balloonUserTracking.getBalloonLinkUuid())
                .set(BALLOON_USER_TRACKING.BALLOON_LINK_CONTEXT, balloonUserTracking.getBalloonLinkContext())
                .set(BALLOON_USER_TRACKING.USER_AGENT, balloonUserTracking.getUserAgent())
                .execute();


            return balloonUserTracking;

    }

    @Override
    public boolean delete(String uuid) {
        return dslContext.deleteFrom(BALLOON_USER_TRACKING)
                .where(BALLOON_USER_TRACKING.UUID.eq(uuid))
                .execute() > 0;
    }
}
