INSERT INTO `xcolab_ContentArticle` (`contentArticleId`,`authorId`,`createDate`,`maxVersionId`,`folderId`,`editRoleGroupId`,`viewRoleGroupId`,`visible`) VALUES (2,2000335,'2016-04-12 17:28:45',4778,5,NULL,NULL,1);
INSERT INTO `xcolab_ContentArticle` (`contentArticleId`,`authorId`,`createDate`,`maxVersionId`,`folderId`,`editRoleGroupId`,`viewRoleGroupId`,`visible`) VALUES (3,2000335,'2016-04-12 17:28:45',4778,5,NULL,NULL,1);
INSERT INTO `xcolab_ContentArticleVersion` (`contentArticleVersionId`,`contentArticleId`,`folderId`,`authorId`,`createDate`,`title`,`content`) VALUES (6,2,5,2000335,'2016-04-12 17:28:45','Landing page top news','\n\n\n	<h2>Top News</h2>\n</div>\n');
INSERT INTO `xcolab_ContentArticleVersion` (`contentArticleVersionId`,`contentArticleId`,`folderId`,`authorId`,`createDate`,`title`,`content`) VALUES (1566,2,5,2000335,'2016-05-16 17:04:45','Landing page top news','<h2>Top News</h2>\n\n');
INSERT INTO `xcolab_ContentArticleVersion` (`contentArticleVersionId`,`contentArticleId`,`folderId`,`authorId`,`createDate`,`title`,`content`) VALUES (1567,3,5,2000335,'2016-05-16 17:04:45','Landing page top news','<h2>Top News</h2>\n\n');
INSERT INTO `xcolab_ContentFolder` (`contentFolderId`,`contentFolderName`,`contentFolderDescription`,`parentFolderId`) VALUES (2,'Static Content','Displays static content that should be editable on the site',1);
INSERT INTO `xcolab_ContentFolder` (`contentFolderId`,`contentFolderName`,`contentFolderDescription`,`parentFolderId`) VALUES (3,'Static Content','Displays static content that should be editable on the site',2);

INSERT INTO `files_FileEntry` (`fileEntryId`,`createDate`,`fileEntryExtension`,`fileEntryName`,`fileEntrySize`) VALUES (901,'2013-01-01 00:00:00','jpeg',' ',10);
INSERT INTO `xcolab_ContentPage` (`pageId`,`title`,`menuArticleId`,`contentArticleId`,`createdDate`,`modifiedDate`) VALUES (2,'crowdsourcing',1550,7,'2017-03-10 02:00:00','2017-03-10 02:00:00');
