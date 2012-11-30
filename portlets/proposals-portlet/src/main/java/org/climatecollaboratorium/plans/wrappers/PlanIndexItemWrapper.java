package org.climatecollaboratorium.plans.wrappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.climatecollaboratorium.plans.Helper;
import org.climatecollaboratorium.plans.NavigationBean;
import org.climatecollaboratorium.plans.PlansIndexBean;
import org.climatecollaboratorium.plans.activity.PlanActivityKeys;

import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
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
            positions = wrapped.getPositionsIds();
        } catch (Exception e) {
            log.error("Error retrieving plan positions for " + wrapped.getName(), e);
        }
        return positions != null && positions.size() > 0;
    }

    public Map<Columns, Object> getColumnValues() {
        return columnValues;
    }

    public String getPlanName() throws SystemException {
        return wrapped.getName();
    }

    public Long getPlanId() {
        return wrapped.getPlanId();
    }

    public User getAuthor() throws PortalException, SystemException {
        return wrapped.getAuthor();
    }

    public Long getContestPhaseId() throws SystemException, PortalException {
        return wrapped.getContestPhase().getContestPhasePK();
    }

    public Long getContestId() throws PortalException, SystemException {
        return wrapped.getContest().getContestPK();
    }

    public boolean isVotedOn() throws PortalException, SystemException {
        boolean voted = false;
        if (Helper.isUserLoggedIn()) {
            voted = wrapped.hasUserVoted(Helper.getLiferayUser().getUserId());
        }
        return voted;
    }

    public void vote(ActionEvent e) throws PortalException, SystemException {
        PlanActivityKeys activityKey = PlanActivityKeys.VOTE_FOR_PLAN;

        if (Helper.isUserLoggedIn()) {
            if (isVotedOn()) {
                wrapped.unvote(Helper.getLiferayUser().getUserId());
                activityKey = PlanActivityKeys.RETRACT_VOTE_FOR_PLAN;
            } else {
                try {
                    if (PlanVoteLocalServiceUtil.getPlanVote(Helper.getLiferayUser().getUserId(), wrapped.getContest()
                            .getContestPK()) != null) {
                        activityKey = PlanActivityKeys.SWICTH_VOTE_FOR_PLAN;
                    }

                } catch (Throwable ex) {
                    // backend can throw no such vote exception, it should be
                    // ignored as this is a normal case
                }
                wrapped.vote(Helper.getLiferayUser().getUserId());
            }

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(NavigationBean.DEFERED_PLAN_VOTE_ID_PARAM);
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), PlanItem.class.getName(),
                wrapped.getPlanId(), activityKey.id(), null, 0);
            plansIndexBean.refresh();
        }
        
    }

    public Integer getPlace() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.PLAN_PLACE.name());
        return attr != null ? (Integer) attr.getTypedValue() : -1;
    }

    public Integer getRibbon() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.PLAN_RIBBON.name());
        try {
            return attr != null ? Integer.parseInt(attr.getAttributeValue()) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String getRibbonText() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.PLAN_RIBBON_TEXT.name());
        return attr != null ? attr.getAttributeValue() : null;
    }


    public boolean isScrapbook() throws SystemException {
        PlanAttribute pa = wrapped.getPlanAttribute("SCRAPBOOK");
        if (pa == null ||! pa.getAttributeValue().equals("true")) {
            return false;
        }
        return true;
    }

    public String getScrapbookText() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.SCRAPBOOK_HOVER.name());
        return attr != null ? attr.getAttributeValue() : null;
    }
    
    public String getName() throws SystemException {
        return wrapped.getName();
    }
    
    public String getAbstract() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.ABSTRACT.name());
        return attr != null ? attr.getAttributeValue() : null;
    }
    
    public int getCommentsCount() throws SystemException, PortalException {
        return wrapped.getDiscussionCategoryGroup().getCommentsCount();
    }
    
    public String getSupportersCount() throws SystemException {
        PlanAttribute attr = wrapped.getPlanAttribute(PlanConstants.Attribute.SUPPORTERS.name());
        return attr != null ? attr.getAttributeValue() : null;
    }
    
    public Date getLastModifiedDate() {
        return wrapped.getUpdated();
    }
    
    public boolean isOpen() throws SystemException {
        return wrapped.getOpen();
    }
    
    public String getTeam() throws SystemException {
        return wrapped.getTeam();
    }
    
    public boolean isFeatured() throws SystemException {
        return wrapped.getRibbon() != null && wrapped.getRibbon() > 0;
    }
    
    public String getTags() throws SystemException {
        return wrapped.getTags();
    }
    
    public String getTagsHover() throws SystemException {
        return wrapped.getTagsHover();
    }

    public int getTagsOrder() throws SystemException {
        return wrapped.getTagsOrder();
    }
    
    
    

}