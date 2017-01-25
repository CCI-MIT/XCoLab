---------------------------
-- SOLVE COLAB SPECIFIC
--
---------------------------

-- Solve colab content article ids
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID', '0', '6', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID', '0', '57', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('FOOTER_CONTENT_ARTICLE_ID', '0', '37', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('MEMBERS_CONTENT_ARTICLE_ID', '0', '22', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('DISCUSSION_CONTENT_ARTICLE_ID', '0', '16', ' ', '0');

INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('ACTIVE_THEME', '0', '0', 'SOLVE_COLAB', '0.0');

UPDATE xcolab_ContentArticleVersion SET `content`='<div class=\"inner\" >\n	<div class=\"home-left\">\n		<div class=\"colabwidget\" data-url=\"/contestswidget\"> </div>\n	</div>\n	<div class=\"home-right\">\n		<div class=\"colabwidget\" data-article-id=\"13\"></div>\n		\n	</div>\n</div>\n' WHERE `contentArticleVersionId`='281';
UPDATE `xcolab_ContentArticleVersion` SET `content`='<div id=\"footleft\" style=\"width: inherit;\"><a href=\"http://solve.mit.edu/\" target=\"_blank\"><img alt=\"Solve\" src=\"/images/solveColab-logo.png\" style=\"display: inline-block; height: 45px; margin-right: 20px;\" /></a> <a href=\"http://cci.mit.edu/\" target=\"_blank\"><img alt=\"CCI\" src=\"/images/footer_logo.png\" style=\"display: inline-block; height: 51px;\" /></a></div>\n\n<div id=\"footright\">\n<div id=\"txt\" style=\"text-align: right;\">Your use of the Solve CoLab&nbsp;is subject to our<br />\n<a href=\"http://creativecommons.org/licenses/by-nc-sa/3.0/us/\">Creative Commons License</a> and other <a href=\"/web/guest/resources/-/wiki/Main/Terms%20of%20use\">Terms of Use</a>.</div>\n\n<div id=\"cc\"><img alt=\"CC\" height=\"31\" src=\"/images/cc_logo.gif\" style=\"float: right;\" width=\"88\" /></div>\n</div>' WHERE `contentArticleVersionId`='283';

UPDATE xcolab_ConfigurationAttribute SET `stringValue`='{\n \"CALL_TO_ACTION\": \"\",\n \"CONTEST_TYPE_ID\": \"3\"}' WHERE `name`='PORTLET_PROPOSALS_PREFERENCES' and`additionalId`='0';


-- COLAB-1589 ContentPages for Solve CoLab
INSERT INTO xcolab_ContentPage (title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES ('about', 29, 38, null, null);