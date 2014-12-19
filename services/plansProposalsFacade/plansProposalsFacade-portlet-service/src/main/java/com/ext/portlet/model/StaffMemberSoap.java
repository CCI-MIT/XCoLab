package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.StaffMemberServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.StaffMemberServiceSoap
 * @generated
 */
public class StaffMemberSoap implements Serializable {
    private long _id;
    private long _userId;
    private long _categoryId;
    private String _firstNames;
    private String _lastName;
    private String _url;
    private String _photoUrl;
    private String _role;
    private String _organization;
    private int _sort;

    public StaffMemberSoap() {
    }

    public static StaffMemberSoap toSoapModel(StaffMember model) {
        StaffMemberSoap soapModel = new StaffMemberSoap();

        soapModel.setId(model.getId());
        soapModel.setUserId(model.getUserId());
        soapModel.setCategoryId(model.getCategoryId());
        soapModel.setFirstNames(model.getFirstNames());
        soapModel.setLastName(model.getLastName());
        soapModel.setUrl(model.getUrl());
        soapModel.setPhotoUrl(model.getPhotoUrl());
        soapModel.setRole(model.getRole());
        soapModel.setOrganization(model.getOrganization());
        soapModel.setSort(model.getSort());

        return soapModel;
    }

    public static StaffMemberSoap[] toSoapModels(StaffMember[] models) {
        StaffMemberSoap[] soapModels = new StaffMemberSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StaffMemberSoap[][] toSoapModels(StaffMember[][] models) {
        StaffMemberSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StaffMemberSoap[models.length][models[0].length];
        } else {
            soapModels = new StaffMemberSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StaffMemberSoap[] toSoapModels(List<StaffMember> models) {
        List<StaffMemberSoap> soapModels = new ArrayList<StaffMemberSoap>(models.size());

        for (StaffMember model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StaffMemberSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long pk) {
        setId(pk);
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(long categoryId) {
        _categoryId = categoryId;
    }

    public String getFirstNames() {
        return _firstNames;
    }

    public void setFirstNames(String firstNames) {
        _firstNames = firstNames;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        _lastName = lastName;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    public String getPhotoUrl() {
        return _photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        _photoUrl = photoUrl;
    }

    public String getRole() {
        return _role;
    }

    public void setRole(String role) {
        _role = role;
    }

    public String getOrganization() {
        return _organization;
    }

    public void setOrganization(String organization) {
        _organization = organization;
    }

    public int getSort() {
        return _sort;
    }

    public void setSort(int sort) {
        _sort = sort;
    }
}
