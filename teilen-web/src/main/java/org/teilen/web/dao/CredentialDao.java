package org.teilen.web.dao;

import org.teilen.web.model.Credential;

/**
 * @author gentjan kolicaj
 */
public interface CredentialDao extends CrudDao<Credential, Long> {

  Credential findByUserId(Long userId) throws Exception;
}
