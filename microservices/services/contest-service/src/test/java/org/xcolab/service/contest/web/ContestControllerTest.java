package org.xcolab.service.contest.web;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
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

import org.xcolab.client.admin.IAdminClient;
import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.IEmailTemplateClient;
import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.IContestTeamMemberClient;
import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.pojo.tables.pojos.ContestDiscussion;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestdiscussion.ContestDiscussionDao;
import org.xcolab.service.contest.service.ontology.OntologyService;
import org.xcolab.util.http.ServiceRequestUtils;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)

@WebMvcTest(ContestController.class)
@ComponentScan("org.xcolab.service.contest")
@ComponentScan("org.xcolab.client")
@ComponentScan("com.netflix.discovery")
@PrepareForTest({ContestWrapper.class,
        IContestClient.class,
        IContestTeamMemberClient.class,
        IOntologyClient.class
})
@ActiveProfiles("test")
@Ignore
public class ContestControllerTest {

    private MockMvc mockMvc;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    private ContestController controller;

    @Mock
    private ContestDao contestDao;

    @Mock
    private ContestDiscussionDao contestDiscussionDao;

    @Mock
    private OntologyService ontologyService;

    @Before
    public void before() throws Exception {
        ServiceRequestUtils.setInitialized(true);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);

        Mockito.when(contestDao.get(anyLong()))
                .thenAnswer(invocation -> new ContestWrapper());

        Mockito.when(contestDiscussionDao.get(anyLong()))
                .thenAnswer(invocation -> Optional.of(new ContestDiscussion()));

        StaticAdminContext.setClients(Mockito.mock(IAdminClient.class),
                Mockito.mock(IContestTypeClient.class), Mockito.mock(IEmailTemplateClient.class));
    }

    private static ContestWrapper getContest() {
        ContestWrapper contest = new ContestWrapper();
        contest.setAuthorUserId(1L);
        return contest;
    }

    @Test
    @Ignore
    public void shouldCreateNewContest() throws Exception {
        ContestWrapper contest = getContest();

        this.mockMvc.perform(post("/contests").contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(contest))).andExpect(status().isOk());

        Mockito.verify(contestDao, Mockito.times(1)).create(Mockito.anyObject());
    }

    @Test
    @Ignore
    public void shouldGetContestWithoutOntologyTermIds() throws Exception {
        this.mockMvc.perform(get("/contests").param("startRecord", "1").param("limitRecord", "100")
                .param("sort", "").param("contestUrlName", "").param("contestYear", "2016")
                .param("active", "").param("featured", "")
                //.param("contestTiers","")
                .param("contestScheduleId", "").param("proposalTemplateId", "")
                //.param("focusAreaIds","")
                //.param("ontologyTermIds","")
                .param("contestPrivate", "").param("searchTerm", "")).andExpect(status().isOk());

        Mockito.verify(contestDao, Mockito.times(1))
                .findByGiven(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(),
                        Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(),
                        Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(),
                        Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject());
    }

    public void shouldGetContestWithOntologyTermIds() throws Exception {
        this.mockMvc.perform(get("/contests").param("startRecord", "1").param("limitRecord", "100")
                .param("sort", "").param("contestUrlName", "").param("contestYear", "2016")
                .param("active", "").param("featured", "").param("contestTiers", "")
                .param("contestScheduleId", "").param("proposalTemplateId", "")

                .param("ontologyTermIds", "123,1230,938,933").param("contestPrivate", "")
                .param("searchTerm", "")).andExpect(status().isOk());
        Mockito.verify(ontologyService, Mockito.times(1))
                .getFocusAreaIdsForDescendantTerms(Mockito.anyObject());
        Mockito.verify(contestDao, Mockito.times(1))
                .findByGiven(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(),
                        Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(),
                        Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(),
                        Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject());
    }

    @Test
    @Ignore
    public void shouldUpdateContestPost() throws Exception {
        ContestWrapper contest = getContest();
        contest.setId(10L);
        this.mockMvc.perform(put("/contests").contentType(contentType)
                .accept(contentType).content(objectMapper.writeValueAsString(contest)))
                .andExpect(status().isOk());

        Mockito.verify(contestDao, Mockito.times(1)).update(Mockito.anyObject());
    }

    @Test
    @Ignore
    public void shouldGetContestDiscussion() throws Exception {
        this.mockMvc.perform(
                get("/contestDiscussions").param("startRecord", "1").param("limitRecord", "100")
                        .param("sort", "").param("contestId", "").param("tab", "2016")

        ).andExpect(status().isOk());
        Mockito.verify(contestDiscussionDao, Mockito.times(1))
                .findByGiven(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject());
    }

    @Test
    @Ignore
    public void shouldCreateNewContestDiscussion() throws Exception {
        ContestDiscussion contest = new ContestDiscussion();

        this.mockMvc.perform(post("/contestDiscussions").contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(contest))).andExpect(status().isOk());

        Mockito.verify(contestDiscussionDao, Mockito.times(1)).create(Mockito.anyObject());
    }
}
