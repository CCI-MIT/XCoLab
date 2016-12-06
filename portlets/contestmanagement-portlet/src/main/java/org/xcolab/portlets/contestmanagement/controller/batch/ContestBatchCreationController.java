package org.xcolab.portlets.contestmanagement.controller.batch;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.portlets.contestmanagement.beans.ContestBatchBean;
import org.xcolab.portlets.contestmanagement.beans.ContestCSVBean;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.ContestCreatorUtil;
import org.xcolab.portlets.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.portlets.contestmanagement.utils.schedule.ContestScheduleUtil;
import org.xcolab.util.IdListUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("view")
public class ContestBatchCreationController {

    private final static Log _log = LogFactoryUtil.getLog(ContestBatchCreationController.class);

    private Map<Long, Map<Long, Integer>> reusableFocusArea = new HashMap<>();

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

    @RequestMapping(params = "action=createBatchContest")
    public String createBatchContestController(RenderRequest request, Model model,
                                               @Valid ContestBatchBean contestBatchBean,
                                               BindingResult result) throws IOException {

        Map<String, String> contestLinks = new LinkedHashMap<>();

        // should check for valid data from CVS even after the fact.
        List<ContestCSVBean> contestsToBeCreated = contestBatchBean.getContestCSVs();
        if (contestsToBeCreated != null && !contestsToBeCreated.isEmpty()) {
            for (ContestCSVBean contestCSVBean : contestsToBeCreated) {

                Contest contest = createContest(contestCSVBean.getContestShortName(),
                        contestBatchBean.getContestDescription(),
                        contestCSVBean.getContestQuestion(),
                        ((contestBatchBean.getContestLogoId() == null) ? (1259173) : (contestBatchBean.getContestLogoId())),
                        ((contestBatchBean.getSponsorLogoId() == null) ? (0l) : (contestBatchBean.getSponsorLogoId())),
                        contestBatchBean.getPlanTemplateId(),
                        contestBatchBean.getScheduleTemplateId(),
                        contestBatchBean.getContestTier(),
                        contestBatchBean.getContestType());

                contestLinks.put("" + contest.getContestShortName(), "/web/guest/cms/-/contestmanagement/contestId/"
                        + contest.getContestPK() + "/tab/DESCRIPTION");

                processOntologyTerms(contestCSVBean, contest);
            }
        }
        model.addAttribute("newContestLinks", contestLinks);

        return "/batch/newContestsCreated";

    }

    private void processOntologyTerms(ContestCSVBean contestCSVBean, Contest contest) {
        List<Long> inputedOntologyTerms = null;
        Map<Long, Integer> uniqueSelectedOntologyTerms = new HashMap<>();

        if (contestCSVBean.getOntologyTerms() != null) {
            inputedOntologyTerms = IdListUtil.getIdsFromString(contestCSVBean.getOntologyTerms());
            try {
                for (Long termId : inputedOntologyTerms) {
                    OntologyTerm ontologyTerm = OntologyClientUtil.getOntologyTerm(termId);
                    if (ontologyTerm != null) {
                        uniqueSelectedOntologyTerms.put(ontologyTerm.getId(), 1);
                    }
                }

                Long focusAreaId = checkForExistingFocusArea(uniqueSelectedOntologyTerms);
                if (focusAreaId == 0l) {
                    FocusArea focusArea = new FocusArea();
                    focusArea = OntologyClientUtil.createFocusArea(focusArea);
                    focusAreaId = focusArea.getId_();

                    for (Map.Entry<Long, Integer> ontologyTerm : uniqueSelectedOntologyTerms.entrySet()) {
                        FocusAreaOntologyTermLocalServiceUtil.addAreaTerm(focusAreaId, ontologyTerm.getKey());

                    }
                    if (!reusableFocusArea.containsKey(focusAreaId)) {
                        reusableFocusArea.put(focusAreaId, uniqueSelectedOntologyTerms);
                    }
                }
                contest.setFocusAreaId(focusAreaId);
                contest.persist();

            } catch (SystemException | PortalException ignored) {
                _log.warn("Update contest overview failed with: ", ignored);
            }
        }
    }

    private Long checkForExistingFocusArea(Map<Long, Integer> selectedOntologyTerms) {
        if (!reusableFocusArea.isEmpty()) {
            Iterator<Long> it = reusableFocusArea.keySet().iterator();
            while (it.hasNext()) {
                Long focusArea = it.next();
                Map<Long, Integer> ontTermsInFocusArea = reusableFocusArea.get(focusArea);
                if (mapHasAllSelectedOntologyTerms(ontTermsInFocusArea, selectedOntologyTerms)) {
                    return focusArea;
                }
            }
        }
        return 0l;
    }

    private boolean mapHasAllSelectedOntologyTerms(Map<Long, Integer> ontTermsInFocusArea,
                                                   Map<Long, Integer> selectedOntologyTerms) {
        if (selectedOntologyTerms.size() == ontTermsInFocusArea.size()) {
            for (Map.Entry<Long, Integer> selectedOntTerm : selectedOntologyTerms.entrySet()) {
                if (!ontTermsInFocusArea.containsKey(selectedOntTerm.getKey())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    private Contest createContest(String contestShortName,
                                  String contestDescription,
                                  String contestQuestion,
                                  Long contestLogoId,
                                  Long sponsorLogoId,
                                  Long planTemplateId,
                                  Long contestScheduleId,
                                  Long contestTierId,
                                  Long contestTypeId) {


            Contest contest = ContestCreatorUtil.createNewContest(contestShortName);
            contest.setContestDescription(contestDescription);
            contest.setContestName(contestQuestion);
            contest.setContestLogoId(contestLogoId);
            contest.setSponsorLogoId(sponsorLogoId);
            contest.setContestYear(new Long(DateTime.now().getYear()));
            contest.setContestPrivate(true);
            contest.setShow_in_tile_view(true);
            contest.setShow_in_list_view(true);
            contest.setShow_in_outline_view(true);
            contest.setPlanTemplateId(planTemplateId);
            contest.setContestScheduleId(contestScheduleId);
            contest.setContestTier(contestTierId);
            contest.setContestTypeId(contestTypeId);
            ContestClientUtil.updateContest(contest);

            ContestScheduleUtil.changeScheduleForContest(contest, contestScheduleId);
            return contest;

    }

    private List<LabelValue> getProposalTemplateSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
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
        List<LabelValue> scheduleTemplateSelectionItems = ContestScheduleLifecycleUtil.getAllScheduleTemplateSelectionItems();
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
            for (ContestType contestType : ContestClientUtil
                    .getAllContestTypes()) {
                selectItems.add(new LabelValue(contestType.getId_(),
                        String.format("%d - %s with %s", contestType.getId_(),
                                contestType.getContestName(), contestType.getProposalNamePlural())));
            }

        return selectItems;
    }

    @ResourceMapping("validateOntologyTerm")
    public void validateOntologyTerm(ResourceRequest request, ResourceResponse response,
                                     @RequestParam(required = false) String ontologyTerms)
            throws IOException, SystemException, PortalException {
        JSONObject responseJSON = JSONFactoryUtil.createJSONObject();
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        List<Long> inputedOntologyTerms = IdListUtil.getIdsFromString(ontologyTerms);
        for (Long termId : inputedOntologyTerms) {
            OntologyTerm ontologyTerm = OntologyClientUtil.getOntologyTerm(termId);
            if (ontologyTerm != null) {
                JSONObject ontItem = JSONFactoryUtil.createJSONObject();
                ontItem.put("termId", ontologyTerm.getId());
                ontItem.put("name", ontologyTerm.getName());
                jsonArray.put(ontItem);

            }
        }
        responseJSON.put("validOntologyTerms", jsonArray);
        responseJSON.put("success", true);
        response.getPortletOutputStream().write(responseJSON.toString().getBytes());
    }
}
