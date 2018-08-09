package org.xcolab.client.contest.pojo.ontology;

import java.io.Serializable;

abstract class AbstractOntologyTerm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long parentid;
    private Long ontologyspaceid;
    private String name;
    private String descriptionurl;
    private Integer sortOrder;

    public AbstractOntologyTerm() {}

    public AbstractOntologyTerm(AbstractOntologyTerm value) {
        this.id = value.id;
        this.parentid = value.parentid;
        this.ontologyspaceid = value.ontologyspaceid;
        this.name = value.name;
        this.descriptionurl = value.descriptionurl;
        this.sortOrder = value.sortOrder;
    }

    public AbstractOntologyTerm(Long id, Long parentid, Long ontologyspaceid,
            String name, String descriptionurl, Integer sortOrder) {
        this.id = id;
        this.parentid = parentid;
        this.ontologyspaceid = ontologyspaceid;
        this.name = name;
        this.descriptionurl = descriptionurl;
        this.sortOrder = sortOrder;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
        result = prime * result + ((parentid == null) ? 0 : parentid.hashCode());
        result = prime * result + ((ontologyspaceid == null) ? 0 : ontologyspaceid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((descriptionurl == null) ? 0 : descriptionurl.hashCode());
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
        final AbstractOntologyTerm other = (AbstractOntologyTerm) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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

        return "OntologyTerm (" + id +
                ", " + parentid +
                ", " + ontologyspaceid +
                ", " + name +
                ", " + descriptionurl +
                ", " + sortOrder +
                ")";
    }
}
