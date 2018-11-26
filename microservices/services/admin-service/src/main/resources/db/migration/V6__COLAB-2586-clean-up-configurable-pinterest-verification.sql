SET @isClimateColab = (SELECT EXISTS(SELECT * FROM admin__configuration_attribute WHERE name='COLAB_NAME' AND string_value='Climate CoLab'));

-- Delete database entry from previous migration
DELETE FROM admin__configuration_attribute
WHERE name='PINTEREST_ID'

-- BEGIN: Climate CoLab specific
INSERT INTO admin__configuration_attribute (name, additional_id, numeric_value, string_value, real_value)
SELECT ('PINTEREST_ID', 0, null, '70e4477f0ab70225da1533151386c16f', null)
WHERE @isClimateColab;
