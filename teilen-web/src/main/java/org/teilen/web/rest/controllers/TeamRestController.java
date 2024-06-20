package org.teilen.web.rest.controllers;

import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.teilen.web.exception.IdException;
import org.teilen.web.exception.NullReferenceException;
import org.teilen.web.model.Team;
import org.teilen.web.service.TeamService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(TeamRestController.URI)
class TeamRestController {

  protected final static String URI = "/api/teams";

  private final TeamService teamService;

  @Autowired
  public TeamRestController(TeamService teamService) {
    super();
    this.teamService = teamService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<Team> getAllTeams() throws Exception {
    return teamService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public Team getTeamById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long teamId = Long.parseLong(id);
      return teamService.getById(teamId);
    } else {
      throw new IdException("Team id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"name"}, method = RequestMethod.GET, produces = "application/json")
  public List<Team> getTeamsByName(@RequestParam("name") String name) throws Exception {
    return teamService.getByNameLike(name);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = "departmentId", method = RequestMethod.GET, produces = "application/json")
  public List<Team> getTeamsByDepartmentId(@RequestParam("departmentId") String departmentId)
      throws Exception {
    if (NumberUtils.isParsable(departmentId)) {
      Long depId = Long.parseLong(departmentId);
      return teamService.getByDepartmentId(depId);
    } else {
      throw new IdException(
          "Department id " + departmentId + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public Team saveTeam(@RequestBody Team team) throws Exception {
    if (team == null) {
      throw new NullReferenceException("Team reference binded is null.");
    }

    return teamService.create(team);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public Team editTeam(@PathVariable("id") String id, @RequestBody Team team) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long teamId = Long.parseLong(id);
      if (team == null) {
        throw new NullReferenceException("Privilege reference binded is null.");
      }
      team.setId(teamId);

      return teamService.edit(team);
    } else {
      throw new IdException("Team id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteTeam(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long teamId = Long.parseLong(id);
      teamService.deleteById(teamId);
    } else {
      throw new IdException("Team id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("/*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

}
