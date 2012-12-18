package org.xcolab.core.documententity.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.EntityVersion;

public class DocumentEntityImplVersionTest {
    private DocumentEntityServiceImpl svc = new DocumentEntityServiceImpl();
    
    @Before
    public void setUpSvc() {
        svc.init();
    }

    @Test
    public void testGetVersion() throws DocumentEntityException {

        DocumentEntity entity = svc.createEntity("/proposals");
        
        for (int i=0; i < 10; i++) {
            entity = svc.createEntity("/proposals");
            
            for (int j=0; j < 10; j++) {
                entity.setLong("testVal", (long) j);
                svc.update(entity);
            }
        }
        
        System.out.println(entity.getVersion());
        int i = 0;
        DocumentEntity firstVersion = null;
        for (EntityVersion<DocumentEntity> ev: svc.getVersions(entity)) {
            if (firstVersion == null) firstVersion = ev.getEntity();
            
            Assert.assertFalse(i < 10 ? ev.isLatest() : false);
            Assert.assertFalse(i > 0 ? ev.isFirst() : false);
            if (ev.getEntity().hasProperty("testVal")) {
                
                Assert.assertEquals(Long.valueOf(i-1), ev.getEntity().getLong("testVal"));
            }
            i++;
        }
        
        i = 0;
        EntityVersion<DocumentEntity> versionTraveler = firstVersion.getVersion();
        
        Assert.assertTrue(versionTraveler.isFirst());
        Assert.assertFalse(versionTraveler.isLatest());
        
        while (! versionTraveler.isLatest()) {
            versionTraveler = versionTraveler.getNext();
            if (versionTraveler.getEntity().hasProperty("testVal")) {
                Assert.assertEquals(Long.valueOf(i), versionTraveler.getEntity().getLong("testVal"));
                i++;
            }
        }
        
        Assert.assertFalse(versionTraveler.isFirst());
        Assert.assertTrue(versionTraveler.isLatest());
    }

}
