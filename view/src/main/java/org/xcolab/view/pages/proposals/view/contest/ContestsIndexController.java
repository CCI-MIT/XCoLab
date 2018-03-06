package org.xcolab.view.pages.proposals.view.contest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.view.proposal.BaseProposalsController;
import org.xcolab.view.pages.proposals.wrappers.CollectionCardFilterBean;
import org.xcolab.view.pages.proposals.wrappers.CollectionCardWrapper;
import org.xcolab.view.pages.proposals.wrappers.ContestsSortFilterBean;
import org.xcolab.view.pages.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContestsIndexController extends BaseProposalsController {

    private static final String COOKIE_VIEW_TYPE = "cc_contests_viewType";
    private static final String VIEW_TYPE_GRID = "GRID";
    private static final String VIEW_TYPE_LIST = "LIST";
    private static final String VIEW_TYPE_OUTLINE = "OUTLINE";
    private static final String VIEW_TYPE_DEFAULT = VIEW_TYPE_GRID;
    private static final int MIN_SIZE_CONTEST_FILTER = 9;
    private static final int FEATURED_COLLECTION_CARD_ID = 1;
    private static final int BY_TOPIC_COLLECTION_CARD_ID = 2;
    private static final int BY_LOCATION_COLLECTION_CARD_ID = 3;

    @GetMapping("/contests")
    public String showContestsIndex(HttpServletRequest request, HttpServletResponse response,
            Model model, Member currentMember, ProposalContext proposalContext,
            @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String viewType,
            @RequestParam(required = false, defaultValue="false") boolean showAllContests,
            @RequestParam(required = false, defaultValue = "" + FEATURED_COLLECTION_CARD_ID) long currentCollectionCardId,
            SortFilterPage sortFilterPage) {

        Locale locale = LocaleContextHolder.getLocale();
        ProposalsPreferencesWrapper preferences = new ProposalsPreferencesWrapper(preferenceId,locale.getLanguage());
        ContestType contestType = preferences.getContestType();

        final int totalContestCount = ContestClientUtil
                .countContests(null, false, contestType.getId());

        if (contestType.isSuggestionsActive()) {
            Contest c = ContestClientUtil.getContest(contestType.getSuggestionContestId());
            String link = c.getContestLinkUrl();
            model.addAttribute("suggestionContestLink", link);
        }

        if (viewType == null) {
            // view type wasn't set
            final Cookie[] cookies = request.getCookies(); //null if cookies are disabled
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(COOKIE_VIEW_TYPE)) {
                        viewType = cookie.getValue();
                    }
                }
            }
        }
        else {
            // we need to change the view type
            if (viewType.equals(VIEW_TYPE_GRID) || viewType.equals(VIEW_TYPE_LIST) || viewType.equals(VIEW_TYPE_OUTLINE)) {
                // we should set the cookie but it doesn't work currently https://issues.liferay.com/browse/LPS-25733
                // it should be handled in the view
                response.addCookie(new Cookie(COOKIE_VIEW_TYPE, viewType));
            }
        }
        if (viewType == null) {
            viewType = VIEW_TYPE_DEFAULT;
        }

        /*--------------------------------*/
        //Only for CollectionCards
        /*--------------------------------*/

        if (ConfigurationAttributeKey.COLAB_USES_CARDS.get() && !viewType.equals(VIEW_TYPE_OUTLINE)) {
            Long ontologyTermToLoad;
            boolean showCollectionCards=true;
            boolean showOnlyFeatured = false;
            if(sortFilterPage != null && sortFilterPage.getFilter() != null && !sortFilterPage.getFilter().isEmpty()) { //if search function was used
                ontologyTermToLoad = null;
                currentCollectionCardId = BY_TOPIC_COLLECTION_CARD_ID;
                showCollectionCards=false;
            } else if(currentCollectionCardId == FEATURED_COLLECTION_CARD_ID) {
                showOnlyFeatured = true;  // filter with JSP  -> TODO COLAB-2627: increase performance
                ontologyTermToLoad = null; //get all
            } else {
                ontologyTermToLoad = ContestClientUtil.getContestCollectionCard(currentCollectionCardId)
                        .getOntology_term_to_load();
            }

            List<CollectionCardWrapper> collectionCards = new ArrayList<>();
            LinkedList<CollectionCardWrapper> collectionHierarchy = new LinkedList<>();
            if(showCollectionCards) { //don't display collectioncards if search results shown
                for (ContestCollectionCard card: ContestClientUtil.getSubContestCollectionCards(currentCollectionCardId)) {
                    collectionCards.add(new CollectionCardWrapper(card, viewType));
                }
                collectionCards.sort((o1, o2) ->
                        o1.getOrder() < o2.getOrder() ? -1
                                : o1.getOrder() == o2.getOrder() ? 0 : 1);
                long tempId = currentCollectionCardId;
                while(ContestClientUtil.getContestCollectionCard(tempId).getParent() != null) {
                    collectionHierarchy.addFirst(new CollectionCardWrapper(ContestClientUtil.getContestCollectionCard(tempId), viewType));
                    tempId = ContestClientUtil.getContestCollectionCard(tempId).getParent();
                }
                collectionHierarchy.addFirst(new CollectionCardWrapper(ContestClientUtil.getContestCollectionCard(tempId), viewType));
                if(collectionHierarchy.size() == 1) {
                    collectionHierarchy.clear();
                }
            }

            model.addAttribute("collectionCards", new CollectionCardFilterBean(collectionCards));
            model.addAttribute("currentCollectionCardId", currentCollectionCardId);
            model.addAttribute("collectionHierarchy", collectionHierarchy);


            model.addAttribute("showOnlyFeatured", showOnlyFeatured);

            //if only featured
            if(ContestClientUtil.getContestCollectionCard(currentCollectionCardId).getOntology_term_to_load() != null) {
                model.addAttribute("ontologySpaceId", OntologyClientUtil.getOntologyTerm(ContestClientUtil.getContestCollectionCard(currentCollectionCardId).getOntology_term_to_load()).getOntologySpaceId());
            } else {
                model.addAttribute("ontologySpaceId", 0);
            }
        }

        /*--------------------------------*/
        //For other views
        /*--------------------------------*/

        List<Contest> contests = new ArrayList<>();

        if (viewType.equals(VIEW_TYPE_OUTLINE)) {

            List<Contest> allContests = wrapContests(ContestClientUtil.getContests(
                    null, false, contestType.getId()));

            final List<Contest> activeContests = allContests.stream()
                    .filter(Contest::isContestActive)
                    .collect(Collectors.toList());

            List<Contest> otherContests;
            if (showAllContests) {
                contests = allContests;
                otherContests = activeContests;
            } else {
                contests = activeContests;
                otherContests = allContests;
            }

        	List<OntologySpace> ontologySpacesRaw = OntologyClientUtil.getAllOntologySpaces();
        	List<OntologyTerm> ontologyTermsRaw = OntologyClientUtil.getAllOntologyTerms();
        	List<FocusArea> focusAreasRaw = OntologyClientUtil.getAllFocusAreas();
        	List<FocusAreaOntologyTerm> focusAreasOntologyTermsRaw = OntologyClientUtil.getAllFocusAreaOntologyTerms();
        	Map<Long, FocusArea> focusAreas = new TreeMap<>();

            for (FocusArea area: focusAreasRaw) {
        		focusAreas.put(area.getId_(), new FocusArea(area));
        	}

            Map<Long, OntologySpace> ontologySpaces = new HashMap<>();
            for (OntologySpace space: ontologySpacesRaw) {
        		ontologySpaces.put(space.getId_(), new OntologySpace(space));
        	}

            Map<Long, OntologyTerm> ontologyTerms = new TreeMap<>();
            for (OntologyTerm term: ontologyTermsRaw) {
        		OntologyTerm termWrapped = new OntologyTerm(term);
        		ontologySpaces.get(term.getOntologySpaceId()).addTerm(termWrapped);
        		ontologyTerms.put(term.getId_(), termWrapped);
        	}

        	for (OntologyTerm term: ontologyTermsRaw) {
        		if (term.getParentId() > 0) {
        			ontologyTerms.get(term.getId_()).setParent(ontologyTerms.get(term.getParentId()));
        		}
        	}
        	
        	for (FocusAreaOntologyTerm faTerm: focusAreasOntologyTermsRaw) {
        		focusAreas.get(faTerm.getFocusAreaId()).addOntologyTerm(ontologyTerms.get(faTerm.getOntologyTermId()));
        	}

        	List<OntologySpace> sortedSpaces = new ArrayList<>(ontologySpaces.values());
        	sortedSpaces.sort(Comparator.comparingInt(OntologySpace::getOrder));
        	model.addAttribute("focusAreas", focusAreas.values());
        	model.addAttribute("ontologyTerms", ontologyTerms.values());
        	model.addAttribute("ontologySpaces", sortedSpaces);
        	model.addAttribute("otherContests", otherContests);
        	model.addAttribute("contestType", contestType);
        } else if (!ConfigurationAttributeKey.COLAB_USES_CARDS.get()) {
            List<Contest> contestsToWrap = ContestClientUtil.getContests(
                    showAllContests ? null : true, false, contestType.getId());

            contests = wrapContests(contestsToWrap);
        }

        model.addAttribute("showCollectionCards", ConfigurationAttributeKey.COLAB_USES_CARDS.get());
        boolean showContestManagementLink = PermissionsClient.canAdminAll(currentMember) ;
        model.addAttribute("showContestManagementLink", showContestManagementLink);
        model.addAttribute("totalContestCount", totalContestCount);
        model.addAttribute("contests", contests);
        //not taken into account if collection cards enabled
        model.addAttribute("showFilter", contests.size() >= MIN_SIZE_CONTEST_FILTER);
        model.addAttribute("contestsSorted", new ContestsSortFilterBean(contests, sortFilterPage));
        model.addAttribute("viewType", viewType);
        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("showAllContests", showAllContests);
        model.addAttribute("showContestDisplayOptions", ConfigurationAttributeKey.SHOW_CONTESTS_DISPLAY_OPTIONS.get());


        final String description = String.format("View %s %s run on the %s. ",
                showAllContests ? "all" : "active",
                contestType.getContestNamePluralLowercase(),
                ConfigurationAttributeKey.COLAB_NAME.get())
                + ConfigurationAttributeKey.META_PAGE_DESCRIPTION_CONTESTS.get();
        model.addAttribute("pageDescription", description);

        setBasePageAttributes(proposalContext, model);
        return "/proposals/contestsIndex";
    }

    private List<Contest> wrapContests(List<Contest> contests){
        List<Contest> contestsToReturn = new ArrayList<>();
        for (Contest contest: contests) {
            if (contest.getIsSharedContestInForeignColab()) {
                final ContestClient contestClient = new ClientHelper(contest).getContestClient();
                try {
                    Contest foreignContest = contestClient.getContest(contest.getContestPK());
                    foreignContest.setUpForeignContestVisualConfigsFromLocal(contest);
                    contestsToReturn.add(foreignContest);
                } catch (ContestNotFoundException notFound) {

                }
            } else {
                contestsToReturn.add((contest));
            }
        }
        return contestsToReturn;
    }
}
