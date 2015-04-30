package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactTemplateSeries;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the impact template series service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateSeriesPersistenceImpl
 * @see ImpactTemplateSeriesUtil
 * @generated
 */
public interface ImpactTemplateSeriesPersistence extends BasePersistence<ImpactTemplateSeries> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImpactTemplateSeriesUtil} to access the impact template series persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the impact template series in the entity cache if it is enabled.
    *
    * @param impactTemplateSeries the impact template series
    */
    public void cacheResult(
        com.ext.portlet.model.ImpactTemplateSeries impactTemplateSeries);

    /**
    * Caches the impact template serieses in the entity cache if it is enabled.
    *
    * @param impactTemplateSerieses the impact template serieses
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ImpactTemplateSeries> impactTemplateSerieses);

    /**
    * Creates a new impact template series with the primary key. Does not add the impact template series to the database.
    *
    * @param seriesId the primary key for the new impact template series
    * @return the new impact template series
    */
    public com.ext.portlet.model.ImpactTemplateSeries create(long seriesId);

    /**
    * Removes the impact template series with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param seriesId the primary key of the impact template series
    * @return the impact template series that was removed
    * @throws com.ext.portlet.NoSuchImpactTemplateSeriesException if a impact template series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateSeries remove(long seriesId)
        throws com.ext.portlet.NoSuchImpactTemplateSeriesException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ImpactTemplateSeries updateImpl(
        com.ext.portlet.model.ImpactTemplateSeries impactTemplateSeries)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact template series with the primary key or throws a {@link com.ext.portlet.NoSuchImpactTemplateSeriesException} if it could not be found.
    *
    * @param seriesId the primary key of the impact template series
    * @return the impact template series
    * @throws com.ext.portlet.NoSuchImpactTemplateSeriesException if a impact template series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateSeries findByPrimaryKey(
        long seriesId)
        throws com.ext.portlet.NoSuchImpactTemplateSeriesException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact template series with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param seriesId the primary key of the impact template series
    * @return the impact template series, or <code>null</code> if a impact template series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactTemplateSeries fetchByPrimaryKey(
        long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the impact template serieses.
    *
    * @return the impact template serieses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactTemplateSeries> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the impact template serieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template serieses
    * @param end the upper bound of the range of impact template serieses (not inclusive)
    * @return the range of impact template serieses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactTemplateSeries> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the impact template serieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template serieses
    * @param end the upper bound of the range of impact template serieses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of impact template serieses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactTemplateSeries> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the impact template serieses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of impact template serieses.
    *
    * @return the number of impact template serieses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
