package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Organization;

/**
 * @author gentjan kolicaj
 */
public interface OrganizationService {


  List<Organization> getAll() throws Exception;

  List<Organization> getAllById(List<Long> ids) throws Exception;

  Organization getById(Long id) throws Exception;

  Organization create(Organization entity) throws Exception;

  List<Organization> createAll(List<Organization> entities) throws Exception;

  Organization edit(Organization entity) throws Exception;

  List<Organization> editAll(List<Organization> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Organization entity) throws Exception;

  void deleteAll(List<Organization> entities) throws Exception;

  boolean existById(Long id) throws Exception;
}
