package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.AuthenticationAttemptDao;
import org.teilen.web.model.AuthenticationAttempt;

/**
 * @author gentjan kolicaj
 */
@Repository
public class AuthenticationAttemptDaoImpl implements AuthenticationAttemptDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public AuthenticationAttemptDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<AuthenticationAttempt> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from AuthenticationAttempt order by id";
    Query<AuthenticationAttempt> query = session.createQuery(hql, AuthenticationAttempt.class);

    return query.getResultList();
  }

  @Override
  public AuthenticationAttempt findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    return session.get(AuthenticationAttempt.class, id);
  }

  @Override
  public AuthenticationAttempt save(AuthenticationAttempt authenticationAttempt) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(authenticationAttempt);

    return authenticationAttempt;

  }

  @Override
  public AuthenticationAttempt update(AuthenticationAttempt authenticationAttempt)
      throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(authenticationAttempt);

    return authenticationAttempt;

  }

  @Override
  public void delete(AuthenticationAttempt attempt) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(attempt);

  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from AuthenticationAttempt A where A.id=:var"; // it is the name of the class that does the
    // mapping not name of table.
    Query<AuthenticationAttempt> query = session.createQuery(hql, AuthenticationAttempt.class);
    query.setParameter("var", id);

    query.executeUpdate();
  }

  @Override
  public List<AuthenticationAttempt> findByUserId(Long userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from AuthenticationAttempt where user_id=:var";
    Query<AuthenticationAttempt> query = session.createQuery(hql, AuthenticationAttempt.class);
    query.setParameter("var", userId);

    return query.getResultList();
  }

  @Override
  public List<AuthenticationAttempt> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<AuthenticationAttempt> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      AuthenticationAttempt tmp = session.get(AuthenticationAttempt.class, id);
      list.add(tmp);
    }
    return list;

  }

  @Override
  public List<AuthenticationAttempt> saveAll(List<AuthenticationAttempt> entities)
      throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (AuthenticationAttempt tmp : entities) {
      session.save(tmp);
    }
    return entities;
  }

  @Override
  public List<AuthenticationAttempt> updateAll(List<AuthenticationAttempt> entities)
      throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (AuthenticationAttempt tmp : entities) {
      session.saveOrUpdate(tmp);
    }
    return entities;
  }

  @Override
  public void deleteAll(List<AuthenticationAttempt> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (AuthenticationAttempt tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    AuthenticationAttempt tmp = session.get(AuthenticationAttempt.class, id);
    return tmp != null;

  }

}
