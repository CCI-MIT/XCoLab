package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.Role_ServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.Role_ServiceSoap
 * @generated
 */
public class Role_Soap implements Serializable {
    private long _roleId;
    private String _name;

    public Role_Soap() {
    }

    public static Role_Soap toSoapModel(Role_ model) {
        Role_Soap soapModel = new Role_Soap();

        soapModel.setRoleId(model.getRoleId());
        soapModel.setName(model.getName());

        return soapModel;
    }

    public static Role_Soap[] toSoapModels(Role_[] models) {
        Role_Soap[] soapModels = new Role_Soap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static Role_Soap[][] toSoapModels(Role_[][] models) {
        Role_Soap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new Role_Soap[models.length][models[0].length];
        } else {
            soapModels = new Role_Soap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static Role_Soap[] toSoapModels(List<Role_> models) {
        List<Role_Soap> soapModels = new ArrayList<Role_Soap>(models.size());

        for (Role_ model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new Role_Soap[soapModels.size()]);
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

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
}
