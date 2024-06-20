package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.Group;

/**
 * @author gentjan kolicaj
 */
public interface GroupDao extends CrudDao<Group, Long> {

  List<Group> findByNameLike(String name) throws Exception;

}
