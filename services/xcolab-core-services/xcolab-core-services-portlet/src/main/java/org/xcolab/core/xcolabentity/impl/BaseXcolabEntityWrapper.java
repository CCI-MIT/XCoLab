package org.xcolab.core.xcolabentity.impl;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Calendar;

import org.xcolab.core.xcolabententity.EntityVersion;
import org.xcolab.core.xcolabententity.XColabEntity;
import org.xcolab.core.xcolabententity.XColabEntityException;
import org.xcolab.core.xcolabententity.XColabEntityWrapper;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class BaseXcolabEntityWrapper implements XColabEntityWrapper {
	protected XColabEntity wrapped;
	private final static Log _log = LogFactoryUtil.getLog(BaseXcolabEntityWrapper.class);
	
	protected BaseXcolabEntityWrapper(XColabEntity wrapped) {
		this.wrapped = wrapped;
	}

	public boolean hasProperty(String property) {
		return wrapped.hasProperty(property);
	}

	public Long getLong(String property) {
		return wrapped.getLong(property);
	}

	public Double getDouble(String property) {
		return wrapped.getDouble(property);
	}

	public BigDecimal getDecimal(String property) {
		return wrapped.getDecimal(property);
	}

	public Boolean getBoolean(String property) {
		return wrapped.getBoolean(property);
	}

	public Calendar getDate(String property) {
		return wrapped.getDate(property);
	}

	public String getString(String property) {
		return wrapped.getString(property);
	}

	public XColabEntity getChild(String property) {
		return wrapped.getChild(property);
	}

	public void setLong(String property, Long value) {
		wrapped.setLong(property, value);
	}

	public void setDouble(String property, Double value) {
		wrapped.setDouble(property, value);
	}

	public void setDecimal(String property, BigDecimal decimal) {
		wrapped.setDecimal(property, decimal);
	}

	public void setBoolean(String property, Boolean value) {
		wrapped.setBoolean(property, value);
	}

	public void setDate(String property, Calendar date) {
		wrapped.setDate(property, date);
	}

	public void setString(String property, String value) {
		wrapped.setString(property, value);
	}

	public XColabEntity addChild(String property) throws XColabEntityException {
		return wrapped.addChild(property);
	}

	public XColabEntity getWrapped() {
		return wrapped;
	}

	public <T extends XColabEntityWrapper> T getEntity(String property,
			Class<T> type) throws XColabEntityException {
		return wrapEntity(getChild(property), type);
	}

	public <T extends XColabEntityWrapper> T addEntity(String property,
			Class<T> type) throws XColabEntityException {
		return wrapEntity(addChild(property), type);
	}

	
	private <T extends XColabEntityWrapper> T wrapEntity(XColabEntity entity, Class<T> type) throws XColabEntityException {
		try {
			return type.getConstructor(XColabEntity.class).newInstance(entity);
		} catch (InstantiationException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (IllegalAccessException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (IllegalArgumentException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (InvocationTargetException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (NoSuchMethodException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (SecurityException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new XColabEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		}
	}
	
	private <LT extends XColabEntityWrapper, IT extends LT> LT[] wrapArray(XColabEntity[] entities, Class<LT> listType, Class<IT> itemType) throws XColabEntityException {
        LT[] wrapped = (LT[]) Array.newInstance(listType, entities.length);
        for (int i=0; i < entities.length; i++) {
            wrapped[i] = wrapEntity(entities[i], itemType);
        }
        
        return wrapped;
	}

	public Object getId() {
		return wrapped.getId();
	}

    public Long[] getLongs(String property) {
        return wrapped.getLongs(property);
    }

    public Double[] getDoubles(String property) {
        return wrapped.getDoubles(property);
    }

    public BigDecimal[] getDecimals(String property) {
        return wrapped.getDecimals(property);
    }

    public Boolean[] getBooleans(String property) {
        return wrapped.getBooleans(property);
    }

    public Calendar[] getDates(String property) {
        return wrapped.getDates(property);
    }

    public String[] getStrings(String property) {
        return wrapped.getStrings(property);
    }

    public XColabEntity[] getChildren(String property) {
        return wrapped.getChildren(property);
    }

    public XColabEntity getReference(String property) {
        return wrapped.getReference(property);
    }

    public XColabEntity[] getReferences(String property) {
        return wrapped.getReferences(property);
    }

    public void setLongs(String property, Long[] values) {
        wrapped.setLongs(property, values);
    }

    public void addLong(String property, Long value) {
        wrapped.addLong(property, value);
    }

    public void setDoubles(String property, Double[] values) {
        wrapped.setDoubles(property, values);
    }

    public void addDouble(String property, Double value) {
        wrapped.addDouble(property, value);
    }

    public void setDecimals(String property, BigDecimal[] values) {
        wrapped.setDecimals(property, values);
    }

    public void addDecimal(String property, BigDecimal value) {
        wrapped.addDecimal(property, value);
    }

    public void setBooleans(String property, Boolean[] values) {
        wrapped.setBooleans(property, values);
    }

    public void addBoolean(String property, Boolean value) {
        wrapped.addBoolean(property, value);
    }

    public void setDates(String property, Calendar[] dates) {
        wrapped.setDates(property, dates);
    }

    public void addDate(String property, Calendar date) {
        wrapped.addDate(property, date);
    }

    public void setStrings(String property, String[] values) {
        wrapped.setStrings(property, values);
    }

    public void addString(String property, String value) {
        wrapped.addString(property, value);
    }

    public void setReference(String property, XColabEntity entity) {
        wrapped.setReference(property, entity);
    }

    public void setReferences(String property, XColabEntity[] entities) {
        wrapped.setReferences(property, entities);
    }

    public void addReference(String property, XColabEntity entity) {
        wrapped.addReference(property, entity);
    }

    public void removeProperty(String propertyName) throws XColabEntityException {
        wrapped.removeProperty(propertyName);
    }

    public <T extends XColabEntityWrapper> T getReference(String property, Class<T> type)
            throws XColabEntityException {
        return wrapEntity(wrapped.getReference(property), type);
    }

    public <LT extends XColabEntityWrapper, IT extends LT> LT[] getReferences(String property,
            Class<LT> listType, Class<IT> itemType) throws XColabEntityException {
        return wrapArray(wrapped.getReferences(property), listType, itemType);
    }

    public <LT extends XColabEntityWrapper, IT extends LT> LT[] getChildren(String property, Class<LT> listType,
            Class<IT> itemType) throws XColabEntityException {
        return wrapArray(wrapped.getChildren(property), listType, itemType);
    }

    @Override
    public XColabEntity getParent() throws XColabEntityException {
        return wrapped.getParent();
    }

    @Override
    public EntityVersion<XColabEntity> getVersion() throws XColabEntityException {
        return wrapped.getVersion();
    }




}
