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
import org.teilen.web.model.Department;
import org.teilen.web.service.DepartmentService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(DepartmentRestController.URI)
class DepartmentRestController {

  protected final static String URI = "/api/departments";

  private final DepartmentService departmentService;

  @Autowired
  public DepartmentRestController(DepartmentService departmentService) {
    super();
    this.departmentService = departmentService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<Department> getAllDepartments() throws Exception {
    return departmentService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public Department getDepartmentById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long departmentId = Long.parseLong(id);
      return departmentService.getById(departmentId);
    } else {
      throw new IdException("Department id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"name"}, method = RequestMethod.GET, produces = "application/json")
  public List<Department> getDepartmentsByNameLike(@RequestParam("name") String name)
      throws Exception { // /api/department?name=departmentName
    return departmentService.getDepartmentByName(name);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"creatorId"}, method = RequestMethod.GET, produces = "application/json")
  public List<Department> getDepartmentsByCreatorId(@RequestParam("creatorId") String creatorId)
      throws Exception { // /api/departments?creatorId=007
    if (NumberUtils.isParsable(creatorId)) {
      Long creatorID = Long.parseLong(creatorId);
      return departmentService.getDepartmentByCreatorId(creatorID);
    } else {
      throw new IdException("Creatort id " + creatorId + " is not parsable.Must be an integer.");
    }

  }

  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public Department saveDepartment(@RequestBody Department department) throws Exception {
    if (department == null) {
      throw new NullReferenceException("Department reference binded is null.");
    }

    return departmentService.create(department);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public Department editDepartment(@PathVariable("id") String id,
      @RequestBody Department department)
      throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long departmentId = Long.parseLong(id);
      if (department == null) {
        throw new NullReferenceException("Department reference binded is null.");
      }
      department.setId(departmentId);

      return departmentService.edit(department);
    } else {
      throw new IdException("Department id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteDepartment(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long departmentId = Long.parseLong(id);
      departmentService.deleteById(departmentId);
    } else {
      throw new IdException("Department id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

}
