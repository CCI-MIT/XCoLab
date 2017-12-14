ALTER TABLE activities_ActivityEntry DROP `activityEntryTitle`;
ALTER TABLE activities_ActivityEntry DROP `activityEntryBody`;
ALTER TABLE activities_ActivityEntry DROP activityEntryName;

ALTER TABLE activities_ActivityEntry ADD activityCategory VARCHAR(30) NULL;
ALTER TABLE activities_ActivityEntry ADD activityType VARCHAR(75) NULL;
ALTER TABLE activities_ActivityEntry
  MODIFY COLUMN activityCategory VARCHAR(30) AFTER createDate,
  MODIFY COLUMN activityType VARCHAR(75) AFTER activityCategory;

-- Update activity type fields

update activities_ActivityEntry set activityCategory = 'MEMBER' where primaryType = 10038;
update activities_ActivityEntry set activityCategory = 'DISCUSSION' where primaryType = 39202;
update activities_ActivityEntry set activityCategory = 'PROPOSAL' where primaryType = 1368503;
update activities_ActivityEntry set activityCategory = 'CONTEST' where primaryType = 39701;

-- Proposal activity types
update activities_ActivityEntry set activityType = 'UPDATED' where primaryType = 1368503 and secondaryType = 1;
update activities_ActivityEntry set activityType = 'UPDATED' where primaryType = 1368503 and secondaryType = 2;
update activities_ActivityEntry set activityType = 'VOTE_ADDED' where primaryType = 1368503 and secondaryType = 3;
update activities_ActivityEntry set activityType = 'VOTE_RETRACTED' where primaryType = 1368503 and secondaryType = 4;
update activities_ActivityEntry set activityType = 'VOTE_SWITCHED' where primaryType = 1368503 and secondaryType = 5;
update activities_ActivityEntry set activityType = 'MEMBER_ADDED' where primaryType = 1368503 and secondaryType = 6;
update activities_ActivityEntry set activityType = 'MEMBER_REMOVED' where primaryType = 1368503 and secondaryType = 7;
update activities_ActivityEntry set activityType = 'SUPPORT_ADDED' where primaryType = 1368503 and secondaryType = 8;
update activities_ActivityEntry set activityType = 'SUPPORT_REMOVED' where primaryType = 1368503 and secondaryType = 9;

-- Member activity types
update activities_ActivityEntry set activityType = 'REGISTERED' where primaryType = 10038 and secondaryType = 1;

-- Contest activity types
update activities_ActivityEntry set activityType = 'PROPOSAL_CREATED' where primaryType = 39701 and secondaryType = 0;

-- Comments
update activities_ActivityEntry set activityCategory = 'PROPOSAL', activityType = 'COMMENT_ADDED' where primaryType = 39202 and secondaryType = 1;
update activities_ActivityEntry set activityCategory = 'CONTEST', activityType = 'COMMENT_ADDED' where primaryType = 39202 and secondaryType = 6;
update activities_ActivityEntry set activityCategory = 'DISCUSSION', activityType = 'THREAD_ADDED' where primaryType = 39202 and secondaryType = 3;
update activities_ActivityEntry set activityCategory = 'DISCUSSION', activityType = 'COMMENT_ADDED' where primaryType = 39202 and secondaryType = 5;

-- Delete add category activities
delete from activities_ActivityEntry where primaryType = 39203 and secondaryType = 1;
delete from activities_ActivityEntry where activityEntryId in (52007, 1002426, 1462191);

-- Delete liferay's upload picture activities
delete from activities_ActivityEntry where primaryType = 10054;



-- ====

ALTER TABLE activities_ActivityEntry ADD categoryId BIGINT(20) NOT NULL;
ALTER TABLE activities_ActivityEntry
  MODIFY COLUMN categoryId BIGINT(20) NOT NULL AFTER activityType;
ALTER TABLE activities_ActivityEntry ADD additionalId BIGINT(20) NULL;
ALTER TABLE activities_ActivityEntry
  MODIFY COLUMN additionalId BIGINT(20) AFTER categoryId;

ALTER TABLE activities_ActivityEntry MODIFY extraData VARCHAR(75);
