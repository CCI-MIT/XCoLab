package org.xcolab.service.contents.domain.page;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.model.tables.pojos.ContentPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.contents")
@ActiveProfiles("test")
public class ContentPageDaoTest {

    @Autowired
    private ContentPageDao contentPageDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateNewContentPage() throws Exception {

        ContentPage ae = new ContentPage();
        ae.setTitle("pagetitle");
        ae.setContentArticleId(1L);
        ae = contentPageDao.create(ae);
        assertTrue(contentPageDao.get(ae.getId()).isPresent());
    }

    @Test
    public void shouldGetContentPage() throws Exception {
        assertTrue(contentPageDao.get(2L).isPresent());
    }

    @Test
    public void shouldListContentPages() throws Exception {

        ContentPage ae = new ContentPage();
        ae.setTitle("pagetitlez");
        ae.setContentArticleId(1L);
        ae = contentPageDao.create(ae);
        assertEquals(1,contentPageDao.list(ae.getTitle()).size());
    }

    @Test
    public void shouldUpdateContentPage() throws Exception {
        ContentPage ae = new ContentPage();
        ae.setTitle("aaa");
        ae.setContentArticleId(1L);
        ae = contentPageDao.create(ae);
        ae.setTitle("bbb");
        contentPageDao.update(ae);

        ContentPage cp = contentPageDao.get(ae.getId()).get();
        assertEquals(cp.getTitle(),ae.getTitle());
    }
}
