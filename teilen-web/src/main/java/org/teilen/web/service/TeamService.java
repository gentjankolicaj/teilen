package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Department;
import org.teilen.web.model.Team;
import org.teilen.web.model.User;

/**
 * @author gentjan kolicaj
 */
public interface TeamService {

  List<Team> getAll() throws Exception;

  List<Team> getAllById(List<Long> ids) throws Exception;

  Team getById(Long id) throws Exception;

  Team create(Team entity) throws Exception;

  List<Team> createAll(List<Team> entities) throws Exception;

  Team edit(Team entity) throws Exception;

  List<Team> editAll(List<Team> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Team entity) throws Exception;

  void deleteAll(List<Team> entities) throws Exception;

  boolean existById(Long id) throws Exception;

  //===========================================================
  List<Team> getByNameLike(String name) throws Exception;

  List<Team> getByDepartmentId(Long departmentId) throws Exception;

  Team createTeam(User creator, Department department, String name,
      String description) throws Exception;

  Team deleteTeam(Team team) throws Exception;

  Team deleteTeam(Long teamId) throws Exception;

  Team editTeam(Team team, User creator, Department department, String name,
      String description) throws Exception;

  Team editTeam(Long teamId, User creator, Department department, String name,
      String description) throws Exception;


}
