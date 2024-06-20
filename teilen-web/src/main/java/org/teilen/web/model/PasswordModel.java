package org.teilen.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Data
@NoArgsConstructor
public class PasswordModel {

  private String password;
  private String rePassword;

}
