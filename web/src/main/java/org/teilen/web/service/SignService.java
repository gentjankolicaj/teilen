package org.teilen.web.service;


import org.teilen.web.model.*;

/**
 * @author gentjan kolicaj
 */
public interface SignService {

    public abstract User signUp(User user, UserContact userContact, UserAddress userAddress, PasswordModel passwordModel, Long countryId, Organization organization) throws Exception;

    public abstract User signUp(User user, UserContact userContact, UserAddress userAddress, PasswordModel passwordModel, Long countryId) throws Exception;

    public abstract User signUp(User user, UserContact userContact, UserAddress userAddress, PasswordModel passwordModel, Long countryId, Organization organization, Credential credential) throws Exception;

    public abstract User signIn(String email, String password) throws Exception;


}
