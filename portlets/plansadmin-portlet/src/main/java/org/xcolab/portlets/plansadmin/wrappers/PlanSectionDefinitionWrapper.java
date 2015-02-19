package org.xcolab.portlets.plansadmin.wrappers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.model.Contest;
import org.apache.commons.lang.StringUtils;

import com.ext.portlet.PlanSectionTypeKeys;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.enums.ContestTier;

public class PlanSectionDefinitionWrapper implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlanSectionDefinition definition;

    public PlanSectionDefinitionWrapper(PlanSectionDefinition definition) {
        this.definition = definition;
    }

    public void setDefinition(PlanSectionDefinition definition) {
        this.definition = definition;
    }

    public PlanSectionDefinition getDefinition() {
        return definition;
    }
    
    public String save() throws SystemException {
        PlanSectionDefinitionLocalServiceUtil.store(definition);
        
        return "backToIndex";
    }
    
    public void focusAreaChange(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(Long.parseLong(e.getNewValue().toString()));
        
        definition.setFocusAreaId(fa.getId());
        PlanSectionDefinitionLocalServiceUtil.store(definition);
    }

    public void tierChange(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        if (e.getNewValue() != null) {
            ContestTier contestTier = ContestTier.getContestTierByTierType((Long) e.getNewValue());
            definition.setFocusAreaId(contestTier.getTierType());
            PlanSectionDefinitionLocalServiceUtil.store(definition);
        }
    }
    
    public void typeChanged(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        definition.setType(e.getNewValue().toString());
    }
    
    public List<SelectItem> getAvailableFocusAreas() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        for (FocusArea fa: FocusAreaLocalServiceUtil.getFocusAreas(0, Integer.MAX_VALUE)) {
            ret.add(new SelectItem(fa.getId(), fa.getName()));
        }
        return ret;
    }

    public List<SelectItem> getAvailableTiers() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();

        for (ContestTier contestTier: ContestTier.values()) {
            ret.add(new SelectItem(contestTier.getTierType(), contestTier.getTierName()));
        }
        return ret;
    }

    public List<SelectItem> getAvailableTypes() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        for (PlanSectionTypeKeys key: PlanSectionTypeKeys.values()) {
            ret.add(new SelectItem(key.name(), key.name()));
        }
        return ret;
    }
    
    public Long getFocusAreaId() {
        return definition.getFocusAreaId();
    }
    
    public void setFocusAreaId(Long focusAreaId) {
        definition.setFocusAreaId(focusAreaId);
    }

    public Long getTier() {
        ContestTier contestTier = ContestTier.getContestTierByTierType(definition.getTier());
        return contestTier.getTierType();
    }

    public void setTier(Long tierType) {
        ContestTier contestTier = ContestTier.getContestTierByTierType(tierType);
        definition.setTier(contestTier.getTierType());
    }

    public String getAdminTitle() {
        
        return definition.getAdminTitle();
    }
    
    public String getTitle() {
        return definition.getTitle();
    }
    
    public PlanSectionTypeKeys getType() {
        if (StringUtils.isNotBlank(definition.getType())) {
            return PlanSectionTypeKeys.valueOf(definition.getType());
        }
        return PlanSectionTypeKeys.TEXT;
    }
    
    public void setType(String type) {
        if (StringUtils.isNotBlank(type)) {
            definition.setType(type);
        }
    }
    
}
