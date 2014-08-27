package com.ext.portlet.service.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.xcolab.services.EventBusService;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ProposalLocalService;
import com.liferay.portal.dao.jdbc.DataSourceFactoryImpl;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.service.MockContextProvider;
import com.liferay.portal.spring.aop.ServiceBeanAutoProxyCreator;
import com.liferay.portal.spring.context.ArrayApplicationContext;
import com.liferay.portal.util.InitUtil;

public class ProposalLocalServiceImplTest {
	private static ProposalLocalService proposalLocalServiceImpl;
	private static AbstractApplicationContext ctx;
	private Random rand = new Random();

    @BeanReference(type = EventBusService.class)
    private EventBusService eventBus;
	
	@BeforeClass
	public static void beforeTest() throws Exception {
	    
	    new DataSourceFactoryImpl();
	    
	    new ServiceBeanAutoProxyCreator();
	    new MockContextProvider();
	    System.out.println("Before init");
	    //InitUtil.init();
	    System.out.println("after init with spring before ctx");
	    ctx = new ArrayApplicationContext(new String[] { 
	            "META-INF/test-spring.xml", 
	            "META-INF/util-spring.xml", 
	            "META-INF/counter-spring.xml",
	            "META-INF/hibernate-spring.xml",
	            "META-INF/base-spring.xml", "META-INF/ext-spring.xml",
	            "META-INF/portlet-spring.xml", "META-INF/infrastructure-spring.xml", "META-INF/management-spring.xml",
	            "META-INF/portlet-spring-override.xml"});
	    /*
	    ctx = new ArrayApplicationContext(new String[] {
	            "META-INF/counter-spring.xml",
	            "META-INF/hibernate-spring.xml",
	            "META-INF/infrastructure-spring.xml",
	            "META-INF/test-spring.xml"
	    });
	    */
	    
        System.out.println("initialized?");
	    //proposalLocalServiceImpl = ctx.getBean(ProposalLocalService.class);
	}

    @Test
    public void testProposalCreation() throws SystemException, PortalException {
        long authorId = 1234L;
        Proposal p = proposalLocalServiceImpl.create(authorId, 0);
        assertEquals(authorId, p.getAuthorId());
        
        Proposal rawFromDb = proposalLocalServiceImpl.getProposal(p.getProposalId());
        assertEquals(rawFromDb.getProposalId(), p.getProposalId());
        assertFalse(rawFromDb == p);
        
        assertEquals(rawFromDb, p);
        assertEquals(rawFromDb.getCreateDate(), rawFromDb.getCreateDate());
        assertEquals(rawFromDb.getVisible(), p.getVisible());
        assertEquals(rawFromDb.getCreateDate(), p.getCreateDate());
        assertEquals(rawFromDb.getAuthorId(), p.getAuthorId());
    }
    
    @Test
    public void testAttributeSetting() throws PortalException, SystemException {
        long authorId = 1234L;
        
        String attributeName = "NAME";
        long attributeAdditionalId = 0;
        String attributeStringVal = "test value" + rand.nextLong();
        long attributeNumericVal = rand.nextLong();
        double attributeRealVal = rand.nextDouble();
        
        Proposal proposal = proposalLocalServiceImpl.create(authorId, 0);
        
        ProposalAttribute attribute = proposalLocalServiceImpl.setAttribute(
                authorId, 
                proposal.getProposalId(), 
                attributeName, 
                attributeAdditionalId,
                attributeStringVal, 
                attributeNumericVal, 
                attributeRealVal);
        
        ProposalAttribute fetchedAttribute = proposalLocalServiceImpl.getAttribute(proposal.getProposalId(), attributeName, attributeAdditionalId);
        
        assertEquals(attribute, fetchedAttribute);
        
        assertEquals(attributeName, fetchedAttribute.getName());
        assertEquals(attributeStringVal, fetchedAttribute.getStringValue());
        assertEquals(attributeNumericVal, fetchedAttribute.getNumericValue());
        assertEquals(attributeRealVal, fetchedAttribute.getRealValue(), 0.001);
        
        assertEquals(proposal.getCurrentVersion() + 1, fetchedAttribute.getVersion());
        
    }
    
    @Test
    public void testProposalVersionHistory() throws PortalException, SystemException {
        long authorId = 1234L;
        ProposalAttributeValues[] valuesToSet = new ProposalAttributeValues[] {
                new ProposalAttributeValues(1, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
                new ProposalAttributeValues(2, "DESCRIPTION", 0, "test description" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
                new ProposalAttributeValues(3, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
                new ProposalAttributeValues(4, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
                new ProposalAttributeValues(5, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
                new ProposalAttributeValues(6, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
                new ProposalAttributeValues(7, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble())
        };
        
        Proposal proposal = proposalLocalServiceImpl.create(authorId, 0);
        
        for (ProposalAttributeValues valueToSet: valuesToSet) {
            proposalLocalServiceImpl.setAttribute(valueToSet.authorId, proposal.getProposalId(), 
                    valueToSet.attributeName, valueToSet.additionalId, valueToSet.stringValue, 
                    valueToSet.numericValue, valueToSet.realValue);
        }
        
        for (int i=0; i < valuesToSet.length; i++) {
            int version = i+1;
            
            ProposalVersion proposalVersion = proposalLocalServiceImpl.getProposalVersion(proposal.getProposalId(), version);
            assertEquals(valuesToSet[i].authorId, proposalVersion.getAuthorId());
            assertEquals(valuesToSet[i].attributeName, proposalVersion.getUpdateType());
            assertEquals(valuesToSet[i].additionalId, proposalVersion.getUpdateAdditionalId());
            
            
            List<ProposalAttribute> proposalVersionAttributes = proposalLocalServiceImpl.getAttributes(proposal.getProposalId(), version);
            
            for (ProposalAttribute attribute: proposalVersionAttributes) {
                // find the value for current attribute in values to set by iterating from the beginning
                ProposalAttributeValues expectedValues = null;
                for (int k = 0; k <= i; k++) {
                    if (valuesToSet[k].attributeName.equals(attribute.getName()) && valuesToSet[k].additionalId == attribute.getAdditionalId()) {
                        expectedValues = valuesToSet[k];
                    }
                }
                assertFalse("Expected values has to be set as all attributes set in the past should be reflected in current version", expectedValues == null);
                
                assertEquals(expectedValues.stringValue, attribute.getStringValue());
                assertEquals(expectedValues.numericValue, attribute.getNumericValue());
                assertEquals(expectedValues.realValue, attribute.getRealValue(), 0.001);
            }
            
            // check if we have correct number of attribtes for this version;
            Set<String> differentAttributes = new HashSet<>();
            for (int k=0; k <= i; k++) {
                differentAttributes.add(valuesToSet[k].attributeName + "_" + valuesToSet[k].additionalId);
            }
            assertEquals(differentAttributes.size(), proposalVersionAttributes.size());
        }
        
        proposal = proposalLocalServiceImpl.getProposal(proposal.getProposalId());
        assertEquals(valuesToSet.length, proposal.getCurrentVersion());
        
        
    }
    
    private class ProposalAttributeValues {
        long authorId;
        String attributeName;
        long additionalId;
        String stringValue;
        long numericValue;
        double realValue;
        
        public ProposalAttributeValues(long authorId, String attributeName, long additionalId, String stringValue, long numericValue, double realValue) {
            super();
            this.authorId = authorId;
            this.attributeName = attributeName;
            this.stringValue = stringValue;
            this.numericValue = numericValue;
            this.realValue = realValue;
            this.additionalId = additionalId;
        }

        @Override
        public String toString() {
            return "ProposalAttributeValues [authorId=" + authorId + ", attributeName=" + attributeName
                    + ", additionalId=" + additionalId + ", stringValue=" + stringValue + ", numericValue="
                    + numericValue + ", realValue=" + realValue + "]";
        }
        
        
        
    }

}
