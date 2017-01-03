-- COLAB-1506
CREATE TABLE `xcolab_ContentPage` (
  `pageId` bigint(11) AUTO_INCREMENT,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `menuArticleId` bigint(11) DEFAULT NULL,
  `contentArticleId` bigint(11) NOT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `modifiedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pageId`),
  UNIQUE KEY `xcolab_ContentPage_title_uindex` (`title`)
);