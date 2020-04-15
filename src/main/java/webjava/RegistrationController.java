package webjava;


import database.DAO.UserDAO;
import database.DAO.impl.UserDAOImpl;
import database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @ModelAttribute("form")
    public RegistrationForm createForm(){
       return new RegistrationForm("", "", "");
    }

    @GetMapping(path = "/reg")
    public String getRegistrationForm(HttpSession session) {
        if (session.getAttribute(LoginController.VERIFIED_USER_NAME) != null) {
            return "redirect:/";
        }
        return "reg";
    }

    @PostMapping(path = "/reg")
    public String processRegistrationForm(
            ModelMap model,
            @Validated
            @ModelAttribute("form") RegistrationForm form,
            BindingResult validationResult
            /*@RequestParam("user") String login,
                                          @RequestParam("email") String email,
                                          @RequestParam String password*/) {
        if(validationResult.hasErrors()) {
            return "reg";
        }

        try {
            User user = userDAO.insertUser(form.getLogin(), form.getEmail(), form.getPasswrod());
            return "redirect:/login?login=" + form.getLogin();
        } catch (Exception e){
            validationResult.addError(
                    new FieldError("form", "login",
                            "User with login " + form.getLogin() + " is already established")
            );

            return "reg";
        }

    }
}
