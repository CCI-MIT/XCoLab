package org.xcolab.service.content.service.contentarticle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.service.content.domain.contentarticle.ContentArticleDao;
import org.xcolab.service.content.domain.contentarticleversion.ContentArticleVersionDao;
import org.xcolab.service.content.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@org.powermock.modules.junit4.PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.content")
@ComponentScan("org.xcolab.client.content")
@ActiveProfiles("test")
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
