package eCommerce.models;

import eCommerce.models.base.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ILans
 * @version 1.0
 * @created 30-май-2021 6:16:11
 */
public class Admin extends User {

	private List<Order> orders;

	public Admin(String firstName, String lastName, String email, String login, String password, String phone){

		super(firstName, lastName, email, login, password, phone);
	}

	public Admin(UUID id, String firstName, String lastName, String email, String login, String password, String phone){

		super(id, firstName, lastName, email, login, password, phone);
	}

	public String getString() {
		return "Имя: " + lastName + " " + firstName + "\nEmail: " + email + "\nТелефон: " + phone;
	}
}