package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Currency;

/**
 * @author gentjan kolicaj
 */
public interface CurrencyService {

  List<Currency> getAll() throws Exception;

  List<Currency> getAllById(List<Long> ids) throws Exception;

  Currency getById(Long id) throws Exception;

  Currency create(Currency entity) throws Exception;

  List<Currency> createAll(List<Currency> entities) throws Exception;

  Currency edit(Currency entity) throws Exception;

  List<Currency> editAll(List<Currency> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Currency entity) throws Exception;

  void deleteAll(List<Currency> entities) throws Exception;

  boolean existById(Long id) throws Exception;

  //======================================================

  List<Currency> getCurrencyByNameLike(String name) throws Exception;

}
