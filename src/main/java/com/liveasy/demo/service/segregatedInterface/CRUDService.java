package com.liveasy.demo.service.segregatedInterface;

import java.util.List;

public interface CRUDService<T> {
    List<?> listAll();
    T getById(Long id);
    T saveOrUpdate(T domainObject);
    void delete(Long id);
}
