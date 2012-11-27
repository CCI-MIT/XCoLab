package com.ext.portlet.contests.model.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestDebate;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.model.ContestTeamMember;
import com.ext.portlet.contests.service.ContestDebateLocalServiceUtil;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.contests.service.ContestTeamMemberLocalServiceUtil;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ImageLocalServiceUtil;

/**
 * The extended model implementation for the Contest service. Represents a row in the &quot;Contests_Contest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.contests.model.Contest} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class ContestImpl extends ContestBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a contest model instance should use the {@link com.ext.portlet.contests.model.Contest} interface instead.
     */
    private final static Log _log = LogFactoryUtil.getLog(ContestImpl.class);
    
    public ContestImpl() {
    }

    public List<ContestPhase> getPhases() throws SystemException {
        return ContestPhaseLocalServiceUtil.getPhasesForContest(this);
    }

    public PlanType getPlanType() throws SystemException, PortalException {
        return PlanTypeLocalServiceUtil.getPlanType(getPlanTypeId());
    }

    public List<ContestPhase> getActivePhases() throws SystemException {
        List<ContestPhase> result = getPhases();
        for (Iterator<ContestPhase> i=result.iterator();i.hasNext();) {
           if (!i.next().getContestStatus().isCanEdit()) {
               i.remove();
           }
        }
        return result;
    }
    
    public ContestPhase getActivePhase() throws NoSuchContestPhaseException, SystemException {
        return ContestPhaseLocalServiceUtil.getActivePhaseForContest(this);
    }
    
    public boolean isActive() throws SystemException {
        try {
            ContestPhaseLocalServiceUtil.getActivePhaseForContest(this);
            return true;
        }
        catch (NoSuchContestPhaseException e) {
            // ignore
        }
        return false;
    }
    
    public List<Long> getDebatesIds() throws SystemException  {
        List<Long> ret = new ArrayList<Long>();
        for (ContestDebate pos: ContestDebateLocalServiceUtil.getContestDebates(getContestPK())) {
            ret.add(pos.getDebateId());
        }
        return ret;
    }
    
    public Integer getTotalVotes() throws SystemException {
        return PlanVoteLocalServiceUtil.countPlanVotes(this);
    }
    
    public void updateDefaultPlanDescription(String description) throws SystemException {
        this.setDefaultPlanDescription(description);
        ContestLocalServiceUtil.updateContest(this);
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            if (getContestPK() == null) {
                setContestPK(CounterLocalServiceUtil.increment(Contest.class.getName()));
            }
            ContestLocalServiceUtil.addContest(this);
        }
        else {
            ContestLocalServiceUtil.updateContest(this);
        }
    }
    
    public PlanTemplate getPlanTemplate() throws PortalException, SystemException {
        if (getPlanTemplateId() != null && getPlanTemplateId() > 0) {
            return PlanTemplateLocalServiceUtil.getPlanTemplate(getPlanTemplateId());
        }
        return null;
    }
    
    public FocusArea getFocusArea() throws PortalException, SystemException {
        if (getFocusAreaId() != null && getFocusAreaId() > 0) {
            return FocusAreaLocalServiceUtil.getFocusArea(getFocusAreaId());
        }
        return null;
    }
    
    public Image getLogo() throws PortalException, SystemException {
        return getContestLogoId() != null && getContestLogoId() > 0 ? 
                ImageLocalServiceUtil.getImage(getContestLogoId()) : 
                null;
    }
    
    public void setLogo(File logoFile) throws IOException, SystemException, PortalException {
        Image i = ImageLocalServiceUtil.getImage(logoFile);//.getImage(logoFile);   
        i.setImageId(CounterLocalServiceUtil.increment(Image.class.getName()));
        
        ImageLocalServiceUtil.addImage(i);
        this.setContestLogoId(i.getImageId());
        
    }
    
    public String getLogoPath() throws PortalException, SystemException {
        Image i = getLogo();
        if (i != null) {
            return "?img_id=" + i.getImageId();// + "&t=" + ImageServletTokenUtil.getToken(i.getImageId());
        }
        return "";
    }
    
    
    public long getProposalsCount() throws PortalException, SystemException {
        return PlanItemLocalServiceUtil.countPlansByContest(getContestPK());
    }
    
    public DiscussionCategoryGroup getDiscussionCategoryGroup() throws PortalException, SystemException {
        DiscussionCategoryGroup dcg = 
            DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(getDiscussionGroupId());
        return dcg;
    }
    
    public long getCommentsCount() throws PortalException, SystemException {
        return getDiscussionCategoryGroup().getCommentsCount();
    }
    
    public long getProposalsCommentsCount() throws SystemException, PortalException {
        long proposalsCommentsCount = 0;
        for (PlanItem pi: PlanItemLocalServiceUtil.getPlansByContest(getContestPK())) {
            proposalsCommentsCount += pi.getCommentsCount();
        }
        return proposalsCommentsCount;
    }
    
    public long getTotalComments() throws PortalException, SystemException {
        return getCommentsCount() + getProposalsCommentsCount();
    }
    
    public List<ContestTeamMember> getTeamMembers() throws SystemException {
        return ContestTeamMemberLocalServiceUtil.findForContest(getContestPK());
    }

}
