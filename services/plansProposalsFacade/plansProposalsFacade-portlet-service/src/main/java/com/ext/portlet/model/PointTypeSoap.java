package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PointTypeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PointTypeServiceSoap
 * @generated
 */
public class PointTypeSoap implements Serializable {
    private long _id;
    private long _parentPointTypeId;
    private double _percentageOfParent;
    private String _distributionStrategy;
    private String _receiverLimitationStrategy;
    private String _name;

    public PointTypeSoap() {
    }

    public static PointTypeSoap toSoapModel(PointType model) {
        PointTypeSoap soapModel = new PointTypeSoap();

        soapModel.setId(model.getId());
        soapModel.setParentPointTypeId(model.getParentPointTypeId());
        soapModel.setPercentageOfParent(model.getPercentageOfParent());
        soapModel.setDistributionStrategy(model.getDistributionStrategy());
        soapModel.setReceiverLimitationStrategy(model.getReceiverLimitationStrategy());
        soapModel.setName(model.getName());

        return soapModel;
    }

    public static PointTypeSoap[] toSoapModels(PointType[] models) {
        PointTypeSoap[] soapModels = new PointTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PointTypeSoap[][] toSoapModels(PointType[][] models) {
        PointTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PointTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new PointTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PointTypeSoap[] toSoapModels(List<PointType> models) {
        List<PointTypeSoap> soapModels = new ArrayList<PointTypeSoap>(models.size());

        for (PointType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PointTypeSoap[soapModels.size()]);
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

    public long getParentPointTypeId() {
        return _parentPointTypeId;
    }

    public void setParentPointTypeId(long parentPointTypeId) {
        _parentPointTypeId = parentPointTypeId;
    }

    public double getPercentageOfParent() {
        return _percentageOfParent;
    }

    public void setPercentageOfParent(double percentageOfParent) {
        _percentageOfParent = percentageOfParent;
    }

    public String getDistributionStrategy() {
        return _distributionStrategy;
    }

    public void setDistributionStrategy(String distributionStrategy) {
        _distributionStrategy = distributionStrategy;
    }

    public String getReceiverLimitationStrategy() {
        return _receiverLimitationStrategy;
    }

    public void setReceiverLimitationStrategy(String receiverLimitationStrategy) {
        _receiverLimitationStrategy = receiverLimitationStrategy;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
}
