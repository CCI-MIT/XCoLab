package org.xcolab.jspTags.discussion;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.enums.MemberRole;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class GetRoleNameStartTag extends BodyTagSupport {

    private long userId;

    private long proposalId;

    private long contestId;


    public long getContestId() {
        return contestId;
    }

    public void setContestId(long contestId) {
        this.contestId = contestId;
    }


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

    private ProposalClient proposalClient;

    @Override
    public int doStartTag() throws JspException {
        try {
            Member member = MembersClient.getMember(userId);
            MemberRole role = MemberRole.getHighestRole(member.getRoles());
            if(contestId > 0){
                try{
                    Contest c = ContestClientUtil.getContest(contestId);
                    if(c.getIsSharedContestInForeignColab()){
                        RestService proposalsService = new RefreshingRestService("proposals-service",
                                ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                                ConfigurationAttributeKey.PARTNER_COLAB_PORT);
                        proposalClient = ProposalClient.fromService(proposalsService);
                    }
                }catch (ContestNotFoundException cnfe){
                    proposalClient = ProposalClientUtil.getClient();
                }
            }else{
                proposalClient = ProposalClientUtil.getClient();
            }
            pageContext.setAttribute("role", role);

            // Is the user contributing to the proposal?
            boolean isContributing = false;
            if (proposalId > 0) {
                List<Member> contributors = proposalClient.getProposalMembers(proposalId);
                for (Member contributor : contributors) {
                    if (contributor.getUserId() == userId) {
                        isContributing = true;
                        break;
                    }
                }
            }
            pageContext.setAttribute("isContributing", isContributing);


        } catch (MemberNotFoundException e) {
            throw ReferenceResolutionException.toObject(Member.class, userId)
                    .fromObject(GetRoleNameStartTag.class,
                            "user id " + userId + " proposalId " + proposalId);
        }
        return EVAL_BODY_INCLUDE;
    }
}
