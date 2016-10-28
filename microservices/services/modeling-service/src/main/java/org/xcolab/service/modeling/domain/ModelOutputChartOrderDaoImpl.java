package org.xcolab.service.modeling.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ModelOutputChartOrder;
import org.xcolab.model.tables.records.ModelOutputChartOrderRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MODEL_OUTPUT_CHART_ORDER;

@Repository
public class ModelOutputChartOrderDaoImpl implements ModelOutputChartOrderDao {

    private final DSLContext dslContext;

    @Autowired
    public ModelOutputChartOrderDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<ModelOutputChartOrder> get(long id) {
        final Record record = dslContext.select()
                .from(MODEL_OUTPUT_CHART_ORDER)
                .where(MODEL_OUTPUT_CHART_ORDER.MODEL_OUTPUT_CHART_ORDER_PK.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelOutputChartOrder.class));
    }

    @Override
    public List<ModelOutputChartOrder> list() {
        return dslContext.select()
                .from(MODEL_OUTPUT_CHART_ORDER)
                .fetch().into(ModelOutputChartOrder.class);
    }

    @Override
    public ModelOutputChartOrder create(ModelOutputChartOrder pojo) {
        final ModelOutputChartOrderRecord record = dslContext.insertInto(MODEL_OUTPUT_CHART_ORDER)
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_ID, pojo.getModelId())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_OUTPUT_CHART_ORDER_, pojo.getModelOutputChartOrder())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_CHART_IS_VISIBLE, pojo.getModelChartIsVisible())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_INDEX_ERROR_MESSAGE, pojo.getModelIndexErrorMessage())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_INDEX_RANGE_MESSAGE, pojo.getModelIndexRangeMessage())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_INDEX_ERROR_POLICY, pojo.getModelIndexErrorPolicy())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_INDEX_RANGE_POLICY, pojo.getModelIndexRangePolicy())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_OUTPUT_LABEL, pojo.getModelOutputLabel())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_INDEX_RANGE_POLICY, pojo.getModelIndexRangePolicy())
                .returning()
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve id of inserted object");
        }
        pojo.setModelOutputChartOrderPK(record.getValue(MODEL_OUTPUT_CHART_ORDER.MODEL_OUTPUT_CHART_ORDER_PK));
        return pojo;
    }

    @Override
    public boolean update(ModelOutputChartOrder pojo) {
        return dslContext.update(MODEL_OUTPUT_CHART_ORDER)
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_ID, pojo.getModelId())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_OUTPUT_CHART_ORDER_, pojo.getModelOutputChartOrder())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_CHART_IS_VISIBLE, pojo.getModelChartIsVisible())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_INDEX_ERROR_MESSAGE, pojo.getModelIndexErrorMessage())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_INDEX_RANGE_MESSAGE, pojo.getModelIndexRangeMessage())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_INDEX_ERROR_POLICY, pojo.getModelIndexErrorPolicy())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_INDEX_RANGE_POLICY, pojo.getModelIndexRangePolicy())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_OUTPUT_LABEL, pojo.getModelOutputLabel())
                .set(MODEL_OUTPUT_CHART_ORDER.MODEL_INDEX_RANGE_POLICY, pojo.getModelIndexRangePolicy())
                .where(MODEL_OUTPUT_CHART_ORDER.MODEL_OUTPUT_CHART_ORDER_PK.eq(pojo.getModelOutputChartOrderPK()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long id) {
        return dslContext.deleteFrom(MODEL_OUTPUT_CHART_ORDER)
                .where(MODEL_OUTPUT_CHART_ORDER.MODEL_OUTPUT_CHART_ORDER_PK.eq(id))
                .execute() > 0;
    }
}
