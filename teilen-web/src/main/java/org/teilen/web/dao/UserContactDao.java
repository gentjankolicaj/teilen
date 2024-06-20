package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.UserContact;


/**
 * @author gentjan kolicaj
 */
public interface UserContactDao extends CrudDao<UserContact, Long> {

  UserContact findByEmail(String email) throws Exception;

  List<UserContact> findByEmailLike(String email) throws Exception;

  List<UserContact> findByTelephone(Long telephone) throws Exception;

  List<UserContact> findByMobile(Long mobile) throws Exception;

}
