-- COLAB-1886
ALTER TABLE xcolab_ContestType ADD overrideCreationPrompt VARCHAR(75) NULL;
-- COLAB-1887
ALTER TABLE xcolab_ContestType ADD pitchName VARCHAR(75) DEFAULT 'Pitch' NULL;
-- COLAB-1888
ALTER TABLE xcolab_ContestType ADD showTeamField TINYINT(4) DEFAULT 1 NULL;
