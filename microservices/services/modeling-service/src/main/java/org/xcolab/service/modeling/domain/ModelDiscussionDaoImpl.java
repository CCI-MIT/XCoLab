package org.xcolab.service.modeling.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ModelDiscussion;
import org.xcolab.model.tables.records.ModelDiscussionRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MODEL_DISCUSSION;

@Repository
public class ModelDiscussionDaoImpl implements ModelDiscussionDao {

    private final DSLContext dslContext;

    @Autowired
    public ModelDiscussionDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<ModelDiscussion> get(long id) {
        final Record record = dslContext.select()
                .from(MODEL_DISCUSSION)
                .where(MODEL_DISCUSSION.MODEL_DISCUSSION_ID.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelDiscussion.class));
    }

    @Override
    public List<ModelDiscussion> list() {
        return dslContext.select()
                .from(MODEL_DISCUSSION)
                .fetch().into(ModelDiscussion.class);
    }

    @Override
    public ModelDiscussion create(ModelDiscussion pojo) {
        final ModelDiscussionRecord record = dslContext.insertInto(MODEL_DISCUSSION)
                .set(MODEL_DISCUSSION.MODEL_ID, pojo.getModelId())
                .set(MODEL_DISCUSSION.CATEGORY_ID, pojo.getCategoryId())
                .returning()
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve id of inserted object");
        }
        pojo.setModelDiscussionId(record.getValue(MODEL_DISCUSSION.MODEL_DISCUSSION_ID));
        return pojo;
    }

    @Override
    public boolean update(ModelDiscussion pojo) {
        return dslContext.update(MODEL_DISCUSSION)
                .set(MODEL_DISCUSSION.MODEL_ID, pojo.getModelId())
                .set(MODEL_DISCUSSION.CATEGORY_ID, pojo.getCategoryId())
                .where(MODEL_DISCUSSION.MODEL_DISCUSSION_ID.eq(pojo.getModelDiscussionId()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long id) {
        return dslContext.deleteFrom(MODEL_DISCUSSION)
                .where(MODEL_DISCUSSION.MODEL_DISCUSSION_ID.eq(id))
                .execute() > 0;
    }
}
