-- COLAB-1433
ALTER TABLE xcolab_ContestType ADD COLUMN showProposalSummary TINYINT(4) DEFAULT 1;
UPDATE xcolab_ContestType set showProposalSummary = 1;
ALTER TABLE `xcolab_OntologyTerm`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

-- 2016-12-12
INSERT INTO xcolab_ConfigurationAttribute
(`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES
 ('GOOGLE_RECAPTCHA_SITE_KEY', '0', '0', '', '0');

INSERT INTO xcolab_ConfigurationAttribute
(`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES
 ('GOOGLE_RECAPTCHA_SITE_SECRET_KEY', '0', '0', '', '0');

-- 2016-12-18

#Configuration attribute Blog URL
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('BLOG_URL', '0', '0', 'http://resiliencedialogues.org/', '0');

#Configuration attribute to use CollectionCards
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('COLAB_USES_CARDS', '0', '0', ' ', '0');

#Create CollectionCards table
CREATE TABLE IF NOT EXISTS `xcolab_ContestCollectionCard` (
  `id_` BIGINT NOT NULL AUTO_INCREMENT,
  `parent` BIGINT NULL DEFAULT NULL,
  `big_ontology_term` BIGINT NULL DEFAULT NULL,
  `small_ontology_term` BIGINT NULL DEFAULT NULL,
  `description` LONGTEXT COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `short_name` LONGTEXT COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `visible` TINYINT NULL DEFAULT 1,
  `order` INT NULL DEFAULT 0,
  `ontology_term_to_load` BIGINT NULL DEFAULT NULL,
  `only_featured` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id_`),
  INDEX `big_ontology_term_idx` (`big_ontology_term` ASC),
  INDEX `small_ontology_term_idx` (`small_ontology_term` ASC),
  INDEX `ontology_term_to_load_idx` (`ontology_term_to_load` ASC),
  CONSTRAINT `FK_big_ontology_term`
    FOREIGN KEY (`big_ontology_term`)
    REFERENCES `xcolab_OntologyTerm` (`id_`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_small_ontology_term`
    FOREIGN KEY (`small_ontology_term`)
    REFERENCES `xcolab_OntologyTerm` (`id_`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ontology_term_to_load`
    FOREIGN KEY (`ontology_term_to_load`)
    REFERENCES `xcolab_OntologyTerm` (`id_`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_parent`
    FOREIGN KEY (`parent`)
    REFERENCES `xcolab_ContestCollectionCard` (`id_`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `xcolab_BalloonText`
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

-- 2016-12-30

-- COLAB-1459
INSERT INTO xcolab_MemberCategory (roleId, displayName, categoryName, sortOrder, showInList, imageName, description) VALUES (10118, 'Admin', 'Admin', 1, 0, 'icon_mem-staff', 'Site admins - non-visible role');

-- COLAB-1362
update xcolab_ProposalMoveHistory set sourcePhaseId = null where sourcePhaseId = 0 or sourcePhaseId = 20;

-- COLAB-1485
ALTER TABLE members_Member
  ADD COLUMN `uuid` VARCHAR(40) NULL AFTER `autoRegisteredMemberStatus`;
