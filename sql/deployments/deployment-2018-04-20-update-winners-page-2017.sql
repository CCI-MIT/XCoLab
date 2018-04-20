/* insert new contentarticle version into table, created by cwoebker */

INSERT INTO `xcolab_ContentArticleVersion` (`contentArticleId`, `folderId`, `authorId`, `createDate`, `title`, `content`, `lang`)
VALUES (1214, 38, 2675595, '2018-04-20 18:00:00', 'Climate CoLab Contest Winners', '', 'en')

/* add new version to contentArticle */

UPDATE `xcolab_ContentArticle` t
SET t.`maxVersionId` = /* TODO: fix this */
WHERE t.`contentArticleId` = 1214
