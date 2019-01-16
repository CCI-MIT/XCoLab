package org.xcolab.service.comment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.client.comment.pojo.ICategoryGroup;
import org.xcolab.service.comment.domain.category.CategoryDao;
import org.xcolab.service.comment.domain.categorygroup.CategoryGroupDao;
import org.xcolab.service.comment.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class CategoryController implements ICategoryClient {

    private final CategoryGroupDao groupDao;
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryController(CategoryGroupDao groupDao, CategoryDao categoryDao) {
        Assert.notNull(groupDao, "GroupDao bean is required");
        Assert.notNull(categoryDao, "CategoryDao bean is required");
        this.groupDao = groupDao;
        this.categoryDao = categoryDao;
    }

    //TODO: what to do with this method?
    @RequestMapping(value = "/groups", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ICategoryGroup> listGroups(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, "");

        return groupDao.findByGiven(paginationHelper);
    }

    @Override
    @GetMapping("/groups/{groupId}")
    public ICategoryGroup getCategoryGroup(@PathVariable Long groupId)
            throws CategoryGroupNotFoundException {
        try {
            return groupDao.get(groupId);
        } catch (NotFoundException e) {
            throw new CategoryGroupNotFoundException(groupId);
        }
    }

    @Override
    @RequestMapping(value = "/categories", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ICategory> listCategories(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorUserId,
            @RequestParam(required = false) Long groupId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        return categoryDao.findByGiven(paginationHelper, groupId, authorUserId);
    }

    @Override
    @GetMapping("/categories/{categoryId}")
    public ICategory getCategory(@PathVariable Long categoryId) throws CategoryNotFoundException {
        try {
            return categoryDao.get(categoryId);
        } catch (NotFoundException e) {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    @Override
    @PostMapping("/categories")
    public ICategory createCategory(@RequestBody ICategory category) {
        category.setCreatedAt(new Timestamp(new Date().getTime()));
        return categoryDao.create(category);
    }

    @Override
    @PutMapping("/categories")
    public boolean updateCategory(@RequestBody ICategory category) {
        try {
            if (categoryDao.get(category.getId()) != null) {
                return categoryDao.update(category);
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException("Category not found with id " + category.getId());
    }
}
