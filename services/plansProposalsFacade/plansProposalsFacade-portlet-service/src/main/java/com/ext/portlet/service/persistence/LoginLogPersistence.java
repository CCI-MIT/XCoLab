package com.ext.portlet.service.persistence;

import com.ext.portlet.model.LoginLog;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the login log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoginLogPersistenceImpl
 * @see LoginLogUtil
 * @generated
 */
public interface LoginLogPersistence extends BasePersistence<LoginLog> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link LoginLogUtil} to access the login log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the login log in the entity cache if it is enabled.
    *
    * @param loginLog the login log
    */
    public void cacheResult(com.ext.portlet.model.LoginLog loginLog);

    /**
    * Caches the login logs in the entity cache if it is enabled.
    *
    * @param loginLogs the login logs
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.LoginLog> loginLogs);

    /**
    * Creates a new login log with the primary key. Does not add the login log to the database.
    *
    * @param pk the primary key for the new login log
    * @return the new login log
    */
    public com.ext.portlet.model.LoginLog create(long pk);

    /**
    * Removes the login log with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the login log
    * @return the login log that was removed
    * @throws com.ext.portlet.NoSuchLoginLogException if a login log with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.LoginLog remove(long pk)
        throws com.ext.portlet.NoSuchLoginLogException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.LoginLog updateImpl(
        com.ext.portlet.model.LoginLog loginLog)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the login log with the primary key or throws a {@link com.ext.portlet.NoSuchLoginLogException} if it could not be found.
    *
    * @param pk the primary key of the login log
    * @return the login log
    * @throws com.ext.portlet.NoSuchLoginLogException if a login log with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.LoginLog findByPrimaryKey(long pk)
        throws com.ext.portlet.NoSuchLoginLogException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the login log with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param pk the primary key of the login log
    * @return the login log, or <code>null</code> if a login log with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.LoginLog fetchByPrimaryKey(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the login logs.
    *
    * @return the login logs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.LoginLog> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the login logs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of login logs
    * @param end the upper bound of the range of login logs (not inclusive)
    * @return the range of login logs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.LoginLog> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the login logs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of login logs
    * @param end the upper bound of the range of login logs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of login logs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.LoginLog> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the login logs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of login logs.
    *
    * @return the number of login logs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
