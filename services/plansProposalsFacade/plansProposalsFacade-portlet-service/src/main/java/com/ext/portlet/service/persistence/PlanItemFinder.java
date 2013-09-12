package com.ext.portlet.service.persistence;

public interface PlanItemFinder {
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans();

    public java.util.List<com.ext.portlet.model.PlanItem> getPlansForPhase(
        java.lang.Long phaseId);

    public void removePlanWithHistory(long planId);

    public java.util.List<com.ext.portlet.model.PlanItem> getPlansForUser(
        long userId);

    public int countVotesForPlanType(com.ext.portlet.model.PlanType type);

    public int countFilteredPlans(
        com.ext.portlet.model.PlansUserSettings planUserSettings)
        throws java.lang.Exception;

    public java.util.List<com.ext.portlet.model.PlanItem> getFilteredPlans(
        com.ext.portlet.model.PlansUserSettings planUserSettings, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection)
        throws java.lang.Exception;

    public int countPlans(long planTypeId);

    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        long planTypeId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.ext.portlet.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    public int getUserVotePosition(long userId, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.ext.portlet.NoSuchPlanAttributeFilterException,
            com.ext.portlet.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public int getFilteredUserVotePosition(
        com.ext.portlet.model.PlansUserSettings planUserSettings, long userId,
        java.lang.String sortColumn, java.lang.String sortDirection)
        throws java.lang.Exception;

    public com.ext.portlet.model.PlanItem findLatestVersion(
        java.lang.Long planId);

    public void clearPhaseCache(java.lang.Long contestPhasePk);

    public long countPlansByContestPhase(java.lang.Long phaseId);
}
