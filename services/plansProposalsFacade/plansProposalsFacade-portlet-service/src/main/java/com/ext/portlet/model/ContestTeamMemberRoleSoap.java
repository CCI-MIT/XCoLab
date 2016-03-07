package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ContestTeamMemberRoleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ContestTeamMemberRoleServiceSoap
 * @generated
 */
public class ContestTeamMemberRoleSoap implements Serializable {
    private long _id;
    private String _role;
    private int _sort;

    public ContestTeamMemberRoleSoap() {
    }

    public static ContestTeamMemberRoleSoap toSoapModel(
        ContestTeamMemberRole model) {
        ContestTeamMemberRoleSoap soapModel = new ContestTeamMemberRoleSoap();

        soapModel.setId(model.getId());
        soapModel.setRole(model.getRole());
        soapModel.setSort(model.getSort());

        return soapModel;
    }

    public static ContestTeamMemberRoleSoap[] toSoapModels(
        ContestTeamMemberRole[] models) {
        ContestTeamMemberRoleSoap[] soapModels = new ContestTeamMemberRoleSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestTeamMemberRoleSoap[][] toSoapModels(
        ContestTeamMemberRole[][] models) {
        ContestTeamMemberRoleSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestTeamMemberRoleSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestTeamMemberRoleSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestTeamMemberRoleSoap[] toSoapModels(
        List<ContestTeamMemberRole> models) {
        List<ContestTeamMemberRoleSoap> soapModels = new ArrayList<ContestTeamMemberRoleSoap>(models.size());

        for (ContestTeamMemberRole model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestTeamMemberRoleSoap[soapModels.size()]);
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

    public String getRole() {
        return _role;
    }

    public void setRole(String role) {
        _role = role;
    }

    public int getSort() {
        return _sort;
    }

    public void setSort(int sort) {
        _sort = sort;
    }
}
