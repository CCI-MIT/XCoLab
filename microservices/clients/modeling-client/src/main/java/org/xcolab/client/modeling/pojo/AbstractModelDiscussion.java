package org.xcolab.client.modeling.pojo;

import java.io.Serializable;

public abstract class AbstractModelDiscussion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long modeldiscussionid;
    private Long modelid;
    private Long categoryid;

    public AbstractModelDiscussion() {

    }

    public AbstractModelDiscussion(AbstractModelDiscussion value) {
        this.modeldiscussionid = value.modeldiscussionid;
        this.modelid = value.modelid;
        this.categoryid = value.categoryid;
    }

    public Long getModelDiscussionId() {
        return this.modeldiscussionid;
    }

    public void setModelDiscussionId(Long modeldiscussionid) {
        this.modeldiscussionid = modeldiscussionid;
    }

    public Long getModelId() {
        return this.modelid;
    }

    public void setModelId(Long modelid) {
        this.modelid = modelid;
    }

    public Long getCategoryId() {
        return this.categoryid;
    }

    public void setCategoryId(Long categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((modeldiscussionid == null) ? 0 : modeldiscussionid.hashCode());
        result = prime * result + ((modelid == null) ? 0 : modelid.hashCode());
        result = prime * result + ((categoryid == null) ? 0 : categoryid.hashCode());
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
        final AbstractModelDiscussion other = (AbstractModelDiscussion) obj;
        if (modeldiscussionid == null) {
            if (other.modeldiscussionid != null) {
                return false;
            }
        } else if (!modeldiscussionid.equals(other.modeldiscussionid)) {
            return false;
        }
        if (modelid == null) {
            if (other.modelid != null) {
                return false;
            }
        } else if (!modelid.equals(other.modelid)) {
            return false;
        }
        if (categoryid == null) {
            if (other.categoryid != null) {
                return false;
            }
        } else if (!categoryid.equals(other.categoryid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb =
                "ModelDiscussion (" + modeldiscussionid + ", " + modelid + ", " + categoryid + ")";

        return sb;
    }
}
