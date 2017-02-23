package org.xcolab.view.pages.search.items;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.search.pojo.SearchPojo;

public class DiscussionSearchItem extends AbstractSearchItem {

    private CommentThread thread;
    private Comment comment;

    private String searchQuery;

    @Override
    public void init(SearchPojo pojo, String searchQuery) {
        try {
            comment = CommentClientUtil.getComment(pojo.getClassPrimaryKey());
            thread = ThreadClientUtil.getThread(comment.getThreadId());
            this.searchQuery = searchQuery;
        } catch (CommentNotFoundException | ThreadNotFoundException ignored) {

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
        return thread.getLinkUrl();
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
