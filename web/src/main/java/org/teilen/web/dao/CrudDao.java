package org.teilen.web.dao;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface CrudDao<E, ID> {

    public abstract List<E> findAll() throws Exception;

    public abstract List<E> findAllById(List<ID> ids) throws Exception;

    public abstract E findById(ID id) throws Exception;

    public abstract E save(E entity) throws Exception;

    public abstract List<E> saveAll(List<E> entities) throws Exception;

    public abstract E update(E entity) throws Exception;

    public abstract List<E> updateAll(List<E> entities) throws Exception;

    public abstract void deleteById(ID id) throws Exception;

    public abstract void delete(E entity) throws Exception;

    public abstract void deleteAll(List<E> entities) throws Exception;

    public abstract boolean existById(ID id) throws Exception;


}
