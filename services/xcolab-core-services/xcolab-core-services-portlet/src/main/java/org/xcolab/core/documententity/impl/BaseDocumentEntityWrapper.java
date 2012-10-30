package org.xcolab.core.documententity.impl;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jcr.Node;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.DocumentEntityWrapper;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class BaseDocumentEntityWrapper implements DocumentEntityWrapper, HasNode {
	protected DocumentEntity wrapped;
	private final static Log _log = LogFactoryUtil.getLog(BaseDocumentEntityWrapper.class);
	
	protected BaseDocumentEntityWrapper(DocumentEntity wrapped) {
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

	public DocumentEntity getChild(String property) {
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

	public DocumentEntity addChild(String property) throws DocumentEntityException {
		return wrapped.addChild(property);
	}

	public DocumentEntity getWrapped() {
		return wrapped;
	}

	public <T extends DocumentEntityWrapper> T getEntity(String property,
			Class<T> type) throws DocumentEntityException {
		return wrapEntity(getChild(property), type);
	}

	public <T extends DocumentEntityWrapper> T addEntity(String property,
			Class<T> type) throws DocumentEntityException {
		return wrapEntity(addChild(property), type);
	}

	
	private <T extends DocumentEntityWrapper> T wrapEntity(DocumentEntity entity, Class<T> type) throws DocumentEntityException {
		try {
			return type.getConstructor(DocumentEntity.class).newInstance(entity);
		} catch (InstantiationException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new DocumentEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (IllegalAccessException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new DocumentEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (IllegalArgumentException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new DocumentEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (InvocationTargetException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new DocumentEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (NoSuchMethodException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new DocumentEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		} catch (SecurityException e) {
			_log.error("Can't create wrapped document entity for type: " + type.getName(), e);
			throw new DocumentEntityException("Can't create wrapped document entity for type: " + type.getName(), e);
		}
	}
	
	private <LT extends DocumentEntityWrapper, IT extends LT> LT[] wrapArray(DocumentEntity[] entities, Class<LT> listType, Class<IT> itemType) throws DocumentEntityException {
        LT[] wrapped = (LT[]) Array.newInstance(listType, entities.length);
        for (int i=0; i < entities.length; i++) {
            wrapped[i] = wrapEntity(entities[i], itemType);
        }
        
        return wrapped;
	}

	public Node getNode() {
		if (! HasNode.class.isAssignableFrom(wrapped.getClass())) {
			_log.error("Wrapped object doesn't have a node");
			return null;
		}
		return ((HasNode) wrapped).getNode();
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

    public DocumentEntity[] getChildren(String property) {
        return wrapped.getChildren(property);
    }

    public DocumentEntity getReference(String property) {
        return wrapped.getReference(property);
    }

    public DocumentEntity[] getReferences(String property) {
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

    public void setReference(String property, DocumentEntity entity) {
        wrapped.setReference(property, entity);
    }

    public void setReferences(String property, DocumentEntity[] entities) {
        wrapped.setReferences(property, entities);
    }

    public void addReference(String property, DocumentEntity entity) {
        wrapped.addReference(property, entity);
    }

    public void removeProperty(String propertyName) throws DocumentEntityException {
        wrapped.removeProperty(propertyName);
    }

    public <T extends DocumentEntityWrapper> T getReference(String property, Class<T> type)
            throws DocumentEntityException {
        return wrapEntity(wrapped.getReference(property), type);
    }

    public <LT extends DocumentEntityWrapper, IT extends LT> LT[] getReferences(String property,
            Class<LT> listType, Class<IT> itemType) throws DocumentEntityException {
        return wrapArray(wrapped.getReferences(property), listType, itemType);
    }

    public <LT extends DocumentEntityWrapper, IT extends LT> LT[] getChildren(String property, Class<LT> listType,
            Class<IT> itemType) throws DocumentEntityException {
        return wrapArray(wrapped.getChildren(property), listType, itemType);
    }



}
