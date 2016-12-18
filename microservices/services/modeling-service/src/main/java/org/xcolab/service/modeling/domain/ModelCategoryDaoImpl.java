package org.xcolab.service.modeling.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ModelCategory;
import org.xcolab.model.tables.records.ModelCategoryRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MODEL_CATEGORY;

@Repository
public class ModelCategoryDaoImpl implements ModelCategoryDao {

    private final DSLContext dslContext;

    @Autowired
    public ModelCategoryDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<ModelCategory> get(long id) {
        final Record record = dslContext.select()
                .from(MODEL_CATEGORY)
                .where(MODEL_CATEGORY.MODEL_CATEGORY_PK.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelCategory.class));
    }

    @Override
    public List<ModelCategory> list() {
        return dslContext.select()
                .from(MODEL_CATEGORY)
                .fetch().into(ModelCategory.class);
    }

    @Override
    public ModelCategory create(ModelCategory pojo) {
        final ModelCategoryRecord record = dslContext.insertInto(MODEL_CATEGORY)
                .set(MODEL_CATEGORY.MODEL_CATEGORY_DESCRIPTION, pojo.getModelCategoryDescription())
                .set(MODEL_CATEGORY.MODEL_CATEGORY_DISPLAY_WEIGHT,
                        pojo.getModelCategoryDisplayWeight())
                .set(MODEL_CATEGORY.MODEL_CATEGORY_NAME, pojo.getModelCategoryName())
                .returning()
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve id of inserted object");
        }
        pojo.setModelCategoryPK(record.getValue(MODEL_CATEGORY.MODEL_CATEGORY_PK));
        return pojo;
    }

    @Override
    public boolean update(ModelCategory pojo) {
        return dslContext.update(MODEL_CATEGORY)
                .set(MODEL_CATEGORY.MODEL_CATEGORY_DESCRIPTION, pojo.getModelCategoryDescription())
                .set(MODEL_CATEGORY.MODEL_CATEGORY_DISPLAY_WEIGHT,
                        pojo.getModelCategoryDisplayWeight())
                .set(MODEL_CATEGORY.MODEL_CATEGORY_NAME, pojo.getModelCategoryName())
                .where(MODEL_CATEGORY.MODEL_CATEGORY_PK.eq(pojo.getModelCategoryPK()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long id) {
        return dslContext.delete(MODEL_CATEGORY)
                .where(MODEL_CATEGORY.MODEL_CATEGORY_PK.eq(id))
                .execute() > 0;
    }
}
