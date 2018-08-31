ALTER TABLE user__staff_member MODIFY id bigint AUTO_INCREMENT

-- BEGIN: Climate CoLab specific
UPDATE user__staff_member
SET first_names="Miguel", last_name="Gamallo", category_id=8
WHERE user_id=2695585 AND EXISTS(SELECT * FROM admin__configuration_attribute WHERE name='COLAB_NAME' AND string_value='Climate CoLab')

UPDATE user__staff_member
SET first_names="Jorge", last_name="Cañada", category_id=8
WHERE user_id=2695625

UPDATE user__staff_member
SET first_names="JavierAnton", last_name="Vidarte", category_id=8
WHERE user_id=2695632

INSERT INTO user__staff_member (user_id,category_id,first_names,last_name,role)
SELECT 2697976, 5, "Sabrina", "Mohr", "Visiting student:<br/>Project assistant"
WHERE EXISTS(SELECT * FROM admin__configuration_attribute WHERE name='COLAB_NAME' AND string_value='Climate CoLab')

INSERT INTO user__staff_member (user_id,category_id,first_names,last_name,role)
SELECT 2697978, 5, "Nikolai", "Kraler", "Visiting student:<br/>Software developer"
WHERE EXISTS(SELECT * FROM admin__configuration_attribute WHERE name='COLAB_NAME' AND string_value='Climate CoLab')
-- END: Climate CoLab specific