package org.teilen.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.UserDao;
import org.teilen.web.enums.Sex;
import org.teilen.web.exception.UserException;
import org.teilen.web.model.User;
import org.teilen.web.service.UserService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


  private final UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    super();
    this.userDao = userDao;
  }

  @Override
  public List<User> getAll() throws Exception {
    return userDao.findAll();
  }

  @Override
  public List<User> getAllById(List<Long> ids) throws Exception {
    return userDao.findAllById(ids);
  }

  @Override
  public User getById(Long id) throws Exception {
    return userDao.findById(id);
  }

  @Override
  public User create(User entity) throws Exception {
    return userDao.save(entity);
  }

  @Override
  public List<User> createAll(List<User> entities) throws Exception {
    return userDao.saveAll(entities);
  }

  @Override
  public User edit(User entity) throws Exception {
    return userDao.update(entity);
  }

  @Override
  public List<User> editAll(List<User> entities) throws Exception {
    return userDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    userDao.deleteById(id);

  }

  @Override
  public void delete(User entity) throws Exception {
    userDao.delete(entity);

  }

  @Override
  public void deleteAll(List<User> entities) throws Exception {
    userDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return userDao.existById(id);
  }

  @Override
  public List<User> getByFirstNameLike(String firstName) throws Exception {
    return userDao.findByFirstNameLike(firstName);
  }

  @Override
  public List<User> getByLastNameLike(String lastName) throws Exception {
    return userDao.findByLastNameLike(lastName);
  }

  @Override
  public List<User> getByUsernameLike(String username) throws Exception {
    return userDao.findByUsernameLike(username);
  }

  @Override
  public List<User> getByUsername(String username) throws Exception {
    return userDao.findByUsername(username);
  }

  @Override
  public User createUser(String username, String firstName, String lastName, Sex sex)
      throws Exception {
    User user = new User();
    user.setUsername(username);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setSex(sex);

    return userDao.save(user);
  }

  @Override
  public User editUser(Long userId, String username, String firstName, String lastName, Sex sex)
      throws Exception {
    User user = userDao.findById(userId);
    if (user == null) {
      throw new UserException("User dosen't exists.Reference is null.");
    }

    user.setUsername(username);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setSex(sex);

    return userDao.update(user);
  }

  @Override
  public User editUser(User user, String username, String firstName, String lastName, Sex sex)
      throws Exception {
    if (user == null) {
      throw new UserException("User dosen't exists.Reference is null.");
    }

    user.setUsername(username);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setSex(sex);

    return userDao.update(user);
  }

  @Override
  public void deleteUser(User user) throws Exception {
    userDao.delete(user);
  }

  @Override
  public void deleteUser(Long userId) throws Exception {
    userDao.deleteById(userId);
  }

  @Override
  public List<User> getAllDeleted() throws Exception {
    return userDao.findAllDeleted();
  }

  @Override
  public List<User> getAllUnDeleted() throws Exception {
    return userDao.findAllUnDeleted();
  }


}
