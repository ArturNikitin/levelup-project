package webjava;

import database.DAO.UserDAO;
import database.DAO.impl.UserDAOImpl;
import database.entities.User;
import database.utilities.UserAddress;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/profile"})
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("verifiedUserName") == null ){
            resp.sendRedirect("login");
        } else {
            EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
            UserDAO userDAO = new UserDAOImpl(manager);
            User user = null;
            try {
                user = userDAO.findUserByLogin((String) req.getSession().getAttribute("verifiedUserName"));
            } finally {
              manager.close();
            }
            ProfileForm form = new ProfileForm(new UserAddress("", "", "", ""));
            if (user.getAddress() != null) {
                form.setAddress(user.getAddress());
            }
            req.setAttribute("form", form);
            req.getRequestDispatcher("pages/profile.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String postcode = req.getParameter("postcode");
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDAO userDAO = new UserDAOImpl(manager);
        try{
            User user = userDAO.findUserByLogin((String) req.getSession().getAttribute("verifiedUserName"));
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
            resp.sendRedirect(req.getContextPath());
        } finally {
            manager.close();
        }
    }
}
