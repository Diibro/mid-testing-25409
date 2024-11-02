package util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import model.Book;
import model.Borrower;
import model.Location;
import model.Membership;
import model.MembershipType;
import model.Room;
import model.Shelf;
import model.User;


public class HibernateUtil {
private static SessionFactory sessionFactory = null;
	
	
	public static SessionFactory openSession() {
		if(sessionFactory == null) {
			Configuration configuration = new Configuration();
			Properties property = new Properties();
			property.put(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/test_library_db");
			property.put(Environment.JAKARTA_JDBC_USER, "postgres");
			property.put(Environment.JAKARTA_JDBC_PASSWORD, "1220");
			property.put(Environment.SHOW_SQL, false);
			property.put(Environment.HBM2DDL_AUTO, "update");
//			property.put(Environment.JAKARTA_JDB, );
			
			configuration.addProperties(property);
			
			configuration.addAnnotatedClass(Book.class);
			configuration.addAnnotatedClass(Borrower.class);
			configuration.addAnnotatedClass(Location.class);
			configuration.addAnnotatedClass(Membership.class);
			configuration.addAnnotatedClass(MembershipType.class);
			configuration.addAnnotatedClass(Room.class);
			configuration.addAnnotatedClass(Shelf.class);
			configuration.addAnnotatedClass(User.class);
			
			sessionFactory = configuration.buildSessionFactory();
			return sessionFactory;
		}
		return sessionFactory;
		
		
	}
}
