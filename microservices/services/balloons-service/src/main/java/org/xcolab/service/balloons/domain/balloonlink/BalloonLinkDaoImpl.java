package org.xcolab.service.balloons.domain.balloonlink;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.BalloonLink;
import org.xcolab.model.tables.records.BalloonLinkRecord;
import org.xcolab.service.balloons.exceptions.NotFoundException;

import static org.xcolab.model.Tables.BALLOON_LINK;
import static org.xcolab.model.Tables.BALLOON_USER_TRACKING;

@Repository
public class BalloonLinkDaoImpl implements BalloonLinkDao {
    @Autowired
    private DSLContext dslContext;

    @Override
    public BalloonLink getBallonLink(String uuid) throws NotFoundException {

        final Record record = dslContext.select()
                .from(BALLOON_LINK)
                .where(BALLOON_LINK.UUID_.eq(uuid)).fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(BalloonLink.class);
    }

    @Override
    public BalloonLink getBallonLinkByUserUuid(String uuid) throws NotFoundException {

        final Record record = dslContext.select()
                .from(BALLOON_LINK)
                .where(BALLOON_LINK.BALLOON_USER_UUID.eq(uuid)).fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(BalloonLink.class);
    }

    @Override
    public boolean update(BalloonLink balloonLink) {
        return dslContext.update(BALLOON_LINK)
                .set(BALLOON_LINK.UUID_, balloonLink.getUuid_())
                .set(BALLOON_LINK.TARGET_URL, balloonLink.getTargetUrl())
                .set(BALLOON_LINK.BALLOON_USER_UUID, balloonLink.getBalloonUserUuid())
                .set(BALLOON_LINK.CREATE_DATE, balloonLink.getCreateDate())
                .where(BALLOON_LINK.UUID_.eq(balloonLink.getUuid_()))
                .execute() > 0;
    }

    @Override
    public BalloonLink create(BalloonLink balloonLink) {
        BalloonLinkRecord ret = this.dslContext.insertInto(BALLOON_LINK)
                .set(BALLOON_LINK.UUID_, balloonLink.getUuid_())
                .set(BALLOON_LINK.TARGET_URL, balloonLink.getTargetUrl())
                .set(BALLOON_LINK.BALLOON_USER_UUID, balloonLink.getBalloonUserUuid())
                .set(BALLOON_LINK.CREATE_DATE, balloonLink.getCreateDate())
                .returning(BALLOON_USER_TRACKING.UUID_)
                .fetchOne();

        if (ret != null) {
            return balloonLink;
        } else {
            return null;
        }
    }

}
