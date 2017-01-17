package org.xcolab.view.pages.proposals.utils;

import org.apache.commons.lang3.tuple.Pair;

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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Utility class to sort lists of contests or proposals by a string parameter
 */
public class ProposalPickerSortingUtil {

    public static void sortContestsList(String sortOrder, String sortColumn,
                                        List<Pair<Contest, Date>> contests, final Map<Long, String> removedContests) {
        if (sortColumn != null) {

            boolean descendingSortOrder = (sortOrder != null) && sortOrder.toLowerCase().equals("desc");
            final int sortOrderModifier = descendingSortOrder? -1 : 1;

            switch (sortColumn.toLowerCase()) {
                case "name":
                    Collections.sort(contests, new Comparator<Pair<Contest, Date>>() {
                        @Override
                        public int compare(Pair<Contest, Date> o1,
                                           Pair<Contest, Date> o2) {
                            final boolean contest1wasRemoved = removedContests.containsKey(o1.getLeft().getContestPK());
                            final boolean contest2wasRemoved = removedContests.containsKey(o2.getLeft().getContestPK());
                            if (contest1wasRemoved != contest2wasRemoved) {
                                return contest1wasRemoved ? 1 : -1;
                            }
                            return sortOrderModifier * o1.getLeft().getContestShortName()
                                    .compareTo(o2.getLeft().getContestShortName());
                        }
                    });
                    break;
                case "comments":
                    Collections.sort(contests, new Comparator<Pair<Contest, Date>>() {
                        @Override
                        public int compare(Pair<Contest, Date> o1,
                                           Pair<Contest, Date> o2) {
                            final boolean contest1wasRemoved = removedContests.containsKey(o1.getLeft().getContestPK());
                            final boolean contest2wasRemoved = removedContests.containsKey(o2.getLeft().getContestPK());
                            if (contest1wasRemoved != contest2wasRemoved) {
                                return contest1wasRemoved ? 1 : -1;
                            }
                            return sortOrderModifier * (int) (o1.getLeft().getTotalCommentsCount() - o2
                                    .getLeft().getTotalCommentsCount());
                        }
                    });
                    break;
                case "what":
                    Collections.sort(contests, new Comparator<Pair<Contest, Date>>() {
                        @Override
                        public int compare(Pair<Contest, Date> o1,
                                           Pair<Contest, Date> o2) {
                            final boolean contest1wasRemoved = removedContests.containsKey(o1.getLeft().getContestPK());
                            final boolean contest2wasRemoved = removedContests.containsKey(o2.getLeft().getContestPK());
                            if (contest1wasRemoved != contest2wasRemoved) {
                                return contest1wasRemoved ? 1 : -1;
                            }
                            return sortOrderModifier * o1.getLeft().getWhatName()
                                    .compareTo(o2.getLeft().getWhatName());
                        }
                    });
                    break;
                case "where":
                    Collections.sort(contests, new Comparator<Pair<Contest, Date>>() {
                        @Override
                        public int compare(Pair<Contest, Date> o1,
                                           Pair<Contest, Date> o2) {
                            final boolean contest1wasRemoved = removedContests.containsKey(o1.getLeft().getContestPK());
                            final boolean contest2wasRemoved = removedContests.containsKey(o2.getLeft().getContestPK());
                            if (contest1wasRemoved != contest2wasRemoved) {
                                return contest1wasRemoved ? 1 : -1;
                            }
                            return sortOrderModifier * o1.getLeft().getWhereName()
                                    .compareTo(o2.getLeft().getWhereName());
                        }
                    });
                    break;
                case "who":
                    Collections.sort(contests, new Comparator<Pair<Contest, Date>>() {
                        @Override
                        public int compare(Pair<Contest, Date> o1,
                                           Pair<Contest, Date> o2) {
                            final boolean contest1wasRemoved = removedContests.containsKey(o1.getLeft().getContestPK());
                            final boolean contest2wasRemoved = removedContests.containsKey(o2.getLeft().getContestPK());
                            if (contest1wasRemoved != contest2wasRemoved) {
                                return contest1wasRemoved ? 1 : -1;
                            }
                            return sortOrderModifier * o1.getLeft().getWhoName()
                                    .compareTo(o2.getLeft().getWhoName());
                        }
                    });
                    break;
                case "how":
                    Collections.sort(contests, new Comparator<Pair<Contest, Date>>() {
                        @Override
                        public int compare(Pair<Contest, Date> o1,
                                           Pair<Contest, Date> o2) {
                            final boolean contest1wasRemoved = removedContests.containsKey(o1.getLeft().getContestPK());
                            final boolean contest2wasRemoved = removedContests.containsKey(o2.getLeft().getContestPK());
                            if (contest1wasRemoved != contest2wasRemoved) {
                                return contest1wasRemoved ? 1 : -1;
                            }
                            return sortOrderModifier * o1.getLeft().getHowName()
                                    .compareTo(o2.getLeft().getHowName());
                        }
                    });
                    break;
                default:
                    Collections.sort(contests, new Comparator<Pair<Contest, Date>>() {
                        @Override
                        public int compare(Pair<Contest, Date> o1,
                                           Pair<Contest, Date> o2) {
                            final boolean contest1wasRemoved = removedContests.containsKey(o1.getLeft().getContestPK());
                            final boolean contest2wasRemoved = removedContests.containsKey(o2.getLeft().getContestPK());
                            if (contest1wasRemoved != contest2wasRemoved) {
                                return contest1wasRemoved ? 1 : -1;
                            }
                            return sortOrderModifier * (int) (o1.getLeft().getProposalsCount() - o2
                                    .getLeft().getProposalsCount());
                        }
                    });
            }
        }
    }

    public static void sortProposalsList(String sortOrder, String sortColumn,
                                   List<Pair<Proposal, Date>> proposals) {

        if (sortColumn != null) {

            boolean descendingSortOrder = (sortOrder != null) && sortOrder.toLowerCase().equals("desc");
            final int sortOrderModifier = descendingSortOrder ? -1 : 1;

            switch (sortColumn.toLowerCase()) {
                case "contest":
                    Collections.sort(proposals, new Comparator<Pair<Proposal, Date>>() {
                        @Override
                        public int compare(Pair<Proposal, Date> o1,
                                Pair<Proposal, Date> o2) {
                            try {
                                return sortOrderModifier * ProposalClientUtil
                                        .getCurrentContestForProposal(
                                                o1.getLeft().getProposalId())
                                        .getContestName()
                                        .compareTo(
                                                ProposalClientUtil
                                                        .getCurrentContestForProposal(
                                                                o2.getLeft()
                                                                        .getProposalId())
                                                        .getContestName());
                            } catch ( ContestNotFoundException e) {
                                return 0;
                            }
                        }
                    });
                    break;
                case "proposal":
                    Collections.sort(proposals, new Comparator<Pair<Proposal, Date>>() {
                        @Override
                        public int compare(Pair<Proposal, Date> o1,
                                Pair<Proposal, Date> o2) {
                                return sortOrderModifier * ProposalAttributeClientUtil

                                        .getProposalAttribute(o1.getLeft().getProposalId(),
                                                ProposalAttributeKeys.NAME, 0L)
                                        .getStringValue()
                                        .compareTo(
                                                ProposalAttributeClientUtil.getProposalAttribute(
                                                        o2.getLeft().getProposalId(),
                                                        ProposalAttributeKeys.NAME, 0L)
                                                        .getStringValue());
                        }
                    });
                    break;
                case "author":
                    Collections.sort(proposals, new Comparator<Pair<Proposal, Date>>() {
                        @Override
                        public int compare(Pair<Proposal, Date> o1,
                                Pair<Proposal, Date> o2) {
                            try {
                                ProposalAttribute t1 = ProposalAttributeClientUtil
                                        .getProposalAttribute(o1.getLeft().getProposalId(),
                                                ProposalAttributeKeys.TEAM, 0L);
                                ProposalAttribute t2 = ProposalAttributeClientUtil
                                        .getProposalAttribute(o2.getLeft().getProposalId(),
                                                ProposalAttributeKeys.TEAM, 0L);

                                String author1 = t1 == null
                                        || t1.getStringValue().trim().isEmpty()
                                        ? MembersClient
                                        .getMember(o1.getLeft().getAuthorId())
                                        .getScreenName() : t1.getStringValue();
                                String author2 = t2 == null
                                        || t2.getStringValue().trim().isEmpty()
                                        ? MembersClient
                                        .getMember(o2.getLeft().getAuthorId())
                                        .getScreenName() : t2.getStringValue();

                                return sortOrderModifier * author1.compareTo(author2);
                            } catch (MemberNotFoundException e) {
                                return 0;
                            }
                        }
                    });
                    break;
                case "date":
                    Collections.sort(proposals, new Comparator<Pair<Proposal, Date>>() {
                        @Override
                        public int compare(Pair<Proposal, Date> o1,
                                Pair<Proposal, Date> o2) {
                            return o1.getRight().compareTo(o2.getRight());
                        }
                    });
                    break;
                case "supporters":
                    Collections.sort(proposals, new Comparator<Pair<Proposal, Date>>() {
                        @Override
                        public int compare(Pair<Proposal, Date> o1,
                                Pair<Proposal, Date> o2) {
                                return sortOrderModifier * ProposalMemberRatingClientUtil
                                        .getProposalSupportersCount(o1
                                                .getLeft().getProposalId())
                                        - ProposalMemberRatingClientUtil
                                        .getProposalSupportersCount(o2.getLeft()
                                                .getProposalId());

                        }
                    });
                    break;
                case "comments":
                    Collections.sort(proposals, new Comparator<Pair<Proposal, Date>>() {
                        @Override
                        public int compare(Pair<Proposal, Date> o1,
                                Pair<Proposal, Date> o2) {
                            return sortOrderModifier *
                                    (CommentClientUtil
                                            .countComments(o1.getLeft().getDiscussionId())
                                            - CommentClientUtil
                                            .countComments(o2.getLeft().getDiscussionId()));
                        }
                    });
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
