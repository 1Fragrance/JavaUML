package eCommerce.models;


import eCommerce.interfaces.IPrintableEntity;
import eCommerce.models.base.EntityBase;

import java.util.Date;
import java.util.UUID;

/**
 * @author ILans
 * @version 1.0
 * @created 30-май-2021 6:16:11
 */
public class Bill extends EntityBase implements IPrintableEntity {

	private final Date issueDate;
	private final double sum;
	private final UUID orderId;

	private Order order;

	public Bill(Date issueDate, double sum, UUID orderId){
		super();
		this.issueDate = issueDate;
		this.sum = sum;
		this.orderId = orderId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public double getSum() {
		return sum;
	}

	public UUID getOrderId() {
		return orderId;
	}

	@Override
	public String getString() {
		return "Дата оплаты " + issueDate + ", сумма: " + sum;
	}
}