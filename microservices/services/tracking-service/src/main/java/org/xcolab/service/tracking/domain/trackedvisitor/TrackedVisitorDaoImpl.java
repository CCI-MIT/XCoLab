package org.xcolab.service.tracking.domain.trackedvisitor;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.TrackedVisitor;

import java.util.Optional;

import static org.xcolab.model.tables.TrackedVisitorTable.TRACKED_VISITOR;

@Repository
public class TrackedVisitorDaoImpl implements org.xcolab.service.tracking.domain.trackedVisitor.TrackedVisitorDao {

    private final DSLContext dslContext;

    @Autowired
    public TrackedVisitorDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<TrackedVisitor> getByUuid(String uuid) {
        final Record record = dslContext.select()
                .from(TRACKED_VISITOR)
                .where(TRACKED_VISITOR.UUID.eq(uuid))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(TrackedVisitor.class));
    }

    @Override
    public Optional<TrackedVisitor> getByUserId(long userId) {
        final Record record = dslContext.select()
                .from(TRACKED_VISITOR)
                .where(TRACKED_VISITOR.USER_ID.eq(userId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(TrackedVisitor.class));
    }

    @Override
    public TrackedVisitor create(TrackedVisitor trackedVisitor) {
        dslContext.insertInto(TRACKED_VISITOR)
                .set(TRACKED_VISITOR.UUID, trackedVisitor.getUuid())
                .set(TRACKED_VISITOR.USER_ID, trackedVisitor.getUserId())
                .set(TRACKED_VISITOR.CREATED_AT, DSL.currentTimestamp())
                .execute();

        return trackedVisitor;
    }

    @Override
    public boolean update(TrackedVisitor pojo) {
        return dslContext.update(TRACKED_VISITOR)
                .set(TRACKED_VISITOR.USER_ID, pojo.getUserId())
                .where(TRACKED_VISITOR.UUID.eq(pojo.getUuid()))
                .execute() > 0;
    }
}
