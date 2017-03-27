package org.xcolab.service.tracking.domain.balloonusertracking;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.BalloonUserTracking;
import org.xcolab.service.tracking.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.BALLOON_USER_TRACKING;


@Repository
public class BalloonUserTrackingDaoImpl implements BalloonUserTrackingDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public BalloonUserTracking getBalloonUserTrackingByUuid(String uuid) throws NotFoundException {
        final Record record = dslContext.select()
                .from(BALLOON_USER_TRACKING)
                .where(BALLOON_USER_TRACKING.UUID_.eq(uuid)).fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(BalloonUserTracking.class);

    }

    @Override
    public List<BalloonUserTracking> getAllBalloonUserTracking() {
        return dslContext.select()
                .from(BALLOON_USER_TRACKING)
                .fetchInto(BalloonUserTracking.class);
    }

    @Override
    public List<BalloonUserTracking> getBallonUserTrackingByEmail(String email) throws NotFoundException {
        return dslContext.select()
                .from(BALLOON_USER_TRACKING)
                .where(BALLOON_USER_TRACKING.EMAIL.eq(email)).fetchInto(BalloonUserTracking.class);

    }

    @Override
    public boolean update(BalloonUserTracking balloonUserTracking) {
        return dslContext.update(BALLOON_USER_TRACKING)
                .set(BALLOON_USER_TRACKING.UUID_, balloonUserTracking.getUuid_())
                .set(BALLOON_USER_TRACKING.EMAIL, balloonUserTracking.getEmail())
                .set(BALLOON_USER_TRACKING.PARENT, balloonUserTracking.getParent())
                .set(BALLOON_USER_TRACKING.IP, balloonUserTracking.getIp())
                .set(BALLOON_USER_TRACKING.CREATE_DATE, balloonUserTracking.getCreateDate())
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
                .where(BALLOON_USER_TRACKING.UUID_.eq(balloonUserTracking.getUuid_()))
                .execute() > 0;
    }

    @Override
    public BalloonUserTracking create(BalloonUserTracking balloonUserTracking) {
        this.dslContext.insertInto(BALLOON_USER_TRACKING)
                .set(BALLOON_USER_TRACKING.UUID_, balloonUserTracking.getUuid_())
                .set(BALLOON_USER_TRACKING.EMAIL, balloonUserTracking.getEmail())
                .set(BALLOON_USER_TRACKING.PARENT, balloonUserTracking.getParent())
                .set(BALLOON_USER_TRACKING.IP, balloonUserTracking.getIp())
                .set(BALLOON_USER_TRACKING.CREATE_DATE, balloonUserTracking.getCreateDate())
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

}
