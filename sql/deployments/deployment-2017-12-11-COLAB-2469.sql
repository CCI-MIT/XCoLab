ALTER TABLE activities_ActivityEntry DROP `activityEntryTitle`;
ALTER TABLE activities_ActivityEntry DROP `activityEntryBody`;
ALTER TABLE activities_ActivityEntry DROP activityEntryName;

ALTER TABLE activities_ActivityEntry ADD activityCategory VARCHAR(30) NULL;
ALTER TABLE activities_ActivityEntry ADD activityType VARCHAR(75) NULL;
ALTER TABLE activities_ActivityEntry
  MODIFY COLUMN activityCategory VARCHAR(30) AFTER createDate,
  MODIFY COLUMN activityType VARCHAR(75) AFTER activityCategory;

ALTER TABLE activities_ActivityEntry MODIFY memberId BIGINT(20) NOT NULL;
ALTER TABLE activities_ActivityEntry MODIFY createDate DATETIME NOT NULL;

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
-- Some proposal created activities have the wrong primary type in the DB
update activities_ActivityEntry set activityCategory = 'CONTEST', activityType = 'PROPOSAL_CREATED' where primaryType = 1368503 and secondaryType = 0;

-- Comments
update activities_ActivityEntry set activityCategory = 'PROPOSAL', activityType = 'COMMENT_ADDED' where primaryType = 39202 and secondaryType = 1;
update activities_ActivityEntry set activityCategory = 'CONTEST', activityType = 'COMMENT_ADDED' where primaryType = 39202 and secondaryType = 6;
update activities_ActivityEntry set activityCategory = 'DISCUSSION', activityType = 'CREATED' where primaryType = 39202 and secondaryType = 3;
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

-- ====
-- Populate categoryId and additionalId fields

-- Proposals
update activities_ActivityEntry set categoryId = classPrimaryKey where activityCategory = 'PROPOSAL' and not activityType = 'COMMENT_ADDED';

-- Contests
update activities_ActivityEntry set categoryId = classPrimaryKey, additionalId = extraData where activityCategory = 'CONTEST' and activityType = 'PROPOSAL_CREATED' and extraData > 0;
update activities_ActivityEntry set categoryId = 0, additionalId = classPrimaryKey where activityCategory = 'CONTEST' and activityType = 'PROPOSAL_CREATED' and (extraData = '' or extraData is NULL);

-- Members
update activities_ActivityEntry set categoryId = classPrimaryKey where activityCategory = 'MEMBER';

-- Discussions
-- delete activities for spam comments that were fully deleted from database
delete from activities_ActivityEntry where activityEntryId in (1687625, 1719867, 1687626, 1720296);
-- only convert activities in new format (category != 701)
update activities_ActivityEntry set categoryId = (select threadId from comment_Comment where commentId = extraData) where activityCategory = 'DISCUSSION' and activityType = 'CREATED' and not classPrimaryKey = 701;
update activities_ActivityEntry set categoryId = (select threadId from comment_Comment where commentId = extraData), additionalId = extraData where activityCategory = 'DISCUSSION' and activityType = 'COMMENT_ADDED';

-- Proposal comments
update activities_ActivityEntry set categoryId = (select proposalId from xcolab_Proposal where discussionId = classPrimaryKey), additionalId = extraData where activityCategory = 'PROPOSAL' and activityType = 'COMMENT_ADDED';

-- Contest comments
update activities_ActivityEntry set categoryId = (select ContestPK from xcolab_Contest where discussionGroupId = classPrimaryKey), additionalId = extraData where activityCategory = 'CONTEST' and activityType = 'COMMENT_ADDED';

