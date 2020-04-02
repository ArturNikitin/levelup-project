package webjava;

import database.DAO.UserDAO;
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

        User user = user = userDAO.findUserByLogin((String) session.getAttribute(LoginController.VERIFIED_USER_NAME));

        ProfileForm form;
        if (user.getItems() != null){
            form = new ProfileForm(new UserAddress("", "", "", ""), user.getItems());
        } else {
            form = new ProfileForm(new UserAddress("", "", "", ""));
        }
        if (user.getAddress() != null) {
            form.setAddress(user.getAddress());
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
        if (user == null){
            throw new IllegalStateException("No user found");
        }
        if(country == null || city == null || street == null || postcode == null){
            throw new IllegalStateException("Something is missing from Address");
        }
        try {
            user = userDAO.addAddress(user, country, city, street, postcode);
        } catch (Exception e){
            System.out.println(e.getCause());
        }
        return "redirect:/profile";
    }
}
