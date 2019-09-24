package org.xcolab.client.modeling.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.modeling.pojo.tables.pojos.ModelInputGroup;

@JsonDeserialize(as = ModelInputGroup.class)
public interface IModelInputGroup {

    Long getId();

    void setId(Long id);

    Long getModelId();

    void setModelId(Long modelId);

    Long getNameAndDescriptionMetaDataId();

    void setNameAndDescriptionMetaDataId(Long nameAndDescriptionMetaDataId);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Integer getDisplayItemOrder();

    void setDisplayItemOrder(Integer displayItemOrder);

    String getGroupType();

    void setGroupType(String groupType);

    Long getParentGroupId();

    void setParentGroupId(Long parentGroupId);
}
