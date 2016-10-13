package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.util.http.client.RestService;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CommentDto extends AbstractComment {

    public CommentDto() {
    }

    public CommentDto(AbstractComment comment) {
        super(comment);
    }

    public Comment toPojo(RestService commentService) {
        return new Comment(this, commentService);
    }

    public static List<Comment> toPojos(List<CommentDto> commentDtos, RestService commentService) {
        final List<Comment>  comments = new ArrayList<>(commentDtos.size());
        for (CommentDto commentDto : commentDtos) {
            comments.add(commentDto.toPojo(commentService));
        }
        return comments;
    }
}
