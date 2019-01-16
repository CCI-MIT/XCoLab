package org.xcolab.client.comment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.client.comment.pojo.ICategoryGroup;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class CategoryClientMockImpl implements ICategoryClient {

    @Override
    public List<ICategory> listCategories(Integer startRecord, Integer limitRecord, String sort,
            Long authorUserId, Long groupId) {
        return Collections.emptyList();
    }

    @Override
    public ICategory getCategory(Long categoryId) throws CategoryNotFoundException {
        return null;
    }

    @Override
    public boolean updateCategory(ICategory category) {
        return false;
    }

    @Override
    public ICategory createCategory(ICategory category) {
        return null;
    }

    @Override
    public ICategoryGroup getCategoryGroup(Long groupId) throws CategoryGroupNotFoundException {
        return null;
    }
}
