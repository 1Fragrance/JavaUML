package eCommerce.models.base;


import eCommerce.interfaces.IPrintableEntity;

import java.util.UUID;

/**
 * @author ILans
 * @version 1.0
 * @created 30-май-2021 6:16:11
 */
public abstract class User extends EntityBase implements IPrintableEntity {

	protected final String firstName;
	protected final String lastName;
	protected final String email;
	protected final String login;
	protected final String password;
	protected final String phone;

	public User(String firstName, String lastName, String email, String login, String password, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.login = login;
		this.password = password;
		this.phone = phone;
	}

	public User(UUID id, String firstName, String lastName, String email, String login, String password, String phone) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.login = login;
		this.password = password;
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}
}