package org.teilen.web.service;

import java.util.List;
import org.teilen.web.enums.HashFunction;
import org.teilen.web.model.Credential;
import org.teilen.web.model.User;

/**
 * @author gentjan kolicaj
 */
public interface PasswordService {


  String hashPassword(String password, HashFunction function) throws Exception;

  List<String> hashPasswords(List<String> passwords, HashFunction function)
      throws Exception;

  User hashPassword(User user, HashFunction function) throws Exception;

  Credential hashPassword(Credential credential, HashFunction hashFunction)
      throws Exception;


}
