package org.xcolab.portlets.contestmanagement.controller.batch;

import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.contestmanagement.beans.ContestBatchBean;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.wrappers.ContestScheduleWrapper;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("view")
public class ContestBatchCreationController {

    private final static Log _log = LogFactoryUtil.getLog(ContestBatchCreationController.class);

    @ModelAttribute("proposalTemplateSelectionItems")
    public List<LabelValue> populateProposalTemplateSelectionItems() {
        return getProposalTemplateSelectionItems();
    }

    @ModelAttribute("scheduleTemplateSelectionItems")
    public List<LabelValue> populateScheduleSelectionItems(PortletRequest request) {
        return getContestScheduleSelectionItems(request);
    }
    @ModelAttribute("contestLevelSelectionItems")
    public List<LabelValue> populateContestLevelSelectionItems() {
        return getContestLevelSelectionItems();
    }

    @ModelAttribute("contestTypeSelectionItems")
    public List<LabelValue> populateContestTypeSelectionItems() {
        return getContestTypeSelectionItems();
    }

    @RequestMapping(params = "batchCreateContest=true")
    public String batchCreateContestController(PortletRequest request, Model model, PortletResponse response)
            throws PortalException, SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        PermissionChecker portletPermissionChecker = themeDisplay.getPermissionChecker();
        User currentUser = themeDisplay.getUser();

        if (!currentUser.isDefaultUser() && portletPermissionChecker.isOmniadmin()) {
            model.addAttribute("contestBatchBean", new ContestBatchBean());
            return "batch/uploadContestCSV";
        }
        throw new PortalException("User not authorized to create contest");
    }

    private List<LabelValue> getProposalTemplateSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        List<Long> excludedList =
                Arrays.asList(1L, 2L, 106L, 201L, 202L, 301L, 401L, 1000401L, 1000501L, 1300104L, 1300201L, 1300302L,
                        1300401L, 1300601L, 1300602L);
        try {
            for (PlanTemplate proposalTemplate : PlanTemplateLocalServiceUtil.getPlanTemplates(0, Integer.MAX_VALUE)) {
                if (!excludedList.contains(proposalTemplate.getId())) {
                    selectItems.add(new LabelValue(proposalTemplate.getId(), proposalTemplate.getName()));
                }
            }
        } catch (SystemException e) {
            _log.warn("Could not get contest proposal template selection items: " + e);
        }
        return selectItems;
    }

    private List<LabelValue> getContestScheduleSelectionItems(PortletRequest request) {
        List<LabelValue> scheduleTemplateSelectionItems =
                    ContestScheduleWrapper
                            .getAllScheduleTemplateSelectionItems();
        return scheduleTemplateSelectionItems;
    }
    private List<LabelValue> getContestLevelSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestTier contestLevel : ContestTier.values()) {
            selectItems.add(new LabelValue(contestLevel.getTierType(), contestLevel.getTierName()));
        }
        return selectItems;
    }
    private List<LabelValue> getContestTypeSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        try {
            for (ContestType contestType : ContestTypeLocalServiceUtil
                    .getContestTypes(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
                selectItems.add(new LabelValue(contestType.getId(),
                        String.format("%d - %s with %s", contestType.getId(),
                                contestType.getContestName(), contestType.getProposalNamePlural())));
            }
        } catch (SystemException e) {
            _log.warn("Could not get contest type selection items: " + e);
        }
        return selectItems;
    }
}
