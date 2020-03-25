package webjava;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/reg.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("verifiedUserName") != null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }

        String username = req.getParameter("user");
        String password = req.getParameter("password");

        req.getSession().setAttribute("regUserName", username);
        req.getSession().setAttribute("regUserPass", password);

        resp.sendRedirect(req.getContextPath() + "/login?login=" + username);
    }
}
