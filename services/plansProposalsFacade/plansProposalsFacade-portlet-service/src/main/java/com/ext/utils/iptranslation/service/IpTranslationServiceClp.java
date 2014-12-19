package com.ext.utils.iptranslation.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.ext.utils.iptranslation.IpTranslationService;
import com.ext.utils.iptranslation.Location;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;

public class IpTranslationServiceClp implements IpTranslationService, InvokableLocalService {
    private InvokableLocalService _invokableLocalService;
    private MethodKey logUserIn;
    private ClassLoader _classLoader;
    
    public IpTranslationServiceClp(InvokableLocalService invokableLocalService) {
    	_invokableLocalService = invokableLocalService;
    }
    
    
    @Override
    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
    	
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        if (contextClassLoader != _classLoader) {
            currentThread.setContextClassLoader(_classLoader);
        }

        try {
            return _invokableLocalService.invokeMethod(name, parameterTypes, arguments);
        } finally {
            if (contextClassLoader != _classLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }
    
    public void afterPropertiesSet() {
        Class<?> clazz = getClass();

        _classLoader = clazz.getClassLoader();
    }

    public void destroy() {
        PersistedModelLocalServiceRegistryUtil.unregister(
            "com.ext.portlet.model.Proposal");
    }


	@Override
	public Location getLocationForIp(String ip) throws Exception {
        try {
            Object retVal = _invokableLocalService.invokeMethod("getLocationForIp", 
            		new String[] {"java.lang.String"}, 
            		new Object[] { ip });
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            
            oos.writeObject(retVal);
            
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Location) ois.readObject();
            
            
        } catch (Throwable t) {
        	if (t instanceof InvocationTargetException) {
        		t = t.getCause();
        	}
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            throw (Exception) t;
        }
	}


	@Override
	public void reloadLocationAndBlockData() throws Exception {
        try {
            Object retVal = _invokableLocalService.invokeMethod("reloadLocationAndBlockData", 
            		new String[] {}, 
            		new Object[] {});
            
        } catch (Throwable t) {
        	if (t instanceof InvocationTargetException) {
        		t = t.getCause();
        	}
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            throw (Exception) t;
        }
		
	}


}
