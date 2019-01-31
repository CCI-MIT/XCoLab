package org.xcolab.view.pages.proposals.view.contest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.http.servlet.RequestUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.permissions.ContestPermissions;
import org.xcolab.view.pages.proposals.utils.ContestsColumn;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.view.proposal.BaseProposalsController;
import org.xcolab.view.pages.proposals.wrappers.CollectionCardFilterBean;
import org.xcolab.view.pages.proposals.wrappers.CollectionCardWrapper;
import org.xcolab.view.pages.proposals.wrappers.ContestList;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
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

    @Autowired
    private IContestTypeClient contestTypeClient;

    @GetMapping("/contests")
    public String showContestsIndex(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper currentMember, ProposalContext proposalContext,
            @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String viewType,
            @RequestParam(required = false, defaultValue="false") boolean showAllContests,
            @RequestParam(required = false, defaultValue = "" + FEATURED_COLLECTION_CARD_ID) long currentCollectionCardId,
            @RequestParam(required = false) Long contestTypeId,
            UserWrapper loggedInMember,
            SortFilterPage sortFilterPage) {

        ContestType contestType;
        if (contestTypeId != null) {
            contestType = new ContestType(contestTypeId);
        } else {
            final ContestType defaultContestType = new ContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
            final String originalUri = RequestUtil.getOriginalUri(request);
            if (!originalUri.startsWith(defaultContestType.getContestBaseUrl())) {
                contestType = contestTypeClient.getActiveContestTypes().stream()
                                .filter(item -> originalUri
                                        .startsWith(item.getContestBaseUrl()))
                                //TODO: better exception --> 404
                                .findFirst().orElseThrow(IllegalStateException::new);
            } else {
                contestType = defaultContestType;
            }
        }

        if (contestType.isRestrictedAccess() && !new ContestPermissions(loggedInMember)
                .getCanAccessContests(contestType)) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }

        final int totalContestCount = contestClient.countContests(null, false, contestType.getId());

        if (contestType.isSuggestionsActive()) {
            ContestWrapper c = contestClient.getContest(contestType.getSuggestionContestId());
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
                ontologyTermToLoad = contestClient.getContestCollectionCard(currentCollectionCardId)
                        .getOntologyTermToLoad();
            }

            List<CollectionCardWrapper> collectionCards = new ArrayList<>();
            LinkedList<CollectionCardWrapper> collectionHierarchy = new LinkedList<>();
            if(showCollectionCards) { //don't display collectioncards if search results shown
                for (IContestCollectionCard card: contestClient.getSubContestCollectionCards(currentCollectionCardId)) {
                    collectionCards.add(new CollectionCardWrapper(card, viewType));
                }
                collectionCards.sort((o1, o2) ->
                        o1.getOrder() < o2.getOrder() ? -1
                                : o1.getOrder() == o2.getOrder() ? 0 : 1);
                long tempId = currentCollectionCardId;
                while(contestClient.getContestCollectionCard(tempId).getParent() != null) {
                    collectionHierarchy.addFirst(new CollectionCardWrapper(contestClient.getContestCollectionCard(tempId), viewType));
                    tempId = contestClient.getContestCollectionCard(tempId).getParent();
                }
                collectionHierarchy.addFirst(new CollectionCardWrapper(contestClient.getContestCollectionCard(tempId), viewType));
                if(collectionHierarchy.size() == 1) {
                    collectionHierarchy.clear();
                }
            }

            model.addAttribute("collectionCards", new CollectionCardFilterBean(collectionCards));
            model.addAttribute("currentCollectionCardId", currentCollectionCardId);
            model.addAttribute("collectionHierarchy", collectionHierarchy);


            model.addAttribute("showOnlyFeatured", showOnlyFeatured);

            //if only featured
            if(contestClient.getContestCollectionCard(currentCollectionCardId).getOntologyTermToLoad() != null) {
                model.addAttribute("ontologySpaceId", ontologyClient.getOntologyTerm(contestClient.getContestCollectionCard(currentCollectionCardId).getOntologyTermToLoad()).getOntologySpaceId());
            } else {
                model.addAttribute("ontologySpaceId", 0);
            }
        }

        /*--------------------------------*/
        //For other views
        /*--------------------------------*/

        List<ContestWrapper> contests = new ArrayList<>();

        if (viewType.equals(VIEW_TYPE_OUTLINE)) {

            List<ContestWrapper> allContests = contestClient.getContests(
                    null, false, contestType.getId()).stream()
                    .filter(ContestWrapper::isShowInOutlineView)
                    .collect(Collectors.toList());

            final List<ContestWrapper> activeContests = allContests.stream()
                    .filter(ContestWrapper::isContestActive)
                    .collect(Collectors.toList());

            List<ContestWrapper> otherContests;
            if (showAllContests) {
                contests = allContests;
                otherContests = activeContests;
            } else {
                contests = activeContests;
                otherContests = allContests;
            }

        	List<OntologySpaceWrapper> ontologySpacesRaw = ontologyClient.getAllOntologySpaces();
        	List<OntologyTermWrapper> ontologyTermsRaw = ontologyClient.getAllOntologyTerms();
        	List<FocusAreaWrapper> focusAreasRaw = ontologyClient.getAllFocusAreas();
        	List<IFocusAreaOntologyTerm> focusAreasOntologyTermsRaw = ontologyClient.getAllFocusAreaOntologyTerms();
        	Map<Long, FocusAreaWrapper> focusAreas = new TreeMap<>();

            for (FocusAreaWrapper area: focusAreasRaw) {
        		focusAreas.put(area.getId(), new FocusAreaWrapper(area));
        	}

            Map<Long, OntologySpaceWrapper> ontologySpaces = new HashMap<>();
            for (OntologySpaceWrapper space: ontologySpacesRaw) {
        		ontologySpaces.put(space.getId(), new OntologySpaceWrapper(space));
        	}

            Map<Long, OntologyTermWrapper> ontologyTerms = new TreeMap<>();
            for (OntologyTermWrapper term: ontologyTermsRaw) {
        		OntologyTermWrapper termWrapped = new OntologyTermWrapper(term);
                final long ontologySpaceId = term.getOntologySpaceId();
                final OntologySpaceWrapper ontologySpace = ontologySpaces.get(ontologySpaceId);
                if (ontologySpace == null) {
                    throw new IllegalStateException(String.format(
                            "Ontology space %d referenced by ontology term %d doesn't exist.",
                            ontologySpaceId, term.getId()));
                }
                ontologySpace.addTerm(termWrapped);
        		ontologyTerms.put(term.getId(), termWrapped);
        	}

        	for (OntologyTermWrapper term: ontologyTermsRaw) {
        		if (term.getParentId() > 0) {
        			ontologyTerms.get(term.getId()).setParent(ontologyTerms.get(term.getParentId()));
        		}
        	}
        	
        	for (IFocusAreaOntologyTerm faTerm: focusAreasOntologyTermsRaw) {
        		focusAreas.get(faTerm.getFocusAreaId()).addOntologyTerm(ontologyTerms.get(faTerm.getOntologyTermId()));
        	}

        	List<OntologySpaceWrapper> sortedSpaces = new ArrayList<>(ontologySpaces.values());
        	sortedSpaces.sort(Comparator.comparingInt(OntologySpaceWrapper::getOrder));
        	model.addAttribute("focusAreas", focusAreas.values());
        	model.addAttribute("ontologyTerms", ontologyTerms.values());
        	model.addAttribute("ontologySpaces", sortedSpaces);
        	model.addAttribute("otherContests", otherContests);
        	model.addAttribute("contestType", contestType);
        } else if (!ConfigurationAttributeKey.COLAB_USES_CARDS.get()) {
            contests = contestClient.getContests(
                    showAllContests ? null : true, false, contestType.getId());
        }

        model.addAttribute("showCollectionCards", ConfigurationAttributeKey.COLAB_USES_CARDS.get());
        boolean showContestManagementLink = StaticUserContext.getPermissionClient().canAdminAll(currentMember) ;
        model.addAttribute("showContestManagementLink", showContestManagementLink);
        model.addAttribute("totalContestCount", totalContestCount);
        model.addAttribute("contests", contests);
        //not taken into account if collection cards enabled
        model.addAttribute("showFilter", contests.size() >= MIN_SIZE_CONTEST_FILTER);
        final ContestList sortedContests = new ContestList(contests, getComparator(sortFilterPage));
        if (StringUtils.isEmpty(sortFilterPage.getFilter())) {
            model.addAttribute("contestsSorted", sortedContests);
        } else {
            model.addAttribute("contestsSorted",
                    sortedContests.getFiltered(getFilter(sortFilterPage)));
        }
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

        setActivePageLink(model, contestType);
        return "/proposals/contestsIndex";
    }

    private Comparator<ContestWrapper> getComparator(SortFilterPage sortFilterPage) {
        ContestsColumn sortColumn;
        if (StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
            sortColumn = ContestsColumn.valueOf(sortFilterPage.getSortColumn());
        } else {
            sortColumn = ContestsColumn.DEFAULT;
        }
        return (o1, o2) -> {
            if (o1.isFeatured() && !o2.isFeatured()) {
                return -1;
            }
            if (!o1.isFeatured() && o2.isFeatured()) {
                return 1;
            }
            if (sortFilterPage.isSortAscending()) {
                return sortColumn.getColumnComparator().compare(o1, o2);
            }
            return sortColumn.getColumnComparator().compare(o2, o1);
        };
    }

    private Predicate<ContestWrapper> getFilter(SortFilterPage sortFilterPage) {
        String filterString = sortFilterPage.getFilter().toLowerCase();

        return contest -> {
            final String contestNameLc = contest.getQuestion().toLowerCase();
            final String contestShortNameLc = contest.getTitleWithEndYear()
                    .toLowerCase();
            return contestNameLc.contains(filterString)
                    || contestShortNameLc.contains(filterString);
        };
    }

    private List<ContestWrapper> wrapContests(List<ContestWrapper> contests){
        List<ContestWrapper> contestsToReturn = new ArrayList<>(contests);
        return contestsToReturn;
    }
}
