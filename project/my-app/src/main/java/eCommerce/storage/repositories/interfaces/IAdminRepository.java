package eCommerce.storage.repositories.interfaces;

import eCommerce.models.Admin;

import java.util.Optional;

public interface IAdminRepository extends IRepository<Admin>{
    public Optional<Admin> getByLoginAndPassword(String login, String password);
}
