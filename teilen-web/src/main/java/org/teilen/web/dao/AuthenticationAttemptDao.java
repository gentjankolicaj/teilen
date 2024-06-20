package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.AuthenticationAttempt;


/**
 * @author gentjan kolicaj
 */
public interface AuthenticationAttemptDao extends CrudDao<AuthenticationAttempt, Long> {

  List<AuthenticationAttempt> findByUserId(Long userId) throws Exception;

}
