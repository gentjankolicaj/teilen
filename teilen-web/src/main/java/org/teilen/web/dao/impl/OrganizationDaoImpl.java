package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.OrganizationDao;
import org.teilen.web.model.Organization;

/**
 * @author gentjan kolicaj
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public OrganizationDaoImpl(SessionFactory sessionFactory) {
    super();
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Organization> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Organization";
    Query<Organization> query = session.createQuery(hql, Organization.class);

    return query.getResultList();
  }

  @Override
  public Organization findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Organization organization = session.get(Organization.class, id);
    return organization;
  }

  @Override
  public Organization save(Organization organization) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(organization);
    return organization;
  }

  @Override
  public Organization update(Organization organization) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(organization);
    return organization;
  }

  @Override
  public void delete(Organization organization) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(organization);

  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Organization O where O.id=:var";
    Query<Organization> query = session.createQuery(hql, Organization.class);
    query.executeUpdate();
  }

  @Override
  public List<Organization> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Organization> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Organization tmp = session.get(Organization.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public List<Organization> saveAll(List<Organization> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Organization tmp : entities) {
      session.save(tmp);
    }

    return entities;
  }

  @Override
  public List<Organization> updateAll(List<Organization> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Organization tmp : entities) {
      session.saveOrUpdate(tmp);
    }

    return entities;
  }

  @Override
  public void deleteAll(List<Organization> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Organization tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Organization tmp = session.get(Organization.class, id);
    return tmp != null;
  }

}
