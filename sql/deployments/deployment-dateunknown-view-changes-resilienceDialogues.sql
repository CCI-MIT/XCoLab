-- -------------------------
-- Resilience Dialogues SPECIFIC
--
-- -------------------------

INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('ACTIVE_THEME', '0', '0', 'RESILIENCE_DIALOGUES', '0.0');

UPDATE xcolab_ConfigurationAttribute SET `stringValue`='{\n \"CALL_TO_ACTION\": \"\",\n \"CONTEST_TYPE_ID\": \"0\"}' WHERE `name`='PORTLET_PROPOSALS_PREFERENCES' and`additionalId`='0';