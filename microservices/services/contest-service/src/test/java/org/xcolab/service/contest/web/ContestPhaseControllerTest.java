package org.xcolab.service.contest.web;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
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

import org.xcolab.client.contest.pojo.phases.AbstractContestPhase;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphaseribbontype.ContestPhaseRibbonTypeDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.service.contestphase.ContestPhaseService;
import org.xcolab.service.contest.utils.promotion.PromotionService;
import org.xcolab.util.http.ServiceRequestUtils;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)

@WebMvcTest(ContestPhaseController.class)
@ComponentScan("org.xcolab.service.contest")
@ComponentScan("com.netflix.discovery")
@PrepareForTest({org.xcolab.client.contest.pojo.Contest.class,
        org.xcolab.client.contest.ContestClient.class,
        org.xcolab.client.contest.ContestTeamMemberClient.class,
        org.xcolab.client.contest.OntologyClient.class,
        org.xcolab.client.comment.CommentClient.class, org.xcolab.client.comment.ThreadClient.class

})
@ActiveProfiles("test")
public class ContestPhaseControllerTest {

    private MockMvc mockMvc;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    ObjectMapper objectMapper;


    @InjectMocks
    private ContestPhaseController controller;

    @Mock
    private ContestPhaseRibbonTypeDao contestPhaseRibbonTypeDao;

    @Mock
    private ContestPhaseDao contestPhaseDao;

    @Mock
    private ContestPhaseTypeDao contestPhaseTypeDao;

    @Mock
    private PromotionService promotionService;

    @Mock
    private ContestPhaseService contestPhaseService;

    @Before
    public void before() throws Exception {

        ServiceRequestUtils.setInitialized(true);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);

        Mockito.when(contestPhaseDao.exists(Matchers.anyLong()))
                .thenAnswer(invocation -> true);
    }

    @Test
    public void shouldCreateNewContestPhase() throws Exception {

        AbstractContestPhase contest = new AbstractContestPhase(){};


        this.mockMvc.perform(post("/contestPhases").contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(contest))).andExpect(status().isOk());

        Mockito.verify(contestPhaseDao, Mockito.times(1)).create(Mockito.anyObject());
    }

    @Test
    public void shouldUpdateContestPhase() throws Exception {

        AbstractContestPhase contestPhase = new AbstractContestPhase(){};
        contestPhase.setId(123L);

        this.mockMvc.perform(put("/contestPhases/" + contestPhase.getId()).contentType(contentType)
                .accept(contentType).content(objectMapper.writeValueAsString(contestPhase)))
                .andExpect(status().isOk());

        Mockito.verify(contestPhaseDao, Mockito.times(1)).update(Mockito.anyObject());
    }
}
