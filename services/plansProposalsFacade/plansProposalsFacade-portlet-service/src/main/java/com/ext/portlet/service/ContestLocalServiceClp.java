package com.ext.portlet.service;

import com.liferay.portal.service.InvokableLocalService;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestLocalServiceClp implements ContestLocalService {
<<<<<<< HEAD
    private InvokableLocalService _invokableLocalService;
    private String _methodName0;
    private String[] _methodParameterTypes0;
    private String _methodName1;
    private String[] _methodParameterTypes1;
    private String _methodName2;
    private String[] _methodParameterTypes2;
    private String _methodName3;
    private String[] _methodParameterTypes3;
    private String _methodName4;
    private String[] _methodParameterTypes4;
    private String _methodName5;
    private String[] _methodParameterTypes5;
    private String _methodName6;
    private String[] _methodParameterTypes6;
    private String _methodName7;
    private String[] _methodParameterTypes7;
    private String _methodName8;
    private String[] _methodParameterTypes8;
    private String _methodName9;
    private String[] _methodParameterTypes9;
    private String _methodName10;
    private String[] _methodParameterTypes10;
    private String _methodName11;
    private String[] _methodParameterTypes11;
    private String _methodName12;
    private String[] _methodParameterTypes12;
    private String _methodName13;
    private String[] _methodParameterTypes13;
    private String _methodName14;
    private String[] _methodParameterTypes14;
    private String _methodName15;
    private String[] _methodParameterTypes15;
    private String _methodName16;
    private String[] _methodParameterTypes16;
    private String _methodName17;
    private String[] _methodParameterTypes17;
    private String _methodName19;
    private String[] _methodParameterTypes19;
    private String _methodName20;
    private String[] _methodParameterTypes20;
    private String _methodName21;
    private String[] _methodParameterTypes21;
    private String _methodName22;
    private String[] _methodParameterTypes22;
    private String _methodName23;
    private String[] _methodParameterTypes23;
    private String _methodName24;
    private String[] _methodParameterTypes24;
    private String _methodName25;
    private String[] _methodParameterTypes25;
    private String _methodName26;
    private String[] _methodParameterTypes26;
    private String _methodName27;
    private String[] _methodParameterTypes27;
    private String _methodName28;
    private String[] _methodParameterTypes28;
    private String _methodName29;
    private String[] _methodParameterTypes29;
    private String _methodName30;
    private String[] _methodParameterTypes30;
    private String _methodName31;
    private String[] _methodParameterTypes31;
    private String _methodName32;
    private String[] _methodParameterTypes32;
    private String _methodName33;
    private String[] _methodParameterTypes33;
    private String _methodName34;
    private String[] _methodParameterTypes34;
    private String _methodName35;
    private String[] _methodParameterTypes35;
    private String _methodName36;
    private String[] _methodParameterTypes36;
    private String _methodName37;
    private String[] _methodParameterTypes37;
    private String _methodName38;
    private String[] _methodParameterTypes38;
    private String _methodName39;
    private String[] _methodParameterTypes39;
    private String _methodName40;
    private String[] _methodParameterTypes40;
    private String _methodName41;
    private String[] _methodParameterTypes41;
    private String _methodName42;
    private String[] _methodParameterTypes42;
    private String _methodName43;
    private String[] _methodParameterTypes43;
    private String _methodName44;
    private String[] _methodParameterTypes44;
    private String _methodName45;
    private String[] _methodParameterTypes45;
    private String _methodName46;
    private String[] _methodParameterTypes46;
    private String _methodName47;
    private String[] _methodParameterTypes47;
    private String _methodName48;
    private String[] _methodParameterTypes48;
    private String _methodName49;
    private String[] _methodParameterTypes49;
    private String _methodName50;
    private String[] _methodParameterTypes50;
    private String _methodName51;
    private String[] _methodParameterTypes51;
    private String _methodName52;
    private String[] _methodParameterTypes52;
    private String _methodName53;
    private String[] _methodParameterTypes53;
    private String _methodName54;
    private String[] _methodParameterTypes54;
    private String _methodName55;
    private String[] _methodParameterTypes55;

    public ContestLocalServiceClp(InvokableLocalService invokableLocalService) {
        _invokableLocalService = invokableLocalService;

        _methodName0 = "addContest";

        _methodParameterTypes0 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName1 = "createContest";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteContest";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteContest";

        _methodParameterTypes3 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName4 = "dynamicQuery";

        _methodParameterTypes4 = new String[] {  };

        _methodName5 = "dynamicQuery";

        _methodParameterTypes5 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName6 = "dynamicQuery";

        _methodParameterTypes6 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
            };

        _methodName7 = "dynamicQuery";

        _methodParameterTypes7 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
                "com.liferay.portal.kernel.util.OrderByComparator"
            };

        _methodName8 = "dynamicQueryCount";

        _methodParameterTypes8 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName9 = "dynamicQueryCount";

        _methodParameterTypes9 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery",
                "com.liferay.portal.kernel.dao.orm.Projection"
            };

        _methodName10 = "fetchContest";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getContest";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getContests";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getContestsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateContest";

        _methodParameterTypes15 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName16 = "getBeanIdentifier";

        _methodParameterTypes16 = new String[] {  };

        _methodName17 = "setBeanIdentifier";

        _methodParameterTypes17 = new String[] { "java.lang.String" };

        _methodName19 = "getContestByActiveFlag";

        _methodParameterTypes19 = new String[] { "boolean" };

        _methodName20 = "createNewContest";

        _methodParameterTypes20 = new String[] {
                "java.lang.Long", "java.lang.String"
            };

        _methodName21 = "updateContestGroupsAndDiscussions";

        _methodParameterTypes21 = new String[] {  };

        _methodName22 = "findByActiveFeatured";

        _methodParameterTypes22 = new String[] { "boolean", "boolean" };

        _methodName23 = "findByActiveFlag";

        _methodParameterTypes23 = new String[] { "boolean", "int" };

        _methodName24 = "findByActiveFlagText";

        _methodParameterTypes24 = new String[] { "boolean", "java.lang.String" };

        _methodName25 = "getPhases";

        _methodParameterTypes25 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName26 = "getPlanType";

        _methodParameterTypes26 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName27 = "getActivePhases";

        _methodParameterTypes27 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName28 = "getActivePhase";

        _methodParameterTypes28 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName29 = "getActiveOrLastPhase";

        _methodParameterTypes29 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName30 = "isActive";

        _methodParameterTypes30 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName31 = "getDebatesIds";

        _methodParameterTypes31 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName32 = "getTotalVotes";

        _methodParameterTypes32 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName33 = "updateDefaultPlanDescription";

        _methodParameterTypes33 = new String[] {
                "com.ext.portlet.model.Contest", "java.lang.String"
            };

        _methodName34 = "store";

        _methodParameterTypes34 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName35 = "getPlanTemplate";
=======
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _addContestMethodKey0;
    private MethodKey _createContestMethodKey1;
    private MethodKey _deleteContestMethodKey2;
    private MethodKey _deleteContestMethodKey3;
    private MethodKey _dynamicQueryMethodKey4;
    private MethodKey _dynamicQueryMethodKey5;
    private MethodKey _dynamicQueryMethodKey6;
    private MethodKey _dynamicQueryCountMethodKey7;
    private MethodKey _fetchContestMethodKey8;
    private MethodKey _getContestMethodKey9;
    private MethodKey _getPersistedModelMethodKey10;
    private MethodKey _getContestsMethodKey11;
    private MethodKey _getContestsCountMethodKey12;
    private MethodKey _updateContestMethodKey13;
    private MethodKey _updateContestMethodKey14;
    private MethodKey _getBeanIdentifierMethodKey15;
    private MethodKey _setBeanIdentifierMethodKey16;
    private MethodKey _getContestByActiveFlagMethodKey17;
    private MethodKey _createNewContestMethodKey18;
    private MethodKey _updateContestGroupsAndDiscussionsMethodKey19;
    private MethodKey _findByActiveFeaturedMethodKey20;
    private MethodKey _findByActiveFlagMethodKey21;
    private MethodKey _findByActiveFlagTextMethodKey22;
    private MethodKey _getPhasesMethodKey23;
    private MethodKey _getPlanTypeMethodKey24;
    private MethodKey _getActivePhasesMethodKey25;
    private MethodKey _getActivePhaseMethodKey26;
    private MethodKey _getActiveOrLastPhaseMethodKey27;
    private MethodKey _isActiveMethodKey28;
    private MethodKey _getDebatesIdsMethodKey29;
    private MethodKey _getTotalVotesMethodKey30;
    private MethodKey _updateDefaultPlanDescriptionMethodKey31;
    private MethodKey _storeMethodKey32;
    private MethodKey _getPlanTemplateMethodKey33;
    private MethodKey _getFocusAreaMethodKey34;
    private MethodKey _getLogoMethodKey35;
    private MethodKey _getSponsorLogoMethodKey36;
    private MethodKey _setLogoMethodKey37;
    private MethodKey _setSponsorLogoMethodKey38;
    private MethodKey _getLogoPathMethodKey39;
    private MethodKey _getSponsorLogoPathMethodKey40;
    private MethodKey _getProposalsCountMethodKey41;
    private MethodKey _getDiscussionCategoryGroupMethodKey42;
    private MethodKey _getCommentsCountMethodKey43;
    private MethodKey _getProposalsCommentsCountMethodKey44;
    private MethodKey _getVotesCountMethodKey45;
    private MethodKey _getTotalCommentsMethodKey46;
    private MethodKey _getTeamMembersMethodKey47;
    private MethodKey _isSubscribedMethodKey48;
    private MethodKey _subscribeMethodKey49;
    private MethodKey _unsubscribeMethodKey50;
    private MethodKey _getModelIdsMethodKey51;
    private MethodKey _getDefaultModelIdMethodKey52;
    private MethodKey _getContestsMatchingOntologyTermsMethodKey53;

    public ContestLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _addContestMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "addContest", com.ext.portlet.model.Contest.class);

        _createContestMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "createContest", long.class);

        _deleteContestMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteContest", long.class);

        _deleteContestMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
                "deleteContest", com.ext.portlet.model.Contest.class);

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

        _fetchContestMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
                "fetchContest", long.class);

        _getContestMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContest", long.class);

        _getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPersistedModel", java.io.Serializable.class);

        _getContestsMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContests", int.class, int.class);

        _getContestsCountMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContestsCount");

        _updateContestMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateContest", com.ext.portlet.model.Contest.class);

        _updateContestMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateContest", com.ext.portlet.model.Contest.class,
                boolean.class);

        _getBeanIdentifierMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
                "getBeanIdentifier");
>>>>>>> added service builder

        _methodParameterTypes35 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName36 = "getFocusArea";

        _methodParameterTypes36 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName37 = "getLogo";

        _methodParameterTypes37 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName38 = "getSponsorLogo";

        _methodParameterTypes38 = new String[] { "com.ext.portlet.model.Contest" };

<<<<<<< HEAD
        _methodName39 = "setLogo";

        _methodParameterTypes39 = new String[] {
                "com.ext.portlet.model.Contest", "java.io.File"
            };

        _methodName40 = "setSponsorLogo";

        _methodParameterTypes40 = new String[] {
                "com.ext.portlet.model.Contest", "java.io.File"
            };

        _methodName41 = "getLogoPath";

        _methodParameterTypes41 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName42 = "getSponsorLogoPath";

        _methodParameterTypes42 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName43 = "getProposalsCount";

        _methodParameterTypes43 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName44 = "getDiscussionCategoryGroup";

        _methodParameterTypes44 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName45 = "getCommentsCount";

        _methodParameterTypes45 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName46 = "getProposalsCommentsCount";

        _methodParameterTypes46 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName47 = "getVotesCount";

        _methodParameterTypes47 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName48 = "getTotalComments";

        _methodParameterTypes48 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName49 = "getTeamMembers";

        _methodParameterTypes49 = new String[] { "com.ext.portlet.model.Contest" };

        _methodName50 = "isSubscribed";

        _methodParameterTypes50 = new String[] { "long", "long" };

        _methodName51 = "subscribe";
=======
        _getPhasesMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPhases", Contest.class);

        _getPlanTypeMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanType", Contest.class);

        _getActivePhasesMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
                "getActivePhases", Contest.class);

        _getActivePhaseMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
                "getActivePhase", Contest.class);

        _getActiveOrLastPhaseMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
                "getActiveOrLastPhase", Contest.class);

        _isActiveMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
                "isActive", Contest.class);

        _getDebatesIdsMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDebatesIds", Contest.class);

        _getTotalVotesMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTotalVotes", Contest.class);

        _updateDefaultPlanDescriptionMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
                "updateDefaultPlanDescription", Contest.class,
                java.lang.String.class);

        _storeMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
                "store", Contest.class);

        _getPlanTemplateMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
                "getPlanTemplate", Contest.class);

        _getFocusAreaMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
                "getFocusArea", Contest.class);

        _getLogoMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
                "getLogo", Contest.class);

        _getSponsorLogoMethodKey36 = new MethodKey(_classLoaderProxy.getClassName(),
                "getSponsorLogo", Contest.class);

        _setLogoMethodKey37 = new MethodKey(_classLoaderProxy.getClassName(),
                "setLogo", Contest.class, java.io.File.class);

        _setSponsorLogoMethodKey38 = new MethodKey(_classLoaderProxy.getClassName(),
                "setSponsorLogo", Contest.class, java.io.File.class);

        _getLogoPathMethodKey39 = new MethodKey(_classLoaderProxy.getClassName(),
                "getLogoPath", Contest.class);

        _getSponsorLogoPathMethodKey40 = new MethodKey(_classLoaderProxy.getClassName(),
                "getSponsorLogoPath", Contest.class);

        _getProposalsCountMethodKey41 = new MethodKey(_classLoaderProxy.getClassName(),
                "getProposalsCount", Contest.class);

        _getDiscussionCategoryGroupMethodKey42 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDiscussionCategoryGroup", Contest.class);

        _getCommentsCountMethodKey43 = new MethodKey(_classLoaderProxy.getClassName(),
                "getCommentsCount", Contest.class);

        _getProposalsCommentsCountMethodKey44 = new MethodKey(_classLoaderProxy.getClassName(),
                "getProposalsCommentsCount", Contest.class);

        _getVotesCountMethodKey45 = new MethodKey(_classLoaderProxy.getClassName(),
                "getVotesCount", Contest.class);

        _getTotalCommentsMethodKey46 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTotalComments", Contest.class);

        _getTeamMembersMethodKey47 = new MethodKey(_classLoaderProxy.getClassName(),
                "getTeamMembers", Contest.class);
>>>>>>> added service builder

        _methodParameterTypes51 = new String[] { "long", "long" };

        _methodName52 = "unsubscribe";

        _methodParameterTypes52 = new String[] { "long", "long" };

        _methodName53 = "getModelIds";

<<<<<<< HEAD
        _methodParameterTypes53 = new String[] { "long" };

        _methodName54 = "getDefaultModelId";

        _methodParameterTypes54 = new String[] { "long" };

        _methodName55 = "getNumberOfProposalsForJudge";

        _methodParameterTypes55 = new String[] {
                "com.liferay.portal.model.User", "com.ext.portlet.model.Contest"
            };
=======
        _getDefaultModelIdMethodKey52 = new MethodKey(_classLoaderProxy.getClassName(),
                "getDefaultModelId", long.class);

        _getContestsMatchingOntologyTermsMethodKey53 = new MethodKey(_classLoaderProxy.getClassName(),
                "getContestsMatchingOntologyTerms", java.util.List.class);
>>>>>>> added service builder
    }

    @Override
    public com.ext.portlet.model.Contest addContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName0,
                    _methodParameterTypes0,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

    @Override
    public com.ext.portlet.model.Contest createContest(long ContestPK) {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName1,
                    _methodParameterTypes1, new Object[] { ContestPK });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.ext.portlet.model.Contest) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.ext.portlet.model.Contest deleteContest(long ContestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName2,
                    _methodParameterTypes2, new Object[] { ContestPK });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

    @Override
    public com.ext.portlet.model.Contest deleteContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName3,
                    _methodParameterTypes3,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName4,
                    _methodParameterTypes4, new Object[] {  });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.kernel.dao.orm.DynamicQuery) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName5,
                    _methodParameterTypes5,
                    new Object[] { ClpSerializer.translateInput(dynamicQuery) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName6,
                    _methodParameterTypes6,
                    new Object[] {
                        ClpSerializer.translateInput(dynamicQuery),
                        
                    start,
                        
                    end
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName7,
                    _methodParameterTypes7,
                    new Object[] {
                        ClpSerializer.translateInput(dynamicQuery),
                        
                    start,
                        
                    end,
                        
                    ClpSerializer.translateInput(orderByComparator)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName8,
                    _methodParameterTypes8,
                    new Object[] { ClpSerializer.translateInput(dynamicQuery) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName9,
                    _methodParameterTypes9,
                    new Object[] {
                        ClpSerializer.translateInput(dynamicQuery),
                        
                    ClpSerializer.translateInput(projection)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

    @Override
    public com.ext.portlet.model.Contest fetchContest(long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName10,
                    _methodParameterTypes10, new Object[] { ContestPK });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

    @Override
    public com.ext.portlet.model.Contest getContest(long ContestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName11,
                    _methodParameterTypes11, new Object[] { ContestPK });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName12,
                    _methodParameterTypes12,
                    new Object[] { ClpSerializer.translateInput(primaryKeyObj) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

    @Override
    public java.util.List<com.ext.portlet.model.Contest> getContests(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName13,
                    _methodParameterTypes13, new Object[] { start, end });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<com.ext.portlet.model.Contest>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public int getContestsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName14,
                    _methodParameterTypes14, new Object[] {  });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

    @Override
    public com.ext.portlet.model.Contest updateContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName15,
                    _methodParameterTypes15,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

    @Override
    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName16,
                    _methodParameterTypes16, new Object[] {  });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        try {
            _invokableLocalService.invokeMethod(_methodName17,
                _methodParameterTypes17,
                new Object[] { ClpSerializer.translateInput(beanIdentifier) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

<<<<<<< HEAD
    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        throw new UnsupportedOperationException();
    }

    @Override
    public com.ext.portlet.model.Contest getContestByActiveFlag(
        boolean contestActive)
=======
    public Contest getContestByActiveFlag(boolean contestActive)
>>>>>>> added service builder
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName19,
                    _methodParameterTypes19, new Object[] { contestActive });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.ext.portlet.NoSuchContestException) {
                throw (com.ext.portlet.NoSuchContestException) t;
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

        return (Contest) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public com.ext.portlet.model.Contest createNewContest(
        java.lang.Long userId, java.lang.String name)
=======
    public Contest createNewContest(java.lang.Long userId, java.lang.String name)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName20,
                    _methodParameterTypes20,
                    new Object[] {
                        ClpSerializer.translateInput(userId),
                        
                    ClpSerializer.translateInput(name)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

        return (Contest) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public void updateContestGroupsAndDiscussions()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        try {
            _invokableLocalService.invokeMethod(_methodName21,
                _methodParameterTypes21, new Object[] {  });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

<<<<<<< HEAD
    @Override
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeatured(
        boolean active, boolean featured)
=======
    public java.util.List<Contest> findByActiveFeatured(boolean active,
        boolean featured)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName22,
                    _methodParameterTypes22, new Object[] { active, featured });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<Contest>) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlag(
        boolean active, int flag)
=======
    public java.util.List<Contest> findByActiveFlag(boolean active, int flag)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName23,
                    _methodParameterTypes23, new Object[] { active, flag });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<Contest>) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagText(
        boolean active, java.lang.String flagText)
=======
    public java.util.List<Contest> findByActiveFlagText(boolean active,
        java.lang.String flagText)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName24,
                    _methodParameterTypes24,
                    new Object[] { active, ClpSerializer.translateInput(
                            flagText) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<Contest>) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getPhases(
        com.ext.portlet.model.Contest contest) {
=======
    public java.util.List<ContestPhase> getPhases(Contest contest) {
>>>>>>> added service builder
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName25,
                    _methodParameterTypes25,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<ContestPhase>) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public com.ext.portlet.model.PlanType getPlanType(
        com.ext.portlet.model.Contest contest)
=======
    public PlanType getPlanType(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName26,
                    _methodParameterTypes26,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

        return (PlanType) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getActivePhases(
        com.ext.portlet.model.Contest contest)
=======
    public java.util.List<ContestPhase> getActivePhases(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName27,
                    _methodParameterTypes27,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

        return (java.util.List<ContestPhase>) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public com.ext.portlet.model.ContestPhase getActivePhase(
        com.ext.portlet.model.Contest contest)
=======
    public ContestPhase getActivePhase(Contest contest)
>>>>>>> added service builder
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName28,
                    _methodParameterTypes28,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.ext.portlet.NoSuchContestPhaseException) {
                throw (com.ext.portlet.NoSuchContestPhaseException) t;
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

        return (ContestPhase) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public com.ext.portlet.model.ContestPhase getActiveOrLastPhase(
        com.ext.portlet.model.Contest contest)
=======
    public ContestPhase getActiveOrLastPhase(Contest contest)
>>>>>>> added service builder
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName29,
                    _methodParameterTypes29,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.ext.portlet.NoSuchContestPhaseException) {
                throw (com.ext.portlet.NoSuchContestPhaseException) t;
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

        return (ContestPhase) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public boolean isActive(com.ext.portlet.model.Contest contest)
=======
    public boolean isActive(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName30,
                    _methodParameterTypes30,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

<<<<<<< HEAD
    @Override
    public java.util.List<java.lang.Long> getDebatesIds(
        com.ext.portlet.model.Contest contest)
=======
    public java.util.List<java.lang.Long> getDebatesIds(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName31,
                    _methodParameterTypes31,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

<<<<<<< HEAD
    @Override
    public java.lang.Integer getTotalVotes(
        com.ext.portlet.model.Contest contest)
=======
    public java.lang.Integer getTotalVotes(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName32,
                    _methodParameterTypes32,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

<<<<<<< HEAD
    @Override
    public void updateDefaultPlanDescription(
        com.ext.portlet.model.Contest contest, java.lang.String description)
=======
    public void updateDefaultPlanDescription(Contest contest,
        java.lang.String description)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.SystemException {
        try {
            _invokableLocalService.invokeMethod(_methodName33,
                _methodParameterTypes33,
                new Object[] {
                    ClpSerializer.translateInput(contest),
                    
                ClpSerializer.translateInput(description)
                });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

<<<<<<< HEAD
    @Override
    public void store(com.ext.portlet.model.Contest contest)
=======
    public void store(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.SystemException {
        try {
            _invokableLocalService.invokeMethod(_methodName34,
                _methodParameterTypes34,
                new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
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

<<<<<<< HEAD
    @Override
    public com.ext.portlet.model.PlanTemplate getPlanTemplate(
        com.ext.portlet.model.Contest contest)
=======
    public PlanTemplate getPlanTemplate(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName35,
                    _methodParameterTypes35,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

        return (PlanTemplate) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public com.ext.portlet.model.FocusArea getFocusArea(
        com.ext.portlet.model.Contest contest)
=======
    public FocusArea getFocusArea(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName36,
                    _methodParameterTypes36,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

        return (FocusArea) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public com.liferay.portal.model.Image getLogo(
        com.ext.portlet.model.Contest contest)
=======
    public com.liferay.portal.model.Image getLogo(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName37,
                    _methodParameterTypes37,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

<<<<<<< HEAD
    @Override
    public com.liferay.portal.model.Image getSponsorLogo(
        com.ext.portlet.model.Contest contest)
=======
    public com.liferay.portal.model.Image getSponsorLogo(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName38,
                    _methodParameterTypes38,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

<<<<<<< HEAD
    @Override
    public void setLogo(com.ext.portlet.model.Contest contest,
        java.io.File logoFile)
=======
    public void setLogo(Contest contest, java.io.File logoFile)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        try {
            _invokableLocalService.invokeMethod(_methodName39,
                _methodParameterTypes39,
                new Object[] {
                    ClpSerializer.translateInput(contest),
                    
                ClpSerializer.translateInput(logoFile)
                });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof java.io.IOException) {
                throw (java.io.IOException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

<<<<<<< HEAD
    @Override
    public void setSponsorLogo(com.ext.portlet.model.Contest contest,
        java.io.File logoFile)
=======
    public void setSponsorLogo(Contest contest, java.io.File logoFile)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        try {
            _invokableLocalService.invokeMethod(_methodName40,
                _methodParameterTypes40,
                new Object[] {
                    ClpSerializer.translateInput(contest),
                    
                ClpSerializer.translateInput(logoFile)
                });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
                throw (com.liferay.portal.kernel.exception.PortalException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof java.io.IOException) {
                throw (java.io.IOException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

<<<<<<< HEAD
    @Override
    public java.lang.String getLogoPath(com.ext.portlet.model.Contest contest)
=======
    public java.lang.String getLogoPath(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName41,
                    _methodParameterTypes41,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

<<<<<<< HEAD
    @Override
    public java.lang.String getSponsorLogoPath(
        com.ext.portlet.model.Contest contest)
=======
    public java.lang.String getSponsorLogoPath(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName42,
                    _methodParameterTypes42,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

<<<<<<< HEAD
    @Override
    public long getProposalsCount(com.ext.portlet.model.Contest contest)
=======
    public long getProposalsCount(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName43,
                    _methodParameterTypes43,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

<<<<<<< HEAD
    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        com.ext.portlet.model.Contest contest)
=======
    public DiscussionCategoryGroup getDiscussionCategoryGroup(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName44,
                    _methodParameterTypes44,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

        return (DiscussionCategoryGroup) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public long getCommentsCount(com.ext.portlet.model.Contest contest)
=======
    public long getCommentsCount(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName45,
                    _methodParameterTypes45,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

<<<<<<< HEAD
    @Override
    public long getProposalsCommentsCount(com.ext.portlet.model.Contest contest)
=======
    public long getProposalsCommentsCount(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName46,
                    _methodParameterTypes46,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

<<<<<<< HEAD
    @Override
    public long getVotesCount(com.ext.portlet.model.Contest contest)
=======
    public long getVotesCount(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName47,
                    _methodParameterTypes47,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

<<<<<<< HEAD
    @Override
    public long getTotalComments(com.ext.portlet.model.Contest contest)
=======
    public long getTotalComments(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName48,
                    _methodParameterTypes48,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

<<<<<<< HEAD
    @Override
    public java.util.List<com.ext.portlet.model.ContestTeamMember> getTeamMembers(
        com.ext.portlet.model.Contest contest)
=======
    public java.util.List<ContestTeamMember> getTeamMembers(Contest contest)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName49,
                    _methodParameterTypes49,
                    new Object[] { ClpSerializer.translateInput(contest) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.util.List<ContestTeamMember>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public boolean isSubscribed(long contestPK, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName50,
                    _methodParameterTypes50, new Object[] { contestPK, userId });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

    @Override
    public void subscribe(long contestPK, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        try {
            _invokableLocalService.invokeMethod(_methodName51,
                _methodParameterTypes51, new Object[] { contestPK, userId });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

    @Override
    public void unsubscribe(long contestPK, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        try {
            _invokableLocalService.invokeMethod(_methodName52,
                _methodParameterTypes52, new Object[] { contestPK, userId });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

    @Override
    public java.util.List<java.lang.Long> getModelIds(long contestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName53,
                    _methodParameterTypes53, new Object[] { contestPK });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

        return (java.util.List<java.lang.Long>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.lang.Long getDefaultModelId(long contestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName54,
                    _methodParameterTypes54, new Object[] { contestPK });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

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

        return (java.lang.Long) ClpSerializer.translateOutput(returnObj);
    }

<<<<<<< HEAD
    @Override
    public int getNumberOfProposalsForJudge(com.liferay.portal.model.User u,
        com.ext.portlet.model.Contest c)
=======
    public java.util.List<Contest> getContestsMatchingOntologyTerms(
        java.util.List<OntologyTerm> ontologyTerms)
>>>>>>> added service builder
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

<<<<<<< HEAD
        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName55,
                    _methodParameterTypes55,
                    new Object[] {
                        ClpSerializer.translateInput(u),
                        
                    ClpSerializer.translateInput(c)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

=======
        MethodHandler methodHandler = new MethodHandler(_getContestsMatchingOntologyTermsMethodKey53,
                ClpSerializer.translateInput(ontologyTerms));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
>>>>>>> added service builder
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

<<<<<<< HEAD
        return ((Integer) returnObj).intValue();
=======
        return (java.util.List<Contest>) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
>>>>>>> added service builder
    }
}
