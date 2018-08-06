package org.xcolab.client.contest.pojo.ontology;

import java.io.Serializable;

abstract class AbstractOntologySpace implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Integer sortOrder;

    public AbstractOntologySpace() {}

    public AbstractOntologySpace(AbstractOntologySpace value) {
        this.id = value.id;
        this.name = value.name;
        this.description = value.description;
        this.sortOrder = value.sortOrder;
    }

    public AbstractOntologySpace(Long id, String name, String description, Integer sortOrder) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer order_) {
        this.sortOrder = order_;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        final AbstractOntologySpace other = (AbstractOntologySpace) obj;
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

        return "OntologySpace (" + id +
                ", " + name +
                ", " + description +
                ", " + sortOrder +
                ")";
    }
}
