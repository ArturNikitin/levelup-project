package database.DAO;

import database.entities.Item;
import database.entities.Order;
import database.entities.User;
import database.utilities.ClothingSize;
import database.utilities.ClothingStatus;
import database.utilities.ClothingType;
import database.utilities.Price;

import java.io.File;

public interface ItemDAO {
    Item createItem(User user, String name, Price price, ClothingSize size, ClothingType type);
    Item setType(Item item, ClothingType type);
    Item connectToOrder(Item item, Order order);
    Item setItemStatus(Item Item, ClothingStatus status);
    Item findItemByName(String name);
    Item changeSize(Item item, ClothingSize size);
    Item addImage(Item item, File file);
    Item addDescription(Item item, String description);
}
