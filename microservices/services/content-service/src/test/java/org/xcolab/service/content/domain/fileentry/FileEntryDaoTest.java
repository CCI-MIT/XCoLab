package org.xcolab.service.content.domain.fileentry;

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

import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.model.tables.pojos.FileEntryImpl;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.content")
@ActiveProfiles("test")
public class FileEntryDaoTest {

    @Autowired
    private FileEntryDao fileEntryDao;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateNewFileEntry() throws Exception {
        IFileEntry ae = new FileEntryImpl();
        ae = fileEntryDao.create(ae);
        assertNotNull(fileEntryDao.get(ae.getId()));
    }

    @Test
    public void shouldGetFileEntry() throws Exception {
        assertNotNull(fileEntryDao.get(901L));
    }
}
