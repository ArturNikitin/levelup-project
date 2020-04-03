package webjava;

import database.DAO.ItemDAO;
import database.DAO.UserDAO;
import database.DAO.impl.TestConfiguration;
import database.entities.Item;
import database.entities.User;
import database.utilities.ClothingSize;
import database.utilities.ClothingType;
import database.utilities.Price;
import database.utilities.UserAddress;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, WebConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
class ProfileControllerTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void profileFormViewTest() throws Exception{
        userDAO.insertUser("test", "test@gmail.com", "123");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/profile")
                .sessionAttr(LoginController.VERIFIED_USER_NAME, "test")
        ).andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attribute("form",
                        new ProfileForm(new UserAddress("", "", "", ""))))
                .andReturn();
    }

    @Test
    public void profileFormChangedAddressTest() throws Exception {
        User user = userDAO.insertUser("test", "test@gmail.com", "123");
        userDAO.addAddress(user, "country", "city", "street", "postcode");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/profile")
                .sessionAttr(LoginController.VERIFIED_USER_NAME, "test")
        ).andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attribute("form",
                        new ProfileForm(new UserAddress("country", "city", "street", "postcode"))))
                .andReturn();
    }

    @Test
    public void profileFormChangedItemsTest() throws Exception {
        User user = userDAO.insertUser("test", "test@gmail.com", "123");
        userDAO.addAddress(user, "country", "city", "street", "postcode");
        Item item = itemDAO.createItem(user, "item", new Price(10.15), ClothingSize.S, ClothingType.JACKET);
        List<Item> items = new ArrayList<>();
        items.add(item);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/profile")
                        .sessionAttr(LoginController.VERIFIED_USER_NAME, "test")
        ).andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attribute("form",
                        new ProfileForm(new UserAddress("country", "city", "street", "postcode"), items)))
                .andReturn();
    }
}