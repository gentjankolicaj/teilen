package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.Team;


/**
 * @author gentjan kolicaj
 */
public interface TeamDao extends CrudDao<Team, Long> {

  List<Team> findByNameLike(String name) throws Exception;

  List<Team> findByDepartmentId(Long departmentId) throws Exception;

}
