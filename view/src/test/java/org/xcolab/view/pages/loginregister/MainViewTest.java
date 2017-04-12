package org.xcolab.view.pages.loginregister;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
import org.springframework.validation.BindingResult;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.exceptions.EurekaNotInitializedException;
import org.xcolab.view.util.TestUtil;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;


import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyString;


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

@PrepareForTest({
        org.xcolab.client.admin.AdminClient.class,
        org.xcolab.client.contest.ContestClientUtil.class,
        org.xcolab.client.sharedcolab.SharedColabClient.class,
        org.xcolab.client.members.MembersClient.class,
        org.xcolab.client.admin.EmailTemplateClientUtil.class,
        org.xcolab.client.emails.EmailClient.class,
        org.xcolab.client.activities.helper.ActivityEntryHelper.class
})

public class MainViewTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        ServiceRequestUtils.setInitialized(true);


        PowerMockito.mockStatic(AdminClient.class);
        PowerMockito.mockStatic(ContestClientUtil.class);
        PowerMockito.mockStatic(SharedColabClient.class);
        PowerMockito.mockStatic(MembersClient.class);
        PowerMockito.mockStatic(EmailTemplateClientUtil.class);
        PowerMockito.mockStatic(EmailClient.class);
        PowerMockito.mockStatic(ActivityEntryHelper.class);


        Mockito.when(MembersClient.findMemberByScreenNameNoRole(anyString()))
                .thenAnswer(new Answer<Member>() {
                    @Override
                    public Member answer(InvocationOnMock invocation)
                            throws Throwable {

                        Member member = new Member();
                        member.setScreenName(TestUtil.createStringWithLength(10));
                        member.setFirstName(TestUtil.createStringWithLength(10));
                        member.setLastName(TestUtil.createStringWithLength(10));
                        member.setId_(100l);
                        member.setHashedPassword(TestUtil.createStringWithLength(10));
                        return member;
                    }
                });

//        Mockito.when(MembersClient.login(anyLong(),anyString(),anyString(),anyString()))
//                .thenAnswer(new Answer<Member>() {
//                    @Override
//                    public Boolean answer(InvocationOnMock invocation)
//                            throws Throwable {
//
//                        return true;
//                    }
//                });

        Mockito.when(EmailTemplateClientUtil.getContestEmailTemplateByType(anyString()))
                .thenAnswer(new Answer<ContestEmailTemplate>() {
                    @Override
                    public ContestEmailTemplate answer(InvocationOnMock invocation)
                            throws Throwable {

                        ContestEmailTemplate contestEmailTemplate = new ContestEmailTemplate();
                        contestEmailTemplate.setFooter(TestUtil.createStringWithLength(10));
                        contestEmailTemplate.setHeader(TestUtil.createStringWithLength(10));
                        contestEmailTemplate.setSubject(TestUtil.createStringWithLength(10));
                        contestEmailTemplate.setType_(TestUtil.createStringWithLength(10));

                        return contestEmailTemplate;
                    }
                });

        Mockito.when(MembersClient.getMemberUnchecked(anyLong()))
                .thenAnswer(new Answer<Member>() {
                                @Override
                                public Member answer(InvocationOnMock invocation)
                                        throws Throwable {

                                    Member member = new Member();
                                    member.setScreenName(TestUtil.createStringWithLength(10));
                                    member.setFirstName(TestUtil.createStringWithLength(10));
                                    member.setLastName(TestUtil.createStringWithLength(10));
                                    member.setHashedPassword(TestUtil.createStringWithLength(10));
                                    return member;
                                }
                            });

        //generic mock for ConfigAttribute
        Mockito.when(AdminClient.getConfigurationAttribute(anyString()))
                .thenAnswer(new Answer<ConfigurationAttribute>() {
                    @Override
                    public ConfigurationAttribute answer(InvocationOnMock invocation)
                            throws Throwable {
                        Object[] arguments = invocation.getArguments();
                        if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                            String key = (String) arguments[0];
                            if (key.equals("ACTIVE_THEME")) {
                                return new ConfigurationAttribute("ACTIVE_THEME", 0l, 0l,
                                        "CLIMATE_COLAB", 0d);
                            } else {
                                return new ConfigurationAttribute(key, 0l, 0l, "12312312332", 0d);
                            }

                        }

                        return new ConfigurationAttribute("GOOGLE_RECAPTCHA_SITE_KEY", 0l, 0l,
                                "12312312332", 0d);
                    }
                });

        Mockito.when(ContestClientUtil.getAllContestTypes()).thenReturn(
                new ArrayList()
        );
    }

    @Test
    public void shouldReturnRegisterForm() throws Exception {


        this.mockMvc.perform(get("/register")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/loginregister/register.jspx"));

    }

    @Test
    public void registrationFailsWhenInvalidDataPostedAndSendsUserBackToForm() throws Exception {


        this.mockMvc.perform(post("/register")
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
                .param("screenName", "username")
                .param("email", "username@gmail.com")
                .param("imageId", "")
                .param("file", "")
                .param("retypeEmail", "username@gmail.com")
                .param("firstName", "User")
                .param("lastName", "Name")
                .param("password", "username")
                .param("retypePassword", "username")
                .param("country", "BR")
                .param("shortBio", "shortbio"))
                .andExpect(redirectedUrl("/"));
        PowerMockito.verifyStatic(Mockito.times(1));
        MembersClient.login(100l,"username","127.0.0.1",null);

    }

}
