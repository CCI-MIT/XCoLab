delete from activities_ActivityEntry;
delete from xcolab_ActivitySubscription;
delete from xcolab_ColabEmail;

-- Contests
delete from xcolab_Contest;
delete from xcolab_ContestCollectionCard;
delete from xcolab_ContestDiscussion;
delete from xcolab_ContestPhase where not ContestPK = 0; -- don't delete schedule phases
delete from xcolab_ContestTeamMember;

delete from xcolab_Proposal;
delete from xcolab_Proposal2Phase;
delete from xcolab_ProposalAttribute;
delete from xcolab_ProposalContestPhaseAttribute;
delete from xcolab_ProposalMoveHistory;
delete from xcolab_ProposalRating;
delete from xcolab_ProposalRatingValue;
delete from xcolab_ProposalReference;
delete from xcolab_ProposalSupporter;
delete from xcolab_ProposalUnversionedAttribute;
delete from xcolab_ProposalVersion;
delete from xcolab_ProposalVote;
delete from xcolab_ProposalVote_deleted;
delete from xcolab_ProposalVoteRemoved;
delete from MembershipRequest;

delete from xcolab_Message;
delete from xcolab_MessageRecipientStatus;

delete from comment_Comment;
delete from comment_Thread;
