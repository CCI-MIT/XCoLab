package org.climatecollaboratorium.facelets.discussions;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.climatecollaboratorium.facelets.discussions.permissions.DiscussionsPermissions;

import javax.el.ELException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;
import java.io.IOException;

public class DiscussionsSupportTag extends TagHandler {
    private final TagAttribute discussionBeanParam;
    private final TagAttribute discussionIdParam;
    private final TagAttribute permissionsParam;
    private final TagAttribute owningGroupIdParam;
    private final TagAttribute commentsParam;
    
    private static final Log _log = LogFactoryUtil.getLog(DiscussionsSupportTag.class);

    public DiscussionsSupportTag(TagConfig config) {
        super(config);
        discussionBeanParam = this.getRequiredAttribute("discussionBean");
        discussionIdParam = this.getRequiredAttribute("discussionId");
        owningGroupIdParam = this.getAttribute("groupId");
        permissionsParam = this.getAttribute("permissions");
        commentsParam = this.getAttribute("comments");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, ELException, FaceletException {

        DiscussionBean discussionBean = (DiscussionBean) discussionBeanParam.getObject(ctx);
        Long discussionId = (Long) discussionIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        Long owningGroupId = owningGroupIdParam == null ? null : (Long) owningGroupIdParam.getObject(ctx, Long.class);
        boolean comments = commentsParam != null && commentsParam.getBoolean(ctx);
        DiscussionsPermissions permissions = permissionsParam == null ? null : (DiscussionsPermissions) permissionsParam.getObject(ctx, DiscussionsPermissions.class);
        try {
            if (discussionBean.init(discussionId, owningGroupId, permissions, comments)) {
                // initialization succeded
                nextHandler.apply(ctx, parent);
            }
        }
        catch (SystemException | PortalException e) {
            _log.error(e);
            throw new FaceletException(e);
        }

    }
}
