package org.xcolab.services.sample.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.xcolab.services.sample.service.http.SampleEntityServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       org.xcolab.services.sample.service.http.SampleEntityServiceSoap
 * @generated
 */
public class SampleEntitySoap implements Serializable {
    private long _id;
    private String _content;
    private Date _created;
    private long _authorId;

    public SampleEntitySoap() {
    }

    public static SampleEntitySoap toSoapModel(SampleEntity model) {
        SampleEntitySoap soapModel = new SampleEntitySoap();

        soapModel.setId(model.getId());
        soapModel.setContent(model.getContent());
        soapModel.setCreated(model.getCreated());
        soapModel.setAuthorId(model.getAuthorId());

        return soapModel;
    }

    public static SampleEntitySoap[] toSoapModels(SampleEntity[] models) {
        SampleEntitySoap[] soapModels = new SampleEntitySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static SampleEntitySoap[][] toSoapModels(SampleEntity[][] models) {
        SampleEntitySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new SampleEntitySoap[models.length][models[0].length];
        } else {
            soapModels = new SampleEntitySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static SampleEntitySoap[] toSoapModels(List<SampleEntity> models) {
        List<SampleEntitySoap> soapModels = new ArrayList<SampleEntitySoap>(models.size());

        for (SampleEntity model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new SampleEntitySoap[soapModels.size()]);
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

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }
}
