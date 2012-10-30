package org.xcolab.core.documententity.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.DocumentEntityServiceUtil;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

public class DocumentEntityServiceImplTest {

	@Test
	public void test() throws DocumentEntityException {
/*		System.setProperty("log4j.configure.on.startup", "false");
		
		System.out.println(System.getProperty("log4j.configure.on.startup"));
		*/
		
		Thread currentThread = Thread.currentThread();
		
		ClassLoader contextClassLoader =
			currentThread.getContextClassLoader();
		
		//Class c = contextClassLoader.loadClass("org.xcolab.services.service.impl.FooLocalServiceImpl");
		                    
		//System.out.println(c);
		
		//com.liferay.portal.util.InitUtil.initWithSpring(true);

		
		System.out.println(currentThread);
		System.out.println(contextClassLoader);
		//ApplicationContext applicationContext =
	    //        WebApplicationContextUtils.getWebApplicationContext(servletContext);

	    //    BeanLocator beanLocator = new BeanLocatorImpl(contextClassLoader, applicationContext);
		
		
		//PortletBeanLocatorUtil.setBeanLocator("xcolab-core-service-portlet", PortalBeanLocatorUtil.getBeanLocator());
		//System.out.print(DocumentEntityServiceUtil.findDocumentEntities("abc"));
		DocumentEntityServiceImpl svc = new DocumentEntityServiceImpl();
		svc.init();
		long prev = System.currentTimeMillis();
		
		DocumentEntity entity = svc.createEntity("/proposals");
		for (int i=0; i < 1000; i++) {
			entity = svc.createEntity("/proposals");
			System.out.print(i + " " + (System.currentTimeMillis() - prev) + "\t");
			prev = System.currentTimeMillis();
			
		}
		
		System.out.println(entity);
		
		List<DocumentEntity> entities = svc.findDocumentEntities("/proposals");
		System.out.println(entities);
		
	}

}
