package org.xcolab.view.pages.proposals.view.proposal.json.picker;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalRibbon;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProposalsResult {

    private static final int MAX_CHARS_FOR_NAMES = 75;

    private final List<SimpleProposal> proposals;
    private final int totalCount;

    public ProposalsResult(List<ProposalWrapper> proposals, int totalCount) {
        this.proposals = proposals.stream()
                .map(SimpleProposal::new)
                .collect(Collectors.toList());
        this.totalCount = totalCount;
    }

    public List<SimpleProposal> getProposals() {
        return proposals;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public static class SimpleProposal {

        private final long id;
        private final String proposalName;
        private final String contestName;
        private final String linkUrl;
        private final long contestId;
        private final String team;
        private final String authorName;
        private final long authorUserId;
        private final long dateSubscribed;
        private final long commentsCount;
        private final long supportersCount;
        private final long votesCount;
        private final String pitch;
        private final int ribbon;
        private final String ribbonText;
        private final boolean featured;

        public SimpleProposal(ProposalWrapper proposal) {

            this.id = proposal.getId();
            this.proposalName = StringUtils.abbreviate(
                StringEscapeUtils.unescapeXml(proposal.getName()), MAX_CHARS_FOR_NAMES);
            this.contestName = StringUtils.abbreviate(proposal
                .getContest().getTitleWithEndYear(), MAX_CHARS_FOR_NAMES);
            this.linkUrl = proposal.getProposalUrl();
            this.contestId = proposal.getContestId();
            this.team = proposal.getTeam();
            this.authorName = proposal.getAuthorName();
            this.authorUserId = proposal.getAuthorUserId();
            //TODO COLAB-2628: get right date
            this.dateSubscribed = new Date().getTime();
            this.commentsCount = proposal.getCommentsCount();
            this.supportersCount = proposal.getSupportersCount();
            this.votesCount = proposal.getVotesCountFromCache();
            this.pitch = proposal.getPitch();
            final ProposalRibbon ribbonWrapper = proposal.getRibbonWrapper();
            this.ribbon = ribbonWrapper.getRibbon();
            this.ribbonText = ribbonWrapper.getRibbonText();
            this.featured = proposal.isFeatured();
        }

        public long getId() {
            return id;
        }

        public String getProposalName() {
            return proposalName;
        }

        public String getContestName() {
            return contestName;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public long getContestId() {
            return contestId;
        }

        public String getTeam() {
            return team;
        }

        public String getAuthorName() {
            return authorName;
        }

        public long getAuthorUserId() {
            return authorUserId;
        }

        public long getDateSubscribed() {
            return dateSubscribed;
        }

        public long getCommentsCount() {
            return commentsCount;
        }

        public long getSupportersCount() {
            return supportersCount;
        }

        public long getVotesCount() {
            return votesCount;
        }

        public String getPitch() {
            return pitch;
        }

        public int getRibbon() {
            return ribbon;
        }

        public String getRibbonText() {
            return ribbonText;
        }

        public boolean isFeatured() {
            return featured;
        }
    }
}
