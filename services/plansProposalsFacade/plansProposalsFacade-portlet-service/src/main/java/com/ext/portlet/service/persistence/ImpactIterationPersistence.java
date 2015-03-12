package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactIteration;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the impact iteration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactIterationPersistenceImpl
 * @see ImpactIterationUtil
 * @generated
 */
public interface ImpactIterationPersistence extends BasePersistence<ImpactIteration> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImpactIterationUtil} to access the impact iteration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the impact iterations where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @return the matching impact iterations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactIteration> findByIterationId(
        long iterationId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the impact iterations where iterationId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param iterationId the iteration ID
    * @param start the lower bound of the range of impact iterations
    * @param end the upper bound of the range of impact iterations (not inclusive)
    * @return the range of matching impact iterations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactIteration> findByIterationId(
        long iterationId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the impact iterations where iterationId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param iterationId the iteration ID
    * @param start the lower bound of the range of impact iterations
    * @param end the upper bound of the range of impact iterations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching impact iterations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactIteration> findByIterationId(
        long iterationId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first impact iteration in the ordered set where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact iteration
    * @throws com.ext.portlet.NoSuchImpactIterationException if a matching impact iteration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactIteration findByIterationId_First(
        long iterationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactIterationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first impact iteration in the ordered set where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact iteration, or <code>null</code> if a matching impact iteration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactIteration fetchByIterationId_First(
        long iterationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last impact iteration in the ordered set where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact iteration
    * @throws com.ext.portlet.NoSuchImpactIterationException if a matching impact iteration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactIteration findByIterationId_Last(
        long iterationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactIterationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last impact iteration in the ordered set where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact iteration, or <code>null</code> if a matching impact iteration could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactIteration fetchByIterationId_Last(
        long iterationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact iterations before and after the current impact iteration in the ordered set where iterationId = &#63;.
    *
    * @param impactIterationPK the primary key of the current impact iteration
    * @param iterationId the iteration ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next impact iteration
    * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactIteration[] findByIterationId_PrevAndNext(
        ImpactIterationPK impactIterationPK, long iterationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactIterationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the impact iterations where iterationId = &#63; from the database.
    *
    * @param iterationId the iteration ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByIterationId(long iterationId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of impact iterations where iterationId = &#63;.
    *
    * @param iterationId the iteration ID
    * @return the number of matching impact iterations
    * @throws SystemException if a system exception occurred
    */
    public int countByIterationId(long iterationId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the impact iteration in the entity cache if it is enabled.
    *
    * @param impactIteration the impact iteration
    */
    public void cacheResult(
        com.ext.portlet.model.ImpactIteration impactIteration);

    /**
    * Caches the impact iterations in the entity cache if it is enabled.
    *
    * @param impactIterations the impact iterations
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ImpactIteration> impactIterations);

    /**
    * Creates a new impact iteration with the primary key. Does not add the impact iteration to the database.
    *
    * @param impactIterationPK the primary key for the new impact iteration
    * @return the new impact iteration
    */
    public com.ext.portlet.model.ImpactIteration create(
        ImpactIterationPK impactIterationPK);

    /**
    * Removes the impact iteration with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactIterationPK the primary key of the impact iteration
    * @return the impact iteration that was removed
    * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactIteration remove(
        ImpactIterationPK impactIterationPK)
        throws com.ext.portlet.NoSuchImpactIterationException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ImpactIteration updateImpl(
        com.ext.portlet.model.ImpactIteration impactIteration)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact iteration with the primary key or throws a {@link com.ext.portlet.NoSuchImpactIterationException} if it could not be found.
    *
    * @param impactIterationPK the primary key of the impact iteration
    * @return the impact iteration
    * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactIteration findByPrimaryKey(
        ImpactIterationPK impactIterationPK)
        throws com.ext.portlet.NoSuchImpactIterationException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact iteration with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param impactIterationPK the primary key of the impact iteration
    * @return the impact iteration, or <code>null</code> if a impact iteration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactIteration fetchByPrimaryKey(
        ImpactIterationPK impactIterationPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the impact iterations.
    *
    * @return the impact iterations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactIteration> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the impact iterations.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact iterations
    * @param end the upper bound of the range of impact iterations (not inclusive)
    * @return the range of impact iterations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactIteration> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the impact iterations.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact iterations
    * @param end the upper bound of the range of impact iterations (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of impact iterations
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactIteration> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the impact iterations from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of impact iterations.
    *
    * @return the number of impact iterations
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
