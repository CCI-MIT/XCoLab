package com.ext.portlet.model;

import com.ext.portlet.service.ModelCategoryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ModelCategoryClp extends BaseModelImpl<ModelCategory>
    implements ModelCategory {
    private long _modelCategoryPK;
    private String _modelCategoryName;
    private String _modelCategoryDescription;
    private int _modelCategoryDisplayWeight;

    public ModelCategoryClp() {
    }

    public Class<?> getModelClass() {
        return ModelCategory.class;
    }

    public String getModelClassName() {
        return ModelCategory.class.getName();
    }

    public long getPrimaryKey() {
        return _modelCategoryPK;
    }

    public void setPrimaryKey(long primaryKey) {
        setModelCategoryPK(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_modelCategoryPK);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getModelCategoryPK() {
        return _modelCategoryPK;
    }

    public void setModelCategoryPK(long modelCategoryPK) {
        _modelCategoryPK = modelCategoryPK;
    }

    public String getModelCategoryName() {
        return _modelCategoryName;
    }

    public void setModelCategoryName(String modelCategoryName) {
        _modelCategoryName = modelCategoryName;
    }

    public String getModelCategoryDescription() {
        return _modelCategoryDescription;
    }

    public void setModelCategoryDescription(String modelCategoryDescription) {
        _modelCategoryDescription = modelCategoryDescription;
    }

    public int getModelCategoryDisplayWeight() {
        return _modelCategoryDisplayWeight;
    }

    public void setModelCategoryDisplayWeight(int modelCategoryDisplayWeight) {
        _modelCategoryDisplayWeight = modelCategoryDisplayWeight;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelCategoryLocalServiceUtil.addModelCategory(this);
        } else {
            ModelCategoryLocalServiceUtil.updateModelCategory(this);
        }
    }

    @Override
    public ModelCategory toEscapedModel() {
        return (ModelCategory) Proxy.newProxyInstance(ModelCategory.class.getClassLoader(),
            new Class[] { ModelCategory.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelCategoryClp clone = new ModelCategoryClp();

        clone.setModelCategoryPK(getModelCategoryPK());
        clone.setModelCategoryName(getModelCategoryName());
        clone.setModelCategoryDescription(getModelCategoryDescription());
        clone.setModelCategoryDisplayWeight(getModelCategoryDisplayWeight());

        return clone;
    }

    public int compareTo(ModelCategory modelCategory) {
        long primaryKey = modelCategory.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelCategoryClp modelCategory = null;

        try {
            modelCategory = (ModelCategoryClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = modelCategory.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{modelCategoryPK=");
        sb.append(getModelCategoryPK());
        sb.append(", modelCategoryName=");
        sb.append(getModelCategoryName());
        sb.append(", modelCategoryDescription=");
        sb.append(getModelCategoryDescription());
        sb.append(", modelCategoryDisplayWeight=");
        sb.append(getModelCategoryDisplayWeight());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ModelCategory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelCategoryPK</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelCategoryName</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelCategoryDescription</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelCategoryDisplayWeight</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryDisplayWeight());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
