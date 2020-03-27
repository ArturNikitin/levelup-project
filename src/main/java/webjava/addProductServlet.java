package webjava;

import database.DAO.ItemDAO;
import database.DAO.UserDAO;
import database.DAO.impl.ItemDAOImpl;
import database.DAO.impl.UserDAOImpl;
import database.entities.Item;
import database.entities.User;
import database.utilities.ClothingSize;
import database.utilities.ClothingType;
import database.utilities.Price;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/create")
public class addProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("verifiedUserName") == null) {
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/pages/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("productName");
        String price = req.getParameter("price");
        String type = req.getParameter("type");
        ClothingType clothingType = ClothingType.valueOf(type);
        String size = req.getParameter("size");
        ClothingSize clothingSize = ClothingSize.valueOf(size);
        String description = req.getParameter("description");
        String username = (String) req.getSession().getAttribute("verifiedUserName");

        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDAO userDAO = new UserDAOImpl(manager);
        ItemDAO itemDAO = new ItemDAOImpl(manager);
        if(username != null){
            User user = null;
            try {
                user = userDAO.findUserByLogin(username);
                itemDAO.createItem(user, name, new Price(Double.parseDouble(price)), clothingSize, clothingType);
            } catch (Exception e){
                System.out.println(e.getCause());
            } finally {
                manager.close();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
