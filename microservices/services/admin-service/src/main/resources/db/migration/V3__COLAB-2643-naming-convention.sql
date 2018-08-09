rename table xcolab_ContestEmailTemplate to admin__email_template;
rename table xcolab_ConfigurationAttribute to admin__configuration_attribute;
rename table admin_ContestTypeAttribute to admin__contest_type_attribute;

ALTER TABLE admin__email_template CHANGE type_ name varchar(75) NOT NULL;

ALTER TABLE admin__configuration_attribute CHANGE additionalId additional_id bigint(20) NOT NULL;
ALTER TABLE admin__configuration_attribute CHANGE numericValue numeric_value bigint(20);
ALTER TABLE admin__configuration_attribute CHANGE stringValue string_value longtext;
ALTER TABLE admin__configuration_attribute CHANGE realValue real_value double;

ALTER TABLE admin__contest_type_attribute CHANGE additionalId additional_id bigint(20) NOT NULL;
ALTER TABLE admin__contest_type_attribute CHANGE numericValue numeric_value bigint(20);
ALTER TABLE admin__contest_type_attribute CHANGE stringValue string_value longtext;
ALTER TABLE admin__contest_type_attribute CHANGE realValue real_value double;
