package org.climatecollaboratorium.facelets.discussions;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import org.climatecollaboratorium.facelets.discussions.permissions.DiscussionsPermissions;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.sun.facelets.FaceletContext;
import com.sun.facelets.FaceletException;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.TagHandler;

public class DiscussionsSupportTag extends TagHandler{
    private final TagAttribute discussionBeanParam;
    private final TagAttribute discussionIdParam;
    private final TagAttribute permissionsParam;
    private final TagAttribute owningGroupIdParam;
    private final TagAttribute commentsParam;
    
    private static Log _log = LogFactoryUtil.getLog(DiscussionsSupportTag.class);

    public DiscussionsSupportTag(TagConfig config) {
        super(config);
        discussionBeanParam = this.getRequiredAttribute("discussionBean");
        discussionIdParam = this.getRequiredAttribute("discussionId");
        owningGroupIdParam = this.getAttribute("groupId");
        permissionsParam = this.getAttribute("permissions");
        commentsParam = this.getAttribute("comments");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, FaceletException,
            ELException {

        DiscussionBean discussionBean = (DiscussionBean) discussionBeanParam.getObject(ctx);
        Long discussionId = (Long) discussionIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        Long owningGroupId = owningGroupIdParam == null ? null : (Long) owningGroupIdParam.getObject(ctx, Long.class);
        Boolean comments = commentsParam != null ? commentsParam.getBoolean(ctx) : false;
        DiscussionsPermissions permissions = permissionsParam == null ? null : (DiscussionsPermissions) permissionsParam.getObject(ctx, DiscussionsPermissions.class);
        try {
            if (discussionBean.init(discussionId, owningGroupId, permissions, comments)) {
                // initialization succeded
                nextHandler.apply(ctx, parent);
            }
        }
        catch (SystemException e) {
            _log.error(e);
            throw new FaceletException(e);
        } catch (PortalException e) {
            // TODO Auto-generated catch block
            _log.error(e);
            throw new FaceletException(e);
        }
        
    }
    
    

}
