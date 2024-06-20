package org.teilen.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.CountryDao;
import org.teilen.web.model.Country;
import org.teilen.web.model.Currency;
import org.teilen.web.model.Language;
import org.teilen.web.service.CountryService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService {

  private final CountryDao countryDao;

  @Autowired
  public CountryServiceImpl(CountryDao countryDao) {
    this.countryDao = countryDao;
  }

  @Override
  public List<Country> getAll() throws Exception {
    return countryDao.findAll();
  }

  @Override
  public List<Country> getAllById(List<Long> ids) throws Exception {
    return countryDao.findAllById(ids);
  }

  @Override
  public Country getById(Long id) throws Exception {
    return countryDao.findById(id);
  }

  @Override
  public Country create(Country entity) throws Exception {
    return countryDao.save(entity);
  }

  @Override
  public List<Country> createAll(List<Country> entities) throws Exception {
    return countryDao.saveAll(entities);
  }

  @Override
  public Country edit(Country entity) throws Exception {
    return countryDao.update(entity);
  }

  @Override
  public List<Country> editAll(List<Country> entities) throws Exception {
    return countryDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    countryDao.deleteById(id);

  }

  @Override
  public void delete(Country entity) throws Exception {
    countryDao.delete(entity);

  }

  @Override
  public void deleteAll(List<Country> entities) throws Exception {
    countryDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return countryDao.existById(id);
  }

  @Override
  public Country setCurrency(Country country, Currency currency) throws Exception {
    List<Currency> list = country.getCurrencies();
    if (list == null) {
      list = new ArrayList<>(1);
      list.add(currency);
      country.setCurrencies(list);
    } else {
      country.getCurrencies().add(currency);
    }

    return countryDao.update(country);

  }

  @Override
  public Country setLanguage(Country country, Language language) throws Exception {
    List<Language> list = country.getLanguages();
    if (list == null) {
      list = new ArrayList<>(1);
      list.add(language);
      country.setLanguages(list);
    } else {
      country.getLanguages().add(language);
    }

    return countryDao.update(country);
  }

}
