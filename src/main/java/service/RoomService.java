package service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Room;
import util.HibernateUtil;

public class RoomService {
	public String save(Room room) {
		try(Session session = HibernateUtil.openSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(room);
			transaction.commit();
			session.close();
			return "Room saved successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Room getById(UUID id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.get(Room.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public List<Room> getAll() {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.createQuery("FROM Room", Room.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public String update(Room room) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(room);
            transaction.commit();
            return "Room updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating membership";
        }
    }
	
	public String delete(Long id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Room room= session.get(Room.class, id);
            if (room != null) {
                session.delete(room);
                transaction.commit();
                return "Room deleted successfully";
            } else {
                return "Room not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting room";
        }
    }
}
