package database.DAO;

import database.entities.Item;

public interface ItemDAO {
    Item addItem(String name);
    Item connectItemToClientById(String name, int id);
}
