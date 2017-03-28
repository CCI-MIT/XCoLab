package org.xcolab.view.pages.loginregister;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
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

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.exceptions.EurekaNotInitializedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PowerMockIgnore("javax.management.*")
@WebMvcTest(MainViewController.class)
@ComponentScan("org.xcolab.view.theme")
@ComponentScan("org.xcolab.view.auth")
@ComponentScan("org.xcolab.view.pages.proposals.interceptors")
@ComponentScan("org.xcolab.view.pages.proposals.utils.context")
@ComponentScan("org.xcolab.view.config")
@TestPropertySource(
        properties = {
                "cache.active=false"
        }
)

@PrepareForTest({org.xcolab.client.admin.AdminClient.class,org.xcolab.client.contest.ContestClientUtil.class})

public class MainViewTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnRegisterForm() throws Exception {


        ServiceRequestUtils.setInitialized(true);


        PowerMockito.mockStatic(AdminClient.class);
        PowerMockito.mockStatic(ContestClientUtil.class);


        Mockito.doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) {
                return "Always the same";
            }
        }).when(listMock).get(anyInt());

        Mockito.when(AdminClient.getConfigurationAttribute(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (String) args[0];
            }
        });
        Mockito.when(AdminClient.getConfigurationAttribute("GOOGLE_RECAPTCHA_SITE_KEY")).thenReturn(
                new ConfigurationAttribute("GOOGLE_RECAPTCHA_SITE_KEY",0l,0l,"12312312332", 0d)
        );
        Mockito.when(AdminClient.getConfigurationAttribute("ACTIVE_THEME")).thenReturn(
                new ConfigurationAttribute("ACTIVE_THEME",0l,0l,"CLIMATE_COLAB", 0d)
        );
        Mockito.when(AdminClient.getConfigurationAttribute("GENERATE_SCREEN_NAME")).thenReturn(
                new ConfigurationAttribute("GENERATE_SCREEN_NAME",0l,0l,"", 0d)
        );
        Mockito.when(AdminClient.getConfigurationAttribute("IS_SHARED_COLAB")).thenReturn(
                new ConfigurationAttribute("IS_SHARED_COLAB",0l,0l,"", 0d)
        );

        Mockito.when(AdminClient.getConfigurationAttribute("LOGIN_INFO_MESSAGE")).thenReturn(
                new ConfigurationAttribute("LOGIN_INFO_MESSAGE",0l,0l,"", 0d)
        );
        Mockito.when(AdminClient.getConfigurationAttribute("COLAB_NAME")).thenReturn(
                new ConfigurationAttribute("COLAB_NAME",0l,0l,"", 0d)
        );
        Mockito.when(AdminClient.getConfigurationAttribute("COLAB_SHORT_NAME")).thenReturn(
                new ConfigurationAttribute("COLAB_SHORT_NAME",0l,0l,"", 0d)
        );

        Mockito.when(AdminClient.getConfigurationAttribute("GOOGLE_ANALYTICS_KEY")).thenReturn(
                new ConfigurationAttribute("GOOGLE_ANALYTICS_KEY",0l,0l,"", 0d)
        );

        Mockito.when(AdminClient.getConfigurationAttribute("BETA_RIBBON_SHOW")).thenReturn(
                new ConfigurationAttribute("BETA_RIBBON_SHOW",0l,0l,"", 0d)
        );

        Mockito.when(AdminClient.getConfigurationAttribute("SHOW_SEARCH_MENU_ITEM")).thenReturn(
                new ConfigurationAttribute("SHOW_SEARCH_MENU_ITEM",0l,0l,"", 0d)
        );

        Mockito.when(AdminClient.getConfigurationAttribute("OPEN_GRAPH_SHARE_TITLE")).thenReturn(
                new ConfigurationAttribute("OPEN_GRAPH_SHARE_TITLE",0l,0l,"", 0d)
        );

        Mockito.when(AdminClient.getConfigurationAttribute("OPEN_GRAPH_SHARE_DESCRIPTION")).thenReturn(
                new ConfigurationAttribute("OPEN_GRAPH_SHARE_DESCRIPTION",0l,0l,"", 0d)
        );

        Mockito.when(AdminClient.getConfigurationAttribute("META_PAGE_DESCRIPTION")).thenReturn(
                new ConfigurationAttribute("META_PAGE_DESCRIPTION",0l,0l,"", 0d)
        );







        Mockito.when(ContestClientUtil.getAllContestTypes()).thenReturn(
                new ArrayList()
        );









        this.mockMvc.perform(get("/register")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("registrationForm")));
    }

}
