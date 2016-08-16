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
import org.xcolab.service.utils.ControllerUtils;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "/comments", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Comment> listComments(HttpServletResponse response,
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long threadId,
            @RequestParam(required = false, defaultValue = "false") boolean includeDeleted) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                    Integer.toString(commentDao.countByGiven(authorId, threadId)));

        return commentDao.findByGiven(paginationHelper, authorId, threadId, includeDeleted);
    }

    @RequestMapping(value = "/comments/countProposalsInContestPhases", method = {RequestMethod.GET})
    public Integer countProposalsInContestPhases(@RequestParam Long contestPhaseId){
        return commentDao.countProposalCommentsByContestPhase(contestPhaseId);
    }

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.GET)
    public Comment getComment(@PathVariable Long commentId,
            @RequestParam(required = false, defaultValue = "false") boolean includeDeleted)
            throws NotFoundException {
        final Comment comment = commentDao.get(commentId);
        if (comment.getDeletedDate() == null || includeDeleted) {
            return comment;
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public Comment createComment(@RequestBody Comment comment) {
        comment.setCreateDate(new Timestamp(new Date().getTime()));
        return commentDao.create(comment);
    }

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.DELETE)
    public boolean deleteComment(@PathVariable Long commentId) throws NotFoundException {
        Comment comment = commentDao.get(commentId);
        comment.setDeletedDate(new Timestamp(new Date().getTime()));
        return commentDao.update(comment);
    }

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.PUT)
    public boolean updateComment(@RequestBody Comment comment, @PathVariable Long commentId)
            throws NotFoundException {
        commentDao.get(commentId);
        comment.setModifiedDate(new Timestamp(new Date().getTime()));
        return commentDao.update(comment);
    }

    @RequestMapping(value = "/groups", method = {RequestMethod.GET, RequestMethod.HEAD})
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

    @RequestMapping(value = "/categories", method = {RequestMethod.GET, RequestMethod.HEAD})
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
        category.setCreateDate(new Timestamp(new Date().getTime()));
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

    @RequestMapping(value = "/threads", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Thread> listThreads(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long groupId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);

        return threadDao.findByGiven(paginationHelper, authorId, categoryId, groupId);
    }

    @RequestMapping(value = "/threads/{threadId}", method = RequestMethod.GET)
    public Thread getThread(@PathVariable Long threadId) throws NotFoundException {
        if (threadId == 0) {
            throw new NotFoundException("No category id given");
        } else {
            return threadDao.get(threadId);
        }
    }

    @RequestMapping(value = "/threads/{threadId}/getProposalIdForThread", method = RequestMethod.GET)
    public Long getProposalIdForThread(@PathVariable Long threadId) throws NotFoundException {
        if (threadId == 0) {
            throw new NotFoundException("No thread found for id given");
        } else {
            return threadDao.getProposalIdForThread(threadId);
        }
    }

    @RequestMapping(value = "/threads", method = RequestMethod.POST)
    public Thread createThread(@RequestBody Thread thread) {
        thread.setCreateDate(new Timestamp(new Date().getTime()));
        return threadDao.create(thread);
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

    @RequestMapping(value = "/threads/{threadId}/lastActivityDate", method = RequestMethod.GET)
    public Date getLastActivityDate(@PathVariable long threadId) throws NotFoundException {
        if (threadId == 0) {
            throw new NotFoundException("No category id given");
        } else {
            final Timestamp timestamp = threadDao.lastActivityDate(threadId);
//            return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            return timestamp.toLocalDateTime();
            return new Date(timestamp.getTime());
        }
    }

    @RequestMapping(value = "/threads/{threadId}/lastActivityAuthorId", method = RequestMethod.GET)
    public long getLastActivityAuthor(@PathVariable long threadId) throws NotFoundException {
        if (threadId == 0) {
            throw new NotFoundException("No category id given");
        } else {
            return threadDao.lastActivityAuthor(threadId);
        }
    }
}
