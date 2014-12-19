package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchDiscussionMessageFlagException;
import com.ext.portlet.model.DiscussionMessageFlag;
import com.ext.portlet.model.impl.DiscussionMessageFlagImpl;
import com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl;
import com.ext.portlet.service.persistence.DiscussionMessageFlagPersistence;

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
 * The persistence implementation for the discussion message flag service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlagPersistence
 * @see DiscussionMessageFlagUtil
 * @generated
 */
public class DiscussionMessageFlagPersistenceImpl extends BasePersistenceImpl<DiscussionMessageFlag>
    implements DiscussionMessageFlagPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DiscussionMessageFlagUtil} to access the discussion message flag persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DiscussionMessageFlagImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MESSAGEID =
        new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMessageId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID =
        new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMessageId",
            new String[] { Long.class.getName() },
            DiscussionMessageFlagModelImpl.MESSAGEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGEID = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMessageId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_MESSAGEID_MESSAGEID_2 = "discussionMessageFlag.messageId = ?";
    public static final FinderPath FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByMessageIdFlagType",
            new String[] { Long.class.getName(), String.class.getName() },
            DiscussionMessageFlagModelImpl.MESSAGEID_COLUMN_BITMASK |
            DiscussionMessageFlagModelImpl.FLAGTYPE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGEIDFLAGTYPE = new FinderPath(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByMessageIdFlagType",
            new String[] { Long.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_MESSAGEIDFLAGTYPE_MESSAGEID_2 = "discussionMessageFlag.messageId = ? AND ";
    private static final String _FINDER_COLUMN_MESSAGEIDFLAGTYPE_FLAGTYPE_1 = "discussionMessageFlag.flagType IS NULL";
    private static final String _FINDER_COLUMN_MESSAGEIDFLAGTYPE_FLAGTYPE_2 = "discussionMessageFlag.flagType = ?";
    private static final String _FINDER_COLUMN_MESSAGEIDFLAGTYPE_FLAGTYPE_3 = "(discussionMessageFlag.flagType IS NULL OR discussionMessageFlag.flagType = '')";
    private static final String _SQL_SELECT_DISCUSSIONMESSAGEFLAG = "SELECT discussionMessageFlag FROM DiscussionMessageFlag discussionMessageFlag";
    private static final String _SQL_SELECT_DISCUSSIONMESSAGEFLAG_WHERE = "SELECT discussionMessageFlag FROM DiscussionMessageFlag discussionMessageFlag WHERE ";
    private static final String _SQL_COUNT_DISCUSSIONMESSAGEFLAG = "SELECT COUNT(discussionMessageFlag) FROM DiscussionMessageFlag discussionMessageFlag";
    private static final String _SQL_COUNT_DISCUSSIONMESSAGEFLAG_WHERE = "SELECT COUNT(discussionMessageFlag) FROM DiscussionMessageFlag discussionMessageFlag WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "discussionMessageFlag.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DiscussionMessageFlag exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DiscussionMessageFlag exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DiscussionMessageFlagPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "data"
            });
    private static DiscussionMessageFlag _nullDiscussionMessageFlag = new DiscussionMessageFlagImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DiscussionMessageFlag> toCacheModel() {
                return _nullDiscussionMessageFlagCacheModel;
            }
        };

    private static CacheModel<DiscussionMessageFlag> _nullDiscussionMessageFlagCacheModel =
        new CacheModel<DiscussionMessageFlag>() {
            @Override
            public DiscussionMessageFlag toEntityModel() {
                return _nullDiscussionMessageFlag;
            }
        };

    public DiscussionMessageFlagPersistenceImpl() {
        setModelClass(DiscussionMessageFlag.class);
    }

    /**
     * Returns all the discussion message flags where messageId = &#63;.
     *
     * @param messageId the message ID
     * @return the matching discussion message flags
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionMessageFlag> findByMessageId(long messageId)
        throws SystemException {
        return findByMessageId(messageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the discussion message flags where messageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param messageId the message ID
     * @param start the lower bound of the range of discussion message flags
     * @param end the upper bound of the range of discussion message flags (not inclusive)
     * @return the range of matching discussion message flags
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionMessageFlag> findByMessageId(long messageId,
        int start, int end) throws SystemException {
        return findByMessageId(messageId, start, end, null);
    }

    /**
     * Returns an ordered range of all the discussion message flags where messageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param messageId the message ID
     * @param start the lower bound of the range of discussion message flags
     * @param end the upper bound of the range of discussion message flags (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching discussion message flags
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionMessageFlag> findByMessageId(long messageId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID;
            finderArgs = new Object[] { messageId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MESSAGEID;
            finderArgs = new Object[] { messageId, start, end, orderByComparator };
        }

        List<DiscussionMessageFlag> list = (List<DiscussionMessageFlag>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (DiscussionMessageFlag discussionMessageFlag : list) {
                if ((messageId != discussionMessageFlag.getMessageId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_DISCUSSIONMESSAGEFLAG_WHERE);

            query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(DiscussionMessageFlagModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                if (!pagination) {
                    list = (List<DiscussionMessageFlag>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DiscussionMessageFlag>(list);
                } else {
                    list = (List<DiscussionMessageFlag>) QueryUtil.list(q,
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
     * Returns the first discussion message flag in the ordered set where messageId = &#63;.
     *
     * @param messageId the message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching discussion message flag
     * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a matching discussion message flag could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag findByMessageId_First(long messageId,
        OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        DiscussionMessageFlag discussionMessageFlag = fetchByMessageId_First(messageId,
                orderByComparator);

        if (discussionMessageFlag != null) {
            return discussionMessageFlag;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("messageId=");
        msg.append(messageId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchDiscussionMessageFlagException(msg.toString());
    }

    /**
     * Returns the first discussion message flag in the ordered set where messageId = &#63;.
     *
     * @param messageId the message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching discussion message flag, or <code>null</code> if a matching discussion message flag could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag fetchByMessageId_First(long messageId,
        OrderByComparator orderByComparator) throws SystemException {
        List<DiscussionMessageFlag> list = findByMessageId(messageId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last discussion message flag in the ordered set where messageId = &#63;.
     *
     * @param messageId the message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching discussion message flag
     * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a matching discussion message flag could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag findByMessageId_Last(long messageId,
        OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        DiscussionMessageFlag discussionMessageFlag = fetchByMessageId_Last(messageId,
                orderByComparator);

        if (discussionMessageFlag != null) {
            return discussionMessageFlag;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("messageId=");
        msg.append(messageId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchDiscussionMessageFlagException(msg.toString());
    }

    /**
     * Returns the last discussion message flag in the ordered set where messageId = &#63;.
     *
     * @param messageId the message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching discussion message flag, or <code>null</code> if a matching discussion message flag could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag fetchByMessageId_Last(long messageId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByMessageId(messageId);

        if (count == 0) {
            return null;
        }

        List<DiscussionMessageFlag> list = findByMessageId(messageId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the discussion message flags before and after the current discussion message flag in the ordered set where messageId = &#63;.
     *
     * @param pk the primary key of the current discussion message flag
     * @param messageId the message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next discussion message flag
     * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag[] findByMessageId_PrevAndNext(long pk,
        long messageId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        DiscussionMessageFlag discussionMessageFlag = findByPrimaryKey(pk);

        Session session = null;

        try {
            session = openSession();

            DiscussionMessageFlag[] array = new DiscussionMessageFlagImpl[3];

            array[0] = getByMessageId_PrevAndNext(session,
                    discussionMessageFlag, messageId, orderByComparator, true);

            array[1] = discussionMessageFlag;

            array[2] = getByMessageId_PrevAndNext(session,
                    discussionMessageFlag, messageId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected DiscussionMessageFlag getByMessageId_PrevAndNext(
        Session session, DiscussionMessageFlag discussionMessageFlag,
        long messageId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_DISCUSSIONMESSAGEFLAG_WHERE);

        query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(DiscussionMessageFlagModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(messageId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(discussionMessageFlag);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<DiscussionMessageFlag> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the discussion message flags where messageId = &#63; from the database.
     *
     * @param messageId the message ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByMessageId(long messageId) throws SystemException {
        for (DiscussionMessageFlag discussionMessageFlag : findByMessageId(
                messageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(discussionMessageFlag);
        }
    }

    /**
     * Returns the number of discussion message flags where messageId = &#63;.
     *
     * @param messageId the message ID
     * @return the number of matching discussion message flags
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByMessageId(long messageId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MESSAGEID;

        Object[] finderArgs = new Object[] { messageId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_DISCUSSIONMESSAGEFLAG_WHERE);

            query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

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
     * Returns the discussion message flag where messageId = &#63; and flagType = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionMessageFlagException} if it could not be found.
     *
     * @param messageId the message ID
     * @param flagType the flag type
     * @return the matching discussion message flag
     * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a matching discussion message flag could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag findByMessageIdFlagType(long messageId,
        String flagType)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        DiscussionMessageFlag discussionMessageFlag = fetchByMessageIdFlagType(messageId,
                flagType);

        if (discussionMessageFlag == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("messageId=");
            msg.append(messageId);

            msg.append(", flagType=");
            msg.append(flagType);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDiscussionMessageFlagException(msg.toString());
        }

        return discussionMessageFlag;
    }

    /**
     * Returns the discussion message flag where messageId = &#63; and flagType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param messageId the message ID
     * @param flagType the flag type
     * @return the matching discussion message flag, or <code>null</code> if a matching discussion message flag could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag fetchByMessageIdFlagType(long messageId,
        String flagType) throws SystemException {
        return fetchByMessageIdFlagType(messageId, flagType, true);
    }

    /**
     * Returns the discussion message flag where messageId = &#63; and flagType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param messageId the message ID
     * @param flagType the flag type
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching discussion message flag, or <code>null</code> if a matching discussion message flag could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag fetchByMessageIdFlagType(long messageId,
        String flagType, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { messageId, flagType };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE,
                    finderArgs, this);
        }

        if (result instanceof DiscussionMessageFlag) {
            DiscussionMessageFlag discussionMessageFlag = (DiscussionMessageFlag) result;

            if ((messageId != discussionMessageFlag.getMessageId()) ||
                    !Validator.equals(flagType,
                        discussionMessageFlag.getFlagType())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_DISCUSSIONMESSAGEFLAG_WHERE);

            query.append(_FINDER_COLUMN_MESSAGEIDFLAGTYPE_MESSAGEID_2);

            boolean bindFlagType = false;

            if (flagType == null) {
                query.append(_FINDER_COLUMN_MESSAGEIDFLAGTYPE_FLAGTYPE_1);
            } else if (flagType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MESSAGEIDFLAGTYPE_FLAGTYPE_3);
            } else {
                bindFlagType = true;

                query.append(_FINDER_COLUMN_MESSAGEIDFLAGTYPE_FLAGTYPE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                if (bindFlagType) {
                    qPos.add(flagType);
                }

                List<DiscussionMessageFlag> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE,
                        finderArgs, list);
                } else {
                    DiscussionMessageFlag discussionMessageFlag = list.get(0);

                    result = discussionMessageFlag;

                    cacheResult(discussionMessageFlag);

                    if ((discussionMessageFlag.getMessageId() != messageId) ||
                            (discussionMessageFlag.getFlagType() == null) ||
                            !discussionMessageFlag.getFlagType().equals(flagType)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE,
                            finderArgs, discussionMessageFlag);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (DiscussionMessageFlag) result;
        }
    }

    /**
     * Removes the discussion message flag where messageId = &#63; and flagType = &#63; from the database.
     *
     * @param messageId the message ID
     * @param flagType the flag type
     * @return the discussion message flag that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag removeByMessageIdFlagType(long messageId,
        String flagType)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        DiscussionMessageFlag discussionMessageFlag = findByMessageIdFlagType(messageId,
                flagType);

        return remove(discussionMessageFlag);
    }

    /**
     * Returns the number of discussion message flags where messageId = &#63; and flagType = &#63;.
     *
     * @param messageId the message ID
     * @param flagType the flag type
     * @return the number of matching discussion message flags
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByMessageIdFlagType(long messageId, String flagType)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MESSAGEIDFLAGTYPE;

        Object[] finderArgs = new Object[] { messageId, flagType };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_DISCUSSIONMESSAGEFLAG_WHERE);

            query.append(_FINDER_COLUMN_MESSAGEIDFLAGTYPE_MESSAGEID_2);

            boolean bindFlagType = false;

            if (flagType == null) {
                query.append(_FINDER_COLUMN_MESSAGEIDFLAGTYPE_FLAGTYPE_1);
            } else if (flagType.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MESSAGEIDFLAGTYPE_FLAGTYPE_3);
            } else {
                bindFlagType = true;

                query.append(_FINDER_COLUMN_MESSAGEIDFLAGTYPE_FLAGTYPE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                if (bindFlagType) {
                    qPos.add(flagType);
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
     * Caches the discussion message flag in the entity cache if it is enabled.
     *
     * @param discussionMessageFlag the discussion message flag
     */
    @Override
    public void cacheResult(DiscussionMessageFlag discussionMessageFlag) {
        EntityCacheUtil.putResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class,
            discussionMessageFlag.getPrimaryKey(), discussionMessageFlag);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE,
            new Object[] {
                discussionMessageFlag.getMessageId(),
                discussionMessageFlag.getFlagType()
            }, discussionMessageFlag);

        discussionMessageFlag.resetOriginalValues();
    }

    /**
     * Caches the discussion message flags in the entity cache if it is enabled.
     *
     * @param discussionMessageFlags the discussion message flags
     */
    @Override
    public void cacheResult(List<DiscussionMessageFlag> discussionMessageFlags) {
        for (DiscussionMessageFlag discussionMessageFlag : discussionMessageFlags) {
            if (EntityCacheUtil.getResult(
                        DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionMessageFlagImpl.class,
                        discussionMessageFlag.getPrimaryKey()) == null) {
                cacheResult(discussionMessageFlag);
            } else {
                discussionMessageFlag.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all discussion message flags.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DiscussionMessageFlagImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DiscussionMessageFlagImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the discussion message flag.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DiscussionMessageFlag discussionMessageFlag) {
        EntityCacheUtil.removeResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class,
            discussionMessageFlag.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(discussionMessageFlag);
    }

    @Override
    public void clearCache(List<DiscussionMessageFlag> discussionMessageFlags) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DiscussionMessageFlag discussionMessageFlag : discussionMessageFlags) {
            EntityCacheUtil.removeResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionMessageFlagImpl.class,
                discussionMessageFlag.getPrimaryKey());

            clearUniqueFindersCache(discussionMessageFlag);
        }
    }

    protected void cacheUniqueFindersCache(
        DiscussionMessageFlag discussionMessageFlag) {
        if (discussionMessageFlag.isNew()) {
            Object[] args = new Object[] {
                    discussionMessageFlag.getMessageId(),
                    discussionMessageFlag.getFlagType()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MESSAGEIDFLAGTYPE,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE,
                args, discussionMessageFlag);
        } else {
            DiscussionMessageFlagModelImpl discussionMessageFlagModelImpl = (DiscussionMessageFlagModelImpl) discussionMessageFlag;

            if ((discussionMessageFlagModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        discussionMessageFlag.getMessageId(),
                        discussionMessageFlag.getFlagType()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MESSAGEIDFLAGTYPE,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE,
                    args, discussionMessageFlag);
            }
        }
    }

    protected void clearUniqueFindersCache(
        DiscussionMessageFlag discussionMessageFlag) {
        DiscussionMessageFlagModelImpl discussionMessageFlagModelImpl = (DiscussionMessageFlagModelImpl) discussionMessageFlag;

        Object[] args = new Object[] {
                discussionMessageFlag.getMessageId(),
                discussionMessageFlag.getFlagType()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEIDFLAGTYPE,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE,
            args);

        if ((discussionMessageFlagModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE.getColumnBitmask()) != 0) {
            args = new Object[] {
                    discussionMessageFlagModelImpl.getOriginalMessageId(),
                    discussionMessageFlagModelImpl.getOriginalFlagType()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEIDFLAGTYPE,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGEIDFLAGTYPE,
                args);
        }
    }

    /**
     * Creates a new discussion message flag with the primary key. Does not add the discussion message flag to the database.
     *
     * @param pk the primary key for the new discussion message flag
     * @return the new discussion message flag
     */
    @Override
    public DiscussionMessageFlag create(long pk) {
        DiscussionMessageFlag discussionMessageFlag = new DiscussionMessageFlagImpl();

        discussionMessageFlag.setNew(true);
        discussionMessageFlag.setPrimaryKey(pk);

        return discussionMessageFlag;
    }

    /**
     * Removes the discussion message flag with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param pk the primary key of the discussion message flag
     * @return the discussion message flag that was removed
     * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag remove(long pk)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        return remove((Serializable) pk);
    }

    /**
     * Removes the discussion message flag with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the discussion message flag
     * @return the discussion message flag that was removed
     * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag remove(Serializable primaryKey)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DiscussionMessageFlag discussionMessageFlag = (DiscussionMessageFlag) session.get(DiscussionMessageFlagImpl.class,
                    primaryKey);

            if (discussionMessageFlag == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDiscussionMessageFlagException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(discussionMessageFlag);
        } catch (NoSuchDiscussionMessageFlagException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DiscussionMessageFlag removeImpl(
        DiscussionMessageFlag discussionMessageFlag) throws SystemException {
        discussionMessageFlag = toUnwrappedModel(discussionMessageFlag);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(discussionMessageFlag)) {
                discussionMessageFlag = (DiscussionMessageFlag) session.get(DiscussionMessageFlagImpl.class,
                        discussionMessageFlag.getPrimaryKeyObj());
            }

            if (discussionMessageFlag != null) {
                session.delete(discussionMessageFlag);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (discussionMessageFlag != null) {
            clearCache(discussionMessageFlag);
        }

        return discussionMessageFlag;
    }

    @Override
    public DiscussionMessageFlag updateImpl(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag)
        throws SystemException {
        discussionMessageFlag = toUnwrappedModel(discussionMessageFlag);

        boolean isNew = discussionMessageFlag.isNew();

        DiscussionMessageFlagModelImpl discussionMessageFlagModelImpl = (DiscussionMessageFlagModelImpl) discussionMessageFlag;

        Session session = null;

        try {
            session = openSession();

            if (discussionMessageFlag.isNew()) {
                session.save(discussionMessageFlag);

                discussionMessageFlag.setNew(false);
            } else {
                session.merge(discussionMessageFlag);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !DiscussionMessageFlagModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((discussionMessageFlagModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        discussionMessageFlagModelImpl.getOriginalMessageId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID,
                    args);

                args = new Object[] {
                        discussionMessageFlagModelImpl.getMessageId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageFlagImpl.class,
            discussionMessageFlag.getPrimaryKey(), discussionMessageFlag);

        clearUniqueFindersCache(discussionMessageFlag);
        cacheUniqueFindersCache(discussionMessageFlag);

        return discussionMessageFlag;
    }

    protected DiscussionMessageFlag toUnwrappedModel(
        DiscussionMessageFlag discussionMessageFlag) {
        if (discussionMessageFlag instanceof DiscussionMessageFlagImpl) {
            return discussionMessageFlag;
        }

        DiscussionMessageFlagImpl discussionMessageFlagImpl = new DiscussionMessageFlagImpl();

        discussionMessageFlagImpl.setNew(discussionMessageFlag.isNew());
        discussionMessageFlagImpl.setPrimaryKey(discussionMessageFlag.getPrimaryKey());

        discussionMessageFlagImpl.setPk(discussionMessageFlag.getPk());
        discussionMessageFlagImpl.setMessageId(discussionMessageFlag.getMessageId());
        discussionMessageFlagImpl.setFlagType(discussionMessageFlag.getFlagType());
        discussionMessageFlagImpl.setData(discussionMessageFlag.getData());
        discussionMessageFlagImpl.setCreated(discussionMessageFlag.getCreated());
        discussionMessageFlagImpl.setUserId(discussionMessageFlag.getUserId());

        return discussionMessageFlagImpl;
    }

    /**
     * Returns the discussion message flag with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the discussion message flag
     * @return the discussion message flag
     * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        DiscussionMessageFlag discussionMessageFlag = fetchByPrimaryKey(primaryKey);

        if (discussionMessageFlag == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDiscussionMessageFlagException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return discussionMessageFlag;
    }

    /**
     * Returns the discussion message flag with the primary key or throws a {@link com.ext.portlet.NoSuchDiscussionMessageFlagException} if it could not be found.
     *
     * @param pk the primary key of the discussion message flag
     * @return the discussion message flag
     * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag findByPrimaryKey(long pk)
        throws NoSuchDiscussionMessageFlagException, SystemException {
        return findByPrimaryKey((Serializable) pk);
    }

    /**
     * Returns the discussion message flag with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the discussion message flag
     * @return the discussion message flag, or <code>null</code> if a discussion message flag with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        DiscussionMessageFlag discussionMessageFlag = (DiscussionMessageFlag) EntityCacheUtil.getResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionMessageFlagImpl.class, primaryKey);

        if (discussionMessageFlag == _nullDiscussionMessageFlag) {
            return null;
        }

        if (discussionMessageFlag == null) {
            Session session = null;

            try {
                session = openSession();

                discussionMessageFlag = (DiscussionMessageFlag) session.get(DiscussionMessageFlagImpl.class,
                        primaryKey);

                if (discussionMessageFlag != null) {
                    cacheResult(discussionMessageFlag);
                } else {
                    EntityCacheUtil.putResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionMessageFlagImpl.class, primaryKey,
                        _nullDiscussionMessageFlag);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DiscussionMessageFlagModelImpl.ENTITY_CACHE_ENABLED,
                    DiscussionMessageFlagImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return discussionMessageFlag;
    }

    /**
     * Returns the discussion message flag with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param pk the primary key of the discussion message flag
     * @return the discussion message flag, or <code>null</code> if a discussion message flag with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessageFlag fetchByPrimaryKey(long pk)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) pk);
    }

    /**
     * Returns all the discussion message flags.
     *
     * @return the discussion message flags
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionMessageFlag> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the discussion message flags.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of discussion message flags
     * @param end the upper bound of the range of discussion message flags (not inclusive)
     * @return the range of discussion message flags
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionMessageFlag> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the discussion message flags.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of discussion message flags
     * @param end the upper bound of the range of discussion message flags (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of discussion message flags
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionMessageFlag> findAll(int start, int end,
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

        List<DiscussionMessageFlag> list = (List<DiscussionMessageFlag>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DISCUSSIONMESSAGEFLAG);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DISCUSSIONMESSAGEFLAG;

                if (pagination) {
                    sql = sql.concat(DiscussionMessageFlagModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<DiscussionMessageFlag>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DiscussionMessageFlag>(list);
                } else {
                    list = (List<DiscussionMessageFlag>) QueryUtil.list(q,
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
     * Removes all the discussion message flags from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (DiscussionMessageFlag discussionMessageFlag : findAll()) {
            remove(discussionMessageFlag);
        }
    }

    /**
     * Returns the number of discussion message flags.
     *
     * @return the number of discussion message flags
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

                Query q = session.createQuery(_SQL_COUNT_DISCUSSIONMESSAGEFLAG);

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
     * Initializes the discussion message flag persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.DiscussionMessageFlag")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DiscussionMessageFlag>> listenersList = new ArrayList<ModelListener<DiscussionMessageFlag>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DiscussionMessageFlag>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DiscussionMessageFlagImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
