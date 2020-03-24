package Web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login" })
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("verifiedUserName") != null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null && username.equals("test") && password.equals("123")) {
            req.getSession().setAttribute("verifiedUserName", username);

            resp.sendRedirect(req.getContextPath());
        } else {
            req.getSession().setAttribute("notVerifiedName", username);
            resp.sendRedirect("login?login=" + username);
        }
    }
}