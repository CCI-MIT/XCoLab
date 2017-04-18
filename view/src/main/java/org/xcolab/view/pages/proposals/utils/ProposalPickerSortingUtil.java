package org.xcolab.view.pages.proposals.utils;

import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Utility class to sort lists of contests or proposals by a string parameter
 */
public class ProposalPickerSortingUtil {

    public static void sortContestsList(String sortOrder, String sortColumn,
        List<Contest> contests, final Map<Long, String> removedContests) {
        if (sortColumn != null) {

            boolean descendingSortOrder =
                (sortOrder != null) && sortOrder.toLowerCase().equals("desc");
            final int sortOrderModifier = descendingSortOrder ? -1 : 1;

            switch (sortColumn.toLowerCase()) {
                case "name":
                    contests.sort((o1, o2) -> {
                        final boolean contest1wasRemoved =
                            removedContests.containsKey(o1.getContestPK());
                        final boolean contest2wasRemoved =
                            removedContests.containsKey(o2.getContestPK());
                        if (contest1wasRemoved != contest2wasRemoved) {
                            return contest1wasRemoved ? 1 : -1;
                        }
                        return sortOrderModifier * o1.getContestShortName()
                            .compareTo(o2.getContestShortName());
                    });
                    break;
                case "comments":
                    contests.sort((o1, o2) -> {
                        final boolean contest1wasRemoved =
                            removedContests.containsKey(o1.getContestPK());
                        final boolean contest2wasRemoved =
                            removedContests.containsKey(o2.getContestPK());
                        if (contest1wasRemoved != contest2wasRemoved) {
                            return contest1wasRemoved ? 1 : -1;
                        }
                        return sortOrderModifier * (int) (o1.getTotalCommentsCount() - 
                            o2.getTotalCommentsCount());
                    });
                    break;
                case "what":
                    contests.sort((o1, o2) -> {
                        final boolean contest1wasRemoved =
                            removedContests.containsKey(o1.getContestPK());
                        final boolean contest2wasRemoved =
                            removedContests.containsKey(o2.getContestPK());
                        if (contest1wasRemoved != contest2wasRemoved) {
                            return contest1wasRemoved ? 1 : -1;
                        }
                        return sortOrderModifier * o1.getWhatName()
                            .compareTo(o2.getWhatName());
                    });
                    break;
                case "where":
                    contests.sort((o1, o2) -> {
                        final boolean contest1wasRemoved =
                            removedContests.containsKey(o1.getContestPK());
                        final boolean contest2wasRemoved =
                            removedContests.containsKey(o2.getContestPK());
                        if (contest1wasRemoved != contest2wasRemoved) {
                            return contest1wasRemoved ? 1 : -1;
                        }
                        return sortOrderModifier * o1.getWhereName()
                            .compareTo(o2.getWhereName());
                    });
                    break;
                case "who":
                    contests.sort((o1, o2) -> {
                        final boolean contest1wasRemoved =
                            removedContests.containsKey(o1.getContestPK());
                        final boolean contest2wasRemoved =
                            removedContests.containsKey(o2.getContestPK());
                        if (contest1wasRemoved != contest2wasRemoved) {
                            return contest1wasRemoved ? 1 : -1;
                        }
                        return sortOrderModifier * o1.getWhoName()
                            .compareTo(o2.getWhoName());
                    });
                    break;
                case "how":
                    contests.sort((o1, o2) -> {
                        final boolean contest1wasRemoved =
                            removedContests.containsKey(o1.getContestPK());
                        final boolean contest2wasRemoved =
                            removedContests.containsKey(o2.getContestPK());
                        if (contest1wasRemoved != contest2wasRemoved) {
                            return contest1wasRemoved ? 1 : -1;
                        }
                        return sortOrderModifier * o1.getHowName()
                            .compareTo(o2.getHowName());
                    });
                    break;
                default:
                    contests.sort((o1, o2) -> {
                        final boolean contest1wasRemoved =
                            removedContests.containsKey(o1.getContestPK());
                        final boolean contest2wasRemoved =
                            removedContests.containsKey(o2.getContestPK());
                        if (contest1wasRemoved != contest2wasRemoved) {
                            return contest1wasRemoved ? 1 : -1;
                        }
                        return sortOrderModifier * (int) (o1.getProposalsCount() - o2
                            .getProposalsCount());
                    });
            }
        }
    }

    public static void sortProposalsList(String sortOrder, String sortColumn,
        List<Proposal> proposals) {

        if (sortColumn != null) {

            boolean descendingSortOrder =
                (sortOrder != null) && sortOrder.toLowerCase().equals("desc");
            final int sortOrderModifier = descendingSortOrder ? -1 : 1;

            switch (sortColumn.toLowerCase()) {
                case "contest":
                    proposals.sort((o1, o2) -> {
                        try {
                            return sortOrderModifier * ProposalClientUtil
                                .getCurrentContestForProposal(o1.getProposalId())
                                .getContestName().compareTo(ProposalClientUtil
                                    .getCurrentContestForProposal(o2.getProposalId())
                                    .getContestName());
                        } catch (ContestNotFoundException e) {
                            return 0;
                        }
                    });
                    break;
                case "proposal":
                    proposals.sort((o1, o2) -> sortOrderModifier * ProposalAttributeClientUtil

                        .getProposalAttribute(o1.getProposalId(),
                            ProposalAttributeKeys.NAME, 0L).getStringValue().compareTo(
                            ProposalAttributeClientUtil
                                .getProposalAttribute(o2.getProposalId(),
                                    ProposalAttributeKeys.NAME, 0L).getStringValue()));
                    break;
                case "author":
                    proposals.sort((o1, o2) -> {
                        try {
                            ProposalAttribute t1 = ProposalAttributeClientUtil
                                .getProposalAttribute(o1.getProposalId(),
                                    ProposalAttributeKeys.TEAM, 0L);
                            ProposalAttribute t2 = ProposalAttributeClientUtil
                                .getProposalAttribute(o2.getProposalId(),
                                    ProposalAttributeKeys.TEAM, 0L);

                            String author1 =
                                t1 == null || t1.getStringValue().trim().isEmpty() ? MembersClient
                                    .getMember(o1.getAuthorId()).getScreenName()
                                    : t1.getStringValue();
                            String author2 =
                                t2 == null || t2.getStringValue().trim().isEmpty() ? MembersClient
                                    .getMember(o2.getAuthorId()).getScreenName()
                                    : t2.getStringValue();

                            return sortOrderModifier * author1.compareTo(author2);
                        } catch (MemberNotFoundException e) {
                            return 0;
                        }
                    });
                    break;
                case "date":
                    proposals.sort(Comparator.comparing(Proposal::getCreateDate));
                    break;
                case "supporters":
                    proposals.sort((o1, o2) -> sortOrderModifier * ProposalMemberRatingClientUtil
                        .getProposalSupportersCount(o1.getProposalId())
                        - ProposalMemberRatingClientUtil
                        .getProposalSupportersCount(o2.getProposalId()));
                    break;
                case "comments":
                    proposals.sort((o1, o2) -> sortOrderModifier * (
                        CommentClientUtil.countComments(o1.getDiscussionId())
                            - CommentClientUtil.countComments(o2.getDiscussionId())));
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown sort column");
            }
        }

        if (sortOrder != null && sortOrder.toLowerCase().equals("desc")) {
            Collections.reverse(proposals);
        }
    }
}
