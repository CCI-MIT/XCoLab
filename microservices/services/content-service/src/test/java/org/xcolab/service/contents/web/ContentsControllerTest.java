package org.xcolab.service.contents.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.model.tables.pojos.ContentArticle;
import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.service.contents.domain.contentFolder.ContentFolderDao;
import org.xcolab.service.contents.domain.contentarticle.ContentArticleDao;
import org.xcolab.service.contents.domain.contentarticleversion.ContentArticleVersionDao;
import org.xcolab.service.contents.domain.page.ContentPageDao;
import org.xcolab.service.contents.service.contentarticle.ContentArticleService;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)

@WebMvcTest(ContentsController.class)
@ComponentScan("org.xcolab.service.contents")

@ComponentScan("com.netflix.discovery")


@TestPropertySource(
        properties = {
                "cache.active=false",
                "eureka.client.enabled=false"
        }
)
public class ContentsControllerTest {
    private MockMvc mockMvc;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    ObjectMapper objectMapper;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @InjectMocks
    private ContentsController controller;

    @Mock
    private ContentArticleDao contentArticleDao;

    @Mock
    private ContentFolderDao contentFolderDao;

    @Mock
    private ContentArticleVersionDao contentArticleVersionDao;

    @Mock
    private ContentPageDao contentPageDao;

    @Mock
    private ContentArticleService contentArticleService;

    @Before
    public void before() throws Exception{

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


        Mockito.when(contentArticleDao.get(anyLong()))
                .thenAnswer(new Answer<ContentArticle>() {
                    @Override
                    public ContentArticle answer(InvocationOnMock invocation)
                            throws Throwable {
                        return new ContentArticle();
                    }
                });
        Mockito.when(contentFolderDao.get(anyLong()))
                .thenAnswer(new Answer<ContentFolder>() {
                    @Override
                    public ContentFolder answer(InvocationOnMock invocation)
                            throws Throwable {
                        return new ContentFolder();
                    }
                });


    }

    @Test
    public void shouldCreateNewContentArticlesPost() throws Exception {
        ContentArticle contentArticle = new ContentArticle();
        contentArticle.setAuthorId(01l);
        this.mockMvc.perform(
                post("/contentArticles")
                        .contentType(contentType).accept(contentType)
                        .content(objectMapper.writeValueAsString(contentArticle)))
                .andExpect(status().isOk());

        Mockito.verify(contentArticleDao,Mockito.times(1)).create(Mockito.anyObject());
    }

    @Test
    public void shouldGetContentArticlesWithoutFolder() throws Exception {

        this.mockMvc.perform(
                get("/contentArticles")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(contentArticleDao,Mockito.times(1)).getArticles();
    }

    @Test
    public void shouldGetContentArticlesWithFolder() throws Exception {

        this.mockMvc.perform(
                get("/contentArticles")
                        .param("folderId","01")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(contentArticleDao,Mockito.times(1)).getArticlesInFolder(Mockito.anyLong());
    }

    @Test
    public void shouldGetContentArticleVersions() throws Exception {

        this.mockMvc.perform(
                get("/contentArticleVersions")
                        .param("startRecord","1")
                        .param("limitRecord","2")
                        .param("folderId","")
                        .param("contentArticleId","")
                        .param("contentArticleVersion","")
                        .param("title","")
                        .param("sort","")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(contentArticleVersionDao,Mockito.times(1)).findByGiven(Mockito.anyObject()
        ,Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong(),Mockito.anyString());
    }

    @Test
    public void shouldGetContentFolderVersions() throws Exception {

        this.mockMvc.perform(
                get("/contentFolders")
                        .param("startRecord","1")
                        .param("limitRecord","2")
                        .param("parentFolderId","1")
                        .param("sort","")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(contentFolderDao,Mockito.times(1)).findByGiven(Mockito.anyObject(),Mockito.anyLong());
    }

    @Test
    public void shouldGetDescendantFolders() throws Exception {

        this.mockMvc.perform(
                get("/contentFolders/1/descendantFolders")
                .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(contentFolderDao,Mockito.times(1)).findByAncestorFolderId(Mockito.anyLong());
    }
    @Test
    public void getContentArticle() throws Exception {

        this.mockMvc.perform(
                get("/contentArticles/1")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(contentArticleDao,Mockito.times(1)).get(Mockito.anyLong());
    }

    @Test
    public void putContentArticle() throws Exception {

        ContentArticle contentArticle = new ContentArticle();
        contentArticle.setAuthorId(1l);
        contentArticle.setContentArticleId(12l);
        contentArticle.setFolderId(12l);
        this.mockMvc.perform(
                put("/contentArticles/1")
                        .contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(contentArticle)))
                .andExpect(status().isOk());

        Mockito.verify(contentArticleDao,Mockito.times(1)).update(Mockito.anyObject());
    }

    @Test
    public void createContentFolderPost() throws Exception {
        ContentFolder contentFolder = new ContentFolder();
        contentFolder.setContentFolderDescription("");
        contentFolder.setContentFolderId(01l);
        contentFolder.setContentFolderName("");
        contentFolder.setContentFolderDescription("");

        this.mockMvc.perform(
                post("/contentFolders")
                        .contentType(contentType).accept(contentType)
                        .content(objectMapper.writeValueAsString(contentFolder)))
                .andExpect(status().isOk());

        Mockito.verify(contentFolderDao,Mockito.times(1)).create(Mockito.anyObject());
    }
    @Test
    public void getContentFolder() throws Exception {

        this.mockMvc.perform(
                get("/contentFolders/12")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(contentFolderDao,Mockito.times(1)).get(Mockito.anyLong());
    }

    @Test
    public void getContentFolderArticleVersions() throws Exception {

        this.mockMvc.perform(
                get("/contentFolders/123/contentArticleVersions")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(contentArticleVersionDao,Mockito.times(1)).getByFolderId(Mockito.anyLong());
    }
    @Test
    public void updateContentFolderArticleVersion() throws Exception {

        ContentFolder contentFolder = new ContentFolder();
        contentFolder.setContentFolderDescription("");
        contentFolder.setContentFolderId(01l);
        contentFolder.setContentFolderName("");
        contentFolder.setContentFolderDescription("");

        this.mockMvc.perform(
                put("/contentFolders/123")
                        .content(objectMapper.writeValueAsString(contentFolder))
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(contentFolderDao,Mockito.times(1)).update(Mockito.anyObject());
    }
    @Test
    public void deleteContentArticles() throws Exception {
        this.mockMvc.perform(
                delete("/contentArticles/12345")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(contentArticleService,Mockito.times(1))
                .delete(Mockito.anyLong());
    }

}
