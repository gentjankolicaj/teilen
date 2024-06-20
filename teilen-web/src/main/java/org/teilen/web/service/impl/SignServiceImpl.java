package org.teilen.web.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.enums.AuthStatus;
import org.teilen.web.enums.HashFunction;
import org.teilen.web.enums.Platform;
import org.teilen.web.model.AuthenticationAttempt;
import org.teilen.web.model.Country;
import org.teilen.web.model.Credential;
import org.teilen.web.model.Organization;
import org.teilen.web.model.PasswordModel;
import org.teilen.web.model.User;
import org.teilen.web.model.UserAddress;
import org.teilen.web.model.UserContact;
import org.teilen.web.security.WebApplicationSecurityConfig;
import org.teilen.web.service.AuthenticationAttemptService;
import org.teilen.web.service.CountryService;
import org.teilen.web.service.CredentialService;
import org.teilen.web.service.OrganizationService;
import org.teilen.web.service.PasswordService;
import org.teilen.web.service.SignService;
import org.teilen.web.service.UserAdressService;
import org.teilen.web.service.UserContactService;
import org.teilen.web.service.UserService;

/**
 * @author gentjan kolicaj
 */
@Service
public class SignServiceImpl implements SignService {

  private final UserService userService;
  private final UserAdressService userAdressService;
  private final UserContactService userContactService;

  private final OrganizationService organizationService;
  private final CountryService countryService;
  private final CredentialService credentialService;
  private final AuthenticationAttemptService authAttemptService;
  private final PasswordService passwordService;


  @Autowired
  public SignServiceImpl(UserService userService, UserAdressService userAdressService,
      UserContactService userContactService, OrganizationService organizationService,
      CountryService countryService, CredentialService credentialService,
      AuthenticationAttemptService authAttemptService, PasswordService passwordService) {
    super();
    this.userService = userService;
    this.userAdressService = userAdressService;
    this.userContactService = userContactService;
    this.organizationService = organizationService;
    this.countryService = countryService;
    this.credentialService = credentialService;
    this.authAttemptService = authAttemptService;
    this.passwordService = passwordService;
  }

  @Transactional(rollbackFor = Exception.class) //rolls back transactions for every exception
  @Override
  public User signUp(User user, UserContact userContact, UserAddress userAddress,
      PasswordModel passwordModel,
      Long countryId, Organization organization) throws Exception {

    Country country = countryService.getById(countryId);

    Credential credential = new Credential();
    credential.setPassword(passwordModel.getPassword());
    credential.setCreationDate(new Date());
    credential.setHashFunction(HashFunction.NONE.getValue());

    //hashPassword
    credential = passwordService.hashPassword(credential,
        WebApplicationSecurityConfig.PASSWORD_HASHING_FUNCTION);
    credential.setUser(user);

    user.setCreationDate(new Date());
    user.setCredential(credential);
    User tmp = userService.create(user);

    credentialService.create(credential);

    userAddress.setCreationDate(new Date());
    userAddress.setUser(user);
    userAddress.setCountry(country);
    userAdressService.create(userAddress);

    userContact.setCreationDate(new Date());
    userContact.setUser(user);
    userContactService.create(userContact);

    organization.setCreationDate(new Date());
    organization.setCreator(user);
    organization.setCountry(country);
    organizationService.create(organization);

    AuthenticationAttempt firstAuthAttempt = new AuthenticationAttempt();
    firstAuthAttempt.setCreationDate(new Date());
    firstAuthAttempt.setStatus(AuthStatus.SUCCESS.getValue());
    firstAuthAttempt.setUser(user);
    firstAuthAttempt.setPlatform(Platform.WEB.getValue());
    authAttemptService.create(firstAuthAttempt);

    return tmp;
  }

  @Override
  public User signIn(String email, String password) throws Exception {
    return null;

  }

  @Override
  public User signUp(User user, UserContact userContact, UserAddress userAddress,
      PasswordModel passwordModel,
      Long countryId, Organization organization, Credential credential) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public User signUp(User user, UserContact userContact, UserAddress userAddress,
      PasswordModel passwordModel,
      Long countryId) throws Exception {
    Country country = countryService.getById(countryId);

    Credential credential = new Credential();
    credential.setPassword(passwordModel.getPassword());
    credential.setCreationDate(new Date());
    credential.setHashFunction(HashFunction.NONE.getValue());

    //hashPassword
    credential = passwordService.hashPassword(credential,
        WebApplicationSecurityConfig.PASSWORD_HASHING_FUNCTION);
    credential.setUser(user);

    user.setCreationDate(new Date());
    user.setCredential(credential);
    User tmp = userService.create(user);

    credentialService.create(credential);

    userAddress.setCreationDate(new Date());
    userAddress.setUser(user);
    userAddress.setCountry(country);
    userAdressService.create(userAddress);

    userContact.setCreationDate(new Date());
    userContact.setUser(user);
    userContactService.create(userContact);

    AuthenticationAttempt firstAuthAttempt = new AuthenticationAttempt();
    firstAuthAttempt.setCreationDate(new Date());
    firstAuthAttempt.setStatus(AuthStatus.SUCCESS.getValue());
    firstAuthAttempt.setUser(user);
    firstAuthAttempt.setPlatform(Platform.WEB.getValue());
    authAttemptService.create(firstAuthAttempt);

    return tmp;
  }


}
