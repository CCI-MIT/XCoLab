update xcolab_ProposalUnversionedAttribute set additionalId = 0 where additionalId is NULL;

ALTER TABLE xcolab_ProposalUnversionedAttribute MODIFY additionalId BIGINT(20) NOT NULL DEFAULT 0;
ALTER TABLE xcolab_ProposalAttribute MODIFY additionalId BIGINT(20) NOT NULL DEFAULT 0;
