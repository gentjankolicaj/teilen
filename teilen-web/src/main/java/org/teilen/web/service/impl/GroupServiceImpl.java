package org.teilen.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.GroupDao;
import org.teilen.web.exception.GroupException;
import org.teilen.web.model.Group;
import org.teilen.web.model.User;
import org.teilen.web.service.GroupService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

  private final GroupDao groupDao;

  @Autowired
  public GroupServiceImpl(GroupDao groupDao) {
    super();
    this.groupDao = groupDao;
  }


  @Override
  public List<Group> getAll() throws Exception {
    return groupDao.findAll();
  }

  @Override
  public List<Group> getAllById(List<Long> ids) throws Exception {
    return groupDao.findAllById(ids);
  }

  @Override
  public Group getById(Long id) throws Exception {
    return groupDao.findById(id);
  }

  @Override
  public Group create(Group entity) throws Exception {
    return groupDao.save(entity);
  }

  @Override
  public List<Group> createAll(List<Group> entities) throws Exception {
    return groupDao.saveAll(entities);
  }

  @Override
  public Group edit(Group entity) throws Exception {
    return groupDao.update(entity);
  }

  @Override
  public List<Group> editAll(List<Group> entities) throws Exception {
    return groupDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    groupDao.deleteById(id);

  }

  @Override
  public void delete(Group entity) throws Exception {
    groupDao.delete(entity);

  }

  @Override
  public void deleteAll(List<Group> entities) throws Exception {
    groupDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return groupDao.existById(id);
  }

  @Override
  public List<Group> getByNameLike(String name) throws Exception {
    return groupDao.findByNameLike(name);
  }

  @Override
  public Group addUser(Group group, User user) throws Exception {
    List<User> userList = group.getMembers();
    if (userList == null) {
      userList = new ArrayList<>();
      userList.add(user);
      group.setMembers(userList);
    } else {
      userList.add(user);
    }
    return groupDao.update(group);
  }

  @Override
  public Group addUsers(Group group, List<User> users) throws Exception {
    List<User> userList = group.getMembers();
    if (userList == null) {
      userList = users;
      group.setMembers(userList);
    } else {
      userList.addAll(users);
    }
    return groupDao.update(group);
  }

  @Override
  public Group removeUser(Group group, User user) throws Exception {
    List<User> userList = group.getMembers();
    if (userList == null) {
      throw new GroupException("User " + user + " dosen't belong to group " + group);
    } else {
      userList.remove(user);
    }
    return groupDao.update(group);
  }


  @Override
  public List<Group> getAllGroupsWithMembers() throws Exception {
    List<Group> list = groupDao.findAll();
    for (Group group : list) {
      group.getMembers();
    }
    return list;
  }


}
