package org.teilen.web.dao;

import org.teilen.web.model.Group;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface GroupDao extends CrudDao<Group, Long> {

    public abstract List<Group> findByNameLike(String name) throws Exception;

}
