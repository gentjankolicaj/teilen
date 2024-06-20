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
import org.teilen.web.model.Group;
import org.teilen.web.service.GroupService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(GroupRestController.URI)
class GroupRestController {

  protected static final String URI = "/api/groups";

  private final GroupService groupService;

  @Autowired
  public GroupRestController(GroupService groupService) {
    super();
    this.groupService = groupService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<Group> getAllGroups() throws Exception {
    return groupService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public Group getGroupById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long groupId = Long.parseLong(id);
      return groupService.getById(groupId);
    } else {
      throw new IdException("Group id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"name"}, method = RequestMethod.GET, produces = "application/json")
  public List<Group> getGroupsByNameLike(@RequestParam("name") String name)
      throws Exception { // /api/groups?name=groupName
    return groupService.getByNameLike(name);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {
      "show_members"}, method = RequestMethod.GET, produces = "application/json")
  public List<Group> getAllGroupsWithMembers(@RequestParam("show_members") String showMembers)
      throws Exception {
    if (showMembers.equalsIgnoreCase("true")) {
      return null; // todo : to fix groupService.getAllGroupsWithMembers(); LAZY issue with associated references
    } else {
      return null;
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public Group saveDepartment(@RequestBody Group group) throws Exception {
    if (group == null) {
      throw new NullReferenceException("Group reference binded is null.");
    }

    return groupService.create(group);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public Group editGroup(@PathVariable("id") String id, @RequestBody Group group) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long groupId = Long.parseLong(id);
      if (group == null) {
        throw new NullReferenceException("Group reference binded is null.");
      }
      group.setId(groupId);

      return groupService.edit(group);
    } else {
      throw new IdException("Group id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteGroup(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long groupId = Long.parseLong(id);
      groupService.deleteById(groupId);
    } else {
      throw new IdException("Group id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

}
