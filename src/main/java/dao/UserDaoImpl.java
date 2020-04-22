package dao;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Login;
import model.User;

public class UserDaoImpl implements UserDao {

	SessionFactory sf =  new Configuration().configure().buildSessionFactory();
	Session session = sf.openSession();
	org.hibernate.Transaction tx = null;
	
	public void register(User user) {
		tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		
		
	}

	public User validateUser(Login login) {
		tx= session.beginTransaction();
		Query query = session.createQuery("from User  where username='"+login.getUsername()+"'and password='"+login.getPassword()+"'");
		List<User> user = query.list();
		return user.get(0);
	}

}
