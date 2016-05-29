package com.liferay.portal.service;

import com.liferay.portal.bean.BeanLocatorImpl;
import com.liferay.portal.cache.CacheRegistryImpl;
import com.liferay.portal.configuration.ConfigurationFactoryImpl;
import com.liferay.portal.dao.db.DBFactoryImpl;
import com.liferay.portal.dao.jdbc.DataSourceFactoryImpl;
import com.liferay.portal.dao.jdbc.spring.MappingSqlQueryFactoryImpl;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataSourceFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.sender.MessageSender;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.lang.DoPrivilegedUtil;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PropsImpl;
import com.sun.syndication.io.XmlReader;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * <p>
 * Creates and initializes all beans that need to be set up for tests to run,
 * also it initializes database.
 * </p>
 *
 * @author janusz
 */
public class MockContextProvider implements ApplicationContextAware {
	public MockContextProvider() {
		PortletClassLoaderUtil.setClassLoader(MockContextProvider.class
				.getClassLoader());
		PortalClassLoaderUtil.setClassLoader(MockContextProvider.class
				.getClassLoader());
		ConfigurationFactoryUtil
				.setConfigurationFactory(new ConfigurationFactoryImpl());
		DBFactoryUtil.setDBFactory(new DBFactoryImpl());
		CacheRegistryUtil.setCacheRegistry(new CacheRegistryImpl());
		(new MappingSqlQueryFactoryUtil())
				.setMappingSqlQueryFactory(new MappingSqlQueryFactoryImpl());
		// Cache registry

		CacheRegistryUtil.setCacheRegistry(DoPrivilegedUtil
				.wrap(new CacheRegistryImpl()));

		// Configuration factory

		ConfigurationFactoryUtil.setConfigurationFactory(DoPrivilegedUtil
				.wrap(new ConfigurationFactoryImpl()));

		// Data source factory

		DataSourceFactoryUtil.setDataSourceFactory(DoPrivilegedUtil
				.wrap(new DataSourceFactoryImpl()));

		// DB factory

		DBFactoryUtil.setDBFactory(DoPrivilegedUtil.wrap(new DBFactoryImpl()));

		// ROME

		XmlReader.setDefaultEncoding(StringPool.UTF8);
		com.liferay.portal.kernel.util.PropsUtil.setProps(new PropsImpl());

	}

	public static ClassLoader getClassLoader() {
		return MockContextProvider.class.getClassLoader();
	}

	public static String getServletContextName() {
		return "mockServletContext";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		PortalBeanLocatorUtil.setBeanLocator(new BeanLocatorImpl(
				getClassLoader(), applicationContext));
		PortletBeanLocatorUtil.setBeanLocator("plansProposalsFacade-portlet",
				new BeanLocatorImpl(getClassLoader(), applicationContext));

	}

	public void afterPropertiesSet() throws FileNotFoundException, IOException,
			NamingException, SQLException {

		DB db = DBFactoryUtil.getDB();
		_log.info("Running SQL scripts");

		String[] dbFiles = { "/src/test/resources/sql/portal-tables.sql",
				"/src/test/resources/sql/indexes.sql",
				"/src/test/resources/sql/sequences.sql",
				"/src/main/webapp/WEB-INF/sql/tables.sql",
				"/src/main/webapp/WEB-INF/sql/sequences.sql",
				"/src/main/webapp/WEB-INF/sql/indexes.sql",
				"/src/test/resources/sql/test-data.sql" };

		for (String dbFile : dbFiles) {
			String sqlStr = IOUtils.toString(new FileReader(new File(
					getPathPrefix(), dbFile)));
			db.runSQLTemplateString(sqlStr, true, false);
		}
		PortalInstances.addCompanyId(10112L);

		MessageBus messageBus = (MessageBus) PortalBeanLocatorUtil
				.locate(MessageBus.class.getName());
		MessageSender messageSender = (MessageSender) PortalBeanLocatorUtil
				.locate(MessageSender.class.getName());
		SynchronousMessageSender synchronousMessageSender = (SynchronousMessageSender) PortalBeanLocatorUtil
				.locate(SynchronousMessageSender.class.getName());

		MessageBusUtil
				.init(messageBus, messageSender, synchronousMessageSender);

	}

	public void setDataSource(DataSource dataSource) {
		new InfrastructureUtil().setDataSource(dataSource);
	}

	/**
	 * When launching the tests directly from IntelliJ IDEA, the base path for
	 * files is set to be $xcolab instead of the portlets folder - which is
	 * default in mvn test. This function checks which one is the case and
	 * returns a prefix that can be used in either case to make the tests work.
	 *
	 * @return
	 */
	private String getPathPrefix() {
		// check "." is xcolab base directory
		String[] xcolabDirs = { "portlets", "conf", "hooks", "layouts",
				"services" };
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

	private final static Log _log = LogFactoryUtil
			.getLog(MockContextProvider.class);
}
