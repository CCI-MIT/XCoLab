package org.xcolab.service.modeling.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.modeling.pojo.IModelCategory;
import org.xcolab.model.tables.pojos.ModelCategoryImpl;
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
    public Optional<IModelCategory> get(long id) {
        final Record record = dslContext.select()
                .from(MODEL_CATEGORY)
                .where(MODEL_CATEGORY.ID.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelCategoryImpl.class));
    }

    @Override
    public List<IModelCategory> list() {
        return dslContext.select()
                .from(MODEL_CATEGORY)
                .fetch().into(ModelCategoryImpl.class);
    }

    @Override
    public IModelCategory create(IModelCategory pojo) {
        final ModelCategoryRecord record = dslContext.insertInto(MODEL_CATEGORY)
                .set(MODEL_CATEGORY.DESCRIPTION, pojo.getDescription())
                .set(MODEL_CATEGORY.DISPLAY_WEIGHT, pojo.getDisplayWeight())
                .set(MODEL_CATEGORY.NAME, pojo.getName())
                .returning()
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve id of inserted object");
        }
        pojo.setId(record.getValue(MODEL_CATEGORY.ID));
        return pojo;
    }

    @Override
    public boolean update(IModelCategory pojo) {
        return dslContext.update(MODEL_CATEGORY)
                .set(MODEL_CATEGORY.DESCRIPTION, pojo.getDescription())
                .set(MODEL_CATEGORY.DISPLAY_WEIGHT, pojo.getDisplayWeight())
                .set(MODEL_CATEGORY.NAME, pojo.getName())
                .where(MODEL_CATEGORY.ID.eq(pojo.getId()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long id) {
        return dslContext.delete(MODEL_CATEGORY)
                .where(MODEL_CATEGORY.ID.eq(id))
                .execute() > 0;
    }
}
