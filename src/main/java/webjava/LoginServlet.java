package webjava;

import database.DAO.UserDAO;
import database.DAO.impl.UserDAOImpl;
import database.entities.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
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

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDAO userDAO = new UserDAOImpl(manager);

        User user = null;
        try{
            if(username != null){
                user = userDAO.findUserByLogin(username);
            }
        } finally {
            manager.close();
        }

        if (user != null && password != null && password.equals(user.getPassword())) {
            req.getSession().setAttribute("verifiedUserName", username);
            req.getSession().removeAttribute("notVerifiedName");
            resp.sendRedirect(req.getContextPath());
        } else {
            req.getSession().setAttribute("notVerifiedName", username);
            resp.sendRedirect("login?login=" + username);
        }
    }
}
