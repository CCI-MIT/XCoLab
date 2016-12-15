package org.xcolab.portlets.contests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
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

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestsController {

    private static final Logger _log = LoggerFactory.getLogger(ContestsController.class);
    
    public ContestsController() {
    }

    @RequestMapping
    public String showContests(PortletRequest request, PortletResponse response, Model model)  {

        ContestPreferences contestPreferences = new ContestPreferences(request);

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
                    contestWrappers.add(contest);
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
                                ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                                ConfigurationAttributeKey.PARTNER_COLAB_PORT);
                        Contest foreignContest = ContestClient.fromService(contestService).getContest(contestId);
                        foreignContest.setUpForeignContestVisualConfigsFromLocal(c);
                        contestWrappers.add(foreignContest);
                    }else {
                        contestWrappers.add(c);
                    }
                } catch (ContestNotFoundException e) {
                    _log.error("Could not find contest " + contestId);
                }
            }
        }

        model.addAttribute("contests", contestWrappers);
        model.addAttribute("preferences", contestPreferences);
        //TODO: allow setting on a per-contest/per portlet basis
        model.addAttribute("contestType",
                ContestClientUtil.getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get()));
        return "showContests";
    }
}