SET FOREIGN_KEY_CHECKS=0;
ALTER TABLE members_Member MODIFY id_ bigint(20) NOT NULL auto_increment;
SET FOREIGN_KEY_CHECKS=1;

DROP TABLE IF EXISTS sharedcolab_SharedMember;
