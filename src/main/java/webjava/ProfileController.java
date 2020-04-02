package webjava;

import database.DAO.ItemDAO;
import database.DAO.UserDAO;
import database.entities.Item;
import database.entities.User;
import database.utilities.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

//@WebServlet(urlPatterns = {"/profile"})
@Controller
public class ProfileController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping(path = "/profile")
    public String getProfileForm(HttpSession session,
                                 ModelMap model) {
        if(session.getAttribute(LoginController.VERIFIED_USER_NAME) == null ){
            return "redirect:/login";
        }
        User user = userDAO.findUserByLogin((String) session.getAttribute(LoginController.VERIFIED_USER_NAME));
        List<Item> items = userDAO.getItemList(user);

        ProfileForm form = new ProfileForm();
        if (user.getAddress() != null){
            form.setAddress(user.getAddress());
        } else
            form.setAddress(new UserAddress("", "", "", ""));

        if (items != null) {
            form.setItems(items);
        }

        model.addAttribute("form", form);
        return "profile";
    }

    @PostMapping(path = "/profile")
    public String processProfilePost(HttpSession session,
                                     @RequestParam String country,
                                     @RequestParam String city,
                                     @RequestParam String street,
                                     @RequestParam String postcode) {
        User user = userDAO.findUserByLogin((String) session.getAttribute(LoginController.VERIFIED_USER_NAME));

        if(country == null || city == null || street == null || postcode == null){
            throw new IllegalStateException("Something is missing from Address");
        }

        user = userDAO.addAddress(user, country, city, street, postcode);

        return "redirect:/profile";
    }
}
