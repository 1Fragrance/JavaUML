package eCommerce.storage.repositories.memory;

import eCommerce.enums.OrderStatus;
import eCommerce.models.Order;
import eCommerce.storage.repositories.interfaces.IOrderRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderRepository extends MemoryRepositoryBase<Order> implements IOrderRepository {
    @Override
    public List<Order> getListByAuthorId(UUID authorId) {
        return dbSet.stream().filter(x -> x.getAuthorId() == authorId).collect(Collectors.toList());
    }

    @Override
    public List<Order> getListByResponsibleAdminIdAndStatus(UUID adminId, OrderStatus status) {
        return dbSet.stream().filter(x -> x.getResponsibleAdminId() == adminId && x.getOrderStatus() == status).collect(Collectors.toList());
    }
}
