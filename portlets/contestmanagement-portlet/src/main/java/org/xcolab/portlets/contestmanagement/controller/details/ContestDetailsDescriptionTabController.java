package org.xcolab.portlets.contestmanagement.controller.details;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.enums.ContestTier;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.ContestDescriptionBean;
import org.xcolab.portlets.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.entities.LabelStringValue;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.utils.emailnotification.ContestCreationNotification;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thomas on 2/6/2015.
 */

@Controller
@RequestMapping("view")
public class ContestDetailsDescriptionTabController extends ContestDetailsBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestDetailsDescriptionTabController.class);
    static final private TabEnum tab = ContestDetailsTabs.DESCRIPTION;
    static final private String TAB_VIEW = "details/descriptionTab";

    @Autowired
    private Validator validator;

    @InitBinder("contestDescriptionBean")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute("proposalTemplateSelectionItems")
    public List<LabelValue> populateProposalTemplateSelectionItems(){
        return getProposalTemplateSelectionItems();
    }

    @ModelAttribute("contestLevelSelectionItems")
    public List<LabelValue> populateContestLevelSelectionItems(){
        return getContestLevelSelectionItems();
    }

    @ModelAttribute("contestTypeSelectionItems")
    public List<LabelValue> populateContestTypeSelectionItems(){
        return getContestTypeSelectionItems();
    }

    @ModelAttribute("scheduleTemplateSelectionItems")
    public List<LabelValue> populateScheduleSelectionItems(PortletRequest request){
        return getContestScheduleSelectionItems(request);
    }

    @ModelAttribute("modelIdsSelectionItems")
    public List<LabelValue> populateModelIdsSelectionItems(){
        return ContestModelSettingsBean.getAllModelIds();
    }
    @ModelAttribute("modelRegionsSelectionItems")
    public List<LabelStringValue> populateModelRegionsSelectionItems(){
        return ContestModelSettingsBean.getAllModelRegions();
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException{
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=DESCRIPTION")
    public String showDescriptionTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }
        setPageAttributes(request, model, tab);
        Contest contest = getContest();
        model.addAttribute("contestDescriptionBean", new ContestDescriptionBean(contest));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestDetails")
    public void updateDescriptionTabController(ActionRequest request, Model model, ActionResponse response,
                                             @Valid ContestDescriptionBean updatedContestDescriptionBean,
                                             BindingResult result) {

        boolean createNew = getCreateNewContestParameterFromRequest(request);

        if(!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateContestDetails");
            return;
        }

        try{
            // TODO check Input
            updatedContestDescriptionBean.persist(getContest());
            if (createNew) {
                ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
                Contest contest = ContestLocalServiceUtil.getContest(updatedContestDescriptionBean.getContestPK());
                sendEmailNotificationToAuthor(themeDisplay, contest);
            }
            SetRenderParameterUtil.setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch(SystemException | PortalException | IOException e){
            _log.warn("Update contest description failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }

    }

    @RequestMapping(params = {"action=updateContestDetails", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

    private void sendEmailNotificationToAuthor(ThemeDisplay themeDisplay, Contest contest) throws PortalException, SystemException{
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(themeDisplay.getPortalURL());
        new ContestCreationNotification(contest, serviceContext).sendMessage();
    }

    private List<LabelValue> getProposalTemplateSelectionItems(){
        List<LabelValue> selectItems = new ArrayList<>();
        List<Long> excludedList = Arrays.asList(1L, 2L, 106L, 201L, 202L, 301L, 401L, 1000401L, 1000501L, 1300104L, 1300201L, 1300302L, 1300401L, 1300601L, 1300602L);
        try {
            for (PlanTemplate proposalTemplate : PlanTemplateLocalServiceUtil.getPlanTemplates(0, Integer.MAX_VALUE)) {
                if(!excludedList.contains(proposalTemplate.getId())) {
                    selectItems.add(new LabelValue(proposalTemplate.getId(), proposalTemplate.getName()));
                }
            }
        } catch (SystemException e){
            _log.warn("Could not get contest proposal template selection items: " + e);
        }
        return selectItems;
    }

    private List<LabelValue> getContestLevelSelectionItems(){
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestTier contestLevel : ContestTier.values()) {
            selectItems.add(new LabelValue(contestLevel.getTierType(), contestLevel.getTierName()));
        }
        return selectItems;
    }

    private List<LabelValue> getContestTypeSelectionItems(){
        List<LabelValue> selectItems = new ArrayList<>();
        try {
            for (ContestType contestType : ContestTypeLocalServiceUtil.getContestTypes(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
                selectItems.add(new LabelValue(contestType.getId(),
                        String.format("%d - %s with %s", contestType.getId(),
                                contestType.getContestName(), contestType.getProposalNamePlural())));
            }
        } catch (SystemException e){
            _log.warn("Could not get contest type selection items: " + e);
        }
        return selectItems;
    }

    private List<LabelValue> getContestScheduleSelectionItems(PortletRequest request){
        List<LabelValue> scheduleTemplateSelectionItems = new ArrayList<>();
        try {
            Contest contest = getContest(request);
            BaseContestWrapper contestWrapper = new BaseContestWrapper(contest);
            Long existingContestScheduleId = contest.getContestScheduleId();
            Boolean contestHasProposals = contestWrapper.getProposalsCount() > 0;
            scheduleTemplateSelectionItems =
                    ContestScheduleWrapper.getScheduleTemplateSelectionItems(existingContestScheduleId, contestHasProposals);
        } catch (SystemException | PortalException e){
            _log.warn("Could not get contest schedule selection items: " + e);
        }
        return scheduleTemplateSelectionItems;
    }
}