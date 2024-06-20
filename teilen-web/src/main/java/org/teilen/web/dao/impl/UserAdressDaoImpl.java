package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.UserAdressDao;
import org.teilen.web.model.UserAddress;

/**
 * @author gentjan kolicaj
 */
@Repository
public class UserAdressDaoImpl implements UserAdressDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public UserAdressDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<UserAddress> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from UserAddress";
    Query<UserAddress> query = session.createQuery(hql, UserAddress.class);
    return query.getResultList();
  }

  @Override
  public UserAddress findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    UserAddress userAddress = session.get(UserAddress.class, id);
    return userAddress;
  }

  @Override
  public List<UserAddress> findByCityLike(String cityName) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from UserAddress U where U.city like :var";
    Query<UserAddress> query = session.createQuery(hql, UserAddress.class);
    query.setParameter("var", cityName + "%");
    return query.getResultList();
  }

  @Override
  public List<UserAddress> findByStreetLike(String street) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from UserAddress U where U.street like :var";
    Query<UserAddress> query = session.createQuery(hql, UserAddress.class);
    query.setParameter("var", street + "%");
    return query.getResultList();

  }

  @Override
  public List<UserAddress> findByCountryId(Long countryId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from UserAddress where country_id=:var";
    Query<UserAddress> query = session.createQuery(hql, UserAddress.class);
    query.setParameter("var", countryId);
    return query.getResultList();
  }

  @Override
  public UserAddress save(UserAddress userAddress) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(userAddress);
    return userAddress;

  }

  @Override
  public UserAddress update(UserAddress userAddress) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(userAddress);
    return userAddress;
  }

  @Override
  public void delete(UserAddress userAddress) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(userAddress);

  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from UserAddress U where U.id=:var";
    Query<UserAddress> query = session.createQuery(hql, UserAddress.class);
    query.setParameter("var", id);
    query.executeUpdate();
  }

  @Override
  public List<UserAddress> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<UserAddress> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      UserAddress tmp = session.get(UserAddress.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public List<UserAddress> saveAll(List<UserAddress> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (UserAddress tmp : entities) {
      session.save(tmp);
    }

    return entities;
  }

  @Override
  public List<UserAddress> updateAll(List<UserAddress> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (UserAddress tmp : entities) {
      session.saveOrUpdate(tmp);
    }

    return entities;
  }

  @Override
  public void deleteAll(List<UserAddress> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (UserAddress tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    UserAddress tmp = session.get(UserAddress.class, id);
    return tmp != null;
  }

}
