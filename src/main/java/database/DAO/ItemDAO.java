package database.DAO;

import database.entities.Item;
import database.entities.Order;
import database.entities.User;
import database.utilities.ClothingSize;
import database.utilities.ClothingType;
import database.utilities.Price;

public interface ItemDAO {
    Item createItem(User user, String name, Price price, ClothingSize size, ClothingType type);
    Item setType(Item item, ClothingType type);
    Item connectToOrder(Item item, Order order);
    Item setItemSold(Item item);
    Item setItemAvailable(Item Item);
    Item setItemOrdered(Item item);
    Item findItemById(int id);
    Item findItemByName(String name);
    Item approveItem(Item item);
}
