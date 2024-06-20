package org.teilen.web.service;


import org.teilen.web.model.Credential;
import org.teilen.web.model.Organization;
import org.teilen.web.model.PasswordModel;
import org.teilen.web.model.User;
import org.teilen.web.model.UserAddress;
import org.teilen.web.model.UserContact;

/**
 * @author gentjan kolicaj
 */
public interface SignService {

  User signUp(User user, UserContact userContact, UserAddress userAddress,
      PasswordModel passwordModel, Long countryId, Organization organization) throws Exception;

  User signUp(User user, UserContact userContact, UserAddress userAddress,
      PasswordModel passwordModel, Long countryId) throws Exception;

  User signUp(User user, UserContact userContact, UserAddress userAddress,
      PasswordModel passwordModel, Long countryId, Organization organization, Credential credential)
      throws Exception;

  User signIn(String email, String password) throws Exception;


}
