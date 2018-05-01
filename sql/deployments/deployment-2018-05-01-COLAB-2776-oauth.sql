ALTER TABLE members_Member ADD colabSsoId VARCHAR(50) NULL;
ALTER TABLE members_Member ADD climateXId VARCHAR(75) NULL;

CREATE INDEX members_Member_colabSsoId_index ON members_Member (colabSsoId);
CREATE INDEX members_Member_climateXId_index ON members_Member (climateXId);

-- Replace outdated google index
CREATE INDEX members_Member_googleId_index ON members_Member (googleId);
DROP INDEX IX_XCOLAB_MEMBERS_OPEN_ID ON members_Member;
