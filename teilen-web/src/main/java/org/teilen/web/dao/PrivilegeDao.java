package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.Privilege;

/**
 * @author gentjan kolicaj
 */
public interface PrivilegeDao extends CrudDao<Privilege, Long> {

  List<Privilege> findByIdentifierLike(String identifier) throws Exception;

}
