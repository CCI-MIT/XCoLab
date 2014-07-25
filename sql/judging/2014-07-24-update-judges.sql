
INSERT INTO xcolab_ContestTeamMember VALUES  (279, 1300204, 1009542, "Judge");
INSERT INTO xcolab_ContestTeamMember VALUES  (280, 1300204, 1355099, "Judge");

INSERT INTO xcolab_ContestTeamMember VALUES  (281, 1300404, 1525866, "Judge");

REPLACE Users_Roles (userId, roleId) VALUES (1525866, 1251483);

DELETE FROM xcolab_ContestTeamMember WHERE contestId = 1300404 AND userId = 1421317 AND role = "Judge" LIMIT 1;