package eCommerce.models;


import eCommerce.interfaces.IPrintableEntity;
import eCommerce.models.base.EntityBase;

import java.util.UUID;

/**
 * @author ILans
 * @version 1.0
 * @created 30-май-2021 6:16:11
 */
public class Item extends EntityBase implements IPrintableEntity  {

	private final int quantity;
	private final UUID productId;
	private UUID cartId;
	private UUID orderId;

	private Product product;
	private ShoppingCart shoppingCart;
	private Order order;

	public Item(int quantity, UUID productId, UUID cartId, UUID orderId){
		super();
		this.quantity = quantity;
		this.productId = productId;
		this.cartId = cartId;
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public UUID getProductId() {
		return productId;
	}

	public UUID getCartId() {
		return cartId;
	}

	public void setCartId(UUID cartId) {
		this.cartId = cartId;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	@Override
	public String getString() {
		return "\nКолличество:" + quantity;
	}

	public String getStringWithProduct(Product product) {
		if(product == null) {
			return getString();
		}

		return "Наименование: " + product.getName() + ", количество: " + quantity + ", цена: " + quantity * product.getPrice();
	}
}