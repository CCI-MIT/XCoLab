package org.xcolab.jspTags.discussion;

import com.ext.portlet.NoSuchProposalException;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.enums.MemberRole;

import javax.portlet.PortletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

/**
 * Created by Klemens Mang on 05.03.14.
 */
public class GetRoleNameStartTag extends BodyTagSupport {

    private long userId;

    private long proposalId;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            User user = UserLocalServiceUtil.getUser(userId);
            MemberRole role = MemberRole.getHighestRole(user.getRoles());

            // Set the role string
            PortletRequest portletRequest = (PortletRequest) pageContext.getAttribute("javax.portlet.request", PageContext.REQUEST_SCOPE);
            if (portletRequest == null) {
                throw new JspException("Can't find portlet request");
            }
            pageContext.setAttribute("role", role);


            // Is the user contributing to the proposal?
            boolean isContributing = false;
            try{
                List<User> contributors = ProposalLocalServiceUtil.getMembers(proposalId);
                for (User contributor : contributors) {
                    if (contributor.getUserId() == userId) {
                        isContributing = true;
                        break;
                    }
                }
            } catch (NoSuchProposalException e){ /*User is not contributing because proposal does not exist, or proposalId = 0 when evaluating this for a contest */  }

            pageContext.setAttribute("isContributing", isContributing);

        } catch (PortalException | SystemException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }
}
