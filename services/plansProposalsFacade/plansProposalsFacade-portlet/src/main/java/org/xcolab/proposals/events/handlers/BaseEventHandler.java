package org.xcolab.proposals.events.handlers;

import org.xcolab.services.EventBusService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.GroupLocalService;

public abstract class BaseEventHandler {

    private static final String DEFAULT_GROUP_NAME = "Guest";

    @BeanReference(type = EventBusService.class) 
    private EventBusService eventBus;

    @BeanReference(type = CompanyLocalService.class) 
    private CompanyLocalService companyLocalService;
    
    @BeanReference(type = GroupLocalService.class) 
    private GroupLocalService groupLocalService;
    
    public void afterPropertiesSet() {
        eventBus.register(this);
    }
    
    public Company getDefaultCompany() throws PortalException, SystemException {
        
        return companyLocalService.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
    }
    
    public Group getDefaultGroup() throws PortalException, SystemException {
        return groupLocalService.getGroup(getDefaultCompany().getCompanyId(), DEFAULT_GROUP_NAME);
    }
    
}
