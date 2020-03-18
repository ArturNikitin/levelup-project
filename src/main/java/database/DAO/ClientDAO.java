package database.DAO;

import database.entities.Client;

import java.util.List;

public interface ClientDAO {
    Client insertClient(String login, String email, String password);
    void removeClientById(int id);
}
