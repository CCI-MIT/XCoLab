DROP TABLE `xcolab_ContentArticle` IF EXISTS ;
CREATE TABLE `xcolab_ContentArticle` (
  `contentArticleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `authorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `maxVersionId` bigint(20) DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `editRoleGroupId` bigint(20) DEFAULT NULL,
  `viewRoleGroupId` bigint(20) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`contentArticleId`)
) ENGINE=InnoDB AUTO_INCREMENT=1758 DEFAULT CHARSET=utf8;
DROP TABLE `xcolab_ContentArticleVersion` IF EXISTS ;
CREATE TABLE `xcolab_ContentArticleVersion` (
  `contentArticleVersionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `contentArticleId` bigint(20) DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `title` varchar(555) DEFAULT NULL,
  `content` longtext,
  PRIMARY KEY (`contentArticleVersionId`)
) ENGINE=InnoDB AUTO_INCREMENT=4059 DEFAULT CHARSET=utf8;

DROP TABLE `xcolab_ContentFolder` IF EXISTS ;

CREATE TABLE `xcolab_ContentFolder` (
  `contentFolderId` bigint(20) NOT NULL AUTO_INCREMENT,
  `contentFolderName` varchar(255) DEFAULT NULL,
  `contentFolderDescription` text,
  `parentFolderId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`contentFolderId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
DROP TABLE `files_FileEntry` IF EXISTS ;
CREATE TABLE `files_FileEntry` (
  `fileEntryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `fileEntryExtension` varchar(10) DEFAULT NULL,
  `fileEntryName` varchar(255) DEFAULT NULL,
  `fileEntrySize` int(11) DEFAULT NULL,
  PRIMARY KEY (`fileEntryId`)
) ENGINE=InnoDB AUTO_INCREMENT=2513729 DEFAULT CHARSET=utf8;

DROP TABLE `xcolab_ContentPage` IF EXISTS ;

CREATE TABLE `xcolab_ContentPage` (
  `pageId` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100)  DEFAULT NULL,
  `metaDescription` varchar(255),
  `menuArticleId` bigint(11) DEFAULT NULL,
  `contentArticleId` bigint(11) NOT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `modifiedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pageId`),
  UNIQUE KEY `xcolab_ContentPage_title_uindex` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
