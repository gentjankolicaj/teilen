package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Group;
import org.teilen.web.model.User;

/**
 * @author gentjan kolicaj
 */
public interface GroupService {

  List<Group> getAll() throws Exception;

  List<Group> getAllById(List<Long> ids) throws Exception;

  Group getById(Long id) throws Exception;

  Group create(Group entity) throws Exception;

  List<Group> createAll(List<Group> entities) throws Exception;

  Group edit(Group entity) throws Exception;

  List<Group> editAll(List<Group> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Group entity) throws Exception;

  void deleteAll(List<Group> entities) throws Exception;

  boolean existById(Long id) throws Exception;

  List<Group> getByNameLike(String name) throws Exception;

  //======================
  Group addUser(Group group, User user) throws Exception;

  Group addUsers(Group group, List<User> users) throws Exception;

  Group removeUser(Group group, User user) throws Exception;

  List<Group> getAllGroupsWithMembers() throws Exception;

}
