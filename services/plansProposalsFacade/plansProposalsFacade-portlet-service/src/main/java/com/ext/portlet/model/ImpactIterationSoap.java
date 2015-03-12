package com.ext.portlet.model;

import com.ext.portlet.service.persistence.ImpactIterationPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ImpactIterationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ImpactIterationServiceSoap
 * @generated
 */
public class ImpactIterationSoap implements Serializable {
    private long _iterationId;
    private int _year;

    public ImpactIterationSoap() {
    }

    public static ImpactIterationSoap toSoapModel(ImpactIteration model) {
        ImpactIterationSoap soapModel = new ImpactIterationSoap();

        soapModel.setIterationId(model.getIterationId());
        soapModel.setYear(model.getYear());

        return soapModel;
    }

    public static ImpactIterationSoap[] toSoapModels(ImpactIteration[] models) {
        ImpactIterationSoap[] soapModels = new ImpactIterationSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImpactIterationSoap[][] toSoapModels(
        ImpactIteration[][] models) {
        ImpactIterationSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImpactIterationSoap[models.length][models[0].length];
        } else {
            soapModels = new ImpactIterationSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImpactIterationSoap[] toSoapModels(
        List<ImpactIteration> models) {
        List<ImpactIterationSoap> soapModels = new ArrayList<ImpactIterationSoap>(models.size());

        for (ImpactIteration model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImpactIterationSoap[soapModels.size()]);
    }

    public ImpactIterationPK getPrimaryKey() {
        return new ImpactIterationPK(_iterationId, _year);
    }

    public void setPrimaryKey(ImpactIterationPK pk) {
        setIterationId(pk.iterationId);
        setYear(pk.year);
    }

    public long getIterationId() {
        return _iterationId;
    }

    public void setIterationId(long iterationId) {
        _iterationId = iterationId;
    }

    public int getYear() {
        return _year;
    }

    public void setYear(int year) {
        _year = year;
    }
}
