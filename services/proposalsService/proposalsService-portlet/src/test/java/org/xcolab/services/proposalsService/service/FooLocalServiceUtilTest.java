package org.xcolab.services.proposalsService.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.xcolab.services.proposalsService.model.impl.FooImpl;

import com.liferay.portal.bean.BeanLocatorImpl;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class FooLocalServiceUtilTest {

	@Test
	public void test() throws PortalException, SystemException {
		try {
		System.setProperty("log4j.configure.on.startup", "false");
		
		System.out.println(System.getProperty("log4j.configure.on.startup"));
		
		Thread currentThread = Thread.currentThread();
		
		ClassLoader contextClassLoader =
			currentThread.getContextClassLoader();
		
		//Class c = contextClassLoader.loadClass("org.xcolab.services.service.impl.FooLocalServiceImpl");
		                    
		//System.out.println(c);
		
		com.liferay.portal.util.InitUtil.initWithSpring(true);

		
		System.out.println(currentThread);
		System.out.println(contextClassLoader);
		//ApplicationContext applicationContext =
	    //        WebApplicationContextUtils.getWebApplicationContext(servletContext);

	    //    BeanLocator beanLocator = new BeanLocatorImpl(contextClassLoader, applicationContext);
		
		
		PortletBeanLocatorUtil.setBeanLocator("proposalsService-portlet", PortalBeanLocatorUtil.getBeanLocator());
		
		FooImpl foo = new FooImpl();
		foo.setFooId(0L);
		foo.setField1("fasdfas");
		foo.setField2(false);
		foo.setField3(4432);
		foo.setField4(new Date());
		foo.setCreateDate(new Date());
		foo.setField5("field 5");
		
		FooLocalServiceUtil.addFoo(foo);
		

		FooLocalServiceUtil.getFoo(0L);
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	

	@Test
	public void testSuccess() {
	}

}
