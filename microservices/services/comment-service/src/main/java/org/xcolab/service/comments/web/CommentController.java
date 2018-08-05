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

import org.xcolab.model.tables.pojos.Category;
import org.xcolab.model.tables.pojos.CategoryGroup;
import org.xcolab.model.tables.pojos.Comment;
import org.xcolab.model.tables.pojos.Thread;
import org.xcolab.service.comments.domain.category.CategoryDao;
import org.xcolab.service.comments.domain.categorygroup.CategoryGroupDao;
import org.xcolab.service.comments.domain.comment.CommentDao;
import org.xcolab.service.comments.domain.thread.ThreadDao;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.ControllerUtils;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CommentController {

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
    @RequestMapping(value = "/comments", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Comment> listComments(HttpServletResponse response,
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorUserid,
            @RequestParam(required = false) List<Long> threadIds,
            @RequestParam(required = false, defaultValue = "false") boolean includeDeleted) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                Integer.toString(commentDao.countByGiven(authorUserid, threadIds)));

        return commentDao.findByGiven(paginationHelper, authorUserid, threadIds, includeDeleted);
    }

    @GetMapping("/comments/{commentId}")
    public Comment getComment(@PathVariable Long commentId,
            @RequestParam(required = false, defaultValue = "false") boolean includeDeleted)
            throws NotFoundException {
        final Comment comment = commentDao.get(commentId);
        if (comment.getDeletedAt() == null || includeDeleted) {
            return comment;
        } else {
            throw new NotFoundException();
        }
    }



    @PostMapping("/comments")
    public Comment createComment(@RequestBody Comment comment) {
        comment.setCreatedAt(new Timestamp(new Date().getTime()));
        return commentDao.create(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public boolean deleteComment(@PathVariable Long commentId) throws NotFoundException {
        Comment comment = commentDao.get(commentId);
        comment.setDeletedAt(new Timestamp(new Date().getTime()));
        //If last comment in thread, delete thread
        if(commentDao.countByGiven(null, Collections.singletonList(comment.getThreadId())) == 1) {
            Thread thread = threadDao.get(comment.getThreadId());
            thread.setDeletedAt(new Timestamp(new Date().getTime()));
            threadDao.update(thread);
        }
        return commentDao.update(comment);
    }

    @PutMapping("/comments/{commentId}")
    public boolean updateComment(@RequestBody Comment comment, @PathVariable Long commentId)
            throws NotFoundException {
        commentDao.get(commentId);
        comment.setUpdatedAt(new Timestamp(new Date().getTime()));
        return commentDao.update(comment);
    }

    @RequestMapping(value = "/groups", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<CategoryGroup> listGroups(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, "");

        return groupDao.findByGiven(paginationHelper);
    }

    @GetMapping("/groups/{groupId}")
    public CategoryGroup getGroup(@PathVariable Long groupId) throws NotFoundException {
        return groupDao.get(groupId);
    }

    @RequestMapping(value = "/categories", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Category> listCategories(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorUserid,
            @RequestParam(required = false) Long groupId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return categoryDao.findByGiven(paginationHelper, groupId, authorUserid);
    }

    @GetMapping("/categories/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) throws NotFoundException {
        return categoryDao.get(categoryId);
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        category.setCreatedAt(new Timestamp(new Date().getTime()));
        return categoryDao.create(category);
    }

    @PutMapping("/categories/{categoryId}")
    public boolean updateCategory(@RequestBody Category category, @PathVariable Long categoryId)
            throws NotFoundException {
        if (categoryDao.get(categoryId) != null) {
            return categoryDao.update(category);
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/threads", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Thread> listThreads(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorUserid,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long groupId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return threadDao.findByGiven(paginationHelper, authorUserid, categoryId, groupId);
    }

    @DeleteMapping("/threads/{threadId}")
    public boolean deleteThreads(@PathVariable Long threadId) {
        boolean result = false;
        if (threadId != null) {
            result = threadDao.delete(threadId);
        }
        return result;
    }

    @GetMapping("/threads/{threadId}")
    public Thread getThread(@PathVariable Long threadId) throws NotFoundException {
        return threadDao.get(threadId);
    }

    @PostMapping("/threads")
    public Thread createThread(@RequestBody Thread thread) {
        thread.setCreatedAt(new Timestamp(new Date().getTime()));
        return threadDao.create(thread);
    }

    @PutMapping("/threads/{threadId}")
    public boolean updateThread(@RequestBody Thread category, @PathVariable Long threadId)
            throws NotFoundException {
        if (threadDao.get(threadId) != null) {
            return threadDao.update(category);
        } else {
            throw new NotFoundException();
        }
    }

    @GetMapping("/threads/{threadId}/lastActivityDate")
    public Date getLastActivityDate(@PathVariable long threadId) {
        return threadDao.getLastComment(threadId)
                .map(Comment::getCreatedAt)
                .map(timestamp -> new Date(timestamp.getTime()))
                .orElse(new Date(0));
    }

    @GetMapping("/threads/{threadId}/lastActivityauthorUserid")
    public long getLastActivityAuthor(@PathVariable long threadId) throws NotFoundException {
        return threadDao.getLastComment(threadId)
                .map(Comment::getAuthorUserId)
                .orElseThrow(NotFoundException::new);
    }
}
