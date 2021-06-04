package eCommerce.storage.repositories.memory;

import eCommerce.models.Admin;
import eCommerce.storage.repositories.interfaces.IAdminRepository;

import java.util.Optional;

public class AdminRepository extends MemoryRepositoryBase<Admin> implements IAdminRepository {
    @Override
    public Optional<Admin> getByLoginAndPassword(String login, String password) {
        return dbSet.stream().filter(x -> x.getLogin().equals(login) && x.getPassword().equals(password)).findFirst();
    }
}
