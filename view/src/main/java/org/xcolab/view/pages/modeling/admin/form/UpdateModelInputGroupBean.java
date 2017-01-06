package org.xcolab.view.pages.modeling.admin.form;

public class UpdateModelInputGroupBean {

    private String inputAction;
    private long id;
    private String name;
    private String description;
    private long nameDescriptionMetaDataId;
    private int order;
    private String groupType;
    private long parentGroupPK;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getNameDescriptionMetaDataId() {
        return nameDescriptionMetaDataId;
    }

    public void setNameDescriptionMetaDataId(long nameDescriptionMetaDataId) {
        this.nameDescriptionMetaDataId = nameDescriptionMetaDataId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public long getParentGroupPK() {
        return parentGroupPK;
    }

    public void setParentGroupPK(long parentGroupPK) {
        this.parentGroupPK = parentGroupPK;
    }

    public String getInputAction() {
        return inputAction;
    }

    public void setInputAction(String action) {
        this.inputAction = action;
    }

}
