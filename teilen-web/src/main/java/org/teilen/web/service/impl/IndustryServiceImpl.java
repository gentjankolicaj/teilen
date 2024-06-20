package org.teilen.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.IndustryDao;
import org.teilen.web.model.Industry;
import org.teilen.web.service.IndustryService;


/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class IndustryServiceImpl implements IndustryService {

  private final IndustryDao industryDao;

  @Autowired
  public IndustryServiceImpl(IndustryDao industryDao) {
    super();
    this.industryDao = industryDao;
  }

  @Override
  public List<Industry> getAll() throws Exception {
    return industryDao.findAll();
  }

  @Override
  public List<Industry> getAllById(List<Long> ids) throws Exception {
    return industryDao.findAllById(ids);
  }

  @Override
  public Industry getById(Long id) throws Exception {
    return industryDao.findById(id);
  }

  @Override
  public Industry create(Industry entity) throws Exception {
    return industryDao.save(entity);
  }

  @Override
  public List<Industry> createAll(List<Industry> entities) throws Exception {
    return industryDao.saveAll(entities);
  }

  @Override
  public Industry edit(Industry entity) throws Exception {
    return industryDao.update(entity);
  }

  @Override
  public List<Industry> editAll(List<Industry> entities) throws Exception {
    return industryDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    industryDao.deleteById(id);

  }

  @Override
  public void delete(Industry entity) throws Exception {
    industryDao.delete(entity);

  }

  @Override
  public void deleteAll(List<Industry> entities) throws Exception {
    industryDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return industryDao.existById(id);
  }

  @Override
  public List<Industry> getIndustryByNameLike(String name) throws Exception {
    return industryDao.findByNameLike(name);
  }


}
