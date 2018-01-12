Select * from xcolab.members_Member where members_Member.createDate is  NULL ;

UPDATE xcolab.members_Member set status=0 where status is NULL;
UPDATE xcolab.members_Member set autoRegisteredMemberStatus=0 where autoRegisteredMemberStatus is NULL;
UPDATE xcolab.members_Member set loginIP='' where loginIP is NULL;

UPDATE xcolab.members_Member set createDate=() where createDate is NULL;
UPDATE xcolab.members_Member set loginDate=createDate where loginDate is NULL;
