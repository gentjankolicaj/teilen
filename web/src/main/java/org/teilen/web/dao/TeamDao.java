package org.teilen.web.dao;

import org.teilen.web.model.Team;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface TeamDao extends CrudDao<Team, Long> {

    public abstract List<Team> findByNameLike(String name) throws Exception;

    public abstract List<Team> findByDepartmentId(Long departmentId) throws Exception;

}
