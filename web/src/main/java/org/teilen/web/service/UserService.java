package org.teilen.web.service;

import org.teilen.web.enums.Sex;
import org.teilen.web.model.User;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface UserService {

    public abstract List<User> getAll() throws Exception;

    public abstract List<User> getAllById(List<Long> ids) throws Exception;

    public abstract User getById(Long id) throws Exception;

    public abstract User create(User entity) throws Exception;

    public abstract List<User> createAll(List<User> entities) throws Exception;

    public abstract User edit(User entity) throws Exception;

    public abstract List<User> editAll(List<User> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(User entity) throws Exception;

    public abstract void deleteAll(List<User> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;

    //==================================================================
    public abstract List<User> getByFirstNameLike(String firstName) throws Exception;

    public abstract List<User> getByLastNameLike(String lastName) throws Exception;

    public abstract List<User> getByUsernameLike(String username) throws Exception;

    public abstract List<User> getByUsername(String username) throws Exception;

    public abstract List<User> getAllDeleted() throws Exception;

    public abstract List<User> getAllUnDeleted() throws Exception;


    public abstract User createUser(String username, String firstName, String lastName, Sex sex) throws Exception;

    public abstract User editUser(Long userId, String username, String firstName, String lastName, Sex sex) throws Exception;

    public abstract User editUser(User user, String username, String firstName, String lastName, Sex sex) throws Exception;

    public abstract void deleteUser(User user) throws Exception;

    public abstract void deleteUser(Long userId) throws Exception;


}
