package org.teilen.web.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.AuthenticationAttemptDao;
import org.teilen.web.dao.UserContactDao;
import org.teilen.web.enums.AuthStatus;
import org.teilen.web.enums.Platform;
import org.teilen.web.exception.CredentialException;
import org.teilen.web.exception.UserContactException;
import org.teilen.web.exception.UserException;
import org.teilen.web.model.AuthenticationAttempt;
import org.teilen.web.model.User;
import org.teilen.web.model.UserContact;
import org.teilen.web.security.WebApplicationSecurityConfig;
import org.teilen.web.service.AuthenticationService;
import org.teilen.web.service.PasswordService;

/**
 * @author gentjan kolicaj
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserContactDao userContactDao;
  private final PasswordService passwordService;
  private final AuthenticationAttemptDao authenticationAttemptDao;

  @Autowired
  public AuthenticationServiceImpl(UserContactDao userContactDao, PasswordService passwordService,
      AuthenticationAttemptDao authenticationAttemptDao) {
    this.userContactDao = userContactDao;
    this.passwordService = passwordService;
    this.authenticationAttemptDao = authenticationAttemptDao;
  }

  @Transactional(noRollbackFor = Exception.class)
  //avoid transaction from rolling back.//because we need to keep record of attempts.
  @Override
  public User authenticate(String email, String password) throws Exception {
    AuthenticationAttempt attempt = null;

    String hashedPassword = passwordService.hashPassword(password,
        WebApplicationSecurityConfig.PASSWORD_HASHING_FUNCTION);

    try {

      UserContact userContact = userContactDao.findByEmail(email);

      if (userContact != null) {
        User user = userContact.getUser();
        if (user != null) {
          String tmpPass = user.getCredential().getPassword();
          if (tmpPass.equals(hashedPassword)) {
            attempt = new AuthenticationAttempt();
            attempt.setUser(user);
            attempt.setStatus(AuthStatus.SUCCESS.getValue());
            attempt.setPlatform(Platform.WEB.getValue());
            attempt.setCreationDate(new Date());
            authenticationAttemptDao.save(attempt);
            return user;
          } else {
            attempt = new AuthenticationAttempt();
            attempt.setUser(user);
            attempt.setStatus(AuthStatus.FAILED.getValue());
            attempt.setPlatform(Platform.WEB.getValue());
            attempt.setCreationDate(new Date());
            authenticationAttemptDao.save(attempt);
            throw new CredentialException("Passwords don't match.");
          }
        } else {
          throw new UserException("User with email " + email + " not found.");
        }

      } else {
        attempt = new AuthenticationAttempt();
        attempt.setEmailOrUsername(email);
        attempt.setUser(null);
        attempt.setPassword(password);
        attempt.setStatus(AuthStatus.FAILED_UNKNOWN.getValue());
        attempt.setPlatform(Platform.WEB.getValue());
        attempt.setCreationDate(new Date());
        authenticationAttemptDao.save(attempt);

        throw new UserContactException("User email " + email + " not found.");

      }

    } catch (Exception e) {

      attempt = new AuthenticationAttempt();
      attempt.setEmailOrUsername(email);
      attempt.setUser(null);
      attempt.setPassword(password);
      attempt.setStatus(AuthStatus.FAILED_UNKNOWN.getValue());
      attempt.setPlatform(Platform.WEB.getValue());
      attempt.setCreationDate(new Date());
      authenticationAttemptDao.save(attempt);

      throw new UserContactException("User email " + email + " not found.");
    }

  }

}
