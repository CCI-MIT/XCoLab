package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalReferenceException;
import com.ext.portlet.model.ProposalReference;
import com.ext.portlet.model.impl.ProposalReferenceImpl;
import com.ext.portlet.model.impl.ProposalReferenceModelImpl;
import com.ext.portlet.service.persistence.ProposalReferencePersistence;

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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the proposal reference service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalReferencePersistence
 * @see ProposalReferenceUtil
 * @generated
 */
public class ProposalReferencePersistenceImpl extends BasePersistenceImpl<ProposalReference>
    implements ProposalReferencePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalReferenceUtil} to access the proposal reference persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalReferenceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceModelImpl.FINDER_CACHE_ENABLED,
            ProposalReferenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceModelImpl.FINDER_CACHE_ENABLED,
            ProposalReferenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceModelImpl.FINDER_CACHE_ENABLED,
            ProposalReferenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProposalId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceModelImpl.FINDER_CACHE_ENABLED,
            ProposalReferenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProposalId",
            new String[] { Long.class.getName() },
            ProposalReferenceModelImpl.PROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID = new FinderPath(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProposalId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALID_2 = "proposalReference.id.proposalId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBPROPOSALID =
        new FinderPath(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceModelImpl.FINDER_CACHE_ENABLED,
            ProposalReferenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySubProposalId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPROPOSALID =
        new FinderPath(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceModelImpl.FINDER_CACHE_ENABLED,
            ProposalReferenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySubProposalId",
            new String[] { Long.class.getName() },
            ProposalReferenceModelImpl.SUBPROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SUBPROPOSALID = new FinderPath(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySubProposalId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_SUBPROPOSALID_SUBPROPOSALID_2 = "proposalReference.id.subProposalId = ?";
    private static final String _SQL_SELECT_PROPOSALREFERENCE = "SELECT proposalReference FROM ProposalReference proposalReference";
    private static final String _SQL_SELECT_PROPOSALREFERENCE_WHERE = "SELECT proposalReference FROM ProposalReference proposalReference WHERE ";
    private static final String _SQL_COUNT_PROPOSALREFERENCE = "SELECT COUNT(proposalReference) FROM ProposalReference proposalReference";
    private static final String _SQL_COUNT_PROPOSALREFERENCE_WHERE = "SELECT COUNT(proposalReference) FROM ProposalReference proposalReference WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalReference.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalReference exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProposalReference exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalReferencePersistenceImpl.class);
    private static ProposalReference _nullProposalReference = new ProposalReferenceImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalReference> toCacheModel() {
                return _nullProposalReferenceCacheModel;
            }
        };

    private static CacheModel<ProposalReference> _nullProposalReferenceCacheModel =
        new CacheModel<ProposalReference>() {
            @Override
            public ProposalReference toEntityModel() {
                return _nullProposalReference;
            }
        };

    public ProposalReferencePersistenceImpl() {
        setModelClass(ProposalReference.class);
    }

    /**
     * Returns all the proposal references where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the matching proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalReference> findByProposalId(long proposalId)
        throws SystemException {
        return findByProposalId(proposalId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal references where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of proposal references
     * @param end the upper bound of the range of proposal references (not inclusive)
     * @return the range of matching proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalReference> findByProposalId(long proposalId, int start,
        int end) throws SystemException {
        return findByProposalId(proposalId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal references where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of proposal references
     * @param end the upper bound of the range of proposal references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalReference> findByProposalId(long proposalId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID;
            finderArgs = new Object[] { proposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID;
            finderArgs = new Object[] { proposalId, start, end, orderByComparator };
        }

        List<ProposalReference> list = (List<ProposalReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalReference proposalReference : list) {
                if ((proposalId != proposalReference.getProposalId())) {
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

            query.append(_SQL_SELECT_PROPOSALREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalReferenceModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                if (!pagination) {
                    list = (List<ProposalReference>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalReference>(list);
                } else {
                    list = (List<ProposalReference>) QueryUtil.list(q,
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
     * Returns the first proposal reference in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal reference
     * @throws com.ext.portlet.NoSuchProposalReferenceException if a matching proposal reference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference findByProposalId_First(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalReferenceException, SystemException {
        ProposalReference proposalReference = fetchByProposalId_First(proposalId,
                orderByComparator);

        if (proposalReference != null) {
            return proposalReference;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalReferenceException(msg.toString());
    }

    /**
     * Returns the first proposal reference in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal reference, or <code>null</code> if a matching proposal reference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference fetchByProposalId_First(long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ProposalReference> list = findByProposalId(proposalId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal reference in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal reference
     * @throws com.ext.portlet.NoSuchProposalReferenceException if a matching proposal reference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference findByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalReferenceException, SystemException {
        ProposalReference proposalReference = fetchByProposalId_Last(proposalId,
                orderByComparator);

        if (proposalReference != null) {
            return proposalReference;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalReferenceException(msg.toString());
    }

    /**
     * Returns the last proposal reference in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal reference, or <code>null</code> if a matching proposal reference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference fetchByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByProposalId(proposalId);

        if (count == 0) {
            return null;
        }

        List<ProposalReference> list = findByProposalId(proposalId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal references before and after the current proposal reference in the ordered set where proposalId = &#63;.
     *
     * @param proposalReferencePK the primary key of the current proposal reference
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal reference
     * @throws com.ext.portlet.NoSuchProposalReferenceException if a proposal reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference[] findByProposalId_PrevAndNext(
        ProposalReferencePK proposalReferencePK, long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalReferenceException, SystemException {
        ProposalReference proposalReference = findByPrimaryKey(proposalReferencePK);

        Session session = null;

        try {
            session = openSession();

            ProposalReference[] array = new ProposalReferenceImpl[3];

            array[0] = getByProposalId_PrevAndNext(session, proposalReference,
                    proposalId, orderByComparator, true);

            array[1] = proposalReference;

            array[2] = getByProposalId_PrevAndNext(session, proposalReference,
                    proposalId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalReference getByProposalId_PrevAndNext(Session session,
        ProposalReference proposalReference, long proposalId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALREFERENCE_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

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
            query.append(ProposalReferenceModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalReference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalReference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal references where proposalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProposalId(long proposalId) throws SystemException {
        for (ProposalReference proposalReference : findByProposalId(
                proposalId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalReference);
        }
    }

    /**
     * Returns the number of proposal references where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the number of matching proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalId(long proposalId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALID;

        Object[] finderArgs = new Object[] { proposalId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

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
     * Returns all the proposal references where subProposalId = &#63;.
     *
     * @param subProposalId the sub proposal ID
     * @return the matching proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalReference> findBySubProposalId(long subProposalId)
        throws SystemException {
        return findBySubProposalId(subProposalId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal references where subProposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param subProposalId the sub proposal ID
     * @param start the lower bound of the range of proposal references
     * @param end the upper bound of the range of proposal references (not inclusive)
     * @return the range of matching proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalReference> findBySubProposalId(long subProposalId,
        int start, int end) throws SystemException {
        return findBySubProposalId(subProposalId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal references where subProposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param subProposalId the sub proposal ID
     * @param start the lower bound of the range of proposal references
     * @param end the upper bound of the range of proposal references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalReference> findBySubProposalId(long subProposalId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPROPOSALID;
            finderArgs = new Object[] { subProposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBPROPOSALID;
            finderArgs = new Object[] {
                    subProposalId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalReference> list = (List<ProposalReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalReference proposalReference : list) {
                if ((subProposalId != proposalReference.getSubProposalId())) {
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

            query.append(_SQL_SELECT_PROPOSALREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_SUBPROPOSALID_SUBPROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalReferenceModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(subProposalId);

                if (!pagination) {
                    list = (List<ProposalReference>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalReference>(list);
                } else {
                    list = (List<ProposalReference>) QueryUtil.list(q,
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
     * Returns the first proposal reference in the ordered set where subProposalId = &#63;.
     *
     * @param subProposalId the sub proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal reference
     * @throws com.ext.portlet.NoSuchProposalReferenceException if a matching proposal reference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference findBySubProposalId_First(long subProposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalReferenceException, SystemException {
        ProposalReference proposalReference = fetchBySubProposalId_First(subProposalId,
                orderByComparator);

        if (proposalReference != null) {
            return proposalReference;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("subProposalId=");
        msg.append(subProposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalReferenceException(msg.toString());
    }

    /**
     * Returns the first proposal reference in the ordered set where subProposalId = &#63;.
     *
     * @param subProposalId the sub proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal reference, or <code>null</code> if a matching proposal reference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference fetchBySubProposalId_First(long subProposalId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ProposalReference> list = findBySubProposalId(subProposalId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal reference in the ordered set where subProposalId = &#63;.
     *
     * @param subProposalId the sub proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal reference
     * @throws com.ext.portlet.NoSuchProposalReferenceException if a matching proposal reference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference findBySubProposalId_Last(long subProposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalReferenceException, SystemException {
        ProposalReference proposalReference = fetchBySubProposalId_Last(subProposalId,
                orderByComparator);

        if (proposalReference != null) {
            return proposalReference;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("subProposalId=");
        msg.append(subProposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalReferenceException(msg.toString());
    }

    /**
     * Returns the last proposal reference in the ordered set where subProposalId = &#63;.
     *
     * @param subProposalId the sub proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal reference, or <code>null</code> if a matching proposal reference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference fetchBySubProposalId_Last(long subProposalId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySubProposalId(subProposalId);

        if (count == 0) {
            return null;
        }

        List<ProposalReference> list = findBySubProposalId(subProposalId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal references before and after the current proposal reference in the ordered set where subProposalId = &#63;.
     *
     * @param proposalReferencePK the primary key of the current proposal reference
     * @param subProposalId the sub proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal reference
     * @throws com.ext.portlet.NoSuchProposalReferenceException if a proposal reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference[] findBySubProposalId_PrevAndNext(
        ProposalReferencePK proposalReferencePK, long subProposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalReferenceException, SystemException {
        ProposalReference proposalReference = findByPrimaryKey(proposalReferencePK);

        Session session = null;

        try {
            session = openSession();

            ProposalReference[] array = new ProposalReferenceImpl[3];

            array[0] = getBySubProposalId_PrevAndNext(session,
                    proposalReference, subProposalId, orderByComparator, true);

            array[1] = proposalReference;

            array[2] = getBySubProposalId_PrevAndNext(session,
                    proposalReference, subProposalId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalReference getBySubProposalId_PrevAndNext(
        Session session, ProposalReference proposalReference,
        long subProposalId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALREFERENCE_WHERE);

        query.append(_FINDER_COLUMN_SUBPROPOSALID_SUBPROPOSALID_2);

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
            query.append(ProposalReferenceModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(subProposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalReference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalReference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal references where subProposalId = &#63; from the database.
     *
     * @param subProposalId the sub proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySubProposalId(long subProposalId)
        throws SystemException {
        for (ProposalReference proposalReference : findBySubProposalId(
                subProposalId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalReference);
        }
    }

    /**
     * Returns the number of proposal references where subProposalId = &#63;.
     *
     * @param subProposalId the sub proposal ID
     * @return the number of matching proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySubProposalId(long subProposalId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SUBPROPOSALID;

        Object[] finderArgs = new Object[] { subProposalId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_SUBPROPOSALID_SUBPROPOSALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(subProposalId);

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
     * Caches the proposal reference in the entity cache if it is enabled.
     *
     * @param proposalReference the proposal reference
     */
    @Override
    public void cacheResult(ProposalReference proposalReference) {
        EntityCacheUtil.putResult(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceImpl.class, proposalReference.getPrimaryKey(),
            proposalReference);

        proposalReference.resetOriginalValues();
    }

    /**
     * Caches the proposal references in the entity cache if it is enabled.
     *
     * @param proposalReferences the proposal references
     */
    @Override
    public void cacheResult(List<ProposalReference> proposalReferences) {
        for (ProposalReference proposalReference : proposalReferences) {
            if (EntityCacheUtil.getResult(
                        ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalReferenceImpl.class,
                        proposalReference.getPrimaryKey()) == null) {
                cacheResult(proposalReference);
            } else {
                proposalReference.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal references.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalReferenceImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalReferenceImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal reference.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProposalReference proposalReference) {
        EntityCacheUtil.removeResult(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceImpl.class, proposalReference.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProposalReference> proposalReferences) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalReference proposalReference : proposalReferences) {
            EntityCacheUtil.removeResult(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
                ProposalReferenceImpl.class, proposalReference.getPrimaryKey());
        }
    }

    /**
     * Creates a new proposal reference with the primary key. Does not add the proposal reference to the database.
     *
     * @param proposalReferencePK the primary key for the new proposal reference
     * @return the new proposal reference
     */
    @Override
    public ProposalReference create(ProposalReferencePK proposalReferencePK) {
        ProposalReference proposalReference = new ProposalReferenceImpl();

        proposalReference.setNew(true);
        proposalReference.setPrimaryKey(proposalReferencePK);

        return proposalReference;
    }

    /**
     * Removes the proposal reference with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param proposalReferencePK the primary key of the proposal reference
     * @return the proposal reference that was removed
     * @throws com.ext.portlet.NoSuchProposalReferenceException if a proposal reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference remove(ProposalReferencePK proposalReferencePK)
        throws NoSuchProposalReferenceException, SystemException {
        return remove((Serializable) proposalReferencePK);
    }

    /**
     * Removes the proposal reference with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal reference
     * @return the proposal reference that was removed
     * @throws com.ext.portlet.NoSuchProposalReferenceException if a proposal reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference remove(Serializable primaryKey)
        throws NoSuchProposalReferenceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalReference proposalReference = (ProposalReference) session.get(ProposalReferenceImpl.class,
                    primaryKey);

            if (proposalReference == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalReferenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalReference);
        } catch (NoSuchProposalReferenceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalReference removeImpl(ProposalReference proposalReference)
        throws SystemException {
        proposalReference = toUnwrappedModel(proposalReference);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposalReference)) {
                proposalReference = (ProposalReference) session.get(ProposalReferenceImpl.class,
                        proposalReference.getPrimaryKeyObj());
            }

            if (proposalReference != null) {
                session.delete(proposalReference);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposalReference != null) {
            clearCache(proposalReference);
        }

        return proposalReference;
    }

    @Override
    public ProposalReference updateImpl(
        com.ext.portlet.model.ProposalReference proposalReference)
        throws SystemException {
        proposalReference = toUnwrappedModel(proposalReference);

        boolean isNew = proposalReference.isNew();

        ProposalReferenceModelImpl proposalReferenceModelImpl = (ProposalReferenceModelImpl) proposalReference;

        Session session = null;

        try {
            session = openSession();

            if (proposalReference.isNew()) {
                session.save(proposalReference);

                proposalReference.setNew(false);
            } else {
                session.merge(proposalReference);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ProposalReferenceModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((proposalReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalReferenceModelImpl.getOriginalProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);

                args = new Object[] { proposalReferenceModelImpl.getProposalId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);
            }

            if ((proposalReferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalReferenceModelImpl.getOriginalSubProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUBPROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPROPOSALID,
                    args);

                args = new Object[] {
                        proposalReferenceModelImpl.getSubProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUBPROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBPROPOSALID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
            ProposalReferenceImpl.class, proposalReference.getPrimaryKey(),
            proposalReference);

        return proposalReference;
    }

    protected ProposalReference toUnwrappedModel(
        ProposalReference proposalReference) {
        if (proposalReference instanceof ProposalReferenceImpl) {
            return proposalReference;
        }

        ProposalReferenceImpl proposalReferenceImpl = new ProposalReferenceImpl();

        proposalReferenceImpl.setNew(proposalReference.isNew());
        proposalReferenceImpl.setPrimaryKey(proposalReference.getPrimaryKey());

        proposalReferenceImpl.setProposalId(proposalReference.getProposalId());
        proposalReferenceImpl.setSubProposalId(proposalReference.getSubProposalId());
        proposalReferenceImpl.setSectionAttributeId(proposalReference.getSectionAttributeId());

        return proposalReferenceImpl;
    }

    /**
     * Returns the proposal reference with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal reference
     * @return the proposal reference
     * @throws com.ext.portlet.NoSuchProposalReferenceException if a proposal reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProposalReferenceException, SystemException {
        ProposalReference proposalReference = fetchByPrimaryKey(primaryKey);

        if (proposalReference == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalReferenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposalReference;
    }

    /**
     * Returns the proposal reference with the primary key or throws a {@link com.ext.portlet.NoSuchProposalReferenceException} if it could not be found.
     *
     * @param proposalReferencePK the primary key of the proposal reference
     * @return the proposal reference
     * @throws com.ext.portlet.NoSuchProposalReferenceException if a proposal reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference findByPrimaryKey(
        ProposalReferencePK proposalReferencePK)
        throws NoSuchProposalReferenceException, SystemException {
        return findByPrimaryKey((Serializable) proposalReferencePK);
    }

    /**
     * Returns the proposal reference with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal reference
     * @return the proposal reference, or <code>null</code> if a proposal reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProposalReference proposalReference = (ProposalReference) EntityCacheUtil.getResult(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
                ProposalReferenceImpl.class, primaryKey);

        if (proposalReference == _nullProposalReference) {
            return null;
        }

        if (proposalReference == null) {
            Session session = null;

            try {
                session = openSession();

                proposalReference = (ProposalReference) session.get(ProposalReferenceImpl.class,
                        primaryKey);

                if (proposalReference != null) {
                    cacheResult(proposalReference);
                } else {
                    EntityCacheUtil.putResult(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalReferenceImpl.class, primaryKey,
                        _nullProposalReference);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalReferenceModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalReferenceImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposalReference;
    }

    /**
     * Returns the proposal reference with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param proposalReferencePK the primary key of the proposal reference
     * @return the proposal reference, or <code>null</code> if a proposal reference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalReference fetchByPrimaryKey(
        ProposalReferencePK proposalReferencePK) throws SystemException {
        return fetchByPrimaryKey((Serializable) proposalReferencePK);
    }

    /**
     * Returns all the proposal references.
     *
     * @return the proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalReference> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal references.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal references
     * @param end the upper bound of the range of proposal references (not inclusive)
     * @return the range of proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalReference> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal references.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal references
     * @param end the upper bound of the range of proposal references (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal references
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalReference> findAll(int start, int end,
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

        List<ProposalReference> list = (List<ProposalReference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALREFERENCE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALREFERENCE;

                if (pagination) {
                    sql = sql.concat(ProposalReferenceModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProposalReference>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalReference>(list);
                } else {
                    list = (List<ProposalReference>) QueryUtil.list(q,
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
     * Removes all the proposal references from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProposalReference proposalReference : findAll()) {
            remove(proposalReference);
        }
    }

    /**
     * Returns the number of proposal references.
     *
     * @return the number of proposal references
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSALREFERENCE);

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
     * Initializes the proposal reference persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalReference")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalReference>> listenersList = new ArrayList<ModelListener<ProposalReference>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalReference>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalReferenceImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
