package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.DepartmentDao;
import org.teilen.web.model.Department;

/**
 * @author gentjan kolicaj
 */
@Repository
public class DepartmentDaoImpl implements DepartmentDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public DepartmentDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Department> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Department";
    Query<Department> query = session.createQuery(hql, Department.class);
    return query.getResultList();

  }

  @Override
  public Department findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Department.class, id);

  }

  @Override
  public List<Department> findByName(String name) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Department D where D.name like :var";
    Query<Department> query = session.createQuery(hql, Department.class);
    query.setParameter("var", name);

    return query.getResultList();
  }

  @Override
  public List<Department> findByCreatorId(Long creatorId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Department where creator_id=:var";
    Query<Department> query = session.createQuery(hql, Department.class);
    query.setParameter("var", creatorId);

    return query.getResultList();
  }

  @Override
  public Department save(Department department) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(department);
    return department;

  }

  @Override
  public Department update(Department department) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(department);
    return department;

  }

  @Override
  public void delete(Department department) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(department);

  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Department D where D.id=:var";
    Query<Department> query = session.createQuery(hql, Department.class);
    query.setParameter("var", id);
    query.executeUpdate();
  }

  @Override
  public List<Department> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Department> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Department tmp = session.get(Department.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public List<Department> saveAll(List<Department> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Department tmp : entities) {
      session.save(tmp);
    }
    return entities;
  }

  @Override
  public List<Department> updateAll(List<Department> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Department tmp : entities) {
      session.saveOrUpdate(tmp);
    }
    return entities;
  }

  @Override
  public void deleteAll(List<Department> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Department tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Department tmp = session.get(Department.class, id);
    return tmp != null;
  }

}
