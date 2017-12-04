-- COLAB-2441
ALTER TABLE xcolab_TrackedVisitor2User MODIFY uuid_ VARCHAR(36) NOT NULL;
ALTER TABLE xcolab_TrackedVisitor2User DROP id_;
ALTER TABLE xcolab_TrackedVisitor2User DROP PRIMARY KEY;
ALTER TABLE xcolab_TrackedVisitor2User ADD PRIMARY KEY (uuid_);
