package org.xcolab.core.xcolabententity;

import java.util.Calendar;

/**
 * Holds a version information for an entity. It provides information like:
 * <ul>
 *   <li>Entity for given version</li>
 *   <li>author of change</li>
 *   <li>tags related to change</li>
 *   <li>date when version was created</li>
 * </ul>
 * This interface provides also API for navigation between entity versions.
 * 
 * @author janusz
 *
 * @param <T> type of an entity
 */
public interface EntityVersion<T extends XColabEntity> {
    
    /**
     * Tells when entity version was created. 
     * 
     * @return date of entity version creation
     */
	Calendar getCreated();

    /**
     * Tells when entity version was created. 
     * 
     * @return date of entity version creation
     */
	Object getAuthor();
	
	/**
	 * Returns tags related to entity version. 
	 * 
	 * @return entity version tags
	 */
	String[] getTags();
	
	/**
	 * Returns entity for given version.
	 * 
	 * @return document entity
	 */
	T getEntity();
	
	/**
	 * Tells if this version is the latest version of an entity. 
	 * 
	 * @return true if this is a latest version, false otherwise
	 * @throws XColabEntityException in case of an error
	 */
	boolean isLatest() throws XColabEntityException;

    /**
     * Tells if this version is the first version of an entity. 
     * 
     * @return true if this is a first version, false otherwise
     * @throws XColabEntityException in case of an error
     */
	boolean isFirst() throws XColabEntityException;
	
	/**
	 * Returns previous version for an entity (ordered by creation date). 
	 * 
	 * @return previous version
	 * @throws XColabEntityException in case of an error
	 */
	EntityVersion<T> getPrev() throws XColabEntityException;

    /**
     * Returns next version for an entity (ordered by creation date). 
     * 
     * @return next version
     * @throws XColabEntityException in case of an error
     */
	EntityVersion<T> getNext() throws XColabEntityException;
	
	
}
