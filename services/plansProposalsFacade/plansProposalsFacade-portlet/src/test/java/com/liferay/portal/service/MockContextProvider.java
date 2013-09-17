package com.liferay.portal.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.icefaces.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.liferay.portal.bean.BeanLocatorImpl;
import com.liferay.portal.cache.CacheRegistryImpl;
import com.liferay.portal.configuration.ConfigurationFactoryImpl;
import com.liferay.portal.dao.db.DBFactoryImpl;
import com.liferay.portal.dao.jdbc.spring.MappingSqlQueryFactoryImpl;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

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
    
    public void afterPropertiesSet() throws FileNotFoundException, IOException, NamingException, SQLException {
        DB db = DBFactoryUtil.getDB();
        //_log.info("Running " + buildNamespace + " SQL scripts");
        
        String tablesSQL = IOUtils.toString(new FileReader(new File("./src/main/webapp/WEB-INF/sql/tables.sql")));
        String sequencesSQL = IOUtils.toString(new FileReader(new File("./src/main/webapp/WEB-INF/sql/sequences.sql")));
        String indexesSQL = IOUtils.toString(new FileReader(new File("./src/main/webapp/WEB-INF/sql/indexes.sql")));
        System.out.println("Running SQL scripts");
        db.runSQLTemplateString(tablesSQL, true, false);
        db.runSQLTemplateString(sequencesSQL, true, false);
        db.runSQLTemplateString(indexesSQL, true, false);
        System.out.println("SQL scripts executed");
    }
    
    public void setDataSource(DataSource dataSource) {
        new InfrastructureUtil().setDataSource(dataSource);
    }

}
