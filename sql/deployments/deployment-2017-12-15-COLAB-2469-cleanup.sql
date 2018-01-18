-- delete broken proposal subscriptions (query to find them below)
-- select pk from xcolab_ActivitySubscription sub
--     left outer join xcolab_Proposal p on p.proposalId = sub.classPK
--     where p.proposalId is null and activityCategory = 'PROPOSAL';
-- Climate CoLab only:
delete from xcolab_ActivitySubscription where
    pk in (1465431, 1465429, 1448745, 1447104, 1447103, 1447102, 1447101, 1447017, 1447016, 1447015, 1447014, 1447013, 1447012, 1447007, 1447006, 1446778, 1446777, 1446709, 1446708, 1446707, 1446706, 1446705, 1446704, 1381001, 1380901)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Climate CoLab';

-- delete broken contest subscriptions (query to find them below)
-- select pk from xcolab_ActivitySubscription sub
--     left outer join xcolab_Contest c on c.ContestPK = sub.classPK
--     where c.ContestPK is null and activityCategory = 'CONTEST';
-- Climate CoLab only:
delete from xcolab_ActivitySubscription where
    pk in (1450001, 1450045, 1450058)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Climate CoLab';



-- delete broken proposal activities
-- select activityEntryId from activities_ActivityEntry ae
--     left outer join xcolab_Proposal p on p.proposalId = ae.categoryId
--     where p.proposalId is null and activityCategory = 'PROPOSAL';
delete from activities_ActivityEntry where
    activityEntryId in (1425644, 1425645, 1425646, 1425647, 1425648, 1425649, 1425650, 1425651, 1425652, 1425653, 1425654, 1439301, 1439302, 1445477, 1445478, 1445479, 1445480, 1445481, 1445482, 1445483, 1445484, 1445485, 1445486, 1445487, 1445489, 1445490, 1445491, 1445492, 1445493, 1445494, 1445495, 1445496, 1445497, 1445498, 1445499, 1583421, 1583422, 1583423, 1583425, 1583426, 1583427)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Climate CoLab';

-- delete broken proposal creation activities
-- select activityEntryId from activities_ActivityEntry ae
--     left outer join xcolab_Proposal p on p.proposalId = ae.additionalId
--     where p.proposalId is null and activityCategory = 'CONTEST' and activityType = 'PROPOSAL_CREATED';
delete from activities_ActivityEntry where
    activityEntryId in (1425643, 1445476, 1445488, 1583420, 1583424, 1720033, 1728786, 1728788)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Climate CoLab';
