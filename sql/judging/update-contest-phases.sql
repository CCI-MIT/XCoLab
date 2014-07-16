-- Youth Contest
UPDATE `xcolab_ContestPhase` SET `PhaseEndDate`='2014-07-31 23:59:59' WHERE `ContestPhasePK`='1301401';
UPDATE `xcolab_ContestPhase` SET `PhaseStartDate`='2014-08-01 0:00:00', `PhaseEndDate`='2014-08-14 20:00:00' WHERE `ContestPhasePK`='1301402';
UPDATE `xcolab_ContestPhase` SET `PhaseStartDate`='2014-08-14 20:00:00', `PhaseEndDate`='2014-08-29 20:00:00' WHERE `ContestPhasePK`='1301403';
UPDATE `xcolab_ContestPhase` SET `PhaseStartDate`='2014-08-29 20:00:00', `PhaseEndDate`='2014-09-04 20:00:00' WHERE `ContestPhasePK`='1301404';
UPDATE `xcolab_ContestPhase` SET `PhaseStartDate`='2014-09-04 20:00:00' WHERE `ContestPhasePK`='1301405';

-- Coastal Risk
UPDATE `xcolab_ContestPhase` SET `PhaseEndDate`='2014-07-20 23:59:59' WHERE `ContestPhasePK`='1301201';
UPDATE `xcolab_ContestPhase` SET `PhaseStartDate`='2014-07-21 0:00:00' WHERE `ContestPhasePK`='1301301';

-- Global Plan
INSERT INTO `xcolab_ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseType`, `fellowScreeningActive`, `phaseActiveOverride`, `phaseInactiveOverride`, `PhaseStartDate`, `PhaseEndDate`, `authorId`) VALUES ('1301102', '1300701', '11', '0', '0', '0', '2014-08-01 00:00:00', '2014-08-14 20:00:00', '0');
INSERT INTO `xcolab_ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseType`, `fellowScreeningActive`, `phaseActiveOverride`, `phaseInactiveOverride`, `PhaseStartDate`, `PhaseEndDate`, `authorId`) VALUES ('1301103', '1300701', '12', '0', '0', '0', '2014-08-14 20:00:00', '2014-08-29 20:00:00', '0');
INSERT INTO `xcolab_ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseType`, `fellowScreeningActive`, `phaseActiveOverride`, `phaseInactiveOverride`, `PhaseStartDate`, `PhaseEndDate`, `authorId`) VALUES ('1301104', '1300701', '13', '0', '0', '0', '2014-08-29 20:00:00', '2014-09-04 20:00:00', '0');
INSERT INTO `xcolab_ContestPhase` (`ContestPhasePK`, `ContestPK`, `ContestPhaseType`, `fellowScreeningActive`, `phaseActiveOverride`, `phaseInactiveOverride`, `PhaseStartDate`, `authorId`) VALUES ('1301105', '1300701', '14', '0', '0', '0', '2014-09-04 20:00:00', '0');
UPDATE `xcolab_ContestPhase` SET `PhaseEndDate`='2014-07-31 23:59:59' WHERE `ContestPhasePK`='1301101';




UPDATE xcolab_manuel.xcolab_ContestPhase SET PhaseStartDate = "2014-08-20 20:00:01" WHERE PhaseStartDate = "2014-08-20 20:00:00" AND ContestPK > 1300002;

UPDATE xcolab_manuel.xcolab_ContestPhase SET PhaseStartDate = "2014-08-16 20:00:01" WHERE PhaseStartDate = "2014-08-16 20:00:00" AND ContestPK > 1300002;

UPDATE xcolab_manuel.xcolab_ContestPhase SET PhaseStartDate = "2014-08-14 20:00:01" WHERE PhaseStartDate = "2014-08-14 20:00:00" AND ContestPK > 1300002;

UPDATE xcolab_manuel.xcolab_ContestPhase SET PhaseStartDate = "2014-08-01 20:00:01" WHERE PhaseStartDate = "2014-08-01 20:00:00" AND ContestPK > 1300002;

UPDATE xcolab_manuel.xcolab_ContestPhase SET PhaseStartDate = "2014-08-29 20:00:01" WHERE PhaseStartDate = "2014-08-29 20:00:00" AND ContestPK > 1300002;

UPDATE xcolab_manuel.xcolab_ContestPhase SET PhaseStartDate = "2014-09-04 20:00:01" WHERE PhaseStartDate = "2014-09-04 20:00:00" AND ContestPK > 1300002;