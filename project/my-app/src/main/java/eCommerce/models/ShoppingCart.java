package eCommerce.models;


import eCommerce.models.base.EntityBase;

import java.util.List;
import java.util.UUID;

/**
 * @author ILans
 * @version 1.0
 * @created 30-май-2021 6:16:11
 */
public class ShoppingCart extends EntityBase {

	private final UUID customerId;
	private boolean hasItems;

	private Customer customer;
	private List<Item> items;

	public ShoppingCart(UUID customerId){
		super();
		this.customerId = customerId;
		hasItems = false;
	}

	public boolean getHasItems() {
		return hasItems;
	}

	public void setHasItems(boolean value) {
		hasItems = value;
	}

	public UUID getCustomerId() {
		return customerId;
	}
}