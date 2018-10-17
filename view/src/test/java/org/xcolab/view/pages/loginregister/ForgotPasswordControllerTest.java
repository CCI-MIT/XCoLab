package org.xcolab.view.pages.loginregister;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.view.util.clienthelpers.AdminClientMockerHelper;
import org.xcolab.view.util.clienthelpers.EmailTemplateClientMockerHelper;
import org.xcolab.view.util.clienthelpers.MembersClientMockerHelper;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PowerMockIgnore("javax.management.*")
@WebMvcTest(ForgotPasswordController.class)

@ComponentScan("org.xcolab.view.theme")
@ComponentScan("org.xcolab.view.auth")
@ComponentScan("org.xcolab.view.pages.proposals.interceptors")
@ComponentScan("org.xcolab.view.pages.proposals.utils.context")
@ComponentScan("org.xcolab.view.pages.loginregister")
@ComponentScan("org.xcolab.view.pages.redballoon")
@ComponentScan("org.xcolab.view.config")
@ComponentScan("org.xcolab.view.i18n")
@ComponentScan("org.xcolab.client.tracking")

@TestPropertySource(
        properties = {
                "cache.enabled=false"
        }
)

@PrepareForTest({
        org.xcolab.client.admin.AdminClient.class,
        org.xcolab.client.admin.ContestTypeClient.class,
        org.xcolab.client.contest.ContestClientUtil.class,
        org.xcolab.client.members.MembersClient.class,
        org.xcolab.client.admin.EmailTemplateClientUtil.class,
        org.xcolab.client.emails.EmailClient.class,
        org.xcolab.commons.servlet.flash.AlertMessage.class,
        org.xcolab.client.members.MessagingClient.class,
        org.xcolab.client.balloons.BalloonsClient.class
})

@ActiveProfiles("test")
public class ForgotPasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        ServiceRequestUtils.setInitialized(true);

        PowerMockito.mockStatic(ContestClientUtil.class);
        PowerMockito.mockStatic(ContestTypeClient.class);
        PowerMockito.mockStatic(EmailTemplateClientUtil.class);
        PowerMockito.mockStatic(EmailClient.class);
        PowerMockito.mockStatic(MessagingClient.class);

        MembersClientMockerHelper.mockMembersClient();
        AdminClientMockerHelper.mockAdminClient();
        EmailTemplateClientMockerHelper.mockEmailTemplateClient();
    }

    @Test
    public void passwordUpdateFailsWhenScreenNameNotPassed() throws Exception {
        mockMvc.perform(post("/login/resetPassword")
                .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void passwordUpdateSucceedsWithValidScreenName() throws Exception {
        mockMvc.perform(post("/login/resetPassword")
                .with(csrf())
                .param("screenNameOrEmail", "superuser"))
                .andExpect(status().is3xxRedirection());
    }
}
