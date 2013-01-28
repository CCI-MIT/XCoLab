package org.xcolab.portlets.plansadmin.wrappers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.xcolab.portlets.plansadmin.Helper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.icesoft.faces.component.inputfile.InputFile;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ImageLocalServiceUtil;

public class ContestWrapper {
    private Contest contest;
    private File newContestLogo;
    
    public ContestWrapper(Contest contest) {
        this.contest = contest;
    }
    
    public String getContestShortName() {
        return contest.getContestShortName();
    }
    
    public String getContestName() {
        return contest.getContestName();
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }
    
    public boolean getFeatured() {
        return contest.getFeatured();
    }
    
    public void setFeatured(boolean featured) {
        contest.setFeatured(featured);
    }
    
    public Integer getFlag() {
        return contest.getFlag();
    }
    
    public void setFlag(Integer flag) {
        contest.setFlag(flag);
    }
    
    public String getFlagText() {
        return contest.getFlagText();
    }
    
    public void setFlagText(String flagText) {
        contest.setFlagText(flagText);
    }
/*
    public String getFlagTooltip() {
        return ContestLocalServiceUtil.getFlagTooltip(contest.getFlagTooltip();
    }
    
    public void setFlagTooltip(String flagTooltip) {
        contest.setFlagTooltip(flagTooltip);
    }*/
    
    public boolean getPlansOpenByDefault() {
    	return contest.getPlansOpenByDefault();
    }
    
    public void setPlansOpenByDefault(boolean open) {
    	contest.setPlansOpenByDefault(open);
    }
    
    
    public void save() throws SystemException, IOException, PortalException {
        if (newContestLogo != null) {
            ContestLocalServiceUtil.setLogo(contest, newContestLogo);
        }
        ContestLocalServiceUtil.store(contest);
    }
    
    public List<SelectItem> getAvailablePlanTemplates() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        ret.add(new SelectItem(null, "-- none --"));
        for (PlanTemplate tmpl: PlanTemplateLocalServiceUtil.getPlanTemplates(0, Integer.MAX_VALUE)) {
            ret.add(new SelectItem(tmpl.getId(), tmpl.getName()));
        }
        return ret;
    }
    
    public List<SelectItem> getAvailableFocusAreas() throws SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        ret.add(new SelectItem(null, "-- none --"));
        
        for (FocusArea fa: FocusAreaLocalServiceUtil.getFocusAreas(0, Integer.MAX_VALUE)) {
            ret.add(new SelectItem(fa.getId(), fa.getName()));
        }
        return ret;
    }
    

    public void planTemplateChange(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        Long id = 0L;
        if (e.getNewValue() == null) {
            id = 0L;
        }
        else {
            PlanTemplate tmpl = PlanTemplateLocalServiceUtil.getPlanTemplate(Long.parseLong(e.getNewValue().toString()));
        }
        
        contest.setPlanTemplateId(id);
        ContestLocalServiceUtil.store(contest);
    }
    
    public void planFocusAreaChange(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {        
        Long id = 0L;
        if (e.getNewValue() == null) {
            id = 0L;
        }
        else {
            FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(Long.parseLong(e.getNewValue().toString()));
        }
        
        contest.setFocusAreaId(id);
        ContestLocalServiceUtil.store(contest);
    }
    
    public void populateFAtoPlans(ActionEvent e) throws PortalException, SystemException {
        FocusArea fa = ContestLocalServiceUtil.getFocusArea(contest);
        
        for (ContestPhase phase: ContestLocalServiceUtil.getPhases(contest)) {
            for (PlanItem plan: PlanItemLocalServiceUtil.getPlansInContestPhase(phase.getContestPhasePK())) {
                FocusAreaLocalServiceUtil.tagClass(fa, PlanItem.class, plan.getPlanId());
            }
        }
    }
    
    public void uploadLogo(ActionEvent e) throws IOException, PortalException, SystemException {
        InputFile inputFile = (InputFile) e.getSource();
        if (inputFile.getFileInfo().getStatus() == InputFile.INVALID) {
            //fileErrorMessage = "Provided file isn't a valid image.";
            _log.error("There was an error when uploading file", inputFile.getFileInfo().getException());
            return;
        }
        
        if (! inputFile.getFileInfo().getContentType().startsWith("image")) {
            //fileErrorMessage = "Provided file isn't a valid image.";
            _log.error("There was an error when uploading file", inputFile.getFileInfo().getException());
            return;
        }
        newContestLogo = inputFile.getFileInfo().getFile();
        Image i = ImageLocalServiceUtil.getImage(newContestLogo);
        System.out.println(i);
        //fileErrorMessage = null;
        //resiseAndCropImage(inputFile.getFile());
        //newUserProfile = inputFile.getFile();
        
    }
    
    public String getLogo() throws PortalException, SystemException {
        System.out.println(ImageLocalServiceUtil.getDefaultSpacer());
        
        return Helper.getThemeDisplay().getPathImage() + ContestLocalServiceUtil.getLogoPath(contest);
    }
    
    
    
    
    private final static Log _log = LogFactoryUtil.getLog(ContestWrapper.class);
}
