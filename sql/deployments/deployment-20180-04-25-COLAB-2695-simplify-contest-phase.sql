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
