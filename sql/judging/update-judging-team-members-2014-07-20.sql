UPDATE `xcolab_ContestPhaseType` SET `name`='Semi-Finalist selection', `description`='Expert judges select semi-finalists<br/><br/>' WHERE `id_`='11';
UPDATE `xcolab_ContestPhaseType` SET `description`='Semi-Finalists can improve their proposals<br/><br/>' WHERE `id_`='12';
UPDATE `xcolab_ContestPhaseType` SET `name`='Winners awarded' WHERE `id_`='14';
UPDATE `xcolab_ContestPhaseType` SET `name`='Winners selection' WHERE `id_`='13';


INSERT INTO xcolab_ContestTeamMember VALUES  (263, 1300203, 1499399, "Judge");
REPLACE Users_Roles (userId, roleId) VALUES (1499399, 1251483);




INSERT INTO xcolab_ContestTeamMember VALUES  (264, 1300801, 1500267, "Judge");
REPLACE Users_Roles (userId, roleId) VALUES (1500267, 1251483);



INSERT INTO xcolab_ContestTeamMember VALUES  (265, 1300201, 1479477, "Judge");
INSERT INTO xcolab_ContestTeamMember VALUES  (266, 1300201, 1488090, "Judge");
INSERT INTO xcolab_ContestTeamMember VALUES  (267, 1300201, 1356584, "Judge");
INSERT INTO xcolab_ContestTeamMember VALUES  (268, 1300201, 1502171, "Judge");
REPLACE Users_Roles (userId, roleId) VALUES (1479477, 1251483);
REPLACE Users_Roles (userId, roleId) VALUES (1488090, 1251483);
REPLACE Users_Roles (userId, roleId) VALUES (1356584, 1251483);
REPLACE Users_Roles (userId, roleId) VALUES (1502171, 1251483);



INSERT INTO xcolab_ContestTeamMember VALUES  (269, 1300207, 1428736, "Judge");
REPLACE Users_Roles (userId, roleId) VALUES (1428736, 1251483);

INSERT INTO xcolab_ContestTeamMember VALUES  (270, 1300207, 1488709, "Judge");
REPLACE Users_Roles (userId, roleId) VALUES (1488709, 1251483);


INSERT INTO xcolab_ContestTeamMember VALUES  (271, 1300206, 1469350, "Judge");
REPLACE Users_Roles (userId, roleId) VALUES (1469350, 1251483);


INSERT INTO xcolab_ContestTeamMember VALUES  (272, 1300208, 1004996, "Judge");
REPLACE Users_Roles (userId, roleId) VALUES (1004996, 1251483);