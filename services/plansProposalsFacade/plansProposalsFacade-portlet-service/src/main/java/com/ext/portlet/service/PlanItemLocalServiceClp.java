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
    private MethodKey _getPlansMethodKey20;
    private MethodKey _getPlansInContestPhaseMethodKey21;
    private MethodKey _getPlanMethodKey22;
    private MethodKey _getPlansMethodKey23;
    private MethodKey _getPlansMethodKey24;
    private MethodKey _getPlansMethodKey25;
    private MethodKey _getPlansMethodKey26;
    private MethodKey _isNameAvailableMethodKey27;
    private MethodKey _applyFiltersMethodKey28;
    private MethodKey _removePlanWithEntireHistoryMethodKey29;
    private MethodKey _getAllVersionsMethodKey30;
    private MethodKey _getPlanAttributesMethodKey31;
    private MethodKey _getPlanAttributeMethodKey32;
    private MethodKey _reIndexMethodKey33;
    private MethodKey _reIndexMethodKey34;
    private MethodKey _findPlansForFocusAreaMethodKey35;
    private MethodKey _findPlansForOntologyTermsMethodKey36;
    private MethodKey _findPlansForOntologyTermsMethodKey37;
    private MethodKey _countPlansByContestPhaseMethodKey38;
    private MethodKey _getPlansByContestMethodKey39;
    private MethodKey _planDeletedMethodKey40;
    private MethodKey _getDescriptionMethodKey41;
    private MethodKey _getNameMethodKey42;
    private MethodKey _getImageIdMethodKey43;
    private MethodKey _getPitchMethodKey44;
    private MethodKey _getImageMethodKey45;
    private MethodKey _setDescriptionMethodKey46;
    private MethodKey _setNameMethodKey47;
    private MethodKey _setImageMethodKey48;
    private MethodKey _setPitchMethodKey49;
    private MethodKey _getAllDescriptionVersionsMethodKey50;
    private MethodKey _getPlanDescriptionsMethodKey51;
    private MethodKey _getScenarioIdMethodKey52;
    private MethodKey _setScenarioIdMethodKey53;
    private MethodKey _setModelIdMethodKey54;
    private MethodKey _getAllPlanModelRunsMethodKey55;
    private MethodKey _getPlanMetaMethodKey56;
    private MethodKey _getAllPlanMetasMethodKey57;
    private MethodKey _getPlanTypeIdMethodKey58;
    private MethodKey _getPlanTypeMethodKey59;
    private MethodKey _getContestMethodKey60;
    private MethodKey _getContestPhaseMethodKey61;
    private MethodKey _setContestPhaseMethodKey62;
    private MethodKey _setPlanTypeIdMethodKey63;
    private MethodKey _getMBCategoryIdMethodKey64;
    private MethodKey _setMBCategoryIdMethodKey65;
    private MethodKey _getCategoryGroupIdMethodKey66;
    private MethodKey _setCategoryGroupIdMethodKey67;
    private MethodKey _getPlanGroupIdMethodKey68;
    private MethodKey _setPlanGroupIdMethodKey69;
    private MethodKey _getAuthorIdMethodKey70;
    private MethodKey _getAuthorMethodKey71;
    private MethodKey _setAuthorIdMethodKey72;
    private MethodKey _getCreateDateMethodKey73;
    private MethodKey _getPublishDateMethodKey74;
    private MethodKey _getCreatorMethodKey75;
    private MethodKey _getVotesMethodKey76;
    private MethodKey _getOpenMethodKey77;
    private MethodKey _setOpenMethodKey78;
    private MethodKey _setOpenMethodKey79;
    private MethodKey _getStatusMethodKey80;
    private MethodKey _setStatusMethodKey81;
    private MethodKey _getPlanPositionsMethodKey82;
    private MethodKey _getPositionsIdsMethodKey83;
    private MethodKey _getPositionsIdsArrayMethodKey84;
    private MethodKey _setPositionsMethodKey85;
    private MethodKey _getAllPositionsVersionsMethodKey86;
    private MethodKey _hasUserVotedMethodKey87;
    private MethodKey _voteMethodKey88;
    private MethodKey _unvoteMethodKey89;
    private MethodKey _storeMethodKey90;
    private MethodKey _updateAllAttributesMethodKey91;
    private MethodKey _updateAttributeMethodKey92;
    private MethodKey _getMembersMethodKey93;
    private MethodKey _getMembershipRequestsMethodKey94;
    private MethodKey _addMembershipRequestMethodKey95;
    private MethodKey _dennyMembershipRequestMethodKey96;
    private MethodKey _approveMembershipRequestMethodKey97;
    private MethodKey _publishMethodKey98;
    private MethodKey _deleteMethodKey99;
    private MethodKey _getUpdateAuthorMethodKey100;
    private MethodKey _getFansMethodKey101;
    private MethodKey _addFanMethodKey102;
    private MethodKey _removeFanMethodKey103;
    private MethodKey _isUserAFanMethodKey104;
    private MethodKey _isUserAMemberMethodKey105;
    private MethodKey _hasUserRequestedMembershipMethodKey106;
    private MethodKey _isAdminMethodKey107;
    private MethodKey _isOwnerMethodKey108;
    private MethodKey _setUserPermissionMethodKey109;
    private MethodKey _removeMemberMethodKey110;
    private MethodKey _joinIfNotAMemberMethodKey111;
    private MethodKey _setSeekingAssistanceMethodKey112;
    private MethodKey _isSeekingAssistanceMethodKey113;
    private MethodKey _getDiscussionCategoryGroupMethodKey114;
    private MethodKey _promoteMethodKey115;
    private MethodKey _getPromotedMethodKey116;
    private MethodKey _getCommentsCountMethodKey117;
    private MethodKey _setPlaceMethodKey118;
    private MethodKey _removePlaceMethodKey119;
    private MethodKey _getPlanVotesMethodKey120;
    private MethodKey _setRibbonMethodKey121;
    private MethodKey _setRibbonTextMethodKey122;
    private MethodKey _setAttributeMethodKey123;
    private MethodKey _removeAttributeMethodKey124;
    private MethodKey _getPlanTemplateMethodKey125;
    private MethodKey _getPlanSectionsMethodKey126;
    private MethodKey _setSectionContentMethodKey127;
    private MethodKey _getAllPlanSectionsMethodKey128;
    private MethodKey _getRibbonMethodKey129;
    private MethodKey _setTeamMethodKey130;
    private MethodKey _getTeamMethodKey131;
    private MethodKey _revertToMethodKey132;
    private MethodKey _getTagsMethodKey133;
    private MethodKey _setTagsMethodKey134;
    private MethodKey _getTagsHoverMethodKey135;
    private MethodKey _setTagsHoverMethodKey136;
    private MethodKey _getTagsOrderMethodKey137;
    private MethodKey _setTagsOrderMethodKey138;
    private MethodKey _promotePlansMethodKey139;
    private MethodKey _promotePlansMethodKey140;

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

        _getPlansMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans");

        _getPlansInContestPhaseMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlansInContestPhase", long.class);

        _getPlanMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlan", java.lang.Long.class);

        _getPlansMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                long.class, long.class, int.class, int.class,
                java.lang.String.class, java.lang.String.class);

        _getPlansMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                long.class, long.class, int.class, int.class,
                java.lang.String.class, java.lang.String.class, boolean.class);

        _getPlansMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.model.PlanType.class, java.util.List.class,
                int.class, int.class, java.lang.String.class,
                java.lang.String.class);

        _getPlansMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlans", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.model.PlanType.class, java.util.List.class,
                int.class, int.class, java.lang.String.class,
                java.lang.String.class, boolean.class);

        _isNameAvailableMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "isNameAvailable", java.lang.String.class,
                com.ext.portlet.model.Contest.class);

        _applyFiltersMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
                "applyFilters", java.util.Map.class, java.util.Map.class,
                com.ext.portlet.model.PlanType.class, java.util.List.class);

        _removePlanWithEntireHistoryMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
                "removePlanWithEntireHistory", java.lang.Long.class);

        _getAllVersionsMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllVersions", com.ext.portlet.model.PlanItem.class);

        _getPlanAttributesMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanAttributes", com.ext.portlet.model.PlanItem.class);

        _getPlanAttributeMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanAttribute", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _reIndexMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
                "reIndex");

        _reIndexMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
                "reIndex", long.class);

        _findPlansForFocusAreaMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
                "findPlansForFocusArea", com.ext.portlet.model.FocusArea.class);

        _findPlansForOntologyTermsMethodKey36 = new MethodKey(_classLoaderProxy.getClassName(),
                "findPlansForOntologyTerms",
                com.ext.portlet.model.OntologyTerm.class);

        _findPlansForOntologyTermsMethodKey37 = new MethodKey(_classLoaderProxy.getClassName(),
                "findPlansForOntologyTerms", java.util.List.class);

        _countPlansByContestPhaseMethodKey38 = new MethodKey(_classLoaderProxy.getClassName(),
                "countPlansByContestPhase",
                com.ext.portlet.model.ContestPhase.class);

        _getPlansByContestMethodKey39 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlansByContest", java.lang.Long.class);

        _planDeletedMethodKey40 = new MethodKey(_classLoaderProxy.getClassName(),
                "planDeleted", com.ext.portlet.model.PlanItem.class);

        _getDescriptionMethodKey41 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDescription", com.ext.portlet.model.PlanItem.class);

        _getNameMethodKey42 = new MethodKey(_classLoaderProxy.getClassName(),
                "getName", com.ext.portlet.model.PlanItem.class);

        _getImageIdMethodKey43 = new MethodKey(_classLoaderProxy.getClassName(),
                "getImageId", com.ext.portlet.model.PlanItem.class);

        _getPitchMethodKey44 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPitch", com.ext.portlet.model.PlanItem.class);

        _getImageMethodKey45 = new MethodKey(_classLoaderProxy.getClassName(),
                "getImage", com.ext.portlet.model.PlanItem.class);

        _setDescriptionMethodKey46 = new MethodKey(_classLoaderProxy.getClassName(),
                "setDescription", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class, java.lang.Long.class);

        _setNameMethodKey47 = new MethodKey(_classLoaderProxy.getClassName(),
                "setName", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class, java.lang.Long.class);

        _setImageMethodKey48 = new MethodKey(_classLoaderProxy.getClassName(),
                "setImage", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _setPitchMethodKey49 = new MethodKey(_classLoaderProxy.getClassName(),
                "setPitch", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class, java.lang.Long.class);

        _getAllDescriptionVersionsMethodKey50 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllDescriptionVersions",
                com.ext.portlet.model.PlanItem.class);

        _getPlanDescriptionsMethodKey51 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanDescriptions", com.ext.portlet.model.PlanItem.class);

        _getScenarioIdMethodKey52 = new MethodKey(_classLoaderProxy.getClassName(),
                "getScenarioId", com.ext.portlet.model.PlanItem.class);

        _setScenarioIdMethodKey53 = new MethodKey(_classLoaderProxy.getClassName(),
                "setScenarioId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _setModelIdMethodKey54 = new MethodKey(_classLoaderProxy.getClassName(),
                "setModelId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getAllPlanModelRunsMethodKey55 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllPlanModelRuns", com.ext.portlet.model.PlanItem.class);

        _getPlanMetaMethodKey56 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanMeta", com.ext.portlet.model.PlanItem.class);

        _getAllPlanMetasMethodKey57 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllPlanMetas", com.ext.portlet.model.PlanItem.class);

        _getPlanTypeIdMethodKey58 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanTypeId", com.ext.portlet.model.PlanItem.class);

        _getPlanTypeMethodKey59 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanType", com.ext.portlet.model.PlanItem.class);

        _getContestMethodKey60 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContest", com.ext.portlet.model.PlanItem.class);

        _getContestPhaseMethodKey61 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContestPhase", com.ext.portlet.model.PlanItem.class);

        _setContestPhaseMethodKey62 = new MethodKey(_classLoaderProxy.getClassName(),
                "setContestPhase", com.ext.portlet.model.PlanItem.class,
                com.ext.portlet.model.ContestPhase.class, java.lang.Long.class);

        _setPlanTypeIdMethodKey63 = new MethodKey(_classLoaderProxy.getClassName(),
                "setPlanTypeId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getMBCategoryIdMethodKey64 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMBCategoryId", com.ext.portlet.model.PlanItem.class);

        _setMBCategoryIdMethodKey65 = new MethodKey(_classLoaderProxy.getClassName(),
                "setMBCategoryId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getCategoryGroupIdMethodKey66 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCategoryGroupId", com.ext.portlet.model.PlanItem.class);

        _setCategoryGroupIdMethodKey67 = new MethodKey(_classLoaderProxy.getClassName(),
                "setCategoryGroupId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getPlanGroupIdMethodKey68 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanGroupId", com.ext.portlet.model.PlanItem.class);

        _setPlanGroupIdMethodKey69 = new MethodKey(_classLoaderProxy.getClassName(),
                "setPlanGroupId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getAuthorIdMethodKey70 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAuthorId", com.ext.portlet.model.PlanItem.class);

        _getAuthorMethodKey71 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAuthor", com.ext.portlet.model.PlanItem.class);

        _setAuthorIdMethodKey72 = new MethodKey(_classLoaderProxy.getClassName(),
                "setAuthorId", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _getCreateDateMethodKey73 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCreateDate", com.ext.portlet.model.PlanItem.class);

        _getPublishDateMethodKey74 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPublishDate", com.ext.portlet.model.PlanItem.class);

        _getCreatorMethodKey75 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCreator", com.ext.portlet.model.PlanItem.class);

        _getVotesMethodKey76 = new MethodKey(_classLoaderProxy.getClassName(),
                "getVotes", com.ext.portlet.model.PlanItem.class);

        _getOpenMethodKey77 = new MethodKey(_classLoaderProxy.getClassName(),
                "getOpen", com.ext.portlet.model.PlanItem.class);

        _setOpenMethodKey78 = new MethodKey(_classLoaderProxy.getClassName(),
                "setOpen", com.ext.portlet.model.PlanItem.class, boolean.class,
                java.lang.Long.class);

        _setOpenMethodKey79 = new MethodKey(_classLoaderProxy.getClassName(),
                "setOpen", com.ext.portlet.model.PlanItem.class, boolean.class);

        _getStatusMethodKey80 = new MethodKey(_classLoaderProxy.getClassName(),
                "getStatus", com.ext.portlet.model.PlanItem.class);

        _setStatusMethodKey81 = new MethodKey(_classLoaderProxy.getClassName(),
                "setStatus", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class, java.lang.Long.class);

        _getPlanPositionsMethodKey82 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanPositions", com.ext.portlet.model.PlanItem.class);

        _getPositionsIdsMethodKey83 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPositionsIds", com.ext.portlet.model.PlanItem.class);

        _getPositionsIdsArrayMethodKey84 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPositionsIdsArray", com.ext.portlet.model.PlanItem.class);

        _setPositionsMethodKey85 = new MethodKey(_classLoaderProxy.getClassName(),
                "setPositions", com.ext.portlet.model.PlanItem.class,
                java.util.List.class, java.lang.Long.class);

        _getAllPositionsVersionsMethodKey86 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllPositionsVersions", com.ext.portlet.model.PlanItem.class);

        _hasUserVotedMethodKey87 = new MethodKey(_classLoaderProxy.getClassName(),
                "hasUserVoted", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _voteMethodKey88 = new MethodKey(_classLoaderProxy.getClassName(),
                "vote", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _unvoteMethodKey89 = new MethodKey(_classLoaderProxy.getClassName(),
                "unvote", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _storeMethodKey90 = new MethodKey(_classLoaderProxy.getClassName(),
                "store", com.ext.portlet.model.PlanItem.class);

        _updateAllAttributesMethodKey91 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateAllAttributes", com.ext.portlet.model.PlanItem.class);

        _updateAttributeMethodKey92 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateAttribute", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _getMembersMethodKey93 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMembers", com.ext.portlet.model.PlanItem.class);

        _getMembershipRequestsMethodKey94 = new MethodKey(_classLoaderProxy.getClassName(),
                "getMembershipRequests", com.ext.portlet.model.PlanItem.class);

        _addMembershipRequestMethodKey95 = new MethodKey(_classLoaderProxy.getClassName(),
                "addMembershipRequest", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.String.class);

        _dennyMembershipRequestMethodKey96 = new MethodKey(_classLoaderProxy.getClassName(),
                "dennyMembershipRequest", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class,
                com.liferay.portal.model.MembershipRequest.class,
                java.lang.String.class, java.lang.Long.class);

        _approveMembershipRequestMethodKey97 = new MethodKey(_classLoaderProxy.getClassName(),
                "approveMembershipRequest",
                com.ext.portlet.model.PlanItem.class, java.lang.Long.class,
                com.liferay.portal.model.MembershipRequest.class,
                java.lang.String.class, java.lang.Long.class);

        _publishMethodKey98 = new MethodKey(_classLoaderProxy.getClassName(),
                "publish", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _deleteMethodKey99 = new MethodKey(_classLoaderProxy.getClassName(),
                "delete", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _getUpdateAuthorMethodKey100 = new MethodKey(_classLoaderProxy.getClassName(),
                "getUpdateAuthor", com.ext.portlet.model.PlanItem.class);

        _getFansMethodKey101 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFans", com.ext.portlet.model.PlanItem.class);

        _addFanMethodKey102 = new MethodKey(_classLoaderProxy.getClassName(),
                "addFan", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _removeFanMethodKey103 = new MethodKey(_classLoaderProxy.getClassName(),
                "removeFan", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _isUserAFanMethodKey104 = new MethodKey(_classLoaderProxy.getClassName(),
                "isUserAFan", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _isUserAMemberMethodKey105 = new MethodKey(_classLoaderProxy.getClassName(),
                "isUserAMember", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _hasUserRequestedMembershipMethodKey106 = new MethodKey(_classLoaderProxy.getClassName(),
                "hasUserRequestedMembership",
                com.ext.portlet.model.PlanItem.class, java.lang.Long.class);

        _isAdminMethodKey107 = new MethodKey(_classLoaderProxy.getClassName(),
                "isAdmin", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _isOwnerMethodKey108 = new MethodKey(_classLoaderProxy.getClassName(),
                "isOwner", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _setUserPermissionMethodKey109 = new MethodKey(_classLoaderProxy.getClassName(),
                "setUserPermission", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.String.class,
                java.lang.Long.class);

        _removeMemberMethodKey110 = new MethodKey(_classLoaderProxy.getClassName(),
                "removeMember", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class, java.lang.Long.class);

        _joinIfNotAMemberMethodKey111 = new MethodKey(_classLoaderProxy.getClassName(),
                "joinIfNotAMember", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _setSeekingAssistanceMethodKey112 = new MethodKey(_classLoaderProxy.getClassName(),
                "setSeekingAssistance", com.ext.portlet.model.PlanItem.class,
                boolean.class);

        _isSeekingAssistanceMethodKey113 = new MethodKey(_classLoaderProxy.getClassName(),
                "isSeekingAssistance", com.ext.portlet.model.PlanItem.class);

        _getDiscussionCategoryGroupMethodKey114 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionCategoryGroup",
                com.ext.portlet.model.PlanItem.class);

        _promoteMethodKey115 = new MethodKey(_classLoaderProxy.getClassName(),
                "promote", com.ext.portlet.model.PlanItem.class,
                com.liferay.portal.model.User.class);

        _getPromotedMethodKey116 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPromoted", com.ext.portlet.model.PlanItem.class);

        _getCommentsCountMethodKey117 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCommentsCount", com.ext.portlet.model.PlanItem.class);

        _setPlaceMethodKey118 = new MethodKey(_classLoaderProxy.getClassName(),
                "setPlace", com.ext.portlet.model.PlanItem.class, int.class);

        _removePlaceMethodKey119 = new MethodKey(_classLoaderProxy.getClassName(),
                "removePlace", com.ext.portlet.model.PlanItem.class);

        _getPlanVotesMethodKey120 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanVotes", com.ext.portlet.model.PlanItem.class);

        _setRibbonMethodKey121 = new MethodKey(_classLoaderProxy.getClassName(),
                "setRibbon", com.ext.portlet.model.PlanItem.class,
                java.lang.Integer.class);

        _setRibbonTextMethodKey122 = new MethodKey(_classLoaderProxy.getClassName(),
                "setRibbonText", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _setAttributeMethodKey123 = new MethodKey(_classLoaderProxy.getClassName(),
                "setAttribute", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class, java.lang.String.class);

        _removeAttributeMethodKey124 = new MethodKey(_classLoaderProxy.getClassName(),
                "removeAttribute", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _getPlanTemplateMethodKey125 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanTemplate", com.ext.portlet.model.PlanItem.class);

        _getPlanSectionsMethodKey126 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanSections", com.ext.portlet.model.PlanItem.class);

        _setSectionContentMethodKey127 = new MethodKey(_classLoaderProxy.getClassName(),
                "setSectionContent", com.ext.portlet.model.PlanItem.class,
                com.ext.portlet.model.PlanSectionDefinition.class,
                java.lang.String.class, java.util.List.class,
                java.lang.Long.class);

        _getAllPlanSectionsMethodKey128 = new MethodKey(_classLoaderProxy.getClassName(),
                "getAllPlanSections", com.ext.portlet.model.PlanItem.class,
                com.ext.portlet.model.PlanSectionDefinition.class);

        _getRibbonMethodKey129 = new MethodKey(_classLoaderProxy.getClassName(),
                "getRibbon", com.ext.portlet.model.PlanItem.class);

        _setTeamMethodKey130 = new MethodKey(_classLoaderProxy.getClassName(),
                "setTeam", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _getTeamMethodKey131 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTeam", com.ext.portlet.model.PlanItem.class);

        _revertToMethodKey132 = new MethodKey(_classLoaderProxy.getClassName(),
                "revertTo", com.ext.portlet.model.PlanItem.class,
                java.lang.Long.class);

        _getTagsMethodKey133 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTags", com.ext.portlet.model.PlanItem.class);

        _setTagsMethodKey134 = new MethodKey(_classLoaderProxy.getClassName(),
                "setTags", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _getTagsHoverMethodKey135 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTagsHover", com.ext.portlet.model.PlanItem.class);

        _setTagsHoverMethodKey136 = new MethodKey(_classLoaderProxy.getClassName(),
                "setTagsHover", com.ext.portlet.model.PlanItem.class,
                java.lang.String.class);

        _getTagsOrderMethodKey137 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTagsOrder", com.ext.portlet.model.PlanItem.class);

        _setTagsOrderMethodKey138 = new MethodKey(_classLoaderProxy.getClassName(),
                "setTagsOrder", com.ext.portlet.model.PlanItem.class, int.class);

        _promotePlansMethodKey139 = new MethodKey(_classLoaderProxy.getClassName(),
                "promotePlans", long.class, long.class);

        _promotePlansMethodKey140 = new MethodKey(_classLoaderProxy.getClassName(),
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

    public java.util.List<com.ext.portlet.model.PlanItem> getPlans()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey20);

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

        MethodHandler methodHandler = new MethodHandler(_getPlansInContestPhaseMethodKey21,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanMethodKey22,
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

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey23,
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

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey24,
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

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey25,
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

        MethodHandler methodHandler = new MethodHandler(_getPlansMethodKey26,
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

        MethodHandler methodHandler = new MethodHandler(_isNameAvailableMethodKey27,
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

        MethodHandler methodHandler = new MethodHandler(_applyFiltersMethodKey28,
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
        MethodHandler methodHandler = new MethodHandler(_removePlanWithEntireHistoryMethodKey29,
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

        MethodHandler methodHandler = new MethodHandler(_getAllVersionsMethodKey30,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanAttributesMethodKey31,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanAttributeMethodKey32,
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
        MethodHandler methodHandler = new MethodHandler(_reIndexMethodKey33);

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
        MethodHandler methodHandler = new MethodHandler(_reIndexMethodKey34,
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

        MethodHandler methodHandler = new MethodHandler(_findPlansForFocusAreaMethodKey35,
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

        MethodHandler methodHandler = new MethodHandler(_findPlansForOntologyTermsMethodKey36,
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

    public long countPlansByContestPhase(
        com.ext.portlet.model.ContestPhase phase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_countPlansByContestPhaseMethodKey38,
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

        MethodHandler methodHandler = new MethodHandler(_getPlansByContestMethodKey39,
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
        MethodHandler methodHandler = new MethodHandler(_planDeletedMethodKey40,
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

        MethodHandler methodHandler = new MethodHandler(_getDescriptionMethodKey41,
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

        MethodHandler methodHandler = new MethodHandler(_getNameMethodKey42,
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

        MethodHandler methodHandler = new MethodHandler(_getImageIdMethodKey43,
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

        MethodHandler methodHandler = new MethodHandler(_getPitchMethodKey44,
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

        MethodHandler methodHandler = new MethodHandler(_getImageMethodKey45,
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
        MethodHandler methodHandler = new MethodHandler(_setDescriptionMethodKey46,
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
        MethodHandler methodHandler = new MethodHandler(_setNameMethodKey47,
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
        MethodHandler methodHandler = new MethodHandler(_setImageMethodKey48,
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
        MethodHandler methodHandler = new MethodHandler(_setPitchMethodKey49,
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

        MethodHandler methodHandler = new MethodHandler(_getAllDescriptionVersionsMethodKey50,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanDescriptionsMethodKey51,
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

        MethodHandler methodHandler = new MethodHandler(_getScenarioIdMethodKey52,
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
        MethodHandler methodHandler = new MethodHandler(_setScenarioIdMethodKey53,
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
        MethodHandler methodHandler = new MethodHandler(_setModelIdMethodKey54,
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

        MethodHandler methodHandler = new MethodHandler(_getAllPlanModelRunsMethodKey55,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanMetaMethodKey56,
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

        MethodHandler methodHandler = new MethodHandler(_getAllPlanMetasMethodKey57,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanTypeIdMethodKey58,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanTypeMethodKey59,
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

        MethodHandler methodHandler = new MethodHandler(_getContestMethodKey60,
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

        MethodHandler methodHandler = new MethodHandler(_getContestPhaseMethodKey61,
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
        MethodHandler methodHandler = new MethodHandler(_setContestPhaseMethodKey62,
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
        MethodHandler methodHandler = new MethodHandler(_setPlanTypeIdMethodKey63,
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

        MethodHandler methodHandler = new MethodHandler(_getMBCategoryIdMethodKey64,
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
        MethodHandler methodHandler = new MethodHandler(_setMBCategoryIdMethodKey65,
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

        MethodHandler methodHandler = new MethodHandler(_getCategoryGroupIdMethodKey66,
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
        MethodHandler methodHandler = new MethodHandler(_setCategoryGroupIdMethodKey67,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanGroupIdMethodKey68,
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
        MethodHandler methodHandler = new MethodHandler(_setPlanGroupIdMethodKey69,
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

        MethodHandler methodHandler = new MethodHandler(_getAuthorIdMethodKey70,
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

        MethodHandler methodHandler = new MethodHandler(_getAuthorMethodKey71,
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
        MethodHandler methodHandler = new MethodHandler(_setAuthorIdMethodKey72,
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

        MethodHandler methodHandler = new MethodHandler(_getCreateDateMethodKey73,
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

        MethodHandler methodHandler = new MethodHandler(_getPublishDateMethodKey74,
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

        MethodHandler methodHandler = new MethodHandler(_getCreatorMethodKey75,
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

        MethodHandler methodHandler = new MethodHandler(_getVotesMethodKey76,
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

        MethodHandler methodHandler = new MethodHandler(_getOpenMethodKey77,
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
        MethodHandler methodHandler = new MethodHandler(_setOpenMethodKey78,
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
        MethodHandler methodHandler = new MethodHandler(_setOpenMethodKey79,
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

        MethodHandler methodHandler = new MethodHandler(_getStatusMethodKey80,
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
        MethodHandler methodHandler = new MethodHandler(_setStatusMethodKey81,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanPositionsMethodKey82,
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

        MethodHandler methodHandler = new MethodHandler(_getPositionsIdsMethodKey83,
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

        MethodHandler methodHandler = new MethodHandler(_getPositionsIdsArrayMethodKey84,
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
        MethodHandler methodHandler = new MethodHandler(_setPositionsMethodKey85,
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

        MethodHandler methodHandler = new MethodHandler(_getAllPositionsVersionsMethodKey86,
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

        MethodHandler methodHandler = new MethodHandler(_hasUserVotedMethodKey87,
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
        MethodHandler methodHandler = new MethodHandler(_voteMethodKey88,
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
        MethodHandler methodHandler = new MethodHandler(_unvoteMethodKey89,
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
        MethodHandler methodHandler = new MethodHandler(_storeMethodKey90,
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
        MethodHandler methodHandler = new MethodHandler(_updateAllAttributesMethodKey91,
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
        MethodHandler methodHandler = new MethodHandler(_updateAttributeMethodKey92,
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

        MethodHandler methodHandler = new MethodHandler(_getMembersMethodKey93,
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

        MethodHandler methodHandler = new MethodHandler(_getMembershipRequestsMethodKey94,
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
        MethodHandler methodHandler = new MethodHandler(_addMembershipRequestMethodKey95,
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
        MethodHandler methodHandler = new MethodHandler(_dennyMembershipRequestMethodKey96,
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
        MethodHandler methodHandler = new MethodHandler(_approveMembershipRequestMethodKey97,
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
        MethodHandler methodHandler = new MethodHandler(_publishMethodKey98,
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
        MethodHandler methodHandler = new MethodHandler(_deleteMethodKey99,
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

        MethodHandler methodHandler = new MethodHandler(_getUpdateAuthorMethodKey100,
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

        MethodHandler methodHandler = new MethodHandler(_getFansMethodKey101,
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

        MethodHandler methodHandler = new MethodHandler(_addFanMethodKey102,
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
        MethodHandler methodHandler = new MethodHandler(_removeFanMethodKey103,
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

        MethodHandler methodHandler = new MethodHandler(_isUserAFanMethodKey104,
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

        MethodHandler methodHandler = new MethodHandler(_isUserAMemberMethodKey105,
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

        MethodHandler methodHandler = new MethodHandler(_hasUserRequestedMembershipMethodKey106,
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

        MethodHandler methodHandler = new MethodHandler(_isAdminMethodKey107,
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

        MethodHandler methodHandler = new MethodHandler(_isOwnerMethodKey108,
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
        MethodHandler methodHandler = new MethodHandler(_setUserPermissionMethodKey109,
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
        MethodHandler methodHandler = new MethodHandler(_removeMemberMethodKey110,
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
        MethodHandler methodHandler = new MethodHandler(_joinIfNotAMemberMethodKey111,
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
        MethodHandler methodHandler = new MethodHandler(_setSeekingAssistanceMethodKey112,
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

        MethodHandler methodHandler = new MethodHandler(_isSeekingAssistanceMethodKey113,
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

        MethodHandler methodHandler = new MethodHandler(_getDiscussionCategoryGroupMethodKey114,
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

        MethodHandler methodHandler = new MethodHandler(_promoteMethodKey115,
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

        MethodHandler methodHandler = new MethodHandler(_getPromotedMethodKey116,
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

        MethodHandler methodHandler = new MethodHandler(_getCommentsCountMethodKey117,
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
        MethodHandler methodHandler = new MethodHandler(_setPlaceMethodKey118,
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
        MethodHandler methodHandler = new MethodHandler(_removePlaceMethodKey119,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanVotesMethodKey120,
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
        MethodHandler methodHandler = new MethodHandler(_setRibbonMethodKey121,
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
        MethodHandler methodHandler = new MethodHandler(_setRibbonTextMethodKey122,
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
        MethodHandler methodHandler = new MethodHandler(_setAttributeMethodKey123,
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
        MethodHandler methodHandler = new MethodHandler(_removeAttributeMethodKey124,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanTemplateMethodKey125,
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

        MethodHandler methodHandler = new MethodHandler(_getPlanSectionsMethodKey126,
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
        MethodHandler methodHandler = new MethodHandler(_setSectionContentMethodKey127,
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

        MethodHandler methodHandler = new MethodHandler(_getAllPlanSectionsMethodKey128,
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

        MethodHandler methodHandler = new MethodHandler(_getRibbonMethodKey129,
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
        MethodHandler methodHandler = new MethodHandler(_setTeamMethodKey130,
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

        MethodHandler methodHandler = new MethodHandler(_getTeamMethodKey131,
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
        MethodHandler methodHandler = new MethodHandler(_revertToMethodKey132,
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

        MethodHandler methodHandler = new MethodHandler(_getTagsMethodKey133,
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
        MethodHandler methodHandler = new MethodHandler(_setTagsMethodKey134,
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

        MethodHandler methodHandler = new MethodHandler(_getTagsHoverMethodKey135,
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
        MethodHandler methodHandler = new MethodHandler(_setTagsHoverMethodKey136,
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

        MethodHandler methodHandler = new MethodHandler(_getTagsOrderMethodKey137,
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
        MethodHandler methodHandler = new MethodHandler(_setTagsOrderMethodKey138,
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
        MethodHandler methodHandler = new MethodHandler(_promotePlansMethodKey139,
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
        MethodHandler methodHandler = new MethodHandler(_promotePlansMethodKey140,
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
