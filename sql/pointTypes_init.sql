insert into xcolab_PointType (id_, parentPointTypeId, percentageOfParent, distributionStrategy, receiverLimitationStrategy) VALUES
(1, 0, 1, "DEFINED_BY_CHILDREN_PERCENTAGES", "NONE"),
(2, 1, 0.8, "EQUAL_DIVISION", "SUBPROPOSALS"),
(3, 1, 0.2, "DEFINED_BY_CHILDREN_PERCENTAGES", "ANY_USER"),
(4, 3, 0.9, "USER_DEFINED", "ANY_TEAM_MEMBER"),
(5, 3, 0.1, "USER_DEFINED", "ANY_NON_TEAM_MEMBER");



update xcolab_Contest set defaultParentPointType = 1, points = 1000 where ContestPK = 1300701 ;

