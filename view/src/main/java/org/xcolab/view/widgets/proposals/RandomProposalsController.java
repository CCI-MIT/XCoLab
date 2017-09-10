package org.xcolab.view.widgets.proposals;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.widgets.AbstractWidgetController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RandomProposalsController.BASE_URL)
public class RandomProposalsController extends AbstractWidgetController<RandomProposalsPreferences> {

    private static final String VIEW_BASE_PATH = "/widgets/proposals";

    public static final String BASE_URL = "/widgets/proposals";

    protected RandomProposalsController() {
        super(BASE_URL, RandomProposalsPreferences::new);
    }

    @GetMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String showPreferences(HttpServletResponse response, Model model, Member member,
            @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String language) {
        return showPreferencesInternal(response, model,  member, preferenceId, language,
                VIEW_BASE_PATH + "/editPreferences");
    }


    @PostMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            Member member, RandomProposalsPreferences preferences) {
        return savePreferencesInternal(request, response, member, preferences);
    }

    @GetMapping
    public String showRandomProposals(HttpServletRequest request, HttpServletResponse response,
            Model model, @RequestParam(required = false) String preferenceId) {

        Locale locale = LocaleContextHolder.getLocale();
        RandomProposalsPreferences preferences =
                new RandomProposalsPreferences(preferenceId, locale.getLanguage());


        ProposalsModel proposalsModel = new ProposalsModel(getProposals(preferences), preferences,
                PlatformAttributeKey.COLAB_URL + "/proposal/");

        model.addAttribute("proposalsModel", proposalsModel);

        return VIEW_BASE_PATH + "/showProposals";
    }

    private List<Proposal> getProposals(RandomProposalsPreferences preferences) {

        List<Proposal> ret = new ArrayList<>();
        List<Proposal> proposals = getAvailableProposals(preferences);

        //TODO: remove loop and use micro service pojo
        if (proposals != null) {
            Collections.shuffle(proposals);
            for (int i = 0; i < proposals.size() && i < preferences.getFeedSize(); ++i) {
                try {
                    ret.add((ProposalClientUtil.getProposal(proposals.get(i).getProposalId())));
                } catch (ProposalNotFoundException e) {
                    //ignored for now, will be removed after LR
                }
            }
        }

        return ret;
    }

    private List<Proposal> getAvailableProposals(RandomProposalsPreferences preferences) {
        Long[] selectedPhases = preferences.getSelectedPhases();
        if (selectedPhases == null) {
            return null;
        }
        Long[] flagFilters = preferences.getFlagFilters();

        List<Proposal> availableProposals = new ArrayList<>();
        for (Long contestPhaseId : selectedPhases) {
            if (flagFilters == null || flagFilters.length == 0) {
                availableProposals.addAll(ProposalClientUtil
                        .listProposals(0, Integer.MAX_VALUE, null, true, contestPhaseId, null));
            } else {
                for (Long flagFilter : flagFilters) {
                    availableProposals.addAll(ProposalClientUtil
                            .listProposals(0, Integer.MAX_VALUE, null, true, contestPhaseId,
                                    flagFilter.intValue()));
                }
            }
        }
        return availableProposals;
    }
}
