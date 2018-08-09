ALTER TABLE MembershipRequest DROP companyId;

ALTER TABLE Group_ DROP companyId;
ALTER TABLE Group_ DROP creatorUserId;
ALTER TABLE Group_ DROP classNameId;
ALTER TABLE Group_ DROP classPK;
ALTER TABLE Group_ DROP parentGroupId;
ALTER TABLE Group_ DROP liveGroupId;
ALTER TABLE Group_ DROP description;
ALTER TABLE Group_ DROP type_;
ALTER TABLE Group_ DROP typeSettings;
ALTER TABLE Group_ DROP friendlyURL;
ALTER TABLE Group_ DROP active_;
ALTER TABLE Group_ DROP site;
ALTER TABLE Group_ DROP uuid_;
ALTER TABLE Group_ DROP treePath;
ALTER TABLE Group_ DROP manualMembership;
ALTER TABLE Group_ DROP membershipRestriction;
ALTER TABLE Group_ DROP remoteStagingGroupCount;

-- delete groups that aren't for a proposal (liferay used to create a group for each new user)
delete from Group_ where groupId not in (select groupId from xcolab_Proposal);

DROP TABLE IF EXISTS xcolab_ProposalContestPhaseAttributeType;
