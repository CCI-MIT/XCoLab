INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES

('PORTLET_CONTACT_FORM_PREFERENCES', '0', '0', '
{\"RECIPIENTS_PREF\": \"pdeboer@mit.edu,lfi@mit.edu,annalyn@mit.edu,jperron@mit.edu\",\"MESSAGE_SUBJECT_PREF\": \"[CoLab] USER_NAME sent a message using contact form\",\"MESSAGE_FORMAT_PREF\": \"USER_NAME (USER_EMAIL) has sent message using contact form 1 USER_MESSAGE\",\"EXPAND_LINK_TEXT_PREF\": \"Send feedback message\"}', '0');


INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('PORTLET_CONTESTS_PREFERENCES', '0', '0', '{\"SELECTED_CONTESTS_PREFERENCE\": \"1304501,1304115\",\"TITLE_PREFERENCE\":\"Featured Contests\",\"ALL_CONTESTS_TITLE\":\"See all contests\",\"SHOW_COUNTS\":\"true\",\"ALL_CONTESTS_URL\":\"http://climatecolab.org/contests\",\"FEED_SIZE_PREFERENCE\":\"2\"}', '0');

UPDATE `xcolab`.`xcolab_ContentArticleVersion` SET `content`='\n\n\n	<div id=\"footleft\"><a href=\"http://cci.mit.edu/\"><img alt=\"MIT\" height=\"51\" src=\"/images/footer_logo.png\" style=\"float: left;\" width=\"210\" /></a></div>\n\n<div id=\"footright\">\n<div id=\"txt\">Your use of the <a href=\"http://cci.mit.edu/\">MIT Center for Collective Intelligence</a> Climate CoLab is subject to our <a href=\"http://creativecommons.org/licenses/by-nc-sa/3.0/us/\">Creative Commons License</a> and other <a href=\"/web/guest/resources/-/wiki/Main/Terms%20of%20use\">Terms of Use</a>.</div>\n\n<div id=\"cc\"><img alt=\"CC\" height=\"31\" src=\"/images/cc_logo.gif\" width=\"88\" /></div>\n</div>\n' WHERE `contentArticleVersionId`='1975';
