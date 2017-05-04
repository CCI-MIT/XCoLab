package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.pojo.Notification;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.util.html.LabelValue;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.beans.VotingReportBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.utils.ActivityCsvConverter;
import org.xcolab.view.pages.contestmanagement.utils.VoteCsvConverter;
import org.xcolab.view.pages.loginregister.LoginRegisterService;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;
import org.xcolab.view.util.entity.enums.ContestPhaseTypeValue;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class AdminTabController extends AbstractTabController {

    private static final ContestManagerTabs tab = ContestManagerTabs.ADMIN;
    private static final String TAB_VIEW = "contestmanagement/manager/adminTab";

    private final LoginRegisterService loginRegisterService;

    @Autowired
    public AdminTabController(LoginRegisterService loginRegisterService) {
        this.loginRegisterService = loginRegisterService;
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @ModelAttribute("votingPhaseSelectionItems")
    public List<LabelValue> votingPhaseSelectionItems() {
        final List<ContestPhase> contestPhasesByType = new ArrayList<>(ContestClientUtil
                .getContestPhasesByType(ContestPhaseTypeValue.VOTING_PHASE_SOLVE.getTypeId()));
        //TODO: don't hard code phase types
        contestPhasesByType.addAll(ContestClientUtil.getContestPhasesByType(20L));

        final Date now = new Date();
        return contestPhasesByType
                .stream()
                .filter(p -> p.getContestPK() != 0L)
                .filter(p -> p.getPhaseStartDateDt().before(now))
                .sorted(Comparator.comparing(ContestPhase::getPhaseStartDate).reversed())
                .map(contestPhase -> {
                    final String contestName = contestPhase.getContest().getContestShortName();
                    final Long phaseId = contestPhase.getContestPhasePK();
                    return new LabelValue(phaseId, String.format("%d in %s", phaseId, contestName));
                })
                .collect(Collectors.toList());
    }

    @GetMapping("tab/ADMIN")
    public String showEditorsTabController(HttpServletRequest request, HttpServletResponse response,
            Model model) {
        if (!tabWrapper.getCanView()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
        setPageAttributes(request, model, tab);
        model.addAttribute("votingReportBean", new VotingReportBean());
        
        List<Notification> list = null;
        try {
            list = AdminClient.getNotifications();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        model.addAttribute("listOfNotifications", list);

        
        return TAB_VIEW;
    }

    @GetMapping("tab/ADMIN/votingReport")
    public void generateVotingReport(HttpServletRequest request, HttpServletResponse response,
            VotingReportBean votingReportBean) throws IOException {
        if (!tabWrapper.getCanView()) {
            ErrorText.ACCESS_DENIED.flashAndRedirect(request, response);
            return;
        }

        VoteCsvConverter csvConverter = new VoteCsvConverter();
        votingReportBean.getVotingPhaseIds().stream()
                .map(ProposalMemberRatingClientUtil::getProposalVotesInPhase)
                .forEach(csvConverter::addVotes);
        csvConverter.initiateDownload("votingReport", response);
    }

    @PostMapping("tab/ADMIN/exportActivities")
    public void exportActivities(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (!tabWrapper.getCanView()) {
            ErrorText.ACCESS_DENIED.flashAndRedirect(request, response);
            return;
        }

        ActivityCsvConverter csvConverter = new ActivityCsvConverter();
        ActivitiesClientUtil.getActivityEntries(0, Integer.MAX_VALUE, null, null)
                .forEach(csvConverter::addActivity);

        csvConverter.initiateDownload("activityReport", response);
    }

    @PostMapping("tab/ADMIN/batchRegister")
    public String batchRegisterMembers(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String members) {
        final String[] memberStrings = members.split("\\r\\n|\\n|\\r");
        for (String memberString : memberStrings) {
            final String[] values = memberString.split(";");
            if (values.length != 3) {
                throw new IllegalArgumentException();
            }
            String email = values[0];
            String firstName = values[1];
            String lastName = values[2];
            loginRegisterService.autoRegister(email, firstName, lastName);
        }
        AlertMessage.CHANGES_SAVED.flash(request);
        return "redirect:" + tab.getTabUrl();
    }
}
