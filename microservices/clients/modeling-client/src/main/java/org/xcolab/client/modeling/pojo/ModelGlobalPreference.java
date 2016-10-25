package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ModelGlobalPreference {

    public static final TypeProvider<ModelGlobalPreference> TYPES = new TypeProvider<>(
            ModelGlobalPreference.class,
            new ParameterizedTypeReference<List<ModelGlobalPreference>>() {});

    private Long    modelglobalpreferencepk;
    private Long    modelid;
    private Boolean visible;
    private Integer weight;
    private Long    expertevaluationpageid;
    private Long    modelcategoryid;
    private Boolean usescustominputs;
    private String  custominputsdefinition;

    public ModelGlobalPreference() {}

    public ModelGlobalPreference(ModelGlobalPreference value) {
        this.modelglobalpreferencepk = value.modelglobalpreferencepk;
        this.modelid = value.modelid;
        this.visible = value.visible;
        this.weight = value.weight;
        this.expertevaluationpageid = value.expertevaluationpageid;
        this.modelcategoryid = value.modelcategoryid;
        this.usescustominputs = value.usescustominputs;
        this.custominputsdefinition = value.custominputsdefinition;
    }

    public ModelGlobalPreference(
        Long    modelglobalpreferencepk,
        Long    modelid,
        Boolean visible,
        Integer weight,
        Long    expertevaluationpageid,
        Long    modelcategoryid,
        Boolean usescustominputs,
        String  custominputsdefinition
    ) {
        this.modelglobalpreferencepk = modelglobalpreferencepk;
        this.modelid = modelid;
        this.visible = visible;
        this.weight = weight;
        this.expertevaluationpageid = expertevaluationpageid;
        this.modelcategoryid = modelcategoryid;
        this.usescustominputs = usescustominputs;
        this.custominputsdefinition = custominputsdefinition;
    }

    public Long getModelGlobalPreferencePK() {
        return this.modelglobalpreferencepk;
    }

    public void setModelGlobalPreferencePK(Long modelglobalpreferencepk) {
        this.modelglobalpreferencepk = modelglobalpreferencepk;
    }

    public Long getModelId() {
        return this.modelid;
    }

    public void setModelId(Long modelid) {
        this.modelid = modelid;
    }

    public Boolean getVisible() {
        return this.visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getExpertEvaluationPageId() {
        return this.expertevaluationpageid;
    }

    public void setExpertEvaluationPageId(Long expertevaluationpageid) {
        this.expertevaluationpageid = expertevaluationpageid;
    }

    public Long getModelCategoryId() {
        return this.modelcategoryid;
    }

    public void setModelCategoryId(Long modelcategoryid) {
        this.modelcategoryid = modelcategoryid;
    }

    public Boolean getUsesCustomInputs() {
        return this.usescustominputs;
    }

    public void setUsesCustomInputs(Boolean usescustominputs) {
        this.usescustominputs = usescustominputs;
    }

    public String getCustomInputsDefinition() {
        return this.custominputsdefinition;
    }

    public void setCustomInputsDefinition(String custominputsdefinition) {
        this.custominputsdefinition = custominputsdefinition;
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
        final ModelGlobalPreference other = (ModelGlobalPreference) obj;
        if (modelglobalpreferencepk == null) {
            if (other.modelglobalpreferencepk != null) {
                return false;
            }
        }
        else if (!modelglobalpreferencepk.equals(other.modelglobalpreferencepk)) {
            return false;
        }
        if (modelid == null) {
            if (other.modelid != null) {
                return false;
            }
        }
        else if (!modelid.equals(other.modelid)) {
            return false;
        }
        if (visible == null) {
            if (other.visible != null) {
                return false;
            }
        }
        else if (!visible.equals(other.visible)) {
            return false;
        }
        if (weight == null) {
            if (other.weight != null) {
                return false;
            }
        }
        else if (!weight.equals(other.weight)) {
            return false;
        }
        if (expertevaluationpageid == null) {
            if (other.expertevaluationpageid != null) {
                return false;
            }
        }
        else if (!expertevaluationpageid.equals(other.expertevaluationpageid)) {
            return false;
        }
        if (modelcategoryid == null) {
            if (other.modelcategoryid != null) {
                return false;
            }
        }
        else if (!modelcategoryid.equals(other.modelcategoryid)) {
            return false;
        }
        if (usescustominputs == null) {
            if (other.usescustominputs != null) {
                return false;
            }
        }
        else if (!usescustominputs.equals(other.usescustominputs)) {
            return false;
        }
        if (custominputsdefinition == null) {
            if (other.custominputsdefinition != null) {
                return false;
            }
        }
        else if (!custominputsdefinition.equals(other.custominputsdefinition)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((modelglobalpreferencepk == null) ? 0 : modelglobalpreferencepk.hashCode());
        result = prime * result + ((modelid == null) ? 0 : modelid.hashCode());
        result = prime * result + ((visible == null) ? 0 : visible.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((expertevaluationpageid == null) ? 0 : expertevaluationpageid.hashCode());
        result = prime * result + ((modelcategoryid == null) ? 0 : modelcategoryid.hashCode());
        result = prime * result + ((usescustominputs == null) ? 0 : usescustominputs.hashCode());
        result = prime * result + ((custominputsdefinition == null) ? 0 : custominputsdefinition.hashCode());
        return result;
    }

    @Override
    public String toString() {

        return "ModelGlobalPreference (" + modelglobalpreferencepk +
                ", " + modelid +
                ", " + visible +
                ", " + weight +
                ", " + expertevaluationpageid +
                ", " + modelcategoryid +
                ", " + usescustominputs +
                ", " + custominputsdefinition +
                ")";
    }
}
