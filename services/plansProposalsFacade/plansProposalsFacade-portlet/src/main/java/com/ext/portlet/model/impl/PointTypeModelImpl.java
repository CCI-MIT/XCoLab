package com.ext.portlet.model.impl;

import com.ext.portlet.model.PointType;
import com.ext.portlet.model.PointTypeModel;
import com.ext.portlet.model.PointTypeSoap;

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
 * The base model implementation for the PointType service. Represents a row in the &quot;xcolab_PointType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.ext.portlet.model.PointTypeModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PointTypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointTypeImpl
 * @see com.ext.portlet.model.PointType
 * @see com.ext.portlet.model.PointTypeModel
 * @generated
 */
@JSON(strict = true)
public class PointTypeModelImpl extends BaseModelImpl<PointType>
    implements PointTypeModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a point type model instance should use the {@link com.ext.portlet.model.PointType} interface instead.
     */
    public static final String TABLE_NAME = "xcolab_PointType";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", Types.BIGINT },
            { "parentPointTypeId", Types.BIGINT },
            { "percentageOfParent", Types.DOUBLE },
            { "distributionStrategy", Types.VARCHAR },
            { "receiverLimitationStrategy", Types.VARCHAR },
            { "name", Types.VARCHAR },
            { "sort", Types.BIGINT }
        };
    public static final String TABLE_SQL_CREATE = "create table xcolab_PointType (id_ LONG not null primary key,parentPointTypeId LONG,percentageOfParent DOUBLE,distributionStrategy VARCHAR(75) null,receiverLimitationStrategy VARCHAR(75) null,name VARCHAR(75) null,sort LONG)";
    public static final String TABLE_SQL_DROP = "drop table xcolab_PointType";
    public static final String ORDER_BY_JPQL = " ORDER BY pointType.id ASC";
    public static final String ORDER_BY_SQL = " ORDER BY xcolab_PointType.id_ ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.ext.portlet.model.PointType"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.ext.portlet.model.PointType"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.com.ext.portlet.model.PointType"),
            true);
    public static long PARENTPOINTTYPEID_COLUMN_BITMASK = 1L;
    public static long ID_COLUMN_BITMASK = 2L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.com.ext.portlet.model.PointType"));
    private static ClassLoader _classLoader = PointType.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            PointType.class
        };
    private long _id;
    private long _parentPointTypeId;
    private long _originalParentPointTypeId;
    private boolean _setOriginalParentPointTypeId;
    private double _percentageOfParent;
    private String _distributionStrategy;
    private String _receiverLimitationStrategy;
    private String _name;
    private long _sort;
    private long _columnBitmask;
    private PointType _escapedModel;

    public PointTypeModelImpl() {
    }

    /**
     * Converts the soap model instance into a normal model instance.
     *
     * @param soapModel the soap model instance to convert
     * @return the normal model instance
     */
    public static PointType toModel(PointTypeSoap soapModel) {
        if (soapModel == null) {
            return null;
        }

        PointType model = new PointTypeImpl();

        model.setId(soapModel.getId());
        model.setParentPointTypeId(soapModel.getParentPointTypeId());
        model.setPercentageOfParent(soapModel.getPercentageOfParent());
        model.setDistributionStrategy(soapModel.getDistributionStrategy());
        model.setReceiverLimitationStrategy(soapModel.getReceiverLimitationStrategy());
        model.setName(soapModel.getName());
        model.setSort(soapModel.getSort());

        return model;
    }

    /**
     * Converts the soap model instances into normal model instances.
     *
     * @param soapModels the soap model instances to convert
     * @return the normal model instances
     */
    public static List<PointType> toModels(PointTypeSoap[] soapModels) {
        if (soapModels == null) {
            return null;
        }

        List<PointType> models = new ArrayList<PointType>(soapModels.length);

        for (PointTypeSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
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
    public Class<?> getModelClass() {
        return PointType.class;
    }

    @Override
    public String getModelClassName() {
        return PointType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("parentPointTypeId", getParentPointTypeId());
        attributes.put("percentageOfParent", getPercentageOfParent());
        attributes.put("distributionStrategy", getDistributionStrategy());
        attributes.put("receiverLimitationStrategy",
            getReceiverLimitationStrategy());
        attributes.put("name", getName());
        attributes.put("sort", getSort());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long parentPointTypeId = (Long) attributes.get("parentPointTypeId");

        if (parentPointTypeId != null) {
            setParentPointTypeId(parentPointTypeId);
        }

        Double percentageOfParent = (Double) attributes.get(
                "percentageOfParent");

        if (percentageOfParent != null) {
            setPercentageOfParent(percentageOfParent);
        }

        String distributionStrategy = (String) attributes.get(
                "distributionStrategy");

        if (distributionStrategy != null) {
            setDistributionStrategy(distributionStrategy);
        }

        String receiverLimitationStrategy = (String) attributes.get(
                "receiverLimitationStrategy");

        if (receiverLimitationStrategy != null) {
            setReceiverLimitationStrategy(receiverLimitationStrategy);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Long sort = (Long) attributes.get("sort");

        if (sort != null) {
            setSort(sort);
        }
    }

    @JSON
    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;
    }

    @JSON
    @Override
    public long getParentPointTypeId() {
        return _parentPointTypeId;
    }

    @Override
    public void setParentPointTypeId(long parentPointTypeId) {
        _columnBitmask |= PARENTPOINTTYPEID_COLUMN_BITMASK;

        if (!_setOriginalParentPointTypeId) {
            _setOriginalParentPointTypeId = true;

            _originalParentPointTypeId = _parentPointTypeId;
        }

        _parentPointTypeId = parentPointTypeId;
    }

    public long getOriginalParentPointTypeId() {
        return _originalParentPointTypeId;
    }

    @JSON
    @Override
    public double getPercentageOfParent() {
        return _percentageOfParent;
    }

    @Override
    public void setPercentageOfParent(double percentageOfParent) {
        _percentageOfParent = percentageOfParent;
    }

    @JSON
    @Override
    public String getDistributionStrategy() {
        if (_distributionStrategy == null) {
            return StringPool.BLANK;
        } else {
            return _distributionStrategy;
        }
    }

    @Override
    public void setDistributionStrategy(String distributionStrategy) {
        _distributionStrategy = distributionStrategy;
    }

    @JSON
    @Override
    public String getReceiverLimitationStrategy() {
        if (_receiverLimitationStrategy == null) {
            return StringPool.BLANK;
        } else {
            return _receiverLimitationStrategy;
        }
    }

    @Override
    public void setReceiverLimitationStrategy(String receiverLimitationStrategy) {
        _receiverLimitationStrategy = receiverLimitationStrategy;
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
        _name = name;
    }

    @JSON
    @Override
    public long getSort() {
        return _sort;
    }

    @Override
    public void setSort(long sort) {
        _sort = sort;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
            PointType.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @Override
    public PointType toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (PointType) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        PointTypeImpl pointTypeImpl = new PointTypeImpl();

        pointTypeImpl.setId(getId());
        pointTypeImpl.setParentPointTypeId(getParentPointTypeId());
        pointTypeImpl.setPercentageOfParent(getPercentageOfParent());
        pointTypeImpl.setDistributionStrategy(getDistributionStrategy());
        pointTypeImpl.setReceiverLimitationStrategy(getReceiverLimitationStrategy());
        pointTypeImpl.setName(getName());
        pointTypeImpl.setSort(getSort());

        pointTypeImpl.resetOriginalValues();

        return pointTypeImpl;
    }

    @Override
    public int compareTo(PointType pointType) {
        long primaryKey = pointType.getPrimaryKey();

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

        if (!(obj instanceof PointType)) {
            return false;
        }

        PointType pointType = (PointType) obj;

        long primaryKey = pointType.getPrimaryKey();

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
        PointTypeModelImpl pointTypeModelImpl = this;

        pointTypeModelImpl._originalParentPointTypeId = pointTypeModelImpl._parentPointTypeId;

        pointTypeModelImpl._setOriginalParentPointTypeId = false;

        pointTypeModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<PointType> toCacheModel() {
        PointTypeCacheModel pointTypeCacheModel = new PointTypeCacheModel();

        pointTypeCacheModel.id = getId();

        pointTypeCacheModel.parentPointTypeId = getParentPointTypeId();

        pointTypeCacheModel.percentageOfParent = getPercentageOfParent();

        pointTypeCacheModel.distributionStrategy = getDistributionStrategy();

        String distributionStrategy = pointTypeCacheModel.distributionStrategy;

        if ((distributionStrategy != null) &&
                (distributionStrategy.length() == 0)) {
            pointTypeCacheModel.distributionStrategy = null;
        }

        pointTypeCacheModel.receiverLimitationStrategy = getReceiverLimitationStrategy();

        String receiverLimitationStrategy = pointTypeCacheModel.receiverLimitationStrategy;

        if ((receiverLimitationStrategy != null) &&
                (receiverLimitationStrategy.length() == 0)) {
            pointTypeCacheModel.receiverLimitationStrategy = null;
        }

        pointTypeCacheModel.name = getName();

        String name = pointTypeCacheModel.name;

        if ((name != null) && (name.length() == 0)) {
            pointTypeCacheModel.name = null;
        }

        pointTypeCacheModel.sort = getSort();

        return pointTypeCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", parentPointTypeId=");
        sb.append(getParentPointTypeId());
        sb.append(", percentageOfParent=");
        sb.append(getPercentageOfParent());
        sb.append(", distributionStrategy=");
        sb.append(getDistributionStrategy());
        sb.append(", receiverLimitationStrategy=");
        sb.append(getReceiverLimitationStrategy());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", sort=");
        sb.append(getSort());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PointType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentPointTypeId</column-name><column-value><![CDATA[");
        sb.append(getParentPointTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>percentageOfParent</column-name><column-value><![CDATA[");
        sb.append(getPercentageOfParent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>distributionStrategy</column-name><column-value><![CDATA[");
        sb.append(getDistributionStrategy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>receiverLimitationStrategy</column-name><column-value><![CDATA[");
        sb.append(getReceiverLimitationStrategy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sort</column-name><column-value><![CDATA[");
        sb.append(getSort());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
