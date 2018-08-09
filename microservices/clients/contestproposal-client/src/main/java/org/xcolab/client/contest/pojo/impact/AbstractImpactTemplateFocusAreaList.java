package org.xcolab.client.contest.pojo.impact;

import java.io.Serializable;

abstract class AbstractImpactTemplateFocusAreaList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public AbstractImpactTemplateFocusAreaList() {}

    public AbstractImpactTemplateFocusAreaList(AbstractImpactTemplateFocusAreaList value) {
        this.id = value.id;
        this.name = value.name;
    }

    public AbstractImpactTemplateFocusAreaList(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long focusarealistid) {
        this.id = focusarealistid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        final AbstractImpactTemplateFocusAreaList other = (AbstractImpactTemplateFocusAreaList) obj;
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
        return true;
    }

    @Override
    public String toString() {

        return "ImpactTemplateFocusAreaList (" + id +
                ", " + name +
                ")";
    }
}
