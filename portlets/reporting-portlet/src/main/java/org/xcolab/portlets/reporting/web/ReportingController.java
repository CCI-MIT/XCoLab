package org.xcolab.portlets.reporting.web;

import au.com.bytecode.opencsv.CSVWriter;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalVoteLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.reporting.beans.AuthorAttractionBean;
import org.xcolab.portlets.reporting.beans.UserActivityReportBean;
import org.xcolab.portlets.reporting.beans.activitiesbycontest.ActivitiesByContestBean;
import org.xcolab.portlets.reporting.beans.activitiesbycontest.ContestActivity;
import org.xcolab.portlets.reporting.beans.activitiesbycontest.UserActivityByContest;
import org.xcolab.portlets.reporting.beans.contests.ContestFetcher;
import org.xcolab.portlets.reporting.beans.proposalactivityexport.ProposalActivities;
import org.xcolab.portlets.reporting.beans.proposalactivityexport.ProposalActivityExport;
import org.xcolab.portlets.reporting.beans.proposalsinyear.ProposalWithFinalistAndContent;
import org.xcolab.portlets.reporting.beans.proposalsinyear.ProposalsInSpecificContests;
import org.xcolab.portlets.reporting.beans.proposalsinyear.proposalversiondeterminer.GetLastVersionOfPhaseType;
import org.xcolab.portlets.reporting.beans.proposaltextextraction.ProposalTextEntity;
import org.xcolab.portlets.reporting.beans.proposaltextextraction.ProposalTextExtraction;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("view")
@Controller
public class ReportingController {

    public static final int FIELDS_PER_CONTEST = 4;

    @RequestMapping
    public String showHomePage(RenderRequest request) {
        return "index";
    }

    @RequestMapping(params="report=getProposalActivity2013")
    public void getProposalActivity(ResourceRequest request, ResourceResponse response) throws Exception {
        ProposalActivityExport pae = new ProposalActivityExport();

        Writer w = response.getWriter();
        CSVWriter csvWriter = new CSVWriter(w);

        csvWriter.writeNext(new String[]{"id", "daysCreatedBeforeFinalist", "numUpdatesBeforeFinalist", "numUpdatesOnDifferentDaysBeforeFinalist", "authorId", "teamMemberIds"});

        for (ProposalActivities a : pae.get()) {
            String authorIds = "";
            for (User u : ProposalLocalServiceUtil.getMembers(a.getProposal().getProposalId())) {
                if (!"".equals(authorIds)) authorIds += " - ";
                authorIds += u.getUserId();
            }

            csvWriter.writeNext(new String[]{
                    ""+a.getProposal().getProposalId(),
                    ""+a.getNumDaysCreationIsBeforeFinalistSelection(),
                    ""+a.getNumUpdates(),
                    ""+a.getNumDifferentDaysProposalUpdates(),
                    ""+a.getProposal().getAuthorId(),
                    ""+authorIds
            });
        }

        response.setContentType("text/csv");
        response.addProperty("Content-Disposition", "attachment;filename=proposalActivities.csv");

        w.close();
    }

    @RequestMapping(params="report=generateProposalTexts2013")
    public void generateProposalTexts2013(ResourceRequest request, ResourceResponse response) throws Exception {
        ProposalTextExtraction pte = new ProposalTextExtraction();

        Writer w = response.getWriter();
        CSVWriter csvWriter = new CSVWriter(w);

        csvWriter.writeNext(new String[]{"id", "url", "rank","winsjudges", "winspopular","text", "htmlcontent"});

        for (ProposalTextEntity e : pte.get()) {
            csvWriter.writeNext(new String[]{
                    ""+e.getId(),
                    e.getUrl(),
                    e.getRank().fileNum+"",
                    e.isProposalWinsJudgesChoice() ? "1" : "0",
                    e.isProposalWinsPopularChoice() ? "1" : "0",
                    e.getContent(),
                    e.getHtmlContent(),
            });
        }

        response.setContentType("text/csv");
        response.addProperty("Content-Disposition", "attachment;filename=proposalTexts.csv");

        w.close();
    }


    @RequestMapping(params="report=generateProposalTexts2014CreationPhase")
    public void generateProposalTexts2014CreationPhase(ResourceRequest request, ResourceResponse response) throws Exception {
        ProposalsInSpecificContests pic = new ProposalsInSpecificContests();
        pic.setProposalVersionDeterminer(new GetLastVersionOfPhaseType(1L,
                ContestFetcher.getContestPhasesIn2014()));

        Writer w = response.getWriter();
        CSVWriter csvWriter = new CSVWriter(w);

        csvWriter.writeNext(new String[]{"id","proposal_name", "url", "contest", "version_in_creation", "ribbon_type_in_completed", "content", "content_with_section_titles"});

        for (ProposalWithFinalistAndContent e : pic.get(ContestFetcher.getContestsIn2014(), true)) {
            csvWriter.writeNext(new String[]{
                    ""+e.getId(),
                    e.getProposalName(),
                    e.getUrl(),
                    e.getContestName(),
                    ""+e.getUsedVersion(),
                    ""+e.getProposalRibbon(),
                    e.getContent(),
                    e.getContentWithSectionTitles()
            });
        }

        response.setContentType("text/csv");
        response.addProperty("Content-Disposition", "attachment;filename=proposals2013creation.csv");

        w.close();
    }

    @RequestMapping(params="report=generateProposalTexts2013CreationPhase")
    public void generateProposalTexts2013CreationPhase(ResourceRequest request, ResourceResponse response) throws Exception {
        ProposalsInSpecificContests pic = new ProposalsInSpecificContests();
        pic.setProposalVersionDeterminer(new GetLastVersionOfPhaseType(11L));

        Writer w = response.getWriter();
        CSVWriter csvWriter = new CSVWriter(w);

        csvWriter.writeNext(new String[]{"id","proposal_name", "url", "contest", "version_in_creation", "ribbon_type_in_completed", "content", "content_with_section_titles"});

        for (ProposalWithFinalistAndContent e : pic.get()) {
            csvWriter.writeNext(new String[]{
                    ""+e.getId(),
                    e.getProposalName(),
                    e.getUrl(),
                    e.getContestName(),
                    ""+e.getUsedVersion(),
                    ""+e.getProposalRibbon(),
                    e.getContent(),
                    e.getContentWithSectionTitles()
            });
        }

        response.setContentType("text/csv");
        response.addProperty("Content-Disposition", "attachment;filename=proposals2013creation.csv");

        w.close();
    }


    @RequestMapping(params="report=authorAttractionReport")
    public void generateAuthorAttractionReport(ResourceRequest request, ResourceResponse response) throws Exception {
        AuthorAttractionBean aab = new AuthorAttractionBean();

        Writer w = response.getWriter();
        CSVWriter csvWriter = new CSVWriter(w);

        csvWriter.writeNext(new String[]{"authorScreenname", "voterScreenname"});
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");

        for (AuthorAttractionBean.UserAttractionPairs pairs : aab.getSupportersOfFinalistsThatRegisteredBeforeVoting()) {
            csvWriter.writeNext(new String[]{
                    pairs.getAuthor().getScreenName(),
                    pairs.getVoter().getScreenName()
            });
        }


        response.setContentType("text/csv");
        response.addProperty("Content-Disposition", "attachment;filename=userAttractionReport.csv");


        w.close();
    }

    @RequestMapping(params="report=userActivitiesByContestReport")
    public void generateUserActivityReport(ResourceRequest request, ResourceResponse response) throws Exception {
        AuthorAttractionBean aab = new AuthorAttractionBean();

        Writer w = response.getWriter();
        CSVWriter csvWriter = new CSVWriter(w);

        String[] initialCells = {"user ID", "user screenname", "user email"};
        List<Contest> contests = ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE);
        String[] contestNames = new String[contests.size()* FIELDS_PER_CONTEST];
        for (int i = 0; i < contests.size(); i++) {
            Contest contest = contests.get(i);
            contestNames[i* FIELDS_PER_CONTEST] = contest.getContestShortName()+" / proposal count";
            contestNames[i* FIELDS_PER_CONTEST +1] = contest.getContestShortName()+" / proposal-comments count";
            contestNames[i* FIELDS_PER_CONTEST +2] = contest.getContestShortName()+" / votes count";
            contestNames[i* FIELDS_PER_CONTEST +3] = contest.getContestShortName()+" / supports count";
        }
        String[] csvHeader = new String[contestNames.length+initialCells.length];
        for (int i = 0; i < csvHeader.length; i++) {
            if(i < initialCells.length)
                csvHeader[i] = initialCells[i];
            else {
                int targetIndex = i-initialCells.length;
                csvHeader[i] = contestNames[targetIndex];
            }
        }

        csvWriter.writeNext(csvHeader);

        for (UserActivityByContest activityByContest : new ActivitiesByContestBean().get()) {
            String[] dataArray = new String[csvHeader.length];
            //initial cells
            dataArray[0] = activityByContest.getUser().getPrimaryKey()+"";
            dataArray[1] = activityByContest.getUser().getScreenName();
            dataArray[2] = activityByContest.getUser().getDisplayEmailAddress();

            //contest cells
            for (int i = 0; i < contests.size(); i++) {
                Contest contest = contests.get(i);
                ContestActivity ca= null;
                for (ContestActivity contestActivity : activityByContest.getContestActivities()) {
                    if(contestActivity.getContest().getContestPK() == contest.getContestPK()) {
                        ca = contestActivity;
                    }
                }
                dataArray[i* FIELDS_PER_CONTEST +initialCells.length] = ca == null ? "0" : ca.getAuthoredProposalCount()+"";
                dataArray[i* FIELDS_PER_CONTEST +initialCells.length+1] = ca == null ? "0" :ca.getCommentCount()+"";
                dataArray[i* FIELDS_PER_CONTEST +initialCells.length+2] = ca == null ? "0" :ca.getVotedProposalCount()+"";
                dataArray[i* FIELDS_PER_CONTEST +initialCells.length+3] = ca == null ? "0" :ca.getSupportedProposalCount()+"";
            }

            csvWriter.writeNext(dataArray);
        }

        response.setContentType("text/csv");
        response.addProperty("Content-Disposition", "attachment;filename=userAttractionReport.csv");


        w.close();
    }

    @RequestMapping(params = "report=userActivitiesReport")
    public void generateUserActivitiesReport(ResourceRequest request, ResourceResponse response) throws IOException, SystemException, PortalException {

        Map<Long, UserActivityReportBean> userActivities = new HashMap<>();

        for (User user : UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE)) {
            userActivities.put(user.getUserId(), new UserActivityReportBean(user));
        }
        for (SocialActivity activity : SocialActivityLocalServiceUtil.getSocialActivities(0, Integer.MAX_VALUE)) {
            if (!userActivities.containsKey(activity.getUserId())) continue;
            userActivities.get(activity.getUserId()).addActivity();
        }

        for (DiscussionMessage message : DiscussionMessageLocalServiceUtil.getDiscussionMessages(0, Integer.MAX_VALUE)) {
            if (!userActivities.containsKey(message.getAuthorId())) continue;
            userActivities.get(message.getAuthorId()).addComment();
        }
        Map<Long, Set<Long>> proposalToUserIds = new HashMap<>();

        for (Proposal proposal : ProposalLocalServiceUtil.getProposals(0, Integer.MAX_VALUE)) {
            //determine all team members of this proposal
            for (User user : ProposalLocalServiceUtil.getMembers(proposal.getProposalId())) {
                if (!userActivities.containsKey(user.getUserId())) continue;

                if (proposalToUserIds.get(proposal.getProposalId()) == null) {
                    Set<Long> userIds = new HashSet<Long>();
                    userIds.add(user.getUserId());
                    proposalToUserIds.put(proposal.getProposalId(), userIds);
                } else {
                    proposalToUserIds.get(proposal.getProposalId()).add(user.getUserId());
                }
            }
            //also add the proposal author's id (because it's a set, it cannot occur twice)
            if (userActivities.containsKey(proposal.getAuthorId())) {
                proposalToUserIds.get(proposal.getProposalId()).add(proposal.getAuthorId());
            }
            //increase the proposal count for the users
            for (Long userId : proposalToUserIds.get(proposal.getProposalId())) {
                userActivities.get(userId).addProposal();
            }
        }

        Set<Long> winningRibbonTypes = new HashSet<>();
        for (ContestPhaseRibbonType cprt : ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonTypes(0, Integer.MAX_VALUE)) {
            if (cprt.getRibbon() == 1) winningRibbonTypes.add(cprt.getId());
        }

        Set<Long> finalistsContestPhases = new HashSet<>();
        for (ContestPhase cp : ContestPhaseLocalServiceUtil.getContestPhases(0, Integer.MAX_VALUE)) {
            if (ContestStatus.valueOf(ContestPhaseLocalServiceUtil.getContestStatusStr(cp)).isCanVote()) {
                finalistsContestPhases.add(cp.getContestPhasePK());
            }
        }


        for (ProposalContestPhaseAttribute pcpa : ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributes(0, Integer.MAX_VALUE)) {
            if (proposalToUserIds.get(pcpa.getProposalId()) != null) {
                for (Long userId : proposalToUserIds.get(pcpa.getProposalId())) {
                    UserActivityReportBean uarb = userActivities.get(userId);
                    if (uarb == null) continue;
                    if (ProposalContestPhaseAttributeKeys.RIBBON.equals(pcpa.getName()) && winningRibbonTypes.contains(pcpa.getNumericValue())) {
                        uarb.addProposalWinner();
                    }
                }
            }
        }

        for (ProposalVote proposalVote : ProposalVoteLocalServiceUtil.getProposalVotes(0, Integer.MAX_VALUE)) {
            UserActivityReportBean uarb = userActivities.get(proposalVote.getUserId());
            if(uarb == null) continue;
            uarb.addProposalVote();
        }

        for (Proposal2Phase p2p : Proposal2PhaseLocalServiceUtil.getProposal2Phases(0, Integer.MAX_VALUE)) {
            if (proposalToUserIds.get(p2p.getProposalId()) != null) {
                for (Long userId : proposalToUserIds.get(p2p.getProposalId())) {
                    UserActivityReportBean uarb = userActivities.get(userId);
                    if (uarb == null) continue;
                    if (finalistsContestPhases.contains(p2p.getContestPhaseId())) {
                        uarb.addProposalFinalist();
                    }
                }
            }
        }

        Writer w = response.getWriter();
        CSVWriter csvWriter = new CSVWriter(w);

        csvWriter.writeNext(new String[]{"userId", "screenName", "emailAddress", "registrationDate", "fullName", "commentsCount", "proposalsCount", "proposalFinalistsCount", "proposalWinnersCount", "totalActivityCount", "amountOfVotesCast", "userRole"});
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        for (UserActivityReportBean uarb : userActivities.values()) {
            User u = uarb.getUser();
            //get highest role of user
            List<Role> roles = u.getRoles();
            MemberRole currentRole;
            MemberRole role = MemberRole.MEMBER;

            for (Role r: roles) {
                final String roleString = r.getName();

                currentRole = MemberRole.getMember(roleString);
                if (currentRole != null && role != null) {
                    if (currentRole.ordinal() > role.ordinal()) {
                        role = currentRole;
                    }
                }
            }

            csvWriter.writeNext(new String[]{String.valueOf(u.getUserId()), u.getScreenName(),
                    u.getEmailAddress(),
                    sdf.format(u.getCreateDate()),
                    u.getFullName(),
                    String.valueOf(uarb.getCommentsCount()), String.valueOf(uarb.getProposalsCount()), String.valueOf(uarb.getProposalFinalistsCount()),
                    String.valueOf(uarb.getProposalWinnersCount()), String.valueOf(uarb.getTotalActivityCount()),
                    ""+uarb.getProposalVotesCount(),
                    String.valueOf(role.ordinal())});
        }


        response.setContentType("text/csv");
        response.addProperty("Content-Disposition", "attachment;filename=userActivitiesReport.csv");


        w.close();
    }

}
