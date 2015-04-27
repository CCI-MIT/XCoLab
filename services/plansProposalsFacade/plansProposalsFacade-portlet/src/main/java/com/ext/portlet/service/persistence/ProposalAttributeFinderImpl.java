package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.impl.ProposalAttributeImpl;
import com.ext.portlet.model.impl.ProposalAttributeModelImpl;
import com.ext.portlet.model.impl.ProposalModelImpl;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * Created by kmang on 12/03/15.
 */
public class ProposalAttributeFinderImpl extends BasePersistenceImpl<ProposalAttribute> implements ProposalAttributeFinder {
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY + ".List";
    private static final FinderPath FINDER_PATH_FETCH_BY_PROPOSAL_ID_VERSION_GE_AND_LE_AND_IMPACT;

    private final String FETCH_BY_PROPOSAL_ID_VERSION_GE_AND_LE_AND_IMPACT = ProposalAttributeFinderImpl.class.getName()
            + ".findByProposalIdVersionGreaterThanVersionWhenCreatedLessThanNameLikeImpact";

    static {
        FINDER_PATH_FETCH_BY_PROPOSAL_ID_VERSION_GE_AND_LE_AND_IMPACT = new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalAttributeModelImpl.FINDER_CACHE_ENABLED, ProposalAttributeImpl.class, FINDER_CLASS_NAME_LIST,
                "fetchByContestPhaseId", new String[]{Long.class.getName()});
    }



    public List<ProposalAttribute> findByProposalIdVersionGreaterThanVersionWhenCreatedLessThanNameLikeImpact(long proposalId,
                                                                                                              long version,
                                                                                                              long versionWhenCreated) {

        Object[] args = new Object[] { proposalId, version, versionWhenCreated };

        List<ProposalAttribute> proposalAttributes = (List<ProposalAttribute>)
                FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PROPOSAL_ID_VERSION_GE_AND_LE_AND_IMPACT, args, this);

        if (proposalAttributes == null) {

            Session session = openSession();
            String sql = CustomSQLUtil.get(FETCH_BY_PROPOSAL_ID_VERSION_GE_AND_LE_AND_IMPACT);
            SQLQuery query = session.createSQLQuery(sql);

            query.addEntity("ProposalAttribute", ProposalAttributeImpl.class);
            query.setLong(0, proposalId);
            query.setLong(1, version);
            query.setLong(2, versionWhenCreated);
            proposalAttributes = (List<ProposalAttribute>) QueryUtil.list(query, getDialect(), 0, Integer.MAX_VALUE);

            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSAL_ID_VERSION_GE_AND_LE_AND_IMPACT, args, proposalAttributes);
        }
        return proposalAttributes;
    }
}
