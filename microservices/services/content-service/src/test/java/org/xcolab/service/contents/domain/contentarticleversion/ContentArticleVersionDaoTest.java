package org.xcolab.service.contents.domain.contentarticleversion;

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

import org.xcolab.model.tables.pojos.ContentArticleVersion;
import org.xcolab.service.contents.exceptions.NotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(
        properties = {
                "cache.active=false",
                "eureka.client.enabled=false",
                "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL"
        }
)
@ComponentScan("org.xcolab.service.contents")
public class ContentArticleVersionDaoTest {

    @Autowired
    ContentArticleVersionDao contentArticleVersionDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldCreateNewContentArticleVersion() throws Exception {

        ContentArticleVersion ae = new ContentArticleVersion();
        ae = contentArticleVersionDao.create(ae);
        assertNotNull(contentArticleVersionDao.get(ae.getContentArticleVersionId()));

    }

    @Test
    public void shouldGetContentArticleVersion() throws Exception {

        assertNotNull(contentArticleVersionDao.get(1567l));

    }

    @Test
    public void shouldGetByFolderId() throws Exception {

        ContentArticleVersion ae = new ContentArticleVersion();
        ae.setContentArticleId(1000l);
        ae.setFolderId(200l);
        ae = contentArticleVersionDao.create(ae);

        assertNotNull(contentArticleVersionDao.getByFolderId(ae.getFolderId()));
    }

    @Test
    public void shouldReturnAllOnGetByFolderId() throws Exception {
        assertNotNull(contentArticleVersionDao.getByFolderId(null));
    }

    @Test
    public void shouldReturnEmptyForOnGetByFolderIdOnInexistentFolder() throws Exception {
        assertNotNull(contentArticleVersionDao.getByFolderId(1233l));
    }


    @Test
    public void shouldDeleteContentArticleVersionByArticleId() throws Exception {

        ContentArticleVersion ae = new ContentArticleVersion();
        ae.setContentArticleId(1000l);
        ae = contentArticleVersionDao.create(ae);

        assertTrue(contentArticleVersionDao.deleteByArticleId(ae.getContentArticleId())==1);
        thrown.expect(NotFoundException.class);
        assertNotNull(contentArticleVersionDao.get(ae.getContentArticleVersionId()));

    }

    @Test
    public void shouldUpdateContentArticle() throws Exception {
        ContentArticleVersion ae = new ContentArticleVersion();
        ae.setAuthorId(03l);
        ae = contentArticleVersionDao.create(ae);
        ae.setAuthorId(01l);
        contentArticleVersionDao.update(ae);
        ContentArticleVersion az = contentArticleVersionDao.get(ae.getContentArticleVersionId());
        assertEquals(az.getAuthorId(),ae.getAuthorId());

    }
}
