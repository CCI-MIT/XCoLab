package org.xcolab.view.widgets.contests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.widgets.AbstractWidgetController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(ContestsController.BASE_URL)
public class ContestsController extends AbstractWidgetController<ContestPreferences> {

    private static final Logger _log = LoggerFactory.getLogger(ContestsController.class);

    private static final String VIEW_BASE_PATH = "/widgets/contests";

    public static final String BASE_URL = "/widgets/contests";

    @Autowired
    private IContestTypeClient contestTypeClient;

    protected ContestsController() {
        super(BASE_URL, ContestPreferences::new);
    }

    @GetMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String showPreferences(HttpServletResponse response, Model model, UserWrapper member,
            @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String language) {
        return showPreferencesInternal(response, model, member, preferenceId, language,
                VIEW_BASE_PATH + "/editPreferences");
    }


    @PostMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member, ContestPreferences preferences) {
        return savePreferencesInternal(request, response, member, preferences);
    }


    @GetMapping
    public String showContests(HttpServletRequest request, HttpServletResponse response,
            Model model, @RequestParam(required = false) String preferenceId) {

        Locale locale = LocaleContextHolder.getLocale();
        ContestPreferences contestPreferences =
                new ContestPreferences(preferenceId, locale.getLanguage());

        List<ContestWrapper> contestWrappers = new ArrayList<>();
        final List<Long> selectedContests = contestPreferences.getSelectedContests();
        if (selectedContests.isEmpty()) {

            List<ContestWrapper> contests = contestClient.findContestsByActiveFeatured(true, true);
            Collections.shuffle(contests);
            for (ContestWrapper contest : contests) {
                if (contestWrappers.size() >= contestPreferences.getFeedSize()) {
                    break;
                }
                if (!contest.isContestPrivate()) {
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
                    ContestWrapper c = contestClient.getContest(contestId);
                    contestWrappers.add(c);
                } catch (ContestNotFoundException e) {
                    _log.error("Could not find contest {}", contestId);
                }
            }
        }

        model.addAttribute("contests", contestWrappers);
        model.addAttribute("contestPreferences", contestPreferences);
        model.addAttribute("contestType", contestTypeClient
                .getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get()));
        return VIEW_BASE_PATH + "/showContests";
    }
}
