UPDATE `xcolab_ContestPhase` SET `PhaseEndDate`='2014-08-12 16:30:00' WHERE `ContestPhasePK`='1300649';
UPDATE `xcolab_ContestPhase` SET `PhaseStartDate`='2014-08-12 16:30:01' WHERE `ContestPhasePK`='1300650';

UPDATE `xcolab_ContestPhase` SET `PhaseEndDate`='2014-08-13 16:30:00' WHERE `ContestPhasePK`='1300637';
UPDATE `xcolab_ContestPhase` SET `PhaseStartDate`='2014-08-13 16:30:01' WHERE `ContestPhasePK`='1300638';


UPDATE xcolab_ContestPhase SET phaseEndDate = "2014-08-16 23:59:59" WHERE contestphasetype = 18 AND phaseEndDate = "2014-08-16 20:00:00";
UPDATE xcolab_ContestPhase SET phaseStartDate = "2014-08-17 00:00:00" WHERE contestphasetype = 19 AND phaseStartDate = "2014-08-16 20:00:01";

UPDATE xcolab_ContestPhase SET phaseEndDate = "2014-08-29 23:59:59" WHERE contestphasetype = 18 AND phaseEndDate = "2014-08-29 20:00:00";
UPDATE xcolab_ContestPhase SET phaseStartDate = "2014-08-30 00:00:00" WHERE contestphasetype = 19 AND phaseStartDate = "2014-08-29 20:00:01";