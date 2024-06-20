package org.teilen.web.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.TeamDao;
import org.teilen.web.exception.TeamException;
import org.teilen.web.model.Department;
import org.teilen.web.model.Team;
import org.teilen.web.model.User;
import org.teilen.web.service.TeamService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class TeamServiceImpl implements TeamService {

  private final TeamDao teamDao;

  @Autowired
  public TeamServiceImpl(TeamDao teamDao) {
    this.teamDao = teamDao;
  }

  @Override
  public List<Team> getAll() throws Exception {
    return teamDao.findAll();
  }

  @Override
  public List<Team> getAllById(List<Long> ids) throws Exception {
    return teamDao.findAllById(ids);
  }

  @Override
  public Team getById(Long id) throws Exception {
    return teamDao.findById(id);
  }

  @Override
  public Team create(Team entity) throws Exception {
    return teamDao.save(entity);
  }

  @Override
  public List<Team> createAll(List<Team> entities) throws Exception {
    return teamDao.saveAll(entities);
  }

  @Override
  public Team edit(Team entity) throws Exception {
    return teamDao.update(entity);
  }

  @Override
  public List<Team> editAll(List<Team> entities) throws Exception {
    return teamDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    teamDao.deleteById(id);

  }

  @Override
  public void delete(Team entity) throws Exception {
    teamDao.delete(entity);

  }

  @Override
  public void deleteAll(List<Team> entities) throws Exception {
    teamDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return teamDao.existById(id);
  }

  @Override
  public List<Team> getByNameLike(String name) throws Exception {
    return teamDao.findByNameLike(name);
  }

  @Override
  public List<Team> getByDepartmentId(Long departmentId) throws Exception {
    return teamDao.findByDepartmentId(departmentId);
  }

  @Override
  public Team createTeam(User creator, Department department, String name, String description)
      throws Exception {
    Team team = new Team();
    team.setDepartment(department);
    team.setCreator(creator);
    team.setName(name);
    team.setDescription(description);
    team.setCreationDate(new Date());
    team.setDeletionDate(null);
    team.setModificationDate(null);

    return teamDao.save(team);

  }

  @Override
  public Team deleteTeam(Team team) throws Exception {
    if (team == null) {
      throw new TeamException("Team dosen't exist.Reference is null.");
    }

    team.setModificationDate(new Date());
    team.setDeletionDate(new Date());

    return teamDao.update(team);
  }

  @Override
  public Team deleteTeam(Long teamId) throws Exception {
    Team tmp = teamDao.findById(teamId);
    if (tmp == null) {
      throw new TeamException("Team dosen't exist.Reference is null.");
    }

    tmp.setModificationDate(new Date());
    tmp.setDeletionDate(new Date());

    return teamDao.update(tmp);
  }

  @Override
  public Team editTeam(Team team, User creator, Department department, String name,
      String description)
      throws Exception {
    if (team == null) {
      throw new TeamException("Team dosen't exist.Reference is null.");
    }

    team.setDepartment(department);
    team.setCreator(creator);
    team.setName(name);
    team.setDescription(description);
    team.setModificationDate(new Date());

    return teamDao.update(team);
  }

  @Override
  public Team editTeam(Long teamId, User creator, Department department, String name,
      String description)
      throws Exception {
    Team tmp = teamDao.findById(teamId);
    if (tmp == null) {
      throw new TeamException("Team dosen't exist.Reference is null.");
    }

    tmp.setDepartment(department);
    tmp.setCreator(creator);
    tmp.setName(name);
    tmp.setDescription(description);
    tmp.setModificationDate(new Date());

    return teamDao.update(tmp);
  }

}
