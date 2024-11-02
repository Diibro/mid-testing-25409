package service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.MembershipType;
import util.HibernateUtil;

public class MembershipTypeService {

	public String save(MembershipType membershipType) {
		try(Session session = HibernateUtil.openSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(membershipType);
			transaction.commit();
			session.close();
			return "Membership Type saved successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public MembershipType getById(UUID id) {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.get(MembershipType.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public List<MembershipType> getAll() {
        try (Session session = HibernateUtil.openSession().openSession()) {
            return session.createQuery("FROM MembershipType", MembershipType.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public String update(MembershipType membership) {
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
            MembershipType membership = session.get(MembershipType.class, id);
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
