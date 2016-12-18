package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchOntologyTermEntityException;
import com.ext.portlet.model.OntologyTermEntity;
import com.ext.portlet.model.impl.OntologyTermEntityImpl;
import com.ext.portlet.model.impl.OntologyTermEntityModelImpl;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the ontology term entity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntityPersistence
 * @see OntologyTermEntityUtil
 * @generated
 */
public class OntologyTermEntityPersistenceImpl extends BasePersistenceImpl<OntologyTermEntity>
    implements OntologyTermEntityPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link OntologyTermEntityUtil} to access the ontology term entity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = OntologyTermEntityImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            OntologyTermEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            OntologyTermEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEID =
        new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            OntologyTermEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByClassNameId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID =
        new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            OntologyTermEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByClassNameId",
            new String[] { Long.class.getName() },
            OntologyTermEntityModelImpl.CLASSNAMEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEID = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByClassNameId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2 = "ontologyTermEntity.classNameId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK =
        new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            OntologyTermEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByClassNameIdClassPk",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK =
        new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            OntologyTermEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByClassNameIdClassPk",
            new String[] { Long.class.getName(), Long.class.getName() },
            OntologyTermEntityModelImpl.CLASSNAMEID_COLUMN_BITMASK |
            OntologyTermEntityModelImpl.CLASSPK_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByClassNameIdClassPk",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2 = "ontologyTermEntity.classNameId = ? AND ";
    private static final String _FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2 = "ontologyTermEntity.classPK = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TERMID = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            OntologyTermEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTermId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TERMID =
        new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            OntologyTermEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTermId",
            new String[] { Long.class.getName() },
            OntologyTermEntityModelImpl.TERMID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TERMID = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTermId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_TERMID_TERMID_2 = "ontologyTermEntity.termId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TERMIDCLASSNAMEID =
        new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            OntologyTermEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTermIdClassNameId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TERMIDCLASSNAMEID =
        new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED,
            OntologyTermEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByTermIdClassNameId",
            new String[] { Long.class.getName(), Long.class.getName() },
            OntologyTermEntityModelImpl.TERMID_COLUMN_BITMASK |
            OntologyTermEntityModelImpl.CLASSNAMEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TERMIDCLASSNAMEID = new FinderPath(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByTermIdClassNameId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_TERMIDCLASSNAMEID_TERMID_2 = "ontologyTermEntity.termId = ? AND ";
    private static final String _FINDER_COLUMN_TERMIDCLASSNAMEID_CLASSNAMEID_2 = "ontologyTermEntity.classNameId = ?";
    private static final String _SQL_SELECT_ONTOLOGYTERMENTITY = "SELECT ontologyTermEntity FROM OntologyTermEntity ontologyTermEntity";
    private static final String _SQL_SELECT_ONTOLOGYTERMENTITY_WHERE = "SELECT ontologyTermEntity FROM OntologyTermEntity ontologyTermEntity WHERE ";
    private static final String _SQL_COUNT_ONTOLOGYTERMENTITY = "SELECT COUNT(ontologyTermEntity) FROM OntologyTermEntity ontologyTermEntity";
    private static final String _SQL_COUNT_ONTOLOGYTERMENTITY_WHERE = "SELECT COUNT(ontologyTermEntity) FROM OntologyTermEntity ontologyTermEntity WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ontologyTermEntity.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OntologyTermEntity exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OntologyTermEntity exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(OntologyTermEntityPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static OntologyTermEntity _nullOntologyTermEntity = new OntologyTermEntityImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<OntologyTermEntity> toCacheModel() {
                return _nullOntologyTermEntityCacheModel;
            }
        };

    private static CacheModel<OntologyTermEntity> _nullOntologyTermEntityCacheModel =
        new CacheModel<OntologyTermEntity>() {
            @Override
            public OntologyTermEntity toEntityModel() {
                return _nullOntologyTermEntity;
            }
        };

    public OntologyTermEntityPersistenceImpl() {
        setModelClass(OntologyTermEntity.class);
    }

    /**
     * Returns all the ontology term entities where classNameId = &#63;.
     *
     * @param classNameId the class name ID
     * @return the matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByClassNameId(long classNameId)
        throws SystemException {
        return findByClassNameId(classNameId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology term entities where classNameId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param classNameId the class name ID
     * @param start the lower bound of the range of ontology term entities
     * @param end the upper bound of the range of ontology term entities (not inclusive)
     * @return the range of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByClassNameId(long classNameId,
        int start, int end) throws SystemException {
        return findByClassNameId(classNameId, start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology term entities where classNameId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param classNameId the class name ID
     * @param start the lower bound of the range of ontology term entities
     * @param end the upper bound of the range of ontology term entities (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByClassNameId(long classNameId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID;
            finderArgs = new Object[] { classNameId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEID;
            finderArgs = new Object[] { classNameId, start, end, orderByComparator };
        }

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (OntologyTermEntity ontologyTermEntity : list) {
                if ((classNameId != ontologyTermEntity.getClassNameId())) {
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

            query.append(_SQL_SELECT_ONTOLOGYTERMENTITY_WHERE);

            query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(OntologyTermEntityModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(classNameId);

                if (!pagination) {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologyTermEntity>(list);
                } else {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
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
     * Returns the first ontology term entity in the ordered set where classNameId = &#63;.
     *
     * @param classNameId the class name ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity findByClassNameId_First(long classNameId,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = fetchByClassNameId_First(classNameId,
                orderByComparator);

        if (ontologyTermEntity != null) {
            return ontologyTermEntity;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("classNameId=");
        msg.append(classNameId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermEntityException(msg.toString());
    }

    /**
     * Returns the first ontology term entity in the ordered set where classNameId = &#63;.
     *
     * @param classNameId the class name ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term entity, or <code>null</code> if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity fetchByClassNameId_First(long classNameId,
        OrderByComparator orderByComparator) throws SystemException {
        List<OntologyTermEntity> list = findByClassNameId(classNameId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ontology term entity in the ordered set where classNameId = &#63;.
     *
     * @param classNameId the class name ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity findByClassNameId_Last(long classNameId,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = fetchByClassNameId_Last(classNameId,
                orderByComparator);

        if (ontologyTermEntity != null) {
            return ontologyTermEntity;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("classNameId=");
        msg.append(classNameId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermEntityException(msg.toString());
    }

    /**
     * Returns the last ontology term entity in the ordered set where classNameId = &#63;.
     *
     * @param classNameId the class name ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term entity, or <code>null</code> if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity fetchByClassNameId_Last(long classNameId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByClassNameId(classNameId);

        if (count == 0) {
            return null;
        }

        List<OntologyTermEntity> list = findByClassNameId(classNameId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ontology term entities before and after the current ontology term entity in the ordered set where classNameId = &#63;.
     *
     * @param id the primary key of the current ontology term entity
     * @param classNameId the class name ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity[] findByClassNameId_PrevAndNext(long id,
        long classNameId, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            OntologyTermEntity[] array = new OntologyTermEntityImpl[3];

            array[0] = getByClassNameId_PrevAndNext(session,
                    ontologyTermEntity, classNameId, orderByComparator, true);

            array[1] = ontologyTermEntity;

            array[2] = getByClassNameId_PrevAndNext(session,
                    ontologyTermEntity, classNameId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected OntologyTermEntity getByClassNameId_PrevAndNext(Session session,
        OntologyTermEntity ontologyTermEntity, long classNameId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ONTOLOGYTERMENTITY_WHERE);

        query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2);

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
            query.append(OntologyTermEntityModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(classNameId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ontologyTermEntity);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<OntologyTermEntity> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ontology term entities where classNameId = &#63; from the database.
     *
     * @param classNameId the class name ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByClassNameId(long classNameId) throws SystemException {
        for (OntologyTermEntity ontologyTermEntity : findByClassNameId(
                classNameId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ontologyTermEntity);
        }
    }

    /**
     * Returns the number of ontology term entities where classNameId = &#63;.
     *
     * @param classNameId the class name ID
     * @return the number of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByClassNameId(long classNameId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSNAMEID;

        Object[] finderArgs = new Object[] { classNameId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ONTOLOGYTERMENTITY_WHERE);

            query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(classNameId);

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
     * Returns all the ontology term entities where classNameId = &#63; and classPK = &#63;.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @return the matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByClassNameIdClassPk(long classNameId,
        long classPK) throws SystemException {
        return findByClassNameIdClassPk(classNameId, classPK,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology term entities where classNameId = &#63; and classPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param start the lower bound of the range of ontology term entities
     * @param end the upper bound of the range of ontology term entities (not inclusive)
     * @return the range of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByClassNameIdClassPk(long classNameId,
        long classPK, int start, int end) throws SystemException {
        return findByClassNameIdClassPk(classNameId, classPK, start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology term entities where classNameId = &#63; and classPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param start the lower bound of the range of ontology term entities
     * @param end the upper bound of the range of ontology term entities (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByClassNameIdClassPk(long classNameId,
        long classPK, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK;
            finderArgs = new Object[] { classNameId, classPK };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK;
            finderArgs = new Object[] {
                    classNameId, classPK,
                    
                    start, end, orderByComparator
                };
        }

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (OntologyTermEntity ontologyTermEntity : list) {
                if ((classNameId != ontologyTermEntity.getClassNameId()) ||
                        (classPK != ontologyTermEntity.getClassPK())) {
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

            query.append(_SQL_SELECT_ONTOLOGYTERMENTITY_WHERE);

            query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2);

            query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(OntologyTermEntityModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(classNameId);

                qPos.add(classPK);

                if (!pagination) {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologyTermEntity>(list);
                } else {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
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
     * Returns the first ontology term entity in the ordered set where classNameId = &#63; and classPK = &#63;.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity findByClassNameIdClassPk_First(long classNameId,
        long classPK, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = fetchByClassNameIdClassPk_First(classNameId,
                classPK, orderByComparator);

        if (ontologyTermEntity != null) {
            return ontologyTermEntity;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("classNameId=");
        msg.append(classNameId);

        msg.append(", classPK=");
        msg.append(classPK);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermEntityException(msg.toString());
    }

    /**
     * Returns the first ontology term entity in the ordered set where classNameId = &#63; and classPK = &#63;.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term entity, or <code>null</code> if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity fetchByClassNameIdClassPk_First(
        long classNameId, long classPK, OrderByComparator orderByComparator)
        throws SystemException {
        List<OntologyTermEntity> list = findByClassNameIdClassPk(classNameId,
                classPK, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ontology term entity in the ordered set where classNameId = &#63; and classPK = &#63;.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity findByClassNameIdClassPk_Last(long classNameId,
        long classPK, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = fetchByClassNameIdClassPk_Last(classNameId,
                classPK, orderByComparator);

        if (ontologyTermEntity != null) {
            return ontologyTermEntity;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("classNameId=");
        msg.append(classNameId);

        msg.append(", classPK=");
        msg.append(classPK);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermEntityException(msg.toString());
    }

    /**
     * Returns the last ontology term entity in the ordered set where classNameId = &#63; and classPK = &#63;.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term entity, or <code>null</code> if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity fetchByClassNameIdClassPk_Last(long classNameId,
        long classPK, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByClassNameIdClassPk(classNameId, classPK);

        if (count == 0) {
            return null;
        }

        List<OntologyTermEntity> list = findByClassNameIdClassPk(classNameId,
                classPK, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ontology term entities before and after the current ontology term entity in the ordered set where classNameId = &#63; and classPK = &#63;.
     *
     * @param id the primary key of the current ontology term entity
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity[] findByClassNameIdClassPk_PrevAndNext(long id,
        long classNameId, long classPK, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            OntologyTermEntity[] array = new OntologyTermEntityImpl[3];

            array[0] = getByClassNameIdClassPk_PrevAndNext(session,
                    ontologyTermEntity, classNameId, classPK,
                    orderByComparator, true);

            array[1] = ontologyTermEntity;

            array[2] = getByClassNameIdClassPk_PrevAndNext(session,
                    ontologyTermEntity, classNameId, classPK,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected OntologyTermEntity getByClassNameIdClassPk_PrevAndNext(
        Session session, OntologyTermEntity ontologyTermEntity,
        long classNameId, long classPK, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ONTOLOGYTERMENTITY_WHERE);

        query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2);

        query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2);

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
            query.append(OntologyTermEntityModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(classNameId);

        qPos.add(classPK);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ontologyTermEntity);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<OntologyTermEntity> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ontology term entities where classNameId = &#63; and classPK = &#63; from the database.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByClassNameIdClassPk(long classNameId, long classPK)
        throws SystemException {
        for (OntologyTermEntity ontologyTermEntity : findByClassNameIdClassPk(
                classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ontologyTermEntity);
        }
    }

    /**
     * Returns the number of ontology term entities where classNameId = &#63; and classPK = &#63;.
     *
     * @param classNameId the class name ID
     * @param classPK the class p k
     * @return the number of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByClassNameIdClassPk(long classNameId, long classPK)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK;

        Object[] finderArgs = new Object[] { classNameId, classPK };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_ONTOLOGYTERMENTITY_WHERE);

            query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2);

            query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(classNameId);

                qPos.add(classPK);

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
     * Returns all the ontology term entities where termId = &#63;.
     *
     * @param termId the term ID
     * @return the matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByTermId(long termId)
        throws SystemException {
        return findByTermId(termId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology term entities where termId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param termId the term ID
     * @param start the lower bound of the range of ontology term entities
     * @param end the upper bound of the range of ontology term entities (not inclusive)
     * @return the range of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByTermId(long termId, int start, int end)
        throws SystemException {
        return findByTermId(termId, start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology term entities where termId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param termId the term ID
     * @param start the lower bound of the range of ontology term entities
     * @param end the upper bound of the range of ontology term entities (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByTermId(long termId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TERMID;
            finderArgs = new Object[] { termId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TERMID;
            finderArgs = new Object[] { termId, start, end, orderByComparator };
        }

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (OntologyTermEntity ontologyTermEntity : list) {
                if ((termId != ontologyTermEntity.getTermId())) {
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

            query.append(_SQL_SELECT_ONTOLOGYTERMENTITY_WHERE);

            query.append(_FINDER_COLUMN_TERMID_TERMID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(OntologyTermEntityModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(termId);

                if (!pagination) {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologyTermEntity>(list);
                } else {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
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
     * Returns the first ontology term entity in the ordered set where termId = &#63;.
     *
     * @param termId the term ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity findByTermId_First(long termId,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = fetchByTermId_First(termId,
                orderByComparator);

        if (ontologyTermEntity != null) {
            return ontologyTermEntity;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("termId=");
        msg.append(termId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermEntityException(msg.toString());
    }

    /**
     * Returns the first ontology term entity in the ordered set where termId = &#63;.
     *
     * @param termId the term ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term entity, or <code>null</code> if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity fetchByTermId_First(long termId,
        OrderByComparator orderByComparator) throws SystemException {
        List<OntologyTermEntity> list = findByTermId(termId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ontology term entity in the ordered set where termId = &#63;.
     *
     * @param termId the term ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity findByTermId_Last(long termId,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = fetchByTermId_Last(termId,
                orderByComparator);

        if (ontologyTermEntity != null) {
            return ontologyTermEntity;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("termId=");
        msg.append(termId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermEntityException(msg.toString());
    }

    /**
     * Returns the last ontology term entity in the ordered set where termId = &#63;.
     *
     * @param termId the term ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term entity, or <code>null</code> if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity fetchByTermId_Last(long termId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByTermId(termId);

        if (count == 0) {
            return null;
        }

        List<OntologyTermEntity> list = findByTermId(termId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ontology term entities before and after the current ontology term entity in the ordered set where termId = &#63;.
     *
     * @param id the primary key of the current ontology term entity
     * @param termId the term ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity[] findByTermId_PrevAndNext(long id, long termId,
        OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            OntologyTermEntity[] array = new OntologyTermEntityImpl[3];

            array[0] = getByTermId_PrevAndNext(session, ontologyTermEntity,
                    termId, orderByComparator, true);

            array[1] = ontologyTermEntity;

            array[2] = getByTermId_PrevAndNext(session, ontologyTermEntity,
                    termId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected OntologyTermEntity getByTermId_PrevAndNext(Session session,
        OntologyTermEntity ontologyTermEntity, long termId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ONTOLOGYTERMENTITY_WHERE);

        query.append(_FINDER_COLUMN_TERMID_TERMID_2);

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
            query.append(OntologyTermEntityModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(termId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ontologyTermEntity);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<OntologyTermEntity> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ontology term entities where termId = &#63; from the database.
     *
     * @param termId the term ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByTermId(long termId) throws SystemException {
        for (OntologyTermEntity ontologyTermEntity : findByTermId(termId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ontologyTermEntity);
        }
    }

    /**
     * Returns the number of ontology term entities where termId = &#63;.
     *
     * @param termId the term ID
     * @return the number of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTermId(long termId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TERMID;

        Object[] finderArgs = new Object[] { termId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ONTOLOGYTERMENTITY_WHERE);

            query.append(_FINDER_COLUMN_TERMID_TERMID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(termId);

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
     * Returns all the ontology term entities where termId = &#63; and classNameId = &#63;.
     *
     * @param termId the term ID
     * @param classNameId the class name ID
     * @return the matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByTermIdClassNameId(long termId,
        long classNameId) throws SystemException {
        return findByTermIdClassNameId(termId, classNameId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology term entities where termId = &#63; and classNameId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param termId the term ID
     * @param classNameId the class name ID
     * @param start the lower bound of the range of ontology term entities
     * @param end the upper bound of the range of ontology term entities (not inclusive)
     * @return the range of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByTermIdClassNameId(long termId,
        long classNameId, int start, int end) throws SystemException {
        return findByTermIdClassNameId(termId, classNameId, start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology term entities where termId = &#63; and classNameId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param termId the term ID
     * @param classNameId the class name ID
     * @param start the lower bound of the range of ontology term entities
     * @param end the upper bound of the range of ontology term entities (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findByTermIdClassNameId(long termId,
        long classNameId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TERMIDCLASSNAMEID;
            finderArgs = new Object[] { termId, classNameId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TERMIDCLASSNAMEID;
            finderArgs = new Object[] {
                    termId, classNameId,
                    
                    start, end, orderByComparator
                };
        }

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (OntologyTermEntity ontologyTermEntity : list) {
                if ((termId != ontologyTermEntity.getTermId()) ||
                        (classNameId != ontologyTermEntity.getClassNameId())) {
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

            query.append(_SQL_SELECT_ONTOLOGYTERMENTITY_WHERE);

            query.append(_FINDER_COLUMN_TERMIDCLASSNAMEID_TERMID_2);

            query.append(_FINDER_COLUMN_TERMIDCLASSNAMEID_CLASSNAMEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(OntologyTermEntityModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(termId);

                qPos.add(classNameId);

                if (!pagination) {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologyTermEntity>(list);
                } else {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
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
     * Returns the first ontology term entity in the ordered set where termId = &#63; and classNameId = &#63;.
     *
     * @param termId the term ID
     * @param classNameId the class name ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity findByTermIdClassNameId_First(long termId,
        long classNameId, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = fetchByTermIdClassNameId_First(termId,
                classNameId, orderByComparator);

        if (ontologyTermEntity != null) {
            return ontologyTermEntity;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("termId=");
        msg.append(termId);

        msg.append(", classNameId=");
        msg.append(classNameId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermEntityException(msg.toString());
    }

    /**
     * Returns the first ontology term entity in the ordered set where termId = &#63; and classNameId = &#63;.
     *
     * @param termId the term ID
     * @param classNameId the class name ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ontology term entity, or <code>null</code> if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity fetchByTermIdClassNameId_First(long termId,
        long classNameId, OrderByComparator orderByComparator)
        throws SystemException {
        List<OntologyTermEntity> list = findByTermIdClassNameId(termId,
                classNameId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ontology term entity in the ordered set where termId = &#63; and classNameId = &#63;.
     *
     * @param termId the term ID
     * @param classNameId the class name ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity findByTermIdClassNameId_Last(long termId,
        long classNameId, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = fetchByTermIdClassNameId_Last(termId,
                classNameId, orderByComparator);

        if (ontologyTermEntity != null) {
            return ontologyTermEntity;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("termId=");
        msg.append(termId);

        msg.append(", classNameId=");
        msg.append(classNameId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchOntologyTermEntityException(msg.toString());
    }

    /**
     * Returns the last ontology term entity in the ordered set where termId = &#63; and classNameId = &#63;.
     *
     * @param termId the term ID
     * @param classNameId the class name ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ontology term entity, or <code>null</code> if a matching ontology term entity could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity fetchByTermIdClassNameId_Last(long termId,
        long classNameId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByTermIdClassNameId(termId, classNameId);

        if (count == 0) {
            return null;
        }

        List<OntologyTermEntity> list = findByTermIdClassNameId(termId,
                classNameId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the ontology term entities before and after the current ontology term entity in the ordered set where termId = &#63; and classNameId = &#63;.
     *
     * @param id the primary key of the current ontology term entity
     * @param termId the term ID
     * @param classNameId the class name ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity[] findByTermIdClassNameId_PrevAndNext(long id,
        long termId, long classNameId, OrderByComparator orderByComparator)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            OntologyTermEntity[] array = new OntologyTermEntityImpl[3];

            array[0] = getByTermIdClassNameId_PrevAndNext(session,
                    ontologyTermEntity, termId, classNameId, orderByComparator,
                    true);

            array[1] = ontologyTermEntity;

            array[2] = getByTermIdClassNameId_PrevAndNext(session,
                    ontologyTermEntity, termId, classNameId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected OntologyTermEntity getByTermIdClassNameId_PrevAndNext(
        Session session, OntologyTermEntity ontologyTermEntity, long termId,
        long classNameId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_ONTOLOGYTERMENTITY_WHERE);

        query.append(_FINDER_COLUMN_TERMIDCLASSNAMEID_TERMID_2);

        query.append(_FINDER_COLUMN_TERMIDCLASSNAMEID_CLASSNAMEID_2);

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
            query.append(OntologyTermEntityModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(termId);

        qPos.add(classNameId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ontologyTermEntity);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<OntologyTermEntity> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ontology term entities where termId = &#63; and classNameId = &#63; from the database.
     *
     * @param termId the term ID
     * @param classNameId the class name ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByTermIdClassNameId(long termId, long classNameId)
        throws SystemException {
        for (OntologyTermEntity ontologyTermEntity : findByTermIdClassNameId(
                termId, classNameId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ontologyTermEntity);
        }
    }

    /**
     * Returns the number of ontology term entities where termId = &#63; and classNameId = &#63;.
     *
     * @param termId the term ID
     * @param classNameId the class name ID
     * @return the number of matching ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTermIdClassNameId(long termId, long classNameId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TERMIDCLASSNAMEID;

        Object[] finderArgs = new Object[] { termId, classNameId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_ONTOLOGYTERMENTITY_WHERE);

            query.append(_FINDER_COLUMN_TERMIDCLASSNAMEID_TERMID_2);

            query.append(_FINDER_COLUMN_TERMIDCLASSNAMEID_CLASSNAMEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(termId);

                qPos.add(classNameId);

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
     * Caches the ontology term entity in the entity cache if it is enabled.
     *
     * @param ontologyTermEntity the ontology term entity
     */
    @Override
    public void cacheResult(OntologyTermEntity ontologyTermEntity) {
        EntityCacheUtil.putResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityImpl.class, ontologyTermEntity.getPrimaryKey(),
            ontologyTermEntity);

        ontologyTermEntity.resetOriginalValues();
    }

    /**
     * Caches the ontology term entities in the entity cache if it is enabled.
     *
     * @param ontologyTermEntities the ontology term entities
     */
    @Override
    public void cacheResult(List<OntologyTermEntity> ontologyTermEntities) {
        for (OntologyTermEntity ontologyTermEntity : ontologyTermEntities) {
            if (EntityCacheUtil.getResult(
                        OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
                        OntologyTermEntityImpl.class,
                        ontologyTermEntity.getPrimaryKey()) == null) {
                cacheResult(ontologyTermEntity);
            } else {
                ontologyTermEntity.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ontology term entities.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(OntologyTermEntityImpl.class.getName());
        }

        EntityCacheUtil.clearCache(OntologyTermEntityImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ontology term entity.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(OntologyTermEntity ontologyTermEntity) {
        EntityCacheUtil.removeResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityImpl.class, ontologyTermEntity.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<OntologyTermEntity> ontologyTermEntities) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (OntologyTermEntity ontologyTermEntity : ontologyTermEntities) {
            EntityCacheUtil.removeResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
                OntologyTermEntityImpl.class, ontologyTermEntity.getPrimaryKey());
        }
    }

    /**
     * Creates a new ontology term entity with the primary key. Does not add the ontology term entity to the database.
     *
     * @param id the primary key for the new ontology term entity
     * @return the new ontology term entity
     */
    @Override
    public OntologyTermEntity create(long id) {
        OntologyTermEntity ontologyTermEntity = new OntologyTermEntityImpl();

        ontologyTermEntity.setNew(true);
        ontologyTermEntity.setPrimaryKey(id);

        return ontologyTermEntity;
    }

    /**
     * Removes the ontology term entity with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the ontology term entity
     * @return the ontology term entity that was removed
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity remove(long id)
        throws NoSuchOntologyTermEntityException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the ontology term entity with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ontology term entity
     * @return the ontology term entity that was removed
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity remove(Serializable primaryKey)
        throws NoSuchOntologyTermEntityException, SystemException {
        Session session = null;

        try {
            session = openSession();

            OntologyTermEntity ontologyTermEntity = (OntologyTermEntity) session.get(OntologyTermEntityImpl.class,
                    primaryKey);

            if (ontologyTermEntity == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchOntologyTermEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ontologyTermEntity);
        } catch (NoSuchOntologyTermEntityException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected OntologyTermEntity removeImpl(
        OntologyTermEntity ontologyTermEntity) throws SystemException {
        ontologyTermEntity = toUnwrappedModel(ontologyTermEntity);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ontologyTermEntity)) {
                ontologyTermEntity = (OntologyTermEntity) session.get(OntologyTermEntityImpl.class,
                        ontologyTermEntity.getPrimaryKeyObj());
            }

            if (ontologyTermEntity != null) {
                session.delete(ontologyTermEntity);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ontologyTermEntity != null) {
            clearCache(ontologyTermEntity);
        }

        return ontologyTermEntity;
    }

    @Override
    public OntologyTermEntity updateImpl(
        com.ext.portlet.model.OntologyTermEntity ontologyTermEntity)
        throws SystemException {
        ontologyTermEntity = toUnwrappedModel(ontologyTermEntity);

        boolean isNew = ontologyTermEntity.isNew();

        OntologyTermEntityModelImpl ontologyTermEntityModelImpl = (OntologyTermEntityModelImpl) ontologyTermEntity;

        Session session = null;

        try {
            session = openSession();

            if (ontologyTermEntity.isNew()) {
                session.save(ontologyTermEntity);

                ontologyTermEntity.setNew(false);
            } else {
                session.merge(ontologyTermEntity);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !OntologyTermEntityModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((ontologyTermEntityModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ontologyTermEntityModelImpl.getOriginalClassNameId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID,
                    args);

                args = new Object[] { ontologyTermEntityModelImpl.getClassNameId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID,
                    args);
            }

            if ((ontologyTermEntityModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ontologyTermEntityModelImpl.getOriginalClassNameId(),
                        ontologyTermEntityModelImpl.getOriginalClassPK()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK,
                    args);

                args = new Object[] {
                        ontologyTermEntityModelImpl.getClassNameId(),
                        ontologyTermEntityModelImpl.getClassPK()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK,
                    args);
            }

            if ((ontologyTermEntityModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TERMID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ontologyTermEntityModelImpl.getOriginalTermId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TERMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TERMID,
                    args);

                args = new Object[] { ontologyTermEntityModelImpl.getTermId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TERMID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TERMID,
                    args);
            }

            if ((ontologyTermEntityModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TERMIDCLASSNAMEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ontologyTermEntityModelImpl.getOriginalTermId(),
                        ontologyTermEntityModelImpl.getOriginalClassNameId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TERMIDCLASSNAMEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TERMIDCLASSNAMEID,
                    args);

                args = new Object[] {
                        ontologyTermEntityModelImpl.getTermId(),
                        ontologyTermEntityModelImpl.getClassNameId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TERMIDCLASSNAMEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TERMIDCLASSNAMEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
            OntologyTermEntityImpl.class, ontologyTermEntity.getPrimaryKey(),
            ontologyTermEntity);

        return ontologyTermEntity;
    }

    protected OntologyTermEntity toUnwrappedModel(
        OntologyTermEntity ontologyTermEntity) {
        if (ontologyTermEntity instanceof OntologyTermEntityImpl) {
            return ontologyTermEntity;
        }

        OntologyTermEntityImpl ontologyTermEntityImpl = new OntologyTermEntityImpl();

        ontologyTermEntityImpl.setNew(ontologyTermEntity.isNew());
        ontologyTermEntityImpl.setPrimaryKey(ontologyTermEntity.getPrimaryKey());

        ontologyTermEntityImpl.setId(ontologyTermEntity.getId());
        ontologyTermEntityImpl.setTermId(ontologyTermEntity.getTermId());
        ontologyTermEntityImpl.setClassNameId(ontologyTermEntity.getClassNameId());
        ontologyTermEntityImpl.setClassPK(ontologyTermEntity.getClassPK());

        return ontologyTermEntityImpl;
    }

    /**
     * Returns the ontology term entity with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ontology term entity
     * @return the ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity findByPrimaryKey(Serializable primaryKey)
        throws NoSuchOntologyTermEntityException, SystemException {
        OntologyTermEntity ontologyTermEntity = fetchByPrimaryKey(primaryKey);

        if (ontologyTermEntity == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchOntologyTermEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ontologyTermEntity;
    }

    /**
     * Returns the ontology term entity with the primary key or throws a {@link com.ext.portlet.NoSuchOntologyTermEntityException} if it could not be found.
     *
     * @param id the primary key of the ontology term entity
     * @return the ontology term entity
     * @throws com.ext.portlet.NoSuchOntologyTermEntityException if a ontology term entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity findByPrimaryKey(long id)
        throws NoSuchOntologyTermEntityException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the ontology term entity with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ontology term entity
     * @return the ontology term entity, or <code>null</code> if a ontology term entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        OntologyTermEntity ontologyTermEntity = (OntologyTermEntity) EntityCacheUtil.getResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
                OntologyTermEntityImpl.class, primaryKey);

        if (ontologyTermEntity == _nullOntologyTermEntity) {
            return null;
        }

        if (ontologyTermEntity == null) {
            Session session = null;

            try {
                session = openSession();

                ontologyTermEntity = (OntologyTermEntity) session.get(OntologyTermEntityImpl.class,
                        primaryKey);

                if (ontologyTermEntity != null) {
                    cacheResult(ontologyTermEntity);
                } else {
                    EntityCacheUtil.putResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
                        OntologyTermEntityImpl.class, primaryKey,
                        _nullOntologyTermEntity);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(OntologyTermEntityModelImpl.ENTITY_CACHE_ENABLED,
                    OntologyTermEntityImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ontologyTermEntity;
    }

    /**
     * Returns the ontology term entity with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the ontology term entity
     * @return the ontology term entity, or <code>null</code> if a ontology term entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologyTermEntity fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the ontology term entities.
     *
     * @return the ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology term entities.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ontology term entities
     * @param end the upper bound of the range of ontology term entities (not inclusive)
     * @return the range of ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology term entities.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.OntologyTermEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ontology term entities
     * @param end the upper bound of the range of ontology term entities (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ontology term entities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<OntologyTermEntity> findAll(int start, int end,
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

        List<OntologyTermEntity> list = (List<OntologyTermEntity>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ONTOLOGYTERMENTITY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ONTOLOGYTERMENTITY;

                if (pagination) {
                    sql = sql.concat(OntologyTermEntityModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<OntologyTermEntity>(list);
                } else {
                    list = (List<OntologyTermEntity>) QueryUtil.list(q,
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
     * Removes all the ontology term entities from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (OntologyTermEntity ontologyTermEntity : findAll()) {
            remove(ontologyTermEntity);
        }
    }

    /**
     * Returns the number of ontology term entities.
     *
     * @return the number of ontology term entities
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

                Query q = session.createQuery(_SQL_COUNT_ONTOLOGYTERMENTITY);

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
     * Initializes the ontology term entity persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.OntologyTermEntity")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<OntologyTermEntity>> listenersList = new ArrayList<ModelListener<OntologyTermEntity>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<OntologyTermEntity>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(OntologyTermEntityImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
