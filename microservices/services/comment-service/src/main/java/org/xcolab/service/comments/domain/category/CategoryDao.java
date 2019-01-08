package org.xcolab.service.comments.domain.category;

import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface CategoryDao {

    List<ICategory> findByGiven(PaginationHelper paginationHelper, Long groupId, Long authorUserId);

    ICategory get(long categoryId) throws NotFoundException;

    boolean update(ICategory category);

    ICategory create(ICategory category);
}
