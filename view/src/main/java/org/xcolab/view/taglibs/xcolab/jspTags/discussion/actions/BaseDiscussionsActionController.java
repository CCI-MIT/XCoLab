package org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions;

import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseDiscussionsActionController {

    public void checkPermissions(HttpServletRequest request, String accessDeniedMessage,
            long additionalId) throws DiscussionAuthorizationException {

        DiscussionPermissions permissions = new DiscussionPermissions(request);

        try {
            if (!isUserAllowed(permissions, additionalId)) {
                throw new DiscussionAuthorizationException(accessDeniedMessage);
            }
        } catch (CommentNotFoundException e) {
            throw new DiscussionAuthorizationException(accessDeniedMessage);
        }
    }

    public abstract boolean isUserAllowed(DiscussionPermissions permissions, long additionalId)
            throws CommentNotFoundException;
}
