package jpa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.*;

import ifaces.UserManager;
import transplant.pojos.*;


public class JPAUserManager implements UserManager {	


EntityManager em;

public JPAUserManager() {
	
	em = Persistence.createEntityManagerFactory("transplant-provider").createEntityManager();
	em.getTransaction().begin();
	em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
	em.getTransaction().commit();
	// Create the needed roles
	if (this.getRoles().isEmpty()) {
		Role surgeon = new Role("surgeon");
		Role nurse = new Role("nurse");
		Role transplantUnit = new Role("transplant unit");
		this.createRole(surgeon);
		this.createRole(nurse);
		this.createRole(transplantUnit);
		
		User user = new User("transplantUnit","organ0","tUnit@dataBase.com");
		register(user);
		Role role = getRole("transplant unit"); 
		assignRole(user, role);
		
	}
}

public void close() {
	em.close();
}

@Override
public void register(User user) {
	em.getTransaction().begin();
	em.persist(user);
	em.getTransaction().commit();
}

//TODO check if this method is correct
@Override 
public void deleteUser (User user) {
	em.getTransaction().begin();
	em.remove(user);
	em.getTransaction().commit();

}

@Override
public void createRole(Role role) {
	em.getTransaction().begin();
	em.persist(role);
	em.getTransaction().commit();
}

@Override
public void assignRole(User user, Role role) {
	em.getTransaction().begin();
	user.setRole(role);
	role.addUser(user);
	em.getTransaction().commit();
}

@Override
public User login(String name, String password) {
	try {
		Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
		q.setParameter(1, name);
		q.setParameter(2, password);
		User user = (User) q.getSingleResult();
		return user;
	} catch (NoResultException e) {
		return null;
	}
}

@Override
public List<Role> getRoles() {
	Query q = em.createNativeQuery("SELECT * FROM roles", Role.class); //READING
	List<Role> roles = (List<Role>) q.getResultList(); //TODO check this casting
	/// List<Role> roles.list = q.getResultList(); ????
	return roles;
}

@Override
public Role getRole(String name) {
	Query q = em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?", Role.class);
	q.setParameter(1, name);
	Role r = (Role) q.getSingleResult();
	return r;
}

@Override
public User getNurseByEmail(String email) {
	try {
		Query sql = em.createNativeQuery("SELECT * FROM users WHERE EMAIL = ?", User.class);
		sql.setParameter(1, email);
		User user = (User) sql.getSingleResult();
		return user;
		
	} catch (NoResultException e) {
		System.out.println("Database error.");
		e.printStackTrace();
	}
	return null;
}	

}

