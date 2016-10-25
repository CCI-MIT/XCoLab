package org.xcolab.portlets.contests;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestsController {

    private static final Log _log = LogFactoryUtil.getLog(ContestsController.class);
    
    public ContestsController() {
    }

    @RequestMapping
    public String showContests(PortletRequest request, PortletResponse response, Model model)  {

        ContestPreferences contestPreferences = new ContestPreferences(request);

        List<ContestWrapper> contestWrappers = new ArrayList<>();
        final List<Long> selectedContests = contestPreferences.getSelectedContests();
        if (selectedContests.isEmpty()) {

            List<Contest> contests = ContestClientUtil.findContestsByActiveFeatured(true, true);
            Collections.shuffle(contests);
            for (Contest contest : contests) {
                if (contestWrappers.size() >= contestPreferences.getFeedSize()) {
                    break;
                }
                try {
                    if (!contest.getContestPrivate()) {
                        contestWrappers.add(new ContestWrapper(contest));
                    }
                } catch (ContestNotFoundException ignored) {
                    //LR
                }
            }
        } else {
            Collections.shuffle(selectedContests);
            for (Long contestId : selectedContests) {
                if (contestWrappers.size() >= contestPreferences.getFeedSize()) {
                    break;
                }
                try {
                    contestWrappers.add(new ContestWrapper(ContestClientUtil.getContest(contestId)));
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