package com.fannog.shared;

import com.fannog.exceptions.ServicioException;
import java.util.List;

public interface DAO<T> {

    T add(T entity) throws ServicioException;

    T update(T entity) throws ServicioException;

    void delete(T entity) throws ServicioException;

    T findById(Long id);

    List<T> findAll();

}
