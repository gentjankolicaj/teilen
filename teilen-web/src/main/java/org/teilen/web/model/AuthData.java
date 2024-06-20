package org.teilen.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Data
@NoArgsConstructor
public class AuthData {

  private String password;
  private String email;

}
