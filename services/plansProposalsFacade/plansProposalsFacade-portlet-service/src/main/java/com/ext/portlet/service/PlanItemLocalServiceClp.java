package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class PlanItemLocalServiceClp implements PlanItemLocalService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addPlanItemMethodKey0;
    private MethodKey _createPlanItemMethodKey1;
    private MethodKey _deletePlanItemMethodKey2;
    private MethodKey _deletePlanItemMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchPlanItemMethodKey8;
    private MethodKey _getPlanItemMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getPlanItemsMethodKey11;
    private MethodKey _getPlanItemsCountMethodKey12;
    private MethodKey _updatePlanItemMethodKey13;
    private MethodKey _updatePlanItemMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _createPlanMethodKey17;
    private MethodKey _createPlanMethodKey18;
    private MethodKey _createPlanMethodKey19;
    private MethodKey _getPlansForUserMethodKey20;
    private MethodKey _getPlansMethodKey21;
    private MethodKey _getPlansInContestPhaseMethodKey22;
    private MethodKey _getPlanMethodKey23;
    private MethodKey _getPlansMethodKey24;
    private MethodKey _getPlansMethodKey25;
    private MethodKey _getPlansMethodKey26;
    private MethodKey _getPlansMethodKey27;
    private MethodKey _isNameAvailableMethodKey28;
    private MethodKey _applyFiltersMethodKey29;
    private MethodKey _removePlanWithEntireHistoryMethodKey30;
    private MethodKey _getAllVersionsMethodKey31;
    private MethodKey _getPlanAttributesMethodKey32;
    private MethodKey _getPlanAttributeMethodKey33;
    private MethodKey _reIndexMethodKey34;
    private MethodKey _reIndexMethodKey35;
    private MethodKey _findPlansForFocusAreaMethodKey36;
    private MethodKey _findPlansForOntologyTermsMethodKey37;
    private MethodKey _findPlansForOntologyTermsMethodKey38;
    private MethodKey _countPlansByContestPhaseMethodKey39;
    private MethodKey _getPlansByContestMethodKey40;
    private MethodKey _planDeletedMethodKey41;
    private MethodKey _getDescriptionMethodKey42;
    private MethodKey _getNameMethodKey43;
    private MethodKey _getImageIdMethodKey44;
    private MethodKey _getPitchMethodKey45;
    private MethodKey _getImageMethodKey46;
    private MethodKey _setDescriptionMethodKey47;
    private MethodKey _setNameMethodKey48;
    private MethodKey _setImageMethodKey49;
    private MethodKey _setPitchMethodKey50;
    private MethodKey _getAllDescriptionVersionsMethodKey51;
    private MethodKey _getPlanDescriptionsMethodKey52;
    private MethodKey _getScenarioIdMethodKey53;
    private MethodKey _setScenarioIdMethodKey54;
    private MethodKey _setModelIdMethodKey55;
    private MethodKey _getAllPlanModelRunsMethodKey56;
    private MethodKey _getPlanMetaMethodKey57;
    private MethodKey _getAllPlanMetasMethodKey58;
    private MethodKey _getPlanTypeIdMethodKey59;
    private MethodKey _getPlanTypeMethodKey60;
    private MethodKey _getContestMethodKey61;
    private MethodKey _getContestPhaseMethodKey62;
    private MethodKey _setContestPhaseMethodKey63;
    private MethodKey _setPlanTypeIdMethodKey64;
    private MethodKey _getMBCategoryIdMethodKey65;
    private MethodKey _setMBCategoryIdMethodKey66;
    private MethodKey _getCategoryGroupIdMethodKey67;
    private MethodKey _setCategoryGroupIdMethodKey68;
    private MethodKey _getPlanGroupIdMethodKey69;
    private MethodKey _setPlanGroupIdMethodKey70;
    private MethodKey _getAuthorIdMethodKey71;
    private MethodKey _getAuthorMethodKey72;
    private MethodKey _setAuthorIdMethodKey73;
    private MethodKey _getCreateDateMethodKey74;
    private MethodKey _getPublishDateMethodKey75;
    private MethodKey _getCreatorMethodKey76;
    private MethodKey _getVotesMethodKey77;
    private MethodKey _getOpenMethodKey78;
    private MethodKey _setOpenMethodKey79;
    private MethodKey _setOpenMethodKey80;
    private MethodKey _getStatusMethodKey81;
    private MethodKey _setStatusMethodKey82;
    private MethodKey _getPlanPositionsMethodKey83;
    private MethodKey _getPositionsIdsMethodKey84;
    private MethodKey _getPositionsIdsArrayMethodKey85;
    private MethodKey _setPositionsMethodKey86;
    private MethodKey _getAllPositionsVersionsMethodKey87;
    private MethodKey _hasUserVotedMethodKey88;
    private MethodKey _voteMethodKey89;
    private MethodKey _unvoteMethodKey90;
    private MethodKey _storeMethodKey91;
    private MethodKey _updateAllAttributesMethodKey92;
    private MethodKey _updateAttributeMethodKey93;
    private MethodKey _getMembersMethodKey94;
    private MethodKey _getMembershipRequestsMethodKey95;
    private MethodKey _addMembershipRequestMethodKey96;
    private MethodKey _dennyMembershipRequestMethodKey97;
    private MethodKey _approveMembershipRequestMethodKey98;
    private MethodKey _publishMethodKey99;
    private MethodKey _deleteMethodKey100;
    private MethodKey _getUpdateAuthorMethodKey101;
    private MethodKey _getFansMethodKey102;
    private MethodKey _addFanMethodKey103;
    private MethodKey _removeFanMethodKey104;
    private MethodKey _isUserAFanMethodKey105;
    private MethodKey _isUserAMemberMethodKey106;
    private MethodKey _hasUserRequestedMembershipMethodKey107;
    private MethodKey _isAdminMethodKey108;
    private MethodKey _isOwnerMethodKey109;
    private MethodKey _setUserPermissionMethodKey110;
    private MethodKey _removeMemberMethodKey111;
    private MethodKey _joinIfNotAMemberMethodKey112;
    private MethodKey _setSeekingAssistanceMethodKey113;
    private MethodKey _isSeekingAssistanceMethodKey114;
    private MethodKey _getDiscussionCategoryGroupMethodKey115;
    private MethodKey _promoteMethodKey116;
    private MethodKey _getPromotedMethodKey117;
    private MethodKey _getCommentsCountMethodKey118;
    private MethodKey _setPlaceMethodKey119;
    private MethodKey _removePlaceMethodKey120;
    private MethodKey _getPlanVotesMethodKey121;
    private MethodKey _setRibbonMethodKey122;
    private MethodKey _setRibbonTextMethodKey123;
    private MethodKey _setAttributeMethodKey124;
    private MethodKey _removeAttributeMethodKey125;
    private MethodKey _getPlanTemplateMethodKey126;
    private MethodKey _getPlanSectionsMethodKey127;
    private MethodKey _setSectionContentMethodKey128;
    private MethodKey _getAllPlanSectionsMethodKey129;
    private MethodKey _getRibbonMethodKey130;
    private MethodKey _setTeamMethodKey131;
    private MethodKey _getTeamMethodKey132;
    private MethodKey _revertToMethodKey133;
    private MethodKey _getTagsMethodKey134;
    private MethodKey _setTagsMethodKey135;
    private MethodKey _getTagsHoverMethodKey136;
    private MethodKey _setTagsHoverMethodKey137;
    private MethodKey _getTagsOrderMethodKey138;
    private MethodKey _setTagsOrderMethodKey139;
    private MethodKey _promotePlansMethodKey140;
    private MethodKey _promotePlansMethodKey141;

    public PlanItemLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addPlanItemMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addPlanItem", com.ext.portlet.model.PlanItem.class);

        _createPlanItemMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createPlanItem", long.class);

        _deletePlanItemMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deletePlanItem", long.class);

        _deletePlanItemMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deletePlanItem", com.ext.portlet.model.PlanItem.class);

        _dynamicQueryMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

        _dynamicQueryMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
                int.class, int.class);

        _dynamicQueryMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQuery",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
                int.class, int.class,
                com.liferay.portal.kernel.util.OrderByComparator.class);

        _dynamicQueryCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
                "dynamicQueryCount",
                com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

        _fetchPlanItemMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchPlanItem", long.class);

        _getPlanItemMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanItem", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getPlanItemsMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanItems", int.class, int.class);

        _getPlanItemsCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanItemsCount");

        _updatePlanItemMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updatePlanItem", com.ext.portlet.model.PlanItem.class);

        _updatePlanItemMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updatePlanItem", com.ext.portlet.model.PlanItem.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");

        _setBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
                "setBeanIdentifier", java.lang.String.class);

        _createPlanMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
                "createPlan", com.ext.portlet.model.ContestPhase.class,
                java.lang.Long.class);

        _createPlanMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
                "createPlan", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _createPlanMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
                "createPlan", com.ext.portlet.model.PlanItem.class,
                com.ext.portlet.model.ContestPhase.class, java.lang.Long.class);

        _getPlansForUserMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlansForUser", long.class);

        _getPlansMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans");

        _getPlansInContestPhaseMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlansInContestPhase", long.class);

        _getPlanMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlan", java.lang.Long.class);

        _getPlansMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                long.class, long.class, int.class, int.class,
                java.lang.String.class, java.lang.String.class);

        _getPlansMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                long.class, long.class, int.class, int.class,
                java.lang.String.class, java.lang.String.class, boolean.class);

        _getPlansMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.model.PlanType.class, java.util.List.class,
                int.class, int.class, java.lang.String.class,
                java.lang.String.class);

        _getPlansMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.model.PlanType.class, java.util.List.class,
                int.class, int.class, java.lang.String.class,
                java.lang.String.class, boolean.class);

        _isNameAvailableMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
                "isNameAvailable", java.lang.String.class,
                com.ext.portlet.model.Contest.class);

        _applyFiltersMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
                "applyFilters", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.model.PlanType.class, java.util.List.class);

        _removePlanWithEntireHistoryMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
                "removePlanWithEntireHistory", java.lang.Long.class);

        _getAllVersionsMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllVersions", com.ext.portlet.model.PlanItem.class);

        _getPlanAttributesMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanAttributes", com.ext.portlet.model.PlanItem.class);

        _getPlanAttributeMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanAttribute", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _reIndexMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
                "reIndex");

        _reIndexMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
                "reIndex", long.class);

        _findPlansForFocusAreaMethodKey36 = new MethodKey(_classLoaderProxy.getClassName(),
                "findPlansForFocusArea", com.ext.portlet.model.FocusArea.class);

        _findPlansForOntologyTermsMethodKey37 = new MethodKey(_classLoaderProxy.getClassName(),
                "findPlansForOntologyTerms",
                com.ext.portlet.model.OntologyTerm.class);

        _findPlansForOntologyTermsMethodKey38 = new MethodKey(_classLoaderProxy.getClassName(),
                "findPlansForOntologyTerms", java.util.List.class);

        _countPlansByContestPhaseMethodKey39 = new MethodKey(_classLoaderProxy.getClassName(),
                "countPlansByContestPhase",
                com.ext.portlet.model.ContestPhase.class);

        _getPlansByContestMethodKey40 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlansByContest", java.lang.Long.class);

        _planDeletedMethodKey41 = new MethodKey(_classLoaderProxy.getClassName(),
                "planDeleted", com.ext.portlet.model.PlanItem.class);

        _getDescriptionMethodKey42 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDescription", com.ext.portlet.model.PlanItem.class);

        _getNameMethodKey43 = new MethodKey(_classLoaderProxy.getClassName(),
                "getName", com.ext.portlet.model.PlanItem.class);

        _getImageIdMethodKey44 = new MethodKey(_classLoaderProxy.getClassName(),
                "getImageId", com.ext.portlet.model.PlanItem.class);

        _getPitchMethodKey45 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPitch", com.ext.portlet.model.PlanItem.class);

        _getImageMethodKey46 = new MethodKey(_classLoaderProxy.getClassName(),
                "getImage", com.ext.portlet.model.PlanItem.class);

        _setDescriptionMethodKey47 = new MethodKey(_classLoaderProxy.getClassName(),
                "setDescription", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class, java.lang.Long.class);

        _setNameMethodKey48 = new MethodKey(_classLoaderProxy.getClassName(),
                "setName", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class, java.lang.Long.class);

        _setImageMethodKey49 = new MethodKey(_classLoaderProxy.getClassName(),
                "setImage", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _setPitchMethodKey50 = new MethodKey(_classLoaderProxy.getClassName(),
                "setPitch", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class, java.lang.Long.class);

        _getAllDescriptionVersionsMethodKey51 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllDescriptionVersions",
                com.ext.portlet.model.PlanItem.class);

        _getPlanDescriptionsMethodKey52 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanDescriptions", com.ext.portlet.model.PlanItem.class);

        _getScenarioIdMethodKey53 = new MethodKey(_classLoaderProxy.getClassName(),
                "getScenarioId", com.ext.portlet.model.PlanItem.class);

        _setScenarioIdMethodKey54 = new MethodKey(_classLoaderProxy.getClassName(),
                "setScenarioId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _setModelIdMethodKey55 = new MethodKey(_classLoaderProxy.getClassName(),
                "setModelId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getAllPlanModelRunsMethodKey56 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllPlanModelRuns", com.ext.portlet.model.PlanItem.class);

        _getPlanMetaMethodKey57 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanMeta", com.ext.portlet.model.PlanItem.class);

        _getAllPlanMetasMethodKey58 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllPlanMetas", com.ext.portlet.model.PlanItem.class);

        _getPlanTypeIdMethodKey59 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanTypeId", com.ext.portlet.model.PlanItem.class);

        _getPlanTypeMethodKey60 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanType", com.ext.portlet.model.PlanItem.class);

        _getContestMethodKey61 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContest", com.ext.portlet.model.PlanItem.class);

        _getContestPhaseMethodKey62 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContestPhase", com.ext.portlet.model.PlanItem.class);

        _setContestPhaseMethodKey63 = new MethodKey(_classLoaderProxy.getClassName(),
                "setContestPhase", com.ext.portlet.model.PlanItem.class,
                com.ext.portlet.model.ContestPhase.class, java.lang.Long.class);

        _setPlanTypeIdMethodKey64 = new MethodKey(_classLoaderProxy.getClassName(),
                "setPlanTypeId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getMBCategoryIdMethodKey65 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMBCategoryId", com.ext.portlet.model.PlanItem.class);

        _setMBCategoryIdMethodKey66 = new MethodKey(_classLoaderProxy.getClassName(),
                "setMBCategoryId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getCategoryGroupIdMethodKey67 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCategoryGroupId", com.ext.portlet.model.PlanItem.class);

        _setCategoryGroupIdMethodKey68 = new MethodKey(_classLoaderProxy.getClassName(),
                "setCategoryGroupId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getPlanGroupIdMethodKey69 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanGroupId", com.ext.portlet.model.PlanItem.class);

        _setPlanGroupIdMethodKey70 = new MethodKey(_classLoaderProxy.getClassName(),
                "setPlanGroupId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getAuthorIdMethodKey71 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAuthorId", com.ext.portlet.model.PlanItem.class);

        _getAuthorMethodKey72 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAuthor", com.ext.portlet.model.PlanItem.class);

        _setAuthorIdMethodKey73 = new MethodKey(_classLoaderProxy.getClassName(),
                "setAuthorId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getCreateDateMethodKey74 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCreateDate", com.ext.portlet.model.PlanItem.class);

        _getPublishDateMethodKey75 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPublishDate", com.ext.portlet.model.PlanItem.class);

        _getCreatorMethodKey76 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCreator", com.ext.portlet.model.PlanItem.class);

        _getVotesMethodKey77 = new MethodKey(_classLoaderProxy.getClassName(),
                "getVotes", com.ext.portlet.model.PlanItem.class);

        _getOpenMethodKey78 = new MethodKey(_classLoaderProxy.getClassName(),
                "getOpen", com.ext.portlet.model.PlanItem.class);

        _setOpenMethodKey79 = new MethodKey(_classLoaderProxy.getClassName(),
                "setOpen", com.ext.portlet.model.PlanItem.class, boolean.class,
                java.lang.Long.class);

        _setOpenMethodKey80 = new MethodKey(_classLoaderProxy.getClassName(),
                "setOpen", com.ext.portlet.model.PlanItem.class, boolean.class);

        _getStatusMethodKey81 = new MethodKey(_classLoaderProxy.getClassName(),
                "getStatus", com.ext.portlet.model.PlanItem.class);

        _setStatusMethodKey82 = new MethodKey(_classLoaderProxy.getClassName(),
                "setStatus", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class, java.lang.Long.class);

        _getPlanPositionsMethodKey83 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanPositions", com.ext.portlet.model.PlanItem.class);

        _getPositionsIdsMethodKey84 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPositionsIds", com.ext.portlet.model.PlanItem.class);

        _getPositionsIdsArrayMethodKey85 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPositionsIdsArray", com.ext.portlet.model.PlanItem.class);

        _setPositionsMethodKey86 = new MethodKey(_classLoaderProxy.getClassName(),
                "setPositions", com.ext.portlet.model.PlanItem.class,
                java.util.List.class, java.lang.Long.class);

        _getAllPositionsVersionsMethodKey87 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllPositionsVersions", com.ext.portlet.model.PlanItem.class);

        _hasUserVotedMethodKey88 = new MethodKey(_classLoaderProxy.getClassName(),
                "hasUserVoted", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _voteMethodKey89 = new MethodKey(_classLoaderProxy.getClassName(),
                "vote", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _unvoteMethodKey90 = new MethodKey(_classLoaderProxy.getClassName(),
                "unvote", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _storeMethodKey91 = new MethodKey(_classLoaderProxy.getClassName(),
                "store", com.ext.portlet.model.PlanItem.class);

        _updateAllAttributesMethodKey92 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateAllAttributes", com.ext.portlet.model.PlanItem.class);

        _updateAttributeMethodKey93 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateAttribute", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _getMembersMethodKey94 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMembers", com.ext.portlet.model.PlanItem.class);

        _getMembershipRequestsMethodKey95 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMembershipRequests", com.ext.portlet.model.PlanItem.class);

        _addMembershipRequestMethodKey96 = new MethodKey(_classLoaderProxy.getClassName(),
                "addMembershipRequest", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.String.class);

        _dennyMembershipRequestMethodKey97 = new MethodKey(_classLoaderProxy.getClassName(),
                "dennyMembershipRequest", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class,
                com.liferay.portal.model.MembershipRequest.class,
                java.lang.String.class, java.lang.Long.class);

        _approveMembershipRequestMethodKey98 = new MethodKey(_classLoaderProxy.getClassName(),
                "approveMembershipRequest",
                com.ext.portlet.model.PlanItem.class, java.lang.Long.class,
                com.liferay.portal.model.MembershipRequest.class,
                java.lang.String.class, java.lang.Long.class);

        _publishMethodKey99 = new MethodKey(_classLoaderProxy.getClassName(),
                "publish", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _deleteMethodKey100 = new MethodKey(_classLoaderProxy.getClassName(),
                "delete", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _getUpdateAuthorMethodKey101 = new MethodKey(_classLoaderProxy.getClassName(),
                "getUpdateAuthor", com.ext.portlet.model.PlanItem.class);

        _getFansMethodKey102 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFans", com.ext.portlet.model.PlanItem.class);

        _addFanMethodKey103 = new MethodKey(_classLoaderProxy.getClassName(),
                "addFan", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _removeFanMethodKey104 = new MethodKey(_classLoaderProxy.getClassName(),
                "removeFan", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _isUserAFanMethodKey105 = new MethodKey(_classLoaderProxy.getClassName(),
                "isUserAFan", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _isUserAMemberMethodKey106 = new MethodKey(_classLoaderProxy.getClassName(),
                "isUserAMember", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _hasUserRequestedMembershipMethodKey107 = new MethodKey(_classLoaderProxy.getClassName(),
                "hasUserRequestedMembership",
                com.ext.portlet.model.PlanItem.class, java.lang.Long.class);

        _isAdminMethodKey108 = new MethodKey(_classLoaderProxy.getClassName(),
                "isAdmin", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _isOwnerMethodKey109 = new MethodKey(_classLoaderProxy.getClassName(),
                "isOwner", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _setUserPermissionMethodKey110 = new MethodKey(_classLoaderProxy.getClassName(),
                "setUserPermission", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.String.class,
                java.lang.Long.class);

        _removeMemberMethodKey111 = new MethodKey(_classLoaderProxy.getClassName(),
                "removeMember", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _joinIfNotAMemberMethodKey112 = new MethodKey(_classLoaderProxy.getClassName(),
                "joinIfNotAMember", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _setSeekingAssistanceMethodKey113 = new MethodKey(_classLoaderProxy.getClassName(),
                "setSeekingAssistance", com.ext.portlet.model.PlanItem.class,
                boolean.class);

        _isSeekingAssistanceMethodKey114 = new MethodKey(_classLoaderProxy.getClassName(),
                "isSeekingAssistance", com.ext.portlet.model.PlanItem.class);

        _getDiscussionCategoryGroupMethodKey115 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionCategoryGroup",
                com.ext.portlet.model.PlanItem.class);

        _promoteMethodKey116 = new MethodKey(_classLoaderProxy.getClassName(),
                "promote", com.ext.portlet.model.PlanItem.class,
                com.liferay.portal.model.User.class);

        _getPromotedMethodKey117 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPromoted", com.ext.portlet.model.PlanItem.class);

        _getCommentsCountMethodKey118 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCommentsCount", com.ext.portlet.model.PlanItem.class);

        _setPlaceMethodKey119 = new MethodKey(_classLoaderProxy.getClassName(),
                "setPlace", com.ext.portlet.model.PlanItem.class, int.class);

        _removePlaceMethodKey120 = new MethodKey(_classLoaderProxy.getClassName(),
                "removePlace", com.ext.portlet.model.PlanItem.class);

        _getPlanVotesMethodKey121 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanVotes", com.ext.portlet.model.PlanItem.class);

        _setRibbonMethodKey122 = new MethodKey(_classLoaderProxy.getClassName(),
                "setRibbon", com.ext.portlet.model.PlanItem.class,
                java.lang.Integer.class);

        _setRibbonTextMethodKey123 = new MethodKey(_classLoaderProxy.getClassName(),
                "setRibbonText", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _setAttributeMethodKey124 = new MethodKey(_classLoaderProxy.getClassName(),
                "setAttribute", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class, java.lang.String.class);

        _removeAttributeMethodKey125 = new MethodKey(_classLoaderProxy.getClassName(),
                "removeAttribute", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _getPlanTemplateMethodKey126 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanTemplate", com.ext.portlet.model.PlanItem.class);

        _getPlanSectionsMethodKey127 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanSections", com.ext.portlet.model.PlanItem.class);

        _setSectionContentMethodKey128 = new MethodKey(_classLoaderProxy.getClassName(),
                "setSectionContent", com.ext.portlet.model.PlanItem.class,
                com.ext.portlet.model.PlanSectionDefinition.class,
                java.lang.String.class, java.util.List.class,
                java.lang.Long.class);

        _getAllPlanSectionsMethodKey129 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllPlanSections", com.ext.portlet.model.PlanItem.class,
                com.ext.portlet.model.PlanSectionDefinition.class);

        _getRibbonMethodKey130 = new MethodKey(_classLoaderProxy.getClassName(),
                "getRibbon", com.ext.portlet.model.PlanItem.class);

        _setTeamMethodKey131 = new MethodKey(_classLoaderProxy.getClassName(),
                "setTeam", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _getTeamMethodKey132 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTeam", com.ext.portlet.model.PlanItem.class);

        _revertToMethodKey133 = new MethodKey(_classLoaderProxy.getClassName(),
                "revertTo", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _getTagsMethodKey134 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTags", com.ext.portlet.model.PlanItem.class);

        _setTagsMethodKey135 = new MethodKey(_classLoaderProxy.getClassName(),
                "setTags", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _getTagsHoverMethodKey136 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTagsHover", com.ext.portlet.model.PlanItem.class);

        _setTagsHoverMethodKey137 = new MethodKey(_classLoaderProxy.getClassName(),
                "setTagsHover", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _getTagsOrderMethodKey138 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTagsOrder", com.ext.portlet.model.PlanItem.class);

        _setTagsOrderMethodKey139 = new MethodKey(_classLoaderProxy.getClassName(),
                "setTagsOrder", com.ext.portlet.model.PlanItem.class, int.class);

        _promotePlansMethodKey140 = new MethodKey(_classLoaderProxy.getClassName(),
                "promotePlans", long.class, long.class);

        _promotePlansMethodKey141 = new MethodKey(_classLoaderProxy.getClassName(),
                "promotePlans", java.util.List.class, long.class);
    }

    public com.ext.portlet.model.PlanItem addPlanItem(
        com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addPlanItemMethodKey0,
                ClpSerializer.translateInput(planItem));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanItem createPlanItem(long id) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createPlanItemMethodKey1,
                id);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public void deletePlanItem(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deletePlanItemMethodKey2,
                id);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void deletePlanItem(com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deletePlanItemMethodKey3,
                ClpSerializer.translateInput(planItem));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
                ClpSerializer.translateInput(dynamicQuery));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey5,
                ClpSerializer.translateInput(dynamicQuery), start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey6,
                ClpSerializer.translateInput(dynamicQuery), start, end,
                ClpSerializer.translateInput(orderByComparator));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List) ClpSerializer.translateOutput(returnObj);
    }

    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
                ClpSerializer.translateInput(dynamicQuery));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Long) returnObj).longValue();
    }

    public com.ext.portlet.model.PlanItem fetchPlanItem(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_fetchPlanItemMethodKey8,
                id);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanItem getPlanItem(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanItemMethodKey9,
                id);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPersistedModelMethodKey10,
                ClpSerializer.translateInput(primaryKeyObj));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.model.PersistedModel) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getPlanItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanItemsMethodKey11,
                start, end);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public int getPlanItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanItemsCountMethodKey12);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Integer) returnObj).intValue();
    }

    public com.ext.portlet.model.PlanItem updatePlanItem(
        com.ext.portlet.model.PlanItem planItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updatePlanItemMethodKey13,
                ClpSerializer.translateInput(planItem));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanItem updatePlanItem(
        com.ext.portlet.model.PlanItem planItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_updatePlanItemMethodKey14,
                ClpSerializer.translateInput(planItem), merge);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getBeanIdentifierMethodKey15);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        MethodHandler methodHandler = new MethodHandler(_setBeanIdentifierMethodKey16,
                ClpSerializer.translateInput(beanIdentifier));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.ContestPhase phase, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createPlanMethodKey17,
                ClpSerializer.translateInput(phase),
                ClpSerializer.translateInput(authorId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.PlanItem basePlan, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createPlanMethodKey18,
                ClpSerializer.translateInput(basePlan),
                ClpSerializer.translateInput(authorId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanItem createPlan(
        com.ext.portlet.model.PlanItem basePlan,
        com.ext.portlet.model.ContestPhase contestPhase, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_createPlanMethodKey19,
                ClpSerializer.translateInput(basePlan),
                ClpSerializer.translateInput(contestPhase),
                ClpSerializer.translateInput(authorId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getPlansForUser(
        long userId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansForUserMethodKey20,
                userId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getPlans()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey21);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getPlansInContestPhase(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansInContestPhaseMethodKey22,
                contestPhaseId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanItem getPlan(java.lang.Long planId)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanMethodKey23,
                ClpSerializer.translateInput(planId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchPlanItemException) {
                throw (com.ext.portlet.NoSuchPlanItemException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId,
        long contestPhaseId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey24,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap), planTypeId,
                contestPhaseId, start, end,
                ClpSerializer.translateInput(sortColumn),
                ClpSerializer.translateInput(sortDirection));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId,
        long contestPhaseId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection, boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey25,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap), planTypeId,
                contestPhaseId, start, end,
                ClpSerializer.translateInput(sortColumn),
                ClpSerializer.translateInput(sortDirection), applyFilters);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.ContestPhase> phases, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey26,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap),
                ClpSerializer.translateInput(planType),
                ClpSerializer.translateInput(phases), start, end,
                ClpSerializer.translateInput(sortColumn),
                ClpSerializer.translateInput(sortDirection));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.ContestPhase> phases, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection,
        boolean applyFilters)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey27,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap),
                ClpSerializer.translateInput(planType),
                ClpSerializer.translateInput(phases), start, end,
                ClpSerializer.translateInput(sortColumn),
                ClpSerializer.translateInput(sortDirection), applyFilters);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public boolean isNameAvailable(java.lang.String planName,
        com.ext.portlet.model.Contest c)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_isNameAvailableMethodKey28,
                ClpSerializer.translateInput(planName),
                ClpSerializer.translateInput(c));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Boolean) returnObj).booleanValue();
    }

    public java.util.List<com.ext.portlet.model.PlanItem> applyFilters(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.model.PlanType planType,
        java.util.List<com.ext.portlet.model.PlanItem> plans)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_applyFiltersMethodKey29,
                ClpSerializer.translateInput(sessionMap),
                ClpSerializer.translateInput(requestMap),
                ClpSerializer.translateInput(planType),
                ClpSerializer.translateInput(plans));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public void removePlanWithEntireHistory(java.lang.Long planId) {
        MethodHandler methodHandler = new MethodHandler(_removePlanWithEntireHistoryMethodKey30,
                ClpSerializer.translateInput(planId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getAllVersions(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAllVersionsMethodKey31,
                ClpSerializer.translateInput(plan));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanAttribute> getPlanAttributes(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanAttributesMethodKey32,
                ClpSerializer.translateInput(plan));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanAttribute>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanAttribute getPlanAttribute(
        com.ext.portlet.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanAttributeMethodKey33,
                ClpSerializer.translateInput(plan),
                ClpSerializer.translateInput(name));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanAttribute) ClpSerializer.translateOutput(returnObj);
    }

    public void reIndex()
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_reIndexMethodKey34);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void reIndex(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_reIndexMethodKey35,
                planId);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.util.List<com.ext.portlet.model.PlanItem> findPlansForFocusArea(
        com.ext.portlet.model.FocusArea fa)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findPlansForFocusAreaMethodKey36,
                ClpSerializer.translateInput(fa));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> findPlansForOntologyTerms(
        com.ext.portlet.model.OntologyTerm terms)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findPlansForOntologyTermsMethodKey37,
                ClpSerializer.translateInput(terms));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchPlanItemException) {
                throw (com.ext.portlet.NoSuchPlanItemException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> findPlansForOntologyTerms(
        java.util.List<com.ext.portlet.model.OntologyTerm> terms)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_findPlansForOntologyTermsMethodKey38,
                ClpSerializer.translateInput(terms));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchPlanItemException) {
                throw (com.ext.portlet.NoSuchPlanItemException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public long countPlansByContestPhase(
        com.ext.portlet.model.ContestPhase phase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countPlansByContestPhaseMethodKey39,
                ClpSerializer.translateInput(phase));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Long) returnObj).longValue();
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getPlansByContest(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansByContestMethodKey40,
                ClpSerializer.translateInput(contestId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanItem>) ClpSerializer.translateOutput(returnObj);
    }

    public void planDeleted(com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_planDeletedMethodKey41,
                ClpSerializer.translateInput(plan));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.String getDescription(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDescriptionMethodKey42,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.String getName(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getNameMethodKey43,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.Long getImageId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getImageIdMethodKey44,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Long) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.String getPitch(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPitchMethodKey45,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.model.Image getImage(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getImageMethodKey46,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.model.Image) ClpSerializer.translateOutput(returnObj);
    }

    public void setDescription(com.ext.portlet.model.PlanItem pi,
        java.lang.String description, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setDescriptionMethodKey47,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(description),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setName(com.ext.portlet.model.PlanItem pi,
        java.lang.String name, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setNameMethodKey48,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(name),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setImage(com.ext.portlet.model.PlanItem pi,
        java.lang.Long imageId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setImageMethodKey49,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(imageId),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setPitch(com.ext.portlet.model.PlanItem pi,
        java.lang.String pitch, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException {
        MethodHandler methodHandler = new MethodHandler(_setPitchMethodKey50,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(pitch),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof com.liferay.portal.kernel.search.SearchException) {
                throw (com.liferay.portal.kernel.search.SearchException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.util.List<com.ext.portlet.model.PlanDescription> getAllDescriptionVersions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAllDescriptionVersionsMethodKey51,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanDescription>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanDescription> getPlanDescriptions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanDescriptionsMethodKey52,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanDescription>) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.Long getScenarioId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getScenarioIdMethodKey53,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Long) ClpSerializer.translateOutput(returnObj);
    }

    public void setScenarioId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long scenarioId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setScenarioIdMethodKey54,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(scenarioId),
                ClpSerializer.translateInput(authorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setModelId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long simulationId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setModelIdMethodKey55,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(simulationId),
                ClpSerializer.translateInput(authorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.util.List<com.ext.portlet.model.PlanModelRun> getAllPlanModelRuns(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAllPlanModelRunsMethodKey56,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanModelRun>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanMeta getPlanMeta(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanMetaMethodKey57,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanMeta) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanMeta> getAllPlanMetas(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAllPlanMetasMethodKey58,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanMeta>) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.Long getPlanTypeId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanTypeIdMethodKey59,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Long) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanType getPlanType(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanTypeMethodKey60,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanType) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.Contest getContest(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getContestMethodKey61,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.Contest) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.ContestPhase getContestPhase(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getContestPhaseMethodKey62,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.ContestPhase) ClpSerializer.translateOutput(returnObj);
    }

    public void setContestPhase(com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.ContestPhase phase, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setContestPhaseMethodKey63,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(phase),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setPlanTypeId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long planTypeId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setPlanTypeIdMethodKey64,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(planTypeId),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.Long getMBCategoryId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMBCategoryIdMethodKey65,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Long) ClpSerializer.translateOutput(returnObj);
    }

    public void setMBCategoryId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long mbCategoryId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setMBCategoryIdMethodKey66,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(mbCategoryId),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.Long getCategoryGroupId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCategoryGroupIdMethodKey67,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Long) ClpSerializer.translateOutput(returnObj);
    }

    public void setCategoryGroupId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long categoryGroupId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setCategoryGroupIdMethodKey68,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(categoryGroupId),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.Long getPlanGroupId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanGroupIdMethodKey69,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Long) ClpSerializer.translateOutput(returnObj);
    }

    public void setPlanGroupId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long groupId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setPlanGroupIdMethodKey70,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(groupId),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.Long getAuthorId(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAuthorIdMethodKey71,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Long) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.model.User getAuthor(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAuthorMethodKey72,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.model.User) ClpSerializer.translateOutput(returnObj);
    }

    public void setAuthorId(com.ext.portlet.model.PlanItem pi,
        java.lang.Long authorId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setAuthorIdMethodKey73,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(authorId),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.util.Date getCreateDate(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCreateDateMethodKey74,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.Date) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.Date getPublishDate(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPublishDateMethodKey75,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.Date) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.String getCreator(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCreatorMethodKey76,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.Integer getVotes(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getVotesMethodKey77,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Integer) ClpSerializer.translateOutput(returnObj);
    }

    public boolean getOpen(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getOpenMethodKey78,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Boolean) returnObj).booleanValue();
    }

    public void setOpen(com.ext.portlet.model.PlanItem pi, boolean open,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setOpenMethodKey79,
                ClpSerializer.translateInput(pi), open,
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setOpen(com.ext.portlet.model.PlanItem pi, boolean open)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setOpenMethodKey80,
                ClpSerializer.translateInput(pi), open);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.String getStatus(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getStatusMethodKey81,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public void setStatus(com.ext.portlet.model.PlanItem pi,
        java.lang.String status, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setStatusMethodKey82,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(status),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public com.ext.portlet.model.PlanPositions getPlanPositions(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanPositionsMethodKey83,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchPlanPositionsException) {
                throw (com.ext.portlet.NoSuchPlanPositionsException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanPositions) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<java.lang.Long> getPositionsIds(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPositionsIdsMethodKey84,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchPlanPositionsException) {
                throw (com.ext.portlet.NoSuchPlanPositionsException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<java.lang.Long>) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.Long[] getPositionsIdsArray(
        com.ext.portlet.model.PlanItem pi)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPositionsIdsArrayMethodKey85,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.ext.portlet.NoSuchPlanPositionsException) {
                throw (com.ext.portlet.NoSuchPlanPositionsException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Long[]) ClpSerializer.translateOutput(returnObj);
    }

    public void setPositions(com.ext.portlet.model.PlanItem pi,
        java.util.List<java.lang.Long> positionsIds,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setPositionsMethodKey86,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(positionsIds),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.util.List<com.ext.portlet.model.PlanPositions> getAllPositionsVersions(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAllPositionsVersionsMethodKey87,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanPositions>) ClpSerializer.translateOutput(returnObj);
    }

    public boolean hasUserVoted(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_hasUserVotedMethodKey88,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Boolean) returnObj).booleanValue();
    }

    public void vote(com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_voteMethodKey89,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void unvote(com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_unvoteMethodKey90,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void store(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_storeMethodKey91,
                ClpSerializer.translateInput(pi));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void updateAllAttributes(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateAllAttributesMethodKey92,
                ClpSerializer.translateInput(pi));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void updateAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_updateAttributeMethodKey93,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(attributeName));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.util.List<com.liferay.portal.model.User> getMembers(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMembersMethodKey94,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getMembershipRequestsMethodKey95,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.liferay.portal.model.MembershipRequest>) ClpSerializer.translateOutput(returnObj);
    }

    public void addMembershipRequest(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.String comments)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_addMembershipRequestMethodKey96,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(comments));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void dennyMembershipRequest(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_dennyMembershipRequestMethodKey97,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(request),
                ClpSerializer.translateInput(reply),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void approveMembershipRequest(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_approveMembershipRequestMethodKey98,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(request),
                ClpSerializer.translateInput(reply),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void publish(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_publishMethodKey99,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void delete(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_deleteMethodKey100,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public com.liferay.portal.model.User getUpdateAuthor(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getUpdateAuthorMethodKey101,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.model.User) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanFan> getFans(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getFansMethodKey102,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanFan>) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanFan addFan(
        com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_addFanMethodKey103,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanFan) ClpSerializer.translateOutput(returnObj);
    }

    public void removeFan(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_removeFanMethodKey104,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public boolean isUserAFan(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_isUserAFanMethodKey105,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Boolean) returnObj).booleanValue();
    }

    public boolean isUserAMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_isUserAMemberMethodKey106,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Boolean) returnObj).booleanValue();
    }

    public boolean hasUserRequestedMembership(
        com.ext.portlet.model.PlanItem pi, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_hasUserRequestedMembershipMethodKey107,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Boolean) returnObj).booleanValue();
    }

    public boolean isAdmin(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_isAdminMethodKey108,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Boolean) returnObj).booleanValue();
    }

    public boolean isOwner(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_isOwnerMethodKey109,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Boolean) returnObj).booleanValue();
    }

    public void setUserPermission(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.String userPermission,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setUserPermissionMethodKey110,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(userPermission),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void removeMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_removeMemberMethodKey111,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void joinIfNotAMember(com.ext.portlet.model.PlanItem pi,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_joinIfNotAMemberMethodKey112,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(userId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setSeekingAssistance(com.ext.portlet.model.PlanItem pi,
        boolean seekingAssistance)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setSeekingAssistanceMethodKey113,
                ClpSerializer.translateInput(pi), seekingAssistance);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public boolean isSeekingAssistance(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_isSeekingAssistanceMethodKey114,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Boolean) returnObj).booleanValue();
    }

    public com.ext.portlet.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getDiscussionCategoryGroupMethodKey115,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.DiscussionCategoryGroup) ClpSerializer.translateOutput(returnObj);
    }

    public com.ext.portlet.model.PlanItem promote(
        com.ext.portlet.model.PlanItem pi, com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_promoteMethodKey116,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(user));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanItem) ClpSerializer.translateOutput(returnObj);
    }

    public boolean getPromoted(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPromotedMethodKey117,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Boolean) returnObj).booleanValue();
    }

    public int getCommentsCount(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getCommentsCountMethodKey118,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return ((Integer) returnObj).intValue();
    }

    public void setPlace(com.ext.portlet.model.PlanItem pi, int place)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setPlaceMethodKey119,
                ClpSerializer.translateInput(pi), place);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void removePlace(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_removePlaceMethodKey120,
                ClpSerializer.translateInput(pi));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.util.List<com.ext.portlet.model.PlanVote> getPlanVotes(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanVotesMethodKey121,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanVote>) ClpSerializer.translateOutput(returnObj);
    }

    public void setRibbon(com.ext.portlet.model.PlanItem pi,
        java.lang.Integer ribbon)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setRibbonMethodKey122,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(ribbon));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setRibbonText(com.ext.portlet.model.PlanItem pi,
        java.lang.String ribbonText)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setRibbonTextMethodKey123,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(ribbonText));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void setAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName, java.lang.String value)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setAttributeMethodKey124,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(attributeName),
                ClpSerializer.translateInput(value));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void removeAttribute(com.ext.portlet.model.PlanItem pi,
        java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_removeAttributeMethodKey125,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(attributeName));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public com.ext.portlet.model.PlanTemplate getPlanTemplate(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanTemplateMethodKey126,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.PlanTemplate) ClpSerializer.translateOutput(returnObj);
    }

    public java.util.List<com.ext.portlet.model.PlanSection> getPlanSections(
        com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlanSectionsMethodKey127,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanSection>) ClpSerializer.translateOutput(returnObj);
    }

    public void setSectionContent(com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.PlanSectionDefinition psd,
        java.lang.String content,
        java.util.List<java.lang.Long> referencedPlans,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setSectionContentMethodKey128,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(psd),
                ClpSerializer.translateInput(content),
                ClpSerializer.translateInput(referencedPlans),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.util.List<com.ext.portlet.model.PlanSection> getAllPlanSections(
        com.ext.portlet.model.PlanItem pi,
        com.ext.portlet.model.PlanSectionDefinition psd)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getAllPlanSectionsMethodKey129,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(psd));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.PlanSection>) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.Integer getRibbon(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getRibbonMethodKey130,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Integer) ClpSerializer.translateOutput(returnObj);
    }

    public void setTeam(com.ext.portlet.model.PlanItem pi, java.lang.String team)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setTeamMethodKey131,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(team));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.String getTeam(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getTeamMethodKey132,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public void revertTo(com.ext.portlet.model.PlanItem pi,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_revertToMethodKey133,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(updateAuthorId));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.String getTags(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getTagsMethodKey134,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public void setTags(com.ext.portlet.model.PlanItem pi, java.lang.String tags)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setTagsMethodKey135,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(tags));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.String getTagsHover(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getTagsHoverMethodKey136,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public void setTagsHover(com.ext.portlet.model.PlanItem pi,
        java.lang.String tagsHover)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setTagsHoverMethodKey137,
                ClpSerializer.translateInput(pi),
                ClpSerializer.translateInput(tagsHover));

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.Integer getTagsOrder(com.ext.portlet.model.PlanItem pi)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getTagsOrderMethodKey138,
                ClpSerializer.translateInput(pi));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.Integer) ClpSerializer.translateOutput(returnObj);
    }

    public void setTagsOrder(com.ext.portlet.model.PlanItem pi, int tagsOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_setTagsOrderMethodKey139,
                ClpSerializer.translateInput(pi), tagsOrder);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void promotePlans(long sourcePhasePk, long destPhasePk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_promotePlansMethodKey140,
                sourcePhasePk, destPhasePk);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public void promotePlans(
        java.util.List<com.ext.portlet.model.PlanItem> plansToBeCopied,
        long destPhasePk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        MethodHandler methodHandler = new MethodHandler(_promotePlansMethodKey141,
                ClpSerializer.translateInput(plansToBeCopied), destPhasePk);

        try {
            _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
