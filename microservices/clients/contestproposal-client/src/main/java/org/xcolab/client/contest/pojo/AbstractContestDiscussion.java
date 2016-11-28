package org.xcolab.client.contest.pojo;

public class AbstractContestDiscussion {

    protected Long discussionId;
    protected Long contestId;
    protected String tab;

    public AbstractContestDiscussion() {
    }

    public AbstractContestDiscussion(AbstractContestDiscussion value) {
        this.discussionId = value.discussionId;
        this.contestId = value.contestId;
        this.tab = value.tab;
    }

    public AbstractContestDiscussion(Long discussionId, Long contestId, String tab) {
        this.discussionId = discussionId;
        this.contestId = contestId;
        this.tab = tab;
    }

    public Long getDiscussionId() {
        return this.discussionId;
    }

    public void setDiscussionId(Long discussionId) {
        this.discussionId = discussionId;
    }

    public Long getContestId() {
        return this.contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getTab() {
        return this.tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((discussionId == null) ? 0 : discussionId.hashCode());
        result = prime * result + ((contestId == null) ? 0 : contestId.hashCode());
        result = prime * result + ((tab == null) ? 0 : tab.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContestDiscussion other = (ContestDiscussion) obj;
        if (discussionId == null) {
            if (other.discussionId != null) {
                return false;
            }
        } else if (!discussionId.equals(other.discussionId)) {
            return false;
        }
        if (contestId == null) {
            if (other.contestId != null) {
                return false;
            }
        } else if (!contestId.equals(other.contestId)) {
            return false;
        }
        if (tab == null) {
            if (other.tab != null) {
                return false;
            }
        } else if (!tab.equals(other.tab)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ContestDiscussion (" + discussionId +
                ", " + contestId +
                ", " + tab +
                ")";
    }
}
