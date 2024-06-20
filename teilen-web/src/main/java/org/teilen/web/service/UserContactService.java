package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.User;
import org.teilen.web.model.UserContact;

/**
 * @author gentjan kolicaj
 */
public interface UserContactService {

  List<UserContact> getAll() throws Exception;

  List<UserContact> getAllById(List<Long> ids) throws Exception;

  UserContact getById(Long id) throws Exception;

  UserContact create(UserContact entity) throws Exception;

  List<UserContact> createAll(List<UserContact> entities) throws Exception;

  UserContact edit(UserContact entity) throws Exception;

  List<UserContact> editAll(List<UserContact> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(UserContact entity) throws Exception;

  void deleteAll(List<UserContact> entities) throws Exception;

  boolean existById(Long id) throws Exception;

  //======================================================================
  UserContact getByEmail(String email) throws Exception;

  List<UserContact> getByEmailLike(String email) throws Exception;

  List<UserContact> getByTelephone(Long telephone) throws Exception;

  List<UserContact> getByMobile(Long mobile) throws Exception;

  UserContact createContact(User user, String email, Long telephone, Long mobile,
      String postalCode) throws Exception;

  UserContact editContact(Long contactId, String email, Long telephone, Long mobile,
      String postalCode) throws Exception;

  UserContact editContact(UserContact userContact, String email, Long telephone,
      Long mobile, String postalCode) throws Exception;

  void deleteDeleteContact(User user) throws Exception;

}
