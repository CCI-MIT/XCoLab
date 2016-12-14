package org.xcolab.client.proposals.pojo.proposals;


import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProposalRatings {
    private final List<ProposalRating> proposalRatings;
    private final Member author;
    private String comment;
    private ContestPhase contestPhase;

    public ProposalRatings(long authorId, List<ProposalRating> proposalRatings,
            Long roundFactor) throws MemberNotFoundException {
        this(MembersClient.getMember(authorId), proposalRatings, roundFactor);
    }

    public ProposalRatings(long authorId) throws MemberNotFoundException {
        this(MembersClient.getMember(authorId), Collections.<ProposalRating>emptyList());
    }
    public ProposalRatings(long authorId, List<ProposalRating> proposalRatings) {
        this(MembersClient.getMemberUnchecked(authorId), proposalRatings);
    }

    public ProposalRatings(Member author, List<ProposalRating> proposalRatings) {
        this(author, proposalRatings, 1L);
    }

    public ProposalRatings(Member author, List<ProposalRating> proposalRatings, Long roundFactor) {
        List<ProposalRating> wrapped = new ArrayList<>();
        for (ProposalRating r : proposalRatings) {
            wrapped.add(new ProposalRating(r, roundFactor));
        }

        //sort the list
        Collections.sort(wrapped, new Comparator<ProposalRating>() {
            @Override
            public int compare(ProposalRating r1, ProposalRating r2) {
                return r1.getRatingTypeId().compareTo(r2.getRatingTypeId());
            }
        });

        this.proposalRatings = wrapped;
        this.author = author;
    }


    public List<ProposalRating> getRatings() {
        return proposalRatings;
    }

    public String getComment() {
        if(comment != null){
            return comment;
        } else {
            for (ProposalRating r : proposalRatings) {
                if (r.unwrap().getCommentEnabled()) {
                    return r.unwrap().getComment_();
                }
            }
            return "";
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentEscaped() {
        String comment = this.getComment();
        comment = org.apache.commons.lang.StringEscapeUtils.escapeHtml(comment);
        comment = comment.replaceAll("\n", "<br>");
        return comment;
    }

    public void setContestPhase(ContestPhase contestPhase){
        this.contestPhase = contestPhase;
    }

    public String getContestPhase(){
        String contestPhaseTitle = "";

            if(this.contestPhase != null) {
                contestPhaseTitle = ContestClientUtil.getContestPhaseName(this.contestPhase);
            } else {
                if (!proposalRatings.isEmpty()) {
                    long contestPhaseId = proposalRatings.get(0).unwrap().getContestPhaseId();
                    ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
                    contestPhaseTitle = ContestClientUtil.getContestPhaseName(contestPhase);
                }
            }


        return contestPhaseTitle.replace("selection", "Evaluation");
    }

    public boolean isReviewComplete() {
        if (!this.proposalRatings.isEmpty()) {
            boolean result = true;
            for (ProposalRating r : proposalRatings) {
                if (!r.unwrap().isRatingComplete()) {
                    result = false;
                }
            }
            return result;
        } else {
            return false;
        }
    }

    public Member getAuthor() {
        return author;
    }
}
