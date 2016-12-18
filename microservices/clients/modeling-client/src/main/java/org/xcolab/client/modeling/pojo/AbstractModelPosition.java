package org.xcolab.client.modeling.pojo;

public abstract class AbstractModelPosition {

    protected Long id_;
    protected Long positionid;
    protected Long modelid;

    public AbstractModelPosition() {
    }

    public AbstractModelPosition(AbstractModelPosition value) {
        this.id_ = value.id_;
        this.positionid = value.positionid;
        this.modelid = value.modelid;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
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
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
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
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
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

        return "ModelPosition (" + id_ +
                ", " + positionid +
                ", " + modelid +
                ")";
    }
}
