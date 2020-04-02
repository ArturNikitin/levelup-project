package webjava;

import database.DAO.ItemDAO;
import database.DAO.UserDAO;
import database.entities.User;
import database.utilities.ClothingSize;
import database.utilities.ClothingType;
import database.utilities.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


//@WebServlet(urlPatterns = "/create")
@Controller
public class AddProductController {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ItemDAO itemDAO;

    @GetMapping(path = "/create")
    public String getCreateProductForm(HttpSession session) {
        if(session.getAttribute(LoginController.VERIFIED_USER_NAME) == null) {
            return "login";
        }
        return "addProduct";
    }

    @PostMapping(path = "/create")
    public String processCreateProductForm(@RequestParam("productName") String name,
                                           @RequestParam("price") double price,
                                           @RequestParam String type,
                                           @RequestParam String size,
                                           @RequestParam(required = false) String description,
                                           HttpSession session) {

        ClothingType clothingType = ClothingType.valueOf(type);
        ClothingSize clothingSize = ClothingSize.valueOf(size);
        String username = (String) session.getAttribute(LoginController.VERIFIED_USER_NAME);

        User user = userDAO.findUserByLogin(username);
        itemDAO.createItem(user, name, new Price(price), clothingSize, clothingType);
        session.setAttribute("addedProductName", name);

        return "redirect:/profile";
//        resp.sendRedirect(req.getContextPath() + "/photo");
    }
}
