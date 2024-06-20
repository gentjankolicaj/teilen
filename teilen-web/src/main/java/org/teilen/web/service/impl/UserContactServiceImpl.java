package org.teilen.web.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.UserContactDao;
import org.teilen.web.exception.UserContactException;
import org.teilen.web.exception.UserException;
import org.teilen.web.model.User;
import org.teilen.web.model.UserContact;
import org.teilen.web.service.UserContactService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class UserContactServiceImpl implements UserContactService {

  private final UserContactDao userContactDao;

  @Autowired
  public UserContactServiceImpl(UserContactDao userContactDao) {
    this.userContactDao = userContactDao;
  }

  @Override
  public List<UserContact> getAll() throws Exception {
    return userContactDao.findAll();
  }

  @Override
  public List<UserContact> getAllById(List<Long> ids) throws Exception {
    return userContactDao.findAllById(ids);
  }

  @Override
  public UserContact getById(Long id) throws Exception {
    return userContactDao.findById(id);
  }

  @Override
  public UserContact create(UserContact entity) throws Exception {
    return userContactDao.save(entity);
  }

  @Override
  public List<UserContact> createAll(List<UserContact> entities) throws Exception {
    return userContactDao.saveAll(entities);
  }

  @Override
  public UserContact edit(UserContact entity) throws Exception {
    return userContactDao.update(entity);
  }

  @Override
  public List<UserContact> editAll(List<UserContact> entities) throws Exception {
    return userContactDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    userContactDao.deleteById(id);

  }

  @Override
  public void delete(UserContact entity) throws Exception {
    userContactDao.delete(entity);

  }

  @Override
  public void deleteAll(List<UserContact> entities) throws Exception {
    userContactDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return userContactDao.existById(id);
  }

  @Override
  public UserContact getByEmail(String email) throws Exception {
    return userContactDao.findByEmail(email);
  }

  @Override
  public List<UserContact> getByEmailLike(String email) throws Exception {
    return userContactDao.findByEmailLike(email);
  }

  @Override
  public List<UserContact> getByTelephone(Long telephone) throws Exception {
    return userContactDao.findByTelephone(telephone);
  }

  @Override
  public List<UserContact> getByMobile(Long mobile) throws Exception {
    return userContactDao.findByMobile(mobile);
  }

  @Override
  public UserContact createContact(User user, String email, Long telephone, Long mobile,
      String postalCode)
      throws Exception {
    UserContact userContact = new UserContact();
    userContact.setUser(user);
    userContact.setEmail(email);
    userContact.setTelephone(telephone);
    userContact.setMobile(mobile);
    userContact.setPostalCode(postalCode);
    userContact.setCreationDate(new Date());
    userContact.setModificationDate(null);

    return userContactDao.save(userContact);
  }

  @Override
  public UserContact editContact(Long contactId, String email, Long telephone, Long mobile,
      String postalCode)
      throws Exception {
    UserContact userContact = userContactDao.findById(contactId);
    if (userContact == null) {
      throw new UserContactException("User contact dosen't exists.Reference is null.");
    }

    userContact.setEmail(email);
    userContact.setTelephone(telephone);
    userContact.setMobile(mobile);
    userContact.setPostalCode(postalCode);
    userContact.setModificationDate(new Date());

    return userContactDao.update(userContact);
  }

  @Override
  public UserContact editContact(UserContact userContact, String email, Long telephone, Long mobile,
      String postalCode) throws Exception {
    if (userContact == null) {
      throw new UserContactException("User contact dosen't exists.Reference is null.");
    }

    userContact.setEmail(email);
    userContact.setTelephone(telephone);
    userContact.setMobile(mobile);
    userContact.setPostalCode(postalCode);
    userContact.setModificationDate(new Date());

    return userContactDao.update(userContact);
  }

  @Override
  public void deleteDeleteContact(User user) throws Exception {
    if (user == null) {
      throw new UserException("User doesn't not exists.Reference is null.");
    }

    UserContact userContact = user.getUserContact();
    userContactDao.delete(userContact);

  }


}
