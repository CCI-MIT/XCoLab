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
