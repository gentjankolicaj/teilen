package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.CountryDao;
import org.teilen.web.model.Country;


/**
 * @author gentjan kolicaj
 */
@Repository
public class CountryDaoImpl implements CountryDao {


  private final SessionFactory sessionFactory;


  @Autowired
  public CountryDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Country> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Country order by id";
    Query<Country> query = session.createQuery(hql, Country.class);

    return query.getResultList();
  }

  @Override
  public Country findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Country.class, id);
  }

  @Override
  public Country save(Country country) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(country);
    return country;
  }

  @Override
  public Country update(Country country) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(country);
    return country;
  }

  @Override
  public void delete(Country country) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(country);

  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Country C where C.id=:var";
    Query<Country> query = session.createQuery(hql, Country.class);
    query.setParameter("var", id);
    query.executeUpdate();
  }

  @Override
  public List<Country> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Country> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Country tmp = session.get(Country.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public List<Country> saveAll(List<Country> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Country tmp : entities) {
      session.save(tmp);
    }
    return entities;
  }

  @Override
  public List<Country> updateAll(List<Country> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Country country : entities) {
      session.saveOrUpdate(country);
    }
    return entities;
  }

  @Override
  public void deleteAll(List<Country> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Country tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Country tmp = session.get(Country.class, id);
    return tmp != null;
  }

}
