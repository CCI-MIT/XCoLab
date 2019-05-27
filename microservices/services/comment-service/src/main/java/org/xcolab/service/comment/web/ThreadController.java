package org.xcolab.service.comment.web;

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

import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.exceptions.LastActivityNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.service.comment.domain.thread.ThreadDao;
import org.xcolab.service.comment.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class ThreadController implements IThreadClient {

    private final ThreadDao threadDao;

    @Autowired
    public ThreadController(ThreadDao threadDao) {
        Assert.notNull(threadDao, "ThreadDao bean is required");
        this.threadDao = threadDao;
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
        //Metrics.counter("comment-service","function","listThreads").increment();
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        return threadDao.findByGiven(paginationHelper, authorUserId, categoryId, groupId);
    }

    @Override
    @DeleteMapping("/threads/{threadId}")
    public boolean deleteThread(@PathVariable Long threadId) {
        //Metrics.counter("comment-service","function","deleteThread").increment();
        boolean result = false;
        if (threadId != null) {
            result = threadDao.delete(threadId);
        }
        return result;
    }

    @Override
    @GetMapping("/threads/{threadId}")
    public IThread getThread(@PathVariable Long threadId) throws ThreadNotFoundException {
        //Metrics.counter("comment-service","function","deleteThread").increment();
        try {
            return threadDao.get(threadId);
        } catch (NotFoundException e) {
            throw new ThreadNotFoundException(threadId);
        }
    }

    @Override
    @PostMapping("/threads")
    public IThread createThread(@RequestBody IThread thread) {
        //Metrics.counter("comment-service","function","createThread").increment();
        thread.setCreatedAt(new Timestamp(new Date().getTime()));
        return threadDao.create(thread);
    }

    @Override
    @PutMapping("/threads/{threadId}")
    public boolean updateThread(@PathVariable Long threadId, @RequestBody IThread thread) {
        //Metrics.counter("comment-service","function","updateThread").increment();
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
        //Metrics.counter("comment-service","function","getLastActivityDate").increment();
        return threadDao.getLastComment(threadId)
                .map(IComment::getCreatedAt)
                .map(timestamp -> new Date(timestamp.getTime()))
                .orElse(new Date(0));
    }

    @Override
    @GetMapping("/threads/{threadId}/lastActivityAuthorUserId")
    public Long getLastActivityAuthorUserId(@PathVariable Long threadId) {
        //Metrics.counter("comment-service","function","getLastActivityAuthorUserId").increment();
        return threadDao.getLastComment(threadId)
                .map(IComment::getAuthorUserId)
                .orElseThrow(() -> new LastActivityNotFoundException(threadId));
    }
}
