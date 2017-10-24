-- COLAB-2373
ALTER TABLE xcolab_ProposalVote DROP PRIMARY KEY;
ALTER TABLE xcolab_ProposalVote ADD PRIMARY KEY (contestPhaseId, userId, proposalId);
ALTER TABLE xcolab_ProposalVote ADD value INT DEFAULT 1 AFTER userId;
