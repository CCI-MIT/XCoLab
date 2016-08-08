package org.xcolab.service.balloons.domain.balloonlink;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.BalloonLink;
import org.xcolab.service.balloons.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.xcolab.model.Tables.BALLOON_LINK;

@Repository
public class BalloonLinkDaoImpl implements BalloonLinkDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public BalloonLink getBalloonLink(String uuid) throws NotFoundException {

        final Record record = dslContext.select()
                .from(BALLOON_LINK)
                .where(BALLOON_LINK.UUID_.eq(uuid)).fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(BalloonLink.class);
    }

    @Override
    public List<BalloonLink> findByGiven(PaginationHelper paginationHelper, String uuid) {
        final SelectQuery<Record> query = dslContext.select()
                .from(BALLOON_LINK)
                .getQuery();

        if (uuid != null) {
            query.addConditions(BALLOON_LINK.BALLOON_USER_UUID.eq(uuid));
        }

        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(BalloonLink.class);
    }

    @Override
    public boolean update(BalloonLink balloonLink) {
        return dslContext.update(BALLOON_LINK)
                .set(BALLOON_LINK.UUID_, balloonLink.getUuid_())
                .set(BALLOON_LINK.TARGET_URL, balloonLink.getTargetUrl())
                .set(BALLOON_LINK.BALLOON_USER_UUID, balloonLink.getBalloonUserUuid())
                .set(BALLOON_LINK.CREATE_DATE, balloonLink.getCreateDate())
                .set(BALLOON_LINK.VISITS, balloonLink.getVisits())
                .where(BALLOON_LINK.UUID_.eq(balloonLink.getUuid_()))
                .execute() > 0;
    }

    @Override
    public BalloonLink create(BalloonLink balloonLink) {
        this.dslContext.insertInto(BALLOON_LINK)
                .set(BALLOON_LINK.UUID_, balloonLink.getUuid_())
                .set(BALLOON_LINK.TARGET_URL, balloonLink.getTargetUrl())
                .set(BALLOON_LINK.VISITS, ((balloonLink.getVisits() == null) ? (0) : (balloonLink.getVisits())))
                .set(BALLOON_LINK.BALLOON_USER_UUID, balloonLink.getBalloonUserUuid())
                .set(BALLOON_LINK.CREATE_DATE, balloonLink.getCreateDate())
                .execute();
        return balloonLink;
    }

}
