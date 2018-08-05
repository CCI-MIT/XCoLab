package org.xcolab.client.modeling.pojo;

import java.io.Serializable;

public abstract class AbstractModelCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Integer displayWeight;

    public AbstractModelCategory() {
    }

    public AbstractModelCategory(AbstractModelCategory value) {
        this.id = value.id;
        this.name = value.name;
        this.description = value.description;
        this.displayWeight = value.displayWeight;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getDisplayWeight() {
        return this.displayWeight;
    }

    public void setDisplayWeight(Integer displayWeight) {
        this.displayWeight = displayWeight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0
                : description.hashCode());
        result = prime * result + ((displayWeight == null) ? 0
                : displayWeight.hashCode());
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
        final AbstractModelCategory other = (AbstractModelCategory) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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
        if (displayWeight == null) {
            if (other.displayWeight != null) {
                return false;
            }
        } else if (!displayWeight.equals(other.displayWeight)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb = "ModelCategory (" + id + ", " + name + ", "
                + description + ", " + displayWeight + ")";

        return sb;
    }
}
