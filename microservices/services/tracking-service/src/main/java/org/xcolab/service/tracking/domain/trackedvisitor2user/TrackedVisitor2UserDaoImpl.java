package org.xcolab.service.tracking.domain.trackedvisitor2user;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.TrackedVisitor2User;

import java.util.Optional;

import static org.xcolab.model.tables.TrackedVisitor2UserTable.TRACKED_VISITOR_2_USER;

@Repository
public class TrackedVisitor2UserDaoImpl implements TrackedVisitor2UserDao {

    private final DSLContext dslContext;

    @Autowired
    public TrackedVisitor2UserDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<TrackedVisitor2User> getByUUID(String uuid) {
        final Record record = dslContext.select()
                .from(TRACKED_VISITOR_2_USER)
                .where(TRACKED_VISITOR_2_USER.UUID_.eq(uuid))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(TrackedVisitor2User.class));
    }

    @Override
    public Optional<TrackedVisitor2User> getByuserId(long userId) {
        final Record record = dslContext.select()
                .from(TRACKED_VISITOR_2_USER)
                .where(TRACKED_VISITOR_2_USER.USER_ID.eq(userId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(TrackedVisitor2User.class));
    }

    @Override
    public TrackedVisitor2User create(TrackedVisitor2User trackedVisitor2User) {
        dslContext.insertInto(TRACKED_VISITOR_2_USER)
                .set(TRACKED_VISITOR_2_USER.UUID_, trackedVisitor2User.getUuid_())
                .set(TRACKED_VISITOR_2_USER.USER_ID, trackedVisitor2User.getUserId())
                .set(TRACKED_VISITOR_2_USER.CREATE_DATE, DSL.currentTimestamp())
                .execute();

        return trackedVisitor2User;
    }

    @Override
    public boolean update(TrackedVisitor2User pojo) {
        return dslContext.update(TRACKED_VISITOR_2_USER)
                .set(TRACKED_VISITOR_2_USER.USER_ID, pojo.getUserId())
                .where(TRACKED_VISITOR_2_USER.UUID_.eq(pojo.getUuid_()))
                .execute() > 0;
    }
}
