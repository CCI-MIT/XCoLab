package org.xcolab.portlets.contestmanagement.controller;

import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanSectionDefinitionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.ContestDescriptionBean;
import org.xcolab.portlets.contestmanagement.beans.ContestResourcesBean;
import org.xcolab.portlets.contestmanagement.beans.SectionDefinitionBean;
import org.xcolab.portlets.contestmanagement.entities.ContestLevels;
import org.xcolab.portlets.contestmanagement.entities.LabelStringValue;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.entities.SectionTypes;
import org.xcolab.portlets.contestmanagement.views.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.wrappers.ContestProposalTemplateWrapper;
import org.xcolab.portlets.contestmanagement.wrappers.WikiPageWrapper;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2/13/2015.
 */

@Controller
@RequestMapping("view")
public class ContestDetailProposalTemplateTabController extends ContestDetailsBaseTabController {

    static final private TabEnum tab = ContestDetailsTabs.PROPOSALTEMPLATE;
    static final private String TAB_VIEW = "details/proposalTemplateTab";
    private ContestProposalTemplateWrapper contestProposalTemplateWrapper;

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException{
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @ModelAttribute("levelSelectionItems")
    public List<LabelValue> populateLevelSelectionItems(){
        return getContestLevelSelectionItems();
    }

    @ModelAttribute("sectionTypeSelectionItems")
    public List<LabelStringValue> populateSectionTypesSelectionItems(){
        return getSectionTypesSelectionItems();
    }

    /*
    @ModelAttribute("sectionTypeSelectionItems")
    public List<LabelValue> populateSectionDefinitionSelectionItems(PortletRequest request) throws PortalException, SystemException {
        List<LabelValue> selectItems = new ArrayList<>();
        Long index = 0L;
        try {
            for (PlanSectionDefinition planSectionDefinition : PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinitions(0, Integer.MAX_VALUE)) {
                if(!planSectionDefinition.getLocked()) {
                    selectItems.add(new LabelValue(planSectionDefinition.getId(), planSectionDefinition.getType() +" "+  planSectionDefinition.getTitle()));
                }
                if(!planSectionDefinition.getType().isEmpty() && !selectItems.contains(planSectionDefinition.getType().trim())){
                    selectItems.add(new LabelValue(index, planSectionDefinition.getType().trim()));
                    index++;
                }
            }
        } catch (Exception e){
        }
        return selectItems;
    }*/

    @RequestMapping(params = "tab=PROPOSALTEMPLATE")
    public String showResourcesTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView() || request.getRemoteUser() == null) {
            return NO_PERMISSION_TAB_VIEW;
        }

        try {
            contestProposalTemplateWrapper = new ContestProposalTemplateWrapper(getContest());
            model.addAttribute("contestProposalTemplateWrapper", contestProposalTemplateWrapper);
            return TAB_VIEW;
        } catch (Exception e){
        }
        return NOT_FOUND_TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestProposalTemplate")
    public void updateResourcesTabController(ActionRequest request, Model model, ActionResponse response,
                                             @ModelAttribute ContestProposalTemplateWrapper updatedContestProposalTemplateWrapper, BindingResult result) {

        if(!tabWrapper.getCanEdit()) {
            setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            //updatedContestProposalTemplateWrapper.sortProposalTemplateSections();
            //updatedContestProposalTemplateWrapper.removeDeletedSections();
            setErrorRenderParameter(response, "updateContestProposalTemplate");
            return;
        }

        try{
            updatedContestProposalTemplateWrapper.init(getContest());
            updatedContestProposalTemplateWrapper.updateNewProposalTemplateSections();

            //contestProposalTemplateWrapper.removeExistingSectionsFromProposalTemplateId();
            setSuccessRenderRedirect(response, tab.getName());
        } catch(Exception e){
            e.printStackTrace();
            setNotFoundErrorRenderParameter(response);
        }
    }

    @RequestMapping(params = {"action=updateContestProposalTemplate", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

    @ResourceMapping(value="getSectionDefinition")
    public @ResponseBody
    void getSectionDefinition(
            @RequestParam("sectionDefinitionId") Long sectionDefinitionId,
            ResourceResponse response
    ) throws Exception{

        PlanSectionDefinition planSectionDefinition =
                PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(sectionDefinitionId);
        SectionDefinitionBean sectionDefinitionBean = new SectionDefinitionBean(planSectionDefinition);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(sectionDefinitionBean));
    }

    private List<LabelValue> getContestLevelSelectionItems(){
        List<LabelValue> selectItems = new ArrayList<>();
        try {
            for (ContestLevels contestLevel : ContestLevels.values()) {
                selectItems.add(new LabelValue(new Long(contestLevel.getLevel()), contestLevel.getDisplayName()));
            }
        } catch (Exception e){
        }
        return selectItems;
    }

    private List<LabelStringValue> getSectionTypesSelectionItems(){
        List<LabelStringValue> selectItems = new ArrayList<>();
        try {
            for (SectionTypes sectionTypes : SectionTypes.values()) {
                selectItems.add(new LabelStringValue(sectionTypes.getSectionType(), sectionTypes.getDisplayName()));
            }
        } catch (Exception e){
        }
        return selectItems;
    }

}