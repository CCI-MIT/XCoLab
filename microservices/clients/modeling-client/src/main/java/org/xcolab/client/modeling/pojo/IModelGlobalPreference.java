package org.xcolab.client.modeling.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.modeling.pojo.tables.pojos.ModelGlobalPreference;

@JsonDeserialize(as = ModelGlobalPreference.class)
public interface IModelGlobalPreference {

    Long getId();

    void setId(Long id);

    Long getModelId();

    void setModelId(Long modelId);

    Boolean isVisible();

    void setVisible(Boolean visible);

    Integer getWeight();

    void setWeight(Integer weight);

    Long getExpertEvaluationPageId();

    void setExpertEvaluationPageId(Long expertEvaluationPageId);

    Long getModelCategoryId();

    void setModelCategoryId(Long modelCategoryId);

    Boolean isUsesCustomInputs();

    void setUsesCustomInputs(Boolean usesCustomInputs);

    String getCustomInputsDefinition();

    void setCustomInputsDefinition(String customInputsDefinition);
}
