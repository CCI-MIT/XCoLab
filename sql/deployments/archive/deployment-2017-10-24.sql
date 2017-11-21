-- COLAB-2373
ALTER TABLE xcolab_ProposalVote DROP PRIMARY KEY;
ALTER TABLE xcolab_ProposalVote ADD PRIMARY KEY (contestPhaseId, userId, proposalId);
ALTER TABLE xcolab_ProposalVote ADD value INT DEFAULT 1 AFTER userId;
DROP INDEX `IX_EA28CF99` on xcolab_ProposalVote;
DROP INDEX `IX_43559ACF` on xcolab_ProposalVote;
DROP INDEX `IX_562EB409` on xcolab_ProposalVote;
