package org.teilen.web.service;

import org.teilen.web.model.Group;
import org.teilen.web.model.User;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface GroupService {

    public abstract List<Group> getAll() throws Exception;

    public abstract List<Group> getAllById(List<Long> ids) throws Exception;

    public abstract Group getById(Long id) throws Exception;

    public abstract Group create(Group entity) throws Exception;

    public abstract List<Group> createAll(List<Group> entities) throws Exception;

    public abstract Group edit(Group entity) throws Exception;

    public abstract List<Group> editAll(List<Group> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(Group entity) throws Exception;

    public abstract void deleteAll(List<Group> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;

    public abstract List<Group> getByNameLike(String name) throws Exception;

    //======================
    public abstract Group addUser(Group group, User user) throws Exception;

    public abstract Group addUsers(Group group, List<User> users) throws Exception;

    public abstract Group removeUser(Group group, User user) throws Exception;

    public List<Group> getAllGroupsWithMembers() throws Exception;

}
