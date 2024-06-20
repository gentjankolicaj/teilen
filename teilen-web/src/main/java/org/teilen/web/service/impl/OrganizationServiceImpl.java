package org.teilen.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.OrganizationDao;
import org.teilen.web.model.Organization;
import org.teilen.web.service.OrganizationService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {


  private final OrganizationDao organizationDao;

  @Autowired
  public OrganizationServiceImpl(OrganizationDao organizationDao) {
    this.organizationDao = organizationDao;
  }

  @Override
  public List<Organization> getAll() throws Exception {
    return organizationDao.findAll();
  }

  @Override
  public List<Organization> getAllById(List<Long> ids) throws Exception {
    return organizationDao.findAllById(ids);
  }

  @Override
  public Organization getById(Long id) throws Exception {
    return organizationDao.findById(id);
  }

  @Override
  public Organization create(Organization entity) throws Exception {
    return organizationDao.save(entity);
  }

  @Override
  public List<Organization> createAll(List<Organization> entities) throws Exception {
    return organizationDao.saveAll(entities);
  }

  @Override
  public Organization edit(Organization entity) throws Exception {
    return organizationDao.update(entity);
  }

  @Override
  public List<Organization> editAll(List<Organization> entities) throws Exception {
    return organizationDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    organizationDao.deleteById(id);

  }

  @Override
  public void delete(Organization entity) throws Exception {
    organizationDao.delete(entity);

  }

  @Override
  public void deleteAll(List<Organization> entities) throws Exception {
    organizationDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return organizationDao.existById(id);
  }


}
