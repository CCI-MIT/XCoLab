ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE addtionalId additionalId BIGINT(20);
ALTER TABLE members_Member ADD isEmailBounced TINYINT(4) DEFAULT 0 AFTER isEmailConfirmed;

update comment_CategoryGroup set url = '/discussion' where groupId = 701;
