package org.xcolab.service.contents.domain.contentarticle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.model.tables.pojos.ContentArticle;
import org.xcolab.service.contents.exceptions.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.contents")
@ActiveProfiles("test")
public class ContentArticleDaoTest {

    @Autowired
    private ContentArticleDao contentArticleDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldGetArticlesInFolder() throws Exception {

        List<? extends ContentArticle> az = contentArticleDao.getArticlesInFolder(5L);

        assertEquals(2,az.size());
    }

    @Test
    public void shouldGetArticles() throws Exception {
        List<? extends ContentArticle> az = contentArticleDao.getArticles();
        assertEquals(3,az.size());
    }

    @Test
    public void shouldCreateNewContentArticle() throws Exception {

        ContentArticle ae = new ContentArticle();
        ae = contentArticleDao.create(ae);
        assertNotNull(contentArticleDao.get(ae.getId()));
    }

    @Test
    public void shouldGetContentArticle() throws Exception {

        ContentArticle ae = contentArticleDao.get(2L);

        assertNotNull(contentArticleDao.get(ae.getId()));
    }

    @Test
    public void shouldDeleteContentArticle() throws Exception {

        ContentArticle ae = new ContentArticle();
        ae = contentArticleDao.create(ae);
        assertTrue(contentArticleDao.delete(ae.getId()) == 1);
        thrown.expect(NotFoundException.class);
        assertNotNull(contentArticleDao.get(ae.getId()));
    }

    @Test
    public void shouldUpdateContentArticle() throws Exception {
        ContentArticle ae = new ContentArticle();
        ae.setAuthorUserId(3L);
        ae = contentArticleDao.create(ae);
        ae.setAuthorUserId(1L);
        contentArticleDao.update(ae);
        ContentArticle az = contentArticleDao.get(ae.getId());
        assertEquals(az.getAuthorUserId(), ae.getAuthorUserId());
    }
}
