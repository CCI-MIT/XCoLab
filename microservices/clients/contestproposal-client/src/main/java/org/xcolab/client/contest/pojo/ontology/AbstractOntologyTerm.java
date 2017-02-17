package org.xcolab.client.contest.pojo.ontology;

import java.io.Serializable;

abstract class AbstractOntologyTerm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private Long parentid;
    private Long ontologyspaceid;
    private String name;
    private String descriptionurl;
    private Integer order_;

    public AbstractOntologyTerm() {}

    public AbstractOntologyTerm(AbstractOntologyTerm value) {
        this.id_ = value.id_;
        this.parentid = value.parentid;
        this.ontologyspaceid = value.ontologyspaceid;
        this.name = value.name;
        this.descriptionurl = value.descriptionurl;
        this.order_ = value.order_;
    }

    public AbstractOntologyTerm(Long id_, Long parentid, Long ontologyspaceid,
            String name, String descriptionurl, Integer order_) {
        this.id_ = id_;
        this.parentid = parentid;
        this.ontologyspaceid = ontologyspaceid;
        this.name = name;
        this.descriptionurl = descriptionurl;
        this.order_ = order_;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public Long getParentId() {
        return this.parentid;
    }

    public void setParentId(Long parentid) {
        this.parentid = parentid;
    }

    public Long getOntologySpaceId() {
        return this.ontologyspaceid;
    }

    public void setOntologySpaceId(Long ontologyspaceid) {
        this.ontologyspaceid = ontologyspaceid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionUrl() {
        return this.descriptionurl;
    }

    public void setDescriptionUrl(String descriptionurl) {
        this.descriptionurl = descriptionurl;
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
        result = prime * result + ((parentid == null) ? 0 : parentid.hashCode());
        result = prime * result + ((ontologyspaceid == null) ? 0 : ontologyspaceid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((descriptionurl == null) ? 0 : descriptionurl.hashCode());
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
        final AbstractOntologyTerm other = (AbstractOntologyTerm) obj;
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
            return false;
        }
        if (parentid == null) {
            if (other.parentid != null) {
                return false;
            }
        } else if (!parentid.equals(other.parentid)) {
            return false;
        }
        if (ontologyspaceid == null) {
            if (other.ontologyspaceid != null) {
                return false;
            }
        } else if (!ontologyspaceid.equals(other.ontologyspaceid)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (descriptionurl == null) {
            if (other.descriptionurl != null) {
                return false;
            }
        } else if (!descriptionurl.equals(other.descriptionurl)) {
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

        return "OntologyTerm (" + id_ +
                ", " + parentid +
                ", " + ontologyspaceid +
                ", " + name +
                ", " + descriptionurl +
                ", " + order_ +
                ")";
    }
}
