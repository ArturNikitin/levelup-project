package webjava;

import database.DAO.ItemDAO;
import database.DAO.OrderDAO;
import database.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private OrderDAO orderDAO;
}
