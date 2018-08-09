package org.xcolab.service.modeling.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ModelInputGroup;
import org.xcolab.model.tables.records.ModelInputGroupRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MODEL_INPUT_GROUP;

@Repository
public class ModelInputGroupDaoImpl implements ModelInputGroupDao {

    private final DSLContext dslContext;

    @Autowired
    public ModelInputGroupDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<ModelInputGroup> get(long id) {
        final Record record = dslContext.select()
                .from(MODEL_INPUT_GROUP)
                .where(MODEL_INPUT_GROUP.ID.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ModelInputGroup.class));
    }

    @Override
    public List<ModelInputGroup> list() {
        return dslContext.select()
                .from(MODEL_INPUT_GROUP)
                .fetch().into(ModelInputGroup.class);
    }

    @Override
    public List<ModelInputGroup> findByGiven(Long parentGroupPk, Long modelId) {
        final SelectQuery<Record> query = dslContext.select().from(MODEL_INPUT_GROUP)
                .getQuery();

        if (parentGroupPk != null) {
            query.addConditions(MODEL_INPUT_GROUP.PARENT_GROUP_ID.eq(parentGroupPk));
        }

        if (modelId != null) {
            query.addConditions(MODEL_INPUT_GROUP.MODEL_ID.eq(modelId));
        }

        return query.fetch().into(ModelInputGroup.class);
    }

    @Override
    public ModelInputGroup create(ModelInputGroup pojo) {
        final ModelInputGroupRecord record = dslContext.insertInto(MODEL_INPUT_GROUP)
                .set(MODEL_INPUT_GROUP.MODEL_ID, pojo.getModelId())
                .set(MODEL_INPUT_GROUP.NAME, pojo.getName())
                .set(MODEL_INPUT_GROUP.PARENT_GROUP_ID, pojo.getParentGroupId())
                .set(MODEL_INPUT_GROUP.NAME_AND_DESCRIPTION_META_DATA_ID, pojo.getNameAndDescriptionMetaDataId())
                .set(MODEL_INPUT_GROUP.DESCRIPTION, pojo.getDescription())
                .set(MODEL_INPUT_GROUP.DISPLAY_ITEM_ORDER, pojo.getDisplayItemOrder())
                .set(MODEL_INPUT_GROUP.GROUP_TYPE, pojo.getGroupType())
                .returning()
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve id of inserted object");
        }
        pojo.setId(record.getValue(MODEL_INPUT_GROUP.ID));
        return pojo;
    }

    @Override
    public boolean update(ModelInputGroup pojo) {
        return dslContext.update(MODEL_INPUT_GROUP)
                .set(MODEL_INPUT_GROUP.MODEL_ID, pojo.getModelId())
                .set(MODEL_INPUT_GROUP.NAME, pojo.getName())
                .set(MODEL_INPUT_GROUP.PARENT_GROUP_ID, pojo.getParentGroupId())
                .set(MODEL_INPUT_GROUP.NAME_AND_DESCRIPTION_META_DATA_ID, pojo.getNameAndDescriptionMetaDataId())
                .set(MODEL_INPUT_GROUP.DESCRIPTION, pojo.getDescription())
                .set(MODEL_INPUT_GROUP.DISPLAY_ITEM_ORDER, pojo.getDisplayItemOrder())
                .set(MODEL_INPUT_GROUP.GROUP_TYPE, pojo.getGroupType())
                .where(MODEL_INPUT_GROUP.ID.eq(pojo.getId()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long id) {
        return dslContext.deleteFrom(MODEL_INPUT_GROUP)
                .where(MODEL_INPUT_GROUP.ID.eq(id))
                .execute() > 0;
    }
}
