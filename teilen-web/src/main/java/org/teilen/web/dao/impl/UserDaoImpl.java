package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.UserDao;
import org.teilen.web.model.User;

/**
 * @author gentjan kolicaj
 */
@Repository
public class UserDaoImpl implements UserDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public UserDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<User> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from User order by id";
    Query<User> query = session.createQuery(hql, User.class);
    return query.getResultList();
  }

  @Override
  public User findById(Long userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    User user = session.get(User.class, userId);

    return user;
  }

  @Override
  public User save(User user) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(user);
    return user;

  }

  @Override
  public User update(User user) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(user);
    return user;
  }

  @Override
  public void delete(User user) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(user);

  }

  @Override
  public void deleteById(Long userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from User U where U.id=:var";
    Query<User> query = session.createQuery(hql, User.class);
    query.setParameter("var", userId);
    query.executeUpdate();
  }

  @Override
  public List<User> findByFirstNameLike(String firstName) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from User U where U.firstName like :var";
    Query<User> query = session.createQuery(hql, User.class);
    query.setParameter("var", firstName + "%");
    return query.getResultList();
  }

  @Override
  public List<User> findByLastNameLike(String lastName) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from User U where U.lastName like :var";
    Query<User> query = session.createQuery(hql, User.class);
    query.setParameter("var", lastName + "%");

    return query.getResultList();
  }

  @Override
  public List<User> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<User> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      User tmp = session.get(User.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public List<User> saveAll(List<User> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (User tmp : entities) {
      session.save(tmp);
    }
    return entities;
  }

  @Override
  public List<User> updateAll(List<User> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (User tmp : entities) {
      session.saveOrUpdate(tmp);
    }
    return entities;
  }

  @Override
  public void deleteAll(List<User> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (User tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    User tmp = session.get(User.class, id);
    return tmp != null;
  }

  @Override
  public List<User> findByUsername(String username) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from User U where U.username like :var";
    Query<User> query = session.createQuery(hql, User.class);
    query.setParameter("var", username);
    return query.getResultList();
  }

  @Override
  public List<User> findByUsernameLike(String username) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from User U where U.username like :var";
    Query<User> query = session.createQuery(hql, User.class);
    query.setParameter("var", username + "%");
    return query.getResultList();
  }

  @Override
  public List<User> findAllDeleted() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from User U where U.deletionDate is not null";
    Query<User> query = session.createQuery(hql, User.class);

    return query.getResultList();
  }

  @Override
  public List<User> findAllUnDeleted() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from User U where U.deletionDate is null";
    Query<User> query = session.createQuery(hql, User.class);

    return query.getResultList();
  }

}
