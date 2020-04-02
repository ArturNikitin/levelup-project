package webjava;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

//@WebServlet(urlPatterns = "/marketplace")
@Controller
public class IndexController {

    @GetMapping(path = "/marketplace/")
    public String getFirstPage() {
        return "redirect:/";
    }

    @PostMapping(path = "/marketplace")
    public String processLogOutButton(HttpSession session) {
        session.setAttribute(LoginController.VERIFIED_USER_NAME, null);
        return "redirect:/";
    }
}
