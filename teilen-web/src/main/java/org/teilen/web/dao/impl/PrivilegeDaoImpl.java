package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.PrivilegeDao;
import org.teilen.web.model.Privilege;

/**
 * @author gentjan kolicaj
 */
@Repository
public class PrivilegeDaoImpl implements PrivilegeDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public PrivilegeDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Privilege> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Privilege";
    Query<Privilege> query = session.createQuery(hql, Privilege.class);
    return query.getResultList();

  }

  @Override
  public List<Privilege> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Privilege> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Privilege tmp = session.get(Privilege.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public Privilege findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Privilege.class, id);
  }

  @Override
  public Privilege save(Privilege entity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(entity);
    return entity;
  }

  @Override
  public List<Privilege> saveAll(List<Privilege> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Privilege tmp : entities) {
      session.save(tmp);
    }
    return entities;
  }

  @Override
  public Privilege update(Privilege entity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);

    return entity;
  }

  @Override
  public List<Privilege> updateAll(List<Privilege> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Privilege tmp : entities) {
      session.saveOrUpdate(tmp);
    }
    return entities;
  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Privilege P where P.id=:var";
    Query<Privilege> query = session.createQuery(hql, Privilege.class);
    query.setParameter("var", id);
    query.executeUpdate();

  }

  @Override
  public void delete(Privilege entity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(entity);

  }

  @Override
  public void deleteAll(List<Privilege> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Privilege tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Privilege tmp = session.get(Privilege.class, id);
    return tmp != null;
  }

  @Override
  public List<Privilege> findByIdentifierLike(String identifier) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Privilege P where P.identifier like :var";
    Query<Privilege> query = session.createQuery(hql, Privilege.class);
    query.setParameter("var", identifier + "%");
    return query.getResultList();
  }

}
