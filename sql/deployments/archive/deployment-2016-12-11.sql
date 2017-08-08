-- COLAB-1433
INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('DEFAULT_CONTEST_SCHEDULE_ID', 0, 601, '', 0);
ALTER TABLE xcolab_ContestType ADD COLUMN showProposalSummary TINYINT(4) DEFAULT 1;
UPDATE xcolab_ContestType set showProposalSummary = 1;
ALTER TABLE `xcolab_OntologyTerm`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;