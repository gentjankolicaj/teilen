package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Department;
import org.teilen.web.model.Organization;
import org.teilen.web.model.User;

/**
 * @author gentjan kolicaj
 */
public interface DepartmentService {

  List<Department> getAll() throws Exception;

  List<Department> getAllById(List<Long> ids) throws Exception;

  Department getById(Long id) throws Exception;

  Department create(Department entity) throws Exception;

  List<Department> createAll(List<Department> entities) throws Exception;

  Department edit(Department entity) throws Exception;

  List<Department> editAll(List<Department> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Department entity) throws Exception;

  void deleteAll(List<Department> entities) throws Exception;

  boolean existById(Long id) throws Exception;

  List<Department> getDepartmentByName(String name) throws Exception;

  List<Department> getDepartmentByCreatorId(Long creatorId) throws Exception;

  //==============================
  Department changeName(Department department, String name) throws Exception;

  Department changeDescription(Department department, String description)
      throws Exception;

  Department createDepartment(User user, Organization organization, String name,
      String description) throws Exception;


}
