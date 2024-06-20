package org.teilen.web.dao;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface CrudDao<E, ID> {

  List<E> findAll() throws Exception;

  List<E> findAllById(List<ID> ids) throws Exception;

  E findById(ID id) throws Exception;

  E save(E entity) throws Exception;

  List<E> saveAll(List<E> entities) throws Exception;

  E update(E entity) throws Exception;

  List<E> updateAll(List<E> entities) throws Exception;

  void deleteById(ID id) throws Exception;

  void delete(E entity) throws Exception;

  void deleteAll(List<E> entities) throws Exception;

  boolean existById(ID id) throws Exception;


}
