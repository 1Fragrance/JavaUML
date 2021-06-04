package eCommerce.storage.repositories.interfaces;

import eCommerce.models.Address;

import java.util.List;
import java.util.UUID;

public interface IAddressRepository extends IRepository<Address> {
    public List<Address> getListByUserId(UUID userId);
}
