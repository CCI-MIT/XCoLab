-- portlet preferences
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES
('PORTLET_CONTACT_FORM_PREFERENCES', '0', '0', '
{\"RECIPIENTS_PREF\": \"pdeboer@mit.edu,lfi@mit.edu,annalyn@mit.edu,jperron@mit.edu\",\"MESSAGE_SUBJECT_PREF\": \"[CoLab] USER_NAME sent a message using contact form\",\"MESSAGE_FORMAT_PREF\": \"USER_NAME (USER_EMAIL) has sent message using contact form 1 USER_MESSAGE\",\"EXPAND_LINK_TEXT_PREF\": \"Send feedback message\"}', '0');

INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('PORTLET_CONTESTS_PREFERENCES', '0', '0', '{\"SELECTED_CONTESTS_PREFERENCE\": \"1304501,1304115\",\"TITLE_PREFERENCE\":\"Featured Contests\",\"ALL_CONTESTS_TITLE\":\"See all contests\",\"SHOW_COUNTS\":\"true\",\"ALL_CONTESTS_URL\":\"http://climatecolab.org/contests\",\"FEED_SIZE_PREFERENCE\":\"2\"}', '0');
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('PORTLET_FEED_PREFERENCES', '0', '0', '{\"FEED_SIZE_PREF\":\"7\"},{\"FEED_TYPE_PREF\":\"ACTIVITIES\"},{\"FEED_TITLE_PREF\":\"Recent Activities\"},{\"PORTLET_TITLE\":\"\"},{\"FEED_REMOVE_ADMIN\":\"false\"},{\"FEED_SEE_MORE_LINK_SHOWN\":\"false\"},{\"FEED_MAX_LENGTH\":\"0\"},{\"FEED_DISPLAY_STYLE\":\"SHORT\"}', '0');
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('PORTLET_RANDOM_PROPOSALS_PREFERENCES', '0', '0',
 '{
 \"SELECTED_PHASES_PREFERENCE\": \"1312801\",
 \"FLAG_FILTER_PREFERENCE\": \"\",
 \"TITLE_PREFERENCE\": \"Interesting Proposals\",
 \"ALL_PROPOSALS_TITLE\": \"see all finalists\",
 \"ALL_PROPOSALS_URL\": \"/community/-/blogs/finalists-selected-vote-to-select-popular-choice-winner-2#Vote\",
 \"FEED_SIZE_PREFERENCE\": \"4\",
 \"IS_COMPACT\": \"false\"}', '0');

 INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('PORTLET_STAFF_MEMBERS_PREFERENCES', '0', '0',
 '{
 \"COLUMN_AMOUNT\": \"3\",
 \"PORTLET_TITLE\": \"Project team\",
 \"DISPLAY_PHOTO\": \"true\",
 \"DISPLAY_URL\": \"true\",
 \"CATEGORY_ID\": \"5\"}', '0');


INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('PORTLET_DISCUSSION_PREFERENCES', '0', '0', '{}', '0');


 INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('PORTLET_PROPOSALS_PREFERENCES', '0', '0',
 '{
 \"CALL_TO_ACTION\": \"\",
 \"CONTEST_TYPE_ID\": \"0\"}', '0');

-- COLAB IMAGE UPDATE ON CONTENT ARTICLE FOR FOOTER
UPDATE  xcolab_ContentArticleVersion SET `content`='\n\n\n	<div id=\"footleft\"><a href=\"http://cci.mit.edu/\"><img alt=\"MIT\" height=\"51\" src=\"/images/footer_logo.png\" style=\"float: left;\" width=\"210\" /></a></div>\n\n<div id=\"footright\">\n<div id=\"txt\">Your use of the <a href=\"http://cci.mit.edu/\">MIT Center for Collective Intelligence</a> Climate CoLab is subject to our <a href=\"http://creativecommons.org/licenses/by-nc-sa/3.0/us/\">Creative Commons License</a> and other <a href=\"/web/guest/resources/-/wiki/Main/Terms%20of%20use\">Terms of Use</a>.</div>\n\n<div id=\"cc\"><img alt=\"CC\" height=\"31\" src=\"/images/cc_logo.gif\" width=\"88\" /></div>\n</div>\n' WHERE `contentArticleVersionId`='1975';

-- UPDATE FOR HOME BOTTOM ARTICLE
UPDATE `xcolab_ContentArticleVersion` SET `content`='<div class=\"inner\">\n	<div class=\"home-left\">\n		<div class=\"colabwidget\" data-url=\"/contestswidget\"></div>\n		<div class=\"colabwidget\" data-url=\"/feedswidget\"></div>\n	</div>\n	<div class=\"home-right\">\n		<div class=\"colabwidget\" data-article-id=\"2\"></div>\n		<div class=\"colabwidget\" data-article-id=\"3\"></div>\n	</div>\n</div>\n' WHERE `contentArticleVersionId`='3577';


INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('ACTIVE_THEME', '0', '0', 'CLIMATE_COLAB', '0.0');

-- bottom content article
-- INSERT INTO xcolab_ContentArticle (`contentArticleId`, `authorId`, `createDate`, `maxVersionId`, `folderId`, `visible`) VALUES ('1615', '0', '2017-01-03 10:57:40', '3577', '5', '1');

-- INSERT INTO xcolab_ContentArticleVersion (`contentArticleVersionId`, `contentArticleId`, `folderId`, `authorId`, `createDate`, `title`, `content`) VALUES ('3577', '1615', '5', '0', '2017-01-01 10:57:40', 'HomeBottom', '<div class=\"inner\"> 	<div class=\"home-left\"> 		<div class=\"colabwidget\" data-url=\"/contestswidget\"></div> 		<div class=\"colabwidget\" data-url=\"/feedswidget\"></div> 		<div class=\"colabwidget\" data-url=\"/randomproposalswidget\"></div> 	</div> 	<div class=\"home-right\"> 		<div class=\"colabwidget\" data-article-id=\"2\"></div> 		<div class=\"colabwidget\" data-article-id=\"3\"></div> 	</div> </div> ');

-- UPDATE `xcolab_ContentArticleVersion` SET `content`='<div class=\"inner\" >\n	<div class=\"home-left\">\n		<div class=\"colabwidget\" data-url=\"/contestswidget\"></div>\n		<div class=\"colabwidget\" data-url=\"/feedswidget\"></div>\n		<div class=\"colabwidget\" data-url=\"/randomproposalswidget\"></div>\n	</div>\n	<div class=\"home-right\">\n		<div class=\"colabwidget\" data-article-id=\"2\"></div>\n		<div class=\"colabwidget\" data-article-id=\"3\"></div>\n	</div>\n</div>\n' WHERE `contentArticleVersionId`='3577';
