package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.CurrencyDao;
import org.teilen.web.model.Currency;

/**
 * @author gentjan kolicaj
 */
@Repository
public class CurrencyDaoImpl implements CurrencyDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public CurrencyDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Currency> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Currency";
    Query<Currency> query = session.createQuery(hql, Currency.class);

    return query.getResultList();
  }

  @Override
  public Currency findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Currency.class, id);
  }

  @Override
  public Currency save(Currency currency) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(currency);
    return currency;
  }

  @Override
  public Currency update(Currency currency) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(currency);
    return currency;
  }

  @Override
  public void delete(Currency currency) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(currency);

  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Currency C where C.id=:var";
    Query<Currency> query = session.createQuery(hql, Currency.class);
    query.setParameter("var", id);
    query.executeUpdate();
  }

  @Override
  public List<Currency> findByNameLike(String name) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Currency C where C.currencyName like :var";
    Query<Currency> query = session.createQuery(hql, Currency.class);
    query.setParameter("var", name + "%");
    return query.getResultList();
  }

  @Override
  public List<Currency> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Currency> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Currency tmp = session.get(Currency.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public List<Currency> saveAll(List<Currency> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Currency tmp : entities) {
      session.save(tmp);
    }
    return entities;
  }

  @Override
  public List<Currency> updateAll(List<Currency> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Currency tmp : entities) {
      session.saveOrUpdate(tmp);
    }
    return entities;
  }

  @Override
  public void deleteAll(List<Currency> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Currency tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Currency tmp = session.get(Currency.class, id);
    return tmp != null;
  }

}
