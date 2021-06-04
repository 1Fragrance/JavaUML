package eCommerce.models;

import eCommerce.models.base.User;
import eCommerce.enums.CustomerStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ILans
 * @version 1.0
 * @created 30-май-2021 6:16:11
 */
public class Customer extends User {

	private final CustomerStatus customerStatus;

	private ShoppingCart shoppingCart;
	private List<Address> addresses;
	private List<Order> orders;

	public Customer(String firstName, String lastName, String email, String login, String password, String phone, CustomerStatus customerStatus) {

		super(firstName, lastName, email, login, password, phone);
		this.customerStatus = customerStatus;
	}

	public Customer(UUID id, String firstName, String lastName, String email, String login, String password, String phone, CustomerStatus customerStatus) {
		super(id, firstName, lastName, email, login, password, phone);
		this.customerStatus = customerStatus;
	}

	public CustomerStatus getCustomerStatus() {
		return customerStatus;
	}

	@Override
	public String getString() {
		return "Имя: " + lastName + " " + firstName + "\nEmail: " + email + "\nТелефон: " + phone + "\nСтатус: " + customerStatus.name();
	}
}