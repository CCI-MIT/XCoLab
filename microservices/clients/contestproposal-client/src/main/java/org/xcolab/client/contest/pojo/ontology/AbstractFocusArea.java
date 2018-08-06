package org.xcolab.client.contest.pojo.ontology;

import java.io.Serializable;

abstract class AbstractFocusArea implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer sortOrder;

    public AbstractFocusArea() {}

    public AbstractFocusArea(AbstractFocusArea value) {
        this.id = value.id;
        this.name = value.name;
        this.sortOrder = value.sortOrder;
    }

    public AbstractFocusArea(Long id, String name, Integer sortOrder) {
        this.id = id;
        this.name = name;
        this.sortOrder = sortOrder;
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

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((sortOrder == null) ? 0 : sortOrder.hashCode());
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
        final AbstractFocusArea other = (AbstractFocusArea) obj;
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
        if (sortOrder == null) {
            if (other.sortOrder != null) {
                return false;
            }
        } else if (!sortOrder.equals(other.sortOrder)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "FocusArea (" + id +
                ", " + name +
                ", " + sortOrder +
                ")";
    }
}
