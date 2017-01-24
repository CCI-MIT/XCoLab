---------------------------
-- Crowdsensor migration since last deployment
--
---------------------------

--- 2016-09-04
-- [COLAB-953] add new auto increment keys
ALTER TABLE xcolab_TrackedVisit MODIFY id_ BIGINT(20) AUTO_INCREMENT;
ALTER TABLE xcolab_TrackedVisitor2User MODIFY id_ BIGINT(20) AUTO_INCREMENT;

-- [COLAB-1333] add new auto increment key
ALTER TABLE xcolab_LoginLog MODIFY pk BIGINT(20) AUTO_INCREMENT;

-- [COLAB-1347] Synchronize passwords to fix missed updates
update `members_Member`, `User_`
set `members_Member`.`hashedPassword` = `User_`.password_
where `members_Member`.`id_` = `User_`.userId;

-- [COLAB-1348] Shared colab
ALTER TABLE `xcolab_Contest`
ADD COLUMN `isSharedContest` TINYINT(4) NULL DEFAULT 0 AFTER `resourceArticleId`;

ALTER TABLE `members_Member`
ADD COLUMN `autoRegisteredMemberStatus` INT(4) NULL DEFAULT 0 AFTER `reportKarma`;


--- 2016-09-18
-- COLAB-1375
ALTER TABLE xcolab_ContestPhase MODIFY ContestPhasePK BIGINT(20) AUTO_INCREMENT;
ALTER TABLE xcolab_ContestSchedule MODIFY id_ BIGINT(20) AUTO_INCREMENT;

-- delete empty contest schedules
delete from xcolab_ContestSchedule where not exists (select * from xcolab_ContestPhase where contestScheduleId = id_);

--- 2016-09-25
-- COLAB-1387
ALTER TABLE comment_Thread ADD sharedColabThreadId BIGINT(20) NULL;

-- 2016-10-09
ALTER TABLE `MembershipRequest`
CHANGE COLUMN `membershipRequestId` `membershipRequestId` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_PointsDistributionConfiguration`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_PointType`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_Proposal`
CHANGE COLUMN `proposalId` `proposalId` BIGINT(20) NOT NULL AUTO_INCREMENT ;

DELETE FROM `xcolab_ProposalAttribute` WHERE `id_`='0';

ALTER TABLE `xcolab_ProposalAttribute`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

DELETE FROM `xcolab_ProposalContestPhaseAttribute` WHERE `id_`='0';

ALTER TABLE `xcolab_ProposalContestPhaseAttribute`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_ProposalMoveHistory`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_ProposalRating`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_ProposalUnversionedAttribute`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_ContestPhaseType`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

UPDATE `xcolab_FocusArea` set id_ = 2  WHERE `id_`='0';
UPDATE `xcolab_FocusAreaOntologyTerm` set focusAreaId = 2 where focusAreaId = 0;


ALTER TABLE `xcolab_FocusArea`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_OntologySpace`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_PlanSectionDefinition`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `xcolab_PlanTemplate`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `Group_`
CHANGE COLUMN `groupId` `groupId` BIGINT(20) NOT NULL AUTO_INCREMENT ;

-- 2016-11-08
-- create shared table
CREATE TABLE IF NOT EXISTS `sharedcolab_SharedContest` (
`sharedContestId` BIGINT(20) NOT NULL AUTO_INCREMENT,
`contestName` VARCHAR(255) NULL,
`createDate` DATETIME NULL,
`colabOrigin` VARCHAR(45) NULL,
PRIMARY KEY (`sharedContestId`));

-- taking auto increment from table
ALTER TABLE `xcolab_Contest`
CHANGE COLUMN `ContestPK` `ContestPK` BIGINT(20) NOT NULL;

-- adding shared origin column
ALTER TABLE `xcolab_Contest`
ADD COLUMN `sharedOrigin` VARCHAR(45) NULL AFTER `isSharedContest`;

-- updating all contests to current colab of origin
update xcolab_Contest set sharedOrigin = (select stringValue from xcolab_ConfigurationAttribute where name = "COLAB_NAME");

-- 2016-12-11
ALTER TABLE xcolab_ContestType ADD COLUMN showProposalSummary TINYINT(4) DEFAULT 1;
UPDATE xcolab_ContestType set showProposalSummary = 1;
ALTER TABLE `xcolab_OntologyTerm` CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

-- 2016-12-18
-- Configuration attribute Blog URL
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('BLOG_URL', '0', '0', 'crowdsensor.org/page/news', '0');

-- Configuration attribute to use CollectionCards
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('COLAB_USES_CARDS', '0', '0', ' ', '0');

-- 2016-12-25
INSERT INTO xcolab_MemberCategory (roleId, displayName, categoryName, sortOrder, showInList, imageName, description) VALUES (10118, 'Admin', 'Admin', 1, 0, 'icon_mem-staff', 'Site admins - non-visible role');

-- COLAB-1362
update xcolab_ProposalMoveHistory set sourcePhaseId = null where sourcePhaseId = 0 or sourcePhaseId = 20;

-- COLAB-1485
ALTER TABLE members_Member
  ADD COLUMN `uuid` VARCHAR(40) NULL AFTER `autoRegisteredMemberStatus`;

ALTER TABLE `xcolab_BalloonText`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('GOOGLE_RECAPTCHA_SITE_KEY', '0', '0', '', '0');
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('GOOGLE_RECAPTCHA_SITE_SECRET_KEY', '0', '0', '', '0');

INSERT INTO `xcolab_ContestTeamMemberRole` (`id_`, `role`, `sort`) VALUES ('1975251', 'IAF', '3');