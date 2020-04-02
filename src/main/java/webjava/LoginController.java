package webjava;

import database.DAO.UserDAO;
import database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    public static final String VERIFIED_USER_NAME = "verifiedUserName";

    @Autowired
    private UserDAO userDAO;

    @GetMapping(path = "/login")
    public String loginPage(@RequestParam(required = false) String login,
                         HttpSession session) {
        if (session.getAttribute(VERIFIED_USER_NAME) != null) {
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping(path ="/login")
    public String processLoginForm(HttpSession session,
                                    @RequestParam String username,
                                    @RequestParam String password) {
        if(session.getAttribute(VERIFIED_USER_NAME) != null) {
            return "redirect:/";
        }


        User user = userDAO.findUserByLogin(username);

        if (user != null && password != null && password.equals(user.getPassword())) {
            session.setAttribute(VERIFIED_USER_NAME, username);
            return "redirect:/";
        } else {
            session.setAttribute("notVerifiedName", username);
            return "redirect:/login?login=" + username;
        }
    }
}
