package eCommerce.models;


import eCommerce.interfaces.IPrintableEntity;
import eCommerce.models.base.EntityBase;

import java.util.List;
import java.util.UUID;

/**
 * @author ILans
 * @version 1.0
 * @created 30-май-2021 6:16:11
 */
public class Product extends EntityBase implements IPrintableEntity {

	private final String description;
	private final String manufacturer;
	private final double price;
	private final String name;

	private List<Item> items;

	public Product(String name, String description, String manufacturer, double price){
		super();
		this.description = description;
		this.manufacturer = manufacturer;
		this.price = price;
		this.name = name;
	}

	public Product(UUID id, String name, String description, String manufacturer, double price){
		super(id);
		this.description = description;
		this.manufacturer = manufacturer;
		this.price = price;
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getString() {
		return "Наименование: " + name + "\nПроизводитель: " + manufacturer + "\nОписание: " + description + "\nЦена: " + price;
	}
}