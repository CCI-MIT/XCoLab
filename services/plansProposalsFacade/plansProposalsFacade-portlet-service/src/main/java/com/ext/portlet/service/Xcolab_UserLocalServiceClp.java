package com.ext.portlet.service;

import com.liferay.portal.service.InvokableLocalService;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Xcolab_UserLocalServiceClp implements Xcolab_UserLocalService {
    private InvokableLocalService _invokableLocalService;
    private String _methodName0;
    private String[] _methodParameterTypes0;
    private String _methodName1;
    private String[] _methodParameterTypes1;
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

    public Xcolab_UserLocalServiceClp(
        InvokableLocalService invokableLocalService) {
        _invokableLocalService = invokableLocalService;

        _methodName0 = "getBeanIdentifier";

        _methodParameterTypes0 = new String[] {  };

        _methodName1 = "setBeanIdentifier";

        _methodParameterTypes1 = new String[] { "java.lang.String" };

        _methodName3 = "getUsersSortedByScreenName";

        _methodParameterTypes3 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName4 = "getUsersSortedByScreenNameFilteredByCategory";

        _methodParameterTypes4 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName5 = "getUsersSortedByRoleName";

        _methodParameterTypes5 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName6 = "getUsersSortedByMemberSince";

        _methodParameterTypes6 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName7 = "getUsersSortedByMemberSinceFilteredByCategory";

        _methodParameterTypes7 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName8 = "getUsersSortedByActivityCount";

        _methodParameterTypes8 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName9 = "getUsersSortedByActivityCountFilteredByCategory";

        _methodParameterTypes9 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName10 = "getUsersSortedByPoints";

        _methodParameterTypes10 = new String[] {
                "int", "int", "java.lang.String", "boolean"
            };

        _methodName11 = "getUsersSortedByPointsFilteredByCategory";

        _methodParameterTypes11 = new String[] {
                "int", "int", "java.lang.String", "java.lang.String", "boolean"
            };

        _methodName12 = "getUserActivityCount";

        _methodParameterTypes12 = new String[] { "java.lang.Long" };

        _methodName13 = "findUsersByLoginIP";

        _methodParameterTypes13 = new String[] { "java.lang.String" };
    }

    @Override
    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName0,
                    _methodParameterTypes0, new Object[] {  });
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
            _invokableLocalService.invokeMethod(_methodName1,
                _methodParameterTypes1,
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

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenName(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName3,
                    _methodParameterTypes3,
                    new Object[] {
                        begin,
                        
                    end,
                        
                    ClpSerializer.translateInput(filter),
                        
                    ascendingOrder
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

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName4,
                    _methodParameterTypes4,
                    new Object[] {
                        begin,
                        
                    end,
                        
                    ClpSerializer.translateInput(filter),
                        
                    ClpSerializer.translateInput(memberCategory),
                        
                    ascendingOrder
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

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleName(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName5,
                    _methodParameterTypes5,
                    new Object[] {
                        begin,
                        
                    end,
                        
                    ClpSerializer.translateInput(filter),
                        
                    ascendingOrder
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

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSince(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName6,
                    _methodParameterTypes6,
                    new Object[] {
                        begin,
                        
                    end,
                        
                    ClpSerializer.translateInput(filter),
                        
                    ascendingOrder
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

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName7,
                    _methodParameterTypes7,
                    new Object[] {
                        begin,
                        
                    end,
                        
                    ClpSerializer.translateInput(filter),
                        
                    ClpSerializer.translateInput(memberCategory),
                        
                    ascendingOrder
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

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCount(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName8,
                    _methodParameterTypes8,
                    new Object[] {
                        begin,
                        
                    end,
                        
                    ClpSerializer.translateInput(filter),
                        
                    ascendingOrder
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

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName9,
                    _methodParameterTypes9,
                    new Object[] {
                        begin,
                        
                    end,
                        
                    ClpSerializer.translateInput(filter),
                        
                    ClpSerializer.translateInput(memberCategory),
                        
                    ascendingOrder
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

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByPoints(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName10,
                    _methodParameterTypes10,
                    new Object[] {
                        begin,
                        
                    end,
                        
                    ClpSerializer.translateInput(filter),
                        
                    ascendingOrder
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

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategoryFilter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName11,
                    _methodParameterTypes11,
                    new Object[] {
                        begin,
                        
                    end,
                        
                    ClpSerializer.translateInput(filter),
                        
                    ClpSerializer.translateInput(memberCategoryFilter),
                        
                    ascendingOrder
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

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public long getUserActivityCount(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName12,
                    _methodParameterTypes12,
                    new Object[] { ClpSerializer.translateInput(userId) });
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
    public java.util.List<com.liferay.portal.model.User> findUsersByLoginIP(
        java.lang.String loginIP)
        throws com.liferay.portal.kernel.exception.SystemException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName13,
                    _methodParameterTypes13,
                    new Object[] { ClpSerializer.translateInput(loginIP) });
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

        return (java.util.List<com.liferay.portal.model.User>) ClpSerializer.translateOutput(returnObj);
    }
}
