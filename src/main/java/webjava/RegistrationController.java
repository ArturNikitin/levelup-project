package webjava;


import database.DAO.UserDAO;
import database.DAO.impl.UserDAOImpl;
import database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class RegistrationController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping(path = "/reg")
    public String getRegistrationForm(HttpSession session) {
        if (session.getAttribute(LoginController.VERIFIED_USER_NAME) != null) {
            return "redirect:/";
        }
        return "reg";
    }

    @PostMapping(path = "/reg")
    public String processRegistrationForm(@RequestParam("user") String login,
                                          @RequestParam("email") String email,
                                          @RequestParam String password) {
        try {
            User user = userDAO.insertUser(login, email, password);
            return "redirect:/login?login=" + login;
        } catch (Exception e){
            return "redirect:/reg";
        }

    }
}
