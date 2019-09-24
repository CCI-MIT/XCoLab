package org.xcolab.service.comment.domain.categorygroup;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.comment.pojo.ICategoryGroup;
import org.xcolab.client.comment.pojo.tables.pojos.CategoryGroup;
import org.xcolab.model.tables.records.CategoryGroupRecord;
import org.xcolab.service.comment.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.xcolab.model.Tables.CATEGORY_GROUP;

@Repository
public class CategoryGroupDaoImpl implements CategoryGroupDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<ICategoryGroup> findByGiven(PaginationHelper paginationHelper) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CATEGORY_GROUP)
                .getQuery();
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(CategoryGroup.class);
    }

    @Override
    public ICategoryGroup get(long groupId) throws NotFoundException {
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
    public boolean update(ICategoryGroup group) {
        return dslContext.update(CATEGORY_GROUP)
                .set(CATEGORY_GROUP.IS_QUIET, group.isIsQuiet())
                .set(CATEGORY_GROUP.DESCRIPTION, group.getDescription())
                .set(CATEGORY_GROUP.URL, group.getUrl())
                .where(CATEGORY_GROUP.ID.equal(group.getId()))
                .execute() > 0;
    }

    @Override
    public ICategoryGroup create(ICategoryGroup group) {
        final CategoryGroupRecord groupRecord = dslContext.insertInto(CATEGORY_GROUP)
                .set(CATEGORY_GROUP.IS_QUIET, group.isIsQuiet())
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
