-- -------------------------
-- CLIMATE COLAB SPECIFIC
--
-- -------------------------

-- COLAB IMAGE UPDATE ON CONTENT ARTICLE FOR FOOTER
UPDATE  xcolab_ContentArticleVersion SET `content`='\n\n\n	<div id=\"footleft\"><a href=\"http://cci.mit.edu/\"><img alt=\"MIT\" height=\"51\" src=\"/images/footer_logo.png\" style=\"float: left;\" width=\"210\" /></a></div>\n\n<div id=\"footright\">\n<div id=\"txt\">Your use of the <a href=\"http://cci.mit.edu/\">MIT Center for Collective Intelligence</a> Climate CoLab is subject to our <a href=\"http://creativecommons.org/licenses/by-nc-sa/3.0/us/\">Creative Commons License</a> and other <a href=\"/web/guest/resources/-/wiki/Main/Terms%20of%20use\">Terms of Use</a>.</div>\n\n<div id=\"cc\"><img alt=\"CC\" height=\"31\" src=\"/images/cc_logo.gif\" width=\"88\" /></div>\n</div>\n' WHERE `contentArticleVersionId`='1975';

-- UPDATE FOR HOME BOTTOM ARTICLE
UPDATE `xcolab_ContentArticleVersion` SET `content`='<div class=\"inner\">\n	<div class=\"home-left\">\n		<div class=\"colabwidget\" data-url=\"/contestswidget\"></div>\n		<div class=\"colabwidget\" data-url=\"/feedswidget\"></div>\n	</div>\n	<div class=\"home-right\">\n		<div class=\"colabwidget\" data-article-id=\"2\"></div>\n		<div class=\"colabwidget\" data-article-id=\"3\"></div>\n	</div>\n</div>\n' WHERE `contentArticleVersionId`='3577';


INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('ACTIVE_THEME', '0', '0', 'CLIMATE_COLAB', '0.0');

-- Climate colab content article ids
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID', '0', '1548', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID', '0', '1615', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('FOOTER_CONTENT_ARTICLE_ID', '0', '4', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('MEMBERS_CONTENT_ARTICLE_ID', '0', '1613', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('DISCUSSION_CONTENT_ARTICLE_ID', '0', '1614', ' ', '0');

-- bottom content article
-- INSERT INTO xcolab_ContentArticle (`contentArticleId`, `authorId`, `createDate`, `maxVersionId`, `folderId`, `visible`) VALUES ('1615', '0', '2017-01-03 10:57:40', '3577', '5', '1');

-- INSERT INTO xcolab_ContentArticleVersion (`contentArticleVersionId`, `contentArticleId`, `folderId`, `authorId`, `createDate`, `title`, `content`) VALUES ('3577', '1615', '5', '0', '2017-01-01 10:57:40', 'HomeBottom', '<div class=\"inner\"> 	<div class=\"home-left\"> 		<div class=\"colabwidget\" data-url=\"/contestswidget\"></div> 		<div class=\"colabwidget\" data-url=\"/feedswidget\"></div> 		<div class=\"colabwidget\" data-url=\"/randomproposalswidget\"></div> 	</div> 	<div class=\"home-right\"> 		<div class=\"colabwidget\" data-article-id=\"2\"></div> 		<div class=\"colabwidget\" data-article-id=\"3\"></div> 	</div> </div> ');

-- UPDATE `xcolab_ContentArticleVersion` SET `content`='<div class=\"inner\" >\n	<div class=\"home-left\">\n		<div class=\"colabwidget\" data-url=\"/contestswidget\"></div>\n		<div class=\"colabwidget\" data-url=\"/feedswidget\"></div>\n		<div class=\"colabwidget\" data-url=\"/randomproposalswidget\"></div>\n	</div>\n	<div class=\"home-right\">\n		<div class=\"colabwidget\" data-article-id=\"2\"></div>\n		<div class=\"colabwidget\" data-article-id=\"3\"></div>\n	</div>\n</div>\n' WHERE `contentArticleVersionId`='3577';


-- COLAB-1589 ContentPages for Climate CoLab
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('about', 1550, 6, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('crowdsourcing', 1550, 7, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('why-contests', 1550, 8, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('get-involved', 1550, 9, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conferences', 1550, 1583, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('faqs', 1550, 10, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('sponsors', 1550, 11, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('people', 1550, 12, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('press', 1550, 1549, null, null);

INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2016', 1568, 1577, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2016-program', 1568, 1567, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2016-breakoutsessions', 1568, 1578, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2016-location', 1568, 1571, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2016-joinonline', 1568, 1573, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2016-contacts', 1568, 1570, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2016-_photosandvideos', 1568, 1574, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2015', 95, 1551, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2015-program', 95, 94, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2015-speakers', 95, 96, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2015-winners', 95, 1552, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2015-joinonline', 95, 100, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2015-location', 95, 98, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2015-contacts', 95, 1553, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2014', 66, 1745, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2014-program', 66, 74, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2014-speakers', 66, 25, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2014-breakouts', 66, 1746, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2014-virtually', 66, 1747, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2014-crowdfunding', 66, 1748, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2013', 1750, 1749, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2013-speakers', 1750, 1751, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2013-breakouts', 1750, 1753, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2013-virtually', 1750, 1754, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2013-testimonials', 1750, 1755, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference2013-contacts', 1750, 1756, null, null);
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('conference-mobile', null, 1589, null, null);