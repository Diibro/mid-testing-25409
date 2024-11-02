package service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Shelf;
import util.HibernateUtil;

public class ShelfService {

	public String save(Shelf shelf) {
		try (Session session = HibernateUtil.openSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(shelf);
			transaction.commit();
			session.close();
			return "Shelf save successfully";
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Shelf getById(UUID id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.get(Shelf.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public List<Shelf> getAll() {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.createQuery("FROM Shelf", Shelf.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public String update(Shelf shelf) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(shelf);
            transaction.commit();
            return "Shelf updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating shelf";
        }
    }
	
	public String delete(Long id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Shelf shelf = session.get(Shelf.class, id);
            if (shelf != null) {
                session.delete(shelf);
                transaction.commit();
                return "Shelf deleted successfully";
            } else {
                return "Shelf not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting shelf";
        }
    }
	
}
