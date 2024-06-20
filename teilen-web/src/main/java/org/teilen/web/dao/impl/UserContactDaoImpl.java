package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.UserContactDao;
import org.teilen.web.model.UserContact;

/**
 * @author gentjan kolicaj
 */
@Repository
public class UserContactDaoImpl implements UserContactDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public UserContactDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<UserContact> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from UserContact order by id";
    Query<UserContact> query = session.createQuery(hql, UserContact.class);

    return query.getResultList();

  }

  @Override
  public UserContact findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    UserContact userContact = session.get(UserContact.class, id);
    return userContact;
  }

  @Override
  public UserContact save(UserContact userContact) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(userContact);
    return userContact;
  }

  @Override
  public UserContact update(UserContact userContact) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(userContact);
    return userContact;
  }

  @Override
  public void delete(UserContact userContact) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(userContact);

  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from UserContact U where U.id=:var";
    Query<UserContact> query = session.createQuery(hql, UserContact.class);
    query.setParameter("var", id);
    query.executeUpdate();
  }

  @Override
  public UserContact findByEmail(String email) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from UserContact U where U.email like :var";
    Query<UserContact> query = session.createQuery(hql, UserContact.class);
    query.setParameter("var", email);
    return query.getSingleResult();
  }

  @Override
  public List<UserContact> findByTelephone(Long telephone) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from UserContact U where U.telephone like :var";
    Query<UserContact> query = session.createQuery(hql, UserContact.class);
    query.setParameter("var", telephone);
    return query.getResultList();
  }

  @Override
  public List<UserContact> findByMobile(Long mobile) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from UserContact U where U.mobile like :var";
    Query<UserContact> query = session.createQuery(hql, UserContact.class);
    query.setParameter("var", mobile);
    return query.getResultList();
  }

  @Override
  public List<UserContact> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<UserContact> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      UserContact tmp = session.get(UserContact.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public List<UserContact> saveAll(List<UserContact> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (UserContact tmp : entities) {
      session.save(tmp);
    }

    return entities;
  }

  @Override
  public List<UserContact> updateAll(List<UserContact> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (UserContact tmp : entities) {
      session.saveOrUpdate(tmp);
    }

    return entities;
  }

  @Override
  public void deleteAll(List<UserContact> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (UserContact tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    UserContact tmp = session.get(UserContact.class, id);
    return tmp != null;
  }

  @Override
  public List<UserContact> findByEmailLike(String email) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from UserContact U where U.email like :var";
    Query<UserContact> query = session.createQuery(hql, UserContact.class);
    query.setParameter("var", email + "%");
    return query.getResultList();
  }

}
