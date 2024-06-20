package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.GroupDao;
import org.teilen.web.model.Group;

/**
 * @author gentjan kolicaj
 */
@Repository
public class GroupDaoImpl implements GroupDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public GroupDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Group> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Group";
    Query<Group> query = session.createQuery(hql, Group.class);
    return query.getResultList();
  }

  @Override
  public List<Group> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Group> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Group tmp = session.get(Group.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public Group findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Group.class, id);
  }

  @Override
  public Group save(Group entity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(entity);
    return entity;
  }

  @Override
  public List<Group> saveAll(List<Group> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Group tmp : entities) {
      session.save(tmp);
    }
    return entities;
  }

  @Override
  public Group update(Group entity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  @Override
  public List<Group> updateAll(List<Group> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Group tmp : entities) {
      session.saveOrUpdate(tmp);
    }
    return entities;
  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Group G where G.id=:var";
    Query<Group> query = session.createQuery(hql, Group.class);
    query.setParameter("var", id);
    query.executeUpdate();

  }

  @Override
  public void delete(Group entity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(entity);
  }

  @Override
  public void deleteAll(List<Group> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Group tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Group tmp = session.get(Group.class, id);
    return tmp != null;
  }

  @Override
  public List<Group> findByNameLike(String name) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Group G where G.name like :var";
    Query<Group> query = session.createQuery(hql, Group.class);
    query.setParameter("var", name + "%");
    return query.getResultList();
  }

}
