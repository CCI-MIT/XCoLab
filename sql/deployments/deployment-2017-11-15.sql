SET @row_number1 = 0;
SET @row_number2 = 0;
UPDATE xcolab_ContestPhase
JOIN (SELECT one.id_ AS id_, one.PhaseStartDate AS PhaseStartDate, one.PhaseEndDate AS oldPhaseEndDate, two.PhaseStartDate AS newPhaseEndDate
      FROM
      (SELECT *, @row_number1 := @row_number1 + 1 AS row_number
      FROM (SELECT DISTINCT
              id_,
              PhaseStartDate,
              PhaseEndDate
            FROM xcolab_ContestSchedule
              JOIN xcolab_ContestPhase ON contestScheduleId = id_
            ORDER BY id_ ASC, PhaseStartDate ASC) AS _) AS one
      LEFT JOIN
      (SELECT *, @row_number2 := @row_number2 + 1 AS row_number
      FROM (SELECT DISTINCT
              id_,
              PhaseStartDate,
              PhaseEndDate
            FROM xcolab_ContestSchedule
              JOIN xcolab_ContestPhase ON contestScheduleId = id_
            ORDER BY id_ ASC, PhaseStartDate ASC) AS _) AS two
      ON one.row_number = two.row_number - 1 AND one.id_ = two.id_) AS r
  ON (xcolab_ContestPhase.contestScheduleId = r.id_
      AND xcolab_ContestPhase.PhaseStartDate = r.PhaseStartDate
      AND xcolab_ContestPhase.PhaseEndDate = r.oldPhaseEndDate)
SET xcolab_ContestPhase.PhaseEndDate = r.newPhaseEndDate
WHERE xcolab_ContestPhase.PhaseEndDate IS NOT NULL;