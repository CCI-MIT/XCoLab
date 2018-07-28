CREATE TABLE IF NOT EXISTS `comment_CategoryGroup` (
  `groupId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `description` text,
  `url` varchar(200) DEFAULT NULL,
  `isQuiet` tinyint(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `comment_Category` (
  `categoryId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `groupId` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` text,
  `createDate` datetime NOT NULL,
  `deletedDate` datetime DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `isQuiet` tinyint(4) DEFAULT '0',
  INDEX `comment_Category__groupId` (`groupId`,`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `comment_Comment` (
  `commentId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `threadId` bigint(20) NOT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `deletedDate` datetime DEFAULT NULL,
  `content` text,
  INDEX `comment_Comment__threadId` (`threadId`,`createDate`),
  INDEX `comment_Comment__authorId` (`authorId`)/*,
  FULLTEXT INDEX `content_comment_Comment` (`content`)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `comment_Thread` (
  `threadId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `categoryId` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `createDate` datetime NOT NULL,
  `deletedDate` datetime DEFAULT NULL,
  `isQuiet` tinyint(4) DEFAULT '0',
  `sharedColabThreadId` bigint(20) DEFAULT NULL,
  INDEX `comment_Thread__categoryId` (`categoryId`,`createDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
