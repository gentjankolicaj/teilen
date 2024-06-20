package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.User;


/**
 * @author gentjan kolicaj
 */
public interface UserDao extends CrudDao<User, Long> {

  List<User> findByFirstNameLike(String firstName) throws Exception;

  List<User> findByLastNameLike(String lastName) throws Exception;

  List<User> findByUsernameLike(String username) throws Exception;

  List<User> findByUsername(String username) throws Exception;

  List<User> findAllDeleted() throws Exception;

  List<User> findAllUnDeleted() throws Exception;


}
