-- -------------------------
-- Resilience Dialogues SPECIFIC
--
-- -------------------------

INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('ACTIVE_THEME', '0', '0', 'RESILIENCE_DIALOGUES', '0.0');

UPDATE xcolab_ConfigurationAttribute SET `stringValue`='{\n \"CALL_TO_ACTION\": \"\",\n \"CONTEST_TYPE_ID\": \"0\"}' WHERE `name`='PORTLET_PROPOSALS_PREFERENCES' and`additionalId`='0';


-- content article ids TODO
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID', '0', '6', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID', '0', '57', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('FOOTER_CONTENT_ARTICLE_ID', '0', '37', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('MEMBERS_CONTENT_ARTICLE_ID', '0', '22', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('DISCUSSION_CONTENT_ARTICLE_ID', '0', '16', ' ', '0');

-- COLAB-1589 ContentPages