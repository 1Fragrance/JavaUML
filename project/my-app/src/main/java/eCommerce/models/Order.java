package eCommerce.models;


import eCommerce.enums.OrderStatus;
import eCommerce.interfaces.IPrintableEntity;
import eCommerce.models.base.EntityBase;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author ILans
 * @version 1.0
 * @created 30-май-2021 6:16:11
 */
public class Order extends EntityBase implements IPrintableEntity {

	private Date orderDate;
	private double sum;
	private UUID billId;
	private UUID responsibleAdminId;
	private UUID addressId;
	private UUID authorId;

	private Bill bill;
	private Admin responsibleAdmin;
	private Address address;
	private Customer author;
	private List<Item> items;

	private OrderStatus orderStatus;

	public Order(Date orderDate, double sum, UUID billId, UUID responsibleAdminId, UUID addressid, OrderStatus orderStatus, UUID authorId){
		super();
		this.orderDate = orderDate;
		this.sum = sum;
		this.billId = billId;
		this.responsibleAdminId = responsibleAdminId;
		this.addressId = addressid;
		this.orderStatus = orderStatus;
		this.authorId = authorId;
	}

	public Order(UUID id, Date orderDate, double sum, UUID billId, UUID responsibleAdminId, UUID addressid, OrderStatus orderStatus, UUID authorId){
		super(id);
		this.orderDate = orderDate;
		this.sum = sum;
		this.billId = billId;
		this.responsibleAdminId = responsibleAdminId;
		this.addressId = addressid;
		this.orderStatus = orderStatus;
		this.authorId = authorId;
	}


	public void setBillId(UUID billId) {
		this.billId = billId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public double getSum() {
		return sum;
	}

	public UUID getBillId() {
		return billId;
	}

	public UUID getResponsibleAdminId() {
		return responsibleAdminId;
	}

	public UUID getAddressId() {
		return addressId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public UUID getAuthorId() {
		return authorId;
	}

	@Override
	public String getString() {
		return "Номер: " + getId().toString() + ", дата заказа: " + orderDate + ", сумма: " + sum;
	}
}