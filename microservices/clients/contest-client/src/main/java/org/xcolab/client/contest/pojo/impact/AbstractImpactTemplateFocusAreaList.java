package org.xcolab.client.contest.pojo.impact;

abstract class AbstractImpactTemplateFocusAreaList {

    private Long focusarealistid;
    private String name;

    public AbstractImpactTemplateFocusAreaList() {}

    public AbstractImpactTemplateFocusAreaList(AbstractImpactTemplateFocusAreaList value) {
        this.focusarealistid = value.focusarealistid;
        this.name = value.name;
    }

    public AbstractImpactTemplateFocusAreaList(Long focusarealistid, String name) {
        this.focusarealistid = focusarealistid;
        this.name = name;
    }

    public Long getFocusAreaListId() {
        return this.focusarealistid;
    }

    public void setFocusAreaListId(Long focusarealistid) {
        this.focusarealistid = focusarealistid;
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
        result = prime * result + ((focusarealistid == null) ? 0 : focusarealistid.hashCode());
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
        if (focusarealistid == null) {
            if (other.focusarealistid != null) {
                return false;
            }
        } else if (!focusarealistid.equals(other.focusarealistid)) {
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

        return "ImpactTemplateFocusAreaList (" + focusarealistid +
                ", " + name +
                ")";
    }
}
