package eCommerce.models;


import eCommerce.interfaces.IPrintableEntity;
import eCommerce.models.base.EntityBase;
import eCommerce.models.base.User;

import java.util.List;
import java.util.UUID;

/**
 * @author ILans
 * @version 1.0
 * @created 30-май-2021 6:16:11
 */
public class Address extends EntityBase implements IPrintableEntity {

	// TODO: make third normalization
	private final String city;
	private final String country;
	private final String street;

	private final String apartmentNumber;
	private final String houseNumber;
	private final UUID userId;

	private Customer customer;
	private List<Order> orders;

	public Address(UUID userId, String apartmentNumber, String city, String country, String houseNumber, String street) {
		super();
		this.userId = userId;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.country = country;
		this.houseNumber = houseNumber;
		this.street = street;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public String getStreet() {
		return street;
	}

	public UUID getId() {
		return id;
	}

	public UUID getUserId() {
		return userId;
	}

	@Override
	public String getString() {
		return country + ", " + city + ", " + street + ", " + houseNumber + ", " + apartmentNumber;
	}
}