package org.teilen.web.dao;

import org.teilen.web.model.Department;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface DepartmentDao extends CrudDao<Department, Long> {

    public abstract List<Department> findByName(String name) throws Exception;

    public abstract List<Department> findByCreatorId(Long creatorId) throws Exception;

}
