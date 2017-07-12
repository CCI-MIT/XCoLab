delete from xcolab_FocusArea where focusAreaId = 0;
delete from xcolab_FocusAreaOntologyTerm where focusAreaId = 0;
update xcolab_Contest set focusAreaId = 2 where focusAreaId = 0;
