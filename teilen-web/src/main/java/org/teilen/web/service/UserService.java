package org.teilen.web.service;

import java.util.List;
import org.teilen.web.enums.Sex;
import org.teilen.web.model.User;


/**
 * @author gentjan kolicaj
 */
public interface UserService {

  List<User> getAll() throws Exception;

  List<User> getAllById(List<Long> ids) throws Exception;

  User getById(Long id) throws Exception;

  User create(User entity) throws Exception;

  List<User> createAll(List<User> entities) throws Exception;

  User edit(User entity) throws Exception;

  List<User> editAll(List<User> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(User entity) throws Exception;

  void deleteAll(List<User> entities) throws Exception;

  boolean existById(Long id) throws Exception;

  //==================================================================
  List<User> getByFirstNameLike(String firstName) throws Exception;

  List<User> getByLastNameLike(String lastName) throws Exception;

  List<User> getByUsernameLike(String username) throws Exception;

  List<User> getByUsername(String username) throws Exception;

  List<User> getAllDeleted() throws Exception;

  List<User> getAllUnDeleted() throws Exception;


  User createUser(String username, String firstName, String lastName, Sex sex)
      throws Exception;

  User editUser(Long userId, String username, String firstName, String lastName,
      Sex sex) throws Exception;

  User editUser(User user, String username, String firstName, String lastName,
      Sex sex) throws Exception;

  void deleteUser(User user) throws Exception;

  void deleteUser(Long userId) throws Exception;


}
