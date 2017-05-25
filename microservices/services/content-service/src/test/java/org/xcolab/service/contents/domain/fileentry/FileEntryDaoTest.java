package org.xcolab.service.contents.domain.fileentry;

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
import org.xcolab.model.tables.pojos.FileEntry;

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
public class FileEntryDaoTest {

    @Autowired
    FileEntryDao fileEntryDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateNewFileEntry() throws Exception {

        FileEntry ae = new FileEntry();
        ae = fileEntryDao.create(ae);
        assertNotNull(fileEntryDao.get(ae.getFileEntryId()));

    }
    @Test
    public void shouldGetFileEntry() throws Exception {

        assertNotNull(fileEntryDao.get(901l));

    }
}
