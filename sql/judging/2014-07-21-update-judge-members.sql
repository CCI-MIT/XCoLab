
INSERT INTO xcolab_ContestTeamMember VALUES  (274, 1300201, 1502171, "Judge");

INSERT INTO xcolab_ContestTeamMember VALUES  (275, 1300207, 1428736, "Judge");
INSERT INTO xcolab_ContestTeamMember VALUES  (276, 1300207, 1488709, "Judge");

INSERT INTO xcolab_ContestTeamMember VALUES  (277, 1300206, 1469350, "Judge");
REPLACE Users_Roles (userId, roleId) VALUES (1469350, 1251483);

INSERT INTO xcolab_ContestTeamMember VALUES  (278, 1300208, 1004996, "Judge");
REPLACE Users_Roles (userId, roleId) VALUES (1004996, 1251483);


-- Winners selection: 13 => 15
-- Semi-Finalist selection: 11->16
-- Winners awarded: 14 -> 17
UPDATE xcolab_ContestPhase SET ContestPhaseType = 15 WHERE ContestPhaseType = 13 AND PhaseStartDate > "2014-01-01";
UPDATE xcolab_ContestPhase SET ContestPhaseType = 16 WHERE ContestPhaseType = 11 AND PhaseStartDate > "2014-01-01";
UPDATE xcolab_ContestPhase SET ContestPhaseType = 17 WHERE ContestPhaseType = 14 AND PhaseStartDate > "2014-01-01";