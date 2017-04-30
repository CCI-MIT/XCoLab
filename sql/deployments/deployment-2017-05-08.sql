-- COLAB-1938
ALTER TABLE xcolab_ContestType ADD showResourceLinks TINYINT(4) DEFAULT 1 NULL;

-- COLAB-1937
ALTER TABLE members_Member ADD loginTokenId VARCHAR(75) NULL;
ALTER TABLE members_Member ADD loginTokenKey VARCHAR(75) NULL;
ALTER TABLE members_Member ADD loginTokenExpirationDate DATETIME NULL;
