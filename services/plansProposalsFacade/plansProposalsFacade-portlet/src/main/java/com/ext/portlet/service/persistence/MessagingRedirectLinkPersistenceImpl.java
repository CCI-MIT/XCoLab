package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMessagingRedirectLinkException;
import com.ext.portlet.model.MessagingRedirectLink;
import com.ext.portlet.model.impl.MessagingRedirectLinkImpl;
import com.ext.portlet.model.impl.MessagingRedirectLinkModelImpl;
import com.ext.portlet.service.persistence.MessagingRedirectLinkPersistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the messaging redirect link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingRedirectLinkPersistence
 * @see MessagingRedirectLinkUtil
 * @generated
 */
public class MessagingRedirectLinkPersistenceImpl extends BasePersistenceImpl<MessagingRedirectLink>
    implements MessagingRedirectLinkPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MessagingRedirectLinkUtil} to access the messaging redirect link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingRedirectLinkImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkModelImpl.FINDER_CACHE_ENABLED,
            MessagingRedirectLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkModelImpl.FINDER_CACHE_ENABLED,
            MessagingRedirectLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MESSAGINGREDIRECTLINK = "SELECT messagingRedirectLink FROM MessagingRedirectLink messagingRedirectLink";
    private static final String _SQL_COUNT_MESSAGINGREDIRECTLINK = "SELECT COUNT(messagingRedirectLink) FROM MessagingRedirectLink messagingRedirectLink";
    private static final String _ORDER_BY_ENTITY_ALIAS = "messagingRedirectLink.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MessagingRedirectLink exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MessagingRedirectLinkPersistenceImpl.class);
    private static MessagingRedirectLink _nullMessagingRedirectLink = new MessagingRedirectLinkImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MessagingRedirectLink> toCacheModel() {
                return _nullMessagingRedirectLinkCacheModel;
            }
        };

    private static CacheModel<MessagingRedirectLink> _nullMessagingRedirectLinkCacheModel =
        new CacheModel<MessagingRedirectLink>() {
            @Override
            public MessagingRedirectLink toEntityModel() {
                return _nullMessagingRedirectLink;
            }
        };

    public MessagingRedirectLinkPersistenceImpl() {
        setModelClass(MessagingRedirectLink.class);
    }

    /**
     * Caches the messaging redirect link in the entity cache if it is enabled.
     *
     * @param messagingRedirectLink the messaging redirect link
     */
    @Override
    public void cacheResult(MessagingRedirectLink messagingRedirectLink) {
        EntityCacheUtil.putResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkImpl.class,
            messagingRedirectLink.getPrimaryKey(), messagingRedirectLink);

        messagingRedirectLink.resetOriginalValues();
    }

    /**
     * Caches the messaging redirect links in the entity cache if it is enabled.
     *
     * @param messagingRedirectLinks the messaging redirect links
     */
    @Override
    public void cacheResult(List<MessagingRedirectLink> messagingRedirectLinks) {
        for (MessagingRedirectLink messagingRedirectLink : messagingRedirectLinks) {
            if (EntityCacheUtil.getResult(
                        MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingRedirectLinkImpl.class,
                        messagingRedirectLink.getPrimaryKey()) == null) {
                cacheResult(messagingRedirectLink);
            } else {
                messagingRedirectLink.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all messaging redirect links.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MessagingRedirectLinkImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MessagingRedirectLinkImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the messaging redirect link.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MessagingRedirectLink messagingRedirectLink) {
        EntityCacheUtil.removeResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkImpl.class,
            messagingRedirectLink.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MessagingRedirectLink> messagingRedirectLinks) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MessagingRedirectLink messagingRedirectLink : messagingRedirectLinks) {
            EntityCacheUtil.removeResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
                MessagingRedirectLinkImpl.class,
                messagingRedirectLink.getPrimaryKey());
        }
    }

    /**
     * Creates a new messaging redirect link with the primary key. Does not add the messaging redirect link to the database.
     *
     * @param redirectId the primary key for the new messaging redirect link
     * @return the new messaging redirect link
     */
    @Override
    public MessagingRedirectLink create(long redirectId) {
        MessagingRedirectLink messagingRedirectLink = new MessagingRedirectLinkImpl();

        messagingRedirectLink.setNew(true);
        messagingRedirectLink.setPrimaryKey(redirectId);

        return messagingRedirectLink;
    }

    /**
     * Removes the messaging redirect link with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param redirectId the primary key of the messaging redirect link
     * @return the messaging redirect link that was removed
     * @throws com.ext.portlet.NoSuchMessagingRedirectLinkException if a messaging redirect link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingRedirectLink remove(long redirectId)
        throws NoSuchMessagingRedirectLinkException, SystemException {
        return remove((Serializable) redirectId);
    }

    /**
     * Removes the messaging redirect link with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the messaging redirect link
     * @return the messaging redirect link that was removed
     * @throws com.ext.portlet.NoSuchMessagingRedirectLinkException if a messaging redirect link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingRedirectLink remove(Serializable primaryKey)
        throws NoSuchMessagingRedirectLinkException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingRedirectLink messagingRedirectLink = (MessagingRedirectLink) session.get(MessagingRedirectLinkImpl.class,
                    primaryKey);

            if (messagingRedirectLink == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMessagingRedirectLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(messagingRedirectLink);
        } catch (NoSuchMessagingRedirectLinkException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MessagingRedirectLink removeImpl(
        MessagingRedirectLink messagingRedirectLink) throws SystemException {
        messagingRedirectLink = toUnwrappedModel(messagingRedirectLink);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(messagingRedirectLink)) {
                messagingRedirectLink = (MessagingRedirectLink) session.get(MessagingRedirectLinkImpl.class,
                        messagingRedirectLink.getPrimaryKeyObj());
            }

            if (messagingRedirectLink != null) {
                session.delete(messagingRedirectLink);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (messagingRedirectLink != null) {
            clearCache(messagingRedirectLink);
        }

        return messagingRedirectLink;
    }

    @Override
    public MessagingRedirectLink updateImpl(
        com.ext.portlet.model.MessagingRedirectLink messagingRedirectLink)
        throws SystemException {
        messagingRedirectLink = toUnwrappedModel(messagingRedirectLink);

        boolean isNew = messagingRedirectLink.isNew();

        Session session = null;

        try {
            session = openSession();

            if (messagingRedirectLink.isNew()) {
                session.save(messagingRedirectLink);

                messagingRedirectLink.setNew(false);
            } else {
                session.merge(messagingRedirectLink);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
            MessagingRedirectLinkImpl.class,
            messagingRedirectLink.getPrimaryKey(), messagingRedirectLink);

        return messagingRedirectLink;
    }

    protected MessagingRedirectLink toUnwrappedModel(
        MessagingRedirectLink messagingRedirectLink) {
        if (messagingRedirectLink instanceof MessagingRedirectLinkImpl) {
            return messagingRedirectLink;
        }

        MessagingRedirectLinkImpl messagingRedirectLinkImpl = new MessagingRedirectLinkImpl();

        messagingRedirectLinkImpl.setNew(messagingRedirectLink.isNew());
        messagingRedirectLinkImpl.setPrimaryKey(messagingRedirectLink.getPrimaryKey());

        messagingRedirectLinkImpl.setRedirectId(messagingRedirectLink.getRedirectId());
        messagingRedirectLinkImpl.setLink(messagingRedirectLink.getLink());
        messagingRedirectLinkImpl.setMessageId(messagingRedirectLink.getMessageId());
        messagingRedirectLinkImpl.setCreateDate(messagingRedirectLink.getCreateDate());

        return messagingRedirectLinkImpl;
    }

    /**
     * Returns the messaging redirect link with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the messaging redirect link
     * @return the messaging redirect link
     * @throws com.ext.portlet.NoSuchMessagingRedirectLinkException if a messaging redirect link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingRedirectLink findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMessagingRedirectLinkException, SystemException {
        MessagingRedirectLink messagingRedirectLink = fetchByPrimaryKey(primaryKey);

        if (messagingRedirectLink == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMessagingRedirectLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return messagingRedirectLink;
    }

    /**
     * Returns the messaging redirect link with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingRedirectLinkException} if it could not be found.
     *
     * @param redirectId the primary key of the messaging redirect link
     * @return the messaging redirect link
     * @throws com.ext.portlet.NoSuchMessagingRedirectLinkException if a messaging redirect link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingRedirectLink findByPrimaryKey(long redirectId)
        throws NoSuchMessagingRedirectLinkException, SystemException {
        return findByPrimaryKey((Serializable) redirectId);
    }

    /**
     * Returns the messaging redirect link with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the messaging redirect link
     * @return the messaging redirect link, or <code>null</code> if a messaging redirect link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingRedirectLink fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MessagingRedirectLink messagingRedirectLink = (MessagingRedirectLink) EntityCacheUtil.getResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
                MessagingRedirectLinkImpl.class, primaryKey);

        if (messagingRedirectLink == _nullMessagingRedirectLink) {
            return null;
        }

        if (messagingRedirectLink == null) {
            Session session = null;

            try {
                session = openSession();

                messagingRedirectLink = (MessagingRedirectLink) session.get(MessagingRedirectLinkImpl.class,
                        primaryKey);

                if (messagingRedirectLink != null) {
                    cacheResult(messagingRedirectLink);
                } else {
                    EntityCacheUtil.putResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingRedirectLinkImpl.class, primaryKey,
                        _nullMessagingRedirectLink);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MessagingRedirectLinkModelImpl.ENTITY_CACHE_ENABLED,
                    MessagingRedirectLinkImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return messagingRedirectLink;
    }

    /**
     * Returns the messaging redirect link with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param redirectId the primary key of the messaging redirect link
     * @return the messaging redirect link, or <code>null</code> if a messaging redirect link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingRedirectLink fetchByPrimaryKey(long redirectId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) redirectId);
    }

    /**
     * Returns all the messaging redirect links.
     *
     * @return the messaging redirect links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingRedirectLink> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the messaging redirect links.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingRedirectLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging redirect links
     * @param end the upper bound of the range of messaging redirect links (not inclusive)
     * @return the range of messaging redirect links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingRedirectLink> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the messaging redirect links.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingRedirectLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging redirect links
     * @param end the upper bound of the range of messaging redirect links (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of messaging redirect links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingRedirectLink> findAll(int start, int end,
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

        List<MessagingRedirectLink> list = (List<MessagingRedirectLink>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MESSAGINGREDIRECTLINK);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MESSAGINGREDIRECTLINK;

                if (pagination) {
                    sql = sql.concat(MessagingRedirectLinkModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MessagingRedirectLink>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MessagingRedirectLink>(list);
                } else {
                    list = (List<MessagingRedirectLink>) QueryUtil.list(q,
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
     * Removes all the messaging redirect links from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MessagingRedirectLink messagingRedirectLink : findAll()) {
            remove(messagingRedirectLink);
        }
    }

    /**
     * Returns the number of messaging redirect links.
     *
     * @return the number of messaging redirect links
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

                Query q = session.createQuery(_SQL_COUNT_MESSAGINGREDIRECTLINK);

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
     * Initializes the messaging redirect link persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MessagingRedirectLink")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingRedirectLink>> listenersList = new ArrayList<ModelListener<MessagingRedirectLink>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingRedirectLink>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MessagingRedirectLinkImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
