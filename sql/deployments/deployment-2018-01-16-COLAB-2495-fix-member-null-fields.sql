# Display all entries that have the issue
# SELECT * FROM xcolab.members_Member WHERE members_Member.status IS  NULL ;

# Display entries without a createDate and the replacement createDate (DryRun)
# SELECT T1.id_, T1.screenName, T1.createDate, T2.createDate
# FROM xcolab.members_Member T1, xcolab.members_Member T2
# WHERE T1.id_ = T2.id_ + 1 AND T1.createDate IS NULL;

# Update status fields
UPDATE members_Member SET status=0 WHERE status IS NULL;
UPDATE members_Member SET autoRegisteredMemberStatus=0 WHERE autoRegisteredMemberStatus IS NULL;

# Update createDate field
UPDATE members_Member AS T1
JOIN members_Member AS T2
    ON T1.id_-1 = T2.id_
SET T1.createDate = T2.createDate
WHERE T1.createDate IS NULL;
