package org.xcolab.core.documententity.impl;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;

public class DocumentEntityImplTest {
    private DocumentEntityServiceImpl svc = new DocumentEntityServiceImpl();
    
    @Before
    public void setUpSvc() {
        svc.init();
    }
    
    @Test
    public void testCreation() throws DocumentEntityException {
        DocumentEntity entity = svc.createEntity("/test");
        
        entity.setString("stringProperty", "StringValue");
        svc.update(entity);
        
        Object id = entity.getId();
        
        entity = svc.findDocumentEntity(id);
        
        Assert.assertEquals("StringValue", entity.getString("stringProperty"));
    }
    
    @Test
    public void testMultiValuedProperties() throws DocumentEntityException {
        String[] valuesToSet = new String[] {"1", "2", "3"};
        DocumentEntity entity = svc.createEntity("/test");
        Object id = entity.getId();
        
        
        entity.setStrings("stringValues", valuesToSet);
        svc.update(entity);

        entity = svc.findDocumentEntity(id);
        
        String[] valuesFetched = entity.getStrings("stringValues");
        Assert.assertArrayEquals(valuesToSet, valuesFetched);
    }
    
    
    @Test
    public void testAdditionOfElements() throws DocumentEntityException {
        String[] valuesToSet = new String[] {"1", "2", "3"};
        String[] targetCollection = new String[] {"1", "2", "3", "4", "5"};
        String propertyName = "stringValues";
        
        DocumentEntity entity = svc.createEntity("/test");
        Object id = entity.getId();
        
        
        entity.setStrings(propertyName, valuesToSet);
        entity.addString(propertyName, "4");
        entity.addString(propertyName, "5");
        
        svc.update(entity);

        entity = svc.findDocumentEntity(id);
        
        String[] valuesFetched = entity.getStrings(propertyName);
        Assert.assertArrayEquals(targetCollection, valuesFetched);
    }
    
    @Test
    public void testReferences() throws DocumentEntityException {
        String propertyName = "entRef";
        
        DocumentEntity entity = svc.createEntity("/test");
        DocumentEntity entity2 = svc.createEntity("/test");
        
        svc.update(entity);
        svc.update(entity2);
        
        entity.addReference(propertyName, entity2);
        svc.update(entity);
        
        Assert.assertEquals(entity2.getId(), entity.getReference(propertyName).getId());
        DocumentEntity entity3 = svc.createEntity("/test");
        svc.update(entity3);
        
        entity.addReference(propertyName, entity3);
        svc.update(entity);
        
        DocumentEntity[] entities = entity.getReferences(propertyName);
        
        Assert.assertEquals(entity2.getId(), entities[0].getId());
        Assert.assertEquals(entity3.getId(), entities[1].getId());
        
    }
    
    
}
