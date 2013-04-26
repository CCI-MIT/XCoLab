package org.xcolab.core.xcolabententity;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Represents an entity that is a document. A XColabEntity consists of properties that can be of following types:
 * <ul>
 *  <li>a primitive type ({@link java.lang.String}, Long, Decimal, Calendar, Boolean, Double),</li>
 *  <li>a child document (embedded),</li>
 *  <li>a reference to other document,</li>
 *  <li>an array (of types mentioned above).</li>
 * </ul>
 * 
 * In addition to properties a XColabEntity provides version tracking. Each entity knows the version it is in.
 * 
 * @see EntityVersion
 * 
 * @author janusz
 *
 */
public interface XColabEntity {
	
    /**
     * Returns an id of an entity;
     * @return entity id
     */
	public Object getId();
	
	/**
	 * Tells if given property is defined in an entity. 
	 * @param property name of a property to be checked
	 * @return true if property is defined in given entity, false otherwise
	 */
	boolean hasProperty(String property);
	
	/**
	 * Returns value of a property as a Long.
	 * 
	 * @param property name of a property
     * @return value of a property or null if not set
	 */
	Long getLong(String property);
	
	
	/**
	 * Returns value of a property as an array of Longs.
	 *  
	 * @param property name of a property
     * @return value of a property or null if not set
	 */
	Long[] getLongs(String property);
	
	/**
	 * Returns value of a property as a Double.
	 * 
	 * @param property name of a property
     * @return value of a property or null if not set
	 */
	Double getDouble(String property);
	
	/**
	 * Returns value of a property as an array of Doubles.
	 * 
	 * @param property name of a property
	 * @return value of a property or null if not set
	 */
	Double[] getDoubles(String property);

    /**
     * Returns value of a property as a BigDecimal.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	BigDecimal getDecimal(String property);
	
    /**
     * Returns value of a property as an array of BigDecimals.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	BigDecimal[] getDecimals(String property);
	

    /**
     * Returns value of a property as a Boolean.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	Boolean getBoolean(String property);

    /**
     * Returns value of a property as an array of Booleans.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	Boolean[] getBooleans(String property);
	

    /**
     * Returns value of a property as a Calendar.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	Calendar getDate(String property);

    /**
     * Returns value of a property as an array of Booleans.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	Calendar[] getDates(String property);
	

    /**
     * Returns value of a property as a String.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	String getString(String property);
	

    /**
     * Returns value of a property as an array of Strings.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	String[] getStrings(String property);
	
    /**
     * Returns child node.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	XColabEntity getChild(String property);

    /**
     * Returns child nodes.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	XColabEntity[] getChildren(String property);
	

    /**
     * Returns referenced node.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	XColabEntity getReference(String property);

    /**
     * Returns array of referenced nodes.
     * 
     * @param property name of a property
     * @return value of a property or null if not set
     */
	XColabEntity[] getReferences(String property);
	

    /**
     * Returns a parent node of current node.
     * 
     * @return entity that is a parent of current node
     * @throws XColabEntityException in case of any error
     */
	XColabEntity getParent() throws XColabEntityException;
	
	/**
	 * Setter that sets value of a property to a Long.
	 * 
	 * @param property name of a property
	 * @param value value that is to be stored for give property
	 */
	void setLong(String property, Long value);
	
    /**
     * Setter that sets value of a property to an array of Longs.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setLongs(String property, Long[] values);
	
    /**
     * Adds a Long value to existing values of a property.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void addLong(String property, Long value);
	
    /**
     * Setter that sets value of a property to a Double.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setDouble(String property, Double value);

    /**
     * Setter that sets value of a property to an array of Doubles.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setDoubles(String property, Double[] values);
    
    /**
     * Adds a Double value to existing values of a property.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void addDouble(String property, Double value);
	
	/**
     * Setter that sets value of a property to a BigDecimal.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setDecimal(String property, BigDecimal value);
	
    /**
     * Setter that sets value of a property to an array of BigDecimals.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setDecimals(String property, BigDecimal[] values);
    
    /**
     * Adds a BigDecimal value to existing values of a property.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void addDecimal(String property, BigDecimal value);

    
    /**
     * Setter that sets value of a property to a Boolean.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setBoolean(String property, Boolean value);

    /**
     * Setter that sets value of a property to an array of Booleans.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setBooleans(String property, Boolean[] values);
    
    /**
     * Adds a Boolean value to existing values of a property.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void addBoolean(String property, Boolean value);

    
    /**
     * Setter that sets value of a property to a Calendar.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setDate(String property, Calendar date);

    /**
     * Setter that sets value of a property to an array of Calendars.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setDates(String property, Calendar[] dates);
    
    /**
     * Adds a Calendar value to existing values of a property.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void addDate(String property, Calendar date);

    
    /**
     * Setter that sets value of a property to a String.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setString(String property, String value);

    /**
     * Setter that sets value of a property to an array of Strings.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setStrings(String property, String[] values);

    
    /**
     * Adds a String value to existing values of a property.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void addString(String property, String value);

    /**
     * Setter that sets value of a property to an entity reference.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setReference(String property, XColabEntity entity);

    /**
     * Setter that sets value of a property to an array of entity references.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void setReferences(String property, XColabEntity[] entities);
    
    /**
     * Adds a node reference existing references stored in a property.
     * 
     * @param property name of a property
     * @param value value that is to be stored for give property
     */
	void addReference(String property, XColabEntity entity);
	
	/**
	 * Creates new child node that will be embedded in this node and stored in given property.
	 * 
	 * @param property name of a property
	 * @return newly created node
	 * @throws XColabEntityException in case of any error
	 */
	XColabEntity addChild(String property) throws XColabEntityException;
	
	/**
	 * Removes property with given name.
	 * @param propertyName name of a property
	 * @throws XColabEntityException in case of any error
	 */
	void removeProperty(String propertyName) throws XColabEntityException;
	
	/**
	 * Returns entity version. 
	 * 
	 * @return entity version
	 * @throws XColabEntityException in case of an error
	 */
	EntityVersion<XColabEntity> getVersion() throws XColabEntityException;
	
}
