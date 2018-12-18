package org.xcolab.service.content.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.model.tables.pojos.FileEntryImpl;
import org.xcolab.service.content.domain.fileentry.FileEntryDao;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)

@WebMvcTest(ContentController.class)
@ComponentScan("org.xcolab.service.content")
@ComponentScan("com.netflix.discovery")
@ActiveProfiles("test")
public class FilesControllerTest {

    @Mock
    private FileEntryDao fileEntryDao;

    private MockMvc mockMvc;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    ObjectMapper objectMapper;


    @InjectMocks
    private FileController controller;

    @Before
    public void before() throws Exception{
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldCreateNewFileEntryDao() throws Exception {
        IFileEntry IFileEntry = new FileEntryImpl();
        IFileEntry.setId(12L);
        this.mockMvc.perform(
                post("/fileEntries")
                        .contentType(contentType).accept(contentType)
                        .content(objectMapper.writeValueAsString(IFileEntry)))
                .andExpect(status().isOk());

        Mockito.verify(fileEntryDao,Mockito.times(1)).create(Mockito.anyObject());
    }

    @Test
    public void shouldGetFileEntryDao() throws Exception {
        IFileEntry IFileEntry = new FileEntryImpl();
        IFileEntry.setId(12L);
        this.mockMvc.perform(
                get("/fileEntries/123")
                        .contentType(contentType).accept(contentType)
                        .content(objectMapper.writeValueAsString(IFileEntry)))
                .andExpect(status().isOk());

        Mockito.verify(fileEntryDao,Mockito.times(1)).get(Mockito.anyLong());
    }
}
