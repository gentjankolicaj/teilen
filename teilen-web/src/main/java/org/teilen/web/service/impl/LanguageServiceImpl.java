package org.teilen.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.LanguageDao;
import org.teilen.web.model.Language;
import org.teilen.web.service.LanguageService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

  private final LanguageDao languageDao;

  @Autowired
  public LanguageServiceImpl(LanguageDao languageDao) {
    super();
    this.languageDao = languageDao;
  }

  @Override
  public List<Language> getAll() throws Exception {
    return languageDao.findAll();
  }

  @Override
  public List<Language> getAllById(List<Long> ids) throws Exception {
    return languageDao.findAllById(ids);
  }

  @Override
  public Language getById(Long id) throws Exception {
    return languageDao.findById(id);
  }

  @Override
  public Language create(Language entity) throws Exception {
    return languageDao.save(entity);
  }

  @Override
  public List<Language> createAll(List<Language> entities) throws Exception {
    return languageDao.saveAll(entities);
  }

  @Override
  public Language edit(Language entity) throws Exception {
    return languageDao.update(entity);
  }

  @Override
  public List<Language> editAll(List<Language> entities) throws Exception {
    return languageDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    languageDao.deleteById(id);

  }

  @Override
  public void delete(Language entity) throws Exception {
    languageDao.delete(entity);

  }

  @Override
  public void deleteAll(List<Language> entities) throws Exception {
    languageDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return languageDao.existById(id);
  }

  @Override
  public List<Language> getByLangLike(String lang) throws Exception {
    return languageDao.findByLangLike(lang);
  }


}
