package com.liferay.portal.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.liferay.portal.bean.BeanLocatorImpl;
import com.liferay.portal.cache.CacheRegistryImpl;
import com.liferay.portal.configuration.ConfigurationFactoryImpl;
import com.liferay.portal.dao.db.DBFactoryImpl;
import com.liferay.portal.dao.jdbc.spring.MappingSqlQueryFactoryImpl;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.util.InitUtil;

public class MockContextProvider implements ApplicationContextAware {
    public MockContextProvider() {
        PortletClassLoaderUtil.setClassLoader(MockContextProvider.class.getClassLoader());
        PortalClassLoaderUtil.setClassLoader(MockContextProvider.class.getClassLoader());
        //PortalClassLoaderUtil.setClassLoader(MockContextProvider.class.getClassLoader());
        ConfigurationFactoryUtil.setConfigurationFactory(new ConfigurationFactoryImpl());
        DBFactoryUtil.setDBFactory(new DBFactoryImpl());
        CacheRegistryUtil.setCacheRegistry(new CacheRegistryImpl());
        (new MappingSqlQueryFactoryUtil()).setMappingSqlQueryFactory(new MappingSqlQueryFactoryImpl());
        //InitUtil.initWithSpring();
        
    }
    public static ClassLoader getClassLoader() {
        return MockContextProvider.class.getClassLoader();
    }
    
    public static String getServletContextName() {
        return "mockServletContext";
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        PortalBeanLocatorUtil.setBeanLocator(new BeanLocatorImpl(getClassLoader(), applicationContext));
        
    }

}
