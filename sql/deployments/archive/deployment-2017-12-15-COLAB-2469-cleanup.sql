-- delete broken proposal subscriptions (query to find them below)
-- select pk from xcolab_ActivitySubscription sub
--     left outer join xcolab_Proposal p on p.proposalId = sub.classPK
--     where p.proposalId is null and activityCategory = 'PROPOSAL';
delete from xcolab_ActivitySubscription where
    pk in (1465431, 1465429, 1448745, 1447104, 1447103, 1447102, 1447101, 1447017, 1447016, 1447015, 1447014, 1447013, 1447012, 1447007, 1447006, 1446778, 1446777, 1446709, 1446708, 1446707, 1446706, 1446705, 1446704, 1381001, 1380901)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Climate CoLab';
delete from xcolab_ActivitySubscription where
    pk in (1453379, 1453387, 1453389, 1453391, 1453395, 1453397, 1453399, 1453401, 1453403, 1453405, 1453407, 1453429, 1453431, 1453433, 1453441, 1453443, 1453445, 1453467, 1453469, 1453475, 1453489, 1453495, 1453497, 1453574, 1453624, 1453630, 1453632, 1453634, 1453636, 1453648, 1453650, 1453652, 1453664, 1453732, 1453752, 1453754, 1453758, 1453760, 1453764, 1453780, 1453783)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'ClimateRisks CoLab';
delete from xcolab_ActivitySubscription where
    pk in (1, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 21)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Crowdsensor';


-- delete broken contest subscriptions (query to find them below)
-- select pk from xcolab_ActivitySubscription sub
--     left outer join xcolab_Contest c on c.ContestPK = sub.classPK
--     where c.ContestPK is null and activityCategory = 'CONTEST';
delete from xcolab_ActivitySubscription where
    pk in (1450001, 1450045, 1450058)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Climate CoLab';
delete from xcolab_ActivitySubscription where
    pk in (1453385)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'ClimateRisks CoLab';


-- delete broken proposal activities
-- select activityEntryId from activities_ActivityEntry ae
--     left outer join xcolab_Proposal p on p.proposalId = ae.categoryId
--     where p.proposalId is null and activityCategory = 'PROPOSAL';
delete from activities_ActivityEntry where
    activityEntryId in (1425644, 1425645, 1425646, 1425647, 1425648, 1425649, 1425650, 1425651, 1425652, 1425653, 1425654, 1439301, 1439302, 1445477, 1445478, 1445479, 1445480, 1445481, 1445482, 1445483, 1445484, 1445485, 1445486, 1445487, 1445489, 1445490, 1445491, 1445492, 1445493, 1445494, 1445495, 1445496, 1445497, 1445498, 1445499, 1583421, 1583422, 1583423, 1583425, 1583426, 1583427)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Climate CoLab';
delete from activities_ActivityEntry where
    activityEntryId in (1726793, 1726795, 1726796, 1726797, 1726798, 1726799)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'ClimateRisks CoLab';
delete from activities_ActivityEntry where
    activityEntryId in (1691103, 1691104, 1691118, 1691119, 1691120, 1691155, 1691156, 1691157, 1691158, 1691161, 1691162, 1691209, 1691342, 1691343, 1691344, 1691345, 1691347, 1691348, 1691349, 1691350, 1691351, 1691352, 1691353, 1691354, 1691355, 1691356, 1691358, 1691359, 1691360, 1691361, 1691362, 1691363, 1691364, 1691365, 1691366, 1691367, 1691368, 1691369, 1691370, 1691371, 1691372, 1691373, 1691374, 1691375, 1691376, 1691379, 1691381, 1691382, 1691384, 1691389, 1691394, 1691395, 1691396, 1691397, 1691399, 1691400, 1691428, 1691429, 1691430, 1691431, 1691438, 1691444, 1691455, 1691456, 1691457, 1691458, 1691466, 1691467, 1691470, 1691471, 1691472, 1691473, 1691474, 1691551, 1691554, 1691555, 1691556, 1691557, 1691558, 1691559, 1691560, 1691561, 1691562, 1691563, 1691564, 1691565, 1691566, 1691567, 1691568, 1691569, 1691570, 1691571, 1691572, 1691573, 1691574, 1691575, 1691576, 1691577, 1691578, 1691579)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Crowdsensor';


-- delete broken proposal creation activities
-- select activityEntryId from activities_ActivityEntry ae
--     left outer join xcolab_Proposal p on p.proposalId = ae.additionalId
--     where p.proposalId is null and activityCategory = 'CONTEST' and activityType = 'PROPOSAL_CREATED';
delete from activities_ActivityEntry where
    activityEntryId in (1425643, 1445476, 1445488, 1583420, 1583424, 1720033, 1728786, 1728788)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Climate CoLab';
delete from activities_ActivityEntry where
    activityEntryId in (1726791)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'ClimateRisks CoLab';
delete from activities_ActivityEntry where
    activityEntryId in (1691102, 1691122, 1691154, 1691160)
    and (select stringValue from xcolab_ConfigurationAttribute where name = 'COLAB_NAME') = 'Crowdsensor';
