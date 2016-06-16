package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.MemberCategoryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.MemberCategoryServiceSoap
 * @generated
 */
public class MemberCategorySoap implements Serializable {
    private long _roleId;
    private String _displayName;
    private String _categoryName;
    private long _sortOrder;
    private boolean _showInList;
    private String _imageName;
    private String _description;

    public MemberCategorySoap() {
    }

    public static MemberCategorySoap toSoapModel(MemberCategory model) {
        MemberCategorySoap soapModel = new MemberCategorySoap();

        soapModel.setRoleId(model.getRoleId());
        soapModel.setDisplayName(model.getDisplayName());
        soapModel.setCategoryName(model.getCategoryName());
        soapModel.setSortOrder(model.getSortOrder());
        soapModel.setShowInList(model.getShowInList());
        soapModel.setImageName(model.getImageName());
        soapModel.setDescription(model.getDescription());

        return soapModel;
    }

    public static MemberCategorySoap[] toSoapModels(MemberCategory[] models) {
        MemberCategorySoap[] soapModels = new MemberCategorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MemberCategorySoap[][] toSoapModels(MemberCategory[][] models) {
        MemberCategorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MemberCategorySoap[models.length][models[0].length];
        } else {
            soapModels = new MemberCategorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MemberCategorySoap[] toSoapModels(List<MemberCategory> models) {
        List<MemberCategorySoap> soapModels = new ArrayList<MemberCategorySoap>(models.size());

        for (MemberCategory model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MemberCategorySoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _roleId;
    }

    public void setPrimaryKey(long pk) {
        setRoleId(pk);
    }

    public long getRoleId() {
        return _roleId;
    }

    public void setRoleId(long roleId) {
        _roleId = roleId;
    }

    public String getDisplayName() {
        return _displayName;
    }

    public void setDisplayName(String displayName) {
        _displayName = displayName;
    }

    public String getCategoryName() {
        return _categoryName;
    }

    public void setCategoryName(String categoryName) {
        _categoryName = categoryName;
    }

    public long getSortOrder() {
        return _sortOrder;
    }

    public void setSortOrder(long sortOrder) {
        _sortOrder = sortOrder;
    }

    public boolean getShowInList() {
        return _showInList;
    }

    public boolean isShowInList() {
        return _showInList;
    }

    public void setShowInList(boolean showInList) {
        _showInList = showInList;
    }

    public String getImageName() {
        return _imageName;
    }

    public void setImageName(String imageName) {
        _imageName = imageName;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
