package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.MessageDao;
import org.teilen.web.model.Message;

/**
 * @author gentjan kolicaj
 */
@Repository
public class MessageDaoImpl implements MessageDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public MessageDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Message> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Message";
    Query<Message> query = session.createQuery(hql, Message.class);

    return query.getResultList();
  }

  @Override
  public Message findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Message chat = session.get(Message.class, id);
    return chat;
  }

  @Override
  public Message save(Message message) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(message);

    return message;

  }

  @Override
  public Message update(Message message) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(message);
    return message;

  }

  @Override
  public void delete(Message message) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(message);

  }

  @Override
  public void deleteAll(List<Message> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Message tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Message tmp = session.get(Message.class, id);
    return tmp != null;
  }

  @Override
  public List<Message> saveAll(List<Message> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Message tmp : entities) {
      session.save(tmp);
    }
    return entities;
  }

  @Override
  public List<Message> updateAll(List<Message> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Message tmp : entities) {
      session.saveOrUpdate(tmp);
    }
    return entities;
  }

  @Override
  public List<Message> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Message> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Message tmp = session.get(Message.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Message M where M.id=:var";
    Query<Message> query = session.createQuery(hql, Message.class);
    query.setParameter("var", id);
    query.executeUpdate();
  }

  //==================================================================================


  @Override
  public List<Message> customFindBySenderId(Long senderId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Message M where M.sender.id =:var and M.deletionDate is null";
    Query<Message> query = session.createQuery(hql, Message.class);
    query.setParameter("var", senderId);
    return query.getResultList();
  }

  @Override
  public List<Message> customFindByReceiverId(Long receiverId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Message M where M.receiver.id =:var and M.deletionDate is null";
    Query<Message> query = session.createQuery(hql, Message.class);
    query.setParameter("var", receiverId);
    return query.getResultList();
  }

  @Override
  public List<Message> customFindByTeamId(Long teamId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Message where team.id =:var and deletionDate is null";
    Query<Message> query = session.createQuery(hql, Message.class);
    query.setParameter("var", teamId);
    return query.getResultList();
  }


  //====================================================================
  @Override
  public int customDeleteBySenderId(Long senderId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "update Message M set M.deletionDate = :date where M.sender.id = :senderId";
    Query<Message> query = session.createQuery(hql);
    query.setParameter("date", new Date());
    query.setParameter("senderId", senderId);

    return query.executeUpdate();
  }

  @Override
  public int customDeleteByReceiverId(Long receiverId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "update Message M set M.deletionDate = :date where M.receiver.id = :receiverId";
    Query<Message> query = session.createQuery(hql);
    query.setParameter("date", new Date());
    query.setParameter("receiverId", receiverId);

    return query.executeUpdate();
  }

  @Override
  public int customDeleteByTeamId(Long teamId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "update Message M set M.deletionDate = :date where M.team.id = :teamId";
    Query query = session.createQuery(hql);
    query.setParameter("date", new Date());
    query.setParameter("teamId", teamId);

    return query.executeUpdate();
  }

  @Override
  public List<Message> customFindAllDeleted() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Message M where M.deletionDate is not null";
    Query<Message> query = session.createQuery(hql, Message.class);

    return query.getResultList();
  }

  @Override
  public List<Message> customFindAllUnDeleted() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Message M where M.deletionDate is null";
    Query<Message> query = session.createQuery(hql, Message.class);

    return query.getResultList();
  }
}
