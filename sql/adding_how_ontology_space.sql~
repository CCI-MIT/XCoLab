
ALTER TABLE  `xcolab_OntologySpace` ADD  `order_` INT NOT NULL DEFAULT  '0';

UPDATE Counter set currentId = currentId + 10000 where name LIKE '%Ontology%';
UPDATE Counter set currentId = currentId + 10000 where name LIKE '%FocusArea%';

delete from xcolab_OntologySpace where name LIKE 'How';
insert into xcolab_OntologySpace VALUES (105, 'How', 'Mechanisms by which actions will be put into effect.', 4);

update xcolab_OntologySpace set order_ = 1, name = 'Actions to address climate change.' where id_ = 103;
update xcolab_OntologySpace set order_ = 2, name = 'Geographic scope of actions.' where id_ = 104;
update xcolab_OntologySpace set order_ = 3, name = 'Organizations/individuals who will take action.' where id_ = 102;

delete from xcolab_OntologyTerm where ontologySpaceId = 105;
insert into xcolab_OntologyTerm VALUES 
(1300601, 0, 105, 'Any actions', ''),
(1300602, 1300601, 105, 'Physical actions', ''),
(1300603, 1300601, 105, 'Political actions', ''),
(1300604, 1300601, 105, 'Economic actions', ''),
(1300605, 1300601, 105, 'Cultural actions', '');

delete from xcolab_FocusAreaOntologyTerm where ontologyTermId in (1300601, 1300602, 1300603, 1300604, 1300605);



insert into xcolab_FocusAreaOntologyTerm (focusAreaId, ontologyTermId, order_) VALUES 
(0, 1300601, 5),
(1, 1300601, 5),
(101, 1300601, 5),
(102, 1300601, 5),
(103, 1300601, 5),
(104, 1300601, 5),
(105, 1300601, 5),
(106, 1300601, 5),
(201, 1300601, 5),
(202, 1300601, 5),
(301, 1300601, 5),
(302, 1300601, 5),
(303, 1300601, 5),
(304, 1300601, 5),
(305, 1300601, 5),
(306, 1300601, 5),
(307, 1300601, 5),
(308, 1300601, 5),
(401, 1300601, 5),
(501, 1300603, 5),
(502, 1300603, 5),
(503, 1300601, 5),
(504, 1300601, 5),
(505, 1300601, 5),
(506, 1300601, 5),
(601, 1300601, 5),
(602, 1300601, 5),
(701, 1300605, 5),
(801, 1300601, 5),
(1000401, 1300601, 5),
(1000501, 1300601, 5),
(1000502, 1300601, 5),
(1000601, 1300601, 5),
(1300005, 1300601, 5),
(1300101, 1300601, 5),
(1300102, 1300601, 5),
(1300103, 1300601, 5),
(1300201, 1300603, 5),
(1300202, 1300601, 5),
(1300203, 1300603, 5),
(1300301, 1300601, 5);

delete from xcolab_FocusAreaOntologyTerm where focusAreaId not in (SELECT id_ from xcolab_FocusArea);
