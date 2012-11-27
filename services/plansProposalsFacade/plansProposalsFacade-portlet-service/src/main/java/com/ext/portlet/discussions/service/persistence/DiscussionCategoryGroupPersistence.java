package com.ext.portlet.discussions.service.persistence;

import com.ext.portlet.discussions.model.DiscussionCategoryGroup;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the discussion category group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryGroupPersistenceImpl
 * @see DiscussionCategoryGroupUtil
 * @generated
 */
public interface DiscussionCategoryGroupPersistence extends BasePersistence<DiscussionCategoryGroup> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DiscussionCategoryGroupUtil} to access the discussion category group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the discussion category group in the entity cache if it is enabled.
    *
    * @param discussionCategoryGroup the discussion category group
    */
    public void cacheResult(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup);

    /**
    * Caches the discussion category groups in the entity cache if it is enabled.
    *
    * @param discussionCategoryGroups the discussion category groups
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> discussionCategoryGroups);

    /**
    * Creates a new discussion category group with the primary key. Does not add the discussion category group to the database.
    *
    * @param id the primary key for the new discussion category group
    * @return the new discussion category group
    */
    public com.ext.portlet.discussions.model.DiscussionCategoryGroup create(
        java.lang.Long id);

    /**
    * Removes the discussion category group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the discussion category group
    * @return the discussion category group that was removed
    * @throws com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionCategoryGroup remove(
        java.lang.Long id)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup updateImpl(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup discussionCategoryGroup,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion category group with the primary key or throws a {@link com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException} if it could not be found.
    *
    * @param id the primary key of the discussion category group
    * @return the discussion category group
    * @throws com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionCategoryGroup findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion category group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the discussion category group
    * @return the discussion category group, or <code>null</code> if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionCategoryGroup fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion category groups.
    *
    * @return the discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion category groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion category groups
    * @param end the upper bound of the range of discussion category groups (not inclusive)
    * @return the range of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion category groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion category groups
    * @param end the upper bound of the range of discussion category groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion category groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion category groups.
    *
    * @return the number of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
