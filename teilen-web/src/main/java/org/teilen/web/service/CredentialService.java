package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Credential;
import org.teilen.web.model.User;

/**
 * @author gentjan kolicaj
 */
public interface CredentialService {

  List<Credential> getAll() throws Exception;

  List<Credential> getAllById(List<Long> ids) throws Exception;

  Credential getById(Long id) throws Exception;

  Credential create(Credential entity) throws Exception;

  List<Credential> createAll(List<Credential> entities) throws Exception;

  Credential edit(Credential entity) throws Exception;

  List<Credential> editAll(List<Credential> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Credential entity) throws Exception;

  void deleteAll(List<Credential> entities) throws Exception;

  boolean existById(Long id) throws Exception;


  //==============================================================
  Credential getByUserId(Long userId) throws Exception;

  Credential changePassword(User user, String newPassword) throws Exception;

}
