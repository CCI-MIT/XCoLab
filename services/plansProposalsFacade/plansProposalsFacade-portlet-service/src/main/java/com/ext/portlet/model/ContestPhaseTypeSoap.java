package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ContestPhaseTypeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ContestPhaseTypeServiceSoap
 * @generated
 */
public class ContestPhaseTypeSoap implements Serializable {
    private long _id;
    private String _name;
    private String _description;
    private String _status;
    private boolean _fellowScreeningActiveDefault;
    private String _contestPhaseAutopromoteDefault;
    private boolean _invisible;
    private int _pointsAccessible;

    public ContestPhaseTypeSoap() {
    }

    public static ContestPhaseTypeSoap toSoapModel(ContestPhaseType model) {
        ContestPhaseTypeSoap soapModel = new ContestPhaseTypeSoap();

        soapModel.setId(model.getId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setStatus(model.getStatus());
        soapModel.setFellowScreeningActiveDefault(model.getFellowScreeningActiveDefault());
        soapModel.setContestPhaseAutopromoteDefault(model.getContestPhaseAutopromoteDefault());
        soapModel.setInvisible(model.getInvisible());
        soapModel.setPointsAccessible(model.getPointsAccessible());

        return soapModel;
    }

    public static ContestPhaseTypeSoap[] toSoapModels(ContestPhaseType[] models) {
        ContestPhaseTypeSoap[] soapModels = new ContestPhaseTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseTypeSoap[][] toSoapModels(
        ContestPhaseType[][] models) {
        ContestPhaseTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestPhaseTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestPhaseTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseTypeSoap[] toSoapModels(
        List<ContestPhaseType> models) {
        List<ContestPhaseTypeSoap> soapModels = new ArrayList<ContestPhaseTypeSoap>(models.size());

        for (ContestPhaseType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestPhaseTypeSoap[soapModels.size()]);
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

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public boolean getFellowScreeningActiveDefault() {
        return _fellowScreeningActiveDefault;
    }

    public boolean isFellowScreeningActiveDefault() {
        return _fellowScreeningActiveDefault;
    }

    public void setFellowScreeningActiveDefault(
        boolean fellowScreeningActiveDefault) {
        _fellowScreeningActiveDefault = fellowScreeningActiveDefault;
    }

    public String getContestPhaseAutopromoteDefault() {
        return _contestPhaseAutopromoteDefault;
    }

    public void setContestPhaseAutopromoteDefault(
        String contestPhaseAutopromoteDefault) {
        _contestPhaseAutopromoteDefault = contestPhaseAutopromoteDefault;
    }

    public boolean getInvisible() {
        return _invisible;
    }

    public boolean isInvisible() {
        return _invisible;
    }

    public void setInvisible(boolean invisible) {
        _invisible = invisible;
    }

    public int getPointsAccessible() {
        return _pointsAccessible;
    }

    public void setPointsAccessible(int pointsAccessible) {
        _pointsAccessible = pointsAccessible;
    }
}
