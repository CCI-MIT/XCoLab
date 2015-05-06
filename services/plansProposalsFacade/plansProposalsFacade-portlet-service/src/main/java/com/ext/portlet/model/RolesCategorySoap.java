package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.RolesCategoryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.RolesCategoryServiceSoap
 * @generated
 */
public class RolesCategorySoap implements Serializable {
    private long _roleId;
    private String _categoryName;
    private int _roleOrdinal;

    public RolesCategorySoap() {
    }

    public static RolesCategorySoap toSoapModel(RolesCategory model) {
        RolesCategorySoap soapModel = new RolesCategorySoap();

        soapModel.setRoleId(model.getRoleId());
        soapModel.setCategoryName(model.getCategoryName());
        soapModel.setRoleOrdinal(model.getRoleOrdinal());

        return soapModel;
    }

    public static RolesCategorySoap[] toSoapModels(RolesCategory[] models) {
        RolesCategorySoap[] soapModels = new RolesCategorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static RolesCategorySoap[][] toSoapModels(RolesCategory[][] models) {
        RolesCategorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new RolesCategorySoap[models.length][models[0].length];
        } else {
            soapModels = new RolesCategorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static RolesCategorySoap[] toSoapModels(List<RolesCategory> models) {
        List<RolesCategorySoap> soapModels = new ArrayList<RolesCategorySoap>(models.size());

        for (RolesCategory model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new RolesCategorySoap[soapModels.size()]);
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

    public String getCategoryName() {
        return _categoryName;
    }

    public void setCategoryName(String categoryName) {
        _categoryName = categoryName;
    }

    public int getRoleOrdinal() {
        return _roleOrdinal;
    }

    public void setRoleOrdinal(int roleOrdinal) {
        _roleOrdinal = roleOrdinal;
    }
}
