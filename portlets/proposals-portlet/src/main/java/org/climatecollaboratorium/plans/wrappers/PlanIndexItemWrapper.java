package org.climatecollaboratorium.plans.wrappers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.climatecollaboratorium.plans.Helper;
import org.climatecollaboratorium.plans.NavigationBean;
import org.climatecollaboratorium.plans.PlansIndexBean;
import org.climatecollaboratorium.plans.activity.PlanActivityKeys;

import com.ext.portlet.NoSuchPlanPositionsException;
import com.ext.portlet.model.PlanAttribute;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class PlanIndexItemWrapper {

    private static Logger log = Logger.getLogger(PlanIndexItemWrapper.class);

    private PlanItem wrapped;
    private Map<Columns, Object> columnValues;
    private PlansIndexBean plansIndexBean;
    private ThemeDisplay td = Helper.getThemeDisplay();

    public PlanIndexItemWrapper(PlanItem wrapped, PlansIndexBean plansIndexBean)
            throws SystemException, PortalException {
        this.wrapped = wrapped;
        columnValues = new HashMap<Columns, Object>();
/*
        for (Columns col : plansIndexBean.getColumns()) {
            columnValues.put(col, col.getValue(wrapped));
        }
        */

        this.plansIndexBean = plansIndexBean;
    }

    public boolean getHasPositions() throws SystemException, NoSuchPlanPositionsException {
        List<Long> positions = null;
        try {
            positions = PlanItemLocalServiceUtil.getPositionsIds(wrapped);
        } catch (Exception e) {
            log.error("Error retrieving plan positions for " + PlanItemLocalServiceUtil.getName(wrapped), e);
        }
        return positions != null && positions.size() > 0;
    }

    public Map<Columns, Object> getColumnValues() {
        return columnValues;
    }

    public String getPlanName() throws SystemException {
        return PlanItemLocalServiceUtil.getName(wrapped);
    }

    public Long getPlanId() {
        return wrapped.getPlanId();
    }

    public User getAuthor() throws PortalException, SystemException {
        return PlanItemLocalServiceUtil.getAuthor(wrapped);
    }

    public Long getContestPhaseId() throws SystemException, PortalException {
        return PlanItemLocalServiceUtil.getContestPhase(wrapped).getContestPhasePK();
    }

    public Long getContestId() throws PortalException, SystemException {
        return PlanItemLocalServiceUtil.getContest(wrapped).getContestPK();
    }

    public boolean isVotedOn() throws PortalException, SystemException {
        boolean voted = false;
        if (Helper.isUserLoggedIn()) {
            voted = PlanItemLocalServiceUtil.hasUserVoted(wrapped, Helper.getLiferayUser().getUserId());
        }
        return voted;
    }

    public void vote(ActionEvent e) throws PortalException, SystemException {
        PlanActivityKeys activityKey = PlanActivityKeys.VOTE_FOR_PLAN;

        if (Helper.isUserLoggedIn()) {
            if (isVotedOn()) {
                PlanItemLocalServiceUtil.unvote(wrapped, Helper.getLiferayUser().getUserId());
                activityKey = PlanActivityKeys.RETRACT_VOTE_FOR_PLAN;
            } else {
                try {
                    if (PlanVoteLocalServiceUtil.getPlanVote(Helper.getLiferayUser().getUserId(), 
                            PlanItemLocalServiceUtil.getContest(wrapped).getContestPK()) != null) {
                        activityKey = PlanActivityKeys.SWICTH_VOTE_FOR_PLAN;
                    }

                } catch (Throwable ex) {
                    // backend can throw no such vote exception, it should be
                    // ignored as this is a normal case
                }
                PlanItemLocalServiceUtil.vote(wrapped, Helper.getLiferayUser().getUserId());
            }

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(NavigationBean.DEFERED_PLAN_VOTE_ID_PARAM);
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), PlanItem.class.getName(),
                wrapped.getPlanId(), activityKey.id(), null, 0);
            plansIndexBean.refresh();
        }
        
    }

    public Integer getPlace() throws SystemException {
        PlanAttribute attr = PlanItemLocalServiceUtil.getPlanAttribute(wrapped, PlanConstants.Attribute.PLAN_PLACE.name());
        return attr != null ? (Integer) PlanAttributeLocalServiceUtil.getTypedValue(attr) : -1;
    }

    public Integer getRibbon() throws SystemException {
        PlanAttribute attr = PlanItemLocalServiceUtil.getPlanAttribute(wrapped, PlanConstants.Attribute.PLAN_RIBBON.name());
        try {
            return attr != null ? Integer.parseInt(attr.getAttributeValue()) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String getRibbonText() throws SystemException {
        PlanAttribute attr = PlanItemLocalServiceUtil.getPlanAttribute(wrapped, PlanConstants.Attribute.PLAN_RIBBON_TEXT.name());
        return attr != null ? attr.getAttributeValue() : null;
    }


    public boolean isScrapbook() throws SystemException {
        PlanAttribute pa = PlanItemLocalServiceUtil.getPlanAttribute(wrapped, "SCRAPBOOK");
        if (pa == null ||! pa.getAttributeValue().equals("true")) {
            return false;
        }
        return true;
    }

    public String getScrapbookText() throws SystemException {
        PlanAttribute attr = PlanItemLocalServiceUtil.getPlanAttribute(wrapped, PlanConstants.Attribute.SCRAPBOOK_HOVER.name());
        return attr != null ? attr.getAttributeValue() : null;
    }
    
    public String getName() throws SystemException {
        return PlanItemLocalServiceUtil.getName(wrapped);
    }
    
    public String getAbstract() throws SystemException {
        return PlanItemLocalServiceUtil.getPitch(wrapped);
    }
    
    public int getCommentsCount() throws SystemException, PortalException {
        return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(
                PlanItemLocalServiceUtil.getDiscussionCategoryGroup(wrapped));
    }
    
    public String getSupportersCount() throws SystemException {
        PlanAttribute attr = PlanItemLocalServiceUtil.getPlanAttribute(wrapped, PlanConstants.Attribute.SUPPORTERS.name());
        return attr != null ? attr.getAttributeValue() : "0";
    }
    
    public Date getLastModifiedDate() {
        return wrapped.getUpdated();
    }
    
    public boolean isOpen() throws SystemException {
        return PlanItemLocalServiceUtil.getOpen(wrapped);
    }
    
    public String getTeam() throws SystemException {
        return PlanItemLocalServiceUtil.getTeam(wrapped);
    }
    
    public boolean isFeatured() throws SystemException {
        return PlanItemLocalServiceUtil.getRibbon(wrapped) != null && PlanItemLocalServiceUtil.getRibbon(wrapped) > 0;
    }
    
    public String getTags() throws SystemException {
        return PlanItemLocalServiceUtil.getTags(wrapped);
    }
    
    public String getTagsHover() throws SystemException {
        return PlanItemLocalServiceUtil.getTagsHover(wrapped);
    }

    public int getTagsOrder() throws SystemException {
        return PlanItemLocalServiceUtil.getTagsOrder(wrapped);
    }
    
    
    

}