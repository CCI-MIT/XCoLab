package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ImpactTemplateSeriesServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ImpactTemplateSeriesServiceSoap
 * @generated
 */
public class ImpactTemplateSeriesSoap implements Serializable {
    private long _seriesId;
    private long _iterationId;
    private String _name;

    public ImpactTemplateSeriesSoap() {
    }

    public static ImpactTemplateSeriesSoap toSoapModel(
        ImpactTemplateSeries model) {
        ImpactTemplateSeriesSoap soapModel = new ImpactTemplateSeriesSoap();

        soapModel.setSeriesId(model.getSeriesId());
        soapModel.setIterationId(model.getIterationId());
        soapModel.setName(model.getName());

        return soapModel;
    }

    public static ImpactTemplateSeriesSoap[] toSoapModels(
        ImpactTemplateSeries[] models) {
        ImpactTemplateSeriesSoap[] soapModels = new ImpactTemplateSeriesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImpactTemplateSeriesSoap[][] toSoapModels(
        ImpactTemplateSeries[][] models) {
        ImpactTemplateSeriesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImpactTemplateSeriesSoap[models.length][models[0].length];
        } else {
            soapModels = new ImpactTemplateSeriesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImpactTemplateSeriesSoap[] toSoapModels(
        List<ImpactTemplateSeries> models) {
        List<ImpactTemplateSeriesSoap> soapModels = new ArrayList<ImpactTemplateSeriesSoap>(models.size());

        for (ImpactTemplateSeries model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImpactTemplateSeriesSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _seriesId;
    }

    public void setPrimaryKey(long pk) {
        setSeriesId(pk);
    }

    public long getSeriesId() {
        return _seriesId;
    }

    public void setSeriesId(long seriesId) {
        _seriesId = seriesId;
    }

    public long getIterationId() {
        return _iterationId;
    }

    public void setIterationId(long iterationId) {
        _iterationId = iterationId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
}
