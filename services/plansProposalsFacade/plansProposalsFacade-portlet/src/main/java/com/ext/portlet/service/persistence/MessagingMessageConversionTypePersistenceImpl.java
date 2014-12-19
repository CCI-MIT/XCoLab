package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMessagingMessageConversionTypeException;
import com.ext.portlet.model.MessagingMessageConversionType;
import com.ext.portlet.model.impl.MessagingMessageConversionTypeImpl;
import com.ext.portlet.model.impl.MessagingMessageConversionTypeModelImpl;
import com.ext.portlet.service.persistence.MessagingMessageConversionTypePersistence;

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

/**
 * The persistence implementation for the messaging message conversion type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionTypePersistence
 * @see MessagingMessageConversionTypeUtil
 * @generated
 */
public class MessagingMessageConversionTypePersistenceImpl
    extends BasePersistenceImpl<MessagingMessageConversionType>
    implements MessagingMessageConversionTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MessagingMessageConversionTypeUtil} to access the messaging message conversion type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingMessageConversionTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_FINDBYNAME = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByfindByName", new String[] { String.class.getName() },
            MessagingMessageConversionTypeModelImpl.NAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYNAME = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByfindByName", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_FINDBYNAME_NAME_1 = "messagingMessageConversionType.name IS NULL";
    private static final String _FINDER_COLUMN_FINDBYNAME_NAME_2 = "messagingMessageConversionType.name = ?";
    private static final String _FINDER_COLUMN_FINDBYNAME_NAME_3 = "(messagingMessageConversionType.name IS NULL OR messagingMessageConversionType.name = '')";
    private static final String _SQL_SELECT_MESSAGINGMESSAGECONVERSIONTYPE = "SELECT messagingMessageConversionType FROM MessagingMessageConversionType messagingMessageConversionType";
    private static final String _SQL_SELECT_MESSAGINGMESSAGECONVERSIONTYPE_WHERE =
        "SELECT messagingMessageConversionType FROM MessagingMessageConversionType messagingMessageConversionType WHERE ";
    private static final String _SQL_COUNT_MESSAGINGMESSAGECONVERSIONTYPE = "SELECT COUNT(messagingMessageConversionType) FROM MessagingMessageConversionType messagingMessageConversionType";
    private static final String _SQL_COUNT_MESSAGINGMESSAGECONVERSIONTYPE_WHERE = "SELECT COUNT(messagingMessageConversionType) FROM MessagingMessageConversionType messagingMessageConversionType WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "messagingMessageConversionType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MessagingMessageConversionType exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MessagingMessageConversionType exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MessagingMessageConversionTypePersistenceImpl.class);
    private static MessagingMessageConversionType _nullMessagingMessageConversionType =
        new MessagingMessageConversionTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MessagingMessageConversionType> toCacheModel() {
                return _nullMessagingMessageConversionTypeCacheModel;
            }
        };

    private static CacheModel<MessagingMessageConversionType> _nullMessagingMessageConversionTypeCacheModel =
        new CacheModel<MessagingMessageConversionType>() {
            @Override
            public MessagingMessageConversionType toEntityModel() {
                return _nullMessagingMessageConversionType;
            }
        };

    public MessagingMessageConversionTypePersistenceImpl() {
        setModelClass(MessagingMessageConversionType.class);
    }

    /**
     * Returns the messaging message conversion type where name = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionTypeException} if it could not be found.
     *
     * @param name the name
     * @return the matching messaging message conversion type
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a matching messaging message conversion type could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType findByfindByName(String name)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        MessagingMessageConversionType messagingMessageConversionType = fetchByfindByName(name);

        if (messagingMessageConversionType == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("name=");
            msg.append(name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMessagingMessageConversionTypeException(msg.toString());
        }

        return messagingMessageConversionType;
    }

    /**
     * Returns the messaging message conversion type where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param name the name
     * @return the matching messaging message conversion type, or <code>null</code> if a matching messaging message conversion type could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType fetchByfindByName(String name)
        throws SystemException {
        return fetchByfindByName(name, true);
    }

    /**
     * Returns the messaging message conversion type where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param name the name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching messaging message conversion type, or <code>null</code> if a matching messaging message conversion type could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType fetchByfindByName(String name,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                    finderArgs, this);
        }

        if (result instanceof MessagingMessageConversionType) {
            MessagingMessageConversionType messagingMessageConversionType = (MessagingMessageConversionType) result;

            if (!Validator.equals(name, messagingMessageConversionType.getName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_MESSAGINGMESSAGECONVERSIONTYPE_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_2);
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

                List<MessagingMessageConversionType> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "MessagingMessageConversionTypePersistenceImpl.fetchByfindByName(String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    MessagingMessageConversionType messagingMessageConversionType =
                        list.get(0);

                    result = messagingMessageConversionType;

                    cacheResult(messagingMessageConversionType);

                    if ((messagingMessageConversionType.getName() == null) ||
                            !messagingMessageConversionType.getName()
                                                               .equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                            finderArgs, messagingMessageConversionType);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (MessagingMessageConversionType) result;
        }
    }

    /**
     * Removes the messaging message conversion type where name = &#63; from the database.
     *
     * @param name the name
     * @return the messaging message conversion type that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType removeByfindByName(String name)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        MessagingMessageConversionType messagingMessageConversionType = findByfindByName(name);

        return remove(messagingMessageConversionType);
    }

    /**
     * Returns the number of messaging message conversion types where name = &#63;.
     *
     * @param name the name
     * @return the number of matching messaging message conversion types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByfindByName(String name) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FINDBYNAME;

        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MESSAGINGMESSAGECONVERSIONTYPE_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_2);
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
     * Caches the messaging message conversion type in the entity cache if it is enabled.
     *
     * @param messagingMessageConversionType the messaging message conversion type
     */
    @Override
    public void cacheResult(
        MessagingMessageConversionType messagingMessageConversionType) {
        EntityCacheUtil.putResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            messagingMessageConversionType.getPrimaryKey(),
            messagingMessageConversionType);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
            new Object[] { messagingMessageConversionType.getName() },
            messagingMessageConversionType);

        messagingMessageConversionType.resetOriginalValues();
    }

    /**
     * Caches the messaging message conversion types in the entity cache if it is enabled.
     *
     * @param messagingMessageConversionTypes the messaging message conversion types
     */
    @Override
    public void cacheResult(
        List<MessagingMessageConversionType> messagingMessageConversionTypes) {
        for (MessagingMessageConversionType messagingMessageConversionType : messagingMessageConversionTypes) {
            if (EntityCacheUtil.getResult(
                        MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageConversionTypeImpl.class,
                        messagingMessageConversionType.getPrimaryKey()) == null) {
                cacheResult(messagingMessageConversionType);
            } else {
                messagingMessageConversionType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all messaging message conversion types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MessagingMessageConversionTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MessagingMessageConversionTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the messaging message conversion type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        MessagingMessageConversionType messagingMessageConversionType) {
        EntityCacheUtil.removeResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            messagingMessageConversionType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(messagingMessageConversionType);
    }

    @Override
    public void clearCache(
        List<MessagingMessageConversionType> messagingMessageConversionTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MessagingMessageConversionType messagingMessageConversionType : messagingMessageConversionTypes) {
            EntityCacheUtil.removeResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageConversionTypeImpl.class,
                messagingMessageConversionType.getPrimaryKey());

            clearUniqueFindersCache(messagingMessageConversionType);
        }
    }

    protected void cacheUniqueFindersCache(
        MessagingMessageConversionType messagingMessageConversionType) {
        if (messagingMessageConversionType.isNew()) {
            Object[] args = new Object[] {
                    messagingMessageConversionType.getName()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYNAME, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME, args,
                messagingMessageConversionType);
        } else {
            MessagingMessageConversionTypeModelImpl messagingMessageConversionTypeModelImpl =
                (MessagingMessageConversionTypeModelImpl) messagingMessageConversionType;

            if ((messagingMessageConversionTypeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_FINDBYNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        messagingMessageConversionType.getName()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYNAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                    args, messagingMessageConversionType);
            }
        }
    }

    protected void clearUniqueFindersCache(
        MessagingMessageConversionType messagingMessageConversionType) {
        MessagingMessageConversionTypeModelImpl messagingMessageConversionTypeModelImpl =
            (MessagingMessageConversionTypeModelImpl) messagingMessageConversionType;

        Object[] args = new Object[] { messagingMessageConversionType.getName() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYNAME, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAME, args);

        if ((messagingMessageConversionTypeModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_FINDBYNAME.getColumnBitmask()) != 0) {
            args = new Object[] {
                    messagingMessageConversionTypeModelImpl.getOriginalName()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYNAME, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAME, args);
        }
    }

    /**
     * Creates a new messaging message conversion type with the primary key. Does not add the messaging message conversion type to the database.
     *
     * @param typeId the primary key for the new messaging message conversion type
     * @return the new messaging message conversion type
     */
    @Override
    public MessagingMessageConversionType create(long typeId) {
        MessagingMessageConversionType messagingMessageConversionType = new MessagingMessageConversionTypeImpl();

        messagingMessageConversionType.setNew(true);
        messagingMessageConversionType.setPrimaryKey(typeId);

        return messagingMessageConversionType;
    }

    /**
     * Removes the messaging message conversion type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param typeId the primary key of the messaging message conversion type
     * @return the messaging message conversion type that was removed
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType remove(long typeId)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        return remove((Serializable) typeId);
    }

    /**
     * Removes the messaging message conversion type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the messaging message conversion type
     * @return the messaging message conversion type that was removed
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType remove(Serializable primaryKey)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingMessageConversionType messagingMessageConversionType = (MessagingMessageConversionType) session.get(MessagingMessageConversionTypeImpl.class,
                    primaryKey);

            if (messagingMessageConversionType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMessagingMessageConversionTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(messagingMessageConversionType);
        } catch (NoSuchMessagingMessageConversionTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MessagingMessageConversionType removeImpl(
        MessagingMessageConversionType messagingMessageConversionType)
        throws SystemException {
        messagingMessageConversionType = toUnwrappedModel(messagingMessageConversionType);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(messagingMessageConversionType)) {
                messagingMessageConversionType = (MessagingMessageConversionType) session.get(MessagingMessageConversionTypeImpl.class,
                        messagingMessageConversionType.getPrimaryKeyObj());
            }

            if (messagingMessageConversionType != null) {
                session.delete(messagingMessageConversionType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (messagingMessageConversionType != null) {
            clearCache(messagingMessageConversionType);
        }

        return messagingMessageConversionType;
    }

    @Override
    public MessagingMessageConversionType updateImpl(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType)
        throws SystemException {
        messagingMessageConversionType = toUnwrappedModel(messagingMessageConversionType);

        boolean isNew = messagingMessageConversionType.isNew();

        Session session = null;

        try {
            session = openSession();

            if (messagingMessageConversionType.isNew()) {
                session.save(messagingMessageConversionType);

                messagingMessageConversionType.setNew(false);
            } else {
                session.merge(messagingMessageConversionType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew ||
                !MessagingMessageConversionTypeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            messagingMessageConversionType.getPrimaryKey(),
            messagingMessageConversionType);

        clearUniqueFindersCache(messagingMessageConversionType);
        cacheUniqueFindersCache(messagingMessageConversionType);

        return messagingMessageConversionType;
    }

    protected MessagingMessageConversionType toUnwrappedModel(
        MessagingMessageConversionType messagingMessageConversionType) {
        if (messagingMessageConversionType instanceof MessagingMessageConversionTypeImpl) {
            return messagingMessageConversionType;
        }

        MessagingMessageConversionTypeImpl messagingMessageConversionTypeImpl = new MessagingMessageConversionTypeImpl();

        messagingMessageConversionTypeImpl.setNew(messagingMessageConversionType.isNew());
        messagingMessageConversionTypeImpl.setPrimaryKey(messagingMessageConversionType.getPrimaryKey());

        messagingMessageConversionTypeImpl.setTypeId(messagingMessageConversionType.getTypeId());
        messagingMessageConversionTypeImpl.setName(messagingMessageConversionType.getName());
        messagingMessageConversionTypeImpl.setDescription(messagingMessageConversionType.getDescription());

        return messagingMessageConversionTypeImpl;
    }

    /**
     * Returns the messaging message conversion type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the messaging message conversion type
     * @return the messaging message conversion type
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType findByPrimaryKey(
        Serializable primaryKey)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        MessagingMessageConversionType messagingMessageConversionType = fetchByPrimaryKey(primaryKey);

        if (messagingMessageConversionType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMessagingMessageConversionTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return messagingMessageConversionType;
    }

    /**
     * Returns the messaging message conversion type with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionTypeException} if it could not be found.
     *
     * @param typeId the primary key of the messaging message conversion type
     * @return the messaging message conversion type
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType findByPrimaryKey(long typeId)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        return findByPrimaryKey((Serializable) typeId);
    }

    /**
     * Returns the messaging message conversion type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the messaging message conversion type
     * @return the messaging message conversion type, or <code>null</code> if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        MessagingMessageConversionType messagingMessageConversionType = (MessagingMessageConversionType) EntityCacheUtil.getResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageConversionTypeImpl.class, primaryKey);

        if (messagingMessageConversionType == _nullMessagingMessageConversionType) {
            return null;
        }

        if (messagingMessageConversionType == null) {
            Session session = null;

            try {
                session = openSession();

                messagingMessageConversionType = (MessagingMessageConversionType) session.get(MessagingMessageConversionTypeImpl.class,
                        primaryKey);

                if (messagingMessageConversionType != null) {
                    cacheResult(messagingMessageConversionType);
                } else {
                    EntityCacheUtil.putResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageConversionTypeImpl.class, primaryKey,
                        _nullMessagingMessageConversionType);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                    MessagingMessageConversionTypeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return messagingMessageConversionType;
    }

    /**
     * Returns the messaging message conversion type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param typeId the primary key of the messaging message conversion type
     * @return the messaging message conversion type, or <code>null</code> if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType fetchByPrimaryKey(long typeId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) typeId);
    }

    /**
     * Returns all the messaging message conversion types.
     *
     * @return the messaging message conversion types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageConversionType> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the messaging message conversion types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging message conversion types
     * @param end the upper bound of the range of messaging message conversion types (not inclusive)
     * @return the range of messaging message conversion types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageConversionType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the messaging message conversion types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging message conversion types
     * @param end the upper bound of the range of messaging message conversion types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of messaging message conversion types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageConversionType> findAll(int start, int end,
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

        List<MessagingMessageConversionType> list = (List<MessagingMessageConversionType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MESSAGINGMESSAGECONVERSIONTYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MESSAGINGMESSAGECONVERSIONTYPE;

                if (pagination) {
                    sql = sql.concat(MessagingMessageConversionTypeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MessagingMessageConversionType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MessagingMessageConversionType>(list);
                } else {
                    list = (List<MessagingMessageConversionType>) QueryUtil.list(q,
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
     * Removes all the messaging message conversion types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MessagingMessageConversionType messagingMessageConversionType : findAll()) {
            remove(messagingMessageConversionType);
        }
    }

    /**
     * Returns the number of messaging message conversion types.
     *
     * @return the number of messaging message conversion types
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

                Query q = session.createQuery(_SQL_COUNT_MESSAGINGMESSAGECONVERSIONTYPE);

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

    /**
     * Initializes the messaging message conversion type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MessagingMessageConversionType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingMessageConversionType>> listenersList =
                    new ArrayList<ModelListener<MessagingMessageConversionType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingMessageConversionType>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MessagingMessageConversionTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
