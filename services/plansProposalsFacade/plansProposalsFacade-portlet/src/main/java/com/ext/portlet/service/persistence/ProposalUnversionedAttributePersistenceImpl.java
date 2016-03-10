package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalUnversionedAttributeException;
import com.ext.portlet.model.ProposalUnversionedAttribute;
import com.ext.portlet.model.impl.ProposalUnversionedAttributeImpl;
import com.ext.portlet.model.impl.ProposalUnversionedAttributeModelImpl;
import com.ext.portlet.service.persistence.ProposalUnversionedAttributePersistence;

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
 * The persistence implementation for the proposal unversioned attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalUnversionedAttributePersistence
 * @see ProposalUnversionedAttributeUtil
 * @generated
 */
public class ProposalUnversionedAttributePersistenceImpl
    extends BasePersistenceImpl<ProposalUnversionedAttribute>
    implements ProposalUnversionedAttributePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalUnversionedAttributeUtil} to access the proposal unversioned attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalUnversionedAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalUnversionedAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalUnversionedAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE =
        new FinderPath(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalUnversionedAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByProposalId_ProposalUnversionedAttribute",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE =
        new FinderPath(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalUnversionedAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByProposalId_ProposalUnversionedAttribute",
            new String[] { Long.class.getName() },
            ProposalUnversionedAttributeModelImpl.PROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE =
        new FinderPath(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalId_ProposalUnversionedAttribute",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE_PROPOSALID_2 =
        "proposalUnversionedAttribute.proposalId = ?";
    public static final FinderPath FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME =
        new FinderPath(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalUnversionedAttributeImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByProposalId_ProposalUnversionedAttributeName",
            new String[] { Long.class.getName(), String.class.getName() },
            ProposalUnversionedAttributeModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalUnversionedAttributeModelImpl.NAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME =
        new FinderPath(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalId_ProposalUnversionedAttributeName",
            new String[] { Long.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_PROPOSALID_2 =
        "proposalUnversionedAttribute.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_NAME_1 =
        "proposalUnversionedAttribute.name IS NULL";
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_NAME_2 =
        "proposalUnversionedAttribute.name = ?";
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_NAME_3 =
        "(proposalUnversionedAttribute.name IS NULL OR proposalUnversionedAttribute.name = '')";
    private static final String _SQL_SELECT_PROPOSALUNVERSIONEDATTRIBUTE = "SELECT proposalUnversionedAttribute FROM ProposalUnversionedAttribute proposalUnversionedAttribute";
    private static final String _SQL_SELECT_PROPOSALUNVERSIONEDATTRIBUTE_WHERE = "SELECT proposalUnversionedAttribute FROM ProposalUnversionedAttribute proposalUnversionedAttribute WHERE ";
    private static final String _SQL_COUNT_PROPOSALUNVERSIONEDATTRIBUTE = "SELECT COUNT(proposalUnversionedAttribute) FROM ProposalUnversionedAttribute proposalUnversionedAttribute";
    private static final String _SQL_COUNT_PROPOSALUNVERSIONEDATTRIBUTE_WHERE = "SELECT COUNT(proposalUnversionedAttribute) FROM ProposalUnversionedAttribute proposalUnversionedAttribute WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalUnversionedAttribute.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalUnversionedAttribute exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProposalUnversionedAttribute exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalUnversionedAttributePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ProposalUnversionedAttribute _nullProposalUnversionedAttribute =
        new ProposalUnversionedAttributeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalUnversionedAttribute> toCacheModel() {
                return _nullProposalUnversionedAttributeCacheModel;
            }
        };

    private static CacheModel<ProposalUnversionedAttribute> _nullProposalUnversionedAttributeCacheModel =
        new CacheModel<ProposalUnversionedAttribute>() {
            @Override
            public ProposalUnversionedAttribute toEntityModel() {
                return _nullProposalUnversionedAttribute;
            }
        };

    public ProposalUnversionedAttributePersistenceImpl() {
        setModelClass(ProposalUnversionedAttribute.class);
    }

    /**
     * Returns all the proposal unversioned attributes where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the matching proposal unversioned attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalUnversionedAttribute> findByProposalId_ProposalUnversionedAttribute(
        long proposalId) throws SystemException {
        return findByProposalId_ProposalUnversionedAttribute(proposalId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal unversioned attributes where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalUnversionedAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of proposal unversioned attributes
     * @param end the upper bound of the range of proposal unversioned attributes (not inclusive)
     * @return the range of matching proposal unversioned attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalUnversionedAttribute> findByProposalId_ProposalUnversionedAttribute(
        long proposalId, int start, int end) throws SystemException {
        return findByProposalId_ProposalUnversionedAttribute(proposalId, start,
            end, null);
    }

    /**
     * Returns an ordered range of all the proposal unversioned attributes where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalUnversionedAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of proposal unversioned attributes
     * @param end the upper bound of the range of proposal unversioned attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal unversioned attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalUnversionedAttribute> findByProposalId_ProposalUnversionedAttribute(
        long proposalId, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE;
            finderArgs = new Object[] { proposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE;
            finderArgs = new Object[] { proposalId, start, end, orderByComparator };
        }

        List<ProposalUnversionedAttribute> list = (List<ProposalUnversionedAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalUnversionedAttribute proposalUnversionedAttribute : list) {
                if ((proposalId != proposalUnversionedAttribute.getProposalId())) {
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

            query.append(_SQL_SELECT_PROPOSALUNVERSIONEDATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE_PROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalUnversionedAttributeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                if (!pagination) {
                    list = (List<ProposalUnversionedAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalUnversionedAttribute>(list);
                } else {
                    list = (List<ProposalUnversionedAttribute>) QueryUtil.list(q,
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
     * Returns the first proposal unversioned attribute in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal unversioned attribute
     * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a matching proposal unversioned attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute findByProposalId_ProposalUnversionedAttribute_First(
        long proposalId, OrderByComparator orderByComparator)
        throws NoSuchProposalUnversionedAttributeException, SystemException {
        ProposalUnversionedAttribute proposalUnversionedAttribute = fetchByProposalId_ProposalUnversionedAttribute_First(proposalId,
                orderByComparator);

        if (proposalUnversionedAttribute != null) {
            return proposalUnversionedAttribute;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalUnversionedAttributeException(msg.toString());
    }

    /**
     * Returns the first proposal unversioned attribute in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal unversioned attribute, or <code>null</code> if a matching proposal unversioned attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute fetchByProposalId_ProposalUnversionedAttribute_First(
        long proposalId, OrderByComparator orderByComparator)
        throws SystemException {
        List<ProposalUnversionedAttribute> list = findByProposalId_ProposalUnversionedAttribute(proposalId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal unversioned attribute in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal unversioned attribute
     * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a matching proposal unversioned attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute findByProposalId_ProposalUnversionedAttribute_Last(
        long proposalId, OrderByComparator orderByComparator)
        throws NoSuchProposalUnversionedAttributeException, SystemException {
        ProposalUnversionedAttribute proposalUnversionedAttribute = fetchByProposalId_ProposalUnversionedAttribute_Last(proposalId,
                orderByComparator);

        if (proposalUnversionedAttribute != null) {
            return proposalUnversionedAttribute;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalUnversionedAttributeException(msg.toString());
    }

    /**
     * Returns the last proposal unversioned attribute in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal unversioned attribute, or <code>null</code> if a matching proposal unversioned attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute fetchByProposalId_ProposalUnversionedAttribute_Last(
        long proposalId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByProposalId_ProposalUnversionedAttribute(proposalId);

        if (count == 0) {
            return null;
        }

        List<ProposalUnversionedAttribute> list = findByProposalId_ProposalUnversionedAttribute(proposalId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal unversioned attributes before and after the current proposal unversioned attribute in the ordered set where proposalId = &#63;.
     *
     * @param id the primary key of the current proposal unversioned attribute
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal unversioned attribute
     * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a proposal unversioned attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute[] findByProposalId_ProposalUnversionedAttribute_PrevAndNext(
        long id, long proposalId, OrderByComparator orderByComparator)
        throws NoSuchProposalUnversionedAttributeException, SystemException {
        ProposalUnversionedAttribute proposalUnversionedAttribute = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ProposalUnversionedAttribute[] array = new ProposalUnversionedAttributeImpl[3];

            array[0] = getByProposalId_ProposalUnversionedAttribute_PrevAndNext(session,
                    proposalUnversionedAttribute, proposalId,
                    orderByComparator, true);

            array[1] = proposalUnversionedAttribute;

            array[2] = getByProposalId_ProposalUnversionedAttribute_PrevAndNext(session,
                    proposalUnversionedAttribute, proposalId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalUnversionedAttribute getByProposalId_ProposalUnversionedAttribute_PrevAndNext(
        Session session,
        ProposalUnversionedAttribute proposalUnversionedAttribute,
        long proposalId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALUNVERSIONEDATTRIBUTE_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE_PROPOSALID_2);

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
            query.append(ProposalUnversionedAttributeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalUnversionedAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalUnversionedAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal unversioned attributes where proposalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProposalId_ProposalUnversionedAttribute(long proposalId)
        throws SystemException {
        for (ProposalUnversionedAttribute proposalUnversionedAttribute : findByProposalId_ProposalUnversionedAttribute(
                proposalId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalUnversionedAttribute);
        }
    }

    /**
     * Returns the number of proposal unversioned attributes where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the number of matching proposal unversioned attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalId_ProposalUnversionedAttribute(long proposalId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE;

        Object[] finderArgs = new Object[] { proposalId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALUNVERSIONEDATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE_PROPOSALID_2);

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
     * Returns the proposal unversioned attribute where proposalId = &#63; and name = &#63; or throws a {@link com.ext.portlet.NoSuchProposalUnversionedAttributeException} if it could not be found.
     *
     * @param proposalId the proposal ID
     * @param name the name
     * @return the matching proposal unversioned attribute
     * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a matching proposal unversioned attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute findByProposalId_ProposalUnversionedAttributeName(
        long proposalId, String name)
        throws NoSuchProposalUnversionedAttributeException, SystemException {
        ProposalUnversionedAttribute proposalUnversionedAttribute = fetchByProposalId_ProposalUnversionedAttributeName(proposalId,
                name);

        if (proposalUnversionedAttribute == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", name=");
            msg.append(name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchProposalUnversionedAttributeException(msg.toString());
        }

        return proposalUnversionedAttribute;
    }

    /**
     * Returns the proposal unversioned attribute where proposalId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param proposalId the proposal ID
     * @param name the name
     * @return the matching proposal unversioned attribute, or <code>null</code> if a matching proposal unversioned attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute fetchByProposalId_ProposalUnversionedAttributeName(
        long proposalId, String name) throws SystemException {
        return fetchByProposalId_ProposalUnversionedAttributeName(proposalId,
            name, true);
    }

    /**
     * Returns the proposal unversioned attribute where proposalId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param proposalId the proposal ID
     * @param name the name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching proposal unversioned attribute, or <code>null</code> if a matching proposal unversioned attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute fetchByProposalId_ProposalUnversionedAttributeName(
        long proposalId, String name, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { proposalId, name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
                    finderArgs, this);
        }

        if (result instanceof ProposalUnversionedAttribute) {
            ProposalUnversionedAttribute proposalUnversionedAttribute = (ProposalUnversionedAttribute) result;

            if ((proposalId != proposalUnversionedAttribute.getProposalId()) ||
                    !Validator.equals(name,
                        proposalUnversionedAttribute.getName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PROPOSALUNVERSIONEDATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_PROPOSALID_2);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_NAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                if (bindName) {
                    qPos.add(name);
                }

                List<ProposalUnversionedAttribute> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ProposalUnversionedAttributePersistenceImpl.fetchByProposalId_ProposalUnversionedAttributeName(long, String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ProposalUnversionedAttribute proposalUnversionedAttribute = list.get(0);

                    result = proposalUnversionedAttribute;

                    cacheResult(proposalUnversionedAttribute);

                    if ((proposalUnversionedAttribute.getProposalId() != proposalId) ||
                            (proposalUnversionedAttribute.getName() == null) ||
                            !proposalUnversionedAttribute.getName().equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
                            finderArgs, proposalUnversionedAttribute);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ProposalUnversionedAttribute) result;
        }
    }

    /**
     * Removes the proposal unversioned attribute where proposalId = &#63; and name = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param name the name
     * @return the proposal unversioned attribute that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute removeByProposalId_ProposalUnversionedAttributeName(
        long proposalId, String name)
        throws NoSuchProposalUnversionedAttributeException, SystemException {
        ProposalUnversionedAttribute proposalUnversionedAttribute = findByProposalId_ProposalUnversionedAttributeName(proposalId,
                name);

        return remove(proposalUnversionedAttribute);
    }

    /**
     * Returns the number of proposal unversioned attributes where proposalId = &#63; and name = &#63;.
     *
     * @param proposalId the proposal ID
     * @param name the name
     * @return the number of matching proposal unversioned attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalId_ProposalUnversionedAttributeName(
        long proposalId, String name) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME;

        Object[] finderArgs = new Object[] { proposalId, name };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALUNVERSIONEDATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_PROPOSALID_2);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME_NAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

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
     * Caches the proposal unversioned attribute in the entity cache if it is enabled.
     *
     * @param proposalUnversionedAttribute the proposal unversioned attribute
     */
    @Override
    public void cacheResult(
        ProposalUnversionedAttribute proposalUnversionedAttribute) {
        EntityCacheUtil.putResult(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeImpl.class,
            proposalUnversionedAttribute.getPrimaryKey(),
            proposalUnversionedAttribute);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
            new Object[] {
                proposalUnversionedAttribute.getProposalId(),
                proposalUnversionedAttribute.getName()
            }, proposalUnversionedAttribute);

        proposalUnversionedAttribute.resetOriginalValues();
    }

    /**
     * Caches the proposal unversioned attributes in the entity cache if it is enabled.
     *
     * @param proposalUnversionedAttributes the proposal unversioned attributes
     */
    @Override
    public void cacheResult(
        List<ProposalUnversionedAttribute> proposalUnversionedAttributes) {
        for (ProposalUnversionedAttribute proposalUnversionedAttribute : proposalUnversionedAttributes) {
            if (EntityCacheUtil.getResult(
                        ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalUnversionedAttributeImpl.class,
                        proposalUnversionedAttribute.getPrimaryKey()) == null) {
                cacheResult(proposalUnversionedAttribute);
            } else {
                proposalUnversionedAttribute.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal unversioned attributes.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalUnversionedAttributeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalUnversionedAttributeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal unversioned attribute.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        ProposalUnversionedAttribute proposalUnversionedAttribute) {
        EntityCacheUtil.removeResult(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeImpl.class,
            proposalUnversionedAttribute.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(proposalUnversionedAttribute);
    }

    @Override
    public void clearCache(
        List<ProposalUnversionedAttribute> proposalUnversionedAttributes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalUnversionedAttribute proposalUnversionedAttribute : proposalUnversionedAttributes) {
            EntityCacheUtil.removeResult(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalUnversionedAttributeImpl.class,
                proposalUnversionedAttribute.getPrimaryKey());

            clearUniqueFindersCache(proposalUnversionedAttribute);
        }
    }

    protected void cacheUniqueFindersCache(
        ProposalUnversionedAttribute proposalUnversionedAttribute) {
        if (proposalUnversionedAttribute.isNew()) {
            Object[] args = new Object[] {
                    proposalUnversionedAttribute.getProposalId(),
                    proposalUnversionedAttribute.getName()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
                args, proposalUnversionedAttribute);
        } else {
            ProposalUnversionedAttributeModelImpl proposalUnversionedAttributeModelImpl =
                (ProposalUnversionedAttributeModelImpl) proposalUnversionedAttribute;

            if ((proposalUnversionedAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalUnversionedAttribute.getProposalId(),
                        proposalUnversionedAttribute.getName()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
                    args, proposalUnversionedAttribute);
            }
        }
    }

    protected void clearUniqueFindersCache(
        ProposalUnversionedAttribute proposalUnversionedAttribute) {
        ProposalUnversionedAttributeModelImpl proposalUnversionedAttributeModelImpl =
            (ProposalUnversionedAttributeModelImpl) proposalUnversionedAttribute;

        Object[] args = new Object[] {
                proposalUnversionedAttribute.getProposalId(),
                proposalUnversionedAttribute.getName()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
            args);

        if ((proposalUnversionedAttributeModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME.getColumnBitmask()) != 0) {
            args = new Object[] {
                    proposalUnversionedAttributeModelImpl.getOriginalProposalId(),
                    proposalUnversionedAttributeModelImpl.getOriginalName()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTENAME,
                args);
        }
    }

    /**
     * Creates a new proposal unversioned attribute with the primary key. Does not add the proposal unversioned attribute to the database.
     *
     * @param id the primary key for the new proposal unversioned attribute
     * @return the new proposal unversioned attribute
     */
    @Override
    public ProposalUnversionedAttribute create(long id) {
        ProposalUnversionedAttribute proposalUnversionedAttribute = new ProposalUnversionedAttributeImpl();

        proposalUnversionedAttribute.setNew(true);
        proposalUnversionedAttribute.setPrimaryKey(id);

        return proposalUnversionedAttribute;
    }

    /**
     * Removes the proposal unversioned attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the proposal unversioned attribute
     * @return the proposal unversioned attribute that was removed
     * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a proposal unversioned attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute remove(long id)
        throws NoSuchProposalUnversionedAttributeException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the proposal unversioned attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal unversioned attribute
     * @return the proposal unversioned attribute that was removed
     * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a proposal unversioned attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute remove(Serializable primaryKey)
        throws NoSuchProposalUnversionedAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalUnversionedAttribute proposalUnversionedAttribute = (ProposalUnversionedAttribute) session.get(ProposalUnversionedAttributeImpl.class,
                    primaryKey);

            if (proposalUnversionedAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalUnversionedAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalUnversionedAttribute);
        } catch (NoSuchProposalUnversionedAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalUnversionedAttribute removeImpl(
        ProposalUnversionedAttribute proposalUnversionedAttribute)
        throws SystemException {
        proposalUnversionedAttribute = toUnwrappedModel(proposalUnversionedAttribute);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposalUnversionedAttribute)) {
                proposalUnversionedAttribute = (ProposalUnversionedAttribute) session.get(ProposalUnversionedAttributeImpl.class,
                        proposalUnversionedAttribute.getPrimaryKeyObj());
            }

            if (proposalUnversionedAttribute != null) {
                session.delete(proposalUnversionedAttribute);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposalUnversionedAttribute != null) {
            clearCache(proposalUnversionedAttribute);
        }

        return proposalUnversionedAttribute;
    }

    @Override
    public ProposalUnversionedAttribute updateImpl(
        com.ext.portlet.model.ProposalUnversionedAttribute proposalUnversionedAttribute)
        throws SystemException {
        proposalUnversionedAttribute = toUnwrappedModel(proposalUnversionedAttribute);

        boolean isNew = proposalUnversionedAttribute.isNew();

        ProposalUnversionedAttributeModelImpl proposalUnversionedAttributeModelImpl =
            (ProposalUnversionedAttributeModelImpl) proposalUnversionedAttribute;

        Session session = null;

        try {
            session = openSession();

            if (proposalUnversionedAttribute.isNew()) {
                session.save(proposalUnversionedAttribute);

                proposalUnversionedAttribute.setNew(false);
            } else {
                session.merge(proposalUnversionedAttribute);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew ||
                !ProposalUnversionedAttributeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((proposalUnversionedAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalUnversionedAttributeModelImpl.getOriginalProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE,
                    args);

                args = new Object[] {
                        proposalUnversionedAttributeModelImpl.getProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID_PROPOSALUNVERSIONEDATTRIBUTE,
                    args);
            }
        }

        EntityCacheUtil.putResult(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalUnversionedAttributeImpl.class,
            proposalUnversionedAttribute.getPrimaryKey(),
            proposalUnversionedAttribute);

        clearUniqueFindersCache(proposalUnversionedAttribute);
        cacheUniqueFindersCache(proposalUnversionedAttribute);

        return proposalUnversionedAttribute;
    }

    protected ProposalUnversionedAttribute toUnwrappedModel(
        ProposalUnversionedAttribute proposalUnversionedAttribute) {
        if (proposalUnversionedAttribute instanceof ProposalUnversionedAttributeImpl) {
            return proposalUnversionedAttribute;
        }

        ProposalUnversionedAttributeImpl proposalUnversionedAttributeImpl = new ProposalUnversionedAttributeImpl();

        proposalUnversionedAttributeImpl.setNew(proposalUnversionedAttribute.isNew());
        proposalUnversionedAttributeImpl.setPrimaryKey(proposalUnversionedAttribute.getPrimaryKey());

        proposalUnversionedAttributeImpl.setId(proposalUnversionedAttribute.getId());
        proposalUnversionedAttributeImpl.setProposalId(proposalUnversionedAttribute.getProposalId());
        proposalUnversionedAttributeImpl.setCreateAuthorId(proposalUnversionedAttribute.getCreateAuthorId());
        proposalUnversionedAttributeImpl.setLastAuthorId(proposalUnversionedAttribute.getLastAuthorId());
        proposalUnversionedAttributeImpl.setCreateDate(proposalUnversionedAttribute.getCreateDate());
        proposalUnversionedAttributeImpl.setLastUpdateDate(proposalUnversionedAttribute.getLastUpdateDate());
        proposalUnversionedAttributeImpl.setName(proposalUnversionedAttribute.getName());
        proposalUnversionedAttributeImpl.setAddtionalId(proposalUnversionedAttribute.getAddtionalId());
        proposalUnversionedAttributeImpl.setNumericValue(proposalUnversionedAttribute.getNumericValue());
        proposalUnversionedAttributeImpl.setStringValue(proposalUnversionedAttribute.getStringValue());
        proposalUnversionedAttributeImpl.setRealValue(proposalUnversionedAttribute.getRealValue());

        return proposalUnversionedAttributeImpl;
    }

    /**
     * Returns the proposal unversioned attribute with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal unversioned attribute
     * @return the proposal unversioned attribute
     * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a proposal unversioned attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute findByPrimaryKey(
        Serializable primaryKey)
        throws NoSuchProposalUnversionedAttributeException, SystemException {
        ProposalUnversionedAttribute proposalUnversionedAttribute = fetchByPrimaryKey(primaryKey);

        if (proposalUnversionedAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalUnversionedAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposalUnversionedAttribute;
    }

    /**
     * Returns the proposal unversioned attribute with the primary key or throws a {@link com.ext.portlet.NoSuchProposalUnversionedAttributeException} if it could not be found.
     *
     * @param id the primary key of the proposal unversioned attribute
     * @return the proposal unversioned attribute
     * @throws com.ext.portlet.NoSuchProposalUnversionedAttributeException if a proposal unversioned attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute findByPrimaryKey(long id)
        throws NoSuchProposalUnversionedAttributeException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the proposal unversioned attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal unversioned attribute
     * @return the proposal unversioned attribute, or <code>null</code> if a proposal unversioned attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        ProposalUnversionedAttribute proposalUnversionedAttribute = (ProposalUnversionedAttribute) EntityCacheUtil.getResult(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalUnversionedAttributeImpl.class, primaryKey);

        if (proposalUnversionedAttribute == _nullProposalUnversionedAttribute) {
            return null;
        }

        if (proposalUnversionedAttribute == null) {
            Session session = null;

            try {
                session = openSession();

                proposalUnversionedAttribute = (ProposalUnversionedAttribute) session.get(ProposalUnversionedAttributeImpl.class,
                        primaryKey);

                if (proposalUnversionedAttribute != null) {
                    cacheResult(proposalUnversionedAttribute);
                } else {
                    EntityCacheUtil.putResult(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalUnversionedAttributeImpl.class, primaryKey,
                        _nullProposalUnversionedAttribute);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalUnversionedAttributeModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalUnversionedAttributeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposalUnversionedAttribute;
    }

    /**
     * Returns the proposal unversioned attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the proposal unversioned attribute
     * @return the proposal unversioned attribute, or <code>null</code> if a proposal unversioned attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalUnversionedAttribute fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the proposal unversioned attributes.
     *
     * @return the proposal unversioned attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalUnversionedAttribute> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal unversioned attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalUnversionedAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal unversioned attributes
     * @param end the upper bound of the range of proposal unversioned attributes (not inclusive)
     * @return the range of proposal unversioned attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalUnversionedAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal unversioned attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalUnversionedAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal unversioned attributes
     * @param end the upper bound of the range of proposal unversioned attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal unversioned attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalUnversionedAttribute> findAll(int start, int end,
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

        List<ProposalUnversionedAttribute> list = (List<ProposalUnversionedAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALUNVERSIONEDATTRIBUTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALUNVERSIONEDATTRIBUTE;

                if (pagination) {
                    sql = sql.concat(ProposalUnversionedAttributeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProposalUnversionedAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalUnversionedAttribute>(list);
                } else {
                    list = (List<ProposalUnversionedAttribute>) QueryUtil.list(q,
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
     * Removes all the proposal unversioned attributes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProposalUnversionedAttribute proposalUnversionedAttribute : findAll()) {
            remove(proposalUnversionedAttribute);
        }
    }

    /**
     * Returns the number of proposal unversioned attributes.
     *
     * @return the number of proposal unversioned attributes
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSALUNVERSIONEDATTRIBUTE);

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
     * Initializes the proposal unversioned attribute persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalUnversionedAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalUnversionedAttribute>> listenersList = new ArrayList<ModelListener<ProposalUnversionedAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalUnversionedAttribute>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalUnversionedAttributeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
