package database.DAO.impl;

import database.DAO.UserDAO;
import database.entities.Item;
import database.entities.User;
import database.utilities.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDAOImpl implements UserDAO {
    private EntityManager manager;

    public UserDAOImpl(@Autowired EntityManager manager) {
        Objects.requireNonNull(manager, "Entity manager can't be null");
        this.manager = manager;
    }



    @Override
    public User findUserByLogin(String login) {
        try {
            return manager.createQuery("from User u where u.login= :searchLogin", User.class)
                    .setParameter("searchLogin", login)
                    .getSingleResult();
        } catch (NoResultException cause){
            return null;
        }
    }

    @Override
    public User insertUser(String login, String email, String password) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);

        manager.getTransaction().begin();
        try {
            manager.persist(user);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return user;
    }

    @Override
    public User addAddress(User user, String country, String city, String street, String postcode) {
        UserAddress address = new UserAddress(country, city, street, postcode);


        manager.getTransaction().begin();
        try {
            user.setAddress(address);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return user;
    }

    @Override
    public User updateUserPassword(User user, String password, String newPassword) {
        Objects.requireNonNull(newPassword, "New password can't be null");


        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password is incorrect");
        }



        manager.getTransaction().begin();
        try {
            user.setPassword(newPassword);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return user;
    }

    @Override
    public void removeUser(User user, String password) {

        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("Password is incorrect");
        }

        manager.getTransaction().begin();
        try {
            manager.remove(user);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();
    }


    @Override
    public boolean validatePassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    @Override
    public List<Item> getItemList(User user) {
        List<Item> items;
        manager.getTransaction().begin();
        try {
            items = user.getItems();
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();
        return items;
    }
}
