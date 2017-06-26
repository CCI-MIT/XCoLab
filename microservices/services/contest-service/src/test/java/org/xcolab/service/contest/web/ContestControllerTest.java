package org.xcolab.service.contest.web;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.AbstractContest;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestDiscussion;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestcollectioncard.ContestCollectionCardDao;
import org.xcolab.service.contest.domain.contestdiscussion.ContestDiscussionDao;
import org.xcolab.service.contest.domain.contesttype.ContestTypeDao;
import org.xcolab.service.contest.service.collectioncard.CollectionCardService;
import org.xcolab.service.contest.service.contest.ContestService;
import org.xcolab.service.contest.service.ontology.OntologyService;
import org.xcolab.util.http.ServiceRequestUtils;

import java.nio.charset.Charset;
import java.util.List;
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

@ComponentScan("com.netflix.discovery")


@TestPropertySource(
        properties = {"cache.enabled=false", "eureka.client.enabled=false"})
@PrepareForTest({org.xcolab.client.contest.pojo.Contest.class,
        org.xcolab.client.contest.ContestClient.class,
        org.xcolab.client.contest.ContestTeamMemberClient.class,
        org.xcolab.client.contest.OntologyClient.class,
        org.xcolab.client.comment.CommentClient.class, org.xcolab.client.comment.ThreadClient.class

})


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
    private ContestTypeDao contestTypeDao;

    @Mock
    private ContestDiscussionDao contestDiscussionDao;

    @Mock
    private ContestCollectionCardDao contestCollectionCardDao;

    @Mock
    private ContestService contestService;

    @Mock
    private CollectionCardService collectionCardService;


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
                .thenAnswer(new Answer<org.xcolab.model.tables.pojos.Contest>() {
                    @Override
                    public org.xcolab.model.tables.pojos.Contest answer(InvocationOnMock invocation)
                            throws Throwable {
                        return new org.xcolab.model.tables.pojos.Contest();
                    }
                });

        Mockito.when(contestDiscussionDao.get(anyLong()))
                .thenAnswer(new Answer<Optional<org.xcolab.model.tables.pojos.ContestDiscussion>>() {
                    @Override
                    public Optional<org.xcolab.model.tables.pojos.ContestDiscussion> answer(InvocationOnMock invocation)
                            throws Throwable {
                        return Optional.of(new org.xcolab.model.tables.pojos.ContestDiscussion());
                    }
                });
    }

    private static AbstractContest getContest() {
        AbstractContest contest = new AbstractContest() {};
        contest.setAuthorId(01l);
        return contest;
    }

    @Test
    public void shouldCreateNewContest() throws Exception {

        AbstractContest contest = getContest();


        this.mockMvc.perform(post("/contests").contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(contest))).andExpect(status().isOk());

        Mockito.verify(contestDao, Mockito.times(1)).create(Mockito.anyObject());
    }

    @Test
    public void shouldGetContestWithoutOntologyTermIds() throws Exception {
        this.mockMvc.perform(get("/contests").param("startRecord", "1").param("limitRecord", "100")
                .param("sort", "").param("contestUrlName", "").param("contestYear", "2016")
                .param("active", "").param("featured", "")
                //.param("contestTiers","")
                .param("contestScheduleId", "").param("planTemplateId", "")
                //.param("focusAreaIds","")
                //.param("ontologyTermIds","")
                //.param("contestTypeIds","")
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
                .param("contestScheduleId", "").param("planTemplateId", "")

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
    public void shouldUpdateContestPost() throws Exception {

        AbstractContest contest = getContest();
        contest.setContestPK(10l);
        this.mockMvc.perform(put("/contests/" + contest.getContestPK()).contentType(contentType)
                .accept(contentType).content(objectMapper.writeValueAsString(contest)))
                .andExpect(status().isOk());

        Mockito.verify(contestDao, Mockito.times(1)).update(Mockito.anyObject());
    }

        @Test
        public void shouldGetContestDiscussion() throws Exception {
            this.mockMvc.perform(
                    get("/contestDiscussions").param("startRecord", "1").param("limitRecord", "100")
                            .param("sort", "").param("contestId", "").param("tab", "2016")

            ).andExpect(status().isOk());
            Mockito.verify(contestDiscussionDao, Mockito.times(1))
                    .findByGiven(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject());
        }
    @Test
    public void shouldUpdateContestDiscussion() throws Exception {

        ContestDiscussion contestDisc = new ContestDiscussion();
        contestDisc.setDiscussionId(10l);


        this.mockMvc.perform(put("/contestDiscussions/"+contestDisc.getDiscussionId()).contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(contestDisc))).andExpect(status().isOk());

        Mockito.verify(contestDiscussionDao, Mockito.times(1)).update(Mockito.anyObject());
    }
    @Test
    public void shouldCreateNewContestDiscussion() throws Exception {

        ContestDiscussion contest = new ContestDiscussion();


        this.mockMvc.perform(post("/contestDiscussions").contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(contest))).andExpect(status().isOk());

        Mockito.verify(contestDiscussionDao, Mockito.times(1)).create(Mockito.anyObject());
    }
}
