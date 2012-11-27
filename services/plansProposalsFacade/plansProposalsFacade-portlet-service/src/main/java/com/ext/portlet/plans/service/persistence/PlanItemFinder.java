package com.ext.portlet.plans.service.persistence;

public interface PlanItemFinder {
    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans();

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlansForPhase(
        java.lang.Long phaseId);

    public void removePlanWithHistory(long planId);

    public int countVotesForPlanType(com.ext.portlet.plans.model.PlanType type);

    public int countFilteredPlans(
        com.ext.portlet.plans.model.PlansUserSettings planUserSettings)
        throws java.lang.Exception;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getFilteredPlans(
        com.ext.portlet.plans.model.PlansUserSettings planUserSettings,
        int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection) throws java.lang.Exception;

    public int countPlans(long planTypeId);

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans(
        long planTypeId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    public int getUserVotePosition(long userId, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.ext.portlet.plans.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public int getFilteredUserVotePosition(
        com.ext.portlet.plans.model.PlansUserSettings planUserSettings,
        long userId, java.lang.String sortColumn, java.lang.String sortDirection)
        throws java.lang.Exception;

    public com.ext.portlet.plans.model.PlanItem findLatestVersion(
        java.lang.Long planId);

    public void clearPhaseCache(java.lang.Long contestPhasePk);
}
