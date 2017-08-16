-- COLAB-1433
ALTER TABLE xcolab_ContestType ADD COLUMN showProposalSummary TINYINT(4) DEFAULT 1;
UPDATE xcolab_ContestType set showProposalSummary = 1;