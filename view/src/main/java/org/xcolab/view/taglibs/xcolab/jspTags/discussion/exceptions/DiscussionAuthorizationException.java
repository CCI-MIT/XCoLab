package org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Action not allowed")
public class DiscussionAuthorizationException extends DiscussionException {
    public DiscussionAuthorizationException(String message) {
        super(message);
    }
}
