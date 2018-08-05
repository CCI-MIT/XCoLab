package org.xcolab.client.modeling.pojo;

import java.io.Serializable;

public abstract class AbstractModelPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;
    protected Long positionid;
    protected Long modelid;

    public AbstractModelPosition() {
    }

    public AbstractModelPosition(AbstractModelPosition value) {
        this.id = value.id;
        this.positionid = value.positionid;
        this.modelid = value.modelid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPositionId() {
        return this.positionid;
    }

    public void setPositionId(Long positionid) {
        this.positionid = positionid;
    }

    public Long getModelId() {
        return this.modelid;
    }

    public void setModelId(Long modelid) {
        this.modelid = modelid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((positionid == null) ? 0 : positionid.hashCode());
        result = prime * result + ((modelid == null) ? 0 : modelid.hashCode());
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
        final ModelPosition other = (ModelPosition) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (positionid == null) {
            if (other.positionid != null) {
                return false;
            }
        } else if (!positionid.equals(other.positionid)) {
            return false;
        }
        if (modelid == null) {
            if (other.modelid != null) {
                return false;
            }
        } else if (!modelid.equals(other.modelid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ModelPosition (" + id +
                ", " + positionid +
                ", " + modelid +
                ")";
    }
}
