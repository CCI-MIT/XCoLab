package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.util.http.client.RestService;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CommentThreadDto extends AbstractCommentThread {

    public CommentThreadDto() {
    }

    public CommentThreadDto(AbstractCommentThread thread) {
        super(thread);
    }

    public CommentThread toPojo(RestService commentService) {
        return new CommentThread(this, commentService);
    }

    public static List<CommentThread> toPojos(List<CommentThreadDto> threadDtos, RestService commentService) {
        final List<CommentThread>  threads = new ArrayList<>(threadDtos.size());
        for (CommentThreadDto threadDto : threadDtos) {
            threads.add(threadDto.toPojo(commentService));
        }
        return threads;
    }
}
