package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.AuthenticationAttempt;

/**
 * @author gentjan kolicaj
 */
public interface AuthenticationAttemptService {

  List<AuthenticationAttempt> getAll() throws Exception;

  List<AuthenticationAttempt> getAllById(List<Long> ids) throws Exception;

  AuthenticationAttempt getById(Long id) throws Exception;

  AuthenticationAttempt create(AuthenticationAttempt entity) throws Exception;

  List<AuthenticationAttempt> createAll(List<AuthenticationAttempt> entities)
      throws Exception;

  AuthenticationAttempt edit(AuthenticationAttempt entity) throws Exception;

  List<AuthenticationAttempt> editAll(List<AuthenticationAttempt> entities)
      throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(AuthenticationAttempt entity) throws Exception;

  void deleteAll(List<AuthenticationAttempt> entities) throws Exception;

  boolean existById(Long id) throws Exception;

  //=========================================================
  List<AuthenticationAttempt> getByUserId(Long userId) throws Exception;

}
