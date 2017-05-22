-- COLAB-1980
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('DISPLAY_FIRST_NAME_LAST_NAME', '0', '0', '0', '0');

-- COLAB-2008
CREATE INDEX activities_ActivityEntry_memberId_index ON activities_ActivityEntry (memberId);
CREATE INDEX activities_ActivityEntry_createDate_index ON activities_ActivityEntry (createDate);
