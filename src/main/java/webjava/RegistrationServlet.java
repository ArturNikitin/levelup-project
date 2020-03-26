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

        String login = req.getParameter("user");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDAO userDAO = new UserDAOImpl(manager);
        User user = null;
        try {
            user = userDAO.findUserByLogin(login);
            if(user != null){
                resp.sendRedirect(req.getContextPath() + "/login?login=" + login);
                return;
            }
        } catch (Exception exp){

        }

        try {
            user = userDAO.insertUser(login, email, password);
        } finally {
            manager.close();
        }
        if(user != null) {
            resp.sendRedirect(req.getContextPath() + "/login?login=" + login);
        }
    }
}
