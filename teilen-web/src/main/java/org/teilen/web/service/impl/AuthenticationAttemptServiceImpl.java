package org.teilen.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.AuthenticationAttemptDao;
import org.teilen.web.model.AuthenticationAttempt;
import org.teilen.web.service.AuthenticationAttemptService;

@Service
@Transactional
public class AuthenticationAttemptServiceImpl implements AuthenticationAttemptService {

  private final AuthenticationAttemptDao authenticationAttemptDao;


  @Autowired
  public AuthenticationAttemptServiceImpl(AuthenticationAttemptDao authenticationAttemptDao) {
    this.authenticationAttemptDao = authenticationAttemptDao;

  }


  @Override
  public List<AuthenticationAttempt> getAll() throws Exception {
    return authenticationAttemptDao.findAll();
  }


  @Override
  public List<AuthenticationAttempt> getAllById(List<Long> ids) throws Exception {
    return authenticationAttemptDao.findAllById(ids);
  }


  @Override
  public AuthenticationAttempt getById(Long id) throws Exception {
    return authenticationAttemptDao.findById(id);
  }


  @Override
  public AuthenticationAttempt create(AuthenticationAttempt entity) throws Exception {
    return authenticationAttemptDao.save(entity);
  }


  @Override
  public List<AuthenticationAttempt> createAll(List<AuthenticationAttempt> entities)
      throws Exception {
    return authenticationAttemptDao.saveAll(entities);
  }


  @Override
  public AuthenticationAttempt edit(AuthenticationAttempt entity) throws Exception {
    return authenticationAttemptDao.update(entity);
  }


  @Override
  public List<AuthenticationAttempt> editAll(List<AuthenticationAttempt> entities)
      throws Exception {
    return authenticationAttemptDao.updateAll(entities);
  }


  @Override
  public void deleteById(Long id) throws Exception {
    authenticationAttemptDao.deleteById(id);

  }


  @Override
  public void delete(AuthenticationAttempt entity) throws Exception {
    authenticationAttemptDao.delete(entity);

  }


  @Override
  public void deleteAll(List<AuthenticationAttempt> entities) throws Exception {
    authenticationAttemptDao.deleteAll(entities);

  }


  @Override
  public boolean existById(Long id) throws Exception {
    return authenticationAttemptDao.existById(id);
  }


  @Override
  public List<AuthenticationAttempt> getByUserId(Long userId) throws Exception {
    return authenticationAttemptDao.findByUserId(userId);
  }


}
