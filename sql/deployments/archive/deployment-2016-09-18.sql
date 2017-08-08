-- COLAB-1375
ALTER TABLE xcolab_ContestPhase MODIFY ContestPhasePK BIGINT(20) AUTO_INCREMENT;
ALTER TABLE xcolab_ContestSchedule MODIFY id_ BIGINT(20) AUTO_INCREMENT;

-- delete empty contest schedules
delete from xcolab_ContestSchedule where not exists (select * from xcolab_ContestPhase where contestScheduleId = id_);