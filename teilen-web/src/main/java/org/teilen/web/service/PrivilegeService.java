package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Privilege;

/**
 * @author gentjan kolicaj
 */
public interface PrivilegeService {


  List<Privilege> getAll() throws Exception;

  List<Privilege> getAllById(List<Long> ids) throws Exception;

  Privilege getById(Long id) throws Exception;

  Privilege create(Privilege entity) throws Exception;

  List<Privilege> createAll(List<Privilege> entities) throws Exception;

  Privilege edit(Privilege entity) throws Exception;

  List<Privilege> editAll(List<Privilege> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Privilege entity) throws Exception;

  void deleteAll(List<Privilege> entities) throws Exception;

  boolean existById(Long id) throws Exception;


  List<Privilege> getByIdentifierLike(String identifier) throws Exception;

}
