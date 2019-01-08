package org.xcolab.service.comments.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.LastActivityNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.client.comment.pojo.ICategoryGroup;
import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.service.comments.domain.category.CategoryDao;
import org.xcolab.service.comments.domain.categorygroup.CategoryGroupDao;
import org.xcolab.service.comments.domain.comment.CommentDao;
import org.xcolab.service.comments.domain.thread.ThreadDao;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.ControllerUtils;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CommentController implements CommentClient, CategoryClient, ThreadClient {

    private final CommentDao commentDao;
    private final CategoryGroupDao groupDao;
    private final CategoryDao categoryDao;
    private final ThreadDao threadDao;

    @Autowired
    public CommentController(CategoryGroupDao groupDao, ThreadDao threadDao, CommentDao commentDao,
            CategoryDao categoryDao) {
        Assert.notNull(groupDao, "GroupDao bean is required");
        Assert.notNull(threadDao, "ThreadDao bean is required");
        Assert.notNull(commentDao, "CommentDao bean is required");
        Assert.notNull(categoryDao, "CategoryDao bean is required");
        this.groupDao = groupDao;
        this.threadDao = threadDao;
        this.commentDao = commentDao;
        this.categoryDao = categoryDao;
    }

    //TODO COLAB-2594: move /comments endpoint to "/threads/{threadId}/comments"
    @Override
    @RequestMapping(value = "/comments", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IComment> listComments(HttpServletResponse response,
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorUserId,
            @RequestParam(required = false) List<Long> threadIds,
            @RequestParam(required = false, defaultValue = "false") Boolean includeDeleted) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        if (response != null) {
            response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                    Integer.toString(commentDao.countByGiven(authorUserId, threadIds)));
        }

        return commentDao.findByGiven(paginationHelper, authorUserId, threadIds, includeDeleted);
    }

    @Override
    @GetMapping("/comments/{commentId}")
    public IComment getComment(@PathVariable Long commentId,
            @RequestParam(required = false, defaultValue = "false") Boolean includeDeleted)
            throws CommentNotFoundException {
        IComment comment = null;
        try {
            comment = commentDao.get(commentId);
        } catch (NotFoundException e) {
            throw new CommentNotFoundException(commentId);
        }
        if (comment.getDeletedAt() == null || includeDeleted) {
            return comment;
        } else {
            throw new CommentNotFoundException(commentId);
        }
    }

    @Override
    @PostMapping("/comments")
    public IComment createComment(@RequestBody IComment comment) {
        comment.setCreatedAt(new Timestamp(new Date().getTime()));
        return commentDao.create(comment);
    }

    @Override
    @DeleteMapping("/comments/{commentId}")
    public boolean deleteComment(@PathVariable Long commentId) {
        try {
            IComment comment = commentDao.get(commentId);
            comment.setDeletedAt(new Timestamp(new Date().getTime()));
            //If last comment in thread, delete thread
            if (commentDao.countByGiven(null, Collections.singletonList(comment.getThreadId()))
                    == 1) {
                IThread thread = threadDao.get(comment.getThreadId());
                thread.setDeletedAt(new Timestamp(new Date().getTime()));
                threadDao.update(thread);
            }
            return commentDao.update(comment);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException("Comment not found with id " + commentId);
        }
    }

    @Override
    @PutMapping("/comments")
    public boolean updateComment(@RequestBody IComment comment) {
        try {
            commentDao.get(comment.getId());
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "Comment not found with id " + comment.getId());
        }
        comment.setUpdatedAt(new Timestamp(new Date().getTime()));
        return commentDao.update(comment);
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
    public ICategoryGroup getCategoryGroup(@PathVariable Long groupId) throws CategoryGroupNotFoundException {
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

    @Override
    @RequestMapping(value = "/threads", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IThread> listThreads(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorUserId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long groupId) {

        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        return threadDao.findByGiven(paginationHelper, authorUserId, categoryId, groupId);
    }

    @Override
    @DeleteMapping("/threads/{threadId}")
    public boolean deleteThread(@PathVariable Long threadId) {
        boolean result = false;
        if (threadId != null) {
            result = threadDao.delete(threadId);
        }
        return result;
    }

    @Override
    @GetMapping("/threads/{threadId}")
    public IThread getThread(@PathVariable Long threadId) throws ThreadNotFoundException {
        try {
            return threadDao.get(threadId);
        } catch (NotFoundException e) {
            throw new ThreadNotFoundException(threadId);
        }
    }

    @Override
    @PostMapping("/threads")
    public IThread createThread(@RequestBody IThread thread) {
        thread.setCreatedAt(new Timestamp(new Date().getTime()));
        return threadDao.create(thread);
    }

    @Override
    @PutMapping("/threads/{threadId}")
    public boolean updateThread(@RequestBody IThread thread) {
        try {
            if (threadDao.get(thread.getId()) != null) {
                return threadDao.update(thread);
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException("Thread not found with id " + thread.getId());
    }

    @Override
    @GetMapping("/threads/{threadId}/lastActivityDate")
    public Date getLastActivityDate(@PathVariable Long threadId) {
        return threadDao.getLastComment(threadId)
                .map(IComment::getCreatedAt)
                .map(timestamp -> new Date(timestamp.getTime()))
                .orElse(new Date(0));
    }

    @Override
    @GetMapping("/threads/{threadId}/lastActivityAuthorUserId")
    public Long getLastActivityAuthorUserId(@PathVariable Long threadId) {
        return threadDao.getLastComment(threadId)
                .map(IComment::getAuthorUserId)
                .orElseThrow(() -> new LastActivityNotFoundException(threadId));
    }
}
