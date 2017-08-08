
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


