package org.teilen.web.service;

import org.teilen.web.enums.HashFunction;
import org.teilen.web.model.Credential;
import org.teilen.web.model.User;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface PasswordService {


    public abstract String hashPassword(String password, HashFunction function) throws Exception;

    public abstract List<String> hashPasswords(List<String> passwords, HashFunction function) throws Exception;

    public abstract User hashPassword(User user, HashFunction function) throws Exception;

    public abstract Credential hashPassword(Credential credential, HashFunction hashFunction) throws Exception;


}
