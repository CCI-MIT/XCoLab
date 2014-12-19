package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.OntologyTermEntityLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class OntologyTermEntityClp extends BaseModelImpl<OntologyTermEntity>
    implements OntologyTermEntity {
    private long _id;
    private long _termId;
    private long _classNameId;
    private long _classPK;
    private BaseModel<?> _ontologyTermEntityRemoteModel;

    public OntologyTermEntityClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return OntologyTermEntity.class;
    }

    @Override
    public String getModelClassName() {
        return OntologyTermEntity.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _id;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("termId", getTermId());
        attributes.put("classNameId", getClassNameId());
        attributes.put("classPK", getClassPK());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long termId = (Long) attributes.get("termId");

        if (termId != null) {
            setTermId(termId);
        }

        Long classNameId = (Long) attributes.get("classNameId");

        if (classNameId != null) {
            setClassNameId(classNameId);
        }

        Long classPK = (Long) attributes.get("classPK");

        if (classPK != null) {
            setClassPK(classPK);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_ontologyTermEntityRemoteModel != null) {
            try {
                Class<?> clazz = _ontologyTermEntityRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_ontologyTermEntityRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getTermId() {
        return _termId;
    }

    @Override
    public void setTermId(long termId) {
        _termId = termId;

        if (_ontologyTermEntityRemoteModel != null) {
            try {
                Class<?> clazz = _ontologyTermEntityRemoteModel.getClass();

                Method method = clazz.getMethod("setTermId", long.class);

                method.invoke(_ontologyTermEntityRemoteModel, termId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getClassName() {
        if (getClassNameId() <= 0) {
            return StringPool.BLANK;
        }

        return PortalUtil.getClassName(getClassNameId());
    }

    @Override
    public void setClassName(String className) {
        long classNameId = 0;

        if (Validator.isNotNull(className)) {
            classNameId = PortalUtil.getClassNameId(className);
        }

        setClassNameId(classNameId);
    }

    @Override
    public long getClassNameId() {
        return _classNameId;
    }

    @Override
    public void setClassNameId(long classNameId) {
        _classNameId = classNameId;

        if (_ontologyTermEntityRemoteModel != null) {
            try {
                Class<?> clazz = _ontologyTermEntityRemoteModel.getClass();

                Method method = clazz.getMethod("setClassNameId", long.class);

                method.invoke(_ontologyTermEntityRemoteModel, classNameId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getClassPK() {
        return _classPK;
    }

    @Override
    public void setClassPK(long classPK) {
        _classPK = classPK;

        if (_ontologyTermEntityRemoteModel != null) {
            try {
                Class<?> clazz = _ontologyTermEntityRemoteModel.getClass();

                Method method = clazz.getMethod("setClassPK", long.class);

                method.invoke(_ontologyTermEntityRemoteModel, classPK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getOntologyTermEntityRemoteModel() {
        return _ontologyTermEntityRemoteModel;
    }

    public void setOntologyTermEntityRemoteModel(
        BaseModel<?> ontologyTermEntityRemoteModel) {
        _ontologyTermEntityRemoteModel = ontologyTermEntityRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _ontologyTermEntityRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_ontologyTermEntityRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            OntologyTermEntityLocalServiceUtil.addOntologyTermEntity(this);
        } else {
            OntologyTermEntityLocalServiceUtil.updateOntologyTermEntity(this);
        }
    }

    @Override
    public OntologyTermEntity toEscapedModel() {
        return (OntologyTermEntity) ProxyUtil.newProxyInstance(OntologyTermEntity.class.getClassLoader(),
            new Class[] { OntologyTermEntity.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        OntologyTermEntityClp clone = new OntologyTermEntityClp();

        clone.setId(getId());
        clone.setTermId(getTermId());
        clone.setClassNameId(getClassNameId());
        clone.setClassPK(getClassPK());

        return clone;
    }

    @Override
    public int compareTo(OntologyTermEntity ontologyTermEntity) {
        long primaryKey = ontologyTermEntity.getPrimaryKey();

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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OntologyTermEntityClp)) {
            return false;
        }

        OntologyTermEntityClp ontologyTermEntity = (OntologyTermEntityClp) obj;

        long primaryKey = ontologyTermEntity.getPrimaryKey();

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

        sb.append("{id=");
        sb.append(getId());
        sb.append(", termId=");
        sb.append(getTermId());
        sb.append(", classNameId=");
        sb.append(getClassNameId());
        sb.append(", classPK=");
        sb.append(getClassPK());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.OntologyTermEntity");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>termId</column-name><column-value><![CDATA[");
        sb.append(getTermId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>classNameId</column-name><column-value><![CDATA[");
        sb.append(getClassNameId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>classPK</column-name><column-value><![CDATA[");
        sb.append(getClassPK());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
