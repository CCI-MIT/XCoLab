package org.xcolab.service.content.domain.contentarticleversion;

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

import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.model.tables.pojos.ContentArticleVersionImpl;
import org.xcolab.service.content.exceptions.NotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.content")
@ActiveProfiles("test")
public class ContentArticleVersionDaoTest {

    @Autowired
    private ContentArticleVersionDao contentArticleVersionDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateNewContentArticleVersion() throws Exception {
        IContentArticleVersion ae = new ContentArticleVersionImpl();
        ae = contentArticleVersionDao.create(ae);
        assertNotNull(contentArticleVersionDao.get(ae.getId()));
    }

    @Test
    public void shouldGetContentArticleVersion() throws Exception {
        assertNotNull(contentArticleVersionDao.get(1567L));
    }

    @Test
    public void shouldGetByFolderId() throws Exception {
        IContentArticleVersion ae = new ContentArticleVersionImpl();
        ae.setId(1000L);
        ae.setFolderId(200L);
        ae = contentArticleVersionDao.create(ae);

        assertNotNull(contentArticleVersionDao.getByFolderId(ae.getFolderId()));
    }

    @Test
    public void shouldReturnAllOnGetByFolderId() throws Exception {
        assertNotNull(contentArticleVersionDao.getByFolderId(null));
    }

    @Test
    public void shouldReturnEmptyForOnGetByFolderIdOnInexistentFolder() throws Exception {
        assertNotNull(contentArticleVersionDao.getByFolderId(1233L));
    }

    @Test
    public void shouldDeleteContentArticleVersionByArticleId() throws Exception {
        final long articleId = 1000L;
        IContentArticleVersion ae = new ContentArticleVersionImpl();
        ae.setArticleId(articleId);
        ae = contentArticleVersionDao.create(ae);

        assertEquals(1, contentArticleVersionDao.deleteByArticleId(articleId));
        thrown.expect(NotFoundException.class);
        assertNotNull(contentArticleVersionDao.get(ae.getId()));
    }

    @Test
    public void shouldUpdateContentArticle() throws Exception {
        IContentArticleVersion ae = new ContentArticleVersionImpl();
        ae.setAuthorUserId(3L);
        ae = contentArticleVersionDao.create(ae);
        ae.setAuthorUserId(1L);
        contentArticleVersionDao.update(ae);
        IContentArticleVersion az = contentArticleVersionDao.get(ae.getId());
        assertEquals(az.getAuthorUserId(), ae.getAuthorUserId());
    }
}
