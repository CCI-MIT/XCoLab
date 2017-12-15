DROP TABLE `activities_ActivityEntry` IF EXISTS;
CREATE TABLE `activities_ActivityEntry` (
  `activityEntryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `memberId` bigint(20) NOT NULL,
  `createDate` datetime NOT NULL,
  `activityCategory` VARCHAR(30) NULL,
  `activityType` VARCHAR(75) NULL,
  `categoryId` BIGINT(20) NOT NULL,
  `additionalId` BIGINT(20) NULL,
  `primaryType` bigint(20) DEFAULT NULL,
  `secondaryType` bigint(20) DEFAULT NULL,
  `classPrimaryKey` bigint(20) DEFAULT NULL,
  `extraData` VARCHAR(75),
  PRIMARY KEY (`activityEntryId`)
) ENGINE=InnoDB AUTO_INCREMENT=1722454 DEFAULT CHARSET=utf8;

DROP TABLE `xcolab_ActivitySubscription` IF EXISTS;
CREATE TABLE `xcolab_ActivitySubscription` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `receiverId` bigint(20) NOT NULL,
  `activityCategory` VARCHAR(30) NOT NULL,
  `categoryId` BIGINT(20) NOT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `automaticSubscriptionCounter` int(11) DEFAULT NULL,
  `extraData` varchar(256) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`pk`),
) ENGINE=InnoDB AUTO_INCREMENT=1451707 DEFAULT CHARSET=utf8;
