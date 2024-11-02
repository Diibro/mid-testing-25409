package service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Location;
import util.HibernateUtil;
import util.enums.ELocationType;

public class LocationService {
	public String save(Location location) {
		try(Session session = HibernateUtil.openSession().openSession()){
			Transaction transaction = session.beginTransaction();
			session.save(location);
			transaction.commit();
			session.close();
			return "Location Saved Successfully";
		}catch (Exception e) {
			e.printStackTrace();
			return "Error";
			// TODO: handle exception
		}
	}
	
	public Location getById(UUID id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.get(Location.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public List<Location> getAll() {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.createQuery("FROM Location", Location.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public String update(Location location) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(location);
            transaction.commit();
            return "Location updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating location";
        }
    }
	
	public String delete(Long id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Location location = session.get(Location.class, id);
            if (location != null) {
                session.delete(location);
                transaction.commit();
                return "Location deleted successfully";
            } else {
                return "Location not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting location";
        }
    }
	
	public List<Location> getAllByType (ELocationType type) {
		try (Session session = HibernateUtil.openSession().openSession()) {
	        return session.createQuery("FROM Location WHERE locationType = :type", Location.class)
	                .setParameter("type", type)
	                .list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
