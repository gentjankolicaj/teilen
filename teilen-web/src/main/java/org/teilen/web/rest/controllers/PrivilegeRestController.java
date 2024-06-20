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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.teilen.web.exception.IdException;
import org.teilen.web.exception.NullReferenceException;
import org.teilen.web.model.Privilege;
import org.teilen.web.service.PrivilegeService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(PrivilegeRestController.URI)
class PrivilegeRestController {

  protected final static String URI = "/api/privileges";

  private final PrivilegeService privilegeService;

  @Autowired
  public PrivilegeRestController(PrivilegeService privilegeService) {
    super();
    this.privilegeService = privilegeService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<Privilege> getAllPrivileges() throws Exception {
    return privilegeService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public Privilege getPrivilegesById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long privilegeId = Long.parseLong(id);
      return privilegeService.getById(privilegeId);
    } else {
      throw new IdException("Privilege id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public Privilege savePrivilege(@RequestBody Privilege privilege) throws Exception {
    if (privilege == null) {
      throw new NullReferenceException("Privilege reference binded is null.");
    }

    return privilegeService.create(privilege);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public Privilege editPrivilege(@PathVariable("id") String id, @RequestBody Privilege privilege)
      throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long privilegeId = Long.parseLong(id);
      if (privilege == null) {
        throw new NullReferenceException("Privilege reference binded is null.");
      }
      privilege.setId(privilegeId);

      return privilegeService.edit(privilege);
    } else {
      throw new IdException("Privilege id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteOrganization(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long privilegeId = Long.parseLong(id);
      privilegeService.deleteById(privilegeId);
    } else {
      throw new IdException("Privilege id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("/*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

}
