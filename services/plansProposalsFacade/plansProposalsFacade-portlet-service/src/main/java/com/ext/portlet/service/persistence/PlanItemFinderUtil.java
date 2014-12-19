package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class PlanItemFinderUtil {
    private static PlanItemFinder _finder;

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlans() {
        return getFinder().getPlans();
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlansForPhase(
        java.lang.Long phaseId) {
        return getFinder().getPlansForPhase(phaseId);
    }

    public static void removePlanWithHistory(long planId) {
        getFinder().removePlanWithHistory(planId);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlansForUser(
        long userId) {
        return getFinder().getPlansForUser(userId);
    }

    public static int countVotesForPlanType(com.ext.portlet.model.PlanType type) {
        return getFinder().countVotesForPlanType(type);
    }

    public static int countFilteredPlans(
        com.ext.portlet.model.PlansUserSettings planUserSettings)
        throws java.lang.Exception {
        return getFinder().countFilteredPlans(planUserSettings);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getFilteredPlans(
        com.ext.portlet.model.PlansUserSettings planUserSettings, int start,
        int end, java.lang.String sortColumn, java.lang.String sortDirection)
        throws java.lang.Exception {
        return getFinder()
                   .getFilteredPlans(planUserSettings, start, end, sortColumn,
            sortDirection);
    }

    public static int countPlans(long planTypeId) {
        return getFinder().countPlans(planTypeId);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        long planTypeId, int start, int end, java.lang.String sortColumn,
        java.lang.String sortDirection)
        throws com.ext.portlet.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .getPlans(planTypeId, start, end, sortColumn, sortDirection);
    }

    public static int getUserVotePosition(long userId,
        java.lang.String sortColumn, java.lang.String sortDirection)
        throws com.ext.portlet.NoSuchPlanAttributeFilterException,
            com.ext.portlet.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUserVotePosition(userId, sortColumn, sortDirection);
    }

    public static int getFilteredUserVotePosition(
        com.ext.portlet.model.PlansUserSettings planUserSettings, long userId,
        java.lang.String sortColumn, java.lang.String sortDirection)
        throws java.lang.Exception {
        return getFinder()
                   .getFilteredUserVotePosition(planUserSettings, userId,
            sortColumn, sortDirection);
    }

    public static com.ext.portlet.model.PlanItem findLatestVersion(
        java.lang.Long planId) {
        return getFinder().findLatestVersion(planId);
    }

    public static void clearPhaseCache(java.lang.Long contestPhasePk) {
        getFinder().clearPhaseCache(contestPhasePk);
    }

    public static long countPlansByContestPhase(java.lang.Long phaseId) {
        return getFinder().countPlansByContestPhase(phaseId);
    }

    public static PlanItemFinder getFinder() {
        if (_finder == null) {
            _finder = (PlanItemFinder) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanItemFinder.class.getName());

            ReferenceRegistry.registerReference(PlanItemFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(PlanItemFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(PlanItemFinderUtil.class, "_finder");
    }
}
