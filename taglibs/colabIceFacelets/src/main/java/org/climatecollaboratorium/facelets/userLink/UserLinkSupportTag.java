package org.climatecollaboratorium.facelets.userLink;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class UserLinkSupportTag extends TagHandler {
    private final TagAttribute userIdParam;

    private static Log _log = LogFactoryUtil.getLog(UserLinkSupportTag.class);

    public UserLinkSupportTag(TagConfig config) {
        super(config);
        userIdParam = this.getRequiredAttribute("userId");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, FaceletException,
            ELException {
        Long userId = (Long) userIdParam.getObject(ctx, Long.class);
        try {
            User user = UserLocalServiceUtil.getUser(userId);
            ctx.setAttribute("user", user);
            nextHandler.apply(ctx, parent);
        } catch (PortalException e) {
            // TODO Auto-generated catch block
            _log.error("There was an error when retrieving user for Id: " + userId, e);
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            _log.error("There was an error when retrieving user for Id: " + userId, e);
        }
    }

}
