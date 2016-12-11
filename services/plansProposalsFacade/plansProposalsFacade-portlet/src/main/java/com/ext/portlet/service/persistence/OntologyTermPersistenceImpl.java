package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchOntologyTermException;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.impl.OntologyTermImpl;
import com.ext.portlet.model.impl.OntologyTermModelImpl;
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
 * The persistence implementation for the ontology term service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermPersistence
 * @see OntologyTermUtil
 * @generated
 */
public class OntologyTermPersistenceImpl extends BasePersistenceImpl<OntologyTerm>
    implements OntologyTermPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link OntologyTermUtil} to access the ontology term persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = OntologyTermImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, OntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, OntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTID = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, OntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByParentId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTID =
        new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, OntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByParentId",
            new String[] { Long.class.getName() },
            OntologyTermModelImpl.PARENTID_COLUMN_BITMASK |
            OntologyTermModelImpl.ORDER__COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PARENTID = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByParentId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PARENTID_PARENTID_2 = "ontologyTerm.parentId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTIDSPACEID =
        new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, OntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByParentIdSpaceId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTIDSPACEID =
        new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, OntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByParentIdSpaceId",
            new String[] { Long.class.getName(), Long.class.getName() },
            OntologyTermModelImpl.PARENTID_COLUMN_BITMASK |
            OntologyTermModelImpl.ONTOLOGYSPACEID_COLUMN_BITMASK |
            OntologyTermModelImpl.ORDER__COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PARENTIDSPACEID = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByParentIdSpaceId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_PARENTIDSPACEID_PARENTID_2 = "ontologyTerm.parentId = ? AND ";
    private static final String _FINDER_COLUMN_PARENTIDSPACEID_ONTOLOGYSPACEID_2 =
        "ontologyTerm.ontologySpaceId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SPACEID = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, OntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySpaceId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPACEID =
        new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, OntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySpaceId",
            new String[] { Long.class.getName() },
            OntologyTermModelImpl.ONTOLOGYSPACEID_COLUMN_BITMASK |
            OntologyTermModelImpl.ORDER__COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SPACEID = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySpaceId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_SPACEID_ONTOLOGYSPACEID_2 = "ontologyTerm.ontologySpaceId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, OntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, OntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
            new String[] { String.class.getName() },
            OntologyTermModelImpl.NAME_COLUMN_BITMASK |
            OntologyTermModelImpl.ORDER__COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_NAME_NAME_1 = "ontologyTerm.name IS NULL";
    private static final String _FINDER_COLUMN_NAME_NAME_2 = "ontologyTerm.name = ?";
    private static final String _FINDER_COLUMN_NAME_NAME_3 = "(ontologyTerm.name IS NULL OR ontologyTerm.name = '')";
    private static final String _SQL_SELECT_ONTOLOGYTERM = "SELECT ontologyTerm FROM OntologyTerm ontologyTerm";
    private static final String _SQL_SELECT_ONTOLOGYTERM_WHERE = "SELECT ontologyTerm FROM OntologyTerm ontologyTerm WHERE ";
    private static final String _SQL_COUNT_ONTOLOGYTERM = "SELECT COUNT(ontologyTerm) FROM OntologyTerm ontologyTerm";
    private static final String _SQL_COUNT_ONTOLOGYTERM_WHERE = "SELECT COUNT(ontologyTerm) FROM OntologyTerm ontologyTerm WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ontologyTerm.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OntologyTerm exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OntologyTerm exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(OntologyTermPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static OntologyTerm _nullOntologyTerm = new OntologyTermImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<OntologyTerm> toCacheModel() {
                return _nullOntologyTermCacheModel;
            }
        };

    private static CacheModel<OntologyTerm> _nullOntologyTermCacheModel = new CacheModel<OntologyTerm>() {
            @Override
            public OntologyTerm toEntityModel() {
                return _nullOntologyTerm;
            }
        };

    public OntologyTermPersistenceImpl() {
        setModelClass(OntologyTerm.class);
    }

    /**
     * Returns all the ontology terms where parentId = &#63;.
     *
     * @param parentId the parent ID
     * @return the matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findByParentId(long parentId)
        throws SystemException {
        return findByParentId(parentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the ontology terms where parentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param parentId the parent ID
     * @param start the lower bound of the range of ontology terms
     * @param end the upper bound of the range of ontology terms (not inclusive)
     * @return the range of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findByParentId(long parentId, int start, int end)
        throws SystemException {
        return findByParentId(parentId, start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology terms where parentId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param parentId the parent ID
     * @param start the lower bound of the range of ontology terms
     * @param end the upper bound of the range of ontology terms (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findByParentId(long parentId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTID;
            finderArgs = new Object[] { parentId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTID;
            finderArgs = new Object[] { parentId, start, end, orderByComparator };
        }

        List<OntologyTerm> list = (List<OntologyTerm>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (OntologyTerm ontologyTerm : list) {
                if ((parentId != ontologyTerm.getParentId())) {
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

            query.append(_SQL_SELECT_ONTOLOGYTERM_WHERE);

            query.append(_FINDER_COLUMN_PARENTID_PARENTID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(OntologyTermModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(parentId);

                if (!pagination) {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologyTerm>(list);
                } else {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first ontology term in the ordered set where parentId = &#63;.
     *
     * @param parentId the parent ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm findByParentId_First(long parentId,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = fetchByParentId_First(parentId,
                orderByComparator);

        if (ontologyTerm != null) {
            return ontologyTerm;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("parentId=");
        msg.append(parentId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermException(msg.toString());
    }

    /**
     * Returns the first ontology term in the ordered set where parentId = &#63;.
     *
     * @param parentId the parent ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term, or <code>null</code> if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm fetchByParentId_First(long parentId,
        OrderByComparator orderByComparator) throws SystemException {
        List<OntologyTerm> list = findByParentId(parentId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ontology term in the ordered set where parentId = &#63;.
     *
     * @param parentId the parent ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm findByParentId_Last(long parentId,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = fetchByParentId_Last(parentId,
                orderByComparator);

        if (ontologyTerm != null) {
            return ontologyTerm;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("parentId=");
        msg.append(parentId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermException(msg.toString());
    }

    /**
     * Returns the last ontology term in the ordered set where parentId = &#63;.
     *
     * @param parentId the parent ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term, or <code>null</code> if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm fetchByParentId_Last(long parentId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByParentId(parentId);

        if (count == 0) {
            return null;
        }

        List<OntologyTerm> list = findByParentId(parentId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ontology terms before and after the current ontology term in the ordered set where parentId = &#63;.
     *
     * @param id the primary key of the current ontology term
     * @param parentId the parent ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm[] findByParentId_PrevAndNext(long id, long parentId,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            OntologyTerm[] array = new OntologyTermImpl[3];

            array[0] = getByParentId_PrevAndNext(session, ontologyTerm,
                    parentId, orderByComparator, true);

            array[1] = ontologyTerm;

            array[2] = getByParentId_PrevAndNext(session, ontologyTerm,
                    parentId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected OntologyTerm getByParentId_PrevAndNext(Session session,
        OntologyTerm ontologyTerm, long parentId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ONTOLOGYTERM_WHERE);

        query.append(_FINDER_COLUMN_PARENTID_PARENTID_2);

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
            query.append(OntologyTermModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(parentId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ontologyTerm);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<OntologyTerm> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ontology terms where parentId = &#63; from the database.
     *
     * @param parentId the parent ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByParentId(long parentId) throws SystemException {
        for (OntologyTerm ontologyTerm : findByParentId(parentId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ontologyTerm);
        }
    }

    /**
     * Returns the number of ontology terms where parentId = &#63;.
     *
     * @param parentId the parent ID
     * @return the number of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByParentId(long parentId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTID;

        Object[] finderArgs = new Object[] { parentId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ONTOLOGYTERM_WHERE);

            query.append(_FINDER_COLUMN_PARENTID_PARENTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(parentId);

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
     * Returns all the ontology terms where parentId = &#63; and ontologySpaceId = &#63;.
     *
     * @param parentId the parent ID
     * @param ontologySpaceId the ontology space ID
     * @return the matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findByParentIdSpaceId(long parentId,
        long ontologySpaceId) throws SystemException {
        return findByParentIdSpaceId(parentId, ontologySpaceId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology terms where parentId = &#63; and ontologySpaceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param parentId the parent ID
     * @param ontologySpaceId the ontology space ID
     * @param start the lower bound of the range of ontology terms
     * @param end the upper bound of the range of ontology terms (not inclusive)
     * @return the range of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findByParentIdSpaceId(long parentId,
        long ontologySpaceId, int start, int end) throws SystemException {
        return findByParentIdSpaceId(parentId, ontologySpaceId, start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology terms where parentId = &#63; and ontologySpaceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param parentId the parent ID
     * @param ontologySpaceId the ontology space ID
     * @param start the lower bound of the range of ontology terms
     * @param end the upper bound of the range of ontology terms (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findByParentIdSpaceId(long parentId,
        long ontologySpaceId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTIDSPACEID;
            finderArgs = new Object[] { parentId, ontologySpaceId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTIDSPACEID;
            finderArgs = new Object[] {
                    parentId, ontologySpaceId,
                    
                    start, end, orderByComparator
                };
        }

        List<OntologyTerm> list = (List<OntologyTerm>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (OntologyTerm ontologyTerm : list) {
                if ((parentId != ontologyTerm.getParentId()) ||
                        (ontologySpaceId != ontologyTerm.getOntologySpaceId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_ONTOLOGYTERM_WHERE);

            query.append(_FINDER_COLUMN_PARENTIDSPACEID_PARENTID_2);

            query.append(_FINDER_COLUMN_PARENTIDSPACEID_ONTOLOGYSPACEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(OntologyTermModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(parentId);

                qPos.add(ontologySpaceId);

                if (!pagination) {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologyTerm>(list);
                } else {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first ontology term in the ordered set where parentId = &#63; and ontologySpaceId = &#63;.
     *
     * @param parentId the parent ID
     * @param ontologySpaceId the ontology space ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm findByParentIdSpaceId_First(long parentId,
        long ontologySpaceId, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = fetchByParentIdSpaceId_First(parentId,
                ontologySpaceId, orderByComparator);

        if (ontologyTerm != null) {
            return ontologyTerm;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("parentId=");
        msg.append(parentId);

        msg.append(", ontologySpaceId=");
        msg.append(ontologySpaceId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermException(msg.toString());
    }

    /**
     * Returns the first ontology term in the ordered set where parentId = &#63; and ontologySpaceId = &#63;.
     *
     * @param parentId the parent ID
     * @param ontologySpaceId the ontology space ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term, or <code>null</code> if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm fetchByParentIdSpaceId_First(long parentId,
        long ontologySpaceId, OrderByComparator orderByComparator)
        throws SystemException {
        List<OntologyTerm> list = findByParentIdSpaceId(parentId,
                ontologySpaceId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ontology term in the ordered set where parentId = &#63; and ontologySpaceId = &#63;.
     *
     * @param parentId the parent ID
     * @param ontologySpaceId the ontology space ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm findByParentIdSpaceId_Last(long parentId,
        long ontologySpaceId, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = fetchByParentIdSpaceId_Last(parentId,
                ontologySpaceId, orderByComparator);

        if (ontologyTerm != null) {
            return ontologyTerm;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("parentId=");
        msg.append(parentId);

        msg.append(", ontologySpaceId=");
        msg.append(ontologySpaceId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermException(msg.toString());
    }

    /**
     * Returns the last ontology term in the ordered set where parentId = &#63; and ontologySpaceId = &#63;.
     *
     * @param parentId the parent ID
     * @param ontologySpaceId the ontology space ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term, or <code>null</code> if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm fetchByParentIdSpaceId_Last(long parentId,
        long ontologySpaceId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByParentIdSpaceId(parentId, ontologySpaceId);

        if (count == 0) {
            return null;
        }

        List<OntologyTerm> list = findByParentIdSpaceId(parentId,
                ontologySpaceId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ontology terms before and after the current ontology term in the ordered set where parentId = &#63; and ontologySpaceId = &#63;.
     *
     * @param id the primary key of the current ontology term
     * @param parentId the parent ID
     * @param ontologySpaceId the ontology space ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm[] findByParentIdSpaceId_PrevAndNext(long id,
        long parentId, long ontologySpaceId, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            OntologyTerm[] array = new OntologyTermImpl[3];

            array[0] = getByParentIdSpaceId_PrevAndNext(session, ontologyTerm,
                    parentId, ontologySpaceId, orderByComparator, true);

            array[1] = ontologyTerm;

            array[2] = getByParentIdSpaceId_PrevAndNext(session, ontologyTerm,
                    parentId, ontologySpaceId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected OntologyTerm getByParentIdSpaceId_PrevAndNext(Session session,
        OntologyTerm ontologyTerm, long parentId, long ontologySpaceId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ONTOLOGYTERM_WHERE);

        query.append(_FINDER_COLUMN_PARENTIDSPACEID_PARENTID_2);

        query.append(_FINDER_COLUMN_PARENTIDSPACEID_ONTOLOGYSPACEID_2);

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
            query.append(OntologyTermModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(parentId);

        qPos.add(ontologySpaceId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ontologyTerm);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<OntologyTerm> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ontology terms where parentId = &#63; and ontologySpaceId = &#63; from the database.
     *
     * @param parentId the parent ID
     * @param ontologySpaceId the ontology space ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByParentIdSpaceId(long parentId, long ontologySpaceId)
        throws SystemException {
        for (OntologyTerm ontologyTerm : findByParentIdSpaceId(parentId,
                ontologySpaceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ontologyTerm);
        }
    }

    /**
     * Returns the number of ontology terms where parentId = &#63; and ontologySpaceId = &#63;.
     *
     * @param parentId the parent ID
     * @param ontologySpaceId the ontology space ID
     * @return the number of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByParentIdSpaceId(long parentId, long ontologySpaceId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTIDSPACEID;

        Object[] finderArgs = new Object[] { parentId, ontologySpaceId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_ONTOLOGYTERM_WHERE);

            query.append(_FINDER_COLUMN_PARENTIDSPACEID_PARENTID_2);

            query.append(_FINDER_COLUMN_PARENTIDSPACEID_ONTOLOGYSPACEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(parentId);

                qPos.add(ontologySpaceId);

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
     * Returns all the ontology terms where ontologySpaceId = &#63;.
     *
     * @param ontologySpaceId the ontology space ID
     * @return the matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findBySpaceId(long ontologySpaceId)
        throws SystemException {
        return findBySpaceId(ontologySpaceId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology terms where ontologySpaceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ontologySpaceId the ontology space ID
     * @param start the lower bound of the range of ontology terms
     * @param end the upper bound of the range of ontology terms (not inclusive)
     * @return the range of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findBySpaceId(long ontologySpaceId, int start,
        int end) throws SystemException {
        return findBySpaceId(ontologySpaceId, start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology terms where ontologySpaceId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ontologySpaceId the ontology space ID
     * @param start the lower bound of the range of ontology terms
     * @param end the upper bound of the range of ontology terms (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findBySpaceId(long ontologySpaceId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPACEID;
            finderArgs = new Object[] { ontologySpaceId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SPACEID;
            finderArgs = new Object[] {
                    ontologySpaceId,
                    
                    start, end, orderByComparator
                };
        }

        List<OntologyTerm> list = (List<OntologyTerm>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (OntologyTerm ontologyTerm : list) {
                if ((ontologySpaceId != ontologyTerm.getOntologySpaceId())) {
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

            query.append(_SQL_SELECT_ONTOLOGYTERM_WHERE);

            query.append(_FINDER_COLUMN_SPACEID_ONTOLOGYSPACEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(OntologyTermModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ontologySpaceId);

                if (!pagination) {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologyTerm>(list);
                } else {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first ontology term in the ordered set where ontologySpaceId = &#63;.
     *
     * @param ontologySpaceId the ontology space ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm findBySpaceId_First(long ontologySpaceId,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = fetchBySpaceId_First(ontologySpaceId,
                orderByComparator);

        if (ontologyTerm != null) {
            return ontologyTerm;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ontologySpaceId=");
        msg.append(ontologySpaceId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermException(msg.toString());
    }

    /**
     * Returns the first ontology term in the ordered set where ontologySpaceId = &#63;.
     *
     * @param ontologySpaceId the ontology space ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term, or <code>null</code> if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm fetchBySpaceId_First(long ontologySpaceId,
        OrderByComparator orderByComparator) throws SystemException {
        List<OntologyTerm> list = findBySpaceId(ontologySpaceId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ontology term in the ordered set where ontologySpaceId = &#63;.
     *
     * @param ontologySpaceId the ontology space ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm findBySpaceId_Last(long ontologySpaceId,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = fetchBySpaceId_Last(ontologySpaceId,
                orderByComparator);

        if (ontologyTerm != null) {
            return ontologyTerm;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ontologySpaceId=");
        msg.append(ontologySpaceId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermException(msg.toString());
    }

    /**
     * Returns the last ontology term in the ordered set where ontologySpaceId = &#63;.
     *
     * @param ontologySpaceId the ontology space ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term, or <code>null</code> if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm fetchBySpaceId_Last(long ontologySpaceId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySpaceId(ontologySpaceId);

        if (count == 0) {
            return null;
        }

        List<OntologyTerm> list = findBySpaceId(ontologySpaceId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ontology terms before and after the current ontology term in the ordered set where ontologySpaceId = &#63;.
     *
     * @param id the primary key of the current ontology term
     * @param ontologySpaceId the ontology space ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm[] findBySpaceId_PrevAndNext(long id,
        long ontologySpaceId, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            OntologyTerm[] array = new OntologyTermImpl[3];

            array[0] = getBySpaceId_PrevAndNext(session, ontologyTerm,
                    ontologySpaceId, orderByComparator, true);

            array[1] = ontologyTerm;

            array[2] = getBySpaceId_PrevAndNext(session, ontologyTerm,
                    ontologySpaceId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected OntologyTerm getBySpaceId_PrevAndNext(Session session,
        OntologyTerm ontologyTerm, long ontologySpaceId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ONTOLOGYTERM_WHERE);

        query.append(_FINDER_COLUMN_SPACEID_ONTOLOGYSPACEID_2);

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
            query.append(OntologyTermModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ontologySpaceId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ontologyTerm);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<OntologyTerm> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ontology terms where ontologySpaceId = &#63; from the database.
     *
     * @param ontologySpaceId the ontology space ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySpaceId(long ontologySpaceId) throws SystemException {
        for (OntologyTerm ontologyTerm : findBySpaceId(ontologySpaceId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ontologyTerm);
        }
    }

    /**
     * Returns the number of ontology terms where ontologySpaceId = &#63;.
     *
     * @param ontologySpaceId the ontology space ID
     * @return the number of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySpaceId(long ontologySpaceId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SPACEID;

        Object[] finderArgs = new Object[] { ontologySpaceId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ONTOLOGYTERM_WHERE);

            query.append(_FINDER_COLUMN_SPACEID_ONTOLOGYSPACEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ontologySpaceId);

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
     * Returns all the ontology terms where name = &#63;.
     *
     * @param name the name
     * @return the matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findByName(String name) throws SystemException {
        return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology terms where name = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param name the name
     * @param start the lower bound of the range of ontology terms
     * @param end the upper bound of the range of ontology terms (not inclusive)
     * @return the range of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findByName(String name, int start, int end)
        throws SystemException {
        return findByName(name, start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology terms where name = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param name the name
     * @param start the lower bound of the range of ontology terms
     * @param end the upper bound of the range of ontology terms (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findByName(String name, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME;
            finderArgs = new Object[] { name };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
            finderArgs = new Object[] { name, start, end, orderByComparator };
        }

        List<OntologyTerm> list = (List<OntologyTerm>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (OntologyTerm ontologyTerm : list) {
                if (!Validator.equals(name, ontologyTerm.getName())) {
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

            query.append(_SQL_SELECT_ONTOLOGYTERM_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_NAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_NAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_NAME_NAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(OntologyTermModelImpl.ORDER_BY_JPQL);
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

                if (!pagination) {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologyTerm>(list);
                } else {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first ontology term in the ordered set where name = &#63;.
     *
     * @param name the name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm findByName_First(String name,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = fetchByName_First(name, orderByComparator);

        if (ontologyTerm != null) {
            return ontologyTerm;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("name=");
        msg.append(name);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermException(msg.toString());
    }

    /**
     * Returns the first ontology term in the ordered set where name = &#63;.
     *
     * @param name the name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term, or <code>null</code> if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm fetchByName_First(String name,
        OrderByComparator orderByComparator) throws SystemException {
        List<OntologyTerm> list = findByName(name, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ontology term in the ordered set where name = &#63;.
     *
     * @param name the name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm findByName_Last(String name,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = fetchByName_Last(name, orderByComparator);

        if (ontologyTerm != null) {
            return ontologyTerm;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("name=");
        msg.append(name);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermException(msg.toString());
    }

    /**
     * Returns the last ontology term in the ordered set where name = &#63;.
     *
     * @param name the name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term, or <code>null</code> if a matching ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm fetchByName_Last(String name,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByName(name);

        if (count == 0) {
            return null;
        }

        List<OntologyTerm> list = findByName(name, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ontology terms before and after the current ontology term in the ordered set where name = &#63;.
     *
     * @param id the primary key of the current ontology term
     * @param name the name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm[] findByName_PrevAndNext(long id, String name,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            OntologyTerm[] array = new OntologyTermImpl[3];

            array[0] = getByName_PrevAndNext(session, ontologyTerm, name,
                    orderByComparator, true);

            array[1] = ontologyTerm;

            array[2] = getByName_PrevAndNext(session, ontologyTerm, name,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected OntologyTerm getByName_PrevAndNext(Session session,
        OntologyTerm ontologyTerm, String name,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ONTOLOGYTERM_WHERE);

        boolean bindName = false;

        if (name == null) {
            query.append(_FINDER_COLUMN_NAME_NAME_1);
        } else if (name.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_NAME_NAME_3);
        } else {
            bindName = true;

            query.append(_FINDER_COLUMN_NAME_NAME_2);
        }

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
            query.append(OntologyTermModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindName) {
            qPos.add(name);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ontologyTerm);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<OntologyTerm> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ontology terms where name = &#63; from the database.
     *
     * @param name the name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByName(String name) throws SystemException {
        for (OntologyTerm ontologyTerm : findByName(name, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(ontologyTerm);
        }
    }

    /**
     * Returns the number of ontology terms where name = &#63;.
     *
     * @param name the name
     * @return the number of matching ontology terms
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

            query.append(_SQL_COUNT_ONTOLOGYTERM_WHERE);

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
     * Caches the ontology term in the entity cache if it is enabled.
     *
     * @param ontologyTerm the ontology term
     */
    @Override
    public void cacheResult(OntologyTerm ontologyTerm) {
        EntityCacheUtil.putResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermImpl.class, ontologyTerm.getPrimaryKey(), ontologyTerm);

        ontologyTerm.resetOriginalValues();
    }

    /**
     * Caches the ontology terms in the entity cache if it is enabled.
     *
     * @param ontologyTerms the ontology terms
     */
    @Override
    public void cacheResult(List<OntologyTerm> ontologyTerms) {
        for (OntologyTerm ontologyTerm : ontologyTerms) {
            if (EntityCacheUtil.getResult(
                        OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                        OntologyTermImpl.class, ontologyTerm.getPrimaryKey()) == null) {
                cacheResult(ontologyTerm);
            } else {
                ontologyTerm.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ontology terms.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(OntologyTermImpl.class.getName());
        }

        EntityCacheUtil.clearCache(OntologyTermImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ontology term.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(OntologyTerm ontologyTerm) {
        EntityCacheUtil.removeResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermImpl.class, ontologyTerm.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<OntologyTerm> ontologyTerms) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (OntologyTerm ontologyTerm : ontologyTerms) {
            EntityCacheUtil.removeResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                OntologyTermImpl.class, ontologyTerm.getPrimaryKey());
        }
    }

    /**
     * Creates a new ontology term with the primary key. Does not add the ontology term to the database.
     *
     * @param id the primary key for the new ontology term
     * @return the new ontology term
     */
    @Override
    public OntologyTerm create(long id) {
        OntologyTerm ontologyTerm = new OntologyTermImpl();

        ontologyTerm.setNew(true);
        ontologyTerm.setPrimaryKey(id);

        return ontologyTerm;
    }

    /**
     * Removes the ontology term with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the ontology term
     * @return the ontology term that was removed
     * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm remove(long id)
        throws NoSuchOntologyTermException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the ontology term with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ontology term
     * @return the ontology term that was removed
     * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm remove(Serializable primaryKey)
        throws NoSuchOntologyTermException, SystemException {
        Session session = null;

        try {
            session = openSession();

            OntologyTerm ontologyTerm = (OntologyTerm) session.get(OntologyTermImpl.class,
                    primaryKey);

            if (ontologyTerm == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchOntologyTermException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ontologyTerm);
        } catch (NoSuchOntologyTermException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected OntologyTerm removeImpl(OntologyTerm ontologyTerm)
        throws SystemException {
        ontologyTerm = toUnwrappedModel(ontologyTerm);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ontologyTerm)) {
                ontologyTerm = (OntologyTerm) session.get(OntologyTermImpl.class,
                        ontologyTerm.getPrimaryKeyObj());
            }

            if (ontologyTerm != null) {
                session.delete(ontologyTerm);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ontologyTerm != null) {
            clearCache(ontologyTerm);
        }

        return ontologyTerm;
    }

    @Override
    public OntologyTerm updateImpl(
        com.ext.portlet.model.OntologyTerm ontologyTerm)
        throws SystemException {
        ontologyTerm = toUnwrappedModel(ontologyTerm);

        boolean isNew = ontologyTerm.isNew();

        OntologyTermModelImpl ontologyTermModelImpl = (OntologyTermModelImpl) ontologyTerm;

        Session session = null;

        try {
            session = openSession();

            if (ontologyTerm.isNew()) {
                session.save(ontologyTerm);

                ontologyTerm.setNew(false);
            } else {
                session.merge(ontologyTerm);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !OntologyTermModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((ontologyTermModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ontologyTermModelImpl.getOriginalParentId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTID,
                    args);

                args = new Object[] { ontologyTermModelImpl.getParentId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTID,
                    args);
            }

            if ((ontologyTermModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTIDSPACEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ontologyTermModelImpl.getOriginalParentId(),
                        ontologyTermModelImpl.getOriginalOntologySpaceId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTIDSPACEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTIDSPACEID,
                    args);

                args = new Object[] {
                        ontologyTermModelImpl.getParentId(),
                        ontologyTermModelImpl.getOntologySpaceId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTIDSPACEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTIDSPACEID,
                    args);
            }

            if ((ontologyTermModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPACEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ontologyTermModelImpl.getOriginalOntologySpaceId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SPACEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPACEID,
                    args);

                args = new Object[] { ontologyTermModelImpl.getOntologySpaceId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SPACEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPACEID,
                    args);
            }

            if ((ontologyTermModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ontologyTermModelImpl.getOriginalName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
                    args);

                args = new Object[] { ontologyTermModelImpl.getName() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
                    args);
            }
        }

        EntityCacheUtil.putResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermImpl.class, ontologyTerm.getPrimaryKey(), ontologyTerm);

        return ontologyTerm;
    }

    protected OntologyTerm toUnwrappedModel(OntologyTerm ontologyTerm) {
        if (ontologyTerm instanceof OntologyTermImpl) {
            return ontologyTerm;
        }

        OntologyTermImpl ontologyTermImpl = new OntologyTermImpl();

        ontologyTermImpl.setNew(ontologyTerm.isNew());
        ontologyTermImpl.setPrimaryKey(ontologyTerm.getPrimaryKey());

        ontologyTermImpl.setId(ontologyTerm.getId());
        ontologyTermImpl.setParentId(ontologyTerm.getParentId());
        ontologyTermImpl.setOntologySpaceId(ontologyTerm.getOntologySpaceId());
        ontologyTermImpl.setName(ontologyTerm.getName());
        ontologyTermImpl.setDescriptionUrl(ontologyTerm.getDescriptionUrl());
        ontologyTermImpl.setOrder_(ontologyTerm.getOrder_());

        return ontologyTermImpl;
    }

    /**
     * Returns the ontology term with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ontology term
     * @return the ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm findByPrimaryKey(Serializable primaryKey)
        throws NoSuchOntologyTermException, SystemException {
        OntologyTerm ontologyTerm = fetchByPrimaryKey(primaryKey);

        if (ontologyTerm == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchOntologyTermException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ontologyTerm;
    }

    /**
     * Returns the ontology term with the primary key or throws a {@link com.ext.portlet.NoSuchOntologyTermException} if it could not be found.
     *
     * @param id the primary key of the ontology term
     * @return the ontology term
     * @throws com.ext.portlet.NoSuchOntologyTermException if a ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm findByPrimaryKey(long id)
        throws NoSuchOntologyTermException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the ontology term with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ontology term
     * @return the ontology term, or <code>null</code> if a ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        OntologyTerm ontologyTerm = (OntologyTerm) EntityCacheUtil.getResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                OntologyTermImpl.class, primaryKey);

        if (ontologyTerm == _nullOntologyTerm) {
            return null;
        }

        if (ontologyTerm == null) {
            Session session = null;

            try {
                session = openSession();

                ontologyTerm = (OntologyTerm) session.get(OntologyTermImpl.class,
                        primaryKey);

                if (ontologyTerm != null) {
                    cacheResult(ontologyTerm);
                } else {
                    EntityCacheUtil.putResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                        OntologyTermImpl.class, primaryKey, _nullOntologyTerm);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(OntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                    OntologyTermImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ontologyTerm;
    }

    /**
     * Returns the ontology term with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the ontology term
     * @return the ontology term, or <code>null</code> if a ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTerm fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the ontology terms.
     *
     * @return the ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology terms.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ontology terms
     * @param end the upper bound of the range of ontology terms (not inclusive)
     * @return the range of ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology terms.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ontology terms
     * @param end the upper bound of the range of ontology terms (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ontology terms
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTerm> findAll(int start, int end,
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

        List<OntologyTerm> list = (List<OntologyTerm>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ONTOLOGYTERM);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ONTOLOGYTERM;

                if (pagination) {
                    sql = sql.concat(OntologyTermModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologyTerm>(list);
                } else {
                    list = (List<OntologyTerm>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the ontology terms from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (OntologyTerm ontologyTerm : findAll()) {
            remove(ontologyTerm);
        }
    }

    /**
     * Returns the number of ontology terms.
     *
     * @return the number of ontology terms
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

                Query q = session.createQuery(_SQL_COUNT_ONTOLOGYTERM);

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
     * Initializes the ontology term persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.OntologyTerm")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<OntologyTerm>> listenersList = new ArrayList<ModelListener<OntologyTerm>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<OntologyTerm>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(OntologyTermImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
