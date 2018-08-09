package org.xcolab.service.comments.domain.category;

import org.xcolab.model.tables.pojos.Category;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface CategoryDao {

    List<Category> findByGiven(PaginationHelper paginationHelper, Long groupId, Long authorUserId);

    Category get(long categoryId) throws NotFoundException;
    boolean update(Category category);
    Category create(Category category);
}
