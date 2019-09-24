package org.xcolab.client.comment;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.IComment;

import java.util.Collections;
import java.util.List;

@FeignClient("xcolab-comment-service")
public interface ICommentClient {

    @GetMapping("/comments")
    List<IComment> listComments(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "limitRecord", required = false) Integer limitRecord,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "authorUserId", required = false) Long authorUserId,
            @RequestParam(value = "threadIds", required = false) List<Long> threadIds,
            @RequestParam(value = "includeDeleted", required = false, defaultValue = "false")
                    boolean includeDeleted);

    default List<IComment> listComments(Integer startRecord, Integer limitRecord, Long threadId) {
        return listComments(startRecord, limitRecord, "createdAt", null,
                Collections.singletonList(threadId), false);
    }

    @GetMapping("/count/comments")
    int countComments(@RequestParam(value = "authorUserId", required = false) Long authorUserId,
            @RequestParam(value = "threadIds", required = false) List<Long> threadIds);

    default int countComments(Long threadId) {
        if (threadId == null) {
            return 0;
        }
        return countComments(null, Collections.singletonList(threadId));
    }

    default int countComments(List<Long> threadIds) {
        if (CollectionUtils.isEmpty(threadIds)) {
            return 0;
        }
        return countComments(null, threadIds);
    }

    default int countCommentsByAuthor(long authorUserId) {
        return countComments(authorUserId, null);
    }

    default IComment getComment(long commentId) throws CommentNotFoundException {
        return getComment(commentId, false);
    }

    @GetMapping("/comments/{commentId}")
    IComment getComment(@PathVariable("commentId") Long commentId,
            @RequestParam(value = "includeDeleted", required = false, defaultValue = "false")
                    boolean includeDeleted)
            throws CommentNotFoundException;

    @PutMapping("/comments")
    boolean updateComment(IComment comment);

    @PostMapping("/comments")
    IComment createComment(@RequestBody IComment comment);

    @DeleteMapping("/comments/{commentId}")
    boolean deleteComment(@PathVariable("commentId") Long commentId);
}
