package org.xcolab.service.modeling.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ModelInputItem;
import org.xcolab.model.tables.records.ModelInputItemRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MODEL_INPUT_ITEM;

@Repository
public class ModelInputItemDaoImpl implements ModelInputItemDao {

    private final DSLContext dslContext;

    @Autowired
    public ModelInputItemDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<ModelInputItem> get(long id) {
        final Record record = dslContext.select()
                .from(MODEL_INPUT_ITEM)
                .where(MODEL_INPUT_ITEM.MODEL_INPUT_ITEM_PK.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelInputItem.class));
    }

    @Override
    public List<ModelInputItem> list() {
        return dslContext.select()
                .from(MODEL_INPUT_ITEM)
                .fetch().into(ModelInputItem.class);
    }

    @Override
    public List<ModelInputItem> findByGiven(Long modelInputGroupPk,
            Long modelId, Long modelInputId) {
        final SelectQuery<Record> query = dslContext.select().from(MODEL_INPUT_ITEM)
                .getQuery();

        if (modelInputGroupPk != null) {
            query.addConditions(MODEL_INPUT_ITEM.MODEL_GROUP_ID.eq(modelInputGroupPk));
        }

        if (modelId != null) {
            query.addConditions(MODEL_INPUT_ITEM.MODEL_ID.eq(modelId));
        }

        if (modelInputId != null) {
            query.addConditions(MODEL_INPUT_ITEM.MODEL_INPUT_ITEM_ID.eq(modelInputId));
        }

        return query.fetch().into(ModelInputItem.class);
    }

    @Override
    public ModelInputItem create(ModelInputItem pojo) {
        final ModelInputItemRecord record = dslContext.insertInto(MODEL_INPUT_ITEM)
                .set(MODEL_INPUT_ITEM.MODEL_GROUP_ID, pojo.getModelGroupId())
                .set(MODEL_INPUT_ITEM.MODEL_ID, pojo.getModelId())
                .set(MODEL_INPUT_ITEM.MODEL_INPUT_ITEM_ID, pojo.getModelInputItemID())
                .set(MODEL_INPUT_ITEM.PROPERTIES, pojo.getProperties())
                .set(MODEL_INPUT_ITEM.TYPE_, pojo.getType_())
                .returning()
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve id of inserted object");
        }
        pojo.setModelInputItemPK(record.getValue(MODEL_INPUT_ITEM.MODEL_INPUT_ITEM_PK));
        return pojo;
    }

    @Override
    public boolean update(ModelInputItem pojo) {
        return dslContext.update(MODEL_INPUT_ITEM)
                .set(MODEL_INPUT_ITEM.MODEL_GROUP_ID, pojo.getModelGroupId())
                .set(MODEL_INPUT_ITEM.MODEL_ID, pojo.getModelId())
                .set(MODEL_INPUT_ITEM.MODEL_INPUT_ITEM_ID, pojo.getModelInputItemID())
                .set(MODEL_INPUT_ITEM.PROPERTIES, pojo.getProperties())
                .set(MODEL_INPUT_ITEM.TYPE_, pojo.getType_())
                .where(MODEL_INPUT_ITEM.MODEL_INPUT_ITEM_PK.eq(pojo.getModelInputItemPK()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long id) {
        return dslContext.deleteFrom(MODEL_INPUT_ITEM)
                .where(MODEL_INPUT_ITEM.MODEL_INPUT_ITEM_PK.eq(id))
                .execute() > 0;
    }
}
