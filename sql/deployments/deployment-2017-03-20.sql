ALTER TABLE members_Member
  ADD COLUMN isEmailConfirmed TINYINT(4) NOT NULL DEFAULT 0 AFTER emailAddress;