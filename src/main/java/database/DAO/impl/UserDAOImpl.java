package database.DAO.impl;

import database.DAO.UserDAO;
import database.entities.Item;
import database.entities.User;
import database.utilities.UserAddress;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager manager;


    @Override
    @Transactional
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
    @Transactional
    public User insertUser(String login, String email, String password) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);

        manager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public User addAddress(User user, String country, String city, String street, String postcode) {
        UserAddress address = new UserAddress(country, city, street, postcode);
        user.setAddress(address);

        manager.merge(user);
        return user;
    }

    @Override
    @Transactional
    public User updateUserPassword(User user, String password, String newPassword) {
        Objects.requireNonNull(newPassword, "New password can't be null");

        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password is incorrect");
        }

        user.setPassword(newPassword);
        return user;
    }

    @Override
    @Transactional
    public void removeUser(User user, String password) {

        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("Password is incorrect");
        }
        manager.remove(user);
    }


    @Override
    @Transactional
    public boolean validatePassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    @Override
    @Transactional
    public List<Item> getItemList(User user) {
        List<Item> items;

        items = user.getItems();
        return items;
    }
}
