package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.model.impl.ProposalRatingImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * Created by manu on 23/06/14.
 */


public class ProposalRatingFinderImpl extends BasePersistenceImpl<ProposalRating> implements ProposalRatingFinder {

    public List<ProposalRating> findByProposalIdJudgeTypeContestPhaseId(long proposalId, int judgeType, long contestPhaseId, int start, int end) throws SystemException {
        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(FIND_BY_PROPOSAL_ID_JUDGE_TYPE_CONTEST_PHASE_ID);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("xcolab_ProposalRating", ProposalRatingImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(proposalId);
            qPos.add(judgeType);
            qPos.add(contestPhaseId);

            return (List<ProposalRating>) QueryUtil.list(q, getDialect(), start, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }

        return null;
    }

    public List<ProposalRating> findByProposalIdJudgeTypeJudgeIdContestPhaseId(long proposalId, int judgeType, long judgeId, long contestPhaseId, int start, int end) throws SystemException {
        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(FIND_BY_PROPOSAL_ID_JUDGE_TYPE_JUDGE_ID_CONTEST_PHASE_ID);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("xcolab_ProposalRating", ProposalRatingImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(proposalId);
            qPos.add(judgeType);
            qPos.add(judgeId);
            qPos.add(contestPhaseId);

            return (List<ProposalRating>) QueryUtil.list(q, getDialect(), start, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }

        return null;
    }

    public List<ProposalRating> findByContestPhaseId(long contestPhaseId, int start, int end) throws SystemException {
        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(FIND_BY_CONTEST_PHASE_ID);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("xcolab_ProposalRating", ProposalRatingImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(contestPhaseId);

            return (List<ProposalRating>) QueryUtil.list(q, getDialect(), start, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }

        return null;
    }

    public static final String FIND_BY_PROPOSAL_ID_JUDGE_TYPE_CONTEST_PHASE_ID =
            ProposalRatingFinder.class.getName() +
                    ".findByProposalIdJudgeTypeContestPhaseId";

    public static final String FIND_BY_PROPOSAL_ID_JUDGE_TYPE_JUDGE_ID_CONTEST_PHASE_ID =
            ProposalRatingFinder.class.getName() +
                    ".findByProposalIdJudgeTypeJudgeIdContestPhaseId";

    public static final String FIND_BY_CONTEST_PHASE_ID =
            ProposalRatingFinder.class.getName() +
                    ".findByContestPhaseId";

}
