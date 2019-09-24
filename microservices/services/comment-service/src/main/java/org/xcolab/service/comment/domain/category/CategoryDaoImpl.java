package org.xcolab.service.comment.domain.category;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.client.comment.pojo.tables.pojos.Category;
import org.xcolab.commons.SortColumn;
import org.xcolab.model.tables.records.CategoryRecord;
import org.xcolab.service.comment.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.xcolab.model.Tables.CATEGORY;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<ICategory> findByGiven(PaginationHelper paginationHelper,
            Long groupId, Long authorUserId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CATEGORY)
                .getQuery();

        if (authorUserId != null) {
            query.addConditions(CATEGORY.AUTHOR_USER_ID.eq(authorUserId));
        }
        if (groupId != null) {
            query.addConditions(CATEGORY.GROUP_ID.eq(groupId));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "createdAt":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CATEGORY.CREATED_AT.asc() : CATEGORY.CREATED_AT.desc());
                    break;
            }
        }
        query.addConditions(CATEGORY.DELETED_AT.isNull());
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Category.class);
    }

    @Override
    public ICategory get(long categoryId) throws NotFoundException {
        final Record categoryRecord = dslContext.select()
                .from(CATEGORY)
                .where(CATEGORY.ID.eq(categoryId))
                .fetchOne();
        if (categoryRecord == null) {
            throw new NotFoundException("Category with id " + categoryId + " does not exist");
        }
        return categoryRecord.into(Category.class);
    }

    @Override
    public boolean update(ICategory category) {
        return dslContext.update(CATEGORY)
                .set(CATEGORY.GROUP_ID, category.getId())
                .set(CATEGORY.CREATED_AT, category.getCreatedAt())
                .set(CATEGORY.DESCRIPTION, category.getDescription())
                .set(CATEGORY.AUTHOR_USER_ID, category.getAuthorUserId())
                .set(CATEGORY.NAME, category.getName())
                .set(CATEGORY.IS_QUIET, category.isIsQuiet())
                .set(CATEGORY.SORT, category.getSort())
                .set(CATEGORY.DELETED_AT, category.getDeletedAt())
                .where(CATEGORY.ID.equal(category.getId()))
                .execute() > 0;
    }

    @Override
    public ICategory create(ICategory category) {
        final CategoryRecord categoryRecord = dslContext.insertInto(CATEGORY)
                .set(CATEGORY.ID, category.getId())
                .set(CATEGORY.CREATED_AT, category.getCreatedAt())
                .set(CATEGORY.DESCRIPTION, category.getDescription())
                .set(CATEGORY.AUTHOR_USER_ID, category.getAuthorUserId())
                .set(CATEGORY.NAME, category.getName())
                .set(CATEGORY.IS_QUIET, category.isIsQuiet())
                .set(CATEGORY.SORT, category.getSort())
                .set(CATEGORY.DELETED_AT, category.getDeletedAt())
                .returning(CATEGORY.ID)
                .fetchOne();
        if (categoryRecord != null) {
            category.setId(categoryRecord.getId());
            return category;
        }
        return null;
    }
}
