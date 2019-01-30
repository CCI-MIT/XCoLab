package org.xcolab.service.modeling.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.modeling.pojo.IModelGlobalPreference;
import org.xcolab.model.tables.pojos.ModelGlobalPreferenceImpl;
import org.xcolab.model.tables.records.ModelGlobalPreferenceRecord;

import java.util.Optional;

import static org.xcolab.model.Tables.MODEL_GLOBAL_PREFERENCE;

@Repository
public class ModelGlobalPreferenceDaoImpl implements ModelGlobalPreferenceDao {

    private final DSLContext dslContext;

    @Autowired
    public ModelGlobalPreferenceDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public IModelGlobalPreference create(IModelGlobalPreference modelGlobalPreference) {
        final ModelGlobalPreferenceRecord record =
                dslContext.insertInto(MODEL_GLOBAL_PREFERENCE)
                        .set(MODEL_GLOBAL_PREFERENCE.MODEL_ID, modelGlobalPreference.getModelId())
                        .set(MODEL_GLOBAL_PREFERENCE.MODEL_CATEGORY_ID,
                                modelGlobalPreference.getModelCategoryId())
                        .set(MODEL_GLOBAL_PREFERENCE.USES_CUSTOM_INPUTS,
                                modelGlobalPreference.isUsesCustomInputs())
                        .set(MODEL_GLOBAL_PREFERENCE.CUSTOM_INPUTS_DEFINITION,
                                modelGlobalPreference.getCustomInputsDefinition())
                        .set(MODEL_GLOBAL_PREFERENCE.VISIBLE, modelGlobalPreference.isVisible())
                        .set(MODEL_GLOBAL_PREFERENCE.WEIGHT, modelGlobalPreference.getWeight())
                        .set(MODEL_GLOBAL_PREFERENCE.EXPERT_EVALUATION_PAGE_ID,
                                modelGlobalPreference.getExpertEvaluationPageId())
                        .returning(MODEL_GLOBAL_PREFERENCE.ID)
                        .fetchOne();
        if (record == null) {
            throw new IllegalStateException("could not retrieve inserted id");
        }
        modelGlobalPreference.setId(record.getValue(MODEL_GLOBAL_PREFERENCE.ID));
        return modelGlobalPreference;
    }

    @Override
    public Optional<IModelGlobalPreference> get(long id) {
        final Record record = dslContext.select()
                .from(MODEL_GLOBAL_PREFERENCE)
                .where(MODEL_GLOBAL_PREFERENCE.ID.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelGlobalPreferenceImpl.class));
    }

    @Override
    public Optional<IModelGlobalPreference> getByModelId(long modelId) {
        final Record record = dslContext.select()
                .from(MODEL_GLOBAL_PREFERENCE)
                .where(MODEL_GLOBAL_PREFERENCE.MODEL_ID.eq(modelId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelGlobalPreferenceImpl.class));
    }

    @Override
    public boolean update(IModelGlobalPreference pojo) {
        return dslContext.update(MODEL_GLOBAL_PREFERENCE)
                .set(MODEL_GLOBAL_PREFERENCE.MODEL_ID, pojo.getModelId())
                .set(MODEL_GLOBAL_PREFERENCE.MODEL_CATEGORY_ID,
                        pojo.getModelCategoryId())
                .set(MODEL_GLOBAL_PREFERENCE.USES_CUSTOM_INPUTS,
                        pojo.isUsesCustomInputs())
                .set(MODEL_GLOBAL_PREFERENCE.CUSTOM_INPUTS_DEFINITION,
                        pojo.getCustomInputsDefinition())
                .set(MODEL_GLOBAL_PREFERENCE.VISIBLE, pojo.isVisible())
                .set(MODEL_GLOBAL_PREFERENCE.WEIGHT, pojo.getWeight())
                .set(MODEL_GLOBAL_PREFERENCE.EXPERT_EVALUATION_PAGE_ID,
                        pojo.getExpertEvaluationPageId())
                .where(MODEL_GLOBAL_PREFERENCE.MODEL_ID.eq(pojo.getModelId()))
                .execute() > 0;
    }
}
