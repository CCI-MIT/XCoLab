package org.xcolab.service.content.domain.contentfolder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.content.pojo.IContentFolder;
import org.xcolab.model.tables.pojos.ContentFolderImpl;
import org.xcolab.service.utils.PaginationHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.content")
@ActiveProfiles("test")
public class ContentFolderDaoTest {

    @Autowired
    ContentFolderDao contentFolderDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateNewContentFolder() throws Exception {
        IContentFolder ae = new ContentFolderImpl();
        ae.setName("upper");
        ae.setDescription("");
        ae.setParentFolderId(0L);
        ae = contentFolderDao.create(ae);
        assertNotNull(contentFolderDao.get(ae.getId()));
    }

    @Test
    public void shouldGetContentFolder() throws Exception {
        assertNotNull(contentFolderDao.get(2L));
    }

    @Test
    public void shouldFindByAncestorFolderId() throws Exception {
        assertEquals(1,contentFolderDao.findByAncestorFolderId(2L).size());
    }

    @Test
    public void shouldUpdateContentFolder() throws Exception {
        IContentFolder ae = new ContentFolderImpl();
        ae.setName("nuper");
        ae.setDescription("");
        ae.setParentFolderId(0L);
        ae = contentFolderDao.create(ae);
        ae.setName("super");
        contentFolderDao.update(ae);
        IContentFolder az = contentFolderDao.get(ae.getId());
        assertEquals(az.getName(), ae.getName());
    }

    @Test
    public void shouldFindByParentFolderIdInAnyOrder() throws Exception {

        assertEquals(contentFolderDao.findByGiven(new PaginationHelper(0,
                Integer.MAX_VALUE,"contentFolderName"), 2L).size(),1);
        assertEquals(contentFolderDao.findByGiven(new PaginationHelper(0,
                Integer.MAX_VALUE,"parentFolderId"), 2L).size(),1);
        assertEquals(contentFolderDao.findByGiven(new PaginationHelper(0,
                Integer.MAX_VALUE,"contentFolderId"), 2L).size(),1);
    }
}
