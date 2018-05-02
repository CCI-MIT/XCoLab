-- Remove unused indices
DROP INDEX IX_9F1D3B81 ON xcolab_ContestPhase;
DROP INDEX IX_4F735B66 ON xcolab_ContestPhase;
DROP INDEX IX_ED61C03C ON xcolab_ContestPhase;

ALTER TABLE xcolab_ContestPhase DROP fellowScreeningActive;
ALTER TABLE xcolab_ContestPhase DROP ContestPhaseDescriptionOverride;
ALTER TABLE xcolab_ContestPhase DROP phaseActiveOverride;
ALTER TABLE xcolab_ContestPhase DROP phaseInactiveOverride;
ALTER TABLE xcolab_ContestPhase DROP PhaseBufferEndDated;
ALTER TABLE xcolab_ContestPhase DROP nextStatus;
ALTER TABLE xcolab_ContestPhase DROP authorId;

ALTER TABLE xcolab_ContestPhaseType ADD isDeprecated TINYINT(4) DEFAULT 0 NOT NULL;

UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 3;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 4;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 5;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 6;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 7;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 8;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 9;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 10;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 12;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 13;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 15;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 17;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 19;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 21;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 200;
UPDATE xcolab_ContestPhaseType SET isDeprecated = 1 WHERE id_ = 301;
