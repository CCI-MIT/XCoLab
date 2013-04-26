package org.xcolab.core.xcolabententity;

/**
 * Entity wrapper API that extends XColabEntity. It adds getReference/getChildren methods allowing to fetch typed 
 * referenced/child entities. 
 * @author janusz
 *
 */
public interface XColabEntityWrapper extends XColabEntity {
    /**
     * Returns wrapped entity. 
     * 
     * @return wrapped entity
     */
	XColabEntity getWrapped();

	/**
	 * Returns typed referenced entity.
	 * 
	 * @param property name of a property
	 * @param type type of an entity
	 * @return referenced entity
	 * @throws XColabEntityException in case of an error
	 */
	<T extends XColabEntityWrapper> T getReference(String property, Class<T> type) throws XColabEntityException;
	
	/**
     * Returns typed referenced entities.
     * 
     * @param property name of a property
     * @param listType type of a list element
     * @param itemType type of an entity
     * @return referenced entities
     * @throws XColabEntityException in case of an error
     */
	<LT extends XColabEntityWrapper, IT extends LT> LT[] getReferences(String property, Class<LT> listType, Class<IT> itemType) throws XColabEntityException;

	/**
	 * Fetches typed children for given property.
	 * 
	 * @param property property name
	 * @param listType type of a list item
	 * @param itemType type of an entity
	 * @return list of entities (wrapped) 
	 * @throws XColabEntityException in case of an error
	 */
    public <LT extends XColabEntityWrapper, IT extends LT> LT[] getChildren(String property, Class<LT> listType, Class<IT> itemType ) 
            throws XColabEntityException;
}
