package eCommerce.storage.repositories.interfaces;

import eCommerce.models.Item;

import java.util.List;
import java.util.UUID;

public interface IItemRepository extends IRepository<Item>{
    public List<Item> getByCartId(UUID cartId);
    public List<Item> getByOrderId(UUID orderId);
}
