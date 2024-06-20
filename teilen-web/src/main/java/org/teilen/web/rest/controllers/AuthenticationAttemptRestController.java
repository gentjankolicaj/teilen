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
import org.teilen.web.model.AuthenticationAttempt;
import org.teilen.web.service.AuthenticationAttemptService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(AuthenticationAttemptRestController.URI)
class AuthenticationAttemptRestController {  //controller classes are private package because aren't going to be used outside package

  protected static final String URI = "/api/auth_attempts";

  private final AuthenticationAttemptService authenticationAttemptService;

  @Autowired
  public AuthenticationAttemptRestController(
      AuthenticationAttemptService authenticationAttemptService) {
    super();
    this.authenticationAttemptService = authenticationAttemptService;
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<AuthenticationAttempt> getAllAttempts() throws Exception {

    return authenticationAttemptService.getAll();

  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
  public AuthenticationAttempt getAttemptById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long attemptId = Long.parseLong(id);
      return authenticationAttemptService.getById(attemptId);
    } else {
      throw new IdException("Authenctiaction attempt id " + id + " is not parsable to long.");
    }

  }

  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public AuthenticationAttempt saveAttempt(@RequestBody AuthenticationAttempt authenticationAttempt)
      throws Exception {
    if (authenticationAttempt == null) {
      throw new NullReferenceException("Authentication attempt reference binded is null.");
    }

    return authenticationAttemptService.create(authenticationAttempt);

  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = "application/json")
  public AuthenticationAttempt editAttempt(@PathVariable("id") String id,
      @RequestBody AuthenticationAttempt authenticationAttempt) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long attemptId = Long.parseLong(id);
      if (authenticationAttempt == null) {
        throw new NullReferenceException("Authentication attempt reference binded is null.");
      } else {
        authenticationAttempt.setId(attemptId);
        return authenticationAttemptService.edit(authenticationAttempt);

      }

    } else {
      throw new IdException("Authenctiaction attempt id " + id + " is not parsable to long.");
    }
  }


  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  public void deleteAttempt(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long attemptId = Long.parseLong(id);
      authenticationAttemptService.deleteById(attemptId);
    } else {
      throw new IdException("Authenctiaction attempt id " + id + " is not parsable to long.");
    }
  }


  @ResponseBody
  @RequestMapping("*") //all other unmaped requests at all other suburi-s
  public ResponseEntity<Object> unMappedRequests() {
    return new ResponseEntity<>("Wrong URI or wrong request method", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }


}
