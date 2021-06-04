package eCommerce.storage.repositories.interfaces;

import eCommerce.enums.OrderStatus;
import eCommerce.models.Order;

import java.util.List;
import java.util.UUID;

public interface IOrderRepository extends IRepository<Order>{
    public List<Order> getListByAuthorId(UUID authorId);
    public List<Order> getListByResponsibleAdminIdAndStatus(UUID adminId, OrderStatus status);
}
