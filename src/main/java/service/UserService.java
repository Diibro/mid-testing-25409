package service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import model.User;
import util.HibernateUtil;

public class UserService {

	public String save(User user) {
        try (Session session = HibernateUtil.openSession().openSession()) {
        	user.setPassword(hashPassword(user.getPassword()));
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return "User saved successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error saving user";
        }
    }
	
	public User getById(String id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
        	User user = session.get(User.class, id);
        	session.close();
        	return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	private User getByUsername(String username) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            User user =  session.createQuery("FROM User WHERE username = :username", User.class)
                          .setParameter("username", username)
                          .uniqueResult();
            session.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public List<User> getAll() {
        try (Session session = HibernateUtil.openSession().openSession()) {
            List<User> users =  session.createQuery("FROM User", User.class).list();
            session.close();
            return users;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public String authenticate(User user) {
        User existingUser = getByUsername(user.getUsername());
        if (user != null) {
            if (validatePassword(user.getPassword(), existingUser.getPassword())) {
                return "Authentication successful";
            } else {
                return "Invalid password";
            }
        } else {
            return "User not found";
        }
    }
	
	public String update(User user) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
            return "User updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating user";
        }
    }
	
	public String delete(String id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                session.close();
                return "User deleted successfully";
            } else {
                return "User not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting user";
        }
    }
	
	public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public boolean validatePassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
