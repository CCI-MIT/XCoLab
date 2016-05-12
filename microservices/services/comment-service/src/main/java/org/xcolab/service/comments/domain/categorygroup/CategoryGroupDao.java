package org.xcolab.service.comments.domain.categorygroup;

import org.xcolab.model.tables.pojos.CategoryGroup;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface CategoryGroupDao {

    List<CategoryGroup> findByGiven(PaginationHelper paginationHelper);

    CategoryGroup get(long groupId) throws NotFoundException;
    boolean update(CategoryGroup group);
    CategoryGroup create(CategoryGroup group);
}
