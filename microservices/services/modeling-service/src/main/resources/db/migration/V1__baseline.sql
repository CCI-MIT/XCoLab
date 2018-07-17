CREATE TABLE IF NOT EXISTS `xcolab_ModelOutputItem` (
  `modelOutputItemModifierPK` bigint(20) NOT NULL PRIMARY KEY,
  `modelId` bigint(20) DEFAULT NULL,
  `modelOutputItemId` bigint(20) DEFAULT NULL,
  `modelOutputItemOrder` int(11) DEFAULT NULL,
  `modelItemRangePolicy` varchar(2048) DEFAULT NULL,
  `modelItemRangeMessage` varchar(2048) DEFAULT NULL,
  `modelItemErrorPolicy` varchar(2048) DEFAULT NULL,
  `modelItemErrorMessage` varchar(2048) DEFAULT NULL,
  `modelItemLabelFormat` varchar(2048) DEFAULT NULL,
  `modelItemIsVisible` tinyint(4) DEFAULT NULL,
  `itemType` varchar(256) DEFAULT NULL,
  `relatedOutputItem` bigint(20) DEFAULT NULL,
  KEY `IX_A17AABB` (`modelOutputItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ModelCategory` (
  `modelCategoryPK` bigint(20) NOT NULL PRIMARY KEY,
  `modelCategoryName` varchar(256) DEFAULT NULL,
  `modelCategoryDescription` varchar(2048) DEFAULT NULL,
  `modelCategoryDisplayWeight` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ModelDiscussion` (
  `modelDiscussionId` bigint(20) NOT NULL PRIMARY KEY,
  `modelId` bigint(20) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  KEY `IX_C4F6226E` (`categoryId`),
  KEY `IX_72D6F073` (`modelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ModelInputGroup` (
  `modelInputGroupPK` bigint(20) NOT NULL PRIMARY KEY,
  `modelId` bigint(20) DEFAULT NULL,
  `nameAndDescriptionMetaDataId` bigint(20) DEFAULT NULL,
  `name` varchar(1024) DEFAULT NULL,
  `description` longtext,
  `displayItemOrder` int(11) DEFAULT NULL,
  `groupType` varchar(256) DEFAULT NULL,
  `parentGroupPK` bigint(20) DEFAULT NULL,
  KEY `IX_23506DA6` (`modelId`),
  KEY `IX_CC01932` (`parentGroupPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ModelOutputChartOrder` (
  `modelOutputChartOrderPK` bigint(20) NOT NULL PRIMARY KEY,
  `modelId` bigint(20) DEFAULT NULL,
  `modelOutputLabel` varchar(1024) DEFAULT NULL,
  `modelOutputChartOrder` int(11) DEFAULT NULL,
  `modelIndexRangePolicy` varchar(2048) DEFAULT NULL,
  `modelIndexRangeMessage` varchar(2048) DEFAULT NULL,
  `modelIndexErrorPolicy` varchar(2048) DEFAULT NULL,
  `modelIndexErrorMessage` varchar(2048) DEFAULT NULL,
  `modelChartIsVisible` tinyint(4) DEFAULT NULL,
  KEY `IX_6D3808C8` (`modelId`,`modelOutputLabel`(50))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ModelInputItem` (
  `modelInputItemPK` bigint(20) NOT NULL PRIMARY KEY,
  `modelId` bigint(20) DEFAULT NULL,
  `modelInputItemID` bigint(20) DEFAULT NULL,
  `modelGroupId` bigint(20) DEFAULT NULL,
  `displayItemOrder` int(11) DEFAULT NULL,
  `type_` varchar(256) DEFAULT NULL,
  `properties` varchar(2048) DEFAULT NULL,
  KEY `IX_39326F55` (`modelGroupId`),
  KEY `IX_13790D44` (`modelId`),
  KEY `IX_EF979667` (`modelId`,`modelInputItemID`),
  KEY `IX_CCEFE733` (`modelInputItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ModelGlobalPreference` (
  `modelGlobalPreferencePK` bigint(20) NOT NULL PRIMARY KEY,
  `modelId` bigint(20) DEFAULT NULL,
  `visible` tinyint(4) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `expertEvaluationPageId` bigint(20) DEFAULT NULL,
  `modelCategoryId` bigint(20) DEFAULT NULL,
  `usesCustomInputs` tinyint(4) DEFAULT NULL,
  `customInputsDefinition` longtext,
  KEY `IX_16CBB25B` (`modelCategoryId`),
  KEY `IX_E9B5E03D` (`modelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `xcolab_ModelPosition` (
  `id_` bigint(20) NOT NULL PRIMARY KEY,
  `positionId` bigint(20) DEFAULT NULL,
  `modelId` bigint(20) DEFAULT NULL,
  KEY `IX_E7C0C412` (`modelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
