package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.IndustryDao;
import org.teilen.web.model.Industry;

/**
 * @author gentjan kolicaj
 */
@Repository
public class IndustryDaoImpl implements IndustryDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public IndustryDaoImpl(SessionFactory sessionFactory) {
    super();
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Industry> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Industry";
    Query<Industry> query = session.createQuery(hql, Industry.class);
    return query.getResultList();
  }

  @Override
  public Industry findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Industry industry = session.get(Industry.class, id);
    return industry;
  }

  @Override
  public List<Industry> findByNameLike(String name) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Industry I where I.name like :var";
    Query<Industry> query = session.createQuery(hql, Industry.class);
    query.setParameter("var", name + "%");

    return query.getResultList();
  }

  @Override
  public Industry save(Industry industry) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(industry);
    return industry;

  }

  @Override
  public Industry update(Industry industry) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(industry);
    return industry;

  }

  @Override
  public void delete(Industry industry) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(industry);

  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Industry I where I.id=:var";
    Query<Industry> query = session.createQuery(hql, Industry.class);
    query.setParameter("var", id);
    query.executeUpdate();
  }

  @Override
  public List<Industry> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Industry> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Industry tmp = session.get(Industry.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public List<Industry> saveAll(List<Industry> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Industry tmp : entities) {
      session.save(tmp);
    }

    return entities;
  }

  @Override
  public List<Industry> updateAll(List<Industry> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Industry tmp : entities) {
      session.saveOrUpdate(tmp);
    }

    return entities;

  }

  @Override
  public void deleteAll(List<Industry> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Industry industry : entities) {
      session.delete(industry);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Industry tmp = session.get(Industry.class, id);
    return tmp != null;
  }

}
