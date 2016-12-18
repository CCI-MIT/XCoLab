package org.xcolab.client.modeling.pojo;

public abstract class AbstractModelInputItem {

    private Long modelinputitempk;
    private Long modelid;
    private Long modelinputitemid;
    private Long modelgroupid;
    private Integer displayitemorder;
    private String type_;
    private String properties;

    public AbstractModelInputItem() {
    }

    public AbstractModelInputItem(AbstractModelInputItem value) {
        this.modelinputitempk = value.modelinputitempk;
        this.modelid = value.modelid;
        this.modelinputitemid = value.modelinputitemid;
        this.modelgroupid = value.modelgroupid;
        this.displayitemorder = value.displayitemorder;
        this.type_ = value.type_;
        this.properties = value.properties;
    }

    public Long getModelInputItemPK() {
        return this.modelinputitempk;
    }

    public void setModelInputItemPK(Long modelinputitempk) {
        this.modelinputitempk = modelinputitempk;
    }

    public Long getModelId() {
        return this.modelid;
    }

    public void setModelId(Long modelid) {
        this.modelid = modelid;
    }

    public Long getModelInputItemID() {
        return this.modelinputitemid;
    }

    public void setModelInputItemID(Long modelinputitemid) {
        this.modelinputitemid = modelinputitemid;
    }

    public Long getModelGroupId() {
        return this.modelgroupid;
    }

    public void setModelGroupId(Long modelgroupid) {
        this.modelgroupid = modelgroupid;
    }

    public Integer getDisplayItemOrder() {
        return this.displayitemorder;
    }

    public void setDisplayItemOrder(Integer displayitemorder) {
        this.displayitemorder = displayitemorder;
    }

    public String getType_() {
        return this.type_;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    public String getProperties() {
        return this.properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((modelinputitempk == null) ? 0 : modelinputitempk.hashCode());
        result = prime * result + ((modelid == null) ? 0 : modelid.hashCode());
        result = prime * result + ((modelinputitemid == null) ? 0 : modelinputitemid.hashCode());
        result = prime * result + ((modelgroupid == null) ? 0 : modelgroupid.hashCode());
        result = prime * result + ((displayitemorder == null) ? 0 : displayitemorder.hashCode());
        result = prime * result + ((type_ == null) ? 0 : type_.hashCode());
        result = prime * result + ((properties == null) ? 0 : properties.hashCode());
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
        final AbstractModelInputItem other = (AbstractModelInputItem) obj;
        if (modelinputitempk == null) {
            if (other.modelinputitempk != null) {
                return false;
            }
        } else if (!modelinputitempk.equals(other.modelinputitempk)) {
            return false;
        }
        if (modelid == null) {
            if (other.modelid != null) {
                return false;
            }
        } else if (!modelid.equals(other.modelid)) {
            return false;
        }
        if (modelinputitemid == null) {
            if (other.modelinputitemid != null) {
                return false;
            }
        } else if (!modelinputitemid.equals(other.modelinputitemid)) {
            return false;
        }
        if (modelgroupid == null) {
            if (other.modelgroupid != null) {
                return false;
            }
        } else if (!modelgroupid.equals(other.modelgroupid)) {
            return false;
        }
        if (displayitemorder == null) {
            if (other.displayitemorder != null) {
                return false;
            }
        } else if (!displayitemorder.equals(other.displayitemorder)) {
            return false;
        }
        if (type_ == null) {
            if (other.type_ != null) {
                return false;
            }
        } else if (!type_.equals(other.type_)) {
            return false;
        }
        if (properties == null) {
            if (other.properties != null) {
                return false;
            }
        } else if (!properties.equals(other.properties)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ModelInputItem (" + modelinputitempk +
                ", " + modelid +
                ", " + modelinputitemid +
                ", " + modelgroupid +
                ", " + displayitemorder +
                ", " + type_ +
                ", " + properties +
                ")";
    }
}
