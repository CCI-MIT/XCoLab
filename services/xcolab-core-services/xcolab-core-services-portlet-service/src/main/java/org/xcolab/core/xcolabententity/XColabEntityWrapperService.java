package org.xcolab.core.xcolabententity;

import java.util.List;

/**
 * 
 * 
 * @author janusz
 *
 * @param <T>
 */
public interface XColabEntityWrapperService<T extends XColabEntityWrapper> {

    /**
     * Returns an entity for given id
     * 
     * @param id entity id
     * @return fetched entity or null if nothing is found
     * @throws XColabEntityException in case of an error
     */
	T findEntity(Object id) throws XColabEntityException;
	
	/**
     * Returns all entities.
     * 
     * @return list of entities
     * @throws XColabEntityException in case of an error
     */
	List<T> findAllEntities() throws XColabEntityException;
	
	/**
	 * Creates new instance of an entity
	 * @return new instance of an entity
	 * @throws XColabEntityException in case of any error
	 */
	T createEntity() throws XColabEntityException;
	
	
	/**
	 * Creates a new version and stores all changes in passed entity.
	 * 
	 * @param entity entity that is to be stored
	 * @throws XColabEntityException in case of an error
	 */
	void updateEntity(T entity) throws XColabEntityException;
	

    /**
     * Returns all versions of an entity.  
     * 
     * @param entity entity for which versions are to be fetched
     * @param listType list object type
     * @param itemType concrete entity class 
     * @throws XColabEntityException in case of an error
     */
	List<EntityVersion<T>> getVersions(T entity) throws XColabEntityException; 
}
