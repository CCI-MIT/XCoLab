package org.xcolab.client.contest.pojo.ontology;

import java.io.Serializable;

abstract class AbstractOntologySpace implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private String name;
    private String description;
    private Integer order_;

    public AbstractOntologySpace() {}

    public AbstractOntologySpace(AbstractOntologySpace value) {
        this.id_ = value.id_;
        this.name = value.name;
        this.description = value.description;
        this.order_ = value.order_;
    }

    public AbstractOntologySpace(Long id_, String name, String description, Integer order_) {
        this.id_ = id_;
        this.name = name;
        this.description = description;
        this.order_ = order_;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
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

    public Integer getOrder_() {
        return this.order_;
    }

    public void setOrder_(Integer order_) {
        this.order_ = order_;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((order_ == null) ? 0 : order_.hashCode());
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
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
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
        if (order_ == null) {
            if (other.order_ != null) {
                return false;
            }
        } else if (!order_.equals(other.order_)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "OntologySpace (" + id_ +
                ", " + name +
                ", " + description +
                ", " + order_ +
                ")";
    }
}
