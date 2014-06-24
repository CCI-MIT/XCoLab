package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.model.impl.ProposalRatingImpl;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ext.portlet.NoSuchPlanAttributeFilterException;
import com.ext.portlet.NoSuchPlanTypeException;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanType;
import com.ext.portlet.model.PlanVote;
import com.ext.portlet.model.PlansUserSettings;
import com.ext.portlet.model.impl.PlanItemImpl;
import com.ext.portlet.model.impl.PlanItemModelImpl;
import com.ext.portlet.plans.EntityState;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.PlanConstants.Property;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

/**
 * Created by manu on 23/06/14.
 */


public class ProposalRatingFinderImpl extends BasePersistenceImpl<ProposalRating> implements ProposalRatingFinder {

    public List<ProposalRating> findByProposalIdJudgeType(long proposalId, int judgeType, int start, int end) throws SystemException {
        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(FIND_BY_PROPOSAL_ID_JUDGE_TYPE);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("xcolab_ProposalRating", ProposalRatingImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(proposalId);
            qPos.add(judgeType);

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

    public static final String FIND_BY_PROPOSAL_ID_JUDGE_TYPE =
            ProposalRatingFinder.class.getName() +
                    ".findByProposalIdJudgeType";
    public static final String FIND_BY_PROPOSAL_ID_JUDGE_TYPE_JUDGE_ID_CONTEST_PHASE_ID =
            ProposalRatingFinder.class.getName() +
                    ".findByProposalIdJudgeTypeJudgeIdContestPhaseId";

}
