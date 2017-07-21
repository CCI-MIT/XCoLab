delete from xcolab_FocusArea where id_ = 0;
delete from xcolab_FocusAreaOntologyTerm where focusAreaId = 0;
update xcolab_Contest set focusAreaId = 2 where focusAreaId = 0;
update xcolab_PlanSectionDefinition set focusAreaId = null where focusAreaId = 0;

-- COLAB-2097
ALTER TABLE xcolab_Contest DROP groupId;
CREATE TABLE `xcolab_ContestTranslation` (
	contestId bigint(20) not null,
	lang varchar(5) not null,
	contestName varchar(255) null,
	contestShortName varchar(128) null,
	contestDescription longtext null,
	createDate timestamp not null,
	modifiedDate timestamp not null,
	authorId bigint(20) null,
	primary key (contestId, lang)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
