package org.xcolab.view.pages.proposals.utils.picker;

import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.Proposal;

import java.util.Comparator;
import java.util.List;

/**
 * Utility class to sort lists of contests or proposals by a string parameter
 */
public class ProposalPickerSortingUtil {

    public static void sortContestsList(String sortOrder, String sortColumn,
            List<ContestWrapper> contests) {
        if (sortColumn != null) {

            Comparator<ContestWrapper> comparator;
            switch (sortColumn.toLowerCase()) {
                case "name":
                    comparator = Comparator.comparing(ContestWrapper::getTitleWithEndYear);
                    break;
                case "comments":
                    comparator = Comparator.comparing(ContestWrapper::getTotalCommentsCount);
                    break;
                case "what":
                    comparator = Comparator.comparing(ContestWrapper::getWhatName);
                    break;
                case "where":
                    comparator = Comparator.comparing(ContestWrapper::getWhereName);
                    break;
                case "who":
                    comparator = Comparator.comparing(ContestWrapper::getWhoName);
                    break;
                case "how":
                    comparator = Comparator.comparing(ContestWrapper::getHowName);
                    break;
                default:
                    comparator = Comparator.comparing(ContestWrapper::getProposalsCount);
            }
            if (sortOrder != null && sortOrder.toLowerCase().equals("desc")) {
                comparator = comparator.reversed();
            }
            contests.sort(comparator);
        }
    }

    public static void sortProposalsList(String sortOrder, String sortColumn,
            List<Proposal> proposals) {

        if (sortColumn != null) {

            Comparator<Proposal> comparator;
            switch (sortColumn.toLowerCase()) {
                case "contest":
                    comparator = Comparator.comparing(o -> o.getContest().getQuestion());
                    break;
                case "proposal":
                    comparator = Comparator.comparing(Proposal::getName);
                    break;
                case "author":
                    comparator = Comparator.comparing(Proposal::getAuthorName);
                    break;
                case "date":
                    comparator = Comparator.comparing(Proposal::getCreatedAt);
                    break;
                case "supporters":
                    comparator = Comparator.comparing(Proposal::getSupportersCountCached);
                    break;
                case "comments":
                    comparator = Comparator.comparing(Proposal::getCommentsCount);
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown sort column");
            }
            if (sortOrder != null && sortOrder.toLowerCase().equals("desc")) {
                comparator = comparator.reversed();
            }
            proposals.sort(comparator);
        }
    }
}
