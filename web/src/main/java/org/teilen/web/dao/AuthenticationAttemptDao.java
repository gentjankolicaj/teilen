package org.teilen.web.dao;

import org.teilen.web.model.AuthenticationAttempt;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface AuthenticationAttemptDao extends CrudDao<AuthenticationAttempt, Long> {

    public abstract List<AuthenticationAttempt> findByUserId(Long userId) throws Exception;

}
