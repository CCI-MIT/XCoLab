DELETE from SocialActivity where classNameId = 1368503 AND classpk NOT IN (SELECT proposalId from xcolab_Proposal);
DELETE from SocialActivity where type_ = 15 OR type_ = 17;
DELETE from SocialActivity where activityid = 1228906;
