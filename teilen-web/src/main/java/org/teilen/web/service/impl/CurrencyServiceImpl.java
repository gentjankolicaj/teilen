package org.teilen.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.CurrencyDao;
import org.teilen.web.model.Currency;
import org.teilen.web.service.CurrencyService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

  private final CurrencyDao currencyDao;

  @Autowired
  public CurrencyServiceImpl(CurrencyDao currencyDao) {
    this.currencyDao = currencyDao;
  }

  @Override
  public List<Currency> getAll() throws Exception {
    return currencyDao.findAll();
  }

  @Override
  public List<Currency> getAllById(List<Long> ids) throws Exception {
    return currencyDao.findAllById(ids);
  }

  @Override
  public Currency getById(Long id) throws Exception {
    return currencyDao.findById(id);
  }

  @Override
  public Currency create(Currency entity) throws Exception {
    return currencyDao.save(entity);
  }

  @Override
  public List<Currency> createAll(List<Currency> entities) throws Exception {
    return currencyDao.saveAll(entities);
  }

  @Override
  public Currency edit(Currency entity) throws Exception {
    return currencyDao.update(entity);
  }

  @Override
  public List<Currency> editAll(List<Currency> entities) throws Exception {
    return currencyDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    currencyDao.deleteById(id);

  }

  @Override
  public void delete(Currency entity) throws Exception {
    currencyDao.delete(entity);

  }

  @Override
  public void deleteAll(List<Currency> entities) throws Exception {
    currencyDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return currencyDao.existById(id);
  }

  @Override
  public List<Currency> getCurrencyByNameLike(String name) throws Exception {
    return currencyDao.findByNameLike(name);
  }

}
