package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Country;
import org.teilen.web.model.Currency;
import org.teilen.web.model.Language;


/**
 * @author gentjan kolicaj
 */
public interface CountryService {

  List<Country> getAll() throws Exception;

  List<Country> getAllById(List<Long> ids) throws Exception;

  Country getById(Long id) throws Exception;

  Country create(Country entity) throws Exception;

  List<Country> createAll(List<Country> entities) throws Exception;

  Country edit(Country entity) throws Exception;

  List<Country> editAll(List<Country> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Country entity) throws Exception;

  void deleteAll(List<Country> entities) throws Exception;

  boolean existById(Long id) throws Exception;

  Country setCurrency(Country country, Currency currency) throws Exception;

  Country setLanguage(Country country, Language language) throws Exception;


}
