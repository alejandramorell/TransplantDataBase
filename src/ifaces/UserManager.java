package ifaces;

import java.util.List;

import transplant.pojos.Role;
import transplant.pojos.User;

public interface UserManager {

	public void register(User user);
	public void deleteUser(User user);
	public void createRole(Role role);
	public Role getRole(String name);
	public List<Role> getRoles();
	public void assignRole(User user, Role role);
	public User login(String name, String password);
	public User getNurseByEmail(String email); 

}
