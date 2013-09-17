package com.ext.portlet.service.impl;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalService;
import com.liferay.portal.dao.jdbc.DataSourceFactoryImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.MockContextProvider;
import com.liferay.portal.spring.aop.ServiceBeanAutoProxyCreator;
import com.liferay.portal.spring.context.ArrayApplicationContext;

public class ProposalLocalServiceImplTest {
	private ProposalLocalService proposalLocalServiceImpl;
	private AbstractApplicationContext ctx;
	
	
	@Before
	public void beforeTest() throws Exception {
	    //new ActivitySubscriptionPersistenceImpl();
	    
	    //System.out.println("after");
	    new DataSourceFactoryImpl();
	    //InitUtil.initWithSpring();
	    
	    new ServiceBeanAutoProxyCreator();
	    new MockContextProvider();
	    
	    
	    ctx = ctx = new ArrayApplicationContext(new String[] { 
	            "META-INF/test-spring.xml",
	            "META-INF/counter-spring.xml",
	            "META-INF/hibernate-spring.xml", "META-INF/base-spring.xml",
	            "META-INF/portlet-spring.xml", "META-INF/infrastructure-spring.xml", "META-INF/util-spring.xml", "META-INF/management-spring.xml",
	            "META-INF/portlet-spring-override.xml"});
        
		proposalLocalServiceImpl = ctx.getBean(ProposalLocalService.class);
		//proposalLocalServiceImpl.setPortalServicesHelper(new PortalServicesHelperMock());
		//proposalLocalServiceImpl.setProposalPersistence(new ProposalPersistenceImplMock());
		
		//proposalLocalServiceImpl.set
		
		
	}

    @Test
    public void testProposalCreation() throws SystemException, PortalException {
        Proposal p = proposalLocalServiceImpl.create(1234L);
        assertEquals(1234L, p.getAuthorId());
        
        Proposal rawFromDb = proposalLocalServiceImpl.getProposal(p.getProposalId());
        assertEquals(rawFromDb.getProposalId(), p.getProposalId());
        assertFalse(rawFromDb == p);
        
        assertEquals(rawFromDb, p);
        assertEquals(rawFromDb.getCreateDate(), rawFromDb.getCreateDate());
        assertEquals(rawFromDb.getVisible(), p.getVisible());
        assertEquals(rawFromDb.getCreateDate(), p.getCreateDate());
        assertEquals(rawFromDb.getAuthorId(), p.getAuthorId());
        
        
    }

}
