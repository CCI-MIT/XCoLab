package com.ext.portlet.service.impl;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.ext.portlet.service.impl.mock.PortalServicesHelperMock;
import com.ext.portlet.service.impl.mock.ProposalPersistenceImplMock;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class ProposalLocalServiceImplTest {
	private ProposalLocalServiceImpl proposalLocalServiceImpl;
	private ApplicationContext ctx;
	
	
	@Before
	public void beforeTest() throws Exception {
	    
	    //ctx = ctx = new ClassPathXmlApplicationContext(new String[] {"META-INF/portlet-spring.xml"});
		proposalLocalServiceImpl = new ProposalLocalServiceImpl(new PortalServicesHelperMock());
		//proposalLocalServiceImpl.setPortalServicesHelper(new PortalServicesHelperMock());
		//proposalLocalServiceImpl.setProposalPersistence(new ProposalPersistenceImplMock());
		
		//proposalLocalServiceImpl.set
	}

    @Test
    public void testProposalCreation() throws SystemException, PortalException {
        try {
            //proposalLocalServiceImpl.create(0);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
