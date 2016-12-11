package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchOntologySpaceException;
import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.impl.OntologySpaceImpl;
import com.ext.portlet.model.impl.OntologySpaceModelImpl;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the ontology space service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologySpacePersistence
 * @see OntologySpaceUtil
 * @generated
 */
public class OntologySpacePersistenceImpl extends BasePersistenceImpl<OntologySpace>
    implements OntologySpacePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link OntologySpaceUtil} to access the ontology space persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = OntologySpaceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED,
            OntologySpaceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED,
            OntologySpaceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED,
            OntologySpaceImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByName",
            new String[] { String.class.getName() },
            OntologySpaceModelImpl.NAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_NAME_NAME_1 = "ontologySpace.name IS NULL";
    private static final String _FINDER_COLUMN_NAME_NAME_2 = "ontologySpace.name = ?";
    private static final String _FINDER_COLUMN_NAME_NAME_3 = "(ontologySpace.name IS NULL OR ontologySpace.name = '')";
    private static final String _SQL_SELECT_ONTOLOGYSPACE = "SELECT ontologySpace FROM OntologySpace ontologySpace";
    private static final String _SQL_SELECT_ONTOLOGYSPACE_WHERE = "SELECT ontologySpace FROM OntologySpace ontologySpace WHERE ";
    private static final String _SQL_COUNT_ONTOLOGYSPACE = "SELECT COUNT(ontologySpace) FROM OntologySpace ontologySpace";
    private static final String _SQL_COUNT_ONTOLOGYSPACE_WHERE = "SELECT COUNT(ontologySpace) FROM OntologySpace ontologySpace WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ontologySpace.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OntologySpace exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OntologySpace exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(OntologySpacePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id", "order"
            });
    private static OntologySpace _nullOntologySpace = new OntologySpaceImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<OntologySpace> toCacheModel() {
                return _nullOntologySpaceCacheModel;
            }
        };

    private static CacheModel<OntologySpace> _nullOntologySpaceCacheModel = new CacheModel<OntologySpace>() {
            @Override
            public OntologySpace toEntityModel() {
                return _nullOntologySpace;
            }
        };

    public OntologySpacePersistenceImpl() {
        setModelClass(OntologySpace.class);
    }

    /**
     * Returns the ontology space where name = &#63; or throws a {@link com.ext.portlet.NoSuchOntologySpaceException} if it could not be found.
     *
     * @param name the name
     * @return the matching ontology space
     * @throws com.ext.portlet.NoSuchOntologySpaceException if a matching ontology space could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace findByName(String name)
        throws NoSuchOntologySpaceException, SystemException {
        OntologySpace ontologySpace = fetchByName(name);

        if (ontologySpace == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("name=");
            msg.append(name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchOntologySpaceException(msg.toString());
        }

        return ontologySpace;
    }

    /**
     * Returns the ontology space where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param name the name
     * @return the matching ontology space, or <code>null</code> if a matching ontology space could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace fetchByName(String name) throws SystemException {
        return fetchByName(name, true);
    }

    /**
     * Returns the ontology space where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param name the name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching ontology space, or <code>null</code> if a matching ontology space could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace fetchByName(String name, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
                    finderArgs, this);
        }

        if (result instanceof OntologySpace) {
            OntologySpace ontologySpace = (OntologySpace) result;

            if (!Validator.equals(name, ontologySpace.getName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_ONTOLOGYSPACE_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_NAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_NAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_NAME_NAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindName) {
                    qPos.add(name);
                }

                List<OntologySpace> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "OntologySpacePersistenceImpl.fetchByName(String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    OntologySpace ontologySpace = list.get(0);

                    result = ontologySpace;

                    cacheResult(ontologySpace);

                    if ((ontologySpace.getName() == null) ||
                            !ontologySpace.getName().equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                            finderArgs, ontologySpace);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (OntologySpace) result;
        }
    }

    /**
     * Removes the ontology space where name = &#63; from the database.
     *
     * @param name the name
     * @return the ontology space that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace removeByName(String name)
        throws NoSuchOntologySpaceException, SystemException {
        OntologySpace ontologySpace = findByName(name);

        return remove(ontologySpace);
    }

    /**
     * Returns the number of ontology spaces where name = &#63;.
     *
     * @param name the name
     * @return the number of matching ontology spaces
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByName(String name) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ONTOLOGYSPACE_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_NAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_NAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_NAME_NAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindName) {
                    qPos.add(name);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the ontology space in the entity cache if it is enabled.
     *
     * @param ontologySpace the ontology space
     */
    @Override
    public void cacheResult(OntologySpace ontologySpace) {
        EntityCacheUtil.putResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceImpl.class, ontologySpace.getPrimaryKey(),
            ontologySpace);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { ontologySpace.getName() }, ontologySpace);

        ontologySpace.resetOriginalValues();
    }

    /**
     * Caches the ontology spaces in the entity cache if it is enabled.
     *
     * @param ontologySpaces the ontology spaces
     */
    @Override
    public void cacheResult(List<OntologySpace> ontologySpaces) {
        for (OntologySpace ontologySpace : ontologySpaces) {
            if (EntityCacheUtil.getResult(
                        OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                        OntologySpaceImpl.class, ontologySpace.getPrimaryKey()) == null) {
                cacheResult(ontologySpace);
            } else {
                ontologySpace.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ontology spaces.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(OntologySpaceImpl.class.getName());
        }

        EntityCacheUtil.clearCache(OntologySpaceImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ontology space.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(OntologySpace ontologySpace) {
        EntityCacheUtil.removeResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceImpl.class, ontologySpace.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(ontologySpace);
    }

    @Override
    public void clearCache(List<OntologySpace> ontologySpaces) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (OntologySpace ontologySpace : ontologySpaces) {
            EntityCacheUtil.removeResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                OntologySpaceImpl.class, ontologySpace.getPrimaryKey());

            clearUniqueFindersCache(ontologySpace);
        }
    }

    protected void cacheUniqueFindersCache(OntologySpace ontologySpace) {
        if (ontologySpace.isNew()) {
            Object[] args = new Object[] { ontologySpace.getName() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
                ontologySpace);
        } else {
            OntologySpaceModelImpl ontologySpaceModelImpl = (OntologySpaceModelImpl) ontologySpace;

            if ((ontologySpaceModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { ontologySpace.getName() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
                    Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
                    ontologySpace);
            }
        }
    }

    protected void clearUniqueFindersCache(OntologySpace ontologySpace) {
        OntologySpaceModelImpl ontologySpaceModelImpl = (OntologySpaceModelImpl) ontologySpace;

        Object[] args = new Object[] { ontologySpace.getName() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);

        if ((ontologySpaceModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
            args = new Object[] { ontologySpaceModelImpl.getOriginalName() };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
        }
    }

    /**
     * Creates a new ontology space with the primary key. Does not add the ontology space to the database.
     *
     * @param id the primary key for the new ontology space
     * @return the new ontology space
     */
    @Override
    public OntologySpace create(long id) {
        OntologySpace ontologySpace = new OntologySpaceImpl();

        ontologySpace.setNew(true);
        ontologySpace.setPrimaryKey(id);

        return ontologySpace;
    }

    /**
     * Removes the ontology space with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the ontology space
     * @return the ontology space that was removed
     * @throws com.ext.portlet.NoSuchOntologySpaceException if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace remove(long id)
        throws NoSuchOntologySpaceException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the ontology space with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ontology space
     * @return the ontology space that was removed
     * @throws com.ext.portlet.NoSuchOntologySpaceException if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace remove(Serializable primaryKey)
        throws NoSuchOntologySpaceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            OntologySpace ontologySpace = (OntologySpace) session.get(OntologySpaceImpl.class,
                    primaryKey);

            if (ontologySpace == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchOntologySpaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ontologySpace);
        } catch (NoSuchOntologySpaceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected OntologySpace removeImpl(OntologySpace ontologySpace)
        throws SystemException {
        ontologySpace = toUnwrappedModel(ontologySpace);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ontologySpace)) {
                ontologySpace = (OntologySpace) session.get(OntologySpaceImpl.class,
                        ontologySpace.getPrimaryKeyObj());
            }

            if (ontologySpace != null) {
                session.delete(ontologySpace);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ontologySpace != null) {
            clearCache(ontologySpace);
        }

        return ontologySpace;
    }

    @Override
    public OntologySpace updateImpl(
        com.ext.portlet.model.OntologySpace ontologySpace)
        throws SystemException {
        ontologySpace = toUnwrappedModel(ontologySpace);

        boolean isNew = ontologySpace.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ontologySpace.isNew()) {
                session.save(ontologySpace);

                ontologySpace.setNew(false);
            } else {
                session.merge(ontologySpace);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !OntologySpaceModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceImpl.class, ontologySpace.getPrimaryKey(),
            ontologySpace);

        clearUniqueFindersCache(ontologySpace);
        cacheUniqueFindersCache(ontologySpace);

        return ontologySpace;
    }

    protected OntologySpace toUnwrappedModel(OntologySpace ontologySpace) {
        if (ontologySpace instanceof OntologySpaceImpl) {
            return ontologySpace;
        }

        OntologySpaceImpl ontologySpaceImpl = new OntologySpaceImpl();

        ontologySpaceImpl.setNew(ontologySpace.isNew());
        ontologySpaceImpl.setPrimaryKey(ontologySpace.getPrimaryKey());

        ontologySpaceImpl.setId(ontologySpace.getId());
        ontologySpaceImpl.setName(ontologySpace.getName());
        ontologySpaceImpl.setDescription(ontologySpace.getDescription());
        ontologySpaceImpl.setOrder(ontologySpace.getOrder());

        return ontologySpaceImpl;
    }

    /**
     * Returns the ontology space with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ontology space
     * @return the ontology space
     * @throws com.ext.portlet.NoSuchOntologySpaceException if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace findByPrimaryKey(Serializable primaryKey)
        throws NoSuchOntologySpaceException, SystemException {
        OntologySpace ontologySpace = fetchByPrimaryKey(primaryKey);

        if (ontologySpace == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchOntologySpaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ontologySpace;
    }

    /**
     * Returns the ontology space with the primary key or throws a {@link com.ext.portlet.NoSuchOntologySpaceException} if it could not be found.
     *
     * @param id the primary key of the ontology space
     * @return the ontology space
     * @throws com.ext.portlet.NoSuchOntologySpaceException if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace findByPrimaryKey(long id)
        throws NoSuchOntologySpaceException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the ontology space with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ontology space
     * @return the ontology space, or <code>null</code> if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        OntologySpace ontologySpace = (OntologySpace) EntityCacheUtil.getResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                OntologySpaceImpl.class, primaryKey);

        if (ontologySpace == _nullOntologySpace) {
            return null;
        }

        if (ontologySpace == null) {
            Session session = null;

            try {
                session = openSession();

                ontologySpace = (OntologySpace) session.get(OntologySpaceImpl.class,
                        primaryKey);

                if (ontologySpace != null) {
                    cacheResult(ontologySpace);
                } else {
                    EntityCacheUtil.putResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                        OntologySpaceImpl.class, primaryKey, _nullOntologySpace);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                    OntologySpaceImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ontologySpace;
    }

    /**
     * Returns the ontology space with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the ontology space
     * @return the ontology space, or <code>null</code> if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the ontology spaces.
     *
     * @return the ontology spaces
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologySpace> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology spaces.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologySpaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ontology spaces
     * @param end the upper bound of the range of ontology spaces (not inclusive)
     * @return the range of ontology spaces
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologySpace> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology spaces.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologySpaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ontology spaces
     * @param end the upper bound of the range of ontology spaces (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ontology spaces
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologySpace> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<OntologySpace> list = (List<OntologySpace>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ONTOLOGYSPACE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ONTOLOGYSPACE;

                if (pagination) {
                    sql = sql.concat(OntologySpaceModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<OntologySpace>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologySpace>(list);
                } else {
                    list = (List<OntologySpace>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the ontology spaces from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (OntologySpace ontologySpace : findAll()) {
            remove(ontologySpace);
        }
    }

    /**
     * Returns the number of ontology spaces.
     *
     * @return the number of ontology spaces
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_ONTOLOGYSPACE);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the ontology space persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.OntologySpace")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<OntologySpace>> listenersList = new ArrayList<ModelListener<OntologySpace>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<OntologySpace>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(OntologySpaceImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
