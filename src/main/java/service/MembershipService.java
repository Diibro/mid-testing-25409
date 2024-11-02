package service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Membership;
import util.HibernateUtil;

public class MembershipService {
	
	public String save(Membership membership) {
		try(Session session = HibernateUtil.openSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(membership);
			transaction.commit();
			session.close();
			return "Membership saved successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Membership getById(UUID id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.get(Membership.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public List<Membership> getAll() {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.createQuery("FROM Membership", Membership.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public String update(Membership membership) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(membership);
            transaction.commit();
            return "Membership updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating membership";
        }
    }
	
	public String delete(Long id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Membership membership = session.get(Membership.class, id);
            if (membership != null) {
                session.delete(membership);
                transaction.commit();
                return "Membership deleted successfully";
            } else {
                return "Membership not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting membership";
        }
    }
}
