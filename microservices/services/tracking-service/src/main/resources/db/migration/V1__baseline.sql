CREATE TABLE IF NOT EXISTS `xcolab_BalloonLink` (
  `uuid_` varchar(75) NOT NULL PRIMARY KEY,
  `targetUrl` varchar(75) DEFAULT NULL,
  `visits` int(11) DEFAULT NULL,
  `balloonUserUuid` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  KEY `IX_1AD9FFEC` (`balloonUserUuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_BalloonText` (
  id_ bigint auto_increment PRIMARY KEY,
	name varchar(75) null,
	textBeforeForm longtext null,
	textBeforeShareButtons longtext null,
	emailTemplate longtext null,
	emailSubjectTemplate varchar(255) null,
	shareTitle varchar(255) null,
	shareDescription text null,
	enabled tinyint null,
  KEY `IX_CE6BAAA5` (`enabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_BalloonUserTracking` (
  `uuid_` varchar(75) NOT NULL PRIMARY KEY,
  `email` varchar(200) DEFAULT NULL,
  `parent` varchar(75) DEFAULT NULL,
  `ip` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `registrationDate` datetime DEFAULT NULL,
  `formFiledDate` datetime DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `balloonTextId` bigint(20) DEFAULT NULL,
  `referrer` varchar(500) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `country` varchar(75) DEFAULT NULL,
  `extraData` longtext,
  `balloonLinkUuid` varchar(75) DEFAULT NULL,
  `balloonLinkContext` varchar(75) DEFAULT NULL,
  `userAgent` varchar(500) DEFAULT NULL,
  KEY `IX_AFDD82EB` (`email`(50))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_TrackedVisit` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `uuid_` varchar(36) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(2) DEFAULT NULL,
  `url` varchar(2048) DEFAULT NULL,
  `browser` longtext,
  `headers` longtext,
  `referer` varchar(2048) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  KEY `IX_21569857` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `xcolab_TrackedVisitor2User` (
  `uuid_` varchar(36) NOT NULL PRIMARY KEY,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
