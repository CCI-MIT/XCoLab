CREATE TABLE IF NOT EXISTS `files_FileEntry` (
  `fileEntryId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `createDate` datetime DEFAULT NULL,
  `fileEntryExtension` varchar(10) DEFAULT NULL,
  `fileEntryName` varchar(255) DEFAULT NULL,
  `fileEntrySize` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ContentArticle` (
  `contentArticleId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `authorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `maxVersionId` bigint(20) DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `editRoleGroupId` bigint(20) DEFAULT NULL,
  `viewRoleGroupId` bigint(20) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ContentArticleVersion` (
  `contentArticleVersionId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `contentArticleId` bigint(20) DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `title` varchar(555) DEFAULT NULL,
  `lang` varchar(2) DEFAULT 'en',
  `content` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ContentFolder` (
  `contentFolderId` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `contentFolderName` varchar(255) DEFAULT NULL,
  `contentFolderDescription` text,
  `parentFolderId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ContentPage` (
  `pageId` bigint(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `metaDescription` VARCHAR(255),
  `menuArticleId` bigint(11) DEFAULT NULL,
  `contentArticleId` bigint(11) NOT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `modifiedDate` timestamp NULL DEFAULT NULL,
  UNIQUE KEY `xcolab_ContentPage_title_uindex` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

