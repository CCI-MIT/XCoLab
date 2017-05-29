ALTER TABLE xcolab_ProposalUnversionedAttribute CHANGE addtionalId additionalId BIGINT(20);

update comment_CategoryGroup set url = '/discussion' where groupId = 701;
