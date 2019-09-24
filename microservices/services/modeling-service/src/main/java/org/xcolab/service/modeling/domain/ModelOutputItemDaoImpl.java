package org.xcolab.service.modeling.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.modeling.pojo.IModelOutputItem;
import org.xcolab.model.tables.pojos.ModelOutputItemImpl;
import org.xcolab.model.tables.records.ModelOutputItemRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MODEL_OUTPUT_ITEM;

@Repository
public class ModelOutputItemDaoImpl implements ModelOutputItemDao {

    private final DSLContext dslContext;

    @Autowired
    public ModelOutputItemDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<IModelOutputItem> get(long id) {
        final Record record = dslContext.select()
                .from(MODEL_OUTPUT_ITEM)
                .where(MODEL_OUTPUT_ITEM.MODEL_OUTPUT_ITEM_ID.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelOutputItemImpl.class));
    }

    @Override
    public List<IModelOutputItem> list() {
        return dslContext.select()
                .from(MODEL_OUTPUT_ITEM)
                .fetch().into(ModelOutputItemImpl.class);
    }

    @Override
    public IModelOutputItem create(IModelOutputItem pojo) {
        final ModelOutputItemRecord record = dslContext.insertInto(MODEL_OUTPUT_ITEM)
                .set(MODEL_OUTPUT_ITEM.MODEL_ID, pojo.getModelId())
                .set(MODEL_OUTPUT_ITEM.MODEL_OUTPUT_ITEM_ID, pojo.getModelOutputItemId())
                .set(MODEL_OUTPUT_ITEM.RELATED_OUTPUT_ITEM, pojo.getRelatedOutputItem())
                .set(MODEL_OUTPUT_ITEM.ITEM_TYPE, pojo.getItemType())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_ERROR_MESSAGE, pojo.getModelItemErrorMessage())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_IS_VISIBLE, pojo.isModelItemIsVisible())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_ERROR_POLICY, pojo.getModelItemErrorPolicy())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_LABEL_FORMAT, pojo.getModelItemLabelFormat())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_RANGE_MESSAGE, pojo.getModelItemRangeMessage())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_RANGE_POLICY, pojo.getModelItemRangePolicy())
                .set(MODEL_OUTPUT_ITEM.MODEL_OUTPUT_ITEM_ORDER, pojo.getModelOutputItemOrder())
                .returning()
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve id of inserted object");
        }
        pojo.setModelOutputItemModifierPk(record.getValue(MODEL_OUTPUT_ITEM.MODEL_OUTPUT_ITEM_MODIFIER_PK));
        return pojo;
    }

    @Override
    public boolean update(IModelOutputItem pojo) {
        return dslContext.update(MODEL_OUTPUT_ITEM)
                .set(MODEL_OUTPUT_ITEM.MODEL_ID, pojo.getModelId())
                .set(MODEL_OUTPUT_ITEM.MODEL_OUTPUT_ITEM_ID, pojo.getModelOutputItemId())
                .set(MODEL_OUTPUT_ITEM.RELATED_OUTPUT_ITEM, pojo.getRelatedOutputItem())
                .set(MODEL_OUTPUT_ITEM.ITEM_TYPE, pojo.getItemType())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_ERROR_MESSAGE, pojo.getModelItemErrorMessage())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_IS_VISIBLE, pojo.isModelItemIsVisible())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_ERROR_POLICY, pojo.getModelItemErrorPolicy())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_LABEL_FORMAT, pojo.getModelItemLabelFormat())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_RANGE_MESSAGE, pojo.getModelItemRangeMessage())
                .set(MODEL_OUTPUT_ITEM.MODEL_ITEM_RANGE_POLICY, pojo.getModelItemRangePolicy())
                .set(MODEL_OUTPUT_ITEM.MODEL_OUTPUT_ITEM_ORDER, pojo.getModelOutputItemOrder())
                .where(MODEL_OUTPUT_ITEM.MODEL_OUTPUT_ITEM_MODIFIER_PK.eq(pojo.getModelOutputItemModifierPk()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long id) {
        return dslContext.deleteFrom(MODEL_OUTPUT_ITEM)
                .where(MODEL_OUTPUT_ITEM.MODEL_OUTPUT_ITEM_MODIFIER_PK.eq(id))
                .execute() > 0;
    }
}
