package org.xcolab.client.comment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.comment.util.ThreadSortColumn;

import java.util.Date;
import java.util.List;

@FeignClient("xcolab-comment-service")
public interface IThreadClient {

    @GetMapping("/threads")
    List<IThread> listThreads(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "limitRecord", required = false) Integer limitRecord,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "authorUserId", required = false) Long authorUserId,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "groupId", required = false) Long groupId);

    default List<IThread> listThreads(Integer startRecord, Integer limitRecord, Long categoryId,
            Long groupId, ThreadSortColumn sortColumn, boolean ascending) {
        return listThreads(startRecord, limitRecord, sortColumn.getIdentifier(ascending), null,
                categoryId, groupId);
    }

    @GetMapping("/threads/{threadId}")
    IThread getThread(@PathVariable("threadId") Long threadId) throws ThreadNotFoundException;

    @PutMapping("/threads/{threadId}")
    boolean updateThread(@PathVariable Long threadId, @RequestBody IThread thread);

    @PostMapping("/threads")
    IThread createThread(@RequestBody IThread thread);

    @DeleteMapping("/threads/{threadId}")
    boolean deleteThread(@PathVariable("threadId") Long threadId);

    @GetMapping("/threads/{threadId}/lastActivityDate")
    Date getLastActivityDate(@PathVariable("threadId") Long threadId);

    @GetMapping("/threads/{threadId}/lastActivityAuthorUserId")
    Long getLastActivityAuthorUserId(@PathVariable("threadId") Long threadId);
}
