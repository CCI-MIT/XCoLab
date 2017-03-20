
ALTER TABLE members_Member
  ADD COLUMN isEmailConfirmed TINYINT(4) NOT NULL DEFAULT 0 AFTER emailAddress;


-- COLAB-1808 embedded microservices

-- Execute on Climate CoLab
-- INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('PARTNER_COLAB_NAMESPACE', 0, null, 'solve-colab', null);

-- Execute on Solve CoLab
-- INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('PARTNER_COLAB_NAMESPACE', 0, null, 'climate-colab', null);
-- INSERT INTO xcolab_ConfigurationAttribute (name, additionalId, numericValue, stringValue, realValue) VALUES ('SHARED_COLAB_NAMESPACE', 0, null, 'climate-colab', null);