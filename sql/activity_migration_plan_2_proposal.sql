UPDATE SocialActivity set classNameId = '1367003' where classNameId = '38302';

-- delete PUBLISH_UPDATES and EDIT_POSITIONS
-- 14. PROMOTE_PLAN - remove
DELETE FROM SocialActivity WHERE type_ IN (6, 4, 14) AND classNameId = '38302';

-- 1. ADD_PLAN - 0. PROPOSAL_CREATE
UPDATE SocialActivity SET type_ = 0 WHERE type_ = 1 AND classNameId = '38302';

-- 2. EDIT_PLAN - 1. ATTRIBUTE_UPDATE (DESCRIPTION, 0)
UPDATE SocialActivity SET type_ = 1, extraData = 'DESCRIPTION,0' WHERE type_ = 2 AND classNameId = '38302';

-- 3. EDIT_DESCRIPTION - 1.ATTRIBUTE_UPDATE extraData 'DESCRIPTION',0
UPDATE SocialActivity SET type_ = 1, extraData = 'DESCRIPTION,0' WHERE type_ = 3 AND classNameId = '38302';

-- 5. EDIT_SCENARIO - 1.ATTRIBUTE_UPDATE extraData 'SCENARIO_ID',10
UPDATE SocialActivity SET type_ = 1, extraData = 'SCENARIO_ID,10' WHERE type_ = 5 AND classNameId = '38302';

-- 7. VOTE_FOR_PLAN - 2.VOTE
UPDATE SocialActivity SET type_ = 2 WHERE type_ = 7 AND classNameId = '38302';

-- 8. SWITCH_VOTE_FOR_PLAN - 4. VOTE_SWITCH
UPDATE SocialActivity SET type_ = 4 WHERE type_ = 8 AND classNameId = '38302';

-- 9. RETRACT_VOTE_FOR_PLAN - 3. VOTE_RETRACT
UPDATE SocialActivity SET type_ = 3 WHERE type_ = 9 AND classNameId = '38302';

-- 10. USER_ADDED_TO_PLAN - 5. USER_ADD
UPDATE SocialActivity SET type_ = 5 WHERE type_ = 10 AND classNameId = '38302';

-- 11. USER_REMOVED_FROM_PLAN - 6. USER_REMOVE
UPDATE SocialActivity SET type_ = 6 WHERE type_ = 11 AND classNameId = '38302';

-- 12. BECOME_A_SUPPORTER - 7. SUPPORTER_ADD
UPDATE SocialActivity SET type_ = 7 WHERE type_ = 12 AND classNameId = '38302';

-- 13. STOPPED_BEEING_A_SUPPORTER 8. SUPPORTER_REMOVE
UPDATE SocialActivity SET type_ = 8 WHERE type_ = 13 AND classNameId = '38302';

-- 15. CHANGE_IMAGE - 1.ATTRIBUTE_UPDATE extraData 'IMAGE_ID',0
UPDATE SocialActivity SET type_ = 1, extraData = 'IMAGE_ID,0' WHERE type_ = 5 AND classNameId = '38302';

-- update class name id 
UPDATE SocialActivity set classNameId = (select classNameId from ClassName_ WHERE value LIKE 'com.ext.portlet.model.Proposal') where classNameId = '38302';
