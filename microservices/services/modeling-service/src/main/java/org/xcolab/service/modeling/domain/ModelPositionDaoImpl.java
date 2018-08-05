package org.xcolab.service.modeling.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ModelPosition;
import org.xcolab.model.tables.records.ModelPositionRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MODEL_POSITION;

@Repository
public class ModelPositionDaoImpl implements ModelPositionDao {

    private final DSLContext dslContext;

    @Autowired
    public ModelPositionDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<ModelPosition> get(long id) {
        final Record record = dslContext.select()
                .from(MODEL_POSITION)
                .where(MODEL_POSITION.ID.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelPosition.class));
    }

    @Override
    public List<ModelPosition> list() {
        return dslContext.select()
                .from(MODEL_POSITION)
                .fetch().into(ModelPosition.class);
    }

    @Override
    public List<ModelPosition> findByGiven(Long modelId) {
        final SelectQuery<Record> query = dslContext.select().from(MODEL_POSITION)
                .getQuery();

        if (modelId != null) {
            query.addConditions(MODEL_POSITION.MODEL_ID.eq(modelId));
        }

        return query.fetch().into(ModelPosition.class);
    }

    @Override
    public ModelPosition create(ModelPosition pojo) {
        final ModelPositionRecord record = dslContext.insertInto(MODEL_POSITION)
                .set(MODEL_POSITION.MODEL_ID, pojo.getModelId())
                .set(MODEL_POSITION.POSITION_ID, pojo.getPositionId())
                .returning()
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve id of inserted object");
        }
        pojo.setId(record.getValue(MODEL_POSITION.ID));
        return pojo;
    }

    @Override
    public boolean update(ModelPosition pojo) {
        return dslContext.update(MODEL_POSITION)
                .set(MODEL_POSITION.MODEL_ID, pojo.getModelId())
                .set(MODEL_POSITION.POSITION_ID, pojo.getPositionId())
                .where(MODEL_POSITION.ID.eq(pojo.getId()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long id) {
        return dslContext.deleteFrom(MODEL_POSITION)
                .where(MODEL_POSITION.ID.eq(id))
                .execute() > 0;
    }
}
