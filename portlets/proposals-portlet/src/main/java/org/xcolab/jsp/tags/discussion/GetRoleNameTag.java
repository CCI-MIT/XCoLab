package org.xcolab.jsp.tags.discussion;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.jsp.tags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.jsp.tags.discussion.wrappers.NewMessageWrapper;
import org.xcolab.portlets.members.MemberCategory;

import javax.portlet.PortletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

/**
 * Created by Mente on 05.03.14.
 */
public class GetRoleNameTag extends BodyTagSupport {

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            User user = UserLocalServiceUtil.getUser(userId);
            List<Role> roles = user.getRoles();

            // Determine the highest role of the user (copied from {@link org.xcolab.portlets.members.MemberListItemBean})
            MemberCategory currentRole = MemberCategory.MEMBER;
            MemberCategory role = MemberCategory.MEMBER;

            for (Role r: roles) {
                final String roleString = r.getName();
                currentRole = MemberCategory.valueOf(roleString.toUpperCase());
                if (currentRole.ordinal() > role.ordinal()) {
                    role = currentRole;
                }
            }

            if (role == MemberCategory.MODERATOR) role = MemberCategory.STAFF;


            // Set the role string
            PortletRequest portletRequest = (PortletRequest) pageContext.getAttribute("javax.portlet.request", PageContext.REQUEST_SCOPE);
            if (portletRequest == null) {
                throw new JspException("Can't find portlet request");
            }
            pageContext.setAttribute("role", role);
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }
}
