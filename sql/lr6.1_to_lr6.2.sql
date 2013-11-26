use xcolab;
-- remove missing group reference
DELETE FROM Users_Groups where groupId NOT IN ( SELECT groupId from Group_ );
