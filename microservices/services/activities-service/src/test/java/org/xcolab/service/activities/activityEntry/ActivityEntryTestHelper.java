package org.xcolab.service.activities.activityEntry;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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



    public static void setupBasic() throws Exception{
        ServiceRequestUtils.setInitialized(true);
        Proposal proposal;
        PowerMockito.mockStatic(AdminClient.class);
        PowerMockito.mockStatic(ContestClientUtil.class);
        PowerMockito.mockStatic(CategoryClientUtil.class);
        PowerMockito.mockStatic(CommentClientUtil.class);
        PowerMockito.mockStatic(ThreadClientUtil.class);
        PowerMockito.mockStatic(MessagingClient.class);
        PowerMockito.mockStatic(MembersClient.class);
        PowerMockito.mockStatic(ProposalClientUtil.class);
        PowerMockito.mockStatic(ProposalAttributeClientUtil.class);

        proposal = Mockito.mock(Proposal.class);
        Mockito.when(proposal.getProposalLinkUrl(anyObject()))
                .thenAnswer(new Answer<String>() {
                    @Override
                    public String answer(InvocationOnMock invocation)
                            throws Throwable {

                        return "";

                    }
                });
        Mockito.when(ContestClientUtil.getContestType(anyLong()))
                .thenAnswer(new Answer<ContestType>() {
                    @Override
                    public ContestType answer(InvocationOnMock invocation)
                            throws Throwable {
                        ContestType contestType = new ContestType();
                        contestType.setProposalName("");
                        return contestType;

                    }
                });
        Mockito.when(MembersClient.getMember(anyLong()))
                .thenAnswer(new Answer<Member>() {
                    @Override
                    public Member answer(InvocationOnMock invocation)
                            throws Throwable {
                        Member member = new Member();
                        member.setScreenName("");
                        return member;

                    }
                });

        Mockito.when(ProposalAttributeClientUtil.getProposalAttribute(anyLong(),anyString(),anyObject()))
                .thenAnswer(new Answer<ProposalAttribute>() {
                    @Override
                    public ProposalAttribute answer(InvocationOnMock invocation)
                            throws Throwable {
                        ProposalAttribute proposalAttribute = new ProposalAttribute();
                        proposalAttribute.setProposalId(1230l);

                        return proposalAttribute;

                    }
                });

        Mockito.when(ProposalClientUtil.getProposal(anyLong()))
                .thenAnswer(new Answer<Proposal>() {
                    @Override
                    public Proposal answer(InvocationOnMock invocation)
                            throws Throwable {
                        Proposal proposalz = proposal;
                        proposalz.setProposalId(1230l);

                        return proposalz;

                    }
                });

        Mockito.when(ProposalClientUtil.getCurrentContestForProposal(anyLong()))
                .thenAnswer(new Answer<Contest>() {
                    @Override
                    public Contest answer(InvocationOnMock invocation)
                            throws Throwable {
                        Contest contest = new Contest();
                        contest.setContestTypeId(213l);

                        return contest;

                    }
                });



        Mockito.when(CommentClientUtil
                .getComment(Mockito.anyLong()))
                .thenAnswer(new Answer<Comment>() {
                    @Override
                    public Comment answer(InvocationOnMock invocation)
                            throws Throwable {
                        Comment c = new Comment();
                        c.setThreadId(1234l);
                        return c;

                    }
                });

        Mockito.when(ThreadClientUtil.getThread(Mockito.anyLong()))
                .thenAnswer(new Answer<CommentThread>() {
                    @Override
                    public CommentThread answer(InvocationOnMock invocation)
                            throws Throwable {
                        CommentThread c = new CommentThread();
                        c.setCategoryId(-1234l);
                        c.setThreadId(1233l);
                        return c;

                    }
                });

        Mockito.when(ThreadClientUtil.getProposalIdForThread(Mockito.anyLong()))
                .thenAnswer(new Answer<Long>() {
                    @Override
                    public Long answer(InvocationOnMock invocation)
                            throws Throwable {
                        return 1230l;

                    }
                });


        Mockito.when(CategoryClientUtil.getCategory(Mockito.anyLong()))
                .thenAnswer(new Answer<Category>() {
                    @Override
                    public Category answer(InvocationOnMock invocation)
                            throws Throwable {
                        Category c = new Category();
                        c.setCategoryId(-1234l);
                        c.setName("a");
                        return c;

                    }
                });

        Mockito.when(ContestClientUtil.getAllContestTypes()).thenReturn(
                new ArrayList()
        );
        Mockito.when(AdminClient.getConfigurationAttribute(anyString()))
                .thenAnswer(new Answer<ConfigurationAttribute>() {
                    @Override
                    public ConfigurationAttribute answer(InvocationOnMock invocation)
                            throws Throwable {
                        return new ConfigurationAttribute("COLAB_NAME", 0l, 0l,
                                "12312312332", 0d);
                    }
                });
    }
}
