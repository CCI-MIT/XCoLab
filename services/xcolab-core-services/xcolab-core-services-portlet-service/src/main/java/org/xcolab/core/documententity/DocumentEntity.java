package org.xcolab.core.documententity;

import java.math.BigDecimal;
import java.util.Calendar;

public interface DocumentEntity {
	
	public Object getId();
	
	boolean hasProperty(String property);
	
	Long getLong(String property);
	Long[] getLongs(String property);
	
	Double getDouble(String property);
	Double[] getDoubles(String property);
	
	BigDecimal getDecimal(String property);
	BigDecimal[] getDecimals(String property);
	
	Boolean getBoolean(String property);
	Boolean[] getBooleans(String property);
	
	Calendar getDate(String property);
	Calendar[] getDates(String property);
	
	String getString(String property);
	String[] getStrings(String property);
	
	DocumentEntity getChild(String property);
	DocumentEntity[] getChildren(String property);
	
	DocumentEntity getReference(String property);
	DocumentEntity[] getReferences(String property);
	
	DocumentEntity getParent() throws DocumentEntityException;
	
	//List<DocumentEntity> getEntities(String property);
	
	
	
	
	void setLong(String property, Long value);
	void setLongs(String property, Long[] values);
	void addLong(String property, Long value);
	
	void setDouble(String property, Double value);
	void setDoubles(String property, Double[] values);
	void addDouble(String property, Double value);
	
	void setDecimal(String property, BigDecimal value);
	void setDecimals(String property, BigDecimal[] values);
	void addDecimal(String property, BigDecimal value);
	
	void setBoolean(String property, Boolean value);
	void setBooleans(String property, Boolean[] values);
	void addBoolean(String property, Boolean value);
	
	void setDate(String property, Calendar date);
	void setDates(String property, Calendar[] dates);
	void addDate(String property, Calendar date);
	
	void setString(String property, String value);
	void setStrings(String property, String[] values);
	void addString(String property, String value);
	
	void setReference(String property, DocumentEntity entity);
	void setReferences(String property, DocumentEntity[] entities);
	void addReference(String property, DocumentEntity entity);
	
	DocumentEntity addChild(String property) throws DocumentEntityException;
	
	void removeProperty(String propertyName) throws DocumentEntityException;
	
	EntityVersion<DocumentEntity> getVersion() throws DocumentEntityException;
	
}
