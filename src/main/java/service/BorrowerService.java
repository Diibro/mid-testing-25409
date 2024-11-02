package service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Borrower;
import util.HibernateUtil;

public class BorrowerService {

	public String save(Borrower borrower) {
		try(Session session = HibernateUtil.openSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(borrower);
			transaction.commit();
			session.close();
			return "Borrower saved successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Borrower getById(UUID id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.get(Borrower.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public List<Borrower> getAll() {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.createQuery("FROM Borrower", Borrower.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public String update(Borrower borrower) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(borrower);
            transaction.commit();
            return "Borrower updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating borrower";
        }
    }
	
	public String delete(Long id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Borrower borrower = session.get(Borrower.class, id);
            if (borrower != null) {
                session.delete(borrower);
                transaction.commit();
                return "Borrower deleted successfully";
            } else {
                return "Borrower not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting borrower";
        }
    }
	
}
