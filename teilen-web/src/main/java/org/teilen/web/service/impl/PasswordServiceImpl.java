package org.teilen.web.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.teilen.web.enums.HashFunction;
import org.teilen.web.model.Credential;
import org.teilen.web.model.User;
import org.teilen.web.service.PasswordService;

/**
 * @author gentjan kolicaj
 */
@Service
public class PasswordServiceImpl implements PasswordService {


  public PasswordServiceImpl() {
    super();
  }

  @Override
  public String hashPassword(String password, HashFunction function) throws Exception {
    if (function.equals(HashFunction.NONE)) {
      return password;
    } else {
      MessageDigest md = MessageDigest.getInstance(function.getValue());
      byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
      return convertToHex(hashedPassword);
    }
  }

  @Override
  public List<String> hashPasswords(List<String> passwords, HashFunction function)
      throws Exception {
    if (function.equals(HashFunction.NONE)) {
      return passwords;
    } else {
      MessageDigest md = MessageDigest.getInstance(function.getValue());
      List<String> hashedPasswordList = new ArrayList<>(passwords.size());
      for (String password : passwords) {
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        hashedPasswordList.add(convertToHex(hashedPassword));

      }
      return hashedPasswordList;
    }
  }

  @Override
  public User hashPassword(User user, HashFunction function) throws Exception {
    if (function.equals(HashFunction.NONE)) {
      return user;
    } else {
      MessageDigest md = MessageDigest.getInstance(function.getValue());
      String password = user.getCredential().getPassword();
      byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
      user.getCredential().setHashFunction(function.getValue());
      user.getCredential().setPassword(convertToHex(hashedPassword));
      return user;
    }
  }

  @Override
  public Credential hashPassword(Credential credential, HashFunction function) throws Exception {
    if (function.equals(HashFunction.NONE)) {
      return credential;
    } else {
      MessageDigest md = MessageDigest.getInstance(function.getValue());
      String password = credential.getPassword();
      byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
      credential.setPassword(convertToHex(hashedPassword));
      credential.setHashFunction(function.getValue());
      return credential;
    }
  }

  private String convertToHex(byte[] hashedPassword) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < hashedPassword.length; i++) {
      String hex = Integer.toHexString(0xff & hashedPassword[i]);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

}
