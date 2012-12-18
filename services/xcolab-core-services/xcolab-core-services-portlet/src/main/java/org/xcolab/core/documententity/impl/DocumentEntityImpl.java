package org.xcolab.core.documententity.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFactory;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.Version;
import javax.jcr.version.VersionException;
import javax.jcr.version.VersionIterator;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.EntityVersion;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


public class DocumentEntityImpl implements DocumentEntity, HasNode {

    private final Node node;
    //
    private final Log _log = LogFactoryUtil.getLog(DocumentEntityImpl.class);
    

    public DocumentEntityImpl(Node node) {
        this.node = node;
    }

    public boolean hasProperty(String property) {
        try {
            return node.hasProperty(property) || node.hasNode(property);
        } catch (RepositoryException e) {
            _log.error("Can't fetch property [" + property + "] information from node [" + node + "]", e);
            return false;
        }
    }

    public Long getLong(String property) {
        return getTypedValue(property, Long.class);
    }

    public Long[] getLongs(String property) {
        return getTypedValue(property, Long[].class);
    }

    public Double getDouble(String property) {
        return getTypedValue(property, Double.class);
    }
    
    public Double[] getDoubles(String property) {
        return getTypedValue(property, Double[].class);
    }

    public BigDecimal getDecimal(String property) {
        return getTypedValue(property, BigDecimal.class);
    }
    
    public BigDecimal[] getDecimals(String property) {
        return getTypedValue(property, BigDecimal[].class);
    }

    public Boolean getBoolean(String property) {
        return getTypedValue(property, Boolean.class);
    }
    
    public Boolean[] getBooleans(String property) {
        return getTypedValue(property, Boolean[].class);
    }

    public Calendar getDate(String property) {
        return getTypedValue(property, Calendar.class);
    }
    
    public Calendar[] getDates(String property) {
        return getTypedValue(property, Calendar[].class);
    }

    public String getString(String property) {
        return getTypedValue(property, String.class);
    }
    
    public String[] getStrings(String property) {
        return getTypedValue(property, String[].class);
    }

    public DocumentEntity getReference(String property) {
        return getTypedValue(property, DocumentEntityImpl.class);
    }
    
    public DocumentEntity[] getReferences(String property) {
        return getTypedValue(property, DocumentEntityImpl[].class);
    }

    public void setLong(String property, Long value) {
        setValueInternal(property, value);
    }
    
    public void setLongs(String property, Long[] value) {
        setValueInternal(property, value);
    }
    
    public void addLong(String property, Long value) {
        addValueInternal(property, value);
    }

    public void setDouble(String property, Double value) {
        setValueInternal(property, value);
    }
    
    public void setDoubles(String property, Double[] value) {
        setValueInternal(property, value);
    }

    public void addDouble(String property, Double value) {
        addValueInternal(property, value);
    }
    
    public void setDecimal(String property, BigDecimal value) {
        setValueInternal(property, value);
    }

    public void setDecimals(String property, BigDecimal[] value) {
        setValueInternal(property, value);
    }

    public void addDecimal(String property, BigDecimal value) {
        addValueInternal(property, value);
    }
    
    public void setBoolean(String property, Boolean value) {
        setValueInternal(property, value);
    }

    public void setBooleans(String property, Boolean[] value) {
        setValueInternal(property, value);
    }

    public void addBoolean(String property, Boolean value) {
        addValueInternal(property, value);
    }
    
    public void setDate(String property, Calendar value) {
        setValueInternal(property, value);
    }
    
    public void setDates(String property, Calendar[] value) {
        setValueInternal(property, value);
    }

    public void addDate(String property, Calendar value) {
        addValueInternal(property, value);
    }

    public void setString(String property, String value) {
        setValueInternal(property, value);
    }

    public void setStrings(String property, String[] value) {
        setValueInternal(property, value);
    }

    public void addString(String property, String value) {
        addValueInternal(property, value);
    }

    public void setReference(String property, DocumentEntity entity) {
        setValueInternal(property, entity);
    }

    public void setReferences(String property, DocumentEntity[] entity) {
        setValueInternal(property, entity);
    }
    
    public void addReference(String property, DocumentEntity entity) {
        addValueInternal(property, entity);
    }
    
    public String toString() {
        return "BaseDocumentEntity[" + node + "]";
    }

    /**
     * Gets value object for given property.
     * 
     * 
     * @return Value object if there is no exception, null if exception was
     *         thrown when retrieving field
     */
    private Value getValue(String property) {
        try {
            return node.getProperty(property).getValue();
        } catch (PathNotFoundException e) {
            try {
                _log.warn(
                        "Can't find property " + property + " on object: " + node + "\tuuid: " + node.getIdentifier(),
                        e);
            } catch (RepositoryException e1) {
                _log.warn("Can't get identifier for node: " + node, e1);
            }
        } catch (RepositoryException e) {
            _log.warn("Can't access field: " + property + " on node: " + node, e);
        }
        return null;
    }

    /**
     * Gets value object for given property.
     * 
     * 
     * @return Value object if there is no exception, null if exception was
     *         thrown when retrieving field
     */
    private Node getReferencedNode(String property) {
        try {
            return node.getProperty(property).getNode();
        } catch (PathNotFoundException e) {
            try {
                _log.warn(
                        "Can't find property " + property + " on object: " + node + "\tuuid: " + node.getIdentifier(),
                        e);
            } catch (RepositoryException e1) {
                _log.warn("Can't get identifier for node: " + node, e1);
            }
        } catch (RepositoryException e) {
            _log.warn("Can't access field: " + property + " on node: " + node, e);
        }
        return null;
    }

    private <T> T getTypedValue(String property, Class<T> type) {
        Object realValue = null;
        try {
            if (type.isArray()) {
                Property p = node.getProperty(property);
                T retValues = null;
                Class<?> itemType = type.getComponentType();
                if (p.isMultiple()) {
                    Value[] values = p.getValues();
                    retValues = (T) java.lang.reflect.Array.newInstance(itemType, values.length);
                    for (int i=0 ; i < values.length; i++) {
                        java.lang.reflect.Array.set(retValues, i, getTypedValue(values[i], property, itemType));
                    }
                }
                else {
                    retValues = (T) java.lang.reflect.Array.newInstance(itemType, 1);
                    java.lang.reflect.Array.set(retValues, 0, getTypedValue(p.getValue(), property, itemType));
                }
                return retValues;
            }
            else {
                return getTypedValue(getValue(property), property, type);
            }
        } catch (PathNotFoundException e) {
            _log.error("Field " + property + " doesn't exist on " + node, e);
        } catch (RepositoryException e) {
            _log.error("Exception thrown when trying to access " + property + " on " + node, e);
        }

        return (T) realValue;
    }
    
    private <T> T getTypedValue(Value v, String property, Class<T> type) {
        Object realValue = null;
        try {
        if (v != null) {
            if (type.equals(String.class)) {
                realValue = v.getString();
            } else if (type.equals(Long.class)) {
                realValue = v.getLong();
            } else if (type.equals(Double.class)) {
                realValue = v.getDouble();
            } else if (type.equals(Boolean.class)) {
                realValue = v.getBoolean();
            } else if (type.equals(BigDecimal.class)) {
                realValue = v.getDecimal();
            } else if (type.equals(Calendar.class)) {
                realValue = v.getDate();
            } else if (DocumentEntity.class.isAssignableFrom(type)) {
                return (T) new DocumentEntityImpl(node.getSession().getNodeByIdentifier(v.getString()));
                
            }
        }
        } catch (ValueFormatException e) {
            _log.error("Field " + property + " can't be converted to " + type.getSimpleName(), e);
        } catch (RepositoryException e) {
            _log.error("Error occurred when fetching value for field " + property + " on node " + node, e);
        }
        return (T) realValue;
    }

    private <T> void setValueInternal(String property, T value) {
        try {
            createNewVersionIfNeeded(property);
            if (value == null) {
                if (node.hasProperty(property))
                    node.getProperty(property).remove();
                return;
            }
            Class<?> clasz = value.getClass();
            if (clasz.isArray()) {
                // remove existing property to avoid conflict when trying to overwrite single valued property
                if (node.hasProperty(property)) node.getProperty(property).remove();
                int len = java.lang.reflect.Array.getLength(value);
                Value[] values = new Value[len];
                for (int i=0; i < len; i++) {
                    values[i] = getValueObjForProperty(java.lang.reflect.Array.get(value, i));
                }
                node.setProperty(property, values);
            }
            else {
                node.setProperty(property, getValueObjForProperty(value));
            }
        } catch (RepositoryException e) {
            _log.error("Exception has been thrown when trying to set field " + property + " on node " + node, e);
        } catch (DocumentEntityException e) {
            _log.error("Exception has been thrown when trying to set field " + property + " on node " + node, e);
        }

    }
    
    private <T> void addValueInternal(String property, T value) {
        try {
            createNewVersionIfNeeded(property);
        Value valueConverted = getValueObjForProperty(value);
        
        if (node.hasProperty(property)) {
            Property p = node.getProperty(property);
            
            Value[] newValues = null;
            if (p.isMultiple()) {
                Value[] values = p.getValues();
                newValues = Arrays.copyOf(values, values.length + 1);
                newValues[values.length] = valueConverted;
                p.setValue(newValues);
            }
            else {
                newValues = new Value[2];
                newValues[0] = p.getValue();
                newValues[1] = valueConverted;
                
                p.remove();
                node.setProperty(property, newValues);
            }
        }
        else {
            node.setProperty(property, valueConverted);
        }
        }
        catch (RepositoryException e) {
            _log.error("Can't add new value to node, property: " + property + ",node: " + node, e);
            
        } catch (DocumentEntityException e) {
            _log.error("Can't add new value to node, property: " + property + ",node: " + node, e);
        }
    }

    private <T> Value getValueObjForProperty(T value) throws DocumentEntityException {
        Class<?> clasz = value.getClass();
        Value retValue = null;
        try {
        ValueFactory valueFactory = node.getSession().getValueFactory();
        if (clasz.equals(Long.class)) {
            retValue = valueFactory.createValue((Long) value);
        } else if (clasz.equals(Double.class)) {
            retValue = valueFactory.createValue((Double) value);
        } else if (clasz.equals(BigDecimal.class)) {
            retValue = valueFactory.createValue((BigDecimal) value);
        } else if (clasz.equals(Boolean.class)) {
            retValue = valueFactory.createValue((Boolean) value);
        } else if (clasz.equals(Calendar.class)) {
            retValue = valueFactory.createValue((Calendar) value);
        } else if (clasz.equals(String.class)) {
            retValue = valueFactory.createValue((String) value);
        } else if (HasNode.class.isAssignableFrom(clasz)) {
            retValue = valueFactory.createValue(((HasNode) value).getNode());
        }
        else {
            String msg = "Can't convert value of type: " + clasz + "\tvalue: " + value;
            _log.error(msg);
            throw new DocumentEntityException(msg);
        }
        return retValue;
        }
        catch (RepositoryException e) {
            String msg = "Can't convert value of type: " + clasz + "\tvalue: " + value;
            _log.error(msg);
            throw new DocumentEntityException(msg, e);
            
        }
    }

    public Node getNode() {
        return node;
    }

    public DocumentEntity[] getChildren(String property) {
        DocumentEntity[] entities = null;
        try {
            NodeIterator iterator = node.getNodes(property);
            entities = new DocumentEntity[(int) iterator.getSize()];

            while (iterator.hasNext()) {
                entities[(int) iterator.getPosition()] = new DocumentEntityImpl(iterator.nextNode());

            }
        } catch (RepositoryException e) {
            _log.error("Exception has been thrown when trying to get entities for property " + property + " on node "
                    + node, e);
            entities = new DocumentEntity[] {};
        }
        return entities;
    }

    public DocumentEntity getChild(String property) {
        try {
            return new DocumentEntityImpl(node.getNode(property));

        } catch (PathNotFoundException e) {
            _log.error("Can't find child entity for property " + property + " in node: " + node, e);
        } catch (RepositoryException e) {
            _log.error("Can't find child entity for property " + property + " in node: " + node, e);
        }
        return null;
    }

    public DocumentEntity addChild(String property) throws DocumentEntityException {
        try {
            createNewVersionIfNeeded(property);
            return new DocumentEntityImpl(node.addNode(property, DocumentEntityServiceImpl.XCOLAB_BASE_TYPE));
        } catch (ItemExistsException e) {
            _log.error("Can't add property as it already exists " + property + " in node: " + node, e);
            throw new DocumentEntityException("error when trying to add new node to property " + property + " on node "
                    + node, e);
        } catch (PathNotFoundException e) {
            _log.error("Can't find given path for property " + property + " in node: " + node, e);
            throw new DocumentEntityException("error when trying to add new node to property " + property + " on node "
                    + node, e);
        } catch (VersionException e) {
            _log.error("Invalid version when setting property " + property + " in node: " + node, e);
            throw new DocumentEntityException("error when trying to add new node to property " + property + " on node "
                    + node, e);
        } catch (ConstraintViolationException e) {
            _log.error("Constraint violation exception when setting property " + property + " in node: " + node, e);
            throw new DocumentEntityException("error when trying to add new node to property " + property + " on node "
                    + node, e);
        } catch (LockException e) {
            _log.error("Lock exception when setting property " + property + " in node: " + node, e);
            throw new DocumentEntityException("error when trying to add new node to property " + property + " on node "
                    + node, e);
        } catch (RepositoryException e) {
            _log.error("Repository exception when setting property " + property + " in node: " + node, e);
            throw new DocumentEntityException("error when trying to add new node to property " + property + " on node "
                    + node, e);
        }
    }

    private void createNewVersionIfNeeded(String property) throws DocumentEntityException {
        try {
            JcrVersionUtils.reportModificationOfProperty(node, property);
        } catch (RepositoryException e) {
            throw new DocumentEntityException("Can't checkout node " + node, e);
        }
    }

    public Object getId() {
        try {
            return node.getIdentifier();
        } catch (RepositoryException e) {
            _log.error("Can't access node id: " + node, e);
        }
        return null;
    }


    public void removeProperty(String property) throws DocumentEntityException {
        try {
            createNewVersionIfNeeded(property);
            if (node.hasProperty(property)) {
                node.getProperty(property).remove();
            }
        } catch (RepositoryException e) {
            _log.error("Error occurred when fetching node reference for property " + property + " on node " + node, e);
            throw new DocumentEntityException("Error occurred when fetching node reference for property " + property
                    + " on node " + node, e);
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (! obj.getClass().equals(DocumentEntityImpl.class)) 
            return false;
        
        return node.equals(((DocumentEntityImpl) obj).node);
        
    }

    @Override
    public DocumentEntity getParent() throws DocumentEntityException {
        try {
            return new DocumentEntityImpl(node.getParent());
        }
        catch (RepositoryException e) {
            throw new DocumentEntityException("Can't access node parent for node: " + node, e);
        }
    }

    @Override
    public EntityVersion<DocumentEntity> getVersion() throws DocumentEntityException {
        try {
            Version version = null;
            if (! node.isNodeType("nt:frozenNode")) {
                version = node.getSession().getWorkspace().getVersionManager().getBaseVersion(node.getPath());
            }
            else {
                Node baseNode = node.getProperty("jcr:frozenUuid").getNode();
                
                VersionIterator iterator = 
                        node.getSession().getWorkspace().getVersionManager().getVersionHistory(baseNode.getPath()).getAllVersions();
                while (iterator.hasNext()) {
                    version = iterator.nextVersion();
                    if (version.getFrozenNode().getIdentifier().equals(node.getIdentifier())) break;
                }
            }

            return new EntityVersionImpl<DocumentEntity>(version, new DocumentEntityImpl(version.getFrozenNode()));
        } catch (RepositoryException e) {
            throw new DocumentEntityException(e);
        }
        
        
    }
    

}
