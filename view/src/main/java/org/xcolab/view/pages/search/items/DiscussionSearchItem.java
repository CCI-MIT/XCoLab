package org.xcolab.view.pages.search.items;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.comment.pojo.tables.pojos.Comment;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.search.pojo.ISearchPojo;
import org.xcolab.commons.exceptions.ReferenceResolutionException;

public class DiscussionSearchItem extends AbstractSearchItem {

    private static final Logger _log = LoggerFactory.getLogger(DiscussionSearchItem.class);

    private IThread thread;
    private IComment comment;

    private String searchQuery;

    @Override
    public void init(ISearchPojo pojo, String searchQuery) {
        this.searchQuery = searchQuery;
        try {
            comment = StaticCommentContext.getCommentClient().getComment(pojo.getClassPrimaryKey());
            thread = StaticCommentContext.getThreadClient().getThread(comment.getThreadId());
        } catch (CommentNotFoundException | ThreadNotFoundException e) {
            throw ReferenceResolutionException.toObject(Comment.class, pojo.getClassPrimaryKey())
                    .build();
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
            ret = getProposalDiscussionUrl();
        }
        if (ret == null) {
            ret = getContestDiscussionUrl();
        }
        if (ret == null) {
            _log.error("URL for thread {} not resolvable", thread.getId());
            return "";
        }
        return ret;
    }

    private String getProposalDiscussionUrl() {
        try {
            return new ProposalWrapper(
                    StaticProposalContext.getProposalClient().getProposalByThreadId(thread.getId()))
                    .getProposalDiscussionUrl();
        } catch (ProposalNotFoundException e) {
            return null;
        }
    }

    private String getContestDiscussionUrl() {
        try {
            ContestWrapper contest = new ContestWrapper(StaticContestContext.getContestClient()
                    .getContestByThreadId(thread.getId()));
            return contest.getContestDiscussionLinkUrl();
        } catch (ContestNotFoundException e1) {
            return null;
        }
    }

    @Override
    public String getContent() {
        return getContent(this.comment.getContent(), searchQuery);
    }

    @Override
    public boolean isVisible() {
        return StringUtils.isNotEmpty(getLinkUrl()) && !thread.isIsQuiet();
    }
}
