package org.xcolab.service.comments.domain.categorygroup;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.CategoryGroup;
import org.xcolab.model.tables.records.CategoryGroupRecord;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.xcolab.model.Tables.CATEGORY_GROUP;

@Repository
public class CategoryGroupDaoImpl implements CategoryGroupDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<CategoryGroup> findByGiven(PaginationHelper paginationHelper) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CATEGORY_GROUP)
                .getQuery();
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(CategoryGroup.class);
    }

    @Override
    public CategoryGroup get(long groupId) throws NotFoundException {
        final Record groupRecord = dslContext.select()
                .from(CATEGORY_GROUP)
                .where(CATEGORY_GROUP.ID.eq(groupId))
                .fetchOne();
        if (groupRecord == null) {
            throw new NotFoundException("CategoryGroup with id " + groupId + " does not exist");
        }
        return groupRecord.into(CategoryGroup.class);
    }

    @Override
    public boolean update(CategoryGroup group) {
        return dslContext.update(CATEGORY_GROUP)
                .set(CATEGORY_GROUP.IS_QUIET, group.getIsQuiet())
                .set(CATEGORY_GROUP.DESCRIPTION, group.getDescription())
                .set(CATEGORY_GROUP.URL, group.getUrl())
                .where(CATEGORY_GROUP.ID.equal(group.getId()))
                .execute() > 0;
    }

    @Override
    public CategoryGroup create(CategoryGroup group) {
        final CategoryGroupRecord groupRecord = dslContext.insertInto(CATEGORY_GROUP)
                .set(CATEGORY_GROUP.IS_QUIET, group.getIsQuiet())
                .set(CATEGORY_GROUP.DESCRIPTION, group.getDescription())
                .set(CATEGORY_GROUP.URL, group.getUrl())
                .returning(CATEGORY_GROUP.ID)
                .fetchOne();
        if (groupRecord != null) {
            group.setId(groupRecord.getId());
            return group;
        }
        return null;
    }
}
