package org.xcolab.service.contents.domain.contentfolder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.service.contents.domain.contentFolder.ContentFolderDao;
import org.xcolab.service.utils.PaginationHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(
        properties = {
                "cache.enabled=false",
                "eureka.client.enabled=false",
                "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL"
        }
)
@ComponentScan("org.xcolab.service.contents")

public class ContentFolderDaoTest {

    @Autowired
    ContentFolderDao contentFolderDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateNewContentFolder() throws Exception {

        ContentFolder ae = new ContentFolder();
        ae.setContentFolderName("upper");
        ae.setContentFolderDescription("");
        ae.setParentFolderId(0L);
        ae = contentFolderDao.create(ae);
        assertNotNull(contentFolderDao.get(ae.getContentFolderId()));

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
        ContentFolder ae = new ContentFolder();
        ae.setContentFolderName("nuper");
        ae.setContentFolderDescription("");
        ae.setParentFolderId(0L);
        ae = contentFolderDao.create(ae);
        ae.setContentFolderName("super");
        contentFolderDao.update(ae);
        ContentFolder az = contentFolderDao.get(ae.getContentFolderId());
        assertEquals(az.getContentFolderName(),ae.getContentFolderName());

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
