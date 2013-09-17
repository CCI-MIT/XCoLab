package com.liferay.portal.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;

public class PersistedModelLocalServiceRegistryMock implements PersistedModelLocalServiceRegistry {

    public PersistedModelLocalService getPersistedModelLocalService(
        String className) {

        return _persistedModelLocalServices.get(className);
    }

    public List<PersistedModelLocalService> getPersistedModelLocalServices() {
        return ListUtil.fromMapValues(_persistedModelLocalServices);
    }

    public boolean isPermissionedModelLocalService(String className) {
        PersistedModelLocalService persistedModelLocalService =
            getPersistedModelLocalService(className);

        if (persistedModelLocalService == null) {
            return false;
        }

        if (persistedModelLocalService instanceof
                PermissionedModelLocalService) {

            return true;
        }

        return false;
    }

    public void register(
        String className,
        PersistedModelLocalService persistedModelLocalService) {
        
        System.out.println(className + "\t" + String.valueOf(persistedModelLocalService));

        // ignore
        /*
        PersistedModelLocalService oldPersistedModelLocalService =
            _persistedModelLocalServices.put(
                className, persistedModelLocalService);
         
        if (oldPersistedModelLocalService != null) {
            _log.warn("Duplicate class name " + className);
        }
        */
    }

    public void unregister(String className) {
        // _persistedModelLocalServices.remove(className);
    }

    private static Log _log = LogFactoryUtil.getLog(
        PersistedModelLocalServiceRegistryMock.class);

    private Map<String, PersistedModelLocalService>
        _persistedModelLocalServices =
            new ConcurrentHashMap<String, PersistedModelLocalService>();

}
