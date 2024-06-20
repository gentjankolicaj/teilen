package org.teilen.web.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.MyAbstractWebAppContext;
import org.teilen.web.model.AuthenticationAttempt;


@Transactional
public class AuthenticationAttemptDaoImplTest extends MyAbstractWebAppContext {

  @Autowired
  private AuthenticationAttemptDao attemptDao;


  @Test
  public void testFindAll() throws Exception {
    int expectedValue = 0;
    List<AuthenticationAttempt> attempts = attemptDao.findAll();

    assertEquals(expectedValue, attempts.size());
  }


}
