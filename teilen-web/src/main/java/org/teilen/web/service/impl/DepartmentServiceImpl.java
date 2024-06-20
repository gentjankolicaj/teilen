package org.teilen.web.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.DepartmentDao;
import org.teilen.web.exception.DepartmentException;
import org.teilen.web.model.Department;
import org.teilen.web.model.Organization;
import org.teilen.web.model.User;
import org.teilen.web.service.DepartmentService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentDao departmentDao;

  @Autowired
  public DepartmentServiceImpl(DepartmentDao departmentDao) {
    this.departmentDao = departmentDao;
  }

  @Override
  public List<Department> getAll() throws Exception {
    return departmentDao.findAll();
  }

  @Override
  public List<Department> getAllById(List<Long> ids) throws Exception {
    return departmentDao.findAllById(ids);
  }

  @Override
  public Department getById(Long id) throws Exception {
    return departmentDao.findById(id);
  }

  @Override
  public Department create(Department entity) throws Exception {
    return departmentDao.save(entity);
  }

  @Override
  public List<Department> createAll(List<Department> entities) throws Exception {
    return departmentDao.saveAll(entities);
  }

  @Override
  public Department edit(Department entity) throws Exception {
    return departmentDao.update(entity);
  }

  @Override
  public List<Department> editAll(List<Department> entities) throws Exception {
    return departmentDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    departmentDao.deleteById(id);

  }

  @Override
  public void delete(Department entity) throws Exception {
    departmentDao.delete(entity);

  }

  @Override
  public void deleteAll(List<Department> entities) throws Exception {
    departmentDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return departmentDao.existById(id);
  }

  @Override
  public List<Department> getDepartmentByName(String name) throws Exception {
    return departmentDao.findByName(name);
  }

  @Override
  public List<Department> getDepartmentByCreatorId(Long creatorId) throws Exception {
    return departmentDao.findByCreatorId(creatorId);
  }

  @Override
  public Department changeName(Department department, String name) throws Exception {
    if (name == null || name.equals("")) {
      throw new DepartmentException("Department name can't be " + name + " .");
    } else {
      department.setName(name);
      department.setModificationDate(new Date());
      return departmentDao.update(department);
    }
  }

  @Override
  public Department changeDescription(Department department, String description) throws Exception {
    department.setDescription(description);
    department.setModificationDate(new Date());
    return departmentDao.update(department);
  }

  @Override
  public Department createDepartment(User creator, Organization organization, String name,
      String description)
      throws Exception {
    Department newDepartment = new Department();
    newDepartment.setOrganization(organization);
    newDepartment.setCreator(creator);
    newDepartment.setName(name);
    newDepartment.setDescription(description);
    newDepartment.setCreationDate(new Date());
    return departmentDao.save(newDepartment);

  }


}
