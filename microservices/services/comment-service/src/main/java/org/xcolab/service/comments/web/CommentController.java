package org.xcolab.service.comments.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Category;
import org.xcolab.model.tables.pojos.CategoryGroup;
import org.xcolab.model.tables.pojos.Comment;
import org.xcolab.model.tables.pojos.Thread;
import org.xcolab.service.comments.domain.category.CategoryDao;
import org.xcolab.service.comments.domain.categorygroup.CategoryGroupDao;
import org.xcolab.service.comments.domain.comment.CommentDao;
import org.xcolab.service.comments.domain.thread.ThreadDao;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CategoryGroupDao groupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ThreadDao threadDao;

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public List<Comment> listComments(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long threadId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return commentDao.findByGiven(paginationHelper, authorId, threadId);
    }

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.GET)
    public Comment getComment(@PathVariable Long commentId) throws NotFoundException {
        if (commentId == 0) {
            throw new NotFoundException("No comment id given");
        } else {
            return commentDao.get(commentId);
        }
    }

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public Comment createComment(@RequestBody Comment comment) {
        return commentDao.create(comment);
    }

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.PUT)
    public boolean updateComment(@RequestBody Comment comment, @PathVariable Long commentId)
            throws NotFoundException {
        if (commentDao.get(commentId) != null) {
            return commentDao.update(comment);
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public List<CategoryGroup> listGroups(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, "");

        return groupDao.findByGiven(paginationHelper);
    }

    @RequestMapping(value = "/groups/{groupId}", method = RequestMethod.GET)
    public CategoryGroup getGroup(@PathVariable Long groupId) throws NotFoundException {
        if (groupId == 0) {
            throw new NotFoundException("No group id given");
        } else {
            return groupDao.get(groupId);
        }
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<Category> listCategories(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long groupId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return categoryDao.findByGiven(paginationHelper, groupId, authorId);
    }

    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable Long categoryId) throws NotFoundException {
        if (categoryId == 0) {
            throw new NotFoundException("No category id given");
        } else {
            return categoryDao.get(categoryId);
        }
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public Category createCategory(@RequestBody Category category) {
        return categoryDao.create(category);
    }

    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.PUT)
    public boolean updateCategory(@RequestBody Category category, @PathVariable Long categoryId)
            throws NotFoundException {
        if (categoryDao.get(categoryId) != null) {
            return categoryDao.update(category);
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/threads", method = RequestMethod.GET)
    public List<Thread> listThreads(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long categoryId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return threadDao.findByGiven(paginationHelper, authorId, categoryId);
    }

    @RequestMapping(value = "/threads/{threadId}", method = RequestMethod.GET)
    public Thread getThread(@PathVariable Long threadId) throws NotFoundException {
        if (threadId == 0) {
            throw new NotFoundException("No category id given");
        } else {
            return threadDao.get(threadId);
        }
    }

    @RequestMapping(value = "/threads", method = RequestMethod.POST)
    public Thread createThread(@RequestBody Thread category) {
        return threadDao.create(category);
    }

    @RequestMapping(value = "/threads/{threadId}", method = RequestMethod.PUT)
    public boolean updateThread(@RequestBody Thread category, @PathVariable Long threadId)
            throws NotFoundException {
        if (threadDao.get(threadId) != null) {
            return threadDao.update(category);
        } else {
            throw new NotFoundException();
        }
    }
}
