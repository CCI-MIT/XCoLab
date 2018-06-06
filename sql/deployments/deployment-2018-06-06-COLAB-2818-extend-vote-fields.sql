ALTER TABLE xcolab_ProposalVote ADD voterIp varchar(75) NULL AFTER createDate;
ALTER TABLE xcolab_ProposalVote ADD voterUserAgent varchar(500) NULL AFTER voterIp;
ALTER TABLE xcolab_ProposalVote ADD lastValidationResult varchar(20) NULL;
ALTER TABLE xcolab_ProposalVote ADD manualValidationResult varchar(20) NULL;
ALTER TABLE xcolab_ProposalVote ADD isValidOverride tinyint(4) NULL;
