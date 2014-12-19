package com.ext.portlet.model.impl;

import com.ext.portlet.model.MessagingMessageConversionType;
import com.ext.portlet.model.MessagingMessageConversionTypeModel;
import com.ext.portlet.model.MessagingMessageConversionTypeSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the MessagingMessageConversionType service. Represents a row in the &quot;xcolab_MessagingMessageConversionType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.ext.portlet.model.MessagingMessageConversionTypeModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MessagingMessageConversionTypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionTypeImpl
 * @see com.ext.portlet.model.MessagingMessageConversionType
 * @see com.ext.portlet.model.MessagingMessageConversionTypeModel
 * @generated
 */
@JSON(strict = true)
public class MessagingMessageConversionTypeModelImpl extends BaseModelImpl<MessagingMessageConversionType>
    implements MessagingMessageConversionTypeModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a messaging message conversion type model instance should use the {@link com.ext.portlet.model.MessagingMessageConversionType} interface instead.
     */
    public static final String TABLE_NAME = "xcolab_MessagingMessageConversionType";
    public static final Object[][] TABLE_COLUMNS = {
            { "typeId", Types.BIGINT },
            { "name", Types.VARCHAR },
            { "description", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table xcolab_MessagingMessageConversionType (typeId LONG not null primary key,name VARCHAR(1024) null,description VARCHAR(2048) null)";
    public static final String TABLE_SQL_DROP = "drop table xcolab_MessagingMessageConversionType";
    public static final String ORDER_BY_JPQL = " ORDER BY messagingMessageConversionType.typeId ASC";
    public static final String ORDER_BY_SQL = " ORDER BY xcolab_MessagingMessageConversionType.typeId ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.ext.portlet.model.MessagingMessageConversionType"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.ext.portlet.model.MessagingMessageConversionType"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.com.ext.portlet.model.MessagingMessageConversionType"),
            true);
    public static long NAME_COLUMN_BITMASK = 1L;
    public static long TYPEID_COLUMN_BITMASK = 2L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.com.ext.portlet.model.MessagingMessageConversionType"));
    private static ClassLoader _classLoader = MessagingMessageConversionType.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            MessagingMessageConversionType.class
        };
    private long _typeId;
    private String _name;
    private String _originalName;
    private String _description;
    private long _columnBitmask;
    private MessagingMessageConversionType _escapedModel;

    public MessagingMessageConversionTypeModelImpl() {
    }

    /**
     * Converts the soap model instance into a normal model instance.
     *
     * @param soapModel the soap model instance to convert
     * @return the normal model instance
     */
    public static MessagingMessageConversionType toModel(
        MessagingMessageConversionTypeSoap soapModel) {
        if (soapModel == null) {
            return null;
        }

        MessagingMessageConversionType model = new MessagingMessageConversionTypeImpl();

        model.setTypeId(soapModel.getTypeId());
        model.setName(soapModel.getName());
        model.setDescription(soapModel.getDescription());

        return model;
    }

    /**
     * Converts the soap model instances into normal model instances.
     *
     * @param soapModels the soap model instances to convert
     * @return the normal model instances
     */
    public static List<MessagingMessageConversionType> toModels(
        MessagingMessageConversionTypeSoap[] soapModels) {
        if (soapModels == null) {
            return null;
        }

        List<MessagingMessageConversionType> models = new ArrayList<MessagingMessageConversionType>(soapModels.length);

        for (MessagingMessageConversionTypeSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    @Override
    public long getPrimaryKey() {
        return _typeId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setTypeId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _typeId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Class<?> getModelClass() {
        return MessagingMessageConversionType.class;
    }

    @Override
    public String getModelClassName() {
        return MessagingMessageConversionType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("typeId", getTypeId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long typeId = (Long) attributes.get("typeId");

        if (typeId != null) {
            setTypeId(typeId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }
    }

    @JSON
    @Override
    public long getTypeId() {
        return _typeId;
    }

    @Override
    public void setTypeId(long typeId) {
        _typeId = typeId;
    }

    @JSON
    @Override
    public String getName() {
        if (_name == null) {
            return StringPool.BLANK;
        } else {
            return _name;
        }
    }

    @Override
    public void setName(String name) {
        _columnBitmask |= NAME_COLUMN_BITMASK;

        if (_originalName == null) {
            _originalName = _name;
        }

        _name = name;
    }

    public String getOriginalName() {
        return GetterUtil.getString(_originalName);
    }

    @JSON
    @Override
    public String getDescription() {
        if (_description == null) {
            return StringPool.BLANK;
        } else {
            return _description;
        }
    }

    @Override
    public void setDescription(String description) {
        _description = description;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
            MessagingMessageConversionType.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @Override
    public MessagingMessageConversionType toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (MessagingMessageConversionType) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        MessagingMessageConversionTypeImpl messagingMessageConversionTypeImpl = new MessagingMessageConversionTypeImpl();

        messagingMessageConversionTypeImpl.setTypeId(getTypeId());
        messagingMessageConversionTypeImpl.setName(getName());
        messagingMessageConversionTypeImpl.setDescription(getDescription());

        messagingMessageConversionTypeImpl.resetOriginalValues();

        return messagingMessageConversionTypeImpl;
    }

    @Override
    public int compareTo(
        MessagingMessageConversionType messagingMessageConversionType) {
        long primaryKey = messagingMessageConversionType.getPrimaryKey();

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

        if (!(obj instanceof MessagingMessageConversionType)) {
            return false;
        }

        MessagingMessageConversionType messagingMessageConversionType = (MessagingMessageConversionType) obj;

        long primaryKey = messagingMessageConversionType.getPrimaryKey();

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
    public void resetOriginalValues() {
        MessagingMessageConversionTypeModelImpl messagingMessageConversionTypeModelImpl =
            this;

        messagingMessageConversionTypeModelImpl._originalName = messagingMessageConversionTypeModelImpl._name;

        messagingMessageConversionTypeModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<MessagingMessageConversionType> toCacheModel() {
        MessagingMessageConversionTypeCacheModel messagingMessageConversionTypeCacheModel =
            new MessagingMessageConversionTypeCacheModel();

        messagingMessageConversionTypeCacheModel.typeId = getTypeId();

        messagingMessageConversionTypeCacheModel.name = getName();

        String name = messagingMessageConversionTypeCacheModel.name;

        if ((name != null) && (name.length() == 0)) {
            messagingMessageConversionTypeCacheModel.name = null;
        }

        messagingMessageConversionTypeCacheModel.description = getDescription();

        String description = messagingMessageConversionTypeCacheModel.description;

        if ((description != null) && (description.length() == 0)) {
            messagingMessageConversionTypeCacheModel.description = null;
        }

        return messagingMessageConversionTypeCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{typeId=");
        sb.append(getTypeId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.MessagingMessageConversionType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>typeId</column-name><column-value><![CDATA[");
        sb.append(getTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
