delete from xcolab_FocusArea where id_ = 0;
delete from xcolab_FocusAreaOntologyTerm where focusAreaId = 0;
update xcolab_Contest set focusAreaId = 2 where focusAreaId = 0;
update xcolab_PlanSectionDefinition set focusAreaId = null where focusAreaId = 0;
