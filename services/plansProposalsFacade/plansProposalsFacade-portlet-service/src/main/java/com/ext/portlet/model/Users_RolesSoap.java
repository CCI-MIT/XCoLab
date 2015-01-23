package com.ext.portlet.model;

import com.ext.portlet.service.persistence.Users_RolesPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.Users_RolesServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.Users_RolesServiceSoap
 * @generated
 */
public class Users_RolesSoap implements Serializable {
    private long _userId;
    private long _roleId;

    public Users_RolesSoap() {
    }

    public static Users_RolesSoap toSoapModel(Users_Roles model) {
        Users_RolesSoap soapModel = new Users_RolesSoap();

        soapModel.setUserId(model.getUserId());
        soapModel.setRoleId(model.getRoleId());

        return soapModel;
    }

    public static Users_RolesSoap[] toSoapModels(Users_Roles[] models) {
        Users_RolesSoap[] soapModels = new Users_RolesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static Users_RolesSoap[][] toSoapModels(Users_Roles[][] models) {
        Users_RolesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new Users_RolesSoap[models.length][models[0].length];
        } else {
            soapModels = new Users_RolesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static Users_RolesSoap[] toSoapModels(List<Users_Roles> models) {
        List<Users_RolesSoap> soapModels = new ArrayList<Users_RolesSoap>(models.size());

        for (Users_Roles model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new Users_RolesSoap[soapModels.size()]);
    }

    public Users_RolesPK getPrimaryKey() {
        return new Users_RolesPK(_userId, _roleId);
    }

    public void setPrimaryKey(Users_RolesPK pk) {
        setUserId(pk.userId);
        setRoleId(pk.roleId);
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public long getRoleId() {
        return _roleId;
    }

    public void setRoleId(long roleId) {
        _roleId = roleId;
    }
}
