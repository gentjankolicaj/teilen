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
import org.teilen.web.model.User;
import org.teilen.web.service.UserService;

/**
 * @author gentjan kolicaj
 */
@Controller                                   // @RestController=@Controller+@ResponseBody
@RequestMapping(UserRestController.URI)
class UserRestController {

  protected static final String URI = "/api/users";

  private final UserService userService;

  @Autowired
  public UserRestController(UserService userService) {
    super();
    this.userService = userService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<User> getAllUsers() throws Exception {
    return userService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public User getUserById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long userId = Long.parseLong(id);
      return userService.getById(userId);
    } else {
      throw new IdException("User id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"firstname"}, method = RequestMethod.GET, produces = "application/json")
  public List<User> getUserByFirstNameLike(@RequestParam("firstname") String firstName)
      throws Exception {
    return userService.getByFirstNameLike(firstName);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"lastname"}, method = RequestMethod.GET, produces = "application/json")
  public List<User> getUserByLastNameLike(@RequestParam("lastname") String lastName)
      throws Exception {
    return userService.getByLastNameLike(lastName);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"username"}, method = RequestMethod.GET, produces = "application/json")
  public List<User> getUserByUsername(@RequestParam("username") String username) throws Exception {
    return userService.getByUsername(username);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {
      "username_like"}, method = RequestMethod.GET, produces = "application/json")
  public List<User> getUserByUsernameLike(@RequestParam("username_like") String usernameLike)
      throws Exception {
    return userService.getByUsername(usernameLike);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public User saveUser(@RequestBody User user) throws Exception {
    if (user == null) {
      throw new NullReferenceException("User reference binded is null.");
    }

    return userService.create(user);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public User editUser(@PathVariable("id") String id, @RequestBody User user) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long userId = Long.parseLong(id);
      if (user == null) {
        throw new NullReferenceException("User reference binded is null.");
      }
      user.setId(userId);

      return userService.edit(user);
    } else {
      throw new IdException("User id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteUser(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long userId = Long.parseLong(id);
      userService.deleteById(userId);
    } else {
      throw new IdException("User id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("/*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

}
