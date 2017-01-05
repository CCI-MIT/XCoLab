package org.xcolab.view.taglibs.xcolab.jspTags.discussion.wrappers;

public class NewMessageWrapper {
    private String title;
    private String description;
    private String discussionId;
    private String threadId;
    private String uuid;
    
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
    public String getDiscussionId() {
        return discussionId;
    }
    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }
    public String getThreadId() {
        return threadId;
    }
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
    public String getUuid() {return uuid;}
    public void setUuid(String uuid) {this.uuid = uuid;}
}
