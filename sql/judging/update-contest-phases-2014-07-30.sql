-- move semi-final deadline to aug 5
UPDATE xcolab_ContestPhase SET PhaseEndDate = "2014-08-05 20:00:00" WHERE ContestPhaseType = 16 AND PhaseStartDate > "2014-01-01" AND ContestPK NOT IN ('1300701', '1300901');
UPDATE xcolab_ContestPhase SET PhaseStartDate = "2014-08-05 20:00:01" WHERE ContestPhaseType = 12 AND PhaseStartDate > "2014-07-01" AND ContestPK NOT IN ('1300701', '1300901');

-- insert new phase "finalist selection"

INSERT INTO `xcolab_ContestPhaseType` (`id_`, `name`, `description`, `status`, `invisible`) VALUES ('18', 'Finalist selection', 'Expert judges select finalists<br/><br/>', 'CLOSED', '0');

-- winners selection phase
UPDATE xcolab_ContestPhase SET PhaseStartDate = "2014-08-25 20:00:01", PhaseEndDate = "2014-09-24 20:00:00" WHERE ContestPhaseType = 15 AND PhaseStartDate > "2014-07-01" AND ContestPK NOT IN ('1300701', '1300901');
UPDATE xcolab_ContestPhase SET PhaseStartDate = "2014-09-04 20:00:01", PhaseEndDate = "2014-09-24 20:00:00" WHERE ContestPhaseType = 15 AND PhaseStartDate > "2014-07-01" AND ContestPK IN ('1300701', '1300901');
-- winners awarded phase
UPDATE xcolab_ContestPhase SET PhaseStartDate = "2014-09-24 20:00:01", PhaseEndDate = NULL WHERE ContestPhaseType = 17 AND PhaseStartDate > "2014-07-01";



INSERT INTO xcolab_ContestPhase VALUES (
'1301501', '1300801', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301502', '1300501', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301503', '1300404', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301504', '1300403', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301505', '1300402', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301506', '1300401', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301507', '1300210', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301508', '1300209', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301509', '1300208', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301510', '1300207', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301511', '1300206', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301512', '1300205', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301513', '1300204', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301514', '1300203', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301515', '1300202', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'
),(
'1301516', '1300201', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-16 20:00:01', '2014-08-25 20:00:00', '', NULL, NULL, '0'

),(
'1301517', '1300701', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-29 20:00:01', '2014-09-04 20:00:00', '', NULL, NULL, '0'
),(
'1301518', '1300901', '18', '1', 'PROMOTE_JUDGED', '', '0', '0', '2014-08-29 20:00:01', '2014-09-04 20:00:00', '', NULL, NULL, '0'
);