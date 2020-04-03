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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, WebConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
class RegistrationControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserDAO userDAO;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void registrationFormViewInvalidUser() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/login")
                .sessionAttr(LoginController.VERIFIED_USER_NAME, "test")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andReturn();
    }

    @Test
    public void registrationFormViewTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/reg")
        ).andExpect(status().isOk())
                .andExpect(view().name("reg"))
                .andReturn();
    }

    @Test
    public void registrationFormValidRegistrationTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/reg")
                .param("user", "test-user")
                .param("email", "test@gmail.com")
                .param("password", "123")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login?login=test-user"))
                .andReturn();
    }

    @Test
    public void registrationFormAlreadyExistingUser() throws Exception {
        userDAO.insertUser("test-name", "test@email.com", "123");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/reg")
                .param("user", "test-name")
                .param("email", "test124@gmail.com")
                .param("password", "123456")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/reg"))
                .andReturn();
    }
}