package org.teilen.web.service;

import org.teilen.web.model.User;

/**
 * @author gentjan kolicaj
 */
public interface AuthenticationService {

  User authenticate(String email, String password) throws Exception;

}
