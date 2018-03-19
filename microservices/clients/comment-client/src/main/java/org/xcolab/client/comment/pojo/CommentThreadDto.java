package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DataTransferObject;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CommentThreadDto extends AbstractCommentThread
        implements DataTransferObject<CommentThread> {

    public CommentThreadDto() {
    }

    public CommentThreadDto(AbstractCommentThread thread) {
        super(thread);
    }

    @Override
    public CommentThread toPojo(ServiceNamespace serviceNamespace) {
        return new CommentThread(this, serviceNamespace);
    }
}
