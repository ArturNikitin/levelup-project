package database.DAO;


import database.entities.Order;
import database.entities.User;
import database.utilities.UserAddress;



public interface OrderDAO {
    Order createOrderWithUser(User user);
    Order createOrder(String email, UserAddress address);
    Order cancelOrder(int id);
    Order updateStatusToShipped(int id);
    Order findOrderById(int id);
}
