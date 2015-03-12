package com.ext.portlet.model;

import com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ImpactDefaultSeriesDataServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ImpactDefaultSeriesDataServiceSoap
 * @generated
 */
public class ImpactDefaultSeriesDataSoap implements Serializable {
    private long _seriesId;
    private int _year;
    private double _value;

    public ImpactDefaultSeriesDataSoap() {
    }

    public static ImpactDefaultSeriesDataSoap toSoapModel(
        ImpactDefaultSeriesData model) {
        ImpactDefaultSeriesDataSoap soapModel = new ImpactDefaultSeriesDataSoap();

        soapModel.setSeriesId(model.getSeriesId());
        soapModel.setYear(model.getYear());
        soapModel.setValue(model.getValue());

        return soapModel;
    }

    public static ImpactDefaultSeriesDataSoap[] toSoapModels(
        ImpactDefaultSeriesData[] models) {
        ImpactDefaultSeriesDataSoap[] soapModels = new ImpactDefaultSeriesDataSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImpactDefaultSeriesDataSoap[][] toSoapModels(
        ImpactDefaultSeriesData[][] models) {
        ImpactDefaultSeriesDataSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImpactDefaultSeriesDataSoap[models.length][models[0].length];
        } else {
            soapModels = new ImpactDefaultSeriesDataSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImpactDefaultSeriesDataSoap[] toSoapModels(
        List<ImpactDefaultSeriesData> models) {
        List<ImpactDefaultSeriesDataSoap> soapModels = new ArrayList<ImpactDefaultSeriesDataSoap>(models.size());

        for (ImpactDefaultSeriesData model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImpactDefaultSeriesDataSoap[soapModels.size()]);
    }

    public ImpactDefaultSeriesDataPK getPrimaryKey() {
        return new ImpactDefaultSeriesDataPK(_seriesId, _year);
    }

    public void setPrimaryKey(ImpactDefaultSeriesDataPK pk) {
        setSeriesId(pk.seriesId);
        setYear(pk.year);
    }

    public long getSeriesId() {
        return _seriesId;
    }

    public void setSeriesId(long seriesId) {
        _seriesId = seriesId;
    }

    public int getYear() {
        return _year;
    }

    public void setYear(int year) {
        _year = year;
    }

    public double getValue() {
        return _value;
    }

    public void setValue(double value) {
        _value = value;
    }
}
