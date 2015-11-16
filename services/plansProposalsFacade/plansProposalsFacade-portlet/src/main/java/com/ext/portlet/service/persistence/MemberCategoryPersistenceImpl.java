package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMemberCategoryException;
import com.ext.portlet.model.MemberCategory;
import com.ext.portlet.model.impl.MemberCategoryImpl;
import com.ext.portlet.model.impl.MemberCategoryModelImpl;
import com.ext.portlet.service.persistence.MemberCategoryPersistence;

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
 * The persistence implementation for the member category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberCategoryPersistence
 * @see MemberCategoryUtil
 * @generated
 */
public class MemberCategoryPersistenceImpl extends BasePersistenceImpl<MemberCategory>
    implements MemberCategoryPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MemberCategoryUtil} to access the member category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MemberCategoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED,
            MemberCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED,
            MemberCategoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_DISPLAYNAME = new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED,
            MemberCategoryImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchBydisplayName", new String[] { String.class.getName() },
            MemberCategoryModelImpl.DISPLAYNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_DISPLAYNAME = new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBydisplayName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_DISPLAYNAME_DISPLAYNAME_1 = "memberCategory.displayName IS NULL";
    private static final String _FINDER_COLUMN_DISPLAYNAME_DISPLAYNAME_2 = "memberCategory.displayName = ?";
    private static final String _FINDER_COLUMN_DISPLAYNAME_DISPLAYNAME_3 = "(memberCategory.displayName IS NULL OR memberCategory.displayName = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SHOWINLIST =
        new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED,
            MemberCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByshowInList",
            new String[] {
                Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHOWINLIST =
        new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED,
            MemberCategoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByshowInList",
            new String[] { Boolean.class.getName() },
            MemberCategoryModelImpl.SHOWINLIST_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SHOWINLIST = new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByshowInList",
            new String[] { Boolean.class.getName() });
    private static final String _FINDER_COLUMN_SHOWINLIST_SHOWINLIST_2 = "memberCategory.showInList = ?";
    private static final String _SQL_SELECT_MEMBERCATEGORY = "SELECT memberCategory FROM MemberCategory memberCategory";
    private static final String _SQL_SELECT_MEMBERCATEGORY_WHERE = "SELECT memberCategory FROM MemberCategory memberCategory WHERE ";
    private static final String _SQL_COUNT_MEMBERCATEGORY = "SELECT COUNT(memberCategory) FROM MemberCategory memberCategory";
    private static final String _SQL_COUNT_MEMBERCATEGORY_WHERE = "SELECT COUNT(memberCategory) FROM MemberCategory memberCategory WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "memberCategory.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MemberCategory exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MemberCategory exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MemberCategoryPersistenceImpl.class);
    private static MemberCategory _nullMemberCategory = new MemberCategoryImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MemberCategory> toCacheModel() {
                return _nullMemberCategoryCacheModel;
            }
        };

    private static CacheModel<MemberCategory> _nullMemberCategoryCacheModel = new CacheModel<MemberCategory>() {
            @Override
            public MemberCategory toEntityModel() {
                return _nullMemberCategory;
            }
        };

    public MemberCategoryPersistenceImpl() {
        setModelClass(MemberCategory.class);
    }

    /**
     * Returns the member category where displayName = &#63; or throws a {@link com.ext.portlet.NoSuchMemberCategoryException} if it could not be found.
     *
     * @param displayName the display name
     * @return the matching member category
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a matching member category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory findBydisplayName(String displayName)
        throws NoSuchMemberCategoryException, SystemException {
        MemberCategory memberCategory = fetchBydisplayName(displayName);

        if (memberCategory == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("displayName=");
            msg.append(displayName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMemberCategoryException(msg.toString());
        }

        return memberCategory;
    }

    /**
     * Returns the member category where displayName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param displayName the display name
     * @return the matching member category, or <code>null</code> if a matching member category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory fetchBydisplayName(String displayName)
        throws SystemException {
        return fetchBydisplayName(displayName, true);
    }

    /**
     * Returns the member category where displayName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param displayName the display name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching member category, or <code>null</code> if a matching member category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory fetchBydisplayName(String displayName,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { displayName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DISPLAYNAME,
                    finderArgs, this);
        }

        if (result instanceof MemberCategory) {
            MemberCategory memberCategory = (MemberCategory) result;

            if (!Validator.equals(displayName, memberCategory.getDisplayName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_MEMBERCATEGORY_WHERE);

            boolean bindDisplayName = false;

            if (displayName == null) {
                query.append(_FINDER_COLUMN_DISPLAYNAME_DISPLAYNAME_1);
            } else if (displayName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_DISPLAYNAME_DISPLAYNAME_3);
            } else {
                bindDisplayName = true;

                query.append(_FINDER_COLUMN_DISPLAYNAME_DISPLAYNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindDisplayName) {
                    qPos.add(displayName);
                }

                List<MemberCategory> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DISPLAYNAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "MemberCategoryPersistenceImpl.fetchBydisplayName(String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    MemberCategory memberCategory = list.get(0);

                    result = memberCategory;

                    cacheResult(memberCategory);

                    if ((memberCategory.getDisplayName() == null) ||
                            !memberCategory.getDisplayName().equals(displayName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DISPLAYNAME,
                            finderArgs, memberCategory);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DISPLAYNAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (MemberCategory) result;
        }
    }

    /**
     * Removes the member category where displayName = &#63; from the database.
     *
     * @param displayName the display name
     * @return the member category that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory removeBydisplayName(String displayName)
        throws NoSuchMemberCategoryException, SystemException {
        MemberCategory memberCategory = findBydisplayName(displayName);

        return remove(memberCategory);
    }

    /**
     * Returns the number of member categories where displayName = &#63;.
     *
     * @param displayName the display name
     * @return the number of matching member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBydisplayName(String displayName) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_DISPLAYNAME;

        Object[] finderArgs = new Object[] { displayName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MEMBERCATEGORY_WHERE);

            boolean bindDisplayName = false;

            if (displayName == null) {
                query.append(_FINDER_COLUMN_DISPLAYNAME_DISPLAYNAME_1);
            } else if (displayName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_DISPLAYNAME_DISPLAYNAME_3);
            } else {
                bindDisplayName = true;

                query.append(_FINDER_COLUMN_DISPLAYNAME_DISPLAYNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindDisplayName) {
                    qPos.add(displayName);
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
     * Returns all the member categories where showInList = &#63;.
     *
     * @param showInList the show in list
     * @return the matching member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MemberCategory> findByshowInList(boolean showInList)
        throws SystemException {
        return findByshowInList(showInList, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the member categories where showInList = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param showInList the show in list
     * @param start the lower bound of the range of member categories
     * @param end the upper bound of the range of member categories (not inclusive)
     * @return the range of matching member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MemberCategory> findByshowInList(boolean showInList, int start,
        int end) throws SystemException {
        return findByshowInList(showInList, start, end, null);
    }

    /**
     * Returns an ordered range of all the member categories where showInList = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param showInList the show in list
     * @param start the lower bound of the range of member categories
     * @param end the upper bound of the range of member categories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MemberCategory> findByshowInList(boolean showInList, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHOWINLIST;
            finderArgs = new Object[] { showInList };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SHOWINLIST;
            finderArgs = new Object[] { showInList, start, end, orderByComparator };
        }

        List<MemberCategory> list = (List<MemberCategory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (MemberCategory memberCategory : list) {
                if ((showInList != memberCategory.getShowInList())) {
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

            query.append(_SQL_SELECT_MEMBERCATEGORY_WHERE);

            query.append(_FINDER_COLUMN_SHOWINLIST_SHOWINLIST_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(MemberCategoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(showInList);

                if (!pagination) {
                    list = (List<MemberCategory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MemberCategory>(list);
                } else {
                    list = (List<MemberCategory>) QueryUtil.list(q,
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
     * Returns the first member category in the ordered set where showInList = &#63;.
     *
     * @param showInList the show in list
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching member category
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a matching member category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory findByshowInList_First(boolean showInList,
        OrderByComparator orderByComparator)
        throws NoSuchMemberCategoryException, SystemException {
        MemberCategory memberCategory = fetchByshowInList_First(showInList,
                orderByComparator);

        if (memberCategory != null) {
            return memberCategory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("showInList=");
        msg.append(showInList);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMemberCategoryException(msg.toString());
    }

    /**
     * Returns the first member category in the ordered set where showInList = &#63;.
     *
     * @param showInList the show in list
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching member category, or <code>null</code> if a matching member category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory fetchByshowInList_First(boolean showInList,
        OrderByComparator orderByComparator) throws SystemException {
        List<MemberCategory> list = findByshowInList(showInList, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last member category in the ordered set where showInList = &#63;.
     *
     * @param showInList the show in list
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching member category
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a matching member category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory findByshowInList_Last(boolean showInList,
        OrderByComparator orderByComparator)
        throws NoSuchMemberCategoryException, SystemException {
        MemberCategory memberCategory = fetchByshowInList_Last(showInList,
                orderByComparator);

        if (memberCategory != null) {
            return memberCategory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("showInList=");
        msg.append(showInList);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMemberCategoryException(msg.toString());
    }

    /**
     * Returns the last member category in the ordered set where showInList = &#63;.
     *
     * @param showInList the show in list
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching member category, or <code>null</code> if a matching member category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory fetchByshowInList_Last(boolean showInList,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByshowInList(showInList);

        if (count == 0) {
            return null;
        }

        List<MemberCategory> list = findByshowInList(showInList, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the member categories before and after the current member category in the ordered set where showInList = &#63;.
     *
     * @param roleId the primary key of the current member category
     * @param showInList the show in list
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next member category
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory[] findByshowInList_PrevAndNext(long roleId,
        boolean showInList, OrderByComparator orderByComparator)
        throws NoSuchMemberCategoryException, SystemException {
        MemberCategory memberCategory = findByPrimaryKey(roleId);

        Session session = null;

        try {
            session = openSession();

            MemberCategory[] array = new MemberCategoryImpl[3];

            array[0] = getByshowInList_PrevAndNext(session, memberCategory,
                    showInList, orderByComparator, true);

            array[1] = memberCategory;

            array[2] = getByshowInList_PrevAndNext(session, memberCategory,
                    showInList, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected MemberCategory getByshowInList_PrevAndNext(Session session,
        MemberCategory memberCategory, boolean showInList,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MEMBERCATEGORY_WHERE);

        query.append(_FINDER_COLUMN_SHOWINLIST_SHOWINLIST_2);

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
            query.append(MemberCategoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(showInList);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(memberCategory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<MemberCategory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the member categories where showInList = &#63; from the database.
     *
     * @param showInList the show in list
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByshowInList(boolean showInList)
        throws SystemException {
        for (MemberCategory memberCategory : findByshowInList(showInList,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(memberCategory);
        }
    }

    /**
     * Returns the number of member categories where showInList = &#63;.
     *
     * @param showInList the show in list
     * @return the number of matching member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByshowInList(boolean showInList) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SHOWINLIST;

        Object[] finderArgs = new Object[] { showInList };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MEMBERCATEGORY_WHERE);

            query.append(_FINDER_COLUMN_SHOWINLIST_SHOWINLIST_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(showInList);

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
     * Caches the member category in the entity cache if it is enabled.
     *
     * @param memberCategory the member category
     */
    @Override
    public void cacheResult(MemberCategory memberCategory) {
        EntityCacheUtil.putResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryImpl.class, memberCategory.getPrimaryKey(),
            memberCategory);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DISPLAYNAME,
            new Object[] { memberCategory.getDisplayName() }, memberCategory);

        memberCategory.resetOriginalValues();
    }

    /**
     * Caches the member categories in the entity cache if it is enabled.
     *
     * @param memberCategories the member categories
     */
    @Override
    public void cacheResult(List<MemberCategory> memberCategories) {
        for (MemberCategory memberCategory : memberCategories) {
            if (EntityCacheUtil.getResult(
                        MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        MemberCategoryImpl.class, memberCategory.getPrimaryKey()) == null) {
                cacheResult(memberCategory);
            } else {
                memberCategory.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all member categories.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MemberCategoryImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MemberCategoryImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the member category.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MemberCategory memberCategory) {
        EntityCacheUtil.removeResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryImpl.class, memberCategory.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(memberCategory);
    }

    @Override
    public void clearCache(List<MemberCategory> memberCategories) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MemberCategory memberCategory : memberCategories) {
            EntityCacheUtil.removeResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
                MemberCategoryImpl.class, memberCategory.getPrimaryKey());

            clearUniqueFindersCache(memberCategory);
        }
    }

    protected void cacheUniqueFindersCache(MemberCategory memberCategory) {
        if (memberCategory.isNew()) {
            Object[] args = new Object[] { memberCategory.getDisplayName() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DISPLAYNAME, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DISPLAYNAME, args,
                memberCategory);
        } else {
            MemberCategoryModelImpl memberCategoryModelImpl = (MemberCategoryModelImpl) memberCategory;

            if ((memberCategoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_DISPLAYNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { memberCategory.getDisplayName() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DISPLAYNAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DISPLAYNAME,
                    args, memberCategory);
            }
        }
    }

    protected void clearUniqueFindersCache(MemberCategory memberCategory) {
        MemberCategoryModelImpl memberCategoryModelImpl = (MemberCategoryModelImpl) memberCategory;

        Object[] args = new Object[] { memberCategory.getDisplayName() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DISPLAYNAME, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DISPLAYNAME, args);

        if ((memberCategoryModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_DISPLAYNAME.getColumnBitmask()) != 0) {
            args = new Object[] { memberCategoryModelImpl.getOriginalDisplayName() };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DISPLAYNAME, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DISPLAYNAME, args);
        }
    }

    /**
     * Creates a new member category with the primary key. Does not add the member category to the database.
     *
     * @param roleId the primary key for the new member category
     * @return the new member category
     */
    @Override
    public MemberCategory create(long roleId) {
        MemberCategory memberCategory = new MemberCategoryImpl();

        memberCategory.setNew(true);
        memberCategory.setPrimaryKey(roleId);

        return memberCategory;
    }

    /**
     * Removes the member category with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param roleId the primary key of the member category
     * @return the member category that was removed
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory remove(long roleId)
        throws NoSuchMemberCategoryException, SystemException {
        return remove((Serializable) roleId);
    }

    /**
     * Removes the member category with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the member category
     * @return the member category that was removed
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory remove(Serializable primaryKey)
        throws NoSuchMemberCategoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MemberCategory memberCategory = (MemberCategory) session.get(MemberCategoryImpl.class,
                    primaryKey);

            if (memberCategory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMemberCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(memberCategory);
        } catch (NoSuchMemberCategoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MemberCategory removeImpl(MemberCategory memberCategory)
        throws SystemException {
        memberCategory = toUnwrappedModel(memberCategory);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(memberCategory)) {
                memberCategory = (MemberCategory) session.get(MemberCategoryImpl.class,
                        memberCategory.getPrimaryKeyObj());
            }

            if (memberCategory != null) {
                session.delete(memberCategory);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (memberCategory != null) {
            clearCache(memberCategory);
        }

        return memberCategory;
    }

    @Override
    public MemberCategory updateImpl(
        com.ext.portlet.model.MemberCategory memberCategory)
        throws SystemException {
        memberCategory = toUnwrappedModel(memberCategory);

        boolean isNew = memberCategory.isNew();

        MemberCategoryModelImpl memberCategoryModelImpl = (MemberCategoryModelImpl) memberCategory;

        Session session = null;

        try {
            session = openSession();

            if (memberCategory.isNew()) {
                session.save(memberCategory);

                memberCategory.setNew(false);
            } else {
                session.merge(memberCategory);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !MemberCategoryModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((memberCategoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHOWINLIST.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        memberCategoryModelImpl.getOriginalShowInList()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SHOWINLIST,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHOWINLIST,
                    args);

                args = new Object[] { memberCategoryModelImpl.getShowInList() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SHOWINLIST,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SHOWINLIST,
                    args);
            }
        }

        EntityCacheUtil.putResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryImpl.class, memberCategory.getPrimaryKey(),
            memberCategory);

        clearUniqueFindersCache(memberCategory);
        cacheUniqueFindersCache(memberCategory);

        return memberCategory;
    }

    protected MemberCategory toUnwrappedModel(MemberCategory memberCategory) {
        if (memberCategory instanceof MemberCategoryImpl) {
            return memberCategory;
        }

        MemberCategoryImpl memberCategoryImpl = new MemberCategoryImpl();

        memberCategoryImpl.setNew(memberCategory.isNew());
        memberCategoryImpl.setPrimaryKey(memberCategory.getPrimaryKey());

        memberCategoryImpl.setRoleId(memberCategory.getRoleId());
        memberCategoryImpl.setDisplayName(memberCategory.getDisplayName());
        memberCategoryImpl.setCategoryName(memberCategory.getCategoryName());
        memberCategoryImpl.setSortOrder(memberCategory.getSortOrder());
        memberCategoryImpl.setShowInList(memberCategory.isShowInList());
        memberCategoryImpl.setImageName(memberCategory.getImageName());

        return memberCategoryImpl;
    }

    /**
     * Returns the member category with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the member category
     * @return the member category
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMemberCategoryException, SystemException {
        MemberCategory memberCategory = fetchByPrimaryKey(primaryKey);

        if (memberCategory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMemberCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return memberCategory;
    }

    /**
     * Returns the member category with the primary key or throws a {@link com.ext.portlet.NoSuchMemberCategoryException} if it could not be found.
     *
     * @param roleId the primary key of the member category
     * @return the member category
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory findByPrimaryKey(long roleId)
        throws NoSuchMemberCategoryException, SystemException {
        return findByPrimaryKey((Serializable) roleId);
    }

    /**
     * Returns the member category with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the member category
     * @return the member category, or <code>null</code> if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MemberCategory memberCategory = (MemberCategory) EntityCacheUtil.getResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
                MemberCategoryImpl.class, primaryKey);

        if (memberCategory == _nullMemberCategory) {
            return null;
        }

        if (memberCategory == null) {
            Session session = null;

            try {
                session = openSession();

                memberCategory = (MemberCategory) session.get(MemberCategoryImpl.class,
                        primaryKey);

                if (memberCategory != null) {
                    cacheResult(memberCategory);
                } else {
                    EntityCacheUtil.putResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        MemberCategoryImpl.class, primaryKey,
                        _nullMemberCategory);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
                    MemberCategoryImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return memberCategory;
    }

    /**
     * Returns the member category with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param roleId the primary key of the member category
     * @return the member category, or <code>null</code> if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory fetchByPrimaryKey(long roleId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) roleId);
    }

    /**
     * Returns all the member categories.
     *
     * @return the member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MemberCategory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the member categories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of member categories
     * @param end the upper bound of the range of member categories (not inclusive)
     * @return the range of member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MemberCategory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the member categories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of member categories
     * @param end the upper bound of the range of member categories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MemberCategory> findAll(int start, int end,
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

        List<MemberCategory> list = (List<MemberCategory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MEMBERCATEGORY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MEMBERCATEGORY;

                if (pagination) {
                    sql = sql.concat(MemberCategoryModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MemberCategory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MemberCategory>(list);
                } else {
                    list = (List<MemberCategory>) QueryUtil.list(q,
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
     * Removes all the member categories from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MemberCategory memberCategory : findAll()) {
            remove(memberCategory);
        }
    }

    /**
     * Returns the number of member categories.
     *
     * @return the number of member categories
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

                Query q = session.createQuery(_SQL_COUNT_MEMBERCATEGORY);

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
     * Initializes the member category persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MemberCategory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MemberCategory>> listenersList = new ArrayList<ModelListener<MemberCategory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MemberCategory>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MemberCategoryImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
