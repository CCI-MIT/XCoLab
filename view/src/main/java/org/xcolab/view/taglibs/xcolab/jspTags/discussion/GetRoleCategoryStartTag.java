package org.xcolab.view.taglibs.xcolab.jspTags.discussion;

import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.MemberCategoryWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.ReferenceResolutionException;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class GetRoleCategoryStartTag extends BodyTagSupport {

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

    private IProposalClient proposalClient = StaticProposalContext.getProposalClient();

    @Override
    public int doStartTag() throws JspException {
        try {
            UserWrapper member = StaticUserContext.getUserClient().getMember(userId);
            MemberCategoryWrapper roleCategory = StaticUserContext.getUserClient().getHighestCategory(member.getRoles());
            pageContext.setAttribute("roleCategory", roleCategory);

            // Is the user contributing to the proposal?
            boolean isContributing = false;
            if (proposalId > 0) {
                List<UserWrapper> contributors = proposalClient.getProposalMembers(proposalId);
                for (UserWrapper contributor : contributors) {
                    if (contributor.getId() == userId) {
                        isContributing = true;
                        break;
                    }
                }
            }
            pageContext.setAttribute("isContributing", isContributing);

        } catch (MemberNotFoundException e) {
            throw ReferenceResolutionException.toObject(UserWrapper.class, userId)
                    .fromObject(GetRoleCategoryStartTag.class,
                            "user id " + userId + " proposalId " + proposalId);
        }
        return EVAL_BODY_INCLUDE;
    }
}
