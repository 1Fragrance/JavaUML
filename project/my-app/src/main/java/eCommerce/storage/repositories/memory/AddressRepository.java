package eCommerce.storage.repositories.memory;

import eCommerce.models.Address;
import eCommerce.storage.repositories.interfaces.IAddressRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AddressRepository extends MemoryRepositoryBase<Address> implements IAddressRepository {
    @Override
    public List<Address> getListByUserId(UUID userId) {
        return dbSet.stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
    }
}
