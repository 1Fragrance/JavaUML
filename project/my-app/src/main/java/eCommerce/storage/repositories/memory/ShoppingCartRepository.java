package eCommerce.storage.repositories.memory;

import eCommerce.models.ShoppingCart;
import eCommerce.storage.repositories.interfaces.IShoppingCartRepository;

import java.util.Optional;
import java.util.UUID;

public class ShoppingCartRepository extends MemoryRepositoryBase<ShoppingCart> implements IShoppingCartRepository {
    @Override
    public Optional<ShoppingCart> getByCustomerId(UUID customerId) {
        return dbSet.stream().filter(x -> x.getCustomerId() == customerId).findFirst();
    }
}
