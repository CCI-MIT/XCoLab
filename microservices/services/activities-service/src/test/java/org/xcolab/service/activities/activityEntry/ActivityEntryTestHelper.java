package org.xcolab.service.activities.activityEntry;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.util.http.ServiceRequestUtils;

import java.util.ArrayList;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

public final class ActivityEntryTestHelper {

    public static void setupBasic() throws Exception {
        ServiceRequestUtils.setInitialized(true);
        PowerMockito.mockStatic(AdminClient.class);
        PowerMockito.mockStatic(ContestClientUtil.class);
        PowerMockito.mockStatic(CategoryClientUtil.class);
        PowerMockito.mockStatic(CommentClientUtil.class);
        PowerMockito.mockStatic(ThreadClientUtil.class);
        PowerMockito.mockStatic(MessagingClient.class);
        PowerMockito.mockStatic(MembersClient.class);
        PowerMockito.mockStatic(ProposalClientUtil.class);
        PowerMockito.mockStatic(ProposalAttributeClientUtil.class);

        Proposal proposal = Mockito.mock(Proposal.class);

        Mockito.when(proposal.getProposalLinkUrl(anyObject()))
                .thenReturn("");

        Mockito.when(ContestClientUtil.getContestType(anyLong()))
                .thenAnswer(invocation -> {
                    ContestType contestType = new ContestType();
                    contestType.setProposalName("");
                    return contestType;
                });

        Mockito.when(MembersClient.getMember(anyLong()))
                .thenAnswer(invocation -> {
                    Member member = new Member();
                    member.setScreenName("");
                    return member;
                });

        Mockito.when(ProposalAttributeClientUtil.getProposalAttribute(anyLong(),anyString(),anyObject()))
                .thenAnswer(invocation -> {
                    ProposalAttribute proposalAttribute = new ProposalAttribute();
                    proposalAttribute.setProposalId(1230L);

                    return proposalAttribute;

                });

        Mockito.when(ProposalClientUtil.getProposal(anyLong()))
                .thenAnswer(invocation -> {
                    proposal.setProposalId(1230L);
                    return proposal;

                });

        Mockito.when(ProposalClientUtil.getCurrentContestForProposal(anyLong()))
                .thenAnswer(invocation -> {
                    Contest contest = new Contest();
                    contest.setContestTypeId(213L);

                    return contest;
                });

        Mockito.when(CommentClientUtil
                .getComment(Mockito.anyLong()))
                .thenAnswer(invocation -> {
                    Comment c = new Comment();
                    c.setThreadId(1234L);
                    return c;
                });

        Mockito.when(ThreadClientUtil.getThread(Mockito.anyLong()))
                .thenAnswer(invocation -> {
                    CommentThread c = new CommentThread();
                    c.setCategoryId(-1234L);
                    c.setThreadId(1233L);
                    return c;
                });

        Mockito.when(ProposalClientUtil.getProposalByThreadId(Mockito.anyLong()))
                .thenAnswer(invocation -> {
                    proposal.setProposalId(1230L);
                    return proposal;
                });


        Mockito.when(CategoryClientUtil.getCategory(Mockito.anyLong()))
                .thenAnswer(invocation -> {
                    Category c = new Category();
                    c.setCategoryId(-1234L);
                    c.setName("a");
                    return c;
                });

        Mockito.when(ContestClientUtil.getAllContestTypes())
                .thenReturn(new ArrayList<>());

        Mockito.when(AdminClient.getConfigurationAttribute(anyString()))
                .thenReturn(new ConfigurationAttribute("COLAB_NAME", 0L, 0L,
                        "12312312332", 0d));
    }
}
