package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.Department;


/**
 * @author gentjan kolicaj
 */
public interface DepartmentDao extends CrudDao<Department, Long> {

  List<Department> findByName(String name) throws Exception;

  List<Department> findByCreatorId(Long creatorId) throws Exception;

}
