package webjava;

import database.DAO.ItemDAO;
import database.DAO.impl.ItemDAOImpl;
import database.entities.Item;
import database.entities.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet(urlPatterns = "/photo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class addPhotoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/addPhoto.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = "E:/images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        String productName = (String) req.getSession().getAttribute("addedProductName");
        String newFileName="";
        try {
            String fileName = "";
            for (Part part : req.getParts()) {
                fileName = part.getSubmittedFileName();
                newFileName =uploadPath + File.separator + fileName;
                part.write(newFileName);
            }
        } catch (FileNotFoundException fne) {
            System.out.println(fne.getMessage());
        }
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        ItemDAO itemDAO = new ItemDAOImpl(manager);

        Item item = null;
        try {
            item = itemDAO.findItemByName(productName);
            itemDAO.addImage(item, new File(newFileName));
        } finally {
            manager.close();
        }
    }
}
