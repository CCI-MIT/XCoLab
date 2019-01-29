package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.IProposalTemplateClient;
import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.IdListUtil;
import org.xcolab.commons.html.LabelValue;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.beans.ContestBatchBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestCSVBean;
import org.xcolab.view.pages.contestmanagement.utils.ContestCreatorUtil;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/contest")
public class BatchCreationController {

    private final Map<Long, Map<Long, Integer>> reusableFocusArea = new HashMap<>();

    @Autowired
    private IContestTypeClient contestTypeClient;

    @Autowired
    private IContestClient contestClient;

    @Autowired
    private IOntologyClient ontologyClient;

    @Autowired
    private IProposalTemplateClient proposalTemplateClient;
    
    @Autowired
    private IPermissionClient permissionClient;

    @ModelAttribute("proposalTemplateSelectionItems")
    public List<LabelValue> populateProposalTemplateSelectionItems() {
        return getProposalTemplateSelectionItems();
    }

    private List<LabelValue> getProposalTemplateSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        List<Long> excludedList =
                Arrays.asList(1L, 2L, 106L, 201L, 202L, 301L, 401L, 1000401L, 1000501L, 1300104L,
                        1300201L, 1300302L,
                        1300401L, 1300601L, 1300602L);
        for (IProposalTemplate proposalTemplate : proposalTemplateClient.getProposalTemplates()) {
            if (!excludedList.contains(proposalTemplate.getId())) {
                selectItems
                        .add(new LabelValue(proposalTemplate.getId(), proposalTemplate.getName()));
            }
        }

        return selectItems;
    }

    @ModelAttribute("scheduleTemplateSelectionItems")
    public List<LabelValue> populateScheduleSelectionItems() {
        return getContestScheduleSelectionItems();
    }

    private List<LabelValue> getContestScheduleSelectionItems() {
        return ContestScheduleLifecycleUtil.getAllScheduleTemplateSelectionItems();
    }

    @ModelAttribute("contestLevelSelectionItems")
    public List<LabelValue> populateContestLevelSelectionItems() {
        return getContestLevelSelectionItems();
    }

    private List<LabelValue> getContestLevelSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestTier contestLevel : ContestTier.values()) {
            selectItems.add(new LabelValue(contestLevel.getTierType(), contestLevel.getTierName()));
        }
        return selectItems;
    }

    @ModelAttribute("contestTypeSelectionItems")
    public List<LabelValue> populateContestTypeSelectionItems() {
        return getContestTypeSelectionItems();
    }

    private List<LabelValue> getContestTypeSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestType contestType : contestTypeClient.getAllContestTypes()) {
            selectItems.add(new LabelValue(contestType.getId(),
                    String.format("%d - %s with %s", contestType.getId(),
                            contestType.getContestName(), contestType.getProposalNamePlural())));
        }

        return selectItems;
    }

    @GetMapping("manager/batchCreateContest")
    public String batchCreateContestController(HttpServletRequest request, Model model,
            HttpServletResponse response, UserWrapper member) {

        if (!permissionClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("contestBatchBean", new ContestBatchBean());
        return "contestmanagement/batch/uploadContestCSV";
    }

    @PostMapping("manager/batchCreateContest")
    public String createBatchContestController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member,
            @Valid ContestBatchBean contestBatchBean, BindingResult result)
            throws IOException {

        if (!permissionClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        Map<String, String> contestLinks = new LinkedHashMap<>();

        // should check for valid data from CVS even after the fact.
        List<ContestCSVBean> contestsToBeCreated = contestBatchBean.getContestCSVs();
        if (contestsToBeCreated != null && !contestsToBeCreated.isEmpty()) {
            for (ContestCSVBean contestCSVBean : contestsToBeCreated) {

                ContestWrapper contest = createContest(contestCSVBean.getContestShortName(),
                        contestBatchBean.getContestDescription(),
                        contestCSVBean.getContestQuestion(),
                        ((contestBatchBean.getContestLogoId() == null) ? (1259173)
                                : (contestBatchBean.getContestLogoId())),
                        ((contestBatchBean.getSponsorLogoId() == null) ? (0L)
                                : (contestBatchBean.getSponsorLogoId())),
                        ((contestBatchBean.getSponsorLink() == null) ? ("")
                                : (contestBatchBean.getSponsorLink())),
                        contestBatchBean.getProposalTemplateId(),
                        contestBatchBean.getScheduleTemplateId(),
                        contestBatchBean.getContestTier(),
                        contestBatchBean.getContestType(), member.getId());

                contestLinks.put("" + contest.getTitle(),
                        "/admin/contest/details/contestId/"
                                + contest.getId() + "/tab/DESCRIPTION");

                processOntologyTerms(contestCSVBean, contest);
            }
        }
        model.addAttribute("newContestLinks", contestLinks);

        return "contestmanagement/batch/newContestsCreated";
    }

    private void processOntologyTerms(ContestCSVBean contestCSVBean, ContestWrapper contest) {

        if (contestCSVBean.getOntologyTerms() != null) {
            List<Long> inputOntologyTerms =
                    IdListUtil.getIdsFromString(contestCSVBean.getOntologyTerms());
            Map<Long, Integer> uniqueSelectedOntologyTerms = new HashMap<>();
            for (Long termId : inputOntologyTerms) {
                OntologyTermWrapper ontologyTerm = ontologyClient.getOntologyTerm(termId);
                if (ontologyTerm != null) {
                    uniqueSelectedOntologyTerms.put(ontologyTerm.getId(), 1);
                }
            }

            Long focusAreaId = checkForExistingFocusArea(uniqueSelectedOntologyTerms);
            if (focusAreaId == 0L) {
                FocusAreaWrapper focusArea = new FocusAreaWrapper();
                focusArea = ontologyClient.createFocusArea(focusArea);
                focusAreaId = focusArea.getId();

                for (Map.Entry<Long, Integer> ontologyTerm : uniqueSelectedOntologyTerms.entrySet()) {
                    ontologyClient.addOntologyTermsToFocusAreaByOntologyTermId(focusAreaId,
                            ontologyTerm.getKey());
                }
                if (!reusableFocusArea.containsKey(focusAreaId)) {
                    reusableFocusArea.put(focusAreaId, uniqueSelectedOntologyTerms);
                }
            }
            contest.setFocusAreaId(focusAreaId);
            contest.persist();
        }
    }

    private Long checkForExistingFocusArea(Map<Long, Integer> selectedOntologyTerms) {
        if (!reusableFocusArea.isEmpty()) {
            for (Long focusArea : reusableFocusArea.keySet()) {
                Map<Long, Integer> ontTermsInFocusArea = reusableFocusArea.get(focusArea);
                if (mapHasAllSelectedOntologyTerms(ontTermsInFocusArea, selectedOntologyTerms)) {
                    return focusArea;
                }
            }
        }
        return 0L;
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

    private ContestWrapper createContest(String contestShortName,
            String contestDescription,
            String contestQuestion,
            Long contestLogoId,
            Long sponsorLogoId,
            String sponsorLink,
            Long proposalTemplateId,
            Long contestScheduleId,
            Long contestTierId,
            Long contestTypeId,
            long authorUserId) {

        ContestWrapper contest = ContestCreatorUtil.createNewContest(contestShortName, authorUserId);
        contest.setDescription(contestDescription);
        contest.setQuestion(contestQuestion);
        contest.setContestLogoId(contestLogoId);
        contest.setSponsorLogoId(sponsorLogoId);
        contest.setSponsorLink(sponsorLink);
        contest.setContestYear((long) DateTime.now().getYear());
        contest.setContestPrivate(true);
        contest.setShowInTileView(true);
        contest.setShowInListView(true);
        contest.setShowInOutlineView(true);
        contest.setProposalTemplateId(proposalTemplateId);
        contest.setContestScheduleId(contestScheduleId);
        contest.setContestTier(contestTierId);
        contest.setContestTypeId(contestTypeId);
        contestClient.updateContest(contest);

        contest.changeScheduleTo(contestScheduleId);
        return contest;

    }

    @PostMapping("api/validateOntologyTerm")
    public void validateOntologyTerm(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String ontologyTerms)
            throws IOException {
        JSONObject responseJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        List<Long> inputOntologyTerms = IdListUtil.getIdsFromString(ontologyTerms);
        for (Long termId : inputOntologyTerms) {
            OntologyTermWrapper ontologyTerm = ontologyClient.getOntologyTerm(termId);
            if (ontologyTerm != null) {
                JSONObject ontItem = new JSONObject();
                ontItem.put("termId", ontologyTerm.getId());
                ontItem.put("name", ontologyTerm.getName());
                jsonArray.put(ontItem);

            }
        }
        responseJSON.put("validOntologyTerms", jsonArray);
        responseJSON.put("success", true);
        response.getOutputStream().write(responseJSON.toString().getBytes());
    }
}
