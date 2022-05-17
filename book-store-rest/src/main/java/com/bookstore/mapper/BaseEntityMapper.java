package com.bookstore.mapper;

import java.util.List;

public interface BaseEntityMapper<EntityObject, DomainObject>{
    EntityObject toEntity(DomainObject domainObject);

    List<EntityObject> toListEntity(List<DomainObject> domainObjects);

    DomainObject toDomainObject(EntityObject entityObject);

    List<DomainObject> toListDomainObject(List<EntityObject> entityList);
}
