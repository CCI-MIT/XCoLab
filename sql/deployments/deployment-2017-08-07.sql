-- COLAB-2150
ALTER TABLE xcolab_BalloonText DROP textAfterForm;
ALTER TABLE xcolab_BalloonText DROP textAfterShareButtons;
ALTER TABLE xcolab_BalloonText DROP acceptTosText;
ALTER TABLE xcolab_BalloonText DROP twitterDescription;
ALTER TABLE xcolab_BalloonText DROP twitterSubject;
ALTER TABLE xcolab_BalloonText CHANGE facebookDescription shareDescription TEXT;
ALTER TABLE xcolab_BalloonText CHANGE facebookSubject shareTitle VARCHAR(255);
ALTER TABLE xcolab_BalloonText MODIFY emailSubjectTemplate VARCHAR(255);
ALTER TABLE xcolab_BalloonText MODIFY COLUMN shareDescription TEXT AFTER shareTitle;

