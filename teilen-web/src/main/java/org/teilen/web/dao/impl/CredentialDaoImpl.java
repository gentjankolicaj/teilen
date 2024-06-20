package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.CredentialDao;
import org.teilen.web.model.Credential;

/**
 * @author gentjan kolicaj
 */
@Repository
public class CredentialDaoImpl implements CredentialDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public CredentialDaoImpl(SessionFactory sessionFactory) {
    super();
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Credential> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Credential";
    Query<Credential> query = session.createQuery(hql, Credential.class);
    return query.getResultList();
  }

  @Override
  public Credential findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Credential.class, id);
  }

  @Override
  public Credential save(Credential credential) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(credential);
    return credential;
  }

  @Override
  public Credential update(Credential credential) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(credential);
    return credential;

  }

  @Override
  public void delete(Credential credential) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(credential);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Credential C where C.id=:var";
    Query<Credential> query = session.createQuery(hql, Credential.class);
    query.setParameter("var", id);
    query.executeUpdate();
  }

  @Override
  public Credential findByUserId(Long userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Credential where user.id =:var";
    Query<Credential> query = session.createQuery(hql, Credential.class);
    query.setParameter("var", userId);

    return query.getSingleResult();
  }

  @Override
  public List<Credential> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Credential> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Credential tmp = session.get(Credential.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public List<Credential> saveAll(List<Credential> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Credential tmp : entities) {
      session.save(tmp);
    }
    return entities;
  }

  @Override
  public List<Credential> updateAll(List<Credential> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Credential tmp : entities) {
      session.saveOrUpdate(tmp);
    }
    return entities;
  }

  @Override
  public void deleteAll(List<Credential> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Credential tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Credential tmp = session.get(Credential.class, id);
    return tmp != null;
  }

}
