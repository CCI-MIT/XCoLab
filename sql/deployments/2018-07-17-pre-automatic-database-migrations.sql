--
-- Drop foreign keys
--

ALTER TABLE xcolab_PlatformTeamMember DROP FOREIGN KEY xcolab_PlatformTeamMember_members_Member_id__fk;
ALTER TABLE xcolab_PlatformTeamMember DROP FOREIGN KEY xcolab_PlatformTeamMember_xcolab_PlatformTeam_id__fk;
ALTER TABLE xcolab_PlatformTeamMember DROP FOREIGN KEY FK_big_ontology_term;
ALTER TABLE xcolab_PlatformTeamMember DROP FOREIGN KEY FK_ontology_term_to_load;
ALTER TABLE xcolab_PlatformTeamMember DROP FOREIGN KEY FK_parent;
ALTER TABLE xcolab_PlatformTeamMember DROP FOREIGN KEY FK_small_ontology_term;

--
-- Fix weird indexes
--

DROP INDEX index2 on xcolab_ColabEmail;
CREATE INDEX `index_xcolab_ColabEmail_emailSubject` on xcolab_ColabEmail (`emailSubject`);
CREATE INDEX `index_xcolab_ColabEmail_emailTo` on xcolab_ColabEmail (`emailTo`);
CREATE INDEX `index_xcolab_ColabEmail_dateSent` on xcolab_ColabEmail (`dateSent`);
