package eCommerce.storage.repositories.memory;

import eCommerce.storage.repositories.interfaces.IRepository;
import eCommerce.models.base.EntityBase;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public abstract class MemoryRepositoryBase<T extends EntityBase> implements IRepository<T> {

    protected ArrayList<T> dbSet;

    protected MemoryRepositoryBase()
    {
        dbSet = new ArrayList<>();
    }

    @Override
    public T insert(T entity) {
        dbSet.add(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        // Nothing to do - everything in memory
        return entity;
    }

    @Override
    public void delete(T entity) {
        dbSet.remove(entity);
    }

    @Override
    public ArrayList<T> getList() {
        return dbSet;
    }

    @Override
    public Optional<T> getById(UUID id) {
        return dbSet.stream().filter(x -> x.getId() == id).findFirst();
    }
}
