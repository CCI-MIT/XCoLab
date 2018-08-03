ALTER TABLE xcolab_Contest MODIFY ContestPK bigint(20) NOT NULL auto_increment;
DROP TABLE IF EXISTS sharedcolab_SharedContest;
ALTER TABLE xcolab_Contest DROP isSharedContest;
ALTER TABLE xcolab_Contest DROP sharedOrigin;
