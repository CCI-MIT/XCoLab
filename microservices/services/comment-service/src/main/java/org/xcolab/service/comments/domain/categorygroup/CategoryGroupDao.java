package org.xcolab.service.comments.domain.categorygroup;

import org.xcolab.client.comment.pojo.ICategoryGroup;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface CategoryGroupDao {

    List<ICategoryGroup> findByGiven(PaginationHelper paginationHelper);

    ICategoryGroup get(long groupId) throws NotFoundException;

    boolean update(ICategoryGroup group);

    ICategoryGroup create(ICategoryGroup group);
}
