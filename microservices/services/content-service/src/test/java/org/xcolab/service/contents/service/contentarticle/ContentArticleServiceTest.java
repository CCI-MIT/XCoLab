package org.xcolab.service.contents.service.contentarticle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.service.contents.domain.contentarticle.ContentArticleDao;
import org.xcolab.service.contents.domain.contentarticleversion.ContentArticleVersionDao;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@org.powermock.modules.junit4.PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(
        properties = {"cache.enabled=false", "eureka.client.enabled=false",
                "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL"})
@ComponentScan("org.xcolab.service.contents")
@ComponentScan("org.xcolab.client")
public class ContentArticleServiceTest {

    @Autowired
    private ContentArticleService contentArticleService;

    @Autowired
    private ContentArticleDao contentArticleDao;

    @Autowired
    private ContentArticleVersionDao contentArticleVersionDao;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldDeleteContentArticleAndContentArticleVersions() throws Exception {

        Long contentArticleId = 2L;
        int totalOfContentArticleVersions = contentArticleVersionDao.findByGiven(new PaginationHelper(0,10,null),contentArticleId,null,null,null,null,null).size();
        int ret = contentArticleService.delete(contentArticleId);

        assertTrue(ret > 0);
        int totalAfterDelete = contentArticleVersionDao.findByGiven(new PaginationHelper(0,10,null),2L,null,null,null,null,null).size();
        assertEquals(totalAfterDelete,0);
        assertNotEquals(totalOfContentArticleVersions,totalAfterDelete);
        exception.expect(NotFoundException.class);
        contentArticleDao.get(contentArticleId);


    }
}
