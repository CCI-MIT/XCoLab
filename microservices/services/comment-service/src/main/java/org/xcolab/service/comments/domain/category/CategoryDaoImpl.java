package org.xcolab.service.comments.domain.category;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Category;
import org.xcolab.model.tables.records.CategoryRecord;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.commons.SortColumn;

import java.util.List;

import static org.xcolab.model.Tables.CATEGORY;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<Category> findByGiven(PaginationHelper paginationHelper,
            Long groupId, Long authorId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CATEGORY)
                .getQuery();

        if (authorId != null) {
            query.addConditions(CATEGORY.AUTHOR_ID.eq(authorId));
        }
        if (groupId != null) {
            query.addConditions(CATEGORY.GROUP_ID.eq(groupId));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "createDate":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CATEGORY.CREATE_DATE.asc() : CATEGORY.CREATE_DATE.desc());
                    break;
            }
        }
        query.addConditions(CATEGORY.DELETED_DATE.isNull());
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Category.class);
    }

    @Override
    public Category get(long categoryId) throws NotFoundException {
        final Record categoryRecord = dslContext.select()
                .from(CATEGORY)
                .where(CATEGORY.CATEGORY_ID.eq(categoryId))
                .fetchOne();
        if (categoryRecord == null) {
            throw new NotFoundException("Category with id " + categoryId + " does not exist");
        }
        return categoryRecord.into(Category.class);
    }

    @Override
    public boolean update(Category category) {
        return dslContext.update(CATEGORY)
                .set(CATEGORY.GROUP_ID, category.getGroupId())
                .set(CATEGORY.CREATE_DATE, category.getCreateDate())
                .set(CATEGORY.DESCRIPTION, category.getDescription())
                .set(CATEGORY.AUTHOR_ID, category.getAuthorId())
                .set(CATEGORY.NAME, category.getName())
                .set(CATEGORY.IS_QUIET, category.getIsQuiet())
                .set(CATEGORY.SORT, category.getSort())
                .set(CATEGORY.DELETED_DATE, category.getDeletedDate())
                .where(CATEGORY.CATEGORY_ID.equal(category.getCategoryId()))
                .execute() > 0;
    }

    @Override
    public Category create(Category category) {
        final CategoryRecord categoryRecord = dslContext.insertInto(CATEGORY)
                .set(CATEGORY.GROUP_ID, category.getGroupId())
                .set(CATEGORY.CREATE_DATE, category.getCreateDate())
                .set(CATEGORY.DESCRIPTION, category.getDescription())
                .set(CATEGORY.AUTHOR_ID, category.getAuthorId())
                .set(CATEGORY.NAME, category.getName())
                .set(CATEGORY.IS_QUIET, category.getIsQuiet())
                .set(CATEGORY.SORT, category.getSort())
                .set(CATEGORY.DELETED_DATE, category.getDeletedDate())
                .returning(CATEGORY.CATEGORY_ID)
                .fetchOne();
        if (categoryRecord != null) {
            category.setCategoryId(categoryRecord.getCategoryId());
            return category;
        }
        return null;
    }
}
