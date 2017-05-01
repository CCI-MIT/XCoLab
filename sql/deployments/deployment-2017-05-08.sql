-- COLAB-1938
ALTER TABLE xcolab_ContestType ADD showResourceLinks TINYINT(4) DEFAULT 1 NULL;

-- COLAB-1937
ALTER TABLE members_Member ADD loginTokenId VARCHAR(75) NULL;
ALTER TABLE members_Member ADD loginTokenKey VARCHAR(75) NULL;
ALTER TABLE members_Member ADD loginTokenExpirationDate DATETIME NULL;
INSERT INTO xcolab_ContestEmailTemplate (type_, subject, header, footer)
  VALUES ('MEMBER_BATCH_REGISTERED_DEFAULT', 'Welcome to the <colab-name/>', 'Dear <firstname/>,<br/><br/>A new account has been created for you on the <colab-name/>. Please click <login-link/> to log in.<br/><br/>Sincerely,<br/>The <colab-name/> Team<br/><colab-admin-email/><br/><colab-url/>', '');
