package org.xcolab.service.comment.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)

@WebMvcTest(CommentController.class)
@ComponentScan("org.xcolab.service.comment")
@ComponentScan("com.netflix.discovery")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
@WebAppConfiguration
public class CommentControllerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private MockMvc mockMvc;

    @Autowired
    private CommentController commentController;

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private ThreadController threadController;

    @Autowired
    private ObjectMapper objectMapper;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void before() {
        StaticCommentContext.setClients(commentController, categoryController, threadController);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(commentController, categoryController, threadController)
                .build();
    }

    @Test
    public void testListComments__shouldReturnAll() throws Exception {
        mockMvc.perform(get("/comments").contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testListComments__shouldContainCorrectCount() throws Exception {
        mockMvc.perform(get("/count/comments")
                    .param("authorUserId", "123456789")
                    .contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("0"));
    }

    @Test
    public void testListComments__shouldFilterByAuthor() throws Exception {
        mockMvc.perform(get("/comments")
                    .param("authorUserId", "12345")
                    .contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetComment__shouldReturnInstance() throws Exception {
        mockMvc.perform(get("/comments/301").contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(301)));
    }

    @Test
    public void testGetComment__deleted__shouldReturnInstance() throws Exception {
        mockMvc.perform(get("/comments/351")
                .param("includeDeleted", "true")
                .contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(351)));
    }

    @Test
    public void testGetComment__deleted__shouldReturn404() throws Exception {
        expectedException.expectCause(IsInstanceOf.instanceOf(CommentNotFoundException.class));
        mockMvc.perform(get("/comments/351")
                .contentType(contentType).accept(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetComment__shouldReturn404() throws Exception {
        expectedException.expectCause(IsInstanceOf.instanceOf(CommentNotFoundException.class));
        mockMvc.perform(get("/comments/399").contentType(contentType).accept(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testListThreads__shouldReturnAll() throws Exception {
        mockMvc.perform(get("/threads").contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetLastActivityDate__shouldReturnCorrectDate() throws Exception {
        mockMvc.perform(get("/threads/201/lastActivityDate").contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().string(Long.toString(
                        DateUtils.parseDate("2010-08-04 16:36:37",
                                "yyyy-MM-dd HH:mm:ss").getTime())));
    }

    @Test
    public void testGetLastActivityAuthorUserId__shouldReturnCorrectId() throws Exception {
        mockMvc.perform(get("/threads/201/lastActivityAuthorUserId").contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().string("12345"));
    }

    @Test
    public void testListCategories__shouldReturnAll() throws Exception {
        mockMvc.perform(get("/categories").contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetCategory__shouldReturnInstance() throws Exception {
        mockMvc.perform(get("/categories/101").contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(101)));
    }

    @Test
    public void testGetCategory__shouldReturn404() throws Exception {
        expectedException.expectCause(IsInstanceOf.instanceOf(CategoryNotFoundException.class));
        mockMvc.perform(get("/categories/199").contentType(contentType).accept(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testListGroups__shouldReturnAll() throws Exception {
        mockMvc.perform(get("/groups").contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetGroup__shouldReturnInstance() throws Exception {
        mockMvc.perform(get("/groups/701").contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(701)));
    }

    @Test
    public void testGetCroup__shouldReturn404() throws Exception {
        expectedException.expectCause(IsInstanceOf.instanceOf(CategoryGroupNotFoundException.class));
        mockMvc.perform(get("/groups/799").contentType(contentType).accept(contentType))
                .andExpect(status().isNotFound());
    }
}
