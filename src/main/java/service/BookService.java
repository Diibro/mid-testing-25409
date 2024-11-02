package service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Book;
import util.HibernateUtil;

public class BookService {

	public String save(Book book ) {
		try(Session session = HibernateUtil.openSession().openSession()) {
			
			Transaction transaction = session.beginTransaction();
			session.save(book);
			transaction.commit();
			session.close();
			
			return "New book saved";
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public Book getById(UUID id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Book book = session.get(Book.class, id);
            session.close();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public List<Book> getAll() {
        try (Session session = HibernateUtil.openSession().openSession()) {
        	return session.createQuery("FROM Book", Book.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public String update(Book book) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
            session.close();
            return "Book updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating book";
        }
    }
	
	public String delete(Long id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book != null) {
                session.delete(book);
                transaction.commit();
                session.close();
                return "Book deleted successfully";
            } else {
                return "Book not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting book";
        }
    }
	
}
