package com.ext.portlet.contests.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.contests.service.http.ContestPhaseColumnServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.contests.service.http.ContestPhaseColumnServiceSoap
 * @generated
 */
public class ContestPhaseColumnSoap implements Serializable {
    private long _id;
    private long _ContestPhasePK;
    private String _columnName;
    private int _columnWeight;
    private boolean _defaultSort;

    public ContestPhaseColumnSoap() {
    }

    public static ContestPhaseColumnSoap toSoapModel(ContestPhaseColumn model) {
        ContestPhaseColumnSoap soapModel = new ContestPhaseColumnSoap();

        soapModel.setId(model.getId());
        soapModel.setContestPhasePK(model.getContestPhasePK());
        soapModel.setColumnName(model.getColumnName());
        soapModel.setColumnWeight(model.getColumnWeight());
        soapModel.setDefaultSort(model.getDefaultSort());

        return soapModel;
    }

    public static ContestPhaseColumnSoap[] toSoapModels(
        ContestPhaseColumn[] models) {
        ContestPhaseColumnSoap[] soapModels = new ContestPhaseColumnSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseColumnSoap[][] toSoapModels(
        ContestPhaseColumn[][] models) {
        ContestPhaseColumnSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestPhaseColumnSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestPhaseColumnSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseColumnSoap[] toSoapModels(
        List<ContestPhaseColumn> models) {
        List<ContestPhaseColumnSoap> soapModels = new ArrayList<ContestPhaseColumnSoap>(models.size());

        for (ContestPhaseColumn model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestPhaseColumnSoap[soapModels.size()]);
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

    public long getContestPhasePK() {
        return _ContestPhasePK;
    }

    public void setContestPhasePK(long ContestPhasePK) {
        _ContestPhasePK = ContestPhasePK;
    }

    public String getColumnName() {
        return _columnName;
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;
    }

    public int getColumnWeight() {
        return _columnWeight;
    }

    public void setColumnWeight(int columnWeight) {
        _columnWeight = columnWeight;
    }

    public boolean getDefaultSort() {
        return _defaultSort;
    }

    public boolean isDefaultSort() {
        return _defaultSort;
    }

    public void setDefaultSort(boolean defaultSort) {
        _defaultSort = defaultSort;
    }
}
