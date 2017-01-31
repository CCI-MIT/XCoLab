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


-- COLAB-1596
ALTER TABLE members_Member ADD googleId VARCHAR(50) NULL AFTER facebookId;

-- IMGUR configuration attribute - should be
UPDATE xcolab_ConfigurationAttribute SET `stringValue`='<h4>Image upload in IMGUR:</h4><h5>This process works best on Google Chrome or Safari:</h5><p><a href=\"http://climatecolab.org/\" target=\"_blank\" style=\"color: blue; text-decoration: underline;\">Screencast</a></p><br /><ol><li>Click on Upload</li><li>Select your picture</li><li>Upload the picture to IMGUR</li><li>In Google Chrome or Safari, Right click on the IMGUR image, and select \"Copy image address\"</li><li>Paste it into the URL field</li><li>Verify the image in the preview</li><li>Click OK to insert the image</li></ol>' WHERE `name`='IMAGE_UPLOAD_HELP_TEXT' and`additionalId`='0';

