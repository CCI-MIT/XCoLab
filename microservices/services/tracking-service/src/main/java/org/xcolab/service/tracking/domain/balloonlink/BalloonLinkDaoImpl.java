package org.xcolab.service.tracking.domain.balloonlink;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.tracking.pojo.IBalloonLink;
import org.xcolab.client.tracking.pojo.tables.pojos.BalloonLink;
import org.xcolab.service.tracking.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.xcolab.model.Tables.BALLOON_LINK;

@Repository
public class BalloonLinkDaoImpl implements BalloonLinkDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public IBalloonLink getBalloonLink(String uuid) throws NotFoundException {

        final Record record = dslContext.select()
                .from(BALLOON_LINK)
                .where(BALLOON_LINK.UUID.eq(uuid)).fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(BalloonLink.class);
    }

    @Override
    public List<IBalloonLink> findByGiven(PaginationHelper paginationHelper, String uuid) {
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
    public boolean update(IBalloonLink balloonLink) {
        return dslContext.update(BALLOON_LINK)
                .set(BALLOON_LINK.UUID, balloonLink.getUuid())
                .set(BALLOON_LINK.TARGET_URL, balloonLink.getTargetUrl())
                .set(BALLOON_LINK.BALLOON_USER_UUID, balloonLink.getBalloonUserUuid())
                .set(BALLOON_LINK.CREATED_AT, balloonLink.getCreatedAt())
                .set(BALLOON_LINK.VISITS, balloonLink.getVisits())
                .where(BALLOON_LINK.UUID.eq(balloonLink.getUuid()))
                .execute() > 0;
    }

    @Override
    public IBalloonLink create(IBalloonLink balloonLink) {
        this.dslContext.insertInto(BALLOON_LINK)
                .set(BALLOON_LINK.UUID, balloonLink.getUuid())
                .set(BALLOON_LINK.TARGET_URL, balloonLink.getTargetUrl())
                .set(BALLOON_LINK.VISITS, ((balloonLink.getVisits() == null) ? (0) : (balloonLink.getVisits())))
                .set(BALLOON_LINK.BALLOON_USER_UUID, balloonLink.getBalloonUserUuid())
                .set(BALLOON_LINK.CREATED_AT, balloonLink.getCreatedAt())
                .execute();
        return balloonLink;
    }
}
