package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.impl.ProposalContestPhaseAttributeImpl;
import com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl;
import com.ext.portlet.service.persistence.ProposalContestPhaseAttributePersistence;

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
 * The persistence implementation for the proposal contest phase attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributePersistence
 * @see ProposalContestPhaseAttributeUtil
 * @generated
 */
public class ProposalContestPhaseAttributePersistenceImpl
    extends BasePersistenceImpl<ProposalContestPhaseAttribute>
    implements ProposalContestPhaseAttributePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalContestPhaseAttributeUtil} to access the proposal contest phase attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalContestPhaseAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByProposalIdContestPhaseIdNameAdditionalId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                String.class.getName(), Long.class.getName()
            },
            ProposalContestPhaseAttributeModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalContestPhaseAttributeModelImpl.CONTESTPHASEID_COLUMN_BITMASK |
            ProposalContestPhaseAttributeModelImpl.NAME_COLUMN_BITMASK |
            ProposalContestPhaseAttributeModelImpl.ADDITIONALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalIdContestPhaseIdNameAdditionalId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                String.class.getName(), Long.class.getName()
            });
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_PROPOSALID_2 =
        "proposalContestPhaseAttribute.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_CONTESTPHASEID_2 =
        "proposalContestPhaseAttribute.contestPhaseId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_1 =
        "proposalContestPhaseAttribute.name IS NULL AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_2 =
        "proposalContestPhaseAttribute.name = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_3 =
        "(proposalContestPhaseAttribute.name IS NULL OR proposalContestPhaseAttribute.name = '') AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_ADDITIONALID_2 =
        "proposalContestPhaseAttribute.additionalId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByProposalIdContestPhaseId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByProposalIdContestPhaseId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ProposalContestPhaseAttributeModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalContestPhaseAttributeModelImpl.CONTESTPHASEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalIdContestPhaseId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2 =
        "proposalContestPhaseAttribute.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2 =
        "proposalContestPhaseAttribute.contestPhaseId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTPHASEID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestPhaseId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContestPhaseId",
            new String[] { Long.class.getName() },
            ProposalContestPhaseAttributeModelImpl.CONTESTPHASEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTPHASEID = new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByContestPhaseId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTPHASEID_CONTESTPHASEID_2 = "proposalContestPhaseAttribute.contestPhaseId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTPHASEIDANDPROPOSALID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByContestPhaseIdAndProposalId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEIDANDPROPOSALID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByContestPhaseIdAndProposalId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ProposalContestPhaseAttributeModelImpl.CONTESTPHASEID_COLUMN_BITMASK |
            ProposalContestPhaseAttributeModelImpl.PROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTPHASEIDANDPROPOSALID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByContestPhaseIdAndProposalId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTPHASEIDANDPROPOSALID_CONTESTPHASEID_2 =
        "proposalContestPhaseAttribute.contestPhaseId = ? AND ";
    private static final String _FINDER_COLUMN_CONTESTPHASEIDANDPROPOSALID_PROPOSALID_2 =
        "proposalContestPhaseAttribute.proposalId = ?";
    private static final String _SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE = "SELECT proposalContestPhaseAttribute FROM ProposalContestPhaseAttribute proposalContestPhaseAttribute";
    private static final String _SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE = "SELECT proposalContestPhaseAttribute FROM ProposalContestPhaseAttribute proposalContestPhaseAttribute WHERE ";
    private static final String _SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE = "SELECT COUNT(proposalContestPhaseAttribute) FROM ProposalContestPhaseAttribute proposalContestPhaseAttribute";
    private static final String _SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE = "SELECT COUNT(proposalContestPhaseAttribute) FROM ProposalContestPhaseAttribute proposalContestPhaseAttribute WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalContestPhaseAttribute.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalContestPhaseAttribute exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProposalContestPhaseAttribute exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalContestPhaseAttributePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ProposalContestPhaseAttribute _nullProposalContestPhaseAttribute =
        new ProposalContestPhaseAttributeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalContestPhaseAttribute> toCacheModel() {
                return _nullProposalContestPhaseAttributeCacheModel;
            }
        };

    private static CacheModel<ProposalContestPhaseAttribute> _nullProposalContestPhaseAttributeCacheModel =
        new CacheModel<ProposalContestPhaseAttribute>() {
            @Override
            public ProposalContestPhaseAttribute toEntityModel() {
                return _nullProposalContestPhaseAttribute;
            }
        };

    public ProposalContestPhaseAttributePersistenceImpl() {
        setModelClass(ProposalContestPhaseAttribute.class);
    }

    /**
     * Returns the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeException} if it could not be found.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param name the name
     * @param additionalId the additional ID
     * @return the matching proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute findByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, String name, long additionalId)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = fetchByProposalIdContestPhaseIdNameAdditionalId(proposalId,
                contestPhaseId, name, additionalId);

        if (proposalContestPhaseAttribute == null) {
            StringBundler msg = new StringBundler(10);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", contestPhaseId=");
            msg.append(contestPhaseId);

            msg.append(", name=");
            msg.append(name);

            msg.append(", additionalId=");
            msg.append(additionalId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchProposalContestPhaseAttributeException(msg.toString());
        }

        return proposalContestPhaseAttribute;
    }

    /**
     * Returns the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param name the name
     * @param additionalId the additional ID
     * @return the matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, String name, long additionalId)
        throws SystemException {
        return fetchByProposalIdContestPhaseIdNameAdditionalId(proposalId,
            contestPhaseId, name, additionalId, true);
    }

    /**
     * Returns the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param name the name
     * @param additionalId the additional ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, String name, long additionalId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] {
                proposalId, contestPhaseId, name, additionalId
            };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                    finderArgs, this);
        }

        if (result instanceof ProposalContestPhaseAttribute) {
            ProposalContestPhaseAttribute proposalContestPhaseAttribute = (ProposalContestPhaseAttribute) result;

            if ((proposalId != proposalContestPhaseAttribute.getProposalId()) ||
                    (contestPhaseId != proposalContestPhaseAttribute.getContestPhaseId()) ||
                    !Validator.equals(name,
                        proposalContestPhaseAttribute.getName()) ||
                    (additionalId != proposalContestPhaseAttribute.getAdditionalId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(6);

            query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_CONTESTPHASEID_2);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_2);
            }

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_ADDITIONALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(contestPhaseId);

                if (bindName) {
                    qPos.add(name);
                }

                qPos.add(additionalId);

                List<ProposalContestPhaseAttribute> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ProposalContestPhaseAttributePersistenceImpl.fetchByProposalIdContestPhaseIdNameAdditionalId(long, long, String, long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ProposalContestPhaseAttribute proposalContestPhaseAttribute = list.get(0);

                    result = proposalContestPhaseAttribute;

                    cacheResult(proposalContestPhaseAttribute);

                    if ((proposalContestPhaseAttribute.getProposalId() != proposalId) ||
                            (proposalContestPhaseAttribute.getContestPhaseId() != contestPhaseId) ||
                            (proposalContestPhaseAttribute.getName() == null) ||
                            !proposalContestPhaseAttribute.getName().equals(name) ||
                            (proposalContestPhaseAttribute.getAdditionalId() != additionalId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                            finderArgs, proposalContestPhaseAttribute);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ProposalContestPhaseAttribute) result;
        }
    }

    /**
     * Removes the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param name the name
     * @param additionalId the additional ID
     * @return the proposal contest phase attribute that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute removeByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, String name, long additionalId)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = findByProposalIdContestPhaseIdNameAdditionalId(proposalId,
                contestPhaseId, name, additionalId);

        return remove(proposalContestPhaseAttribute);
    }

    /**
     * Returns the number of proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param name the name
     * @param additionalId the additional ID
     * @return the number of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, String name, long additionalId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID;

        Object[] finderArgs = new Object[] {
                proposalId, contestPhaseId, name, additionalId
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_CONTESTPHASEID_2);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_2);
            }

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_ADDITIONALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(contestPhaseId);

                if (bindName) {
                    qPos.add(name);
                }

                qPos.add(additionalId);

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
     * Returns all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @return the matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId) throws SystemException {
        return findByProposalIdContestPhaseId(proposalId, contestPhaseId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @return the range of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId, int start, int end)
        throws SystemException {
        return findByProposalIdContestPhaseId(proposalId, contestPhaseId,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID;
            finderArgs = new Object[] { proposalId, contestPhaseId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID;
            finderArgs = new Object[] {
                    proposalId, contestPhaseId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalContestPhaseAttribute> list = (List<ProposalContestPhaseAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : list) {
                if ((proposalId != proposalContestPhaseAttribute.getProposalId()) ||
                        (contestPhaseId != proposalContestPhaseAttribute.getContestPhaseId())) {
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

            query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalContestPhaseAttributeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(contestPhaseId);

                if (!pagination) {
                    list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalContestPhaseAttribute>(list);
                } else {
                    list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
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
     * Returns the first proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute findByProposalIdContestPhaseId_First(
        long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = fetchByProposalIdContestPhaseId_First(proposalId,
                contestPhaseId, orderByComparator);

        if (proposalContestPhaseAttribute != null) {
            return proposalContestPhaseAttribute;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(", contestPhaseId=");
        msg.append(contestPhaseId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalContestPhaseAttributeException(msg.toString());
    }

    /**
     * Returns the first proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByProposalIdContestPhaseId_First(
        long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ProposalContestPhaseAttribute> list = findByProposalIdContestPhaseId(proposalId,
                contestPhaseId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute findByProposalIdContestPhaseId_Last(
        long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = fetchByProposalIdContestPhaseId_Last(proposalId,
                contestPhaseId, orderByComparator);

        if (proposalContestPhaseAttribute != null) {
            return proposalContestPhaseAttribute;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(", contestPhaseId=");
        msg.append(contestPhaseId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalContestPhaseAttributeException(msg.toString());
    }

    /**
     * Returns the last proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByProposalIdContestPhaseId_Last(
        long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByProposalIdContestPhaseId(proposalId, contestPhaseId);

        if (count == 0) {
            return null;
        }

        List<ProposalContestPhaseAttribute> list = findByProposalIdContestPhaseId(proposalId,
                contestPhaseId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal contest phase attributes before and after the current proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param id the primary key of the current proposal contest phase attribute
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute[] findByProposalIdContestPhaseId_PrevAndNext(
        long id, long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ProposalContestPhaseAttribute[] array = new ProposalContestPhaseAttributeImpl[3];

            array[0] = getByProposalIdContestPhaseId_PrevAndNext(session,
                    proposalContestPhaseAttribute, proposalId, contestPhaseId,
                    orderByComparator, true);

            array[1] = proposalContestPhaseAttribute;

            array[2] = getByProposalIdContestPhaseId_PrevAndNext(session,
                    proposalContestPhaseAttribute, proposalId, contestPhaseId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalContestPhaseAttribute getByProposalIdContestPhaseId_PrevAndNext(
        Session session,
        ProposalContestPhaseAttribute proposalContestPhaseAttribute,
        long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2);

        query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2);

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
            query.append(ProposalContestPhaseAttributeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        qPos.add(contestPhaseId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalContestPhaseAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalContestPhaseAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId) throws SystemException {
        for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : findByProposalIdContestPhaseId(
                proposalId, contestPhaseId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(proposalContestPhaseAttribute);
        }
    }

    /**
     * Returns the number of proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @return the number of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID;

        Object[] finderArgs = new Object[] { proposalId, contestPhaseId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(contestPhaseId);

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
     * Returns all the proposal contest phase attributes where contestPhaseId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @return the matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findByContestPhaseId(
        long contestPhaseId) throws SystemException {
        return findByContestPhaseId(contestPhaseId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal contest phase attributes where contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestPhaseId the contest phase ID
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @return the range of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findByContestPhaseId(
        long contestPhaseId, int start, int end) throws SystemException {
        return findByContestPhaseId(contestPhaseId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal contest phase attributes where contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestPhaseId the contest phase ID
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findByContestPhaseId(
        long contestPhaseId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEID;
            finderArgs = new Object[] { contestPhaseId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTPHASEID;
            finderArgs = new Object[] {
                    contestPhaseId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalContestPhaseAttribute> list = (List<ProposalContestPhaseAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : list) {
                if ((contestPhaseId != proposalContestPhaseAttribute.getContestPhaseId())) {
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

            query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTPHASEID_CONTESTPHASEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalContestPhaseAttributeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestPhaseId);

                if (!pagination) {
                    list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalContestPhaseAttribute>(list);
                } else {
                    list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
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
     * Returns the first proposal contest phase attribute in the ordered set where contestPhaseId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute findByContestPhaseId_First(
        long contestPhaseId, OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = fetchByContestPhaseId_First(contestPhaseId,
                orderByComparator);

        if (proposalContestPhaseAttribute != null) {
            return proposalContestPhaseAttribute;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestPhaseId=");
        msg.append(contestPhaseId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalContestPhaseAttributeException(msg.toString());
    }

    /**
     * Returns the first proposal contest phase attribute in the ordered set where contestPhaseId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByContestPhaseId_First(
        long contestPhaseId, OrderByComparator orderByComparator)
        throws SystemException {
        List<ProposalContestPhaseAttribute> list = findByContestPhaseId(contestPhaseId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal contest phase attribute in the ordered set where contestPhaseId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute findByContestPhaseId_Last(
        long contestPhaseId, OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = fetchByContestPhaseId_Last(contestPhaseId,
                orderByComparator);

        if (proposalContestPhaseAttribute != null) {
            return proposalContestPhaseAttribute;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestPhaseId=");
        msg.append(contestPhaseId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalContestPhaseAttributeException(msg.toString());
    }

    /**
     * Returns the last proposal contest phase attribute in the ordered set where contestPhaseId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByContestPhaseId_Last(
        long contestPhaseId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByContestPhaseId(contestPhaseId);

        if (count == 0) {
            return null;
        }

        List<ProposalContestPhaseAttribute> list = findByContestPhaseId(contestPhaseId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal contest phase attributes before and after the current proposal contest phase attribute in the ordered set where contestPhaseId = &#63;.
     *
     * @param id the primary key of the current proposal contest phase attribute
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute[] findByContestPhaseId_PrevAndNext(
        long id, long contestPhaseId, OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ProposalContestPhaseAttribute[] array = new ProposalContestPhaseAttributeImpl[3];

            array[0] = getByContestPhaseId_PrevAndNext(session,
                    proposalContestPhaseAttribute, contestPhaseId,
                    orderByComparator, true);

            array[1] = proposalContestPhaseAttribute;

            array[2] = getByContestPhaseId_PrevAndNext(session,
                    proposalContestPhaseAttribute, contestPhaseId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalContestPhaseAttribute getByContestPhaseId_PrevAndNext(
        Session session,
        ProposalContestPhaseAttribute proposalContestPhaseAttribute,
        long contestPhaseId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

        query.append(_FINDER_COLUMN_CONTESTPHASEID_CONTESTPHASEID_2);

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
            query.append(ProposalContestPhaseAttributeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestPhaseId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalContestPhaseAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalContestPhaseAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal contest phase attributes where contestPhaseId = &#63; from the database.
     *
     * @param contestPhaseId the contest phase ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestPhaseId(long contestPhaseId)
        throws SystemException {
        for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : findByContestPhaseId(
                contestPhaseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalContestPhaseAttribute);
        }
    }

    /**
     * Returns the number of proposal contest phase attributes where contestPhaseId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @return the number of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestPhaseId(long contestPhaseId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTPHASEID;

        Object[] finderArgs = new Object[] { contestPhaseId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTPHASEID_CONTESTPHASEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestPhaseId);

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
     * Returns all the proposal contest phase attributes where contestPhaseId = &#63; and proposalId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param proposalId the proposal ID
     * @return the matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findByContestPhaseIdAndProposalId(
        long contestPhaseId, long proposalId) throws SystemException {
        return findByContestPhaseIdAndProposalId(contestPhaseId, proposalId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal contest phase attributes where contestPhaseId = &#63; and proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestPhaseId the contest phase ID
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @return the range of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findByContestPhaseIdAndProposalId(
        long contestPhaseId, long proposalId, int start, int end)
        throws SystemException {
        return findByContestPhaseIdAndProposalId(contestPhaseId, proposalId,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal contest phase attributes where contestPhaseId = &#63; and proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestPhaseId the contest phase ID
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findByContestPhaseIdAndProposalId(
        long contestPhaseId, long proposalId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEIDANDPROPOSALID;
            finderArgs = new Object[] { contestPhaseId, proposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTPHASEIDANDPROPOSALID;
            finderArgs = new Object[] {
                    contestPhaseId, proposalId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalContestPhaseAttribute> list = (List<ProposalContestPhaseAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : list) {
                if ((contestPhaseId != proposalContestPhaseAttribute.getContestPhaseId()) ||
                        (proposalId != proposalContestPhaseAttribute.getProposalId())) {
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

            query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTPHASEIDANDPROPOSALID_CONTESTPHASEID_2);

            query.append(_FINDER_COLUMN_CONTESTPHASEIDANDPROPOSALID_PROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalContestPhaseAttributeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestPhaseId);

                qPos.add(proposalId);

                if (!pagination) {
                    list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalContestPhaseAttribute>(list);
                } else {
                    list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
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
     * Returns the first proposal contest phase attribute in the ordered set where contestPhaseId = &#63; and proposalId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute findByContestPhaseIdAndProposalId_First(
        long contestPhaseId, long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = fetchByContestPhaseIdAndProposalId_First(contestPhaseId,
                proposalId, orderByComparator);

        if (proposalContestPhaseAttribute != null) {
            return proposalContestPhaseAttribute;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestPhaseId=");
        msg.append(contestPhaseId);

        msg.append(", proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalContestPhaseAttributeException(msg.toString());
    }

    /**
     * Returns the first proposal contest phase attribute in the ordered set where contestPhaseId = &#63; and proposalId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByContestPhaseIdAndProposalId_First(
        long contestPhaseId, long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ProposalContestPhaseAttribute> list = findByContestPhaseIdAndProposalId(contestPhaseId,
                proposalId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal contest phase attribute in the ordered set where contestPhaseId = &#63; and proposalId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute findByContestPhaseIdAndProposalId_Last(
        long contestPhaseId, long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = fetchByContestPhaseIdAndProposalId_Last(contestPhaseId,
                proposalId, orderByComparator);

        if (proposalContestPhaseAttribute != null) {
            return proposalContestPhaseAttribute;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestPhaseId=");
        msg.append(contestPhaseId);

        msg.append(", proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalContestPhaseAttributeException(msg.toString());
    }

    /**
     * Returns the last proposal contest phase attribute in the ordered set where contestPhaseId = &#63; and proposalId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByContestPhaseIdAndProposalId_Last(
        long contestPhaseId, long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContestPhaseIdAndProposalId(contestPhaseId,
                proposalId);

        if (count == 0) {
            return null;
        }

        List<ProposalContestPhaseAttribute> list = findByContestPhaseIdAndProposalId(contestPhaseId,
                proposalId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal contest phase attributes before and after the current proposal contest phase attribute in the ordered set where contestPhaseId = &#63; and proposalId = &#63;.
     *
     * @param id the primary key of the current proposal contest phase attribute
     * @param contestPhaseId the contest phase ID
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute[] findByContestPhaseIdAndProposalId_PrevAndNext(
        long id, long contestPhaseId, long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ProposalContestPhaseAttribute[] array = new ProposalContestPhaseAttributeImpl[3];

            array[0] = getByContestPhaseIdAndProposalId_PrevAndNext(session,
                    proposalContestPhaseAttribute, contestPhaseId, proposalId,
                    orderByComparator, true);

            array[1] = proposalContestPhaseAttribute;

            array[2] = getByContestPhaseIdAndProposalId_PrevAndNext(session,
                    proposalContestPhaseAttribute, contestPhaseId, proposalId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalContestPhaseAttribute getByContestPhaseIdAndProposalId_PrevAndNext(
        Session session,
        ProposalContestPhaseAttribute proposalContestPhaseAttribute,
        long contestPhaseId, long proposalId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

        query.append(_FINDER_COLUMN_CONTESTPHASEIDANDPROPOSALID_CONTESTPHASEID_2);

        query.append(_FINDER_COLUMN_CONTESTPHASEIDANDPROPOSALID_PROPOSALID_2);

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
            query.append(ProposalContestPhaseAttributeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestPhaseId);

        qPos.add(proposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalContestPhaseAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalContestPhaseAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal contest phase attributes where contestPhaseId = &#63; and proposalId = &#63; from the database.
     *
     * @param contestPhaseId the contest phase ID
     * @param proposalId the proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestPhaseIdAndProposalId(long contestPhaseId,
        long proposalId) throws SystemException {
        for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : findByContestPhaseIdAndProposalId(
                contestPhaseId, proposalId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(proposalContestPhaseAttribute);
        }
    }

    /**
     * Returns the number of proposal contest phase attributes where contestPhaseId = &#63; and proposalId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param proposalId the proposal ID
     * @return the number of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestPhaseIdAndProposalId(long contestPhaseId,
        long proposalId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTPHASEIDANDPROPOSALID;

        Object[] finderArgs = new Object[] { contestPhaseId, proposalId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTPHASEIDANDPROPOSALID_CONTESTPHASEID_2);

            query.append(_FINDER_COLUMN_CONTESTPHASEIDANDPROPOSALID_PROPOSALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestPhaseId);

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
     * Caches the proposal contest phase attribute in the entity cache if it is enabled.
     *
     * @param proposalContestPhaseAttribute the proposal contest phase attribute
     */
    @Override
    public void cacheResult(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        EntityCacheUtil.putResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            proposalContestPhaseAttribute.getPrimaryKey(),
            proposalContestPhaseAttribute);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
            new Object[] {
                proposalContestPhaseAttribute.getProposalId(),
                proposalContestPhaseAttribute.getContestPhaseId(),
                proposalContestPhaseAttribute.getName(),
                proposalContestPhaseAttribute.getAdditionalId()
            }, proposalContestPhaseAttribute);

        proposalContestPhaseAttribute.resetOriginalValues();
    }

    /**
     * Caches the proposal contest phase attributes in the entity cache if it is enabled.
     *
     * @param proposalContestPhaseAttributes the proposal contest phase attributes
     */
    @Override
    public void cacheResult(
        List<ProposalContestPhaseAttribute> proposalContestPhaseAttributes) {
        for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : proposalContestPhaseAttributes) {
            if (EntityCacheUtil.getResult(
                        ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalContestPhaseAttributeImpl.class,
                        proposalContestPhaseAttribute.getPrimaryKey()) == null) {
                cacheResult(proposalContestPhaseAttribute);
            } else {
                proposalContestPhaseAttribute.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal contest phase attributes.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalContestPhaseAttributeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalContestPhaseAttributeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal contest phase attribute.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        EntityCacheUtil.removeResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            proposalContestPhaseAttribute.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(proposalContestPhaseAttribute);
    }

    @Override
    public void clearCache(
        List<ProposalContestPhaseAttribute> proposalContestPhaseAttributes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : proposalContestPhaseAttributes) {
            EntityCacheUtil.removeResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalContestPhaseAttributeImpl.class,
                proposalContestPhaseAttribute.getPrimaryKey());

            clearUniqueFindersCache(proposalContestPhaseAttribute);
        }
    }

    protected void cacheUniqueFindersCache(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        if (proposalContestPhaseAttribute.isNew()) {
            Object[] args = new Object[] {
                    proposalContestPhaseAttribute.getProposalId(),
                    proposalContestPhaseAttribute.getContestPhaseId(),
                    proposalContestPhaseAttribute.getName(),
                    proposalContestPhaseAttribute.getAdditionalId()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                args, proposalContestPhaseAttribute);
        } else {
            ProposalContestPhaseAttributeModelImpl proposalContestPhaseAttributeModelImpl =
                (ProposalContestPhaseAttributeModelImpl) proposalContestPhaseAttribute;

            if ((proposalContestPhaseAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalContestPhaseAttribute.getProposalId(),
                        proposalContestPhaseAttribute.getContestPhaseId(),
                        proposalContestPhaseAttribute.getName(),
                        proposalContestPhaseAttribute.getAdditionalId()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                    args, proposalContestPhaseAttribute);
            }
        }
    }

    protected void clearUniqueFindersCache(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        ProposalContestPhaseAttributeModelImpl proposalContestPhaseAttributeModelImpl =
            (ProposalContestPhaseAttributeModelImpl) proposalContestPhaseAttribute;

        Object[] args = new Object[] {
                proposalContestPhaseAttribute.getProposalId(),
                proposalContestPhaseAttribute.getContestPhaseId(),
                proposalContestPhaseAttribute.getName(),
                proposalContestPhaseAttribute.getAdditionalId()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
            args);

        if ((proposalContestPhaseAttributeModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID.getColumnBitmask()) != 0) {
            args = new Object[] {
                    proposalContestPhaseAttributeModelImpl.getOriginalProposalId(),
                    proposalContestPhaseAttributeModelImpl.getOriginalContestPhaseId(),
                    proposalContestPhaseAttributeModelImpl.getOriginalName(),
                    proposalContestPhaseAttributeModelImpl.getOriginalAdditionalId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                args);
        }
    }

    /**
     * Creates a new proposal contest phase attribute with the primary key. Does not add the proposal contest phase attribute to the database.
     *
     * @param id the primary key for the new proposal contest phase attribute
     * @return the new proposal contest phase attribute
     */
    @Override
    public ProposalContestPhaseAttribute create(long id) {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = new ProposalContestPhaseAttributeImpl();

        proposalContestPhaseAttribute.setNew(true);
        proposalContestPhaseAttribute.setPrimaryKey(id);

        return proposalContestPhaseAttribute;
    }

    /**
     * Removes the proposal contest phase attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute that was removed
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute remove(long id)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the proposal contest phase attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute that was removed
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute remove(Serializable primaryKey)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalContestPhaseAttribute proposalContestPhaseAttribute = (ProposalContestPhaseAttribute) session.get(ProposalContestPhaseAttributeImpl.class,
                    primaryKey);

            if (proposalContestPhaseAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalContestPhaseAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalContestPhaseAttribute);
        } catch (NoSuchProposalContestPhaseAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalContestPhaseAttribute removeImpl(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws SystemException {
        proposalContestPhaseAttribute = toUnwrappedModel(proposalContestPhaseAttribute);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposalContestPhaseAttribute)) {
                proposalContestPhaseAttribute = (ProposalContestPhaseAttribute) session.get(ProposalContestPhaseAttributeImpl.class,
                        proposalContestPhaseAttribute.getPrimaryKeyObj());
            }

            if (proposalContestPhaseAttribute != null) {
                session.delete(proposalContestPhaseAttribute);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposalContestPhaseAttribute != null) {
            clearCache(proposalContestPhaseAttribute);
        }

        return proposalContestPhaseAttribute;
    }

    @Override
    public ProposalContestPhaseAttribute updateImpl(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws SystemException {
        proposalContestPhaseAttribute = toUnwrappedModel(proposalContestPhaseAttribute);

        boolean isNew = proposalContestPhaseAttribute.isNew();

        ProposalContestPhaseAttributeModelImpl proposalContestPhaseAttributeModelImpl =
            (ProposalContestPhaseAttributeModelImpl) proposalContestPhaseAttribute;

        Session session = null;

        try {
            session = openSession();

            if (proposalContestPhaseAttribute.isNew()) {
                session.save(proposalContestPhaseAttribute);

                proposalContestPhaseAttribute.setNew(false);
            } else {
                session.merge(proposalContestPhaseAttribute);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew ||
                !ProposalContestPhaseAttributeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((proposalContestPhaseAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalContestPhaseAttributeModelImpl.getOriginalProposalId(),
                        proposalContestPhaseAttributeModelImpl.getOriginalContestPhaseId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID,
                    args);

                args = new Object[] {
                        proposalContestPhaseAttributeModelImpl.getProposalId(),
                        proposalContestPhaseAttributeModelImpl.getContestPhaseId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID,
                    args);
            }

            if ((proposalContestPhaseAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalContestPhaseAttributeModelImpl.getOriginalContestPhaseId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEID,
                    args);

                args = new Object[] {
                        proposalContestPhaseAttributeModelImpl.getContestPhaseId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEID,
                    args);
            }

            if ((proposalContestPhaseAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEIDANDPROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalContestPhaseAttributeModelImpl.getOriginalContestPhaseId(),
                        proposalContestPhaseAttributeModelImpl.getOriginalProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTPHASEIDANDPROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEIDANDPROPOSALID,
                    args);

                args = new Object[] {
                        proposalContestPhaseAttributeModelImpl.getContestPhaseId(),
                        proposalContestPhaseAttributeModelImpl.getProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTPHASEIDANDPROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEIDANDPROPOSALID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            proposalContestPhaseAttribute.getPrimaryKey(),
            proposalContestPhaseAttribute);

        clearUniqueFindersCache(proposalContestPhaseAttribute);
        cacheUniqueFindersCache(proposalContestPhaseAttribute);

        return proposalContestPhaseAttribute;
    }

    protected ProposalContestPhaseAttribute toUnwrappedModel(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        if (proposalContestPhaseAttribute instanceof ProposalContestPhaseAttributeImpl) {
            return proposalContestPhaseAttribute;
        }

        ProposalContestPhaseAttributeImpl proposalContestPhaseAttributeImpl = new ProposalContestPhaseAttributeImpl();

        proposalContestPhaseAttributeImpl.setNew(proposalContestPhaseAttribute.isNew());
        proposalContestPhaseAttributeImpl.setPrimaryKey(proposalContestPhaseAttribute.getPrimaryKey());

        proposalContestPhaseAttributeImpl.setId(proposalContestPhaseAttribute.getId());
        proposalContestPhaseAttributeImpl.setProposalId(proposalContestPhaseAttribute.getProposalId());
        proposalContestPhaseAttributeImpl.setContestPhaseId(proposalContestPhaseAttribute.getContestPhaseId());
        proposalContestPhaseAttributeImpl.setName(proposalContestPhaseAttribute.getName());
        proposalContestPhaseAttributeImpl.setAdditionalId(proposalContestPhaseAttribute.getAdditionalId());
        proposalContestPhaseAttributeImpl.setNumericValue(proposalContestPhaseAttribute.getNumericValue());
        proposalContestPhaseAttributeImpl.setStringValue(proposalContestPhaseAttribute.getStringValue());
        proposalContestPhaseAttributeImpl.setRealValue(proposalContestPhaseAttribute.getRealValue());

        return proposalContestPhaseAttributeImpl;
    }

    /**
     * Returns the proposal contest phase attribute with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute findByPrimaryKey(
        Serializable primaryKey)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = fetchByPrimaryKey(primaryKey);

        if (proposalContestPhaseAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalContestPhaseAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposalContestPhaseAttribute;
    }

    /**
     * Returns the proposal contest phase attribute with the primary key or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeException} if it could not be found.
     *
     * @param id the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute findByPrimaryKey(long id)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the proposal contest phase attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute, or <code>null</code> if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = (ProposalContestPhaseAttribute) EntityCacheUtil.getResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalContestPhaseAttributeImpl.class, primaryKey);

        if (proposalContestPhaseAttribute == _nullProposalContestPhaseAttribute) {
            return null;
        }

        if (proposalContestPhaseAttribute == null) {
            Session session = null;

            try {
                session = openSession();

                proposalContestPhaseAttribute = (ProposalContestPhaseAttribute) session.get(ProposalContestPhaseAttributeImpl.class,
                        primaryKey);

                if (proposalContestPhaseAttribute != null) {
                    cacheResult(proposalContestPhaseAttribute);
                } else {
                    EntityCacheUtil.putResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalContestPhaseAttributeImpl.class, primaryKey,
                        _nullProposalContestPhaseAttribute);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalContestPhaseAttributeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposalContestPhaseAttribute;
    }

    /**
     * Returns the proposal contest phase attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute, or <code>null</code> if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the proposal contest phase attributes.
     *
     * @return the proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal contest phase attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @return the range of proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal contest phase attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttribute> findAll(int start, int end,
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

        List<ProposalContestPhaseAttribute> list = (List<ProposalContestPhaseAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE;

                if (pagination) {
                    sql = sql.concat(ProposalContestPhaseAttributeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalContestPhaseAttribute>(list);
                } else {
                    list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
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
     * Removes all the proposal contest phase attributes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : findAll()) {
            remove(proposalContestPhaseAttribute);
        }
    }

    /**
     * Returns the number of proposal contest phase attributes.
     *
     * @return the number of proposal contest phase attributes
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE);

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
     * Initializes the proposal contest phase attribute persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalContestPhaseAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalContestPhaseAttribute>> listenersList =
                    new ArrayList<ModelListener<ProposalContestPhaseAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalContestPhaseAttribute>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalContestPhaseAttributeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
