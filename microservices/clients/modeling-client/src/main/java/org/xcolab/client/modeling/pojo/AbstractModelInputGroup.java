package org.xcolab.client.modeling.pojo;

import java.io.Serializable;

public abstract class AbstractModelInputGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long modelid;
    private Long nameanddescriptionmetadataid;
    private String name;
    private String description;
    private Integer displayitemorder;
    private String grouptype;
    private Long parentGroupId;

    public AbstractModelInputGroup() {
    }

    public AbstractModelInputGroup(AbstractModelInputGroup value) {
        this.id = value.id;
        this.modelid = value.modelid;
        this.nameanddescriptionmetadataid = value.nameanddescriptionmetadataid;
        this.name = value.name;
        this.description = value.description;
        this.displayitemorder = value.displayitemorder;
        this.grouptype = value.grouptype;
        this.parentGroupId = value.parentGroupId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelId() {
        return this.modelid;
    }

    public void setModelId(Long modelid) {
        this.modelid = modelid;
    }

    public Long getNameAndDescriptionMetaDataId() {
        return this.nameanddescriptionmetadataid;
    }

    public void setNameAndDescriptionMetaDataId(Long nameanddescriptionmetadataid) {
        this.nameanddescriptionmetadataid = nameanddescriptionmetadataid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDisplayItemOrder() {
        return this.displayitemorder;
    }

    public void setDisplayItemOrder(Integer displayitemorder) {
        this.displayitemorder = displayitemorder;
    }

    public String getGroupType() {
        return this.grouptype;
    }

    public void setGroupType(String grouptype) {
        this.grouptype = grouptype;
    }

    public Long getParentGroupId() {
        return this.parentGroupId;
    }

    public void setParentGroupId(Long parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((modelid == null) ? 0 : modelid.hashCode());
        result = prime * result + ((nameanddescriptionmetadataid == null) ? 0
                : nameanddescriptionmetadataid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((displayitemorder == null) ? 0 : displayitemorder.hashCode());
        result = prime * result + ((grouptype == null) ? 0 : grouptype.hashCode());
        result = prime * result + ((parentGroupId == null) ? 0 : parentGroupId.hashCode());
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
        final AbstractModelInputGroup other = (AbstractModelInputGroup) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (modelid == null) {
            if (other.modelid != null) {
                return false;
            }
        } else if (!modelid.equals(other.modelid)) {
            return false;
        }
        if (nameanddescriptionmetadataid == null) {
            if (other.nameanddescriptionmetadataid != null) {
                return false;
            }
        } else if (!nameanddescriptionmetadataid.equals(other.nameanddescriptionmetadataid)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (displayitemorder == null) {
            if (other.displayitemorder != null) {
                return false;
            }
        } else if (!displayitemorder.equals(other.displayitemorder)) {
            return false;
        }
        if (grouptype == null) {
            if (other.grouptype != null) {
                return false;
            }
        } else if (!grouptype.equals(other.grouptype)) {
            return false;
        }
        if (parentGroupId == null) {
            if (other.parentGroupId != null) {
                return false;
            }
        } else if (!parentGroupId.equals(other.parentGroupId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ModelInputGroup (" + id +
                ", " + modelid +
                ", " + nameanddescriptionmetadataid +
                ", " + name +
                ", " + description +
                ", " + displayitemorder +
                ", " + grouptype +
                ", " + parentGroupId +
                ")";
    }
}
