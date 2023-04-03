package org.teilen.web.service;

import org.teilen.web.model.User;

/**
 * @author gentjan kolicaj
 */
public interface AuthenticationService {

    public abstract User authenticate(String email, String password) throws Exception;

}
