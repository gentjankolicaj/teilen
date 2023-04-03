package org.teilen.web.dao;

import org.teilen.web.model.Privilege;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface PrivilegeDao extends CrudDao<Privilege, Long> {

    public abstract List<Privilege> findByIdentifierLike(String identifier) throws Exception;

}
