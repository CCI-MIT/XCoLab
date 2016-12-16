#Configuration attribute Blog URL
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('BLOG_URL', '0', '0', 'news.climatecolab.com', '0');

#Configuration attribute to use CollectionCards
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('COLAB_USES_CARDS', '0', '1', ' ', '0');

#Create CollectionCcards table
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


insert into xcolab_ContestCollectionCard (big_ontology_term, description, visible, ontology_term_to_load, only_featured)
values (null, 'Featured', 1, null, 1);

insert into xcolab_ContestCollectionCard (big_ontology_term, description, short_name, ontology_term_to_load, small_ontology_term, only_featured)
select id_, name, name, id_, id_, 0 from xcolab_OntologyTerm where ontologySpaceId=103 or ontologySpaceId=104 order by id_ asc;


UPDATE `xcolab_ContestCollectionCard` SET `parent`='2' WHERE `id_`='5';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='5' WHERE `id_`='6';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='3' WHERE `id_`='7';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='2' WHERE `id_`='29';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='2' WHERE `id_`='28';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='5' WHERE `id_`='48';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='5' WHERE `id_`='52';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='5' WHERE `id_`='31';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='5' WHERE `id_`='30';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='6' WHERE `id_`='33';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='6' WHERE `id_`='34';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='33' WHERE `id_`='35';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='33' WHERE `id_`='36';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='33' WHERE `id_`='45';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='36' WHERE `id_`='37';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='36' WHERE `id_`='38';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='36' WHERE `id_`='39';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='39' WHERE `id_`='40';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='39' WHERE `id_`='41';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='39' WHERE `id_`='42';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='39' WHERE `id_`='43';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='39' WHERE `id_`='44';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='34' WHERE `id_`='46';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='34' WHERE `id_`='47';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='28' WHERE `id_`='74';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='28' WHERE `id_`='78';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='28' WHERE `id_`='79';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='28' WHERE `id_`='80';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='29' WHERE `id_`='81';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='29' WHERE `id_`='82';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='29' WHERE `id_`='83';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='81' WHERE `id_`='84';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='81' WHERE `id_`='85';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='81' WHERE `id_`='86';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='82' WHERE `id_`='87';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='82' WHERE `id_`='88';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='82' WHERE `id_`='89';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='3' WHERE `id_`='18';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='18' WHERE `id_`='23';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='18' WHERE `id_`='24';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='18' WHERE `id_`='25';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='18' WHERE `id_`='26';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='18' WHERE `id_`='91';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='18' WHERE `id_`='92';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='92' WHERE `id_`='27';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='3' WHERE `id_`='19';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='3' WHERE `id_`='20';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='3' WHERE `id_`='21';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='21' WHERE `id_`='90';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='3' WHERE `id_`='22';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='5' WHERE `id_`='49';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='49' WHERE `id_`='50';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='49' WHERE `id_`='51';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='52' WHERE `id_`='53';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='30' WHERE `id_`='32';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='32' WHERE `id_`='56';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='56' WHERE `id_`='57';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='57' WHERE `id_`='58';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='57' WHERE `id_`='59';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='57' WHERE `id_`='60';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='57' WHERE `id_`='61';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='57' WHERE `id_`='62';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='57' WHERE `id_`='63';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='57' WHERE `id_`='64';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='57' WHERE `id_`='65';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='65' WHERE `id_`='66';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='65' WHERE `id_`='67';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='65' WHERE `id_`='68';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='65' WHERE `id_`='69';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='65' WHERE `id_`='70';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='65' WHERE `id_`='71';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='65' WHERE `id_`='72';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='56' WHERE `id_`='73';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='31' WHERE `id_`='93';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='31' WHERE `id_`='94';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='4' WHERE `id_`='54';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='4' WHERE `id_`='55';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='31' WHERE `id_`='4';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='74' WHERE `id_`='75';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='74' WHERE `id_`='76';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='74' WHERE `id_`='77';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='80' WHERE `id_`='8';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='80' WHERE `id_`='9';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='80' WHERE `id_`='10';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='79' WHERE `id_`='11';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='79' WHERE `id_`='12';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='79' WHERE `id_`='13';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='78' WHERE `id_`='14';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='78' WHERE `id_`='15';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='78' WHERE `id_`='16';
UPDATE `xcolab_ContestCollectionCard` SET `parent`='31' WHERE `id_`='17';

ALTER TABLE `xcolab_BalloonText` 
CHANGE COLUMN `id_` `id_` BIGINT(20) NOT NULL AUTO_INCREMENT ;

INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('GOOGLE_RECAPTCHA_SITE_KEY', '0', '0', '', '0');
INSERT INTO `xcolab_ConfigurationAttribute` (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('GOOGLE_RECAPTCHA_SITE_SECRET_KEY', '0', '0', '', '0');
