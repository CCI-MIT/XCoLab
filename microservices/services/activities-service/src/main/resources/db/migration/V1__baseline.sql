CREATE TABLE IF NOT EXISTS `activities_ActivityEntry` (
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
  PRIMARY KEY (`activityEntryId`),
  KEY `activityEntry_memberId` (`memberId`),
  KEY `activityEntry_createDate` (`createDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ActivitySubscription` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `receiverId` bigint(20) NOT NULL,
  `activityCategory` VARCHAR(30) NOT NULL,
  `categoryId` BIGINT(20) NOT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `automaticSubscriptionCounter` int(11) DEFAULT NULL,
  `extraData` varchar(256) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  KEY `ActivitySubscription_receiver_index` (`receiverId`),
  KEY `ActivitySubscription_category_id_receiver_index` (`activityCategory`, `categoryId`, `receiverId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
