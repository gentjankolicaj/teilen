package org.teilen.web.service;

import org.teilen.web.model.Department;
import org.teilen.web.model.Team;
import org.teilen.web.model.User;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface TeamService {

    public abstract List<Team> getAll() throws Exception;

    public abstract List<Team> getAllById(List<Long> ids) throws Exception;

    public abstract Team getById(Long id) throws Exception;

    public abstract Team create(Team entity) throws Exception;

    public abstract List<Team> createAll(List<Team> entities) throws Exception;

    public abstract Team edit(Team entity) throws Exception;

    public abstract List<Team> editAll(List<Team> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(Team entity) throws Exception;

    public abstract void deleteAll(List<Team> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;

    //===========================================================
    public abstract List<Team> getByNameLike(String name) throws Exception;

    public abstract List<Team> getByDepartmentId(Long departmentId) throws Exception;

    public abstract Team createTeam(User creator, Department department, String name, String description) throws Exception;

    public abstract Team deleteTeam(Team team) throws Exception;

    public abstract Team deleteTeam(Long teamId) throws Exception;

    public abstract Team editTeam(Team team, User creator, Department department, String name, String description) throws Exception;

    public abstract Team editTeam(Long teamId, User creator, Department department, String name, String description) throws Exception;


}
