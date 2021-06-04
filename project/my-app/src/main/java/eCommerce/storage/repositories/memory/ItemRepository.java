package eCommerce.storage.repositories.memory;

import eCommerce.models.Item;
import eCommerce.storage.repositories.interfaces.IItemRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ItemRepository extends MemoryRepositoryBase<Item> implements IItemRepository {
    @Override
    public List<Item> getByCartId(UUID cartId) {
        return dbSet.stream().filter(x -> x.getCartId() == cartId).collect(Collectors.toList());
    }

    @Override
    public List<Item> getByOrderId(UUID orderId) {
        return dbSet.stream().filter(x -> x.getOrderId() == orderId).collect(Collectors.toList());
    }
}
