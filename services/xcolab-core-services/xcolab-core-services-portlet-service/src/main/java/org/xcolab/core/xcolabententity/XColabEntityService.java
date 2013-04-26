package org.xcolab.core.xcolabententity;

import java.util.List;

/**
 * This interface defines a API for XColabEntity management. It provides CRUD operations also it manages version storage
 * and retrieval. In addition to that methods responsible for wrapping an entity are provided. By design all classes 
 * implementing XColabEntity should actually implement XColabEntityWrapper. Thanks to that changing persistence layer 
 * would be transparent to concrete entities implementations.
 * 
 *  
 * @author janusz
 *
 */
public interface XColabEntityService {
    /**
     * Returns an entity for given id
     * 
     * @param id entity id
     * @return fetched entity or null if nothing is found
     * @throws XColabEntityException in case of an error
     */
	XColabEntity findDocumentEntity(Object id) throws XColabEntityException;
	
	/**
	 * Returns a XColabEntity wrapped by a concrete class. 
	 * 
	 * @param id entity id
	 * @param itemType concrete entity class 
	 * @return wrapped entity or null if nothing is found
	 * @throws XColabEntityException in case of an error
	 */
	<IT extends XColabEntityWrapper> IT findDocumentEntity(Object id, Class<IT> itemType) throws XColabEntityException;
	
	/**
	 * Returns all entities in given context. 
	 * 
	 * @param context context name/path 
	 * @return list of entities
	 * @throws XColabEntityException in case of an error
	 */
	List<XColabEntity> findDocumentEntities(String context) throws XColabEntityException;
	
	/**
     * Returns all entities in given context wrapped by a concrete class. 
     * 
     * @param context context name/path 
     * @param listType list object type
     * @param itemType concrete entity class 
     * @return list of wrapped entities
     * @throws XColabEntityException in case of an error
     */
	<LT extends XColabEntityWrapper, IT extends LT> List<LT> findDocumentEntities(String context, Class<LT> listType, Class<IT> itemType) throws XColabEntityException;
	
	/**
	 * Creates an entity in given context.
	 * 
     * @param context context name/path 
	 * @return created entity
	 * @throws XColabEntityException in case of an error
	 */
	XColabEntity createEntity(String context) throws XColabEntityException;
	   
    /**
     * Creates a wrapped entity in given context.
     * 
     * @param context context name/path 
     * @return created entity
     * @throws XColabEntityException in case of an error
     */
	<IT extends XColabEntityWrapper> IT createEntity(String context, Class<IT> itemType) throws XColabEntityException;  
	
    /**
     * Creates new version of an entity and stores it in persistent storage.  
     * 
     * @param entity entity that is to be stored
     * @throws XColabEntityException in case of an error
     */
	void update(XColabEntity entity) throws XColabEntityException;

    /**
     * Creates new version of an entity and stores it in persistent storage.  
     * 
     * @param entity entity that is to be stored
     * @throws XColabEntityException in case of an error
     */
	<IT extends XColabEntityWrapper> void update(IT wrapped) throws XColabEntityException;
	

    /**
     * Returns all versions of an entity.  
     * 
     * @param entity entity for which versions are to be fetched
     * @throws XColabEntityException in case of an error
     */
	<T extends XColabEntity> List<EntityVersion<T>> getVersions(T entity) throws XColabEntityException;

    /**
     * Returns all versions of an entity.  
     * 
     * @param entity entity for which versions are to be fetched
     * @param listType list object type
     * @param itemType concrete entity class 
     * @throws XColabEntityException in case of an error
     */
	<LT extends XColabEntityWrapper, IT extends LT> List<EntityVersion<LT>> getVersions(XColabEntity entity, Class<LT> listType, Class<IT> itemType) throws XColabEntityException;
}
