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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

/**
 * <p>Creates and initializes all beans that need to be set up for tests to run, also it initializes database.</p>
 *
 * @author janusz
 */
public class MockContextProvider implements ApplicationContextAware {
    public MockContextProvider() {
        PortletClassLoaderUtil.setClassLoader(MockContextProvider.class.getClassLoader());
        PortalClassLoaderUtil.setClassLoader(MockContextProvider.class.getClassLoader());
        ConfigurationFactoryUtil.setConfigurationFactory(new ConfigurationFactoryImpl());
        DBFactoryUtil.setDBFactory(new DBFactoryImpl());
        CacheRegistryUtil.setCacheRegistry(new CacheRegistryImpl());
        (new MappingSqlQueryFactoryUtil()).setMappingSqlQueryFactory(new MappingSqlQueryFactoryImpl());

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
        _log.info("Running SQL scripts");

        String tablesSQL = IOUtils.toString(new FileReader(new File(getPathPrefix() + "/src/main/webapp/WEB-INF/sql/tables.sql")));
        String sequencesSQL = IOUtils.toString(new FileReader(new File(getPathPrefix() + "/src/main/webapp/WEB-INF/sql/sequences.sql")));
        String indexesSQL = IOUtils.toString(new FileReader(new File(getPathPrefix() + "/src/main/webapp/WEB-INF/sql/indexes.sql")));
        db.runSQLTemplateString(tablesSQL, true, false);
        db.runSQLTemplateString(sequencesSQL, true, false);
        db.runSQLTemplateString(indexesSQL, true, false);
    }

    public void setDataSource(DataSource dataSource) {
        new InfrastructureUtil().setDataSource(dataSource);
    }

    /**
     * When launching the tests directly from IntelliJ IDEA, the base path for files is set to be $xcolab instead of the portlets folder - which is default in mvn test. This function checks which one is the case and returns a prefix that can be used in either case to make the tests work.
     *
     * @return
     */
    private String getPathPrefix() {
        //check "." is xcolab base directory
        String[] xcolabDirs = {"portlets", "conf", "hooks", "layouts", "services"};
        boolean assumption = true;
        for (String dir : xcolabDirs) {
            if (!new File("./" + dir).exists()) {
                assumption = false;
                break;
            }
        }

        if (assumption) {
            return "./services/plansProposalsFacade/plansProposalsFacade-portlet";
        } else {
            return ".";
        }
    }

    private final static Log _log = LogFactoryUtil.getLog(MockContextProvider.class);
}
