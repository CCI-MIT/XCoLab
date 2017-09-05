package org.xcolab.view.pages.loginregister;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.MockContestType;
import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.view.util.clienthelpers.AdminClientMockerHelper;
import org.xcolab.view.util.clienthelpers.EmailTemplateClientMockerHelper;
import org.xcolab.view.util.clienthelpers.MembersClientMockerHelper;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.springframework.security.test.web.servlet.request
        .SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PowerMockIgnore("javax.management.*")
@WebMvcTest(LoginRegisterController.class)

@ComponentScan("org.xcolab.view.theme")
@ComponentScan("org.xcolab.view.auth")
@ComponentScan("org.xcolab.view.pages.proposals.interceptors")
@ComponentScan("org.xcolab.view.pages.proposals.utils.context")
@ComponentScan("org.xcolab.view.pages.loginregister")
@ComponentScan("org.xcolab.view.config")
@ComponentScan("org.xcolab.view.i18n")

@TestPropertySource(properties = {"cache.enabled=false"})

@PrepareForTest({
        org.xcolab.client.admin.AdminClient.class,
        org.xcolab.client.admin.ContestTypeClient.class,
        org.xcolab.client.contest.ContestClientUtil.class,
        org.xcolab.client.sharedcolab.SharedColabClient.class,
        org.xcolab.client.members.MembersClient.class,
        org.xcolab.client.admin.EmailTemplateClientUtil.class,
        org.xcolab.client.emails.EmailClient.class,
        org.xcolab.client.activities.helper.ActivityEntryHelper.class,
        org.xcolab.client.members.MessagingClient.class,
        org.xcolab.client.balloons.BalloonsClient.class
})

public class LoginRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        ServiceRequestUtils.setInitialized(true);

        PowerMockito.mockStatic(ContestClientUtil.class);
        PowerMockito.mockStatic(SharedColabClient.class);
        PowerMockito.mockStatic(ContestTypeClient.class);

        PowerMockito.mockStatic(EmailClient.class);
        PowerMockito.mockStatic(ActivityEntryHelper.class);

        PowerMockito.mockStatic(MessagingClient.class);
        PowerMockito.mockStatic(BalloonsClient.class);

        MembersClientMockerHelper.mockMembersClient();
        AdminClientMockerHelper.mockAdminClient();
        EmailTemplateClientMockerHelper.mockEmailTemplateClient();

        Mockito.when(ContestTypeClient.getAllContestTypes())
                .thenReturn(new ArrayList<>());

        Mockito.when(ContestTypeClient.getContestType(anyLong()))
                .thenReturn(new MockContestType(0));
        Mockito.when(ContestTypeClient.getContestType(anyLong(), anyString()))
                .thenReturn(new MockContestType(0, "en"));
    }

    @Test
    public void shouldReturnRegisterForm() throws Exception {


        this.mockMvc.perform(get("/register")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/loginregister/register.jspx"));

    }

    @Test
    public void registrationFailsWhenInvalidDataPostedAndSendsUserBackToForm() throws Exception {

        this.mockMvc.perform(post("/register")
                .with(csrf())
                .param("screenName", "")
                .param("email", "")
                .param("imageId", "")
                .param("file", "")
                .param("retypeEmail", "")
                .param("firstName", "")
                .param("lastName", "")
                .param("password", "")
                .param("retypePassword", "")
                .param("country", "")
                .param("shortBio", ""))
                .andExpect(forwardedUrl("/WEB-INF/jsp/loginregister/register.jspx"))
                .andExpect(model().hasErrors());

    }
    //.andExpect(model().attributeHasFieldErrors(
    //        "screenName"));
    //, "email","retypeEmail", "firstName","lastName", "password", "retypePassword"


    @Test
    public void registrationWorksAndDoLoginAndUserRedirectedToHome() throws Exception {

        this.mockMvc.perform(post("/register")
                .with(csrf())
                .param("screenName", "username")
                .param("email", "username@gmail.com")
                .param("imageId", "")
                .param("file", "")
                .param("retypeEmail", "username@gmail.com")
                .param("firstName", "User")
                .param("lastName", "Name")
                .param("password", "username")
                .param("retypePassword", "username")
                .param("language", "en")
                .param("country", "BR")
                .param("shortBio", "shortbio"))
                .andExpect(redirectedUrl("/"));
        PowerMockito.verifyStatic(Mockito.times(1));
    }

    @Test
    public void generateScreenName() throws Exception {
        Mockito.when(MembersClient.generateScreenName(anyString(), anyString()))
                .thenAnswer(invocation -> "---");


        this.mockMvc.perform(post("/api/register/generateScreenName")
                .with(csrf())
                .param("firstName", "User")
                .param("lastName", "Name"))
                .andExpect(content().string(containsString("screenName")));
        PowerMockito.verifyStatic(Mockito.times(1));
        MembersClient.generateScreenName("Name","User");
    }

}
