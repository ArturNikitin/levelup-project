package webjava;

import database.DAO.UserDAO;
import database.DAO.impl.TestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, WebConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
class LoginControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserDAO userDAO;

    private MockMvc mockMvc;

    @BeforeEach
    public void set() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    void loginFormViewTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/login")
        ).andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andReturn();
    }

    @Test
    public void loginFormViewWithSessionTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/login")
                        .sessionAttr(LoginController.VERIFIED_USER_NAME, "test")
        ).andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    public void loginFormValidLogin() throws Exception {
        userDAO.insertUser("test", "testematil@gmail.com", "123");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("username", "test")
                        .param("password", "123")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(request().sessionAttribute(LoginController.VERIFIED_USER_NAME,
                        "test"))
                .andReturn();
    }

    @Test
    public void loginFormInvalidLogin() throws Exception {
        userDAO.insertUser("test", "testematil@gmail.com", "123");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("username", "test2")
                        .param("password", "123")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login?login=test2"))
                .andReturn();
    }

    @Test
    public void loginFormInvalidPassword() throws Exception {
        userDAO.insertUser("test", "testematil@gmail.com", "123");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("username", "test")
                        .param("password", "1234")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login?login=test"))
                .andReturn();
    }

    @Test
    public void loginFormAlreadyLoggedInTest() throws Exception {
        userDAO.insertUser("test", "testematil@gmail.com", "123");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("username", "test")
                        .param("password", "123")
                        .sessionAttr(LoginController.VERIFIED_USER_NAME, "test")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andReturn();
    }
}