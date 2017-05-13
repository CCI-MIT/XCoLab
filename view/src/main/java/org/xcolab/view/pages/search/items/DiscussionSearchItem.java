package org.xcolab.view.pages.search.items;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.search.pojo.SearchPojo;
import org.xcolab.util.exceptions.ReferenceResolutionException;

public class DiscussionSearchItem extends AbstractSearchItem {

    private CommentThread thread;
    private Comment comment;

    private String searchQuery;

    @Override
    public void init(SearchPojo pojo, String searchQuery) {
        this.searchQuery = searchQuery;
        try {
            comment = CommentClientUtil.getComment(pojo.getClassPrimaryKey());
            thread = comment.getThread();
        } catch (CommentNotFoundException e) {
            throw ReferenceResolutionException.toObject(Comment.class, pojo.getClassPrimaryKey())
                    .fromObject(SearchPojo.class, pojo.toString());
        }
    }

    @Override
    public String getPrintName() {
        return "Discussions";
    }

    @Override
    public String getTitle() {
        String title = thread.getTitle();
        if (StringUtils.isBlank(title)) {
            return "Comment";
        }
        return highlight(title, searchQuery);
    }

    @Override
    public String getLinkUrl() {
        String ret = thread.getLinkUrl();
        if (ret == null) {
            Long propId = ThreadClientUtil.getProposalIdForThread(thread.getThreadId());
            if (propId != null) {
                ret = ProposalClientUtil.getProposal(propId).getProposalDiscussionUrl();
            } else {
                Contest contest = ContestClientUtil.getContestByThreadId(thread.getThreadId());
                if (contest != null) {
                    ret = contest.getContestDiscussionLinkUrl();
                } else {
                    ret = "";
                }
            }
        }
        return ret;
    }

    @Override
    public String getContent() {
        return getContent(this.comment.getContent(), searchQuery);
    }

    @Override
    public boolean isVisible() {
        return ((!getLinkUrl().isEmpty()) && (!thread.getIsQuiet()));
    }
}
