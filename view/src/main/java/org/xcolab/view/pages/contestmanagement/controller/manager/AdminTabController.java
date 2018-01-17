package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.util.enums.contest.ContestPhaseTypeValue;
import org.xcolab.util.html.LabelValue;
import org.xcolab.view.activityentry.ActivityEntryHelper;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.beans.ProposalReportBean;
import org.xcolab.view.pages.contestmanagement.beans.VotingReportBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.utils.ActivityCsvWriter;
import org.xcolab.view.pages.contestmanagement.utils.ContestCsvWriter;
import org.xcolab.view.pages.contestmanagement.utils.ProposalCsvWriter;
import org.xcolab.view.pages.contestmanagement.utils.VoteCsvWriter;
import org.xcolab.view.pages.loginregister.LoginRegisterService;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;
import org.xcolab.view.util.entity.EntityIdListUtil;
import org.xcolab.view.util.entity.enums.MemberRole;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class AdminTabController extends AbstractTabController {

    private static final Logger log = LoggerFactory.getLogger(AdminTabController.class);

    private static final ContestManagerTabs tab = ContestManagerTabs.ADMIN;
    private static final String TAB_VIEW = "contestmanagement/manager/adminTab";
    private static final Attributes.Name MANIFEST_ATTRIBUTE_GIT_COMMIT
            = new Attributes.Name("Git-Commit");

    private final LoginRegisterService loginRegisterService;
    private final ServletContext servletContext;
    private final ActivityEntryHelper activityEntryHelper;

    @Autowired
    public AdminTabController(LoginRegisterService loginRegisterService,
            ServletContext servletContext, ActivityEntryHelper activityEntryHelper) {
        this.loginRegisterService = loginRegisterService;
        this.servletContext = servletContext;
        this.activityEntryHelper = activityEntryHelper;
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

    @ModelAttribute("contestSelectionItems")
    public List<LabelValue> contestSelectionItems() {
        return ContestClientUtil.getAllContests()
                .stream()
                .sorted(Comparator.comparing(Contest::getContestPK).reversed())
                .map(contest -> {
                    final String contestName = contest.getContestShortName();
                    final Long contestId = contest.getContestPK();
                    return new LabelValue(contestId, String.format("%d - %s", contestId, contestName));
                })
                .collect(Collectors.toList());
    }

    @GetMapping("tab/ADMIN")
    public String showAdminTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member) {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        model.addAttribute("votingReportBean", new VotingReportBean());
        model.addAttribute("proposalReportBean", new ProposalReportBean());

        List<Notification> list = AdminClient.getNotifications();
        model.addAttribute("listOfNotifications", list);

        model.addAttribute("buildCommit", getBuildCommit());
        model.addAttribute("javaVersion", Runtime.class.getPackage().getImplementationVersion());
        model.addAttribute("javaVendor", Runtime.class.getPackage().getImplementationVendor());

        return TAB_VIEW;
    }

    private String getBuildCommit() {
        try (final InputStream manifestInputStream =
                servletContext.getResourceAsStream("/META-INF/MANIFEST.MF")) {
            if (manifestInputStream != null) {
                final Manifest manifest = new Manifest(manifestInputStream);
                final Attributes mainAttributes = manifest.getMainAttributes();
                if (mainAttributes.containsKey(MANIFEST_ATTRIBUTE_GIT_COMMIT)) {
                    return (String) mainAttributes.get(MANIFEST_ATTRIBUTE_GIT_COMMIT);
                } else {
                    log.warn("Manifest does not contain 'Git-Commit' attribute.");
                }
            } else {
                log.error("Could not open input stream for manifest.");
            }
        } catch (IOException e) {
            log.error("Exception while opening input stream for manifest: {}", e.getMessage() );
        }
        return "unknown";
    }

    @GetMapping("tab/ADMIN/votingReport")
    public void generateVotingReport(HttpServletRequest request, HttpServletResponse response,
            VotingReportBean votingReportBean) throws IOException {
        if (!tabWrapper.getCanView()) {
            ErrorText.ACCESS_DENIED.flashAndRedirect(request, response);
            return;
        }

        try (VoteCsvWriter csvWriter = new VoteCsvWriter(response)) {
            votingReportBean.getVotingPhaseIds().stream()
                    .map(ProposalMemberRatingClientUtil::getProposalVotesInPhase)
                    .forEach(csvWriter::writeVotes);
        }
    }

    @GetMapping("tab/ADMIN/proposalReport")
    public void generateProposalReport(HttpServletRequest request, HttpServletResponse response,
            ProposalReportBean proposalReportBean) throws IOException {
        if (!tabWrapper.getCanView()) {
            ErrorText.ACCESS_DENIED.flashAndRedirect(request, response);
            return;
        }

        try (ProposalCsvWriter csvWriter = new ProposalCsvWriter(response)) {
            final List<Contest> contests =
                    EntityIdListUtil.CONTESTS.fromIdList(proposalReportBean.getContestIds());

            contests.forEach(csvWriter::writeProposalsInContest);
        }
    }

    @GetMapping("tab/ADMIN/contestReport")
    public void generateContestReport(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (!tabWrapper.getCanView()) {
            ErrorText.ACCESS_DENIED.flashAndRedirect(request, response);
            return;
        }

        try (ContestCsvWriter csvWriter = new ContestCsvWriter(response)) {
            csvWriter.writeContests(ContestClientUtil.getAllContests());
        }
    }

    @PostMapping("tab/ADMIN/exportActivities")
    public void exportActivities(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (!tabWrapper.getCanView()) {
            ErrorText.ACCESS_DENIED.flashAndRedirect(request, response);
            return;
        }

        try (ActivityCsvWriter csvWriter = new ActivityCsvWriter(response, activityEntryHelper)) {
            ActivitiesClientUtil.getActivityEntries(0, Integer.MAX_VALUE, null, null)
                    .forEach(csvWriter::writeActivity);
        }
    }

    @PostMapping("tab/ADMIN/batchRegister")
    public String batchRegisterMembers(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String members, @RequestParam(defaultValue = "false") Boolean asGuests) {
        final String[] memberStrings = members.split("\\r\\n|\\n|\\r");
        for (String memberString : memberStrings) {
            final String[] values = memberString.split(";");
            if (values.length != 3) {
                throw new IllegalArgumentException();
            }
            String email = values[0];
            String firstName = values[1];
            String lastName = values[2];
            Member member = loginRegisterService.autoRegister(email, firstName, lastName);
            if (asGuests) {
                MembersClient.assignMemberRole(member.getId_(), MemberRole.GUEST.getRoleId());
                MembersClient.removeMemberRole(member.getId_(), MemberRole.MEMBER.getRoleId());
            }
        }
        AlertMessage.CHANGES_SAVED.flash(request);
        return "redirect:" + tab.getTabUrl();
    }

    @PostMapping("tab/ADMIN/notificationMessageDelete")
    public String saveNotification(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String notificationId, Member loggedInMember)
            throws IOException {

        if (!PermissionsClient.canAdminAll(loggedInMember)) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }

        AdminClient.deleteNotifications(notificationId);

        AlertMessage.DELETED.flash(request);
        return "redirect:" + tab.getTabUrl();
    }

    @PostMapping("tab/ADMIN/notificationMessageCreate")
    public String saveNotification(HttpServletRequest request,
            HttpServletResponse response, Member member, @RequestParam String notificationText,
            @RequestParam String expiretime)
            throws IOException, ParseException {

        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        expiretime = expiretime.replace("T", " ");

        if (expiretime.length() < "yyyy-MM-dd hh:mm:ss".length()) {
            expiretime = expiretime + ":00";
        }


        Date endDate = formatter.parse(expiretime);

        if (endDate.before(new Date())) {
            AlertMessage.danger("Expiry date cannot be in the past. "
                    + "Notification will not be saved!")
                    .flash(request);
            return "redirect:" + tab.getTabUrl();
        }

        Notification newNotification = new Notification();
        newNotification.setNotificationText(notificationText);
        newNotification.setEndTime(endDate);

        AdminClient.setNotifications(newNotification);

        AlertMessage.CREATED.flash(request);
        return "redirect:" + tab.getTabUrl();
    }
}
