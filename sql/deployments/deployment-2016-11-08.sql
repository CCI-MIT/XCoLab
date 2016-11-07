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