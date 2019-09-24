package org.xcolab.service.modeling.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.modeling.pojo.IModelDiscussion;
import org.xcolab.model.tables.pojos.ModelDiscussionImpl;
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
    public Optional<IModelDiscussion> get(long id) {
        final Record record = dslContext.select()
                .from(MODEL_DISCUSSION)
                .where(MODEL_DISCUSSION.ID.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelDiscussionImpl.class));
    }

    @Override
    public List<IModelDiscussion> list() {
        return dslContext.select()
                .from(MODEL_DISCUSSION)
                .fetch().into(ModelDiscussionImpl.class);
    }

    @Override
    public IModelDiscussion create(IModelDiscussion pojo) {
        final ModelDiscussionRecord record = dslContext.insertInto(MODEL_DISCUSSION)
                .set(MODEL_DISCUSSION.MODEL_ID, pojo.getModelId())
                .set(MODEL_DISCUSSION.CATEGORY_ID, pojo.getCategoryId())
                .returning()
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve id of inserted object");
        }
        pojo.setId(record.getValue(MODEL_DISCUSSION.ID));
        return pojo;
    }

    @Override
    public boolean update(IModelDiscussion pojo) {
        return dslContext.update(MODEL_DISCUSSION)
                .set(MODEL_DISCUSSION.MODEL_ID, pojo.getModelId())
                .set(MODEL_DISCUSSION.CATEGORY_ID, pojo.getCategoryId())
                .where(MODEL_DISCUSSION.ID.eq(pojo.getId()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long id) {
        return dslContext.deleteFrom(MODEL_DISCUSSION)
                .where(MODEL_DISCUSSION.ID.eq(id))
                .execute() > 0;
    }
}
