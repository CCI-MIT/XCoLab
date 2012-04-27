package org.xcolab.services.proposalsService.service;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.spring.hibernate.PortletHibernateConfiguration;

public class PortletHibernateTestConfiguration extends
		PortletHibernateConfiguration {
	
	protected ClassLoader getConfigurationClassLoader() {
        System.out.println(">>>>jack configuration hibernate");
         return this.getClass().getClassLoader();
        }
   
    protected String[] getConfigurationResources() {
        String[] configs = PropsUtil.getArray(PropsKeys.HIBERNATE_CONFIGS);
        for(String config : configs){
        System.out.println(config);
        }
        return configs;
        }
}
