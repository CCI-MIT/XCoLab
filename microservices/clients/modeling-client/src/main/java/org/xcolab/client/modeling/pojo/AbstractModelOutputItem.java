package org.xcolab.client.modeling.pojo;

import java.io.Serializable;

public abstract class AbstractModelOutputItem implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long modeloutputitemmodifierpk;
    protected Long modelid;
    protected Long modeloutputitemid;
    protected Integer modeloutputitemorder;
    protected String modelitemrangepolicy;
    protected String modelitemrangemessage;
    protected String modelitemerrorpolicy;
    protected String modelitemerrormessage;
    protected String modelitemlabelformat;
    protected Boolean modelitemisvisible;
    protected String itemtype;
    protected Long relatedoutputitem;

    public AbstractModelOutputItem() {
    }

    public AbstractModelOutputItem(AbstractModelOutputItem value) {
        this.modeloutputitemmodifierpk = value.modeloutputitemmodifierpk;
        this.modelid = value.modelid;
        this.modeloutputitemid = value.modeloutputitemid;
        this.modeloutputitemorder = value.modeloutputitemorder;
        this.modelitemrangepolicy = value.modelitemrangepolicy;
        this.modelitemrangemessage = value.modelitemrangemessage;
        this.modelitemerrorpolicy = value.modelitemerrorpolicy;
        this.modelitemerrormessage = value.modelitemerrormessage;
        this.modelitemlabelformat = value.modelitemlabelformat;
        this.modelitemisvisible = value.modelitemisvisible;
        this.itemtype = value.itemtype;
        this.relatedoutputitem = value.relatedoutputitem;
    }

    public Long getModelOutputItemModifierPK() {
        return this.modeloutputitemmodifierpk;
    }

    public void setModelOutputItemModifierPK(Long modeloutputitemmodifierpk) {
        this.modeloutputitemmodifierpk = modeloutputitemmodifierpk;
    }

    public Long getModelId() {
        return this.modelid;
    }

    public void setModelId(Long modelid) {
        this.modelid = modelid;
    }

    public Long getModelOutputItemId() {
        return this.modeloutputitemid;
    }

    public void setModelOutputItemId(Long modeloutputitemid) {
        this.modeloutputitemid = modeloutputitemid;
    }

    public Integer getModelOutputItemOrder() {
        return this.modeloutputitemorder;
    }

    public void setModelOutputItemOrder(Integer modeloutputitemorder) {
        this.modeloutputitemorder = modeloutputitemorder;
    }

    public String getModelItemRangePolicy() {
        return this.modelitemrangepolicy;
    }

    public void setModelItemRangePolicy(String modelitemrangepolicy) {
        this.modelitemrangepolicy = modelitemrangepolicy;
    }

    public String getModelItemRangeMessage() {
        return this.modelitemrangemessage;
    }

    public void setModelItemRangeMessage(String modelitemrangemessage) {
        this.modelitemrangemessage = modelitemrangemessage;
    }

    public String getModelItemErrorPolicy() {
        return this.modelitemerrorpolicy;
    }

    public void setModelItemErrorPolicy(String modelitemerrorpolicy) {
        this.modelitemerrorpolicy = modelitemerrorpolicy;
    }

    public String getModelItemErrorMessage() {
        return this.modelitemerrormessage;
    }

    public void setModelItemErrorMessage(String modelitemerrormessage) {
        this.modelitemerrormessage = modelitemerrormessage;
    }

    public String getModelItemLabelFormat() {
        return this.modelitemlabelformat;
    }

    public void setModelItemLabelFormat(String modelitemlabelformat) {
        this.modelitemlabelformat = modelitemlabelformat;
    }

    public Boolean getModelItemIsVisible() {
        return this.modelitemisvisible;
    }

    public void setModelItemIsVisible(Boolean modelitemisvisible) {
        this.modelitemisvisible = modelitemisvisible;
    }

    public String getItemType() {
        return this.itemtype;
    }

    public void setItemType(String itemtype) {
        this.itemtype = itemtype;
    }

    public Long getRelatedOutputItem() {
        return this.relatedoutputitem;
    }

    public void setRelatedOutputItem(Long relatedoutputitem) {
        this.relatedoutputitem = relatedoutputitem;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((modeloutputitemmodifierpk == null) ? 0
                : modeloutputitemmodifierpk.hashCode());
        result = prime * result + ((modelid == null) ? 0 : modelid.hashCode());
        result = prime * result + ((modeloutputitemid == null) ? 0 : modeloutputitemid.hashCode());
        result = prime * result + ((modeloutputitemorder == null) ? 0
                : modeloutputitemorder.hashCode());
        result = prime * result + ((modelitemrangepolicy == null) ? 0
                : modelitemrangepolicy.hashCode());
        result = prime * result + ((modelitemrangemessage == null) ? 0
                : modelitemrangemessage.hashCode());
        result = prime * result + ((modelitemerrorpolicy == null) ? 0
                : modelitemerrorpolicy.hashCode());
        result = prime * result + ((modelitemerrormessage == null) ? 0
                : modelitemerrormessage.hashCode());
        result = prime * result + ((modelitemlabelformat == null) ? 0
                : modelitemlabelformat.hashCode());
        result =
                prime * result + ((modelitemisvisible == null) ? 0 : modelitemisvisible.hashCode());
        result = prime * result + ((itemtype == null) ? 0 : itemtype.hashCode());
        result = prime * result + ((relatedoutputitem == null) ? 0 : relatedoutputitem.hashCode());
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
        final ModelOutputItem other = (ModelOutputItem) obj;
        if (modeloutputitemmodifierpk == null) {
            if (other.modeloutputitemmodifierpk != null) {
                return false;
            }
        } else if (!modeloutputitemmodifierpk.equals(other.modeloutputitemmodifierpk)) {
            return false;
        }
        if (modelid == null) {
            if (other.modelid != null) {
                return false;
            }
        } else if (!modelid.equals(other.modelid)) {
            return false;
        }
        if (modeloutputitemid == null) {
            if (other.modeloutputitemid != null) {
                return false;
            }
        } else if (!modeloutputitemid.equals(other.modeloutputitemid)) {
            return false;
        }
        if (modeloutputitemorder == null) {
            if (other.modeloutputitemorder != null) {
                return false;
            }
        } else if (!modeloutputitemorder.equals(other.modeloutputitemorder)) {
            return false;
        }
        if (modelitemrangepolicy == null) {
            if (other.modelitemrangepolicy != null) {
                return false;
            }
        } else if (!modelitemrangepolicy.equals(other.modelitemrangepolicy)) {
            return false;
        }
        if (modelitemrangemessage == null) {
            if (other.modelitemrangemessage != null) {
                return false;
            }
        } else if (!modelitemrangemessage.equals(other.modelitemrangemessage)) {
            return false;
        }
        if (modelitemerrorpolicy == null) {
            if (other.modelitemerrorpolicy != null) {
                return false;
            }
        } else if (!modelitemerrorpolicy.equals(other.modelitemerrorpolicy)) {
            return false;
        }
        if (modelitemerrormessage == null) {
            if (other.modelitemerrormessage != null) {
                return false;
            }
        } else if (!modelitemerrormessage.equals(other.modelitemerrormessage)) {
            return false;
        }
        if (modelitemlabelformat == null) {
            if (other.modelitemlabelformat != null) {
                return false;
            }
        } else if (!modelitemlabelformat.equals(other.modelitemlabelformat)) {
            return false;
        }
        if (modelitemisvisible == null) {
            if (other.modelitemisvisible != null) {
                return false;
            }
        } else if (!modelitemisvisible.equals(other.modelitemisvisible)) {
            return false;
        }
        if (itemtype == null) {
            if (other.itemtype != null) {
                return false;
            }
        } else if (!itemtype.equals(other.itemtype)) {
            return false;
        }
        if (relatedoutputitem == null) {
            if (other.relatedoutputitem != null) {
                return false;
            }
        } else if (!relatedoutputitem.equals(other.relatedoutputitem)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ModelOutputItem (");

        sb.append(modeloutputitemmodifierpk);
        sb.append(", ").append(modelid);
        sb.append(", ").append(modeloutputitemid);
        sb.append(", ").append(modeloutputitemorder);
        sb.append(", ").append(modelitemrangepolicy);
        sb.append(", ").append(modelitemrangemessage);
        sb.append(", ").append(modelitemerrorpolicy);
        sb.append(", ").append(modelitemerrormessage);
        sb.append(", ").append(modelitemlabelformat);
        sb.append(", ").append(modelitemisvisible);
        sb.append(", ").append(itemtype);
        sb.append(", ").append(relatedoutputitem);

        sb.append(")");
        return sb.toString();
    }
}
