package org.xcolab.portlets.contestmanagement.controller.details;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.ContestDescriptionBean;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.portlets.contestmanagement.utils.schedule.ContestScheduleUtil;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.utils.emailnotification.contest.ContestCreationNotification;
import org.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("view")
public class ContestDetailsDescriptionTabController extends ContestDetailsBaseTabController {

    private final static Logger _log = LoggerFactory.getLogger(ContestDetailsDescriptionTabController.class);
    static final private TabEnum tab = ContestDetailsTabs.DESCRIPTION;
    static final private String TAB_VIEW = "details/descriptionTab";

    @Autowired
    private Validator validator;

    @InitBinder("contestDescriptionBean")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute("proposalTemplateSelectionItems")
    public List<LabelValue> populateProposalTemplateSelectionItems() {
        return getProposalTemplateSelectionItems();
    }

    @ModelAttribute("scheduleTemplateSelectionItems")
    public List<LabelValue> populateScheduleSelectionItems(PortletRequest request) {
        return getContestScheduleSelectionItems(request);
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=DESCRIPTION")
    public String showDescriptionTabController(PortletRequest request, PortletResponse response, Model model) {

        if (!tabWrapper.getCanView()) {
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

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateContestDetails");
            return;
        }

        if (ContestScheduleUtil.canUpdateContestToSchedule(getContest(), updatedContestDescriptionBean.getScheduleTemplateId())) {
            result.reject("scheduleTemplateId","This contest already has proposals. "
                    + "Please select a schedule with matching phases or contact a developer.");
        }

        try {
            // TODO check Input
            updatedContestDescriptionBean.persist(getContest());
            if (createNew) {
                try {
                    ThemeDisplay themeDisplay = (ThemeDisplay) request
                            .getAttribute(WebKeys.THEME_DISPLAY);
                    Contest contest = ContestClientUtil.getContest(updatedContestDescriptionBean.getContestPK());
                    sendEmailNotificationToAuthor(themeDisplay, contest);
                } catch (ContestNotFoundException | MemberNotFoundException e) {
                    throw new DatabaseAccessException(e);
                }
            }
            SetRenderParameterUtil.setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch (IOException e) {
            _log.warn("Update contest description failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }

    }

    @RequestMapping(params = {"action=updateContestDetails", "error=true"})
    public String reportError(PortletRequest request, Model model) {
        return TAB_VIEW;
    }

    private void sendEmailNotificationToAuthor(ThemeDisplay themeDisplay, Contest contest)
            throws MemberNotFoundException {
        new ContestCreationNotification(contest, themeDisplay.getPortalURL()).sendMessage();
    }

    private List<LabelValue> getProposalTemplateSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        //TODO: why do we need this? and why is hard coded?
        List<Long> excludedList =
                Arrays.asList(1L, 2L, 106L, 201L, 202L, 301L, 401L, 1000401L, 1000501L, 1300104L, 1300201L, 1300302L,
                        1300401L, 1300601L, 1300602L);
        for (PlanTemplate proposalTemplate : PlanTemplateClientUtil.getPlanTemplates()) {
            if (!excludedList.contains(proposalTemplate.getId_())) {
                selectItems.add(new LabelValue(proposalTemplate.getId_(), proposalTemplate.getName()));
            }
        }
        return selectItems;
    }

    private List<LabelValue> getContestScheduleSelectionItems(PortletRequest request) {
        Contest contest = getContest(request);
        Long existingContestScheduleId = contest.getContestScheduleId();
        Boolean contestHasProposals = contest.getProposalsCount() > 0;
        return ContestScheduleLifecycleUtil
                .getScheduleTemplateSelectionItems(existingContestScheduleId, contestHasProposals);
    }
}