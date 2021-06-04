package eCommerce.storage.repositories.memory;

import eCommerce.models.Product;
import eCommerce.storage.repositories.interfaces.IProductRepository;

public class ProductRepository extends MemoryRepositoryBase<Product> implements IProductRepository {
}
