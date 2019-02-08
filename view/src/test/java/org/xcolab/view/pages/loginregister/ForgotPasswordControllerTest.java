package org.xcolab.view.pages.loginregister;

import org.junit.Before;
import org.junit.Ignore;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.xcolab.client.admin.IAdminClient;
import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.IEmailTemplateClient;
import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.email.IEmailClient;
import org.xcolab.client.email.StaticEmailContext;

import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.view.util.clienthelpers.AdminClientMockerHelper;
import org.xcolab.view.util.clienthelpers.ContestTypeClientMockerHelper;
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
@ComponentScan("org.xcolab.client")

@TestPropertySource(
        properties = {
                "cache.enabled=false"
        }
)

@PrepareForTest({
        AlertMessage.class,
})

@ActiveProfiles("test")
public class ForgotPasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        ServiceRequestUtils.setInitialized(true);


        MembersClientMockerHelper.mockMembersClient();
        IAdminClient adminClient = AdminClientMockerHelper.mockAdminClient();
        IEmailTemplateClient emailTemplateClient =
                EmailTemplateClientMockerHelper.mockEmailTemplateClient();
        IContestTypeClient contestTypeClient =
                ContestTypeClientMockerHelper.mockContestTypeClient();

        StaticAdminContext.setClients(adminClient, contestTypeClient, emailTemplateClient);
        StaticEmailContext.setClients(Mockito.mock(IEmailClient.class));
    }

    @Test
    @Ignore
    public void passwordUpdateFailsWhenScreenNameNotPassed() throws Exception {
        mockMvc.perform(post("/login/resetPassword")
                .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Ignore
    public void passwordUpdateSucceedsWithValidScreenName() throws Exception {
        mockMvc.perform(post("/login/resetPassword")
                .with(csrf())
                .param("screenNameOrEmail", "superuser"))
                .andExpect(status().is3xxRedirection());
    }
}
