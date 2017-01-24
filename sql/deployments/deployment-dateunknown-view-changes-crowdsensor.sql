---------------------------
-- Crowdsensor SPECIFIC
--
---------------------------

-- UPDATE FOR HOME BOTTOM ARTICLE TODO
UPDATE `xcolab_ContentArticleVersion` SET `content`='<div class=\"inner\">\n	<div class=\"home-left\">\n		<div class=\"colabwidget\" data-url=\"/contestswidget\"></div>\n		<div class=\"colabwidget\" data-url=\"/feedswidget\"></div>\n	</div>\n	<div class=\"home-right\">\n		<div class=\"colabwidget\" data-article-id=\"2\"></div>\n		<div class=\"colabwidget\" data-article-id=\"3\"></div>\n	</div>\n</div>\n' WHERE `contentArticleVersionId`='3577';

INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('ACTIVE_THEME', '0', '0', 'CROWDSENSOR', '0.0');

-- content article ids TODO
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID', '0', '490', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID', '0', '1615', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('FOOTER_CONTENT_ARTICLE_ID', '0', '4', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('MEMBERS_CONTENT_ARTICLE_ID', '0', '1613', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('DISCUSSION_CONTENT_ARTICLE_ID', '0', '1614', ' ', '0');

-- COLAB-1589 ContentPages