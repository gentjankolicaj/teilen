package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.TeamDao;
import org.teilen.web.model.Team;

/**
 * @author gentjan kolicaj
 */
@Repository
public class TeamDaoImpl implements TeamDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public TeamDaoImpl(SessionFactory sessionFactory) {
    super();
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Team> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Team";
    Query<Team> query = session.createQuery(hql, Team.class);
    return query.getResultList();

  }

  @Override
  public Team findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Team team = session.get(Team.class, id);
    return team;
  }

  @Override
  public List<Team> findByNameLike(String name) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Team T where T.name like :var";
    Query<Team> query = session.createQuery(hql, Team.class);
    query.setParameter("var", name + "%");
    return query.getResultList();

  }

  @Override
  public List<Team> findByDepartmentId(Long departmentId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Team where department_id=:var";
    Query<Team> query = session.createQuery(hql, Team.class);
    query.setParameter("var", departmentId);
    return query.getResultList();
  }

  @Override
  public Team save(Team team) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(team);
    return team;
  }

  @Override
  public Team update(Team team) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(team);
    return team;
  }

  @Override
  public void delete(Team team) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(team);

  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Team T where T.id=:var";
    Query<Team> query = session.createQuery(hql, Team.class);
    query.executeUpdate();
  }

  @Override
  public List<Team> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Team> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Team tmp = session.get(Team.class, id);
      list.add(tmp);
    }
    return list;

  }

  @Override
  public List<Team> saveAll(List<Team> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Team tmp : entities) {
      session.save(tmp);
    }
    return entities;
  }

  @Override
  public List<Team> updateAll(List<Team> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Team tmp : entities) {
      session.saveOrUpdate(tmp);
    }

    return entities;
  }

  @Override
  public void deleteAll(List<Team> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Team tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Team tmp = session.get(Team.class, id);
    return tmp != null;
  }

}
