package com.ext.portlet.model;

import com.ext.portlet.service.persistence.ImpactDefaultSeriesPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ImpactDefaultSeriesServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ImpactDefaultSeriesServiceSoap
 * @generated
 */
public class ImpactDefaultSeriesSoap implements Serializable {
    private long _seriesId;
    private String _name;
    private String _description;
    private long _focusAreaId;
    private boolean _visible;
    private boolean _editable;

    public ImpactDefaultSeriesSoap() {
    }

    public static ImpactDefaultSeriesSoap toSoapModel(ImpactDefaultSeries model) {
        ImpactDefaultSeriesSoap soapModel = new ImpactDefaultSeriesSoap();

        soapModel.setSeriesId(model.getSeriesId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setFocusAreaId(model.getFocusAreaId());
        soapModel.setVisible(model.getVisible());
        soapModel.setEditable(model.getEditable());

        return soapModel;
    }

    public static ImpactDefaultSeriesSoap[] toSoapModels(
        ImpactDefaultSeries[] models) {
        ImpactDefaultSeriesSoap[] soapModels = new ImpactDefaultSeriesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImpactDefaultSeriesSoap[][] toSoapModels(
        ImpactDefaultSeries[][] models) {
        ImpactDefaultSeriesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImpactDefaultSeriesSoap[models.length][models[0].length];
        } else {
            soapModels = new ImpactDefaultSeriesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImpactDefaultSeriesSoap[] toSoapModels(
        List<ImpactDefaultSeries> models) {
        List<ImpactDefaultSeriesSoap> soapModels = new ArrayList<ImpactDefaultSeriesSoap>(models.size());

        for (ImpactDefaultSeries model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImpactDefaultSeriesSoap[soapModels.size()]);
    }

    public ImpactDefaultSeriesPK getPrimaryKey() {
        return new ImpactDefaultSeriesPK(_seriesId, _name);
    }

    public void setPrimaryKey(ImpactDefaultSeriesPK pk) {
        setSeriesId(pk.seriesId);
        setName(pk.name);
    }

    public long getSeriesId() {
        return _seriesId;
    }

    public void setSeriesId(long seriesId) {
        _seriesId = seriesId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public boolean getVisible() {
        return _visible;
    }

    public boolean isVisible() {
        return _visible;
    }

    public void setVisible(boolean visible) {
        _visible = visible;
    }

    public boolean getEditable() {
        return _editable;
    }

    public boolean isEditable() {
        return _editable;
    }

    public void setEditable(boolean editable) {
        _editable = editable;
    }
}
