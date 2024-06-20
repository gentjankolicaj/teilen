package org.teilen.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teilen.web.dao.LanguageDao;
import org.teilen.web.model.Language;

/**
 * @author gentjan kolicaj
 */
@Repository
public class LanguageDaoImpl implements LanguageDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public LanguageDaoImpl(SessionFactory sessionFactory) {
    super();
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Language> findAll() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Language";
    Query<Language> query = session.createQuery(hql, Language.class);
    return query.getResultList();
  }

  @Override
  public Language findById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Language language = session.get(Language.class, id);

    return language;
  }

  @Override
  public List<Language> findByLangLike(String lang) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Language L where L.language like :var";
    Query<Language> query = session.createQuery(hql, Language.class);
    query.setParameter("var", lang + "%");
    return query.getResultList();
  }

  @Override
  public Language save(Language language) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(language);
    return language;

  }

  @Override
  public Language update(Language language) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(language);
    return language;
  }

  @Override
  public void delete(Language language) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.delete(language);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String hql = "delete from Language L where L.id=:var";
    Query<Language> query = session.createQuery(hql, Language.class);
    query.setParameter("var", id);
    query.executeUpdate();
  }

  @Override
  public List<Language> findAllById(List<Long> ids) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    List<Language> list = new ArrayList<>(ids.size());
    for (Long id : ids) {
      Language tmp = session.get(Language.class, id);
      list.add(tmp);
    }
    return list;
  }

  @Override
  public List<Language> saveAll(List<Language> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Language tmp : entities) {
      session.save(tmp);
    }

    return entities;
  }

  @Override
  public List<Language> updateAll(List<Language> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Language tmp : entities) {
      session.saveOrUpdate(tmp);
    }

    return entities;
  }

  @Override
  public void deleteAll(List<Language> entities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for (Language tmp : entities) {
      session.delete(tmp);
    }

  }

  @Override
  public boolean existById(Long id) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Language tmp = session.get(Language.class, id);
    return tmp != null;
  }

}
