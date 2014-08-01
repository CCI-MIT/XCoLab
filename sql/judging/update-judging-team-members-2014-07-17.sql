UPDATE xcolab_ContestTeamMember SET userId = "1354630" WHERE contestId = 1300203 AND userId = 1475554 LIMIT 1;

INSERT INTO `xcolab_ContestTeamMember` (`id_`, `contestId`, `userId`, `role`) VALUES ('268', '1300203', '1489793', 'Judge');
INSERT INTO `xcolab_ContestTeamMember` (`id_`, `contestId`, `userId`, `role`) VALUES ('269', '1300403', '1475378', 'Judge');
INSERT INTO `xcolab_ContestTeamMember` (`id_`, `contestId`, `userId`, `role`) VALUES ('270', '1300403', '1475398', 'Judge');

INSERT INTO `xcolab_ContestTeamMember` (`id_`, `contestId`, `userId`, `role`) VALUES ('271', '1300204', '1480794', 'Judge');
INSERT INTO `xcolab_ContestTeamMember` (`id_`, `contestId`, `userId`, `role`) VALUES ('272', '1300204', '1355120', 'Judge');

REPLACE Users_Roles (userId, roleId) VALUES (1489793, 1251483);

REPLACE Users_Roles (userId, roleId) VALUES (1475378, 1251483);
REPLACE Users_Roles (userId, roleId) VALUES (1475398, 1251483);


REPLACE Users_Roles (userId, roleId) VALUES (1480794, 1251483);

REPLACE Users_Roles (userId, roleId) VALUES (1355120, 1251483);

INSERT INTO `xcolab_ContestTeamMember` (`id_`, `contestId`, `userId`, `role`) VALUES ('272', '1300206', '1469350', 'Advisor');

REPLACE Users_Roles (userId, roleId) VALUES (1469350, 193260);