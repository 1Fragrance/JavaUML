package eCommerce.models.base;

import java.util.UUID;

public abstract class EntityBase {
    protected final UUID id;

    protected EntityBase() {
        id = java.util.UUID.randomUUID();
    }

    protected EntityBase(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
