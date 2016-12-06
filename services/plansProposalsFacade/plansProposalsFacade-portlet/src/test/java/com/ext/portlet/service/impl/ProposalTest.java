package com.ext.portlet.service.impl;


import org.junit.Test;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.services.EventBusService;

import java.util.Random;

public class ProposalTest extends XCoLabTest {
	private Random rand = new Random();

    @BeanReference(type = EventBusService.class)
    private EventBusService eventBus;


    @Test
    public void testProposalCreation() throws SystemException, PortalException {
//        long authorId = 10144L;
//        System.out.println(ContestPhaseLocalServiceUtil.getContestPhases(0, Integer.MAX_VALUE));
//        List<User> users = UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE);
//        System.out.println(users);
//
//        PermissionCheckerUtil.setThreadValues(UserLocalServiceUtil.getUser(authorId));
//        Proposal p = proposalLocalService.create(authorId, 1);
//        assertEquals(authorId, p.getAuthorId());
//
//        Proposal rawFromDb = proposalLocalService.getProposal(p.getProposalId());
//        assertEquals(rawFromDb.getProposalId(), p.getProposalId());
//        assertFalse(rawFromDb == p);
//
//        assertEquals(rawFromDb, p);
//        assertEquals(rawFromDb.getCreateDate(), rawFromDb.getCreateDate());
//        assertEquals(rawFromDb.getVisible(), p.getVisible());
//        assertEquals(rawFromDb.getCreateDate(), p.getCreateDate());
//        assertEquals(rawFromDb.getAuthorId(), p.getAuthorId());
    }
    
    @Test
    public void testAttributeSetting() throws PortalException, SystemException {
    	
//        long authorId = 10144L;
//        PermissionCheckerUtil.setThreadValues(UserLocalServiceUtil.getUser(authorId));
//
//        String attributeName = "NAME";
//        long attributeAdditionalId = 0;
//        String attributeStringVal = "test value" + rand.nextLong();
//        long attributeNumericVal = rand.nextLong();
//        double attributeRealVal = rand.nextDouble();
//
//        Proposal proposal = proposalLocalService.create(authorId, 1);
//
//        ProposalAttribute attribute = proposalAttributeLocalService.setAttribute(
//                authorId,
//                proposal.getProposalId(),
//                attributeName,
//                attributeAdditionalId,
//                attributeStringVal,
//                attributeNumericVal,
//                attributeRealVal);
//
//        ProposalAttribute fetchedAttribute = proposalAttributeLocalService.getAttribute(proposal.getProposalId(), attributeName, attributeAdditionalId);
//
//        assertEquals(attribute, fetchedAttribute);
//
//        assertEquals(attributeName, fetchedAttribute.getName());
//        assertEquals(attributeStringVal, fetchedAttribute.getStringValue());
//        assertEquals(attributeNumericVal, fetchedAttribute.getNumericValue());
//        assertEquals(attributeRealVal, fetchedAttribute.getRealValue(), 0.001);
//
//        assertEquals(proposal.getCurrentVersion() + 1, fetchedAttribute.getVersion());
        
    }
    
    @Test
    public void testProposalVersionHistory() throws PortalException, SystemException {
//        long authorId = 10144L;
//        PermissionCheckerUtil.setThreadValues(UserLocalServiceUtil.getUser(authorId));
//        ProposalAttributeValues[] valuesToSet = new ProposalAttributeValues[] {
//                new ProposalAttributeValues(authorId, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
//                new ProposalAttributeValues(authorId, "DESCRIPTION", 0, "test description" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
//                new ProposalAttributeValues(authorId, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
//                new ProposalAttributeValues(authorId, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
//                new ProposalAttributeValues(authorId, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
//                new ProposalAttributeValues(authorId, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble()),
//                new ProposalAttributeValues(authorId, "NAME", 0, "test name" + rand.nextLong(), rand.nextLong(), rand.nextDouble())
//        };
//
//        Proposal proposal = proposalLocalService.create(authorId, 1);
//
//        for (ProposalAttributeValues valueToSet: valuesToSet) {
//            proposalAttributeLocalService.setAttribute(valueToSet.authorId, proposal.getProposalId(),
//                    valueToSet.attributeName, valueToSet.additionalId, valueToSet.stringValue,
//                    valueToSet.numericValue, valueToSet.realValue);
//        }
//
//        for (int i=0; i < valuesToSet.length; i++) {
//            int version = i+1;
//
//            ProposalVersion proposalVersion = proposalLocalService.getProposalVersion(proposal.getProposalId(), version);
//            assertEquals(valuesToSet[i].authorId, proposalVersion.getAuthorId());
//            assertEquals(valuesToSet[i].attributeName, proposalVersion.getUpdateType());
//            assertEquals(valuesToSet[i].additionalId, proposalVersion.getUpdateAdditionalId());
//
//
//            List<ProposalAttribute> proposalVersionAttributes = proposalAttributeLocalService.getAttributes(proposal.getProposalId(), version);
//
//            for (ProposalAttribute attribute: proposalVersionAttributes) {
//                // find the value for current attribute in values to set by iterating from the beginning
//                ProposalAttributeValues expectedValues = null;
//                for (int k = 0; k <= i; k++) {
//                    if (valuesToSet[k].attributeName.equals(attribute.getName()) && valuesToSet[k].additionalId == attribute.getAdditionalId()) {
//                        expectedValues = valuesToSet[k];
//                    }
//                }
//                assertFalse("Expected values has to be set as all attributes set in the past should be reflected in current version", expectedValues == null);
//
//                assertEquals(expectedValues.stringValue, attribute.getStringValue());
//                assertEquals(expectedValues.numericValue, attribute.getNumericValue());
//                assertEquals(expectedValues.realValue, attribute.getRealValue(), 0.001);
//            }
//
//            // check if we have correct number of attribtes for this version;
//            Set<String> differentAttributes = new HashSet<>();
//            for (int k=0; k <= i; k++) {
//                differentAttributes.add(valuesToSet[k].attributeName + "_" + valuesToSet[k].additionalId);
//            }
//            assertEquals(differentAttributes.size(), proposalVersionAttributes.size());
//        }
//
//        proposal = proposalLocalService.getProposal(proposal.getProposalId());
//        assertEquals(valuesToSet.length, proposal.getCurrentVersion());
//
//
    }
    
//    private class ProposalAttributeValues {
//        long authorId;
//        String attributeName;
//        long additionalId;
//        String stringValue;
//        long numericValue;
//        double realValue;
//
//        public ProposalAttributeValues(long authorId, String attributeName, long additionalId, String stringValue, long numericValue, double realValue) {
//            super();
//            this.authorId = authorId;
//            this.attributeName = attributeName;
//            this.stringValue = stringValue;
//            this.numericValue = numericValue;
//            this.realValue = realValue;
//            this.additionalId = additionalId;
//        }
//
//        @Override
//        public String toString() {
//            return "ProposalAttributeValues [authorId=" + authorId + ", attributeName=" + attributeName
//                    + ", additionalId=" + additionalId + ", stringValue=" + stringValue + ", numericValue="
//                    + numericValue + ", realValue=" + realValue + "]";
//        }
//

}
