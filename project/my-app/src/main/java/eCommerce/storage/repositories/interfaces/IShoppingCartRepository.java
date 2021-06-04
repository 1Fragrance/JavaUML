package eCommerce.storage.repositories.interfaces;

import eCommerce.models.ShoppingCart;

import java.util.Optional;
import java.util.UUID;

public interface IShoppingCartRepository extends IRepository<ShoppingCart>{
    public Optional<ShoppingCart> getByCustomerId(UUID customerId);
}
