package org.xcolab.client.modeling.pojo;

public abstract class AbstractModelCategory {

    private Long modelcategorypk;
    private String modelcategoryname;
    private String modelcategorydescription;
    private Integer modelcategorydisplayweight;

    public AbstractModelCategory() {
    }

    public AbstractModelCategory(AbstractModelCategory value) {
        this.modelcategorypk = value.modelcategorypk;
        this.modelcategoryname = value.modelcategoryname;
        this.modelcategorydescription = value.modelcategorydescription;
        this.modelcategorydisplayweight = value.modelcategorydisplayweight;
    }

    public Long getModelCategoryPK() {
        return this.modelcategorypk;
    }

    public void setModelCategoryPK(Long modelcategorypk) {
        this.modelcategorypk = modelcategorypk;
    }

    public String getModelCategoryName() {
        return this.modelcategoryname;
    }

    public void setModelCategoryName(String modelcategoryname) {
        this.modelcategoryname = modelcategoryname;
    }

    public String getModelCategoryDescription() {
        return this.modelcategorydescription;
    }

    public void setModelCategoryDescription(String modelcategorydescription) {
        this.modelcategorydescription = modelcategorydescription;
    }

    public Integer getModelCategoryDisplayWeight() {
        return this.modelcategorydisplayweight;
    }

    public void setModelCategoryDisplayWeight(Integer modelcategorydisplayweight) {
        this.modelcategorydisplayweight = modelcategorydisplayweight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((modelcategorypk == null) ? 0 : modelcategorypk.hashCode());
        result = prime * result + ((modelcategoryname == null) ? 0 : modelcategoryname.hashCode());
        result = prime * result + ((modelcategorydescription == null) ? 0
                : modelcategorydescription.hashCode());
        result = prime * result + ((modelcategorydisplayweight == null) ? 0
                : modelcategorydisplayweight.hashCode());
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
        final AbstractModelCategory other = (AbstractModelCategory) obj;
        if (modelcategorypk == null) {
            if (other.modelcategorypk != null) {
                return false;
            }
        } else if (!modelcategorypk.equals(other.modelcategorypk)) {
            return false;
        }
        if (modelcategoryname == null) {
            if (other.modelcategoryname != null) {
                return false;
            }
        } else if (!modelcategoryname.equals(other.modelcategoryname)) {
            return false;
        }
        if (modelcategorydescription == null) {
            if (other.modelcategorydescription != null) {
                return false;
            }
        } else if (!modelcategorydescription.equals(other.modelcategorydescription)) {
            return false;
        }
        if (modelcategorydisplayweight == null) {
            if (other.modelcategorydisplayweight != null) {
                return false;
            }
        } else if (!modelcategorydisplayweight.equals(other.modelcategorydisplayweight)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ModelCategory (");

        sb.append(modelcategorypk);
        sb.append(", ").append(modelcategoryname);
        sb.append(", ").append(modelcategorydescription);
        sb.append(", ").append(modelcategorydisplayweight);

        sb.append(")");
        return sb.toString();
    }
}
