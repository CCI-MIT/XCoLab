package org.xcolab.jspTags.discussion.wrappers;

public class NewMessageWrapper {
    private String title;
    private String description;
    private long discussionId;
    private long threadId;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public long getDiscussionId() {
        return discussionId;
    }
    public void setDiscussionId(long discussionId) {
        this.discussionId = discussionId;
    }
    public long getThreadId() {
        return threadId;
    }
    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

}
