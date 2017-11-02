package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CommentDto extends AbstractComment implements DataTransferObject<Comment> {

    public CommentDto() {
    }

    public CommentDto(AbstractComment comment) {
        super(comment);
    }

    @Override
    public Comment toPojo(ServiceNamespace serviceNamespace) {
        return new Comment(this, serviceNamespace);
    }
}
