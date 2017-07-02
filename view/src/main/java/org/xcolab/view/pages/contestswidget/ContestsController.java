package org.xcolab.view.pages.contestswidget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContestsController {

    private static final Logger _log = LoggerFactory.getLogger(ContestsController.class);
    
    public ContestsController() {
    }

    @GetMapping("/contestswidget")
    public String showContests(@RequestParam(required = false) String preferenceId, HttpServletRequest request, HttpServletResponse response, Model model)  {

        ContestPreferences contestPreferences = new ContestPreferences(preferenceId);

        List<Contest> contestWrappers = new ArrayList<>();
        final List<Long> selectedContests = contestPreferences.getSelectedContests();
        if (selectedContests.isEmpty()) {

            List<Contest> contests = ContestClientUtil.findContestsByActiveFeatured(true, true);
            Collections.shuffle(contests);
            for (Contest contest : contests) {
                if (contestWrappers.size() >= contestPreferences.getFeedSize()) {
                    break;
                }
                if (!contest.getContestPrivate()) {
                    if (contest.getIsSharedContestInForeignColab()) {
                        RestService contestService = new RefreshingRestService(CoLabService.CONTEST,
                                ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
                        );
                        Contest foreignContest = ContestClient.fromService(contestService).getContest(contest.getContestPK());
                        foreignContest.setUpForeignContestVisualConfigsFromLocal(contest);
                        contestWrappers.add(foreignContest);
                    } else {
                        contestWrappers.add(contest);
                    }
                }
            }
        } else {
            Collections.shuffle(selectedContests);
            for (Long contestId : selectedContests) {
                if (contestWrappers.size() >= contestPreferences.getFeedSize()) {
                    break;
                }
                try {
                    Contest c = ContestClientUtil.getContest(contestId);
                    if(c.getIsSharedContestInForeignColab()){
                        RestService contestService = new RefreshingRestService(CoLabService.CONTEST,
                                ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
                        );
                        Contest foreignContest = ContestClient.fromService(contestService).getContest(contestId);
                        foreignContest.setUpForeignContestVisualConfigsFromLocal(c);
                        contestWrappers.add(foreignContest);
                    }else {
                        contestWrappers.add(c);
                    }
                } catch (ContestNotFoundException e) {
                    _log.error("Could not find contest {}", contestId);
                }
            }
        }


        model.addAttribute("contests", contestWrappers);
        model.addAttribute("contestPreferences", contestPreferences);
        //TODO: allow setting on a per-contest/per portlet basis
        model.addAttribute("contestType",
                ContestTypeClient.getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get()));
        return "contestswidget/showContests";
    }
}
