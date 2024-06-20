package org.teilen.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.PrivilegeDao;
import org.teilen.web.model.Privilege;
import org.teilen.web.service.PrivilegeService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

  private final PrivilegeDao privilegeDao;

  @Autowired
  public PrivilegeServiceImpl(PrivilegeDao privilegeDao) {
    this.privilegeDao = privilegeDao;
  }

  @Override
  public List<Privilege> getAll() throws Exception {
    return privilegeDao.findAll();
  }

  @Override
  public List<Privilege> getAllById(List<Long> ids) throws Exception {
    return privilegeDao.findAllById(ids);
  }

  @Override
  public Privilege getById(Long id) throws Exception {
    return privilegeDao.findById(id);
  }

  @Override
  public Privilege create(Privilege entity) throws Exception {
    return privilegeDao.save(entity);
  }

  @Override
  public List<Privilege> createAll(List<Privilege> entities) throws Exception {
    return privilegeDao.saveAll(entities);
  }

  @Override
  public Privilege edit(Privilege entity) throws Exception {
    return privilegeDao.update(entity);
  }

  @Override
  public List<Privilege> editAll(List<Privilege> entities) throws Exception {
    return privilegeDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    privilegeDao.deleteById(id);

  }

  @Override
  public void delete(Privilege entity) throws Exception {
    privilegeDao.delete(entity);

  }

  @Override
  public void deleteAll(List<Privilege> entities) throws Exception {
    privilegeDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return privilegeDao.existById(id);
  }

  @Override
  public List<Privilege> getByIdentifierLike(String identifier) throws Exception {
    return privilegeDao.findByIdentifierLike(identifier);
  }


}
